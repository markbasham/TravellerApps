package traveller.web;

import java.io.StringWriter;

import org.apache.velocity.VelocityContext;
import org.mla.html.table.Table;

import traveller.TravellerFactory;
import traveller.subsector.Subsector;
import traveller.trade.TradeGood;
import traveller.trade.generators.DefinedTradeItem;
import traveller.trade.generators.Trade;
import traveller.trade.generators.TradeItem;
import traveller.world.World;

public class TradeBuilder {

	private static Table traderTableGen(Trade trade) {

		Table table = new Table(2, 3);
		table.setGrow();

		int row = 0;

		// World name part
		table.setCell(TableUtils.cellGen(String.format("Trade Goods Available from %s",
				trade.getCurrent().getName()),
				TableUtils.HEADING, 1, 9), row++, 0);

		// Set up headers
		table.setCell(TableUtils.cellGen("Item",TableUtils.DESCRIPTOR), row, 0);
		table.setCell(TableUtils.cellGen("Tons",TableUtils.DESCRIPTOR), row, 1);
		table.setCell(TableUtils.cellGen("Purchase DM",TableUtils.DESCRIPTOR), row, 2);
		table.setCell(TableUtils.cellGen("Sale DM",TableUtils.DESCRIPTOR), row, 3);
		table.setCell(TableUtils.cellGen("Risk Assesment DM",TableUtils.DESCRIPTOR), row, 4);
		table.setCell(TableUtils.cellGen("Dangerous Goods DM",TableUtils.DESCRIPTOR), row,5);
		table.setCell(TableUtils.cellGen("Defined Good",TableUtils.DESCRIPTOR), row, 6);
		table.setCell(TableUtils.cellGen("Tons",TableUtils.DESCRIPTOR), row, 7);
		table.setCell(TableUtils.cellGen("Cost",TableUtils.DESCRIPTOR), row++, 8);		

		for (TradeItem item : trade.getItems()) {
			table.setCell(TableUtils.cellGen(item.getGood().getType(),TableUtils.DESCRIPTOR), row, 0);
			table.setCell(TableUtils.cellGen(Integer.toString(item.getTons()),TableUtils.VALUE), row, 1);
			table.setCell(TableUtils.cellGen(Integer.toString(item.getPurchaseDM()),TableUtils.VALUE), row, 2);
			table.setCell(TableUtils.cellGen(Integer.toString(item.getSaleDM()),TableUtils.VALUE), row, 3);
			table.setCell(TableUtils.cellGen(Integer.toString(item.getGood().getMaxRiskAssesmentDM()),TableUtils.VALUE), row, 4);
			table.setCell(TableUtils.cellGen(Integer.toString(item.getGood().getDangerousGoodsDM()),TableUtils.VALUE), row, 5);
			for (DefinedTradeItem defItem : item.getDefinedItems()) {
				table.setCell(TableUtils.cellGen(defItem.getDefinedGood().getDescription(),TableUtils.VALUE), row, 6);
				table.setCell(TableUtils.cellGen(Integer.toString(defItem.getTons()),TableUtils.VALUE), row, 7);
				table.setCell(TableUtils.cellGen(Integer.toString(defItem.getDefinedGood().getBasePrice()),TableUtils.VALUE), row++, 8);
			}
		}

		return table;
	}

