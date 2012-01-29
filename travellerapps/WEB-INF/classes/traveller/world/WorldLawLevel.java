package traveller.world;

import java.util.HashMap;

public class WorldLawLevel {

	private int digit;
	private HashMap<Contraband, String> illegalPossesions;
	public int getDigit() {
		return digit;
	}
	public void setDigit(int digit) {
		this.digit = digit;
	}
	public HashMap<Contraband, String> getIllegalPossesions() {
		return illegalPossesions;
	}
	public void setIllegalPossesions(HashMap<Contraband, String> illegalPossesions) {
		this.illegalPossesions = illegalPossesions;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WorldLawLevel [digit=");
		builder.append(digit);
		builder.append(", illegalPossesions=");
		builder.append(illegalPossesions);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
