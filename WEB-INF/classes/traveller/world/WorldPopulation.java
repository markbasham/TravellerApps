package traveller.world;

public class WorldPopulation {

	private int digit;
	private String population;
	private long range;
	private String description;
	
	public int getDigit() {
		return digit;
	}
	public void setDigit(int digit) {
		this.digit = digit;
	}
	public String getPopulation() {
		return population;
	}
	public void setPopulation(String population) {
		this.population = population;
	}
	public long getRange() {
		return range;
	}
	public void setRange(long range) {
		this.range = range;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WorldPopulation [digit=");
		builder.append(digit);
		builder.append(", population=");
		builder.append(population);
		builder.append(", range=");
		builder.append(range);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}
	
}
