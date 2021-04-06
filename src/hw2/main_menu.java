package hw2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class main_menu {
	static Database db;
	public static void main(String[] args) throws Exception {
		init_set();
		if(!login()) return;
		while(true) {
			if(!function()) return;
		}
	}
	/*
	 * initialize the dataset
	 * @param db database
	 * @throws NumberFormatException
	 * If input is not integer, throw NumberFormatException
	 * Time estimate: O(n)
	*/
	public static void init_set() throws Exception {
		db = new Database();
		String file = "input.txt";
		BufferedReader input = new BufferedReader(new FileReader(file));
		String out;
		while((out = input.readLine())!= null) {
			String[] recordlist = out.split(" ");
			Integer id, date, cost;
			try {
			    id = Integer.parseInt(recordlist[0]);
			    date = Integer.parseInt(recordlist[1]);
			    cost = Integer.parseInt(recordlist[2]);
			    db.addRecord(id, date, cost); 
			} catch (NumberFormatException e) {
			  
			}
			 
		}
		input.close();
	}
	/*
	 * find the id that user enters
	 * @return true if the id is found, false if user wants to exit
	 * @param db database
	 * @throws NumberFormatException
	 * If input is not integer, throw NumberFormatException
	 * Time estimate: O(n)
	*/
	public static Boolean login() {
		System.out.println("請輸入 ID或 Q (結束使用)");
		Scanner scanner = new Scanner(System.in);
		String input;
		while((input = scanner.nextLine())!=null) {
			Integer id = null;
			try{
				id = Integer.parseInt(input);
			}catch(NumberFormatException e){
				char x = input.charAt(0);
				if(x == 'Q' || x == 'q') {
					System.out.println("感謝您的使用");
					scanner.close();
					return false;
				}
				else {
					System.out.println("輸入錯誤!\n請輸入 ID或 Q (結束使用)");
					continue;
				}
			}
			if(db.login(id)) {
				System.out.println("Welcome " + db.getID().toString() + "");
				break;
			}
			else {
				System.out.println("查無此ID!\n請輸入 ID或 Q (結束使用)");
			}
		}
		return true;
	}
	/* judge which function that user want to use and call it
	 * @return true means not to exit, false means exit
	*/
	public static Boolean function() {
		System.out.println(
				"請輸入指令：\r\n" + 
				"1) A 顯示 總支出\r\n" + 
				"2) B 顯示 全部消費紀錄\r\n" + 
				"3) C 顯示特定日期消費金額\r\n" + 
				"4) D 顯示特定月份日平均消費金額\r\n" + 
				"5) Q 離開系統");
		Scanner scanner = new Scanner(System.in);
		String command = scanner.nextLine();
		char x = command.charAt(0);
		switch(x) {
		case 'A':
		case 'a':
			Total();
			break;
		case 'B':
		case 'b':
			ListAll();
			break;
		case 'C':
		case 'c':
			DateCost(scanner);
			break;
		case 'D':
		case 'd':
			MonthCost(scanner);
			break;
		case 'Q':
		case 'q':
			System.out.println("感謝您的使用");
			scanner.close();
			return false;
		default:
			System.out.println("無此指令，請重新輸入");
		}
		return true;
	}
	/*
	 * call the functions of Database by the input by user and print the result out
	 * Time estimate: equal to the functions of User
	*/
	private static void MonthCost(Scanner scanner) {
		System.out.println("請輸入欲查詢月份:\n");
		//Scanner scanner = new Scanner(System.in);
		Integer month = scanner.nextInt();
		Double cost = db.getMonthCost(month);
		System.out.println("您在" + month.toString() + "的月平均消費金額為" + cost.toString() + "\n");
		
	}
	private static void DateCost(Scanner scanner) {
		System.out.println("請輸入欲查詢日期:\n");
		//Scanner scanner = new Scanner(System.in);
		Integer date = scanner.nextInt();
		Integer cost = db.getDayCost(date);
		System.out.println("您在" + date.toString() + "的總消費金額為" + cost.toString() + "\n");
	}
	private static void ListAll() {
		System.out.println("消費日期   消費金額");
		System.out.println(db.getAllRecord());
	}
	private static void Total() {
		Integer cost = db.getTotalCost();
		System.out.println("總消費金額為" + cost.toString() + "\n");
	}
}
