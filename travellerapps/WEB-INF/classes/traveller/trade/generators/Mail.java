package traveller.trade.generators;

import traveller.Dice;
import traveller.TravellerFactory;
import traveller.subsector.Location;
import traveller.trade.TradeCode;
import traveller.world.World;

public class Mail {

	static private int getMailModifier(int freightMod) {
		if (freightMod < -9) return -2;
		if (freightMod < -4) return -1;
		if (freightMod < 5) return 0;
		if (freightMod < 10) return 1;
		return 2;

	}

	static public boolean mailAvailable(World current, World destination, boolean shipArmed, int navalScoutFreeTraderRank, int socialStandingDM) {
		
		// get the modifiers
		int modifiers = destination.getPopulation().getDigit();
		
		for (TradeCode code : current.getTradeCodeList()) {
			modifiers += TravellerFactory.getFreightTraffic().getWorldDMs().get(code).getCurrentDM();
		}
		
		for (TradeCode code : destination.getTradeCodeList()) {
			modifiers += TravellerFactory.getFreightTraffic().getWorldDMs().get(code).getDestinationDM();
		}

		int mod = getMailModifier(modifiers);
		
		// check the TLS
		int tlMod = 0;
		if(current.getTech().getDigit() < 6) tlMod = -4;
		if(destination.getTech().getDigit() < 6) tlMod = -4;
		
		// add all teh things together
		int totalmod = mod + tlMod + navalScoutFreeTraderRank + socialStandingDM;
		if (shipArmed) totalmod += 2;
		
		if(Dice.roll2D6(totalmod) > 11 ) return true;
		return false;	
	}	
	
	
	
	public static void main(String[] args) {
		
		System.out.println(Mail.mailAvailable(new World("Current", new Location(0,0)),
				new World("Destination", new Location(0,0)), true, 0, 0));
		System.out.println(Mail.mailAvailable(new World("Current", new Location(0,0)),
				new World("Destination", new Location(0,0)), false, 1, 0));
		System.out.println(Mail.mailAvailable(new World("Current", new Location(0,0)),
				new World("Destination", new Location(0,0)), true, 0, 1));
		System.out.println(Mail.mailAvailable(new World("Current", new Location(0,0)),
				new World("Destination", new Location(0,0)), false, 1, 1));
		System.out.println(Mail.mailAvailable(new World("Current", new Location(0,0)),
				new World("Destination", new Location(0,0)), true, 2, 1));
		System.out.println(Mail.mailAvailable(new World("Current", new Location(0,0)),
				new World("Destination", new Location(0,0)), false, 1, 2));
		System.out.println(Mail.mailAvailable(new World("Current", new Location(0,0)),
				new World("Destination", new Location(0,0)), true, 2, 2));

	}

}
