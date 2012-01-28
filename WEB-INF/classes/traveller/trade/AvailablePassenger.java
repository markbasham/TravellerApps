package traveller.trade;

public class AvailablePassenger {

	int value;
	String low;
	String medium;
	String high;
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getLow() {
		return low;
	}
	public void setLow(String low) {
		this.low = low;
	}
	public String getMedium() {
		return medium;
	}
	public void setMedium(String medium) {
		this.medium = medium;
	}
	public String getHigh() {
		return high;
	}
	public void setHigh(String high) {
		this.high = high;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AvailablePassenger [value=");
		builder.append(value);
		builder.append(", Low=");
		builder.append(low);
		builder.append(", Medium=");
		builder.append(medium);
		builder.append(", High=");
		builder.append(high);
		builder.append("]");
		return builder.toString();
	}	
	
}
