package traveller.trade.generators;

import traveller.world.World;

public class FreightItem {

	private World destination;
	private int tons;
	private TradeItem good;
	
	public World getDestination() {
		return destination;
	}
	public void setDestination(World destination) {
		this.destination = destination;
	}
	public int getTons() {
		return tons;
	}
	public void setTons(int tons) {
		this.tons = tons;
	}
	public TradeItem getGood() {
		return good;
	}
	public void setGood(TradeItem tradeItem) {
		this.good = tradeItem;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FreightItem [destination=");
		builder.append(destination);
		builder.append(", tons=");
		builder.append(tons);
		builder.append(", good=");
		builder.append(good);
		builder.append("]");
		return builder.toString();
	}
	
	
		
}
