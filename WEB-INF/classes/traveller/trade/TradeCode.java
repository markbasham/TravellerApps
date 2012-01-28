package traveller.trade;

import traveller.Utils;

public class TradeCode {

	private String code;
	private String name;
	private String description;
	private String compPlanetSize;
	private String compAtmosphere;
	private String compHydro;
	private String compPopulation;
	private String compGovernment;
	private String compLawLevel;
	private String compTechLevel;
	private Boolean travelZone;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCompPlanetSize() {
		return compPlanetSize;
	}
	public void setCompPlanetSize(String compPlanetSize) {
		this.compPlanetSize = compPlanetSize;
	}
	public String getCompAtmosphere() {
		return compAtmosphere;
	}
	public void setCompAtmosphere(String compAtmosphere) {
		this.compAtmosphere = compAtmosphere;
	}
	public String getCompHydro() {
		return compHydro;
	}
	public void setCompHydro(String compHydro) {
		this.compHydro = compHydro;
	}
	public String getCompPopulation() {
		return compPopulation;
	}
	public void setCompPopulation(String compPopulation) {
		this.compPopulation = compPopulation;
	}
	public String getCompGovernment() {
		return compGovernment;
	}
	public void setCompGovernment(String compGovernment) {
		this.compGovernment = compGovernment;
	}
	public String getCompLawLevel() {
		return compLawLevel;
	}
	public void setCompLawLevel(String compLawLevel) {
		this.compLawLevel = compLawLevel;
	}
	public String getCompTechLevel() {
		return compTechLevel;
	}
	public void setCompTechLevel(String compTechLevel) {
		this.compTechLevel = compTechLevel;
	}
	public boolean isTravelZone() {
		if(travelZone == null) {
			return false;
		}
		return travelZone;
	}
	public void setTravelZone(boolean travelZone) {
		this.travelZone = travelZone;
	}
	
	public boolean compareCode(int size, int atmos, int hydro, int pop, int gov, int law, int tech) {
		
		if(isTravelZone()) return false;
		
		if (compPlanetSize != "") {
			if (!Utils.isIn(compPlanetSize, size)){
				return false;
			}
		}
		
		if (compAtmosphere != "") {
			if (!Utils.isIn(compAtmosphere, atmos)){
				return false;
			}
		}
		
		if (compHydro != "") {
			if (!Utils.isIn(compHydro, hydro)){
				return false;
			}
		}
		
		if (compPopulation != "") {
			if (!Utils.isIn(compPopulation, pop)){
				return false;
			}
		}
		
		if (compGovernment != "") {
			if (!Utils.isIn(compGovernment, gov)){
				return false;
			}
		}
		
		if (compLawLevel != "") {
			if (!Utils.isIn(compLawLevel, law)){
				return false;
			}
		}
		
		if (compTechLevel != "") {
			if (!Utils.isIn(compTechLevel, tech)){
				return false;
			}
		}
		
		return true;
		
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		TradeCode other = (TradeCode) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TradeCode [code=");
		builder.append(code);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}
	
}
