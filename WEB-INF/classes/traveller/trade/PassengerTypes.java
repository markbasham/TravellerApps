package traveller.trade;

import java.util.HashMap;

public class PassengerTypes {

	HashMap<Integer, PassengerType> passengerTypes;
	
	private Integer minDigit = null;
	private Integer maxDigit = null;
	
	public HashMap<Integer, PassengerType> getPassengerTypes() {
		return passengerTypes;
	}

	public void setPassengerTypes(HashMap<Integer, PassengerType> passengerTypes) {
		this.passengerTypes = passengerTypes;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PassengerTypes [passengerTypes=");
		builder.append(passengerTypes);
		builder.append("]");
		return builder.toString();
	}
	
	private void getMinMax() {
		maxDigit = -1000;
		minDigit = 1000;
		for (Integer digit : this.passengerTypes.keySet()) {
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

	public PassengerType getValue(int digit) {
		// clip the return value to within the bounds
		if (digit < getMin()) return passengerTypes.get(getMin());
		if (digit > getMax()) return passengerTypes.get(getMax());
		return passengerTypes.get(digit);
	}
	
	
	
	
}
