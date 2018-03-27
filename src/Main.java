import java.awt.Choice;
import java.io.IOException;
import java.util.Scanner;

/**
 * Main class
 * @author Patryk
 *
 */
public class Main {
	
	public static void main(String[] args) {
		Factory factory = new Factory();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Hello! Enter your order like this: \n<order>\r\n" + 
				"<item type=”car”/>\n" + 
				"<item type=”motorcycle”/>\r\n" + 
				".................\n"+
				"<item type=”truck”/>\r\n" + 
				"</order>\r\n" + 
				" ");
		while(true) {
		System.out.println("");
		System.out.println("Type what you want to do: \n type 'order' to make order \n type 'exit' to exit");
		System.out.print("Your choice: ");
		String choice = scanner.nextLine();
		if(choice.equals("exit")) {
			break;
		}else {
		factory.loadOrderFromUser();
		factory.loadFromXML();
		factory.doOrder();
		}
		}
		scanner.close();
		System.out.println("Goodbye!");
		

	}

}
