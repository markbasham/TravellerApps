package traveller.trade.generators;

import java.util.Vector;

import traveller.Dice;
import traveller.TravellerFactory;
import traveller.subsector.Location;
import traveller.trade.TradeCode;
import traveller.trade.TradeGood;
import traveller.world.World;

public class Trade {

	private Vector<TradeItem> items = new Vector<TradeItem>();
	private World current;

	private TradeItem getTradeCodeItem(World world, TradeGood good) {
		for (TradeCode code : world.getTradeCodeList()) {
			if(good.getAvailable().contains(code)) {
				// the good is a standard good for one of these codes
				return new TradeItem(good.getRoll(),world);
			}
		}
		return null;
	}
	
	public Trade(World world) {
		
		current = world;
				
		for (TradeGood good : TravellerFactory.getTradeGoods().getTradeGoods().values()) {
			if(good.getAvailable().isEmpty()) {
				// the good is a basic good, so add it
				items.add(new TradeItem(good.getRoll(),world));
			}
			
			// add the trade good specific goods
			TradeItem tradeCodeSpecific = getTradeCodeItem(world, good);
			if(tradeCodeSpecific != null) items.add(tradeCodeSpecific);		
			
		}
		
		// These items should be included in the main items, rather than seperate
		// Finally add d6 random ones
		for(int i = 0; i < Dice.roll1D6(0); i++) {
			items.add(new TradeItem(world));			
		}
		
	}	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Trade [items=");
		builder.append(items);
		builder.append("]");
		return builder.toString();
	}

	public World getCurrent() {
		return current;
	}	
	
		
	public Vector<TradeItem> getItems() {
		return items;
	}

	public static void main(String[] args) {

		for(int i = 0; i < 10; i++) {
			System.out.println(new Trade(new World("Test", new Location(0,0))));
		}
		
	}

}
