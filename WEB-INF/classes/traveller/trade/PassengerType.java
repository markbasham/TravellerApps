package traveller.trade;

import java.util.HashMap;

public class PassengerType {

	private int digit;
	private String description;
	private int transitHazardDM;
	private HashMap<String, String> traits;
	private HashMap<String, String> skills;
	
	public int getDigit() {
		return digit;
	}
	public void setDigit(int digit) {
		this.digit = digit;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getTransitHazardDM() {
		return transitHazardDM;
	}
	public void setTransitHazardDM(int transitHazardDM) {
		this.transitHazardDM = transitHazardDM;
	}
	public HashMap<String, String> getTraits() {
		return traits;
	}
	public void setTraits(HashMap<String, String> traits) {
		this.traits = traits;
	}
	public HashMap<String, String> getSkills() {
		return skills;
	}
	public void setSkills(HashMap<String, String> skills) {
		this.skills = skills;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PassengerTypes [digit=");
		builder.append(digit);
		builder.append(", description=");
		builder.append(description);
		builder.append(", transitHazardDM=");
		builder.append(transitHazardDM);
		builder.append(", traits=");
		builder.append(traits);
		builder.append(", skills=");
		builder.append(skills);
		builder.append("]");
		return builder.toString();
	}
	
}
