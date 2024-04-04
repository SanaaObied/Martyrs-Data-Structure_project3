package application;


import java.time.LocalDate;
import java.time.Period;
import java.time.chrono.ChronoLocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import application.SortedCircularDoublyLinkedList.LocationNode;



public class Martyr implements Comparable<Martyr> {
	private String name;
	private int age;
	private LocalDate dateOfDeath;
	private char gender;
	String Location;
    static int martyrCount=0;
  
	//private Stack martyrStack;

	// Constructor to initialize a Martyr object
	public Martyr(String name, int age, LocalDate dateOfDeath, char gender,
			String location) {
		super();

		
		this.name = name;
		this.age = age;
		this.dateOfDeath = dateOfDeath;
		this.gender = gender;
		this.Location=location;
		martyrCount++;
		//martyrStack=Main.locationsList.getFirst().avlTwo.getRoot().data

	}
	
	
    public String getLocation() {
		return Location;
	}

    
    public boolean equals2(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Martyr other = (Martyr) obj;
        return Objects.equals(this.name, other.name);
    }
	public void setLocation(String location) {
		Location = location;
	}


	public int compareTo2(Martyr other) {
        return this.name.compareTo(other.name);
    }
	// Getter and setter methods
	 @Override
	    public int compareTo(Martyr other) {
	        return this.dateOfDeath.compareTo(other.dateOfDeath);
	    }

	public String getName() {
		return name;
	}

	

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public LocalDate getDateOfDeath() {
		return dateOfDeath;
	}
	 public String getDateOfDeath2() {
	        return dateOfDeath.toString();
	    }

	public void setDateOfDeath(LocalDate dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}
	
	@Override
	public String toString() {
		return name + "," + age + ","+Location+ "," + dateOfDeath + "," + gender;
				 
	}

	// Check if two Martyr objects are equal based on dateOfDeath
	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Martyr)) {
			return false;
		}
		Martyr otherMartyr = (Martyr) other;
		return this.dateOfDeath.equals(otherMartyr.dateOfDeath);
	} // Additional helper methods

	

	public boolean isBefore(LocalDate date) {
		return dateOfDeath.isBefore(date);
	}

	public boolean isAfter(LocalDate date) {
		return dateOfDeath.isAfter(date);
	}

	public boolean isOn(LocalDate date) {
		return dateOfDeath.isEqual(date);
	}

	public int getDaysSinceDeath() {
		return Period.between(dateOfDeath, LocalDate.now()).getDays();
	}

	public int getYearsSinceDeath() {
		return Period.between(dateOfDeath, LocalDate.now()).getYears();
	}


	 public int getMartyrCount() {
	        return martyrCount;
	    }


	
	
	
}
