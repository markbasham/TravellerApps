package traveller.web;

import java.io.FileWriter;
import java.io.StringWriter;

import traveller.subsector.Location;
import traveller.world.World;

public class HTMLBuilder {

	public static String generateTradeTables(World current, int numberOfTraders, World... destinations) throws Exception {
		StringWriter sw = new StringWriter();
		
		sw.write(CurrentBuilder.build(current, numberOfTraders));
		sw.write("<BR>");
		for (World world : destinations) {
			sw.write(DestinationBuilder.build(current, world, 0));
			sw.write("<BR>");
		}
		return sw.toString();		
	}
	
	public static void main(String[] args) throws Exception{

		FileWriter fw = new FileWriter("test.html");
		fw.write("<html><body>");
		World current = new World("Current",new Location(0,0));
		World destination1 = new World("Destination1",new Location(0,0));
		World destination2 = new World("Destination2",new Location(0,0));
		World destination3 = new World("Destination3",new Location(0,0));
		World destination4 = new World("Destination4",new Location(0,0));		
		
		fw.write(generateTradeTables(current, 3, destination1, destination2, destination3, destination4));
		
		fw.write("</body></html>");
		fw.close();
	}
}
