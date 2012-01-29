package traveller.trade;

import java.util.HashMap;

public class AvailablePassengers {

	private HashMap<Integer, AvailablePassenger> passengers;

	private Integer minDigit = null;
	private Integer maxDigit = null;

	public HashMap<Integer, AvailablePassenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(HashMap<Integer, AvailablePassenger> passengers) {
		this.passengers = passengers;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AvailablePassengers [passengers=");
		builder.append(passengers);
		builder.append("]");
		return builder.toString();
	}

	private void getMinMax() {
		maxDigit = -1000;
		minDigit = 1000;
		for (Integer digit : this.passengers.keySet()) {
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

	public AvailablePassenger getValue(int digit) {
		// clip the return value to within the bounds
		if (digit < getMin()) return passengers.get(getMin());
		if (digit > getMax()) return passengers.get(getMax());
		return passengers.get(digit);
	}

}
