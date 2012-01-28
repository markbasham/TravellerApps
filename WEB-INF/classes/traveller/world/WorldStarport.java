package traveller.world;

import java.util.HashMap;

public class WorldStarport {

	private String starportClass;
	private String quality;
	private String berthingCost;
	private String fuel;
	private String facilities;
	private HashMap<Base, Integer> bases;
	
	public String getStarportClass() {
		return starportClass;
	}
	public void setStarportClass(String starportClass) {
		this.starportClass = starportClass;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public String getBerthingCost() {
		return berthingCost;
	}
	public void setBerthingCost(String berthingCost) {
		this.berthingCost = berthingCost;
	}
	public String getFuel() {
		return fuel;
	}
	public void setFuel(String fuel) {
		this.fuel = fuel;
	}
	public String getFacilities() {
		return facilities;
	}
	public void setFacilities(String facilities) {
		this.facilities = facilities;
	}
	public HashMap<Base, Integer> getBases() {
		return bases;
	}
	public void setBases(HashMap<Base, Integer> bases) {
		this.bases = bases;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WorldStarport [starportClass=");
		builder.append(starportClass);
		builder.append(", quality=");
		builder.append(quality);
		builder.append(", berthingCost=");
		builder.append(berthingCost);
		builder.append(", fuel=");
		builder.append(fuel);
		builder.append(", facilities=");
		builder.append(facilities);
		builder.append(", bases=");
		builder.append(bases);
		builder.append("]");
		return builder.toString();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((starportClass == null) ? 0 : starportClass.hashCode());
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
		WorldStarport other = (WorldStarport) obj;
		if (starportClass == null) {
			if (other.starportClass != null)
				return false;
		} else if (!starportClass.equals(other.starportClass))
			return false;
		return true;
	}
	
	
	
}
