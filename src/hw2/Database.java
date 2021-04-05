package hw2;

import java.util.Hashtable;

public class Database {
	private Hashtable<Integer, User> User_table;
	private Integer ID_now = null;
	private User User_now = null;
	Database(){
		User_table = new Hashtable<Integer, User>();
	}
	/*
	 * Add a new record to the records of specific user
	 * Time estimate: O(1)(containsKey, get, put, addRecord in User are all O(1))
	*/
	public void addRecord(Integer id, Integer date, Integer cost) {
		if(User_table.containsKey(id)) {
			User_table.get(id).addRecord(date, cost);
		}
		else {
			User new_user = new User(id);
			User_table.put(id, new_user);
			new_user.addRecord(date, cost);
		}
	}
	/*
	 * Record which user is using the system
	 * @param ID_now the ID of the user who has logged
	 * @param User_now the User object of the user
	 * @return true means the user is found, false means the user is not found
	*/
	public Boolean login(Integer id) {
		if(User_table.containsKey(id)) {
			ID_now = id;
			User_now = User_table.get(id);
			return true;
		}
		else return false;
	}
	/*
	 * Get the ID
	 * @return id if there is a user, 0 if there is not
	*/
	public Integer getID() {
		if(ID_now != null)
			return ID_now;
		else return 0;
	}
	/*
	 * call the functions of User
	 * Time estimate: equal to the functions of User
	 * @return the return of function in User if there is a User
	*/
	public Integer getTotalCost() {
		if(User_now != null)
			return User_now.getTotalCost();
		else return 0;
	}
	public Integer getDayCost(Integer date) {
		if(User_now != null)
			return User_now.getDayCost(date);
		else return 0;
	}
	public Double getMonthCost(Integer month) {
		if(User_now != null)
			return User_now.getMonthCost(month);
		else return 0.0;
	}
	public String getAllRecord() {
		if(User_now != null)
			return User_now.getAllRecord();
		else return "";
	}
}
