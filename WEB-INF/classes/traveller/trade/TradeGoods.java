package traveller.trade;

import java.util.HashMap;

public class TradeGoods {

	private HashMap<Integer, TradeGood> tradeGoods;

	public HashMap<Integer, TradeGood> getTradeGoods() {
		return tradeGoods;
	}

	public void setTradeGoods(HashMap<Integer, TradeGood> tradeGoods) {
		this.tradeGoods = tradeGoods;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TradeGoods [tradeGoods=");
		builder.append(tradeGoods);
		builder.append("]");
		return builder.toString();
	}

	
}
