package traveller.world;

import java.util.HashMap;

public class WorldSizes {

	private HashMap<Integer, WorldSize> sizes;

	public HashMap<Integer, WorldSize> getSizes() {
		return sizes;
	}

	public void setSizes(HashMap<Integer, WorldSize> sizes) {
		this.sizes = sizes;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WorldSizes [worldSize=");
		builder.append(sizes);
		builder.append("]");
		return builder.toString();

	}

	public WorldSize getDigit(int digit) {
		return sizes.get(digit);
	}

}
