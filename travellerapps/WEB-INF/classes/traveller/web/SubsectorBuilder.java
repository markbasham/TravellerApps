package traveller.web;

import java.io.StringWriter;

import org.apache.velocity.VelocityContext;
import org.mla.html.table.Table;

import traveller.servlets.WorldInfoManager;
import traveller.subsector.Location;
import traveller.subsector.Subsector;
import traveller.world.World;

public class SubsectorBuilder {

	private static Table listTableGen(Subsector subsector) throws Exception {

		Table table = new Table(1, 1);
		table.setGrow();

		int row = 0;

		// Subsector name part
		table.setCell(TableUtils.cellGen(String.format("%s : Subsector List" , subsector.getName()), TableUtils.TITLE, 1, 4), row++, 0);

		// Headers for the columbs
		table.setCell(TableUtils.cellGen("World Name",TableUtils.HEADING), row, 0);
		table.setCell(TableUtils.cellGen("Location",TableUtils.HEADING), row, 1);
		table.setCell(TableUtils.cellGen("UPP Code",TableUtils.HEADING), row, 2);
		table.setCell(TableUtils.cellGen("Trade Codes",TableUtils.HEADING), row++, 3);

		for (Location location : subsector.getSortedLocations()) {
			World world = subsector.getWorld(location);
			String worldLink = world.generateWorldLink(world.getName(),null);
			table.setCell(TableUtils.cellGen(worldLink, TableUtils.VALUE), row, 0);
			table.setCell(TableUtils.cellGen(location.toString(),TableUtils.VALUE), row, 1);
			table.setCell(TableUtils.cellGen(world.getUPP(),TableUtils.VALUE), row, 2);
			table.setCell(TableUtils.cellGen(world.printTradeCodes(),TableUtils.VALUE), row++, 3);
		}

		return table;
	}

	private static Table arrayTableGen(Subsector subsector, String target) {
		Table table = new Table(1, 1);
		table.setGrow();

		// Subsector name part
		table.setCell(TableUtils.cellGen(String.format("%s : Subsector Layout" , subsector.getName()), TableUtils.TITLE, 1, 17), 0, 0);

		for(int col = 0; col < 8; col++) {
			int rowpos = 1;
			if(col%2 != 0) rowpos = 2;
			for(int row = 0; row < 10; row++) {
				StringBuilder builder = new StringBuilder();
				
				builder.append("<br>");
				try {
					Location loc = new Location(col+1,row+1);
					World world = subsector.getWorld(loc);
					builder.append(world.generateWorldLink(world.getName(), target));
				} catch (Exception e) {
					
				}
				builder.append("<br>");
				
				table.setCell(TableUtils.cellGen(builder.toString(),TableUtils.VALUE,2,1), rowpos, col+1);	
				
				rowpos += 2;
			}			
		}
		
		return table;
	}
	
	
	private static Table sectorTableGen(Subsector subsector, String target) {
		Table table = new Table(1, 1);
		table.setGrow();

		// Subsector name part
		//table.setCell(TableUtils.cellGen(String.format("%s : Sector Layout" , subsector.getName()), TableUtils.TITLE, 1, 17), 0, 0);

		for(int col = 0; col < 32; col++) {
			int rowpos = 0;
			if(col%2 != 0) {
				table.setCell(TableUtils.cellGen("" ,TableUtils.TOP ,1 ,1), rowpos, col+1);	
				rowpos = 1;
			} else {
				table.setCell(TableUtils.cellGen("" ,TableUtils.BOTTOM ,1 ,1), 80, col+1);	
			}
			for(int row = 0; row < 40; row++) {
			
				try {
					Location loc = new Location(col+1,row+1);
					World world = subsector.getWorld(loc);
					String type = TableUtils.BASIC;
					if(world.hasGasGiant()) {
						type = TableUtils.GASGIANT;
					} 
					table.setCell(TableUtils.cellGen(
							LinkUtils.generateWorldManagerLink(world,"<small><small><small><small>"+world.getName()+"</small></small></small><br>"+world.getStarport().getStarportClass()+"</small>"),
							type ,2 ,1), rowpos, col+1);
				} catch (Exception e) {
					table.setCell(TableUtils.cellGen(
							"", TableUtils.EMPTY ,2 ,1), rowpos, col+1);
				}	
				
				rowpos += 2;
			}			
		}
		
		return table;
	}
	
	
	public static String buildList(Subsector subsector) throws Exception {
		
		// add the table to the context
		VelocityContext context = new VelocityContext();
		context.put("dataTable",SubsectorBuilder.listTableGen(subsector));
		context.put("tableWidth",600);

		// then get the string out and return it
		StringWriter writer = new StringWriter();
		TableUtils.getTableTemplate().merge( context, writer );	
		return writer.toString();		
	}

	public static String buildArray(Subsector subsector, String target) throws Exception {
		// add the table to the context
		VelocityContext context = new VelocityContext();
		context.put("dataTable",SubsectorBuilder.arrayTableGen(subsector, target));
		context.put("tableWidth",600);

		// then get the string out and return it
		StringWriter writer = new StringWriter();
		TableUtils.getTableTemplate().merge( context, writer );	
		return writer.toString();		
	}
	
	public static String buildSectorArray(Subsector subsector, String target) throws Exception {
		// add the table to the context
		VelocityContext context = new VelocityContext();
		context.put("dataTable",SubsectorBuilder.sectorTableGen(subsector, target));
		context.put("tableWidth",(64*32));

		// then get the string out and return it
		StringWriter writer = new StringWriter();
		TableUtils.getSectorTableTemplate().merge( context, writer );	
		return writer.toString();		
	}

}