	private static Table saleTableGen(World world) {

		Table table = new Table(2, 3);
		table.setGrow();

		int row = 0;

		// World name part
		table.setCell(TableUtils.cellGen(String.format("Trade Sale Modifiers for %s",
				world.getName()),
				TableUtils.HEADING, 1, 3), row++, 0);

		// Set up headers
		table.setCell(TableUtils.cellGen("Item",TableUtils.DESCRIPTOR), row, 0);
		table.setCell(TableUtils.cellGen("Purchase DM",TableUtils.DESCRIPTOR), row, 1);
		table.setCell(TableUtils.cellGen("Sale DM",TableUtils.DESCRIPTOR), row++, 2);

		for (TradeGood good : TravellerFactory.getTradeGoods().getTradeGoods().values()) {
			int pdm = good.getPurchaseDMValue(world);
			int sdm = good.getSaleDMValue(world);
			if((sdm != 0) || (pdm != 0)) {
				table.setCell(TableUtils.cellGen(good.getType(),TableUtils.DESCRIPTOR), row, 0);
				table.setCell(TableUtils.cellGen(Integer.toString(pdm),TableUtils.VALUE), row, 1);
				table.setCell(TableUtils.cellGen(Integer.toString(sdm),TableUtils.VALUE), row++, 2);
			}
		}
		return table;
	}

	
	private static Object saleTableComparisonGen(World world,
			Subsector subsector) {
		
		World[] comparisonWorlds = subsector.getWorldArray();
		
		Table table = new Table(1, 1);
		table.setGrow();

		int row = 0;

		// World name part
		table.setCell(TableUtils.cellGen(String.format("Trade Sale Modifiers for %s for %s",
				world.getName(),subsector.getName()),
				TableUtils.HEADING, 1, comparisonWorlds.length+2), row++, 0);

		// Set up headers
		table.setCell(TableUtils.cellGen("Item",TableUtils.DESCRIPTOR,2,1), row, 0);
		table.setCell(TableUtils.cellGen("Purchase Mods",TableUtils.DESCRIPTOR), row, 1);
		table.setCell(TableUtils.cellGen("Sale Mods",TableUtils.DESCRIPTOR,1,comparisonWorlds.length), row++, 2);
		
		
		int col = 1;
		table.setCell(TableUtils.cellGen(world.getName(),TableUtils.DESCRIPTOR), row, col++);
		for(int i = 0; i < comparisonWorlds.length; i++) {
			table.setCell(TableUtils.cellGen(LinkUtils.generateJumpFreightLink(world,comparisonWorlds[i],comparisonWorlds[i].getName(),"world"),TableUtils.DESCRIPTOR), row, col++);			
		}
		row++;

		for (TradeGood good : TravellerFactory.getTradeGoods().getTradeGoods().values()) {
			
			col = 0;
			table.setCell(TableUtils.cellGen(good.getType(),TableUtils.DESCRIPTOR), row, col++);
			
			int purchaseMod = good.getPurchaseDMValue(world)-good.getSaleDMValue(world);
			table.setCell(TableUtils.cellGen(Integer.toString(purchaseMod),TableUtils.VALUE), row, col++);
			
			for(int i = 0; i < comparisonWorlds.length; i++) {
				int saleMod = good.getSaleDMValue(comparisonWorlds[i])-good.getPurchaseDMValue(comparisonWorlds[i]);
				table.setCell(TableUtils.cellGen(Integer.toString(saleMod),TableUtils.VALUE), row, col++);
			}		
			row++;
		}
		
		return table;
		
	}

	
	public static String build(Trade trade) throws Exception {

		// add the table to the context
		VelocityContext context = new VelocityContext();
		context.put("dataTable",TradeBuilder.traderTableGen(trade));
		context.put("tableWidth",800);

		// then get the string out and return it
		StringWriter writer = new StringWriter();
		TableUtils.getTableTemplate().merge( context, writer );	
		return writer.toString();		
	}

	public static String buildSaleTable(World world) throws Exception {

		// add the table to the context
		VelocityContext context = new VelocityContext();
		context.put("dataTable",TradeBuilder.saleTableGen(world));
		context.put("tableWidth",400);

		// then get the string out and return it
		StringWriter writer = new StringWriter();
		TableUtils.getTableTemplate().merge( context, writer );	
		return writer.toString();		
	}

	public static String buildSaleComparisonTable(World world, Subsector comparisonWorlds) throws Exception {

		// add the table to the context
		VelocityContext context = new VelocityContext();
		context.put("dataTable",TradeBuilder.saleTableComparisonGen(world,comparisonWorlds));
		context.put("tableWidth",800);

		// then get the string out and return it
		StringWriter writer = new StringWriter();
		TableUtils.getTableTemplate().merge( context, writer );	
		return writer.toString();		
	}
	
}
