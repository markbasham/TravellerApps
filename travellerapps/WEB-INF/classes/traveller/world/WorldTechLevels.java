package traveller.world;

import java.util.HashMap;

import traveller.Utils;

public class WorldTechLevels {

	private HashMap<WorldStarport, Integer> starportModifier;
	private HashMap<String, Integer> sizeModifier;
	private HashMap<String, Integer> atmosphereModifier;
	private HashMap<String, Integer> hydroModifier;
	private HashMap<String, Integer> populationModifier;
	private HashMap<String, Integer> governmentModifier;
	private HashMap<String, Integer> enviromentalLimit;
	
	private HashMap<Integer, WorldTechLevel> techLevels;
	
	private Integer minDigit = null;
	private Integer maxDigit = null;
	
	public HashMap<WorldStarport, Integer> getStarportModifier() {
		return starportModifier;
	}
	public void setStarportModifier(HashMap<WorldStarport, Integer> starportModifier) {
		this.starportModifier = starportModifier;
	}
	public HashMap<String, Integer> getSizeModifier() {
		return sizeModifier;
	}
	public void setSizeModifier(HashMap<String, Integer> sizeModifier) {
		this.sizeModifier = sizeModifier;
	}
	public HashMap<String, Integer> getAtmosphereModifier() {
		return atmosphereModifier;
	}
	public void setAtmosphereModifier(HashMap<String, Integer> atmosphereModifier) {
		this.atmosphereModifier = atmosphereModifier;
	}
	public HashMap<String, Integer> getHydroModifier() {
		return hydroModifier;
	}
	public void setHydroModifier(HashMap<String, Integer> hydroModifier) {
		this.hydroModifier = hydroModifier;
	}
	public HashMap<String, Integer> getPopulationModifier() {
		return populationModifier;
	}
	public void setPopulationModifier(HashMap<String, Integer> populationModifier) {
		this.populationModifier = populationModifier;
	}
	public HashMap<String, Integer> getGovernmentModifier() {
		return governmentModifier;
	}
	public void setGovernmentModifier(HashMap<String, Integer> governmentModifier) {
		this.governmentModifier = governmentModifier;
	}
	public HashMap<String, Integer> getEnviromentalLimit() {
		return enviromentalLimit;
	}
	public void setEnviromentalLimit(HashMap<String, Integer> enviromentalLimit) {
		this.enviromentalLimit = enviromentalLimit;
	}
	public HashMap<Integer, WorldTechLevel> getTechLevels() {
		return techLevels;
	}
	public void setTechLevels(HashMap<Integer, WorldTechLevel> techLevels) {
		this.techLevels = techLevels;
	}
	
	public int getStarportDM(WorldStarport starport) {
		for (WorldStarport port : this.starportModifier.keySet()) {
			if (port.equals(starport)) return this.starportModifier.get(port);
		}
		return 0;
	}
	
	public int getSizeDM(WorldSize size) {
		for (String worldSize : this.sizeModifier.keySet()) {
			if (Utils.isIn(worldSize, size.getDigit())) return this.sizeModifier.get(worldSize);
		}
		return 0;
	}
	
	public int getAtmosphereDM(WorldAtmosphere atmosphere) {
		for (String worldAtmos : this.atmosphereModifier.keySet()) {
			if (Utils.isIn(worldAtmos, atmosphere.getDigit())) return this.atmosphereModifier.get(worldAtmos);
		}
		return 0;
	}
	
	public int getHydroDM(WorldHydrographic hydro) {
		for (String worldHydro : this.hydroModifier.keySet()) {
			if (Utils.isIn(worldHydro, hydro.getDigit())) return this.hydroModifier.get(worldHydro);
		}
		return 0;
	}
	
	public int getPopulationDM(WorldPopulation population) {
		for (String worldPop : this.populationModifier.keySet()) {
			if (Utils.isIn(worldPop, population.getDigit())) return this.populationModifier.get(worldPop);
		}
		return 0;
	}
	
	public int getGovernmentDM(WorldGovernment government) {
		for (String worldGov : this.governmentModifier.keySet()) {
			if (Utils.isIn(worldGov, government.getDigit())) return this.governmentModifier.get(worldGov);
		}
		return 0;
	}
	
	public int getEnviromentalMin(WorldAtmosphere atmosphere) {
		for (String worldAtmos : this.enviromentalLimit.keySet()) {
			if (Utils.isIn(worldAtmos, atmosphere.getDigit())) return this.enviromentalLimit.get(worldAtmos);
		}
		return 0;
	}
	
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WorldTechLevels [starportModifier=");
		builder.append(starportModifier);
		builder.append(", sizeModifier=");
		builder.append(sizeModifier);
		builder.append(", atmosphereModifier=");
		builder.append(atmosphereModifier);
		builder.append(", hydroModifier=");
		builder.append(hydroModifier);
		builder.append(", populationModifier=");
		builder.append(populationModifier);
		builder.append(", governmantModifier=");
		builder.append(governmentModifier);
		builder.append(", enviromentalLimit=");
		builder.append(enviromentalLimit);
		builder.append(", techLevels=");
		builder.append(techLevels);
		builder.append("]");
		return builder.toString();
	}
	
	private void getMinMax() {
		maxDigit = -1000;
		minDigit = 1000;
		for (Integer digit : this.techLevels.keySet()) {
			if (digit > maxDigit) maxDigit = digit;
			if (digit < minDigit) minDigit = digit;
		}
	}

	private int getMax() {
		if (maxDigit == null) {
			getMinMax();
		}
		return maxDigit;		
	}

	private int getMin() {
		if (minDigit == null) {
			getMinMax();
		}
		return minDigit;		
	}	
	
	public WorldTechLevel getDigit(int digit) {
		// clip the return value to within the bounds
		if (digit < getMin()) return techLevels.get(getMin());
		if (digit > getMax()) return techLevels.get(getMax());
		return techLevels.get(digit);
	}
	
}
