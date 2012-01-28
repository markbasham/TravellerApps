package traveller.trade;

import java.util.HashMap;
import java.util.Vector;

public class TradeCodes {

	private HashMap<String, TradeCode> tradeCodes;

	public HashMap<String, TradeCode> getTradeCodes() {
		return tradeCodes;
	}
	
	public TradeCode getTradeCode(String code) {
		return tradeCodes.get(code);
	}

	public void setTradeCodes(HashMap<String, TradeCode> tradeCodes) {
		this.tradeCodes = tradeCodes;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TradeCodes [tradeCodes=");
		builder.append(tradeCodes.toString().replace("]", "]\n"));
		builder.append("]");
		return builder.toString();
	}
	
	public Vector<TradeCode> getCodes(int size, int atmos, int hydro, int pop, int gov, int law, int tech) {
		Vector<TradeCode> result = new Vector<TradeCode>();
		
		for (String codeKey : tradeCodes.keySet()) {
			if (tradeCodes.get(codeKey).compareCode(size, atmos, hydro, pop, gov, law, tech)) {
				result.add(tradeCodes.get(codeKey));
			}
		}
		
		return result;
		
	}

}
