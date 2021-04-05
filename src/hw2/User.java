package hw2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public class User {
	/*private int id;
	private int total_cost;
	private Hashtable<Integer, Integer> day_cost;
	private Hashtable<Integer, Integer> month_cost;
	private String records;
	User(int id){
		this.id = id;
		day_cost = new Hashtable<Integer, Integer>();
		month_cost = new Hashtable<Integer, Integer>();
		total_cost = 0;
		records = new String();
	}
	public void addRecord(Integer date, Integer cost){
		Integer month = date / 100;
		if(day_cost.containsKey(date)) {
			int new_cost = cost + day_cost.get(date);
			day_cost.put(date, new_cost);
		}
		else day_cost.put(date, cost);
		if(month_cost.containsKey(month)) {
			int new_cost = cost + month_cost.get(month);
			month_cost.put(month, new_cost);
		}
		else month_cost.put(month, cost);
		total_cost += cost;
		records += date.toString() + " " + cost.toString() + "\n";
	}
	public Integer getDayCost(Integer date) {
		if(day_cost.containsKey(date)) return day_cost.get(date);
		else return 0;
	}
	public Double getMonthCost(Integer month) {
		if(month_cost.containsKey(month)) 
			{
				Integer total = month_cost.get(month);
				switch(month % 100) {
				case 1:
				case 3:
				case 5:
				case 7:
				case 8:
				case 10:
				case 12:
					return (double) (total) / 31;
				case 4:
				case 6:
				case 9:
				case 11:
					return (double) (total) / 30;
				case 2:
					if((month / 100) % 4 == 0) return (double) (total) / 29;
					else return (double) (total) / 28;
				default:
					return 0.0;
				}
			}
		else return 0.0;
	}
	public Integer getTotalCost() {return total_cost;}
	public String getAllRecord() {return records;}*/
	
	private List<List<Integer>> records;
	private Integer id;
	User(Integer id){
		this.id = id;
		records = new ArrayList<List<Integer>>();
	}
	/*
	 * Add a new record to the records of this user
	 * Time estimate: O(1)
	*/
	public void addRecord(Integer date, Integer cost) {
		records.add(Arrays.asList(date, cost));
	}
	/*
	 * Get the total cost of specific date
	 * @return the total cost of specific date
	 * @param cost compute the total cost
	 * Time estimate: O(n) (n=records of this user)
	*/
	public Integer getDayCost(Integer date) {
		Integer cost = 0;
		for(List<Integer> record: records) {
			if(date.equals(record.get(0)))
				cost += record.get(1);
		}
		return cost;
	}
	/*
	 * Get the average cost of specific month
	 * @return the average cost of specific month
	 * @param cost compute the total cost
	 * Time estimate: O(n) (n=records of this user)
	*/
	public Double getMonthCost(Integer month) {
		Double cost = 0.0;
		for(List<Integer> record: records) {
			if(month.equals((Integer) (record.get(0) / 100)))
				cost += record.get(1);
		}
		switch(month % 100) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			return (double) (cost) / 31;
		case 4:
		case 6:
		case 9:
		case 11:
			return (double) (cost) / 30;
		case 2:
			if((month / 100) % 4 == 0 && ((month / 100) % 100 != 0 || (month / 100) % 400 == 0)) return (double) (cost) / 29;
			else return (double) (cost) / 28;
		default:
			return 0.0;
		}
	}
	/*
	 * Get the total cost of all records
	 * @return the total cost of all records
	 * @param cost compute the total cost
	 * Time estimate: O(n) (n=records of this user)
	*/
	public Integer getTotalCost() {
		Integer cost = 0;
		for(List<Integer> record: records) {
			cost += record.get(1);
		}
		return cost;
	}
	/*
	 * Get all records 
	 * @return all records in String
	 * @param record_string the string of all records 
	 * Time estimate: O(n) (n=records of this user)
	*/
	public String getAllRecord() {
		String record_string = new String();
		for(List<Integer> record: records) {
			record_string += record.get(0).toString() + " " + record.get(1).toString() + "\n";
		}
		return record_string;
	}
}
