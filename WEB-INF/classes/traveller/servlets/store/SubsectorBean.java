package traveller.servlets.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import traveller.subsector.Location;
import traveller.subsector.Subsector;
import traveller.trade.generators.Freight;
import traveller.trade.generators.Passengers;
import traveller.trade.generators.Trade;
import traveller.world.World;

public class SubsectorBean {

	private Subsector subsector;
	private HashMap<Location, Map<Integer, Trade>> TraderMap = new HashMap<Location, Map<Integer, Trade>>();
	private HashMap<Location, Map<Location, Passengers>> passengersMap = new HashMap<Location, Map<Location, Passengers>>();
	private HashMap<Location, Map<Location, Freight>> freightMap = new HashMap<Location, Map<Location, Freight>>();

	public void clearTransientElements() {
		TraderMap = new HashMap<Location, Map<Integer, Trade>>();
		passengersMap = new HashMap<Location, Map<Location, Passengers>>();
		freightMap = new HashMap<Location, Map<Location, Freight>>();
	}

	public Passengers getPassengers(World current, World destination, int enviromentalModifier) {
		Location cloc = current.getLocation();
		Location dloc = destination.getLocation();
		if (passengersMap.containsKey(cloc)) {
			Map<Location, Passengers> cmap = passengersMap.get(cloc);
			if(cmap.containsKey(dloc)) {
				return cmap.get(dloc); 
			} else {
				Passengers passengers = new Passengers(current, destination, enviromentalModifier);
				cmap.put(dloc, passengers);
				return passengers;
			}
		} else {
			HashMap<Location, Passengers> cmap = new HashMap<Location, Passengers>();
			passengersMap.put(cloc, cmap);
			Passengers passengers = new Passengers(current, destination, enviromentalModifier);
			cmap.put(dloc, passengers);
			return passengers;
		}
	}

	public Freight getFreight(World current, World destination) {
		Location cloc = current.getLocation();
		Location dloc = destination.getLocation();
		if (freightMap.containsKey(cloc)) {
			Map<Location, Freight> cmap = freightMap.get(cloc);
			if(cmap.containsKey(dloc)) {
				return cmap.get(dloc); 
			} else {
				Freight freight = new Freight(current, destination);
				cmap.put(dloc, freight);
				return freight;
			}
		} else {
			HashMap<Location, Freight> cmap = new HashMap<Location, Freight>();
			freightMap.put(cloc, cmap);
			Freight freight = new Freight(current, destination);
			cmap.put(dloc, freight);
			return freight;
		}
	}


	public Trade getTrader(World world, int traderNumber) {
		Location location = world.getLocation();
		if (TraderMap.containsKey(location)) {
			Map<Integer, Trade> cmap = TraderMap.get(location);
			if(cmap.containsKey(traderNumber)) {
				return cmap.get(traderNumber); 
			} else {
				Trade trade = new Trade(world);
				cmap.put(traderNumber, trade);
				return trade;
			}
		} else {
			HashMap<Integer, Trade> cmap = new HashMap<Integer, Trade>();
			TraderMap.put(location, cmap);
			Trade trade = new Trade(world);
			cmap.put(traderNumber, trade);
			return trade;
		}
	}	

	public Subsector getSubsector() {
		return subsector;
	}

	public void setSubsector(Subsector subsector) {
		this.subsector = subsector;
	}

}
