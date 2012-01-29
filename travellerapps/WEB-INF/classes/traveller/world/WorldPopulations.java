package traveller.world;

import java.util.HashMap;

public class WorldPopulations {

	private HashMap<Integer, WorldPopulation> populations;

	public HashMap<Integer, WorldPopulation> getPopulations() {
		return populations;
	}

	public void setPopulations(HashMap<Integer, WorldPopulation> populations) {
		this.populations = populations;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WorldPopulations [populations=");
		builder.append(populations);
		builder.append("]");
		return builder.toString();
	}

	public WorldPopulation getDigit(int digit) {
		return populations.get(digit);
	}
	
}
