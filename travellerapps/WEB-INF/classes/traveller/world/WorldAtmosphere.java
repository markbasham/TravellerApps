package traveller.world;


public class WorldAtmosphere {

	private int digit;
	private String atmosphere;
	private String pressure;
	private String survivalGearRequired;
	public int getDigit() {
		return digit;
	}
	public void setDigit(int digit) {
		this.digit = digit;
	}
	public String getAtmosphere() {
		return atmosphere;
	}
	public void setAtmosphere(String atmosphere) {
		this.atmosphere = atmosphere;
	}
	public String getPressure() {
		return pressure;
	}
	public void setPressure(String pressure) {
		this.pressure = pressure;
	}
	public String getSurvivalGearRequired() {
		return survivalGearRequired;
	}
	public void setSurvivalGearRequired(String survivalGearRequired) {
		this.survivalGearRequired = survivalGearRequired;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WorldAtmosphere [digit=");
		builder.append(digit);
		builder.append(", atmosphere=");
		builder.append(atmosphere);
		builder.append(", pressure=");
		builder.append(pressure);
		builder.append(", survivalGearRequired=");
		builder.append(survivalGearRequired);
		builder.append("]");
		return builder.toString();
	}


}
