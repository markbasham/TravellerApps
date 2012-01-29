package traveller.trade;

public class WorldDMs {

	private TradeCode code;
	private Integer currentDM;
	private Integer destinationDM;
	
	
	public TradeCode getCode() {
		return code;
	}
	public void setCode(TradeCode code) {
		this.code = code;
	}
	public int getCurrentDM() {
		return currentDM;
	}
	public void setCurrentDM(int currentDM) {
		this.currentDM = currentDM;
	}
	public int getDestinationDM() {
		return destinationDM;
	}
	public void setDestinationDM(int destinationDM) {
		this.destinationDM = destinationDM;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FreightDMs [code=");
		builder.append(code);
		builder.append(", currentDM=");
		builder.append(currentDM);
		builder.append(", destinationDM=");
		builder.append(destinationDM);
		builder.append("]");
		return builder.toString();
	}

}
