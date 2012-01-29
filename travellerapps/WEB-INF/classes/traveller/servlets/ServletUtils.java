package traveller.servlets;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import traveller.servlets.store.SubsectorBean;
import traveller.subsector.Location;
import traveller.subsector.Subsector;
import traveller.trade.generators.Freight;
import traveller.trade.generators.Passengers;
import traveller.trade.generators.Trade;
import traveller.world.World;

public class ServletUtils {

	// Enviroment statics
	public static final String envMod = "envMod";

	// World statics
	public static final String worldName     = "worldName";
	public static final String worldCode     = "worldCode";
	public static final String worldTZ       = "worldTZ";
	public static final String worldLocation = "worldLocation";

	// Destination statics
	public static final String destinationName     = "destinationName";
	public static final String destinationCode     = "destinationCode";
	public static final String destinationTZ       = "destinationTZ";
	public static final String destinationLocation = "destinationLocation";

	// Subsector statics
	public static final String subsectorName = "subsectorName";
	public static final String subsectorList = "subsectorList";

	// Subsector statics
	public static final String jump1List = "jump1List";
	public static final String jump2List = "jump2List";
	public static final String jump3List = "jump3List";
	public static final String jump4List = "jump4List";
	public static final String jump5List = "jump5List";
	public static final String jump6List = "jump6List";

	// Jump distance
	public static final String jumpDistance = "jumpDistance";

	// Trader information
	public static final String traderNumber = "traderNumber";
	
	
	public static World extractDestinationInformation(HttpServletRequest request, HttpServlet servlet) {
		return extractWorldInformation(request, servlet, destinationLocation, destinationName, destinationCode, destinationTZ);
	}

	public static World extractWorldInformation(HttpServletRequest request, HttpServlet servlet) {
		return extractWorldInformation(request, servlet, worldLocation, worldName, worldCode, worldTZ);
	}

	public static World extractWorldInformation(HttpServletRequest request, HttpServlet servlet, String inLocation, String inName, String inCode, String inTZ) {

		World world = null;

		// get the bean out for the session
		SubsectorBean bean = (SubsectorBean) servlet.getServletContext()
		.getAttribute(request.getSession().getId());

		Subsector subsector = null;

		if (bean != null) {
			subsector = bean.getSubsector();
		}

		String loc = request.getParameter(inLocation);

		if (loc != null && subsector != null) {

			world = subsector.getWorld(new Location(loc));
			return world;

		}

		// Otherwise do as normal

		String name = "Unnamed";

		// Deal with the name parameter
		if(request.getParameter(inName) != null) {
			name = request.getParameter(inName);
		}

		// Now deal with the code if given, if not generate a random one
		String code = request.getParameter(inCode);
		if (code == null) code = "";

		if(code.length() == 9) {
			world = new World(name, request.getParameter(inCode), new Location(0, 0));
		} else {
			world = new World(name, new Location(0, 0));
		}

		String TZ = request.getParameter(inTZ);
		if (TZ == null) TZ = "";
		if(TZ.length() == 1) {
			world.addTravelZoneCode(request.getParameter(inTZ));		
		}

		return world;		
	}




	public static int extractEnviromentalModifier(ServletRequest request) {
		if(request.getParameter(envMod) != null) {
			return Integer.parseInt(request.getParameter(envMod));
		}
		return 0;
	}


	public static int extractJumpDistance(ServletRequest request, HttpServlet servlet) {
		if(request.getParameter(jumpDistance) != null) {
			return Integer.parseInt(request.getParameter(jumpDistance));
		}
		return 0;
	}


	public static Subsector extractSubsector(ServletRequest request) {

		String name = "Subsector";
		if(request.getParameter(subsectorName) != null) {
			name = request.getParameter(subsectorName);
		}

		String subList = request.getParameter(subsectorList);
		if (subList == null) subList = "";

		if(subList.trim().length() > 1) {
			return new Subsector(name, subList);
		}

		return new Subsector(name);

	}


	public static Subsector extractSubsector(HttpServletRequest request, HttpServlet servlet) {

		// get the bean out for the session
		SubsectorBean bean = (SubsectorBean) servlet.getServletContext()
		.getAttribute(request.getSession().getId());

		// if no bean exists, create one.
		if(bean == null) {
			bean = new SubsectorBean();
		}		

		// Generate the subsector
		Subsector subsector = null;

		// check to see if there is a subsector name in the request
		// if there is, then put that information into the bean
		String name = request.getParameter(subsectorName);
		if(name == null) name = "";
		if(name.trim().length() > 0) {

			String subList = request.getParameter(subsectorList);
			if (subList == null) subList = "";

			if(subList.trim().length() > 1) {
				subsector = new Subsector(request.getParameter(subsectorName), subList);
			} else {
				subsector = new Subsector(request.getParameter(subsectorName));
			}

		} else {
			// there is no subsector in the request, try looking in the bean for stored information
			if(bean.getSubsector() != null) subsector = bean.getSubsector();
		}

		// Finaly put the subsector back into the bean, and return the subsector
		bean.setSubsector(subsector);
		servlet.getServletContext().setAttribute(request.getSession().getId(), bean);

		return subsector;
	}

