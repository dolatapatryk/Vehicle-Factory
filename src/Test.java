import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;


class Test {

	
	
	@org.junit.jupiter.api.Test
	public void TestTotalValue() { //testujemy koszt wytworzenia pojazdow z przykladu w poleceniu zadania
		Factory factory = new Factory();
		String userInput = "<order>\n"+
						"<item type=\"car\"/>\n"+
						"<item type=\"car\"/>\n"+
						"<item type=\"truck\"/>\n"+
						"</order>";
		try {
			factory.saveAsXML(userInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		factory.loadFromXML();
		factory.doOrder();
		assertEquals(4000,factory.getValue());
	}
	
	@org.junit.jupiter.api.Test
	public void TestCar() { //testujemy czy jesli uzytkownik wprowadzi <item type="car"/> to czy stworzymy pojazd klasy Car
		Factory factory = new Factory();
		String userInput = "<order>\n"+
				"<item type=\"car\"/>\n"+
				"</order>";
		try {
			factory.saveAsXML(userInput);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		factory.loadFromXML();
		assertEquals(true, factory.getListOfVehicles().get(0) instanceof Car);
	}
	
	@org.junit.jupiter.api.Test
	public void TestMotorcycle() { //testujemy czy jesli uzytkownik wprowadzi <item type="motorcycle"/> to czy stworzymy pojazd klasy Motorcycle
		Factory factory = new Factory();
		String userInput = "<order>\n"+
				"<item type=\"motorcycle\"/>\n"+
				"</order>";
		try {
			factory.saveAsXML(userInput);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		factory.loadFromXML();
		assertEquals(true, factory.getListOfVehicles().get(0) instanceof Motorcycle);
	}
	
	@org.junit.jupiter.api.Test
	public void TestTruck() { //testujemy czy jesli uzytkownik wprowadzi <item type="truck"/> to czy stworzymy pojazd klasy Truck
		Factory factory = new Factory();
		String userInput = "<order>\n"+
				"<item type=\"truck\"/>\n"+
				"</order>";
		try {
			factory.saveAsXML(userInput);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		factory.loadFromXML();
		assertEquals(true, factory.getListOfVehicles().get(0) instanceof Truck);
	}

}

