package traveller.world;

import java.util.HashMap;

public class WorldLawLevels {

	HashMap<Integer, WorldLawLevel> lawLevels;

	private Integer minDigit = null;
	private Integer maxDigit = null;

	public HashMap<Integer, WorldLawLevel> getLawLevels() {
		return lawLevels;
	}

	public void setLawLevels(HashMap<Integer, WorldLawLevel> lawLevels) {
		this.lawLevels = lawLevels;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WorldLawLevels [lawLevels=");
		builder.append(lawLevels);
		builder.append("]");
		return builder.toString();
	}

	private void getMinMax() {
		maxDigit = -1000;
		minDigit = 1000;
		for (Integer digit : this.lawLevels.keySet()) {
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


	public WorldLawLevel getDigit(int digit) {
		// clip the return value to within the bounds
		if (digit < getMin()) return lawLevels.get(getMin());
		if (digit > getMax()) return lawLevels.get(getMax());
		return lawLevels.get(digit);
	}

}
