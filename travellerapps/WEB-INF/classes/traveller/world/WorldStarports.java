package traveller.world;

import java.util.HashMap;

public class WorldStarports {

	HashMap<Integer, WorldStarport> starports;

	private Integer minDigit = null;
	private Integer maxDigit = null;

	public HashMap<Integer, WorldStarport> getStarports() {
		return starports;
	}

	public void setStarports(HashMap<Integer, WorldStarport> starports) {
		this.starports = starports;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WorldStarports [starports=");
		builder.append(starports);
		builder.append("]");
		return builder.toString();
	}

	public WorldStarport getStarport(char starportClass) {
		for (WorldStarport starport : starports.values()) {
			if (starport.getStarportClass().contains(starportClass+"")) {
				return starport;
			}			
		}
		return null;
	}

	private void getMinMax() {
		maxDigit = -1000;
		minDigit = 1000;
		for (Integer digit : this.starports.keySet()) {
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

	public WorldStarport getDigit(int digit) {
		// clip the return value to within the bounds
		if (digit < getMin()) return starports.get(getMin());
		if (digit > getMax()) return starports.get(getMax());
		return starports.get(digit);
	}



}
