package traveller.world;

import java.util.Vector;

import javax.management.openmbean.InvalidKeyException;

import traveller.Dice;
import traveller.TravellerFactory;
import traveller.Utils;
import traveller.subsector.Location;
import traveller.trade.TradeCode;

public class World {

	String name;

	WorldStarport starport;
	WorldSize size;
	WorldAtmosphere atmosphere;
	WorldHydrographic hydrographic;
	WorldPopulation population;
	WorldGovernment government;
	WorldLawLevel law;
	WorldTechLevel tech;	
	Location location;

	// new Items if available
	private int gasGiants = 0;
	private int populationMultiplier = 1;
	private int belts = 0;

	Vector<TradeCode> TradeCodeList = null;


	public World(String name, String code, Location location) {
		this.name = name;
		this.location = location;
		this.starport = TravellerFactory.getWorldStarports().getStarport(code.charAt(0));
		this.size = TravellerFactory.getWorldSizes().getDigit(Integer.parseInt(code.substring(1, 2),16));
		this.atmosphere = TravellerFactory.getWorldAtmospheres().getDigit(Integer.parseInt(code.substring(2, 3),16));
		this.hydrographic = TravellerFactory.getWorldHydrographics().getDigit(Integer.parseInt(code.substring(3, 4),16));
		this.population = TravellerFactory.getWorldPopulations().getDigit(Integer.parseInt(code.substring(4, 5),16));
		this.government = TravellerFactory.getWorldGovernments().getDigit(Integer.parseInt(code.substring(5, 6),20));
		this.law = TravellerFactory.getWorldLawLevels().getDigit(Integer.parseInt(code.substring(6, 7),20));
		this.tech = TravellerFactory.getWorldTechLevels().getDigit(Integer.parseInt(code.substring(8, 9),17));

		// now the data is inputed, the code list should be generated
		generateTradeCodeList();
	}

	public World(String name, Location location) {
		this.name = name;
		this.location = location;
		this.generateWorld();
		this.generateTradeCodeList();
	}

	private void generateTradeCodeList() {
		TradeCodeList = TravellerFactory.getTradeCodes().getCodes(size.getDigit(),
				atmosphere.getDigit(), hydrographic.getDigit(), population.getDigit(),
				government.getDigit(), law.getDigit(), tech.getDigit());
	}

	private void generateWorld() {
		// Get the size
		this.size = TravellerFactory.getWorldSizes().getDigit(Dice.roll2D6(-2));

		// calculate the atmosphere
		this.atmosphere = TravellerFactory.getWorldAtmospheres().getDigit(Dice.roll2D6(this.size.getDigit()-7));

		// calculate the hydrographics
		int hydrographicValue = Dice.roll2D6(this.size.getDigit()-7);
		if (Utils.isIn("0-1,10-12", this.atmosphere.getDigit())) hydrographicValue -= 4;
		if (Utils.isIn("1-", this.size.getDigit())) hydrographicValue = 0;
		this.hydrographic = TravellerFactory.getWorldHydrographics().getDigit(hydrographicValue);		

		// calc the population
		this.population = TravellerFactory.getWorldPopulations().getDigit(Dice.roll2D6(-2));

		// calculate the government
		this.government = TravellerFactory.getWorldGovernments().getDigit(Dice.roll2D6(this.population.getDigit()-7));

		// calc the Law
		this.law = TravellerFactory.getWorldLawLevels().getDigit(Dice.roll2D6(this.government.getDigit()-7));

		// Calculate the starport
		this.starport = TravellerFactory.getWorldStarports().getDigit(Dice.roll2D6(0));

		// Finaly do the Tech Levels
		int techMods = 0;
		techMods += TravellerFactory.getWorldTechLevels().getAtmosphereDM(atmosphere);
		techMods += TravellerFactory.getWorldTechLevels().getGovernmentDM(government);
		techMods += TravellerFactory.getWorldTechLevels().getHydroDM(hydrographic);
		techMods += TravellerFactory.getWorldTechLevels().getPopulationDM(population);
		techMods += TravellerFactory.getWorldTechLevels().getSizeDM(size);
		techMods += TravellerFactory.getWorldTechLevels().getStarportDM(starport);
		int techlevel = Dice.roll1D6(techMods);
		if (techlevel < TravellerFactory.getWorldTechLevels().getEnviromentalMin(atmosphere) ) 
			techlevel = TravellerFactory.getWorldTechLevels().getEnviromentalMin(atmosphere); 
		this.tech = TravellerFactory.getWorldTechLevels().getDigit(techlevel);

	}

	public String printTradeCodes() {
		String codeString = "";
		for (TradeCode code : TradeCodeList) {
			codeString += code.getCode() + " ";			
		}
		return codeString;
	}

