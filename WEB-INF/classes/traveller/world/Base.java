package traveller.world;

public class Base {

	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Base [name=");
		builder.append(type);
		builder.append("]");
		return builder.toString();
	}
	
}
