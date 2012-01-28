package traveller.world;

public class WorldSize {

	private int digit;
	private int size;
	private float gravity;
	
	public int getDigit() {
		return digit;
	}
	public void setDigit(int digit) {
		this.digit = digit;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public float getGravity() {
		return gravity;
	}
	public void setGravity(float gravity) {
		this.gravity = gravity;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WorldSize [digit=");
		builder.append(digit);
		builder.append(", size=");
		builder.append(size);
		builder.append(", gravity=");
		builder.append(gravity);
		builder.append("]");
		return builder.toString();
	}
	
}
