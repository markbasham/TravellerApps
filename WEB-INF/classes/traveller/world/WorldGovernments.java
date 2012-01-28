package traveller.world;

import java.util.HashMap;

public class WorldGovernments {

	private HashMap<Integer, WorldGovernment> governments;

	private Integer minDigit = null;
	private Integer maxDigit = null;

	public HashMap<Integer, WorldGovernment> getGovernments() {
		return governments;
	}

	public void setGovernments(HashMap<Integer, WorldGovernment> governments) {
		this.governments = governments;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WorldGovernments [governments=");
		builder.append(governments);
		builder.append("]");
		return builder.toString();
	}

	private void getMinMax() {
		maxDigit = -1000;
		minDigit = 1000;
		for (Integer digit : this.governments.keySet()) {
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

	public WorldGovernment getDigit(int digit) {
		// clip the return value to within the bounds
		if (digit < getMin()) return governments.get(getMin());
		if (digit > getMax()) return governments.get(getMax());
		return governments.get(digit);
	}

}
