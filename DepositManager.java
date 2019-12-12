package detranet;

import java.util.InputMismatchException;
import java.util.Scanner;
/* Class for deposit Manager */
public class DepositManager extends Employee {
	
	private static String pCMGoals;
	private static String tellerGoals;
	private static String pCMVipGoals;
	private static String pCMDelaysGoals;
	
	public DepositManager(String fullname, int idEmployee, String department, String email, double salary,
			String firstDate, int leaves, String username, String password, double overall) {
		super(fullname, idEmployee, department, email, salary, firstDate, leaves, username, password, overall);
	}
	
	/* Display the goals for the deposit department set by the manager */
	@Override
	public void goals() {
		if (Manager.getDepositGoals() == null) {
			System.out.printf("No available goals!");
		}else {
			System.out.printf("Department goals:\n" + Manager.getDepositGoals());
		}
	}
	
	/* Method for goals assignment for employees of deposit department */
	public void setGoals() {
		Scanner sc = new Scanner (System.in);
		boolean endOfProcedure = true;
		do {
			boolean flag = true;
			int select = 0;
			do {
				try {
					System.out.printf("\nGoals sharing for depositors."
							+ "\n1.Private customer manager."
							+ "\n2.Teller"
							+ "\n3.Private customer manager vip"
							+ "\n4.Private customer manager delays"
							+ "\n5.Εnd of procedure\n");
					select = sc.nextInt();
					if (select > 0 && select < 6) {
						flag = false;
					}else {
						System.out.printf("Please insert an integer between 1-5.Try again...");
						}
					}
				catch (InputMismatchException inputmismatchexception) {
					System.err.printf("%nException%n: %s%n", inputmismatchexception);
					sc.nextLine();
					System.out.printf("Please insert an integer between 1-5.Try again...");
				}
			}while (flag);
			switch (select) {
			case 1:
				System.out.printf("Private service manager goals:\n");
				pCMGoals = sc.next();
				break;
			case 2:
				System.out.printf("Teller goals:\n");
				tellerGoals = sc.next();
				break;
			case 3:
				System.out.printf("Private customer manager Vip goals:\n");
				pCMVipGoals = sc.next();
				break;
			case 4:
				System.out.printf("Private customer manager delays goals:\n");
				pCMDelaysGoals = sc.next();
				break;
			case 5:
				endOfProcedure = false;
				break;
			}
		} while (endOfProcedure);
	}
	
	/* Methods return goals for employees of deposit department */
	public static String getpCMGoals() {
		return pCMGoals;
	}
	public static String gettellerGoals() {
		return tellerGoals;
	}
	public static String getpCMGoldGoals() {
		return pCMVipGoals;
	}
	public static String getpCMDelaysGoals() {
		return pCMDelaysGoals;
	}
	
	
	/* Method compute bonus for deposit manager 
	 * based on the average overall of the employees of the deposit department */
	@Override
	public double computeBonus() {
		int nmbrOfEmployees = 0;
		double sumOverall = 0;
		double rate = 0;
		double bonus = 0;
		/* Find deposit employees and sum overalls */
		for(int i=0; i < employees.size(); i++) {
			Employee emp = employees.get(i);
			if (emp.getDepartment() == "Private Customer Manager" 
					|| emp.getDepartment() == "Teller" 
					|| emp.getDepartment() == "Private Customer Manager Vip" 
					|| emp.getDepartment() == "Private Customer Manager Delays") {
				int idCurrEmployee = emp.getIdEmployee();
				nmbrOfEmployees += 1;
				for (int j = 0; j < idEmployees.size(); j++ ) {			/* The positions of employees are not  */
					if (idCurrEmployee == idEmployees.get(j)) {			/* the same in lists employees and idEmployees */
						sumOverall += overalls.get(j);
						break;
					}
				}
			}
		}
		rate = sumOverall / nmbrOfEmployees;
		if(rate > 75) {
			bonus = rate * 3;
		}	
		return bonus;
	}
	
	 /* Add rate of deposit manager */
	public void evaluationDepositManager() {
		double rate = (computeBonus()/3);
		for(int j = 0; j < employees.size(); j++) {
			Employee empLM = employees.get(j);
			if(empLM.getDepartment() == "Deposit Manager") {
				empLM.setOverall(rate);
				idEmployees.add(empLM.getIdEmployee());
				overalls.add(rate);
				break;
			}
		}	
	}
	
	public double finalSalary() {
		return (this.getSalary() + computeBonus());
	}
	
	/* Display the menu for deposit manager */
	@Override
	public void getMenu() {
		Scanner sc = new Scanner (System.in);
		for(;;) {
			int select = 0;
			boolean flag = true;
			do {
				try {
					System.out.printf("\nWelcome to the Deposit Manager menu!"
							+ "\n1.Display department goals"
							+ "\n2.Goals sharing"
							+ "\n3.Leaves"
							+ "\n4.Compute BONUS"
							+ "\n5.News"
							+ "\n6.Log Out\n");
					select = sc.nextInt();
					if (select > 0 && select < 7) {
						flag = false;
					}else {
						System.out.printf("Please insert an integer between 1-6.Try again...");
					}
				}	
				catch (InputMismatchException inputmismatchexception) {
					System.err.printf("%nException%n: %s%n", inputmismatchexception);
					sc.nextLine();
					System.out.printf("Please insert an integer between 1-6.Try again...");
				}
			}while(flag);
			switch (select) {
			case 1:
				goals();
				break;
			case 2:
				setGoals();
				break;
			case 3:
				leaves();
				break;
			case 4:
				evaluationDepositManager();
				System.out.println("Goals achievment rate for deposit manager: " + (computeBonus()/3) + "%" + 
						"\nBonus: " + computeBonus() + "$" + 
						"\nFinal Salary: " + finalSalary() + "$");
				break;
			case 5:
				Employee.getNews();
				break;
			case 6:
				Main.main(null);
				break;
			}
		}	
	}
}
