package traveller.trade;

public class DefinedGood {
	
	//TODO must sort out the Exotic items list
	
	private String 	description;
	private String 	tonIncrement;
	private int 	basePrice;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTonIncrement() {
		return tonIncrement;
	}
	public void setTonIncrement(String tonIncrement) {
		this.tonIncrement = tonIncrement;
	}
	public int getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(int basePrice) {
		this.basePrice = basePrice;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DefinedGood [description=");
		builder.append(description);
		builder.append(", tonIncrement=");
		builder.append(tonIncrement);
		builder.append(", basePrice=");
		builder.append(basePrice);
		builder.append("]");
		return builder.toString();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + basePrice;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DefinedGood other = (DefinedGood) obj;
		if (basePrice != other.basePrice)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		return true;
	}
	
	
}
