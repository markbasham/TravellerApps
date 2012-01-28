package traveller.web;

import traveller.world.World;

public class LinkUtils {

	public static String generateWorldUPPInfoLink(World world, String linkName, String target) {
		if(target == null) {
			return String.format("<a href='World?worldLocation=%s'>%s</a>",
					world.getLocation() , linkName);
		}
		return String.format("<a href='World?worldLocation=%s' target='%s'>%s</a>",
				world.getLocation() , target, linkName);
	}	
	
	public static String generateWorldManagerLink(World world, String linkName) {
		return generateMultiLink(
				String.format("WorldInfoManager?worldLocation=%s", world.getLocation()),
				"worldManager",
				String.format("World?worldLocation=%s", world.getLocation()),
				"world",
				linkName);
	}	

	public static String generateLink(String href, String target, String linkName) {
		return String.format("<a href='%s' target='%s'>%s</a>",
				href, target, linkName);	
	}

	public static String generateMultiLink(String href1, String target1, String href2, 
			String target2, String linkName) {
		return String.format("<a href='%s' target='%s' onclick=\"window.open('%s', '%s');\">%s</a>",
				href1, target1, href2, target2, linkName);	
	}
	
	public static String generateMultiLink(String href1, String target1, String href2, 
			String target2, String href3, String target3, String linkName) {
		return String.format("<a href='%s' target='%s' onclick='window.open('%s', '%s'); window.open('%s', '%s');'>%s</a>",
				href1, target1, href2, target2, href3, target3, linkName);	
	}

	public static String generateJumpTradeLink(World world, String linkName,
			String target, int jumpDistance) {
		return generateLink(String.format("JumpTrade?worldLocation=%s&jumpDistance=%s",world.getLocation(), jumpDistance),
				target, linkName);
	}
	
	public static String generateJumpFreightLink(World current, World destination, String linkName,
			String target) {
		return generateLink(String.format("FreightServlet?worldLocation=%s&destinationLocation=%s",current.getLocation(), destination.getLocation()),
				target, linkName);
	}

	public static String generateTraderLink(World world, int traderNumber, String linkName,
			String target) {
		return generateLink(String.format("TraderServlet?worldLocation=%s&traderNumber=%d",world.getLocation(),traderNumber ),
				target, linkName);
	}

}