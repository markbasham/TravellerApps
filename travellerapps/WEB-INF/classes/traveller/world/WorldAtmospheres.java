package traveller.world;

import java.util.HashMap;

public class WorldAtmospheres {

	private HashMap<Integer, WorldAtmosphere> atmospheres;

	private Integer minDigit = null;
	private Integer maxDigit = null;

	public HashMap<Integer, WorldAtmosphere> getAtmospheres() {
		return atmospheres;
	}

	public void setAtmospheres(HashMap<Integer, WorldAtmosphere> atmospheres) {
		this.atmospheres = atmospheres;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WorldAtmospheres [atmospheres=");
		builder.append(atmospheres);
		builder.append("]");
		return builder.toString();
	}

	private void getMinMax() {
		maxDigit = -1000;
		minDigit = 1000;
		for (Integer digit : this.atmospheres.keySet()) {
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

	public WorldAtmosphere getDigit(int digit) {
		// clip the return value to within the bounds
		if (digit < getMin()) return atmospheres.get(getMin());
		if (digit > getMax()) return atmospheres.get(getMax());
		return atmospheres.get(digit);
	}

}
