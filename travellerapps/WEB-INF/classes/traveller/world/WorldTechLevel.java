package traveller.world;

public class WorldTechLevel {

	private int digit;
	private String shortDescription;
	private String description;
	
	public int getDigit() {
		return digit;
	}
	public void setDigit(int digit) {
		this.digit = digit;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String decription) {
		this.description = decription;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WorldTechLevel [digit=");
		builder.append(digit);
		builder.append(", shortDescription=");
		builder.append(shortDescription);
		builder.append(", decription=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}	
	
}
