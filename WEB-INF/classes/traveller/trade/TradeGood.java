package traveller.trade;

import java.util.HashMap;
import java.util.Vector;

import traveller.Utils;
import traveller.world.World;

public class TradeGood {

	private int 							roll;
	private String 							type;
	private Vector<TradeCode>				available;
	private String 							maxTons;
	private int 							minPrice;
	private int								maxPrice;
	private HashMap<TradeCode, Integer> 	purchaseDM;
	private HashMap<TradeCode, Integer> 	saleDM;
	private int 							maxRiskAssesmentDM;
	private int								dangerousGoodsDM;
	private HashMap<String,DefinedGood>		definedTradeGoods;

	public int getRoll() {
		return roll;
	}
	public void setRoll(int roll) {
		this.roll = roll;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Vector<TradeCode> getAvailable() {
		return available;
	}
	public void setAvailable(Vector<TradeCode> available) {
		this.available = available;
	}
	public String getMaxTons() {
		return maxTons;
	}
	public void setMaxTons(String maxTons) {
		this.maxTons = maxTons;
	}
	public int getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}
	public int getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}
	public HashMap<TradeCode, Integer> getPurchaseDM() {
		return purchaseDM;
	}
	public void setPurchaseDM(HashMap<TradeCode, Integer> purchaseDM) {
		this.purchaseDM = purchaseDM;
	}
	public HashMap<TradeCode, Integer> getSaleDM() {
		return saleDM;
	}
	public void setSaleDM(HashMap<TradeCode, Integer> saleDM) {
		this.saleDM = saleDM;
	}
	public int getMaxRiskAssesmentDM() {
		return maxRiskAssesmentDM;
	}
	public void setMaxRiskAssesmentDM(int maxRiskAssesmentDM) {
		this.maxRiskAssesmentDM = maxRiskAssesmentDM;
	}
	public int getDangerousGoodsDM() {
		return dangerousGoodsDM;
	}
	public void setDangerousGoodsDM(int dangerousGoodsDM) {
		this.dangerousGoodsDM = dangerousGoodsDM;
	}
	public HashMap<String, DefinedGood> getDefinedTradeGoods() {
		return definedTradeGoods;
	}
	public void setDefinedTradeGoods(HashMap<String, DefinedGood> definedTradeGoods) {
		this.definedTradeGoods = definedTradeGoods;
	}

	public DefinedGood getDefinedTradeGood(int roll) {
		for (String key : definedTradeGoods.keySet()) {
			if(Utils.isIn(key, roll)) return definedTradeGoods.get(key);			
		}
		return null;
	}

	public Integer getSaleDMValue(TradeCode code) {
		if(saleDM.get(code) != null) return saleDM.get(code);
		return 0;
	}

	public Integer getPurchaseDMValue(TradeCode code) {
		if(purchaseDM.get(code) != null) return purchaseDM.get(code);
		return 0;
	}

	public Integer getPurchaseDMValue(World world) {
		int tmp = -1000;
		for (TradeCode code : world.getTradeCodeList()) {
			if(this.getPurchaseDMValue(code)>tmp) tmp = this.getPurchaseDMValue(code);
		}
		// Finally make a check to see that were not getting -1000
		if(tmp < -500) tmp = 0;
		return tmp;
	}
	
	public Integer getSaleDMValue(World world) {
		int tmp = -1000;
		for (TradeCode code : world.getTradeCodeList()) {
			if(this.getSaleDMValue(code)>tmp) tmp = this.getSaleDMValue(code);
		}
		// Finally make a check to see that were not getting -1000
		if(tmp < -500) tmp = 0;
		return tmp;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TradeGood [roll=");
		builder.append(roll);
		builder.append(", type=");
		builder.append(type);
		builder.append(", available=");
		builder.append(available);
		builder.append(", maxTons=");
		builder.append(maxTons);
		builder.append(", minPrice=");
		builder.append(minPrice);
		builder.append(", maxPrice=");
		builder.append(maxPrice);
		builder.append(", purchaseDM=");
		builder.append(purchaseDM);
		builder.append(", saleDM=");
		builder.append(saleDM);
		builder.append(", maxRiskAssesmentDM=");
		builder.append(maxRiskAssesmentDM);
		builder.append(", dangerousGoodsDM=");
		builder.append(dangerousGoodsDM);
		builder.append(", definedTradeGoods=");
		builder.append(definedTradeGoods);
		builder.append("]");
		return builder.toString();
	}	

}
