package detranet;

/*This class is about one of the Business's employees and more specifically the one who deals mostly with the transaction processing.
 * He/She can see the list of customers,delete an old customer or add a new one.
 * He/She can also see the goals that he/she must accomplish, the bonus that he/she deserves, 
 * the remaining days of leaves or ask for a new leave and the news of the bank */


import java.util.Scanner;
import java.util.InputMismatchException;

/*Business Service Manager*/
public class BusinessServiceManager extends Employee {
	
	/*Constructor of the class*/
	public BusinessServiceManager(String fullname, int idEmployee, String department, String email, double salary,
			String firstDate, int leaves, String username, String password, double overall) {
		super(fullname, idEmployee, department, email, salary, firstDate, leaves, username, password, overall);
		// TODO Auto-generated constructor stub
	}
    
	/* This method deletes an already existing customer based on the id 
	 * that the Business Service Manager gives
	 */
	public void removeCust(int id) {
		@SuppressWarnings("unlikely-arg-type")
		int index = Business.bSM.indexOf(id);
		if (index == -1) {
			System.out.println("The id you gave is not valid");
		}else {
			Business.bSM.remove(index);
			System.out.println("The customer was deleted succesfuly");
		}
	}

	/*This method displays the menu to the employee 
	 * who has 3 options to choose between*/
	public void employeeList() {
		int select=0;
		boolean value=true;
		Scanner sc= new Scanner(System.in);
		do {
			try {
			System.out.println("Menu"
				+ "\n1.Add customer"
				+ "\n2.Delete customer"
				+ "\n3.View list of customers");
	        select = sc.nextInt();

	        if (select>0 && select<3) {
			    value=false;
		    } else {
			    System.out.printf("You did't insert an integer between 1 and 3.Please try again");
			}
		}
	       catch (InputMismatchException inputmismatchexception) {
		      System.err.printf("%nException%n: %s%n" , inputmismatchexception);
		      sc.nextLine();
		      System.out.printf("You did't insert an integer between 1 and 3.Please try again");

	       }
		}while(value);

		switch (select) {
		case 1:
			System.out.println("Give your new customer's name");
			String name = sc.nextLine();
			System.out.println("Give the type of his/hers business");
			String type = sc.nextLine();
			System.out.println("Give your new customer's id");
			int businessId = sc.nextInt();
			System.out.println("Give the amount of his/hers depositions");
			double amount = sc.nextDouble();
			System.out.println("Give the number of his/hers loans");
			int nmbrLoans = sc.nextInt();
			Business newCust = new Business(name,type,businessId,amount,nmbrLoans);
			newCust.setName(name);
			newCust.setType(type);
			newCust.setAmount(amount);
			newCust.setNmbrLoans(nmbrLoans);
			newCust.addBusiness(1);
			System.out.println("Your new customer was added succesfully");

		case 2:
			System.out.println("Type the customer's id you would like to delete");
			int id2= sc.nextInt();
			removeCust(id2);

		case 3:
			for(int i=0; i<=Business.bSM.size(); i++)
				toString();
			}
		sc.close();
	}
	
	/*This method is inherited from Employee class and returns the bonus 
	 * that the manager has accomplished to take
	 */
	@Override
	public double computeBonus() {
		double rate = this.getOverall() ;
		return rate * 3;
	}
	
	/*This method shows all the functions that a Business Service Manager
	 * can have
	 */
	@Override
	public void getMenu() {
		boolean value=true;
		int select=0;
		Scanner sc=new Scanner(System.in);
		do {
			try {
				System.out.println("Menu"
						+ "\n1.Customer management"
						+ "\n2.Display of goals"
						+ "\n3.Bonus"
						+ "\n4.Permission for absence"
						+ "\n5.Display of bank's news"
						+ "\n6.Log Out");
				select=sc.nextInt();
				if (select>0 && select<6) {
					value=false;
				}else {
					System.out.printf("You did't insert an integer between 1 and 6.Please try again");
					}
				}
			catch (InputMismatchException inputmismatchexception) {
				System.err.printf("%nException%n: %s%n" , inputmismatchexception);
				sc.nextLine();
				System.out.printf("You did't insert an integer between 1 and 6.Please try again");
			}
		} while (value);
				switch (select) {
				case 1:
					employeeList();
					break;
				case 2:
					goals("Business Service Manager");
				case 3:
					computeBonus();
				case 4:
					leaves();
				case 5:
					Employee.getNews();
				case 6:
					Main.main(null);
		}
				sc.close();
	}

	}