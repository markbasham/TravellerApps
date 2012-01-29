package traveller.trade.generators;

import java.util.Vector;

import traveller.Dice;
import traveller.TravellerFactory;
import traveller.subsector.Location;
import traveller.trade.TradeCode;
import traveller.world.World;

public class Passengers {

	private int numberOfLowPassengers;
	private int numberOfMiddlePassengers;
	private int numberOfHighPassengers;
	private Vector<Passenger> outstandingPassengers = new Vector<Passenger>();
	private World current;
	private World destination;
	
	public int getNumberOfLowPassengers() {
		return numberOfLowPassengers;
	}

	public int getNumberOfMiddlePassengers() {
		return numberOfMiddlePassengers;
	}

	public int getNumberOfHighPassengers() {
		return numberOfHighPassengers;
	}

	public Vector<Passenger> getOutstandingPassengers() {
		return outstandingPassengers;
	}

	public World getCurrent() {
		return current;
	}

	public World getDestination() {
		return destination;
	}

	public Passengers(World current, World destination, int enviromentalModifier) {

		// Initial setup
		this.current = current;
		this.destination = destination;
		
		// get the modifiers
		int modifiers = destination.getPopulation().getDigit() + enviromentalModifier;

		for (TradeCode code : current.getTradeCodeList()) {
			modifiers += TravellerFactory.getPassengerTraffic().getWorldDMs().get(code).getCurrentDM();
		}

		for (TradeCode code : destination.getTradeCodeList()) {
			modifiers += TravellerFactory.getPassengerTraffic().getWorldDMs().get(code).getDestinationDM();
		}

		// now the tech level modifier
		int techMod = Math.abs(current.getTech().getDigit() - destination.getTech().getDigit());
		if (techMod > 5) techMod = 5;

		modifiers -= techMod;

		// now work out how many lots there are, and then populate the Freight
		String incidental = TravellerFactory.getAvailablePassengers().getValue(modifiers).getLow();
		String minor = TravellerFactory.getAvailablePassengers().getValue(modifiers).getMedium();
		String major = TravellerFactory.getAvailablePassengers().getValue(modifiers).getHigh();

		numberOfLowPassengers = Dice.roll(incidental);
		numberOfMiddlePassengers = Dice.roll(minor);
		numberOfHighPassengers = Dice.roll(major);
		
		if (numberOfLowPassengers < 0) numberOfLowPassengers = 0;
		if (numberOfMiddlePassengers < 0) numberOfMiddlePassengers = 0;
		if (numberOfHighPassengers < 0) numberOfHighPassengers = 0;

		int specialMiddlePassengers = ((numberOfMiddlePassengers+5)/6);
		
		for (int i = 0; i < specialMiddlePassengers; i++) {
			if (Dice.roll1D6(0)>3) {
				Passenger passenger = new Passenger(Passenger.MIDDLE);
				outstandingPassengers.add(passenger);
				numberOfMiddlePassengers--;				
			}
		}

		int specialHighPassengers = ((numberOfHighPassengers+5)/6);
	
		
		for (int i = 0; i < specialHighPassengers; i++) {
			if (Dice.roll1D6(0)>3) {
				Passenger passenger = new Passenger(Passenger.HIGH);
				outstandingPassengers.add(passenger);
				numberOfHighPassengers--;
			}
		}

	}	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Passengers [numberOfLowPassengers=");
		builder.append(numberOfLowPassengers);
		builder.append(", numberOfMiddlePassengers=");
		builder.append(numberOfMiddlePassengers);
		builder.append(", numberOfHighPassengers=");
		builder.append(numberOfHighPassengers);
		builder.append(", outstandingPassengers=");
		builder.append(outstandingPassengers);
		builder.append("]");
		return builder.toString();
	}


	public static void main(String[] args) {
		
		for (int i = 0; i < 10; i++) {
			Passengers p = new Passengers(new World("current", new Location(0,0)), new World("destination", new Location(0,0)), 0);
			System.out.println(p);
		}

	}

}
