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
		System.out.println("�п�J ID�� Q (�����ϥ�)");
		Scanner scanner = new Scanner(System.in);
		String input;
		while((input = scanner.nextLine())!=null) {
			Integer id = null;
			try{
				id = Integer.parseInt(input);
			}catch(NumberFormatException e){
				char x = input.charAt(0);
				if(x == 'Q' || x == 'q') {
					System.out.println("�P�±z���ϥ�");
					scanner.close();
					return false;
				}
				else {
					System.out.println("��J���~!\n�п�J ID�� Q (�����ϥ�)");
					continue;
				}
			}
			if(db.login(id)) {
				System.out.println("Welcome " + db.getID().toString() + "");
				break;
			}
			else {
				System.out.println("�d�L��ID!\n�п�J ID�� Q (�����ϥ�)");
			}
		}
		return true;
	}
	/* judge which function that user want to use and call it
	 * @return true means not to exit, false means exit
	*/
	public static Boolean function() {
		System.out.println(
				"�п�J���O�G\r\n" + 
				"1) A ��� �`��X\r\n" + 
				"2) B ��� �������O����\r\n" + 
				"3) C ��ܯS�w������O���B\r\n" + 
				"4) D ��ܯS�w����饭�����O���B\r\n" + 
				"5) Q ���}�t��");
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
			System.out.println("�P�±z���ϥ�");
			scanner.close();
			return false;
		default:
			System.out.println("�L�����O�A�Э��s��J");
		}
		return true;
	}
	/*
	 * call the functions of Database by the input by user and print the result out
	 * Time estimate: equal to the functions of User
	*/
	private static void MonthCost(Scanner scanner) {
		System.out.println("�п�J���d�ߤ��:\n");
		//Scanner scanner = new Scanner(System.in);
		Integer month = scanner.nextInt();
		Double cost = db.getMonthCost(month);
		System.out.println("�z�b" + month.toString() + "���륭�����O���B��" + cost.toString() + "\n");
		
	}
	private static void DateCost(Scanner scanner) {
		System.out.println("�п�J���d�ߤ��:\n");
		//Scanner scanner = new Scanner(System.in);
		Integer date = scanner.nextInt();
		Integer cost = db.getDayCost(date);
		System.out.println("�z�b" + date.toString() + "���`���O���B��" + cost.toString() + "\n");
	}
	private static void ListAll() {
		System.out.println("���O���   ���O���B");
		System.out.println(db.getAllRecord());
	}
	private static void Total() {
		Integer cost = db.getTotalCost();
		System.out.println("�`���O���B��" + cost.toString() + "\n");
	}
}