	public String getUPP() {
		return String.format("%s%s%s%s%s%s%s-%s", 
				this.starport.getStarportClass(),
				Integer.toString(this.size.getDigit(),20).toUpperCase(),
				Integer.toString(this.atmosphere.getDigit(),20).toUpperCase(),
				Integer.toString(this.hydrographic.getDigit(),20).toUpperCase(),
				Integer.toString(this.population.getDigit(),20).toUpperCase(),
				Integer.toString(this.government.getDigit(),20).toUpperCase(),
				Integer.toString(this.law.getDigit(),20).toUpperCase(),
				Integer.toString(this.tech.getDigit(),20).toUpperCase());
	}

	@Override
	public String toString() {
		return String.format("%-10s %s %s", 
				name,
				this.getUPP(),
				this.printTradeCodes());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public WorldStarport getStarport() {
		return starport;
	}

	public void setStarport(WorldStarport starport) {
		this.starport = starport;
	}

	public WorldSize getSize() {
		return size;
	}

	public void setSize(WorldSize size) {
		this.size = size;
	}

	public WorldAtmosphere getAtmosphere() {
		return atmosphere;
	}

	public void setAtmosphere(WorldAtmosphere atmosphere) {
		this.atmosphere = atmosphere;
	}

	public WorldHydrographic getHydrographic() {
		return hydrographic;
	}

	public void setHydrographic(WorldHydrographic hydrographic) {
		this.hydrographic = hydrographic;
	}

	public WorldPopulation getPopulation() {
		return population;
	}

	public void setPopulation(WorldPopulation population) {
		this.population = population;
	}

	public WorldGovernment getGovernment() {
		return government;
	}

	public void setGovernment(WorldGovernment government) {
		this.government = government;
	}

	public WorldLawLevel getLaw() {
		return law;
	}

	public void setLaw(WorldLawLevel law) {
		this.law = law;
	}

	public WorldTechLevel getTech() {
		return tech;
	}

	public void setTech(WorldTechLevel tech) {
		this.tech = tech;
	}

	public Vector<TradeCode> getTradeCodeList() {
		return TradeCodeList;
	}

	public void setTradeCodeList(Vector<TradeCode> tradeCodeList) {
		TradeCodeList = tradeCodeList;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public void setBelts(int belts) {
		this.belts = belts;
	}

	public int getBelts() {
		return belts;
	}

	public void setPopulationMultiplier(int populationMultiplier) {
		this.populationMultiplier = populationMultiplier;
	}

	public int getPopulationMultiplier() {
		return populationMultiplier;
	}

	public void setGasGiants(int gasGiants) {
		this.gasGiants = gasGiants;
	}

	public int getGasGiants() {
		return gasGiants;
	}
	
	public boolean hasGasGiant() {
		if (gasGiants > 0) return true;
		return false;
	}

	public void addTravelZoneCode(String code) {
		if (code.toUpperCase().contains("A")) {
			TradeCodeList.add(TravellerFactory.getTradeCodes().getTradeCode("A"));
			return;
		}
		if (code.toUpperCase().contains("R")) {
			TradeCodeList.add(TravellerFactory.getTradeCodes().getTradeCode("R"));
			return;
		}
		throw new InvalidKeyException("Travel Zone code should be an A or R");
	}	

	public void addPBGCode(String PBG) {
		this.setPopulationMultiplier(Integer.parseInt(PBG.substring(0,1)));
		this.setBelts(Integer.parseInt(PBG.substring(1,2)));
		this.setGasGiants(Integer.parseInt(PBG.substring(2,3)));
	}

	public String generateWorldLink(String linkName, String target) {
		if(target == null) {
			return String.format("<a href='WorldServlet?worldLocation=%s'>%s</a>",
					getLocation() , linkName);
		}
		return String.format("<a href='WorldServlet?worldLocation=%s' target='%s'>%s</a>",
				getLocation() , target, linkName);
	}	

	public static void main(String[] args) {
		System.out.println("World Test");

		World world = new World("Test", "A345678-9", new Location(0,0));
		System.out.println(world);

		// test from the core worlds.
		World coreWorld = new World("Test", "B6529EG-D", new Location(0,0));
		System.out.println(coreWorld);

		World worldA = new World("Amber Test", "A345678-9", new Location(0,0));
		worldA.addTravelZoneCode("A");
		System.out.println(worldA);

		World worldR = new World("Red Test", "A345678-9", new Location(0,0));
		worldR.addTravelZoneCode("R");
		System.out.println(worldR);


		for (int i = 0; i < 10; i++) {

			World world2 = new World(String.format("Gen %d",i),new Location(0,0));
			System.out.println(world2);
		}

		System.out.println("F = " + Integer.parseInt("F",16));
		System.out.println("H = " + Integer.parseInt("H",27));

		World worldPBG = new World("GasTest", "A345678-9", new Location(0,0));
		worldPBG.addPBGCode("312");
		System.out.println(worldPBG);
		System.out.println(worldPBG.hasGasGiant());

		
	}

}