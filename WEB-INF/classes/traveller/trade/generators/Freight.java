package traveller.trade.generators;

import java.util.Vector;

import traveller.Dice;
import traveller.TravellerFactory;
import traveller.subsector.Location;
import traveller.trade.TradeCode;
import traveller.world.World;

public class Freight {
	
	private Vector<FreightItem> freightItems = new Vector<FreightItem>();
	
	private World current;
	private World destination;	
	
	public World getCurrent() {
		return current;
	}

	public World getDestination() {
		return destination;
	}
	
	public Vector<FreightItem> getFreightItems() {
		return freightItems;
	}

	public Freight(World current, World destination) {
		
		// populate the structure
		this.current = current;
		this.destination = destination;
		
		// get the modifiers
		int modifiers = destination.getPopulation().getDigit();
		
		for (TradeCode code : current.getTradeCodeList()) {
			modifiers += TravellerFactory.getFreightTraffic().getWorldDMs().get(code).getCurrentDM();
		}
		
		for (TradeCode code : destination.getTradeCodeList()) {
			modifiers += TravellerFactory.getFreightTraffic().getWorldDMs().get(code).getDestinationDM();
		}

		// now the tech level modifier
		int techMod = Math.abs(current.getTech().getDigit() - destination.getTech().getDigit());
		if (techMod > 5) techMod = 5;
		
		modifiers -= techMod;
				
		// now work out how many lots there are, and then populate the Freight
		String incidental = TravellerFactory.getAvailableFreightLots().getValue(modifiers).getIncidental();
		String minor = TravellerFactory.getAvailableFreightLots().getValue(modifiers).getMinor();
		String major = TravellerFactory.getAvailableFreightLots().getValue(modifiers).getMajor();
		
		int noIncidental = Dice.roll(incidental);
		int noMinor = Dice.roll(minor);
		int noMajor = Dice.roll(major);
		
		for (int i = 0; i < noIncidental; i++) {
			FreightItem item = new FreightItem();
			item.setDestination(destination);
			item.setTons(Dice.roll("1d6"));
			item.setGood(new TradeItem(Dice.rollD66(), item.getTons()));
			freightItems.add(item);
		}
		
		for (int i = 0; i < noMinor; i++) {
			FreightItem item = new FreightItem();
			item.setDestination(destination);
			item.setTons(Dice.roll("1d6x5"));
			item.setGood(new TradeItem(Dice.rollD66(), item.getTons()));
			freightItems.add(item);
		}
		
		for (int i = 0; i < noMajor; i++) {
			FreightItem item = new FreightItem();
			item.setDestination(destination);
			item.setTons(Dice.roll("1d6x10"));
			item.setGood(new TradeItem(Dice.rollD66(), item.getTons()));
			freightItems.add(item);
		}
		
	}	
	
	
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Freight [freightItems=");
		builder.append(freightItems);
		builder.append("]");
		return builder.toString();
	}


	public static void main(String[] args) {
		
		for (int i = 0; i< 10; i++) {
		
			Freight f = new Freight(new World("curr", new Location(0,0)), new World("dest", new Location(0,0)));
			System.out.println(f);
		}
		
	}

}
