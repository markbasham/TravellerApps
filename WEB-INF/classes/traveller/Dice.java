package traveller;

import java.util.Random;

public class Dice {
	
	static Random rand = new Random();
	
	static public int roll1D6(int mod){
		return rand.nextInt(6)+1+mod;
	}

	static public int roll2D6(int mod){
		return rand.nextInt(6)+rand.nextInt(6)+2+mod;
	}
	
	static public int rollD66(){
		return ((rand.nextInt(6)+1)*10)+(rand.nextInt(6)+1);
	}

	public static int roll(String dieString) {
		
		if(dieString.contains("x")) {
			int midpoint = dieString.indexOf('x');
			String left = dieString.substring(0, midpoint);
			String right = dieString.substring(midpoint+1);
			int total = roll(left) * roll(right);				
			return total;
		}
		
		if(dieString.contains("+")) {
			int midpoint = dieString.indexOf('+');
			String left = dieString.substring(0, midpoint);
			String right = dieString.substring(midpoint+1);
			int total = roll(left) + roll(right);				
			return total;
		}
		
		//TODO This dosent work too well for real systems, might need an update
		if(dieString.contains("-")) {
			int midpoint = dieString.indexOf('-');
			String left = dieString.substring(0, midpoint);
			String right = dieString.substring(midpoint+1);
			int total = roll(left) - roll(right);				
			return total;
		}
		
		if(dieString.contains("d")) {
			int midpoint = dieString.indexOf('d');
			String left = dieString.substring(0, midpoint);
			String right = dieString.substring(midpoint+1);
			int total = 0;
			for(int i = 0; i < Integer.parseInt(left); i++ ) {
				total += (rand.nextInt(Integer.parseInt(right))+1);		
			}
			return total;
		}
		
		return Integer.parseInt(dieString);
			
	}
	
	public static void main(String[] args) {

		System.out.println(roll("1d6"));
		System.out.println(roll("2d6"));
		System.out.println(roll("1d8"));
		System.out.println(roll("1d6+2"));
		System.out.println(roll("2d6+5"));
		System.out.println(roll("2d6-5"));
		System.out.println(roll("2d6+2d5+3d6+2"));
		System.out.println(roll("1d6x5"));
		System.out.println(roll("1d6x2"));
		
	}

}