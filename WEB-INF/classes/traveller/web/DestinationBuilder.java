package traveller.web;

import java.io.StringWriter;

import org.apache.velocity.VelocityContext;
import org.mla.html.table.Table;

import traveller.trade.generators.Freight;
import traveller.trade.generators.Passengers;
import traveller.world.World;

public class DestinationBuilder {
	
	private static Table tableGen(World current, World destination, int enviromentalModifier, boolean includeDescription) throws Exception {

		Table table = new Table(1, 1);
		table.setGrow();

		int row = 0;

		// World name part
		table.setCell(TableUtils.cellGen(String.format("%s : Destination world Information",destination.getName()), TableUtils.TITLE, 1, 2), row++, 0);

		if(includeDescription) {
			// World and purchase DM's Information
			table.setCell(TableUtils.cellGen(WorldBuilder.build(destination),TableUtils.VALUE), row, 0);
			table.setCell(TableUtils.cellGen(TradeBuilder.buildSaleTable(destination),TableUtils.VALUE), row++, 1);
		}
			
		// Freight and Passengers
		table.setCell(TableUtils.cellGen(PassengerBuilder.build(new Passengers(current, destination, enviromentalModifier)),TableUtils.VALUE), row++, 0);
		table.setCell(TableUtils.cellGen(FreightBuilder.build(new Freight(current, destination)),TableUtils.VALUE), row++, 0);
		
		return table;
	}	
	
	
	public static String build(World current, World destination, int enviromentalModifier) throws Exception {
		return build(current, destination, enviromentalModifier, true);
	}
	
	public static String build(World current, World destination, int enviromentalModifier, boolean includeDescription) throws Exception {

		// add the table to the context
		VelocityContext context = new VelocityContext();
		context.put("dataTable",DestinationBuilder.tableGen(current,destination,enviromentalModifier,includeDescription));
		context.put("tableWidth",800);

		// then get the string out and return it
		StringWriter writer = new StringWriter();
		TableUtils.getTableTemplate().merge( context, writer );	
		return writer.toString();		
	}

}
