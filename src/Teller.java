package gr.aueb.dmst.DETranet;
import java.util.Date;
import java.util.InputMismatchException;

/**
 * This class is for the Teller.The Teller is responsible for the cash transactions 
 * of the private customers and also the business customers.
 * It includes a Menu that Manager chooses what actions want to make and
 *  by this way Manager can control his customers,his leaves and his bonuses.
 */

public class Teller extends Employee {

  /**
 * This is the constructor of the class.
 *
 */
  public Teller(String fullname, String department,
      String email, double salary, Date firstDate,
      int leaves, String password, double overall) {
    super(fullname, department, email, salary, firstDate, leaves, 
          password, overall);
  }

  /**
 * This method is responsible for choosing if the customer is private or business.
 */
  public void chooseCategory() {
    boolean flag = true;
   
    int option = 0;
    do {
      try {
        System.out.println("Choose the category :"
            + "\n1.Business"
            + "\n2.Private");
        option = Main.sc.nextInt();
        if (option == 1 || option == 2) {
          flag = false;
        } else {
          System.out.println("Please enter 1 for Business or 2 for Private.Try again...");
        } 
      } catch (InputMismatchException inputmismatchexception) {
        System.err.printf("%nException%n: %s%n", inputmismatchexception);
        Main.sc.nextLine();
        System.out.println("Please enter 1 for Business or 2 for Private.Try again...");
      }
    } while (flag);
    if (option == 1) {
      busMenu();
    }  else { 
      custMenu();
    }
  }

  /**
 * This method is responsible for showing the menu for the Business Customer's management
 * and it has 3 options: addition, delete and display of customers.
 */
  public void busMenu() {
    boolean flag =  true;
    
    int option = 0;
    do {
      try {
        System.out.println("Menu"
            + "\n1.Addition of Business"
            + "\n2.Delete of Business"
            + "\n3.Display of Business");
        option = Main.sc.nextInt();
        if (option > 0 && option <= 3) {
          flag = false;
        } else {
          System.out.printf("Please insert an integer between 1-3.Try again...");
        }
      } catch (InputMismatchException inputmismatchexception) {
        System.err.printf("%nException%n: %s%n", inputmismatchexception);
        Main.sc.nextLine();
        System.out.printf("Please insert an integer between 1-3.Try again...");
      }
    } while (flag);
    switch (option) {
      case 1:
        try {
          System.out.println("Give business's name");
          String name =  Main.sc.next();
          System.out.println("Give business's amount of deposit");
          double amount = Main.sc.nextDouble();
          System.out.println("Give business's type");
          String type = Main.sc.next();
          System.out.println("Give business's number of loans");
          int loans = Main.sc.nextInt();
          Business b = new Business(name, type, amount, loans);
          b.setName(name);
          b.setAmount(amount);
          b.setType(type);
          b.setNmbrLoans(loans);
          b.addBusiness(5);
          Database.createBusinessCust(name, type, b.getIdBusiness(), amount, loans, this.getIdEmployee());
        } catch (InputMismatchException inputmismatchexception) {
          System.err.printf("%nException%n: %s%n",inputmismatchexception);
          Main.sc.nextLine();
          System.out.printf("Please insert the right type of data.Try again...");
        }
        break;
      case 2:
        System.out.println("Give business's id that you want to delete");
        int delid = Main.sc.nextInt();
        removeBusiness(delid);
        break;
      case 3:
        int size = Business.tellerBusiness.size();
        if (size == 0) {
          System.out.println("Customers list: empty ");
        }
        for (int i = 0; i < size; i++) {
          Business b = Business.tellerBusiness.get(i);
          System.out.println(b.toString());
        }
        break;
      default:
        break;
    }
  }

  /**
 * This method removes the business clients from the list.
 */
  public void removeBusiness(int id) {
    boolean b = false;
    if (id < 0) {
      System.out.println("The id you gave is not valid");
    }
    for (int i = 0; i < Business.tellerBusiness.size(); i++) {
      int k = Business.tellerBusiness.get(i).getIdBusiness();
      if (k == id) {
        Business.tellerBusiness.remove(Business.tellerBusiness.get(i));
        Database.deleteBusinessCust(id);
        System.out.println("The customer was deleted succesfuly");
        b = true;
        break;
      }
    }
    if (b == false) {
      System.out.println("there is not such id");
    }
  }

