package traveller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import traveller.trade.AvailableFreightLots;
import traveller.trade.AvailablePassengers;
import traveller.trade.PassengerTypes;
import traveller.trade.TradeCodes;
import traveller.trade.TradeGoods;
import traveller.trade.Traffic;
import traveller.world.WorldAtmospheres;
import traveller.world.WorldGovernments;
import traveller.world.WorldHydrographics;
import traveller.world.WorldLawLevels;
import traveller.world.WorldPopulations;
import traveller.world.WorldSizes;
import traveller.world.WorldStarports;
import traveller.world.WorldTechLevels;

public class TravellerFactory {

	private static ApplicationContext context = null;
	
	// Trade items
	private static TradeCodes tradeCodes = null;
	private static TradeGoods tradeGoods = null;
	private static Traffic freightTraffic = null;
	private static AvailableFreightLots availableFreightLots = null;
	private static Traffic passengerTraffic = null;	
	private static AvailablePassengers availablePassengers = null;	
	private static PassengerTypes passengerTypes = null;
	
	// World Items
	private static WorldSizes worldSizes = null;
	private static WorldAtmospheres worldAtmospheres = null;
	private static WorldHydrographics worldHydrographics = null;
	private static WorldPopulations worldPopulations = null;
	private static WorldGovernments worldGovernments = null;
	private static WorldLawLevels worldLawLevels = null;
	private static WorldStarports worldStarports = null;
	private static WorldTechLevels worldTechLevels = null;
	
	private static ApplicationContext getContext() {
		if (context == null) {
			context = new ClassPathXmlApplicationContext(new String[] {"traveller/xml/Traveller.xml"});
		}
		return context;
	}

	public static TradeCodes getTradeCodes() {
		if(tradeCodes == null) {
			tradeCodes = getContext().getBean("tradeCodes",TradeCodes.class);
		}
		return tradeCodes;
	}

	public static TradeGoods getTradeGoods() {
		if(tradeGoods == null) {
			tradeGoods = getContext().getBean("tradeGoods",TradeGoods.class);
		}
		return tradeGoods;
	}

	public static Traffic getFreightTraffic() {
		if(freightTraffic == null) {
			freightTraffic = getContext().getBean("freightTraffic",Traffic.class);
		}
		return freightTraffic;
	}

	public static AvailableFreightLots getAvailableFreightLots() {
		if(availableFreightLots == null) {
			availableFreightLots = getContext().getBean("availableFreightLots",AvailableFreightLots.class);
		}
		return availableFreightLots;
	}
	
	public static AvailablePassengers getAvailablePassengers() {
		if(availablePassengers == null) {
			availablePassengers = getContext().getBean("availablePassengers",AvailablePassengers.class);
		}
		return availablePassengers;
	}

	public static Traffic getPassengerTraffic() {
		if(passengerTraffic == null) {
			passengerTraffic = getContext().getBean("passengerTraffic",Traffic.class);
		}
		return passengerTraffic;
	}
	
	
	public static PassengerTypes getPassengerTypes() {
		if(passengerTypes == null) {
			passengerTypes = getContext().getBean("passengerTypes",PassengerTypes.class);
		}
		return passengerTypes;
	}

	public static WorldSizes getWorldSizes() {
		if(worldSizes == null) {
			worldSizes = getContext().getBean("worldSizes",WorldSizes.class);
		}
		return worldSizes;
	}
	

	public static WorldAtmospheres getWorldAtmospheres() {
		if(worldAtmospheres == null) {
			worldAtmospheres = getContext().getBean("worldAtmospheres",WorldAtmospheres.class);
		}
		return worldAtmospheres;
	}
	

	public static WorldHydrographics getWorldHydrographics() {
		if(worldHydrographics == null) {
			worldHydrographics = getContext().getBean("worldHydrographics",WorldHydrographics.class);
		}
		return worldHydrographics;
	}
	
	public static WorldPopulations getWorldPopulations() {
		if(worldPopulations == null) {
			worldPopulations = getContext().getBean("worldPopulations",WorldPopulations.class);
		}
		return worldPopulations;
	}

	public static WorldGovernments getWorldGovernments() {
		if(worldGovernments == null) {
			worldGovernments = getContext().getBean("worldGovernments",WorldGovernments.class);
		}
		return worldGovernments;
	}

	
	public static WorldLawLevels getWorldLawLevels() {
		if(worldLawLevels == null) {
			worldLawLevels = getContext().getBean("worldLawLevels",WorldLawLevels.class);
		}
		return worldLawLevels;
	}
	
	public static WorldStarports getWorldStarports() {
		if(worldStarports == null) {
			worldStarports = getContext().getBean("worldStarports",WorldStarports.class);
		}
		return worldStarports;
	}

	public static WorldTechLevels getWorldTechLevels() {
		if(worldTechLevels == null) {
			worldTechLevels = getContext().getBean("worldTechLevels",WorldTechLevels.class);
		}
		return worldTechLevels;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("Trade Factory");
		
		System.out.println(getTradeCodes());
		System.out.println(getTradeGoods());
		System.out.println(getFreightTraffic());
		System.out.println(getAvailableFreightLots());
		System.out.println(getPassengerTraffic());
		System.out.println(getAvailablePassengers());
		System.out.println(getPassengerTypes());
		System.out.println(getWorldSizes());
		System.out.println(getWorldAtmospheres());
		System.out.println(getWorldHydrographics());
		System.out.println(getWorldPopulations());
		System.out.println(getWorldGovernments());
		System.out.println(getWorldLawLevels());
		System.out.println(getWorldStarports());
		System.out.println(getWorldTechLevels());
		
	}

}
