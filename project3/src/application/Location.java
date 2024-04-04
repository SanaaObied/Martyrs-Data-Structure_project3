package application;

public class Location implements Comparable<Location> {
	private String name; // Name of the location

	public Location(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int compareTo(Location other) {
		return this.name.compareTo(other.getName());
	}

}
