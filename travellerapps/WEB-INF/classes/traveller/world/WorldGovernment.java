package traveller.world;

import java.util.Vector;

public class WorldGovernment {

	private int digit;
	private String government;
	private String description;
	private Vector<Contraband> contraband;
	
	public int getDigit() {
		return digit;
	}
	public void setDigit(int digit) {
		this.digit = digit;
	}
	public String getGovernment() {
		return government;
	}
	public void setGovernment(String government) {
		this.government = government;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Vector<Contraband> getContraband() {
		return contraband;
	}
	public void setContraband(Vector<Contraband> contraband) {
		this.contraband = contraband;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WorldGovenment [digit=");
		builder.append(digit);
		builder.append(", government=");
		builder.append(government);
		builder.append(", description=");
		builder.append(description);
		builder.append(", contraband=");
		builder.append(contraband);
		builder.append("]");
		return builder.toString();
	}
	
}
