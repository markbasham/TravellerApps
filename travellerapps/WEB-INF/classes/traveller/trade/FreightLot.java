package traveller.trade;

public class FreightLot {

	private int value;
	private String incidental;
	private String minor;
	private String major;
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getIncidental() {
		return incidental;
	}
	public void setIncidental(String incidental) {
		this.incidental = incidental;
	}
	public String getMinor() {
		return minor;
	}
	public void setMinor(String minor) {
		this.minor = minor;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AvailableFreightLots [value=");
		builder.append(value);
		builder.append(", incidental=");
		builder.append(incidental);
		builder.append(", minor=");
		builder.append(minor);
		builder.append(", major=");
		builder.append(major);
		builder.append("]");
		return builder.toString();
	}

}
