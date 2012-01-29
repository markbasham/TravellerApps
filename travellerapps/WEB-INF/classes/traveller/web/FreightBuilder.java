package traveller.web;

import java.io.StringWriter;

import org.apache.velocity.VelocityContext;
import org.mla.html.table.Table;

import traveller.trade.generators.Freight;
import traveller.trade.generators.FreightItem;

public class FreightBuilder {
	
	private static Table tableGen(Freight freight) {
		
		Table table = new Table(2, 3);
		table.setGrow();
		
		int row = 0;
		
		// World name part
		table.setCell(TableUtils.cellGen(String.format("Freight from %s to %s",
				freight.getCurrent().getName(),
				freight.getDestination().getName()),
				TableUtils.HEADING, 1, 3), row++, 0);

		// Set up headers
		table.setCell(TableUtils.cellGen("FreightLot",TableUtils.DESCRIPTOR), row, 0);
		table.setCell(TableUtils.cellGen("Tons",TableUtils.DESCRIPTOR), row, 1);
		table.setCell(TableUtils.cellGen("Contents",TableUtils.DESCRIPTOR), row++, 2);
		Integer freightNumber = 0;
		for (FreightItem lot : freight.getFreightItems()) {
			table.setCell(TableUtils.cellGen(Integer.toString(freightNumber),TableUtils.DESCRIPTOR), row, 0);
			table.setCell(TableUtils.cellGen(Integer.toString(lot.getTons()),TableUtils.VALUE), row, 1);
			table.setCell(TableUtils.cellGen(lot.getGood().getGood().getType(),TableUtils.VALUE), row++, 2);
			freightNumber++;
		}
		
		return table;
	}

	public static String build(Freight freight) throws Exception {

	    // add the table to the context
		VelocityContext context = new VelocityContext();
		context.put("dataTable",FreightBuilder.tableGen(freight));
		context.put("tableWidth",800);

		// then get the string out and return it
	    StringWriter writer = new StringWriter();
	    TableUtils.getTableTemplate().merge( context, writer );	
	    return writer.toString();		
	}
	
}
