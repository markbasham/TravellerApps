package traveller.trade;

import java.util.HashMap;

public class Traffic {

	private HashMap<TradeCode,WorldDMs> worldDMs;

	public HashMap<TradeCode, WorldDMs> getWorldDMs() {
		return worldDMs;
	}

	public void setWorldDMs(HashMap<TradeCode, WorldDMs> worldDMs) {
		this.worldDMs = worldDMs;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FreightTraffic [freightDMs=");
		builder.append(worldDMs.toString().replace("],", "]\n"));
		builder.append("]");
		return builder.toString();
	}	
	
}
