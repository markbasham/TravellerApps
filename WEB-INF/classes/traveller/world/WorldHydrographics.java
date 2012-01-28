package traveller.world;

import java.util.HashMap;

public class WorldHydrographics {

	HashMap<Integer, WorldHydrographic> hydrographics;

	private Integer minDigit = null;
	private Integer maxDigit = null;
	
	public HashMap<Integer, WorldHydrographic> getHydrographics() {
		return hydrographics;
	}

	public void setHydrographics(HashMap<Integer, WorldHydrographic> hydrographics) {
		this.hydrographics = hydrographics;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WorldHydrographics [hydrographics=");
		builder.append(hydrographics);
		builder.append("]");
		return builder.toString();
	}

	private void getMinMax() {
		maxDigit = -1000;
		minDigit = 1000;
		for (Integer digit : this.hydrographics.keySet()) {
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
	
	public WorldHydrographic getDigit(int digit) {
		// clip the return value to within the bounds
		if (digit < getMin()) return hydrographics.get(getMin());
		if (digit > getMax()) return hydrographics.get(getMax());
		return hydrographics.get(digit);
	}
	
	
}
