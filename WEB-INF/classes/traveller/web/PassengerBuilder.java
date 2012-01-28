package traveller.web;

import java.io.StringWriter;

import org.apache.velocity.VelocityContext;
import org.mla.html.table.Table;

import traveller.trade.generators.Passenger;
import traveller.trade.generators.Passengers;

public class PassengerBuilder {
	
	private static Table tableGen(Passengers passengers) {
		
		Table table = new Table(1, 3);
		table.setGrow();
		
		int row = 0;
		
		// World name part
		table.setCell(TableUtils.cellGen(String.format("Standard Passengers from %s to %s",
				passengers.getCurrent().getName(),
				passengers.getDestination().getName()),
				TableUtils.HEADING, 1, 3), row++, 0);

		// number of passengers
		table.setCell(TableUtils.cellGen("Low Passengers",TableUtils.DESCRIPTOR), row, 0);
		table.setCell(TableUtils.cellGen(Integer.toString(passengers.getNumberOfLowPassengers()),TableUtils.VALUE,1,2), row++, 1);
		table.setCell(TableUtils.cellGen("Middle Passengers",TableUtils.DESCRIPTOR), row, 0);
		table.setCell(TableUtils.cellGen(Integer.toString(passengers.getNumberOfMiddlePassengers()),TableUtils.VALUE,1,2), row++, 1);
		table.setCell(TableUtils.cellGen("High Passengers",TableUtils.DESCRIPTOR), row, 0);
		table.setCell(TableUtils.cellGen(Integer.toString(passengers.getNumberOfHighPassengers()),TableUtils.VALUE,1,2), row++, 1);
		
		// Set up headers
		table.setCell(TableUtils.cellGen("Outstanding Passengers",TableUtils.HEADING, 1, 3), row++, 0);
		table.setCell(TableUtils.cellGen("Passenger Class",TableUtils.DESCRIPTOR), row, 0);
		table.setCell(TableUtils.cellGen("Passenger",TableUtils.DESCRIPTOR), row, 1);
		table.setCell(TableUtils.cellGen("Transit Hazzard DM",TableUtils.DESCRIPTOR), row++, 2);
		for (Passenger passenger : passengers.getOutstandingPassengers()) {
			table.setCell(TableUtils.cellGen(passenger.getTravelClass(),TableUtils.VALUE), row, 0);
			table.setCell(TableUtils.cellGen(passenger.getDescription(),TableUtils.VALUE), row, 1);
			table.setCell(TableUtils.cellGen(Integer.toString(passenger.getType().getTransitHazardDM()),TableUtils.VALUE), row++, 2);
			
			// if there are any skills
			for (String skill : passenger.getSkills().keySet()) {
				table.setCell(TableUtils.cellGen(skill,TableUtils.VALUE), row, 1);
				table.setCell(TableUtils.cellGen(Integer.toString(passenger.getSkills().get(skill)),TableUtils.VALUE), row++, 2);
			}
		}
		
		return table;
	}

	public static String build(Passengers passengers) throws Exception {


	    // add the table to the context
		VelocityContext context = new VelocityContext();
		context.put("dataTable",PassengerBuilder.tableGen(passengers));
		context.put("tableWidth",800);

		// then get the string out and return it
	    StringWriter writer = new StringWriter();
	    TableUtils.getTableTemplate().merge( context, writer );	
	    return writer.toString();		
	}
	

	
}
