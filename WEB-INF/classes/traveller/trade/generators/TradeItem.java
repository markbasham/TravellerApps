package traveller.trade.generators;

import java.util.Vector;

import traveller.Dice;
import traveller.TravellerFactory;
import traveller.subsector.Location;
import traveller.trade.DefinedGood;
import traveller.trade.TradeGood;
import traveller.world.World;

public class TradeItem {

	private World world;
	private TradeGood good;
	private int tons;
	private int purchaseDM;
	private int saleDM;

	Vector<DefinedTradeItem> definedItems = new Vector<DefinedTradeItem>();

	public TradeItem(World world) {
		this(Dice.rollD66(), world, null);
	}	

	public TradeItem(int roll, World world) {
		this(roll, world, null);
	}	

	public TradeItem(int roll, World world, Integer tons) {
		this.world = world;
		// get the info from the Factory
		this.good = TravellerFactory.getTradeGoods().getTradeGoods().get(roll);
		if(tons == null) {
			tons = Dice.roll(good.getMaxTons());	
		}
		this.tons = tons;	
		if (world != null) setDMs();
		populateDefinedItems();
	}

	public TradeItem(int roll, int tons) {
		this(roll, null, tons);
	}

	private void setDMs() {
		purchaseDM = good.getPurchaseDMValue(world);
		saleDM = good.getSaleDMValue(world);		
	}

	public void populateDefinedItems() {
		int tonsleft = tons;
		while (tonsleft > 0) {
			DefinedGood definedGood = good.getDefinedTradeGood(Dice.roll2D6(0));
			DefinedTradeItem defItem = null;
			// see if its already in the list
			for (DefinedTradeItem definedItem : definedItems) {
				if (definedItem.getDefinedGood().equals(definedGood)) defItem = definedItem;
			}
			// if its not, then create a new one
			if(defItem == null) {
				defItem = new DefinedTradeItem();
				defItem.setDefinedGood(definedGood);
				defItem.setTons(0);
				definedItems.add(defItem);
			}
			// now fill it with some goods
			int ammount = Dice.roll(definedGood.getTonIncrement());

			if(ammount > tonsleft) ammount = tonsleft;

			// add the tons to the item
			defItem.setTons(defItem.getTons() + ammount);
			tonsleft -= ammount;			
		}

	}

	public TradeGood getGood() {
		return good;
	}
	public void setGood(TradeGood good) {
		this.good = good;
	}
	public int getTons() {
		return tons;
	}
	public void setTons(int tons) {
		this.tons = tons;
	}
	public Vector<DefinedTradeItem> getDefinedItems() {
		return definedItems;
	}
	public void setDefinedItems(Vector<DefinedTradeItem> definedItems) {
		this.definedItems = definedItems;
	}
	public boolean add(DefinedTradeItem e) {
		return definedItems.add(e);
	}
	public int getPurchaseDM() {
		return purchaseDM;
	}
	public void setPurchaseDM(int purchaseDM) {
		this.purchaseDM = purchaseDM;
	}
	public int getSaleDM() {
		return saleDM;
	}
	public void setSaleDM(int saleDM) {
		this.saleDM = saleDM;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TradeItem [good=");
		builder.append(good);
		builder.append(", tons=");
		builder.append(tons);
		builder.append(", definedItems=");
		builder.append(definedItems);
		builder.append("]");
		return builder.toString();
	}

	public static void main(String[] args) {

		for (int i = 0; i < 10; i++) {

			TradeItem t1 = new TradeItem(new World("Test", new Location(0,0)));
			System.out.println(t1);
		}

	}

}
