package traveller.web;

import java.io.StringWriter;

import org.apache.velocity.VelocityContext;
import org.mla.html.table.Table;

import traveller.trade.TradeCode;
import traveller.world.Contraband;
import traveller.world.World;

public class WorldBuilder {

	private static Table tableGen(World world) {

		Table table = new Table(2, 3);
		table.setGrow();

		int row = 0;

		// World name part
		table.setCell(TableUtils.cellGen(world.toString(), TableUtils.HEADING, 1, 3), row++, 0);

		// Starport Information
		table.setCell(TableUtils.cellGen("StarPort",TableUtils.DESCRIPTOR), row, 0);
		table.setCell(TableUtils.cellGen("Class",TableUtils.DESCRIPTOR), row, 1);
		table.setCell(TableUtils.cellGen(world.getStarport().getStarportClass(),TableUtils.VALUE), row++, 2);
		table.setCell(TableUtils.cellGen("Quality",TableUtils.DESCRIPTOR), row, 1);
		table.setCell(TableUtils.cellGen(world.getStarport().getQuality(),TableUtils.VALUE), row++, 2);
		table.setCell(TableUtils.cellGen("Berthing Cost",TableUtils.DESCRIPTOR), row, 1);
		table.setCell(TableUtils.cellGen(world.getStarport().getBerthingCost()+" Cr.",TableUtils.VALUE), row++, 2);
		table.setCell(TableUtils.cellGen("Fuel",TableUtils.DESCRIPTOR), row, 1);
		table.setCell(TableUtils.cellGen(world.getStarport().getFuel(),TableUtils.VALUE), row++, 2);
		table.setCell(TableUtils.cellGen("Facilities",TableUtils.DESCRIPTOR), row, 1);
		table.setCell(TableUtils.cellGen(world.getStarport().getFacilities(),TableUtils.VALUE), row++, 2);

		// Size
		table.setCell(TableUtils.cellGen("Size",TableUtils.DESCRIPTOR), row, 0);
		table.setCell(TableUtils.cellGen("Diameter",TableUtils.DESCRIPTOR), row, 1);
		table.setCell(TableUtils.cellGen(world.getSize().getSize()+" km",TableUtils.VALUE), row++, 2);
		table.setCell(TableUtils.cellGen("Gravity",TableUtils.DESCRIPTOR), row, 1);
		table.setCell(TableUtils.cellGen(world.getSize().getGravity()+" gs",TableUtils.VALUE), row++, 2);

		// Atmosphere Information
		table.setCell(TableUtils.cellGen("Atmosphere",TableUtils.DESCRIPTOR), row, 0);
		table.setCell(TableUtils.cellGen("Atmosphere",TableUtils.DESCRIPTOR), row, 1);
		table.setCell(TableUtils.cellGen(world.getAtmosphere().getAtmosphere(),TableUtils.VALUE), row++, 2);
		table.setCell(TableUtils.cellGen("Pressure",TableUtils.DESCRIPTOR), row, 1);
		table.setCell(TableUtils.cellGen(world.getAtmosphere().getPressure(),TableUtils.VALUE), row++, 2);
		table.setCell(TableUtils.cellGen("Survival Gear Required",TableUtils.DESCRIPTOR), row, 1);
		table.setCell(TableUtils.cellGen(world.getAtmosphere().getSurvivalGearRequired(),TableUtils.VALUE), row++, 2);

		// Hydrographic Information
		table.setCell(TableUtils.cellGen("Hydrographics",TableUtils.DESCRIPTOR), row, 0);
		table.setCell(TableUtils.cellGen("Percentage",TableUtils.DESCRIPTOR), row, 1);
		table.setCell(TableUtils.cellGen(world.getHydrographic().getPercentage(),TableUtils.VALUE), row++, 2);
		table.setCell(TableUtils.cellGen("Description",TableUtils.DESCRIPTOR), row, 1);
		table.setCell(TableUtils.cellGen(world.getHydrographic().getDescription(),TableUtils.VALUE), row++, 2);

		// Population Information
		table.setCell(TableUtils.cellGen("Population",TableUtils.DESCRIPTOR), row, 0);
		table.setCell(TableUtils.cellGen("Population",TableUtils.DESCRIPTOR), row, 1);
		table.setCell(TableUtils.cellGen(world.getPopulation().getPopulation(),TableUtils.VALUE), row++, 2);
		table.setCell(TableUtils.cellGen("Description",TableUtils.DESCRIPTOR), row, 1);
		table.setCell(TableUtils.cellGen(world.getPopulation().getDescription(),TableUtils.VALUE), row++, 2);

		// Government Information
		table.setCell(TableUtils.cellGen("Government",TableUtils.DESCRIPTOR), row, 0);
		table.setCell(TableUtils.cellGen("Type",TableUtils.DESCRIPTOR), row, 1);
		table.setCell(TableUtils.cellGen(world.getGovernment().getGovernment(),TableUtils.VALUE), row++, 2);
		table.setCell(TableUtils.cellGen("Description",TableUtils.DESCRIPTOR), row, 1);
		table.setCell(TableUtils.cellGen(world.getGovernment().getDescription(),TableUtils.VALUE), row++, 2);

		// Law Information
		table.setCell(TableUtils.cellGen("Illegal Goods",TableUtils.DESCRIPTOR), row, 0);
		if(world.getGovernment().getContraband().isEmpty()) {
			table.setCell(TableUtils.cellGen("None",TableUtils.DESCRIPTOR), row, 1);
			table.setCell(TableUtils.cellGen("None",TableUtils.VALUE), row++, 2);
		} else {
			if(world.getGovernment().getContraband().get(0).getType().contains("Varies")) {
				table.setCell(TableUtils.cellGen("Varies",TableUtils.DESCRIPTOR), row, 1);
				table.setCell(TableUtils.cellGen("Varies",TableUtils.VALUE), row++, 2);
			} else {
				for (Contraband contraband : world.getGovernment().getContraband()) {
					table.setCell(TableUtils.cellGen(contraband.toString(),TableUtils.DESCRIPTOR), row, 1);
					table.setCell(TableUtils.cellGen(world.getLaw().getIllegalPossesions().get(contraband),TableUtils.VALUE), row++, 2);
				}
			}
		}

		// Tech Information
		table.setCell(TableUtils.cellGen("Tech Level",TableUtils.DESCRIPTOR), row, 0);
		table.setCell(TableUtils.cellGen("Description",TableUtils.DESCRIPTOR), row, 1);
		table.setCell(TableUtils.cellGen(world.getTech().getDescription(),TableUtils.VALUE), row++, 2);	

		// Trade Codes
		table.setCell(TableUtils.cellGen("Trade Codes",TableUtils.DESCRIPTOR), row, 0);
		for (TradeCode code : world.getTradeCodeList()) {
			table.setCell(TableUtils.cellGen(code.getName(),TableUtils.DESCRIPTOR), row, 1);
			table.setCell(TableUtils.cellGen(code.getDescription(),TableUtils.VALUE), row++, 2);
		}

		return table;
	}

	public static String build(World world) throws Exception {
		return build(world,800);
	}

	public static String build(World world, int tableWidth) throws Exception {

		// add the table to the context
		VelocityContext context = new VelocityContext();
		context.put("dataTable",WorldBuilder.tableGen(world));
		context.put("tableWidth",tableWidth);

		// then get the string out and return it
		StringWriter writer = new StringWriter();
		TableUtils.getTableTemplate().merge( context, writer );	
		return writer.toString();		
	}

}