  /**
 * This method removes the private clients from the list.
 */
  public void removePrivate(int id) {
    boolean b = false;
    if (id < 0) {
      System.out.println("The id you gave is not valid");
    }
    for (int i = 0; i < Private.tellerPrivate.size(); i++) {
      int k = Private.tellerPrivate.get(i).getIdPrivate();
      if (k == id) {
        Private.tellerPrivate.remove(Private.tellerPrivate.get(i));
        Database.deletePrivateCust(id);
        System.out.println("The customer was deleted succesfuly");
        b = true;
        break;
      }
    }
    if (b == false) {
      System.out.println("there is not such id");
    }
  }

  /* 
 * This method is inherited by the class Employee and it returns the bonus that the Teller will gain
 */

  @Override
  public double computeBonus() {
    double rate = this.getOverall();
    return rate * 3;
  }

  /**
 * This method is responsible for showing the menu for the Customer's management 
 * and it has 3 options: addition, delete and display of customers.
 */
  
  public void custMenu() {
    boolean flag = true;
   
    int option = 0;
    do {
      try {
        System.out.println("Menu"
            + "\n1.Addition of Customer"
            + "\n2.Delete of Customer"
            + "\n3.Display of Customer");
        option = Main.sc.nextInt();
        if (option > 0 && option <= 3) {
          flag = false;
        } else {
          System.out.printf("Please insert an integer between 1-3.Try again...");
        }
      } catch (InputMismatchException inputmismatchexception) {
        System.err.printf("%nException%n: %s%n",inputmismatchexception);
        Main.sc.nextLine();
        System.out.printf("Please insert an integer between 1-3.Try again...");
      }
    } while (flag);
    switch (option) {
      case 1:
        try {
          System.out.println("Give customer's name");
          String name = Main.sc.next();
          System.out.println("Give customer's amount of deposit");
          double amount = Main.sc.nextDouble();
          System.out.println("Give customer's number of cards");
          int cards = Main.sc.nextInt();
          System.out.println("Give customer's number of loans");
          int loans = Main.sc.nextInt();
          Private p = new Private(name,amount,cards,loans);
          p.setName(name);
          p.setAmount(amount);
          p.setCards(cards);
          p.setNmbrLoans(loans);
          p.addPrivate(1);
          Database.createPrivateCust(name, p.getIdPrivate(), amount, cards, loans, this.getIdEmployee());
        } catch (InputMismatchException inputmismatchexception) {
          System.err.printf("%nException%n: %s%n",inputmismatchexception);
          Main.sc.nextLine();
          System.out.printf("Please insert the right type of data.Try again...");
        }
        break;
      case 2:
        System.out.println("Give customer's id that you want to delete");
        int delid = Main.sc.nextInt();
        removePrivate(delid);
        Database.deletePrivateCust(delid);
        break;
      case 3:
    	  int size = Private.tellerPrivate.size();
          if (size == 0) {
            System.out.println("Customers list: empty ");
          }
          for (int i = 0; i < size; i++) {
            Private p = Private.tellerPrivate.get(i);
            System.out.println(p.toString());
          }
        break;
      default:
        break;
    }
  }

  /**
 *This method includes the main menu which shows all the functions that Teller2 can have.
 */

  @Override
  public void getMenu() {
    for (;;) {
      boolean flag = true;
      int option = 0;
      do {
        try {
          System.out.println("Menu"
              + "\n1.Customers' management"
              + "\n2.Goals'display"
              + "\n3.Bonus"
              + "\n4.Leaves"
              + "\n5.Display of the bank's news"
              + "\n6.Log Out");
          option = Main.sc.nextInt();
          if (option > 0 && option <= 6) {
            flag = false;
          } else {
            System.out.printf("Please insert an integer between 1-6.Try again...");
          }
        } catch (InputMismatchException inputmismatchexception) {
          System.err.printf("%nException%n: %s%n",inputmismatchexception);
          Main.sc.nextLine();
          System.out.printf("Please insert an integer between 1-6.Try again...");
        }
      } while (flag);
      switch (option) {
        case 1:
          chooseCategory();
          break;
        case 2:
          goals("Teller goals");
          break;
        case 3:
          System.out.println(computeBonus());
          break;
        case 4:
          leaves();
          break;
        case 5:
          PrivateCustomerManager.getNews();
          break;
        case 6:
          Main.main(null);
          break;
        default:
          break;
      }
    }
  }
}