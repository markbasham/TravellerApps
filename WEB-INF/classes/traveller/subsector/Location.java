package traveller.subsector;

import java.util.ArrayList;
import java.util.List;

public class Location implements Comparable<Location>{

	public static Location[] concat(Location[] l1, Location[] l2) {
		ArrayList<Location> locations = new ArrayList<Location>();
		for (Location location : l1) {
			locations.add(location);
		}
		for (Location location : l2) {
			locations.add(location);
		}
		return locations.toArray(new Location[0]);
	}
	
	public static Location[] unique(Location[] l1) {
		ArrayList<Location> locations = new ArrayList<Location>();
		
		for (Location location : l1) {
			if(location.isIn(locations) == false) locations.add(location);
		}
		
		return locations.toArray(new Location[0]);
	}
	
	public static Location[] remove(Location[] original, Location[] removeList) {
		ArrayList<Location> locations = new ArrayList<Location>();
		
		for (Location location : original) {
			if(location.isIn(removeList) == false) locations.add(location);
		}
		
		return locations.toArray(new Location[0]);
	}
	
	
	int column;
	int row;
	
	public Location(int column, int row) {
		super();
		this.column = column;
		this.row = row;
	}
	
	public Location(String string) {
		super();
		this.column = Integer.parseInt(string.substring(0, 2));
		this.row = Integer.parseInt(string.substring(2, 4));
	}

	public Location[] jump1Locations() {
		ArrayList<Location> locations = new ArrayList<Location>();
		
		// first add the hex above and below
		locations.add(new Location(column,row-1));
		locations.add(new Location(column,row+1));
		
		if (column%2 == 0) {
			locations.add(new Location(column-1,row));
			locations.add(new Location(column-1,row+1));
			locations.add(new Location(column+1,row));
			locations.add(new Location(column+1,row+1));
		} else {
			locations.add(new Location(column-1,row-1));
			locations.add(new Location(column-1,row));
			locations.add(new Location(column+1,row-1));
			locations.add(new Location(column+1,row));
		}
		
		// finally go through the list and remove elements which are outside the limits
		ArrayList<Location> result = new ArrayList<Location>();
		for (Location location : locations) {
			if (location.row < 1) continue;
			if (location.row > 40) continue;
			if (location.column < 1) continue;
			if (location.column > 32) continue;
			result.add(location);
		}
			
		return result.toArray(new Location[0]);
	}
	
	public Location[] jumpLocations(int jumpNumber) {
		
		Location[] visitedList = {this};
		
		// ok, start with a single jump
		Location[] jumplocations = jump1Locations();
		
		// for each jump over the first
		for(int i = 1; i < jumpNumber; i++) {
			visitedList = Location.unique(Location.concat(visitedList, jumplocations));
			Location[] newJumpLocations = new Location[0];
			for (Location location : jumplocations) {
				newJumpLocations = Location.concat(newJumpLocations, location.jump1Locations());
			}
			// the new locations have been sorted out, now remove the old ones
			jumplocations = Location.remove(Location.unique(newJumpLocations), visitedList);
		}
		
		return jumplocations;
	}
	
	@Override
	public int compareTo(Location o) {
		int colDiff = this.column - o.column;
		if(colDiff==0) {
			return (this.row - o.row);
		} 
		return colDiff;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}
	
	public boolean isIn(Location[] locations) {
		for (Location location : locations) {
			if(this.equals(location)) return true;
		}
		return false;
	}
	
	public boolean isIn(List<Location> locations) {
		for (Location location : locations) {
			if(this.equals(location)) return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return String.format("%02d%02d", this.column, this.row);
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + row;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (column != other.column)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

	public static void main(String[] args) {
		Location loc1 = new Location(4,4);
		System.out.println("For location (4,4) neighbours");
		for (Location location : loc1.jump1Locations()) {
			System.out.println(location);
		}
		
		loc1 = new Location(5,5);
		System.out.println("For location (5,5) neighbours");
		for (Location location : loc1.jump1Locations()) {
			System.out.println(location);
		}
		
		loc1 = new Location(1,1);
		System.out.println("For location (1,1) neighbours");
		for (Location location : loc1.jump1Locations()) {
			System.out.println(location);
		}
		
		loc1 = new Location(8,10);
		System.out.println("For location (8,10) neighbours");
		for (Location location : loc1.jump1Locations()) {
			System.out.println(location);
		}
		
		loc1 = new Location(4,4);
		System.out.println("For location (4,4) jump 1");
		for (Location location : loc1.jumpLocations(1)) {
			System.out.println(location);
		}
		
		loc1 = new Location(4,4);
		System.out.println("For location (4,4) jump 2");
		for (Location location : loc1.jumpLocations(2)) {
			System.out.println(location);
		}
		
		loc1 = new Location(4,4);
		System.out.println("For location (4,4) jump 3");
		for (Location location : loc1.jumpLocations(3)) {
			System.out.println(location);
		}
		
		loc1 = new Location(4,4);
		System.out.println("For location (4,4) jump 4");
		for (Location location : loc1.jumpLocations(4)) {
			System.out.println(location);
		}
		
		loc1 = new Location(4,4);
		System.out.println("For location (4,4) jump 5");
		for (Location location : loc1.jumpLocations(5)) {
			System.out.println(location);
		}
	}
	
}
