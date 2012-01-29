package traveller.trade.generators;

import java.util.HashMap;

import traveller.Dice;
import traveller.TravellerFactory;
import traveller.Utils;
import traveller.trade.PassengerType;

public class Passenger {
	
	static final String MIDDLE = "Middle";
	static final String HIGH = "High";
	
	private HashMap<String, Integer> skills = new HashMap<String, Integer>();
	private String descriptor = null;
	
	String travelClass;
	PassengerType type;
	
	public HashMap<String, Integer> getSkills() {
		return skills;
	}

	public String getTravelClass() {
		return travelClass;
	}

	public PassengerType getType() {
		return type;
	}
	
	public String getDescription() {
		if(descriptor == null) return type.getDescription();
		return String.format("%s(%s)", type.getDescription(), descriptor); 		
	}
	
	public Passenger(String travelClass) {
		this(travelClass, Dice.rollD66());
	}
	
	public Passenger(String travelClass, int roll) {
		this.travelClass = travelClass;
		this.type = TravellerFactory.getPassengerTypes().getValue(roll);
		fleshOut();
	}
	
	private void fleshOut() {
		
		//TODO could do with adding in a random name
		
		if (type.getSkills() != null) {
			for (String skill : type.getSkills().keySet()) {
				skills.put(skill, Dice.roll(type.getSkills().get(skill)));
			}
		}
		
		//TODO trait could be used for Alien type, more specifically.
		if(type.getTraits() != null) {
			descriptor = type.getTraits().get(Utils.isAt(type.getTraits().keySet(), Dice.roll1D6(0)));
		}
		
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Passenger [skills=");
		builder.append(skills);
		builder.append(", descriptor=");
		builder.append(descriptor);
		builder.append(", travelClass=");
		builder.append(travelClass);
		builder.append(", type=");
		builder.append(type);
		builder.append("]");
		return builder.toString();
	}
	
	

}
