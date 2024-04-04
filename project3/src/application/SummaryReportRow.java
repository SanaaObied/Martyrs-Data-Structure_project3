package application;

import java.util.Map;

import application.SortedCircularDoublyLinkedList.LocationNode;

public class SummaryReportRow {
	private String location;
	private String level_by_level = new String();
	private int heightAVlOne = 0;
	private int numbersOfMartyrs = 0;
	private int heightAVlTwo = 0;
	private String backward = new String();;
	private String maximumNumber = new String();;

	public SummaryReportRow(LocationNode current) {
		 if (current != null) {
		        this.numbersOfMartyrs =current.avlOne.countMartyrsInLocation(current.getLocation().getName()); //.getNumOfMartyrsInLocation(current.getLocation().getName());
		        //System.out.println( Main.locationsList.getNumOfMartyrsInLocation(current.getLocation().getName()));
		     this.location=current.getLocation().getName();
		       //current.avlTwo.inOrderTraversal();
		        
		      this.level_by_level = current.getAvlOne().traverseLevelOrder2(current.avlOne.root);//.traverseLevelOrder2(current.getLocation().getName());//traverseLevelOrder2(current.avlOne.root) : "";
		        this.heightAVlOne = current.avlOne.getHeight();//getHeightAVL1(current.getLocation().getName()) : 0;
		        this.heightAVlTwo = current.avlTwo.getHeight();
		        System.out.println(location + "is Height "+current.avlTwo.getHeight());
		        this.backward = current.avlTwo.traverseBackwardAndGetTable(current.getLocation().getName());
		        System.out.println(current.avlTwo.traverseBackwardAndGetTable(current.getLocation().getName()));
		        this.maximumNumber = current.avlTwo != null ? current.avlTwo.reportMaxMartyrsDate() : "";
		    } else {
		        this.numbersOfMartyrs = 0;
		        this.level_by_level = "";
		        this.heightAVlOne = 0;
		        this.heightAVlTwo = 0;
		        this.backward = "";
		        this.maximumNumber = "";
		    }

	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setHeightAVlTwo(int heightAVlTwo) {
		this.heightAVlTwo = heightAVlTwo;
	}

	public String getLevel_by_level() {
		return level_by_level;
	}

	public void setLevel_by_level(String level_by_level) {
		this.level_by_level = level_by_level;
	}

	public int getHeightAVlOne() {
		return heightAVlOne;
	}

	public void setHeightAVlOne(int heightAVlOne) {
		this.heightAVlOne = heightAVlOne;
	}

	public int getNumbersOfMartyrs() {
		return numbersOfMartyrs;
	}

	public void setNumbersOfMartyrs(int numbersOfMartyrs) {
		this.numbersOfMartyrs = numbersOfMartyrs;
	}

	public int getHeightAVlTwo() {
		return heightAVlTwo;
	}

	public String getBackward() {
		return backward;
	}

	public void setBackward(String backward) {
		this.backward = backward;
	}

	public String getMaximumNumber() {
		return maximumNumber;
	}

	public void setMaximumNumber(String maximumNumber) {
		this.maximumNumber = maximumNumber;
	}

}
	