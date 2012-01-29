package traveller.trade.generators;

import traveller.trade.DefinedGood;

public class DefinedTradeItem {

	private DefinedGood definedGood;
	private int tons;
	
	public DefinedGood getDefinedGood() {
		return definedGood;
	}
	public void setDefinedGood(DefinedGood definedGood) {
		this.definedGood = definedGood;
	}
	public int getTons() {
		return tons;
	}
	public void setTons(int tons) {
		this.tons = tons;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DefinedTradeItem [definedGood=");
		builder.append(definedGood);
		builder.append(", tons=");
		builder.append(tons);
		builder.append("]");
		return builder.toString();
	}
	
}
