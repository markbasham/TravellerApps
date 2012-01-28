package traveller.web;

import java.io.StringWriter;

import org.apache.velocity.VelocityContext;
import org.mla.html.table.Table;

import traveller.trade.generators.Trade;
import traveller.world.World;

public class CurrentBuilder {

	private static Table tableGen(World world, int numberOfTraders) throws Exception {

		Table table = new Table(1, 1);
		table.setGrow();

		int row = 0;

		// World name part
		table.setCell(TableUtils.cellGen(String.format("%s : World and Trader Information" , world.getName()), TableUtils.TITLE, 1, 2), row++, 0);

		// World and prchase DM's Information
		table.setCell(TableUtils.cellGen(WorldBuilder.build(world),TableUtils.VALUE), row, 0);
		table.setCell(TableUtils.cellGen(TradeBuilder.buildSaleTable(world),TableUtils.VALUE), row++, 1);

		// traders
		for(int i = 0; i < numberOfTraders; i++) {
			table.setCell(TableUtils.cellGen(TradeBuilder.build(new Trade(world)),TableUtils.VALUE,1,2), row++, 0);
		}

		return table;
	}

	public static String build(World world, int numberOfTraders) throws Exception {
		
		// add the table to the context
		VelocityContext context = new VelocityContext();
		context.put("dataTable",CurrentBuilder.tableGen(world,numberOfTraders));
		context.put("tableWidth",1000);

		// then get the string out and return it
		StringWriter writer = new StringWriter();
		TableUtils.getTableTemplate().merge( context, writer );	
		return writer.toString();		
	}

}
