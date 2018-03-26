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
//		System.out.println("Hello! Enter your order like this: \n<order>\r\n" + 
//				"<item type=”car”/>\n" + 
//				"<item type=”motorcycle”/>\r\n" + 
//				".................\n"+
//				"<item type=”truck”/>\r\n" + 
//				"</order>\r\n" + 
//				" ");
//		String order = factory.loadOrder();
//		try {
//			factory.saveAsXML(order);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		factory.loadFromXML();
		factory.doOrder();
		System.out.println("Total value: "+factory.calculateValue());
		

	}

}
