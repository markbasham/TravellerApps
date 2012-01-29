package traveller;


public class Utils {

	public static boolean isIn(String range, int value) {

		if(range.contains(",")) {
			String ranges[] = range.split(",");
			for (String string : ranges) {
				if (isIn(string,value)) return true;				
			}
			return false;
		}

		if(range.endsWith("+")) {
			String check = range.replace("+", "");
			// do check for minus
			if (value >= Integer.parseInt(check)) {
				return true;
			}
			return false;
		}

		if(range.endsWith("-")) {
			String check = range.replace("-", "");
			// do check for minus
			if (value <= Integer.parseInt(check)) {
				return true;
			}
			return false;
		}

		if(range.contains("-")) {
			String ends[] = range.split("-");
			for (int i = Integer.parseInt(ends[0]); i <= Integer.parseInt(ends[1]); i++) {
				if (value == i) {
					return true;
				}
			}
			return false;
		}

		// otherwise hopefully its just a number, in which case check it
		if (value == Integer.parseInt(range)) {
			return true;
		}
		return false;		

	}

	public static String isAt(Iterable<String> ranges, int value) {
		for (String range : ranges) {
			if(isIn(range, value)) return range;
		}
		return null;		
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// couple of tests
		System.out.println(isIn("2", 2));
		System.out.println(isIn("2", 5));
		System.out.println(isIn("2,3,4", 3));
		System.out.println(isIn("2,3,4", 5));
		System.out.println(isIn("2-8", 5));
		System.out.println(isIn("3-6", 5));
		System.out.println(isIn("3-6", 8));
		System.out.println(isIn("5+", 8));
		System.out.println(isIn("5+", 5));
		System.out.println(isIn("5+", 3));
		System.out.println(isIn("4-", 8));
		System.out.println(isIn("4-", 4));
		System.out.println(isIn("4-", 2));
		System.out.println("here");
		System.out.println(isIn("0-2,4,6-9,11,14+", 3));
		System.out.println(isIn("0-2,4,6-9,11,14+", 4));
		System.out.println(isIn("0-2,4,6-9,11,14+", 5));
		System.out.println(isIn("0-2,4,6-9,11,14+", 6));
		System.out.println(isIn("0-2,4,6-9,11,14+", 7));
		System.out.println(isIn("0-2,4,6-9,11,14+", 8));
		System.out.println(isIn("0-2,4,6-9,11,14+", 9));
		System.out.println(isIn("0-2,4,6-9,11,14+", 10));
		System.out.println(isIn("0-2,4,6-9,11,14+", 11));
		System.out.println(isIn("0-2,4,6-9,11,14+", 12));
		System.out.println(isIn("0-2,4,6-9,11,14+", 13));
		System.out.println(isIn("0-2,4,6-9,11,14+", 14));


	}

}