	private static String cleanString(String input) {
		// first see if the data is encoded
		if (input.substring(0, 3).contains("ENC")) {
			// need to deencode this.
			return input.replace("|", "\n").replace("_", " ").replace("ENC", "");
		}
		return input;
	}

	public static Subsector extractJumpList(int jumpNumber, HttpServletRequest request, HttpServlet servlet) {
		// get the bean out for the session
		SubsectorBean bean = (SubsectorBean) servlet.getServletContext()
		.getAttribute(request.getSession().getId());

		Subsector subsector = null;

		if (bean != null) {
			subsector = bean.getSubsector();
		}

		String loc = request.getParameter(worldLocation);

		if (loc != null && subsector != null) {

			subsector =  subsector.getJumpWorlds(new Location(loc), jumpNumber);
		}

		return subsector;
	}



	public static Subsector extractJump1List(ServletRequest request) {
		String jumpList = request.getParameter(jump1List);
		if (jumpList == null) {
			return null;
		} else {			
			return new Subsector("Jump1List", cleanString(jumpList));
		}
	}

	public static Subsector extractJump2List(ServletRequest request) {
		String jumpList = request.getParameter(jump2List);
		if (jumpList == null) {
			return null;
		} else {
			return new Subsector("Jump2List", cleanString(jumpList));
		}
	}

	public static Subsector extractJump3List(ServletRequest request) {
		String jumpList = request.getParameter(jump3List);
		if (jumpList == null) {
			return null;
		} else {
			return new Subsector("Jump3List", cleanString(jumpList));
		}
	}

	public static Subsector extractJump4List(ServletRequest request) {
		String jumpList = request.getParameter(jump4List);
		if (jumpList == null) {
			return null;
		} else {
			return new Subsector("Jump4List", cleanString(jumpList));
		}
	}

	public static Subsector extractJump5List(ServletRequest request) {
		String jumpList = request.getParameter(jump5List);
		if (jumpList == null) {
			return null;
		} else {
			return new Subsector("Jump5List", cleanString(jumpList));
		}
	}

	public static Subsector extractJump6List(ServletRequest request) {
		String jumpList = request.getParameter(jump6List);
		if (jumpList == null) {
			return null;
		} else {
			return new Subsector("Jump6List", cleanString(jumpList));
		}
	}

	public static Trade extractTradeInformation(HttpServletRequest request,
			HttpServlet servlet) {

		// get the bean out for the session
		SubsectorBean bean = (SubsectorBean) servlet.getServletContext()
		.getAttribute(request.getSession().getId());

		World world = extractWorldInformation(request, servlet);

		int traderNum = 0;
		try {
			traderNum = Integer.parseInt(request.getParameter(traderNumber));
		} catch (Exception e) {
			//TODO log this somewhere.
		}
		
		if (bean != null) {
					
			return bean.getTrader(world, traderNum);
		}

		// Otherwise do as normal

		String name = "Unnamed";

		// Deal with the name parameter
		if(request.getParameter(worldName) != null) {
			name = request.getParameter(worldName);
		}

		// Now deal with the code if given, if not generate a random one
		String code = request.getParameter(worldCode);
		if (code == null) code = "";

		if(code.length() == 9) {
			world = new World(name, request.getParameter(worldCode), new Location(0, 0));
		} else {
			world = new World(name, new Location(0, 0));
		}

		String TZ = request.getParameter(worldTZ);
		if (TZ == null) TZ = "";
		if(TZ.length() == 1) {
			world.addTravelZoneCode(request.getParameter(worldTZ));		
		}

		return new Trade(world);
	}

	public static Passengers extractPassengerInformation(
			HttpServletRequest request, HttpServlet servlet) {

		try {

			// get the bean out for the session
			SubsectorBean bean = (SubsectorBean) servlet.getServletContext()
				.getAttribute(request.getSession().getId());

			World current = extractWorldInformation(request, servlet);
			World destination = extractDestinationInformation(request, servlet);
			int enviromentalModifier = extractEnviromentalModifier(request);
			
			return bean.getPassengers(current, destination, enviromentalModifier);

		} catch (Exception e) {
			return new Passengers(new World("Current", new Location(0, 0)), new World("destination", new Location(0, 0)), 0);
		}
	}

	public static Freight extractFreightInformation(HttpServletRequest request,
			HttpServlet servlet) {
		
		try {

			// get the bean out for the session
			SubsectorBean bean = (SubsectorBean) servlet.getServletContext()
				.getAttribute(request.getSession().getId());

			World current = extractWorldInformation(request, servlet);
			World destination = extractDestinationInformation(request, servlet);
			
			return bean.getFreight(current, destination);

		} catch (Exception e) {
			return new Freight(new World("Current", new Location(0, 0)), new World("destination", new Location(0, 0)));
		}
	}
	
	public static void resetSubsectorBeanData(HttpServletRequest request, HttpServlet servlet) {
		
		// get the bean out for the session
		SubsectorBean bean = (SubsectorBean) servlet.getServletContext()
			.getAttribute(request.getSession().getId());
		
		bean.clearTransientElements();
		
	}

}
