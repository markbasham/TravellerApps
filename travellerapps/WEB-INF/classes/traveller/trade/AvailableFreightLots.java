package traveller.trade;

import java.util.HashMap;

public class AvailableFreightLots {

	private HashMap<Integer, FreightLot> freightLots;

	private Integer minDigit = null;
	private Integer maxDigit = null;
	
	public HashMap<Integer, FreightLot> getFreightLots() {
		return freightLots;
	}

	public void setFreightLots(HashMap<Integer, FreightLot> freightLots) {
		this.freightLots = freightLots;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AvailableFreightLots [freightLots=");
		builder.append(freightLots.toString().replace("]", "]\n"));
		builder.append("]");
		return builder.toString();
	}
	
	private void getMinMax() {
		maxDigit = -1000;
		minDigit = 1000;
		for (Integer digit : this.freightLots.keySet()) {
			if (digit > maxDigit) maxDigit = digit;
			if (digit < minDigit) minDigit = digit;
		}
	}

	private int getMax() {
		if (maxDigit == null) {
			getMinMax();
		}
		return maxDigit;		
	}

	private int getMin() {
		if (minDigit == null) {
			getMinMax();
		}
		return minDigit;		
	}

	public FreightLot getValue(int digit) {
		// clip the return value to within the bounds
		if (digit < getMin()) return freightLots.get(getMin());
		if (digit > getMax()) return freightLots.get(getMax());
		return freightLots.get(digit);
	}

}
