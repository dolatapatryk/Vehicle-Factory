import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Factory class
 * @author Patryk
 *
 */
public class Factory {
	private int value;
	private LinkedList<Vehicle> listOfVehicles;
	private static volatile int finishedThreads = 0;
	
	public Factory() {
		this.value = 0;
		this.listOfVehicles = new LinkedList<>();
	}
	
	public void loadOrderFromUser() {
		System.out.print("Your order: ");
		Scanner scanner = new Scanner(System.in);
		String temp = null;
		StringBuilder order = new StringBuilder();
		while(true) {
			temp=scanner.nextLine();
			if(temp.equals("</order>")) { //dopoki uzytkownik nie wprowadzi </order> to czekamy na jego input
				order.append(temp+"\n");
				break;
			}else {
				order.append(temp+"\n");
			}
		}
		
		try {
			saveAsXML(order.toString()); //przesylamy input uzytkownika do metody saveAsXML, zeby stworzyla z niego plik xml
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loadFromXML();
		
	}
	
	public void saveAsXML(String text) throws IOException {
		BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream("order.xml"));
		PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(output));
		printWriter.print(text);
		printWriter.close();
		output.close();
	}
	
	public void loadFromXML() { //parsujemy nasz plik xml zeby wyciagnac z niego typy pojazdow do zbudowania
		try {
			 Vehicle v = null;
	         File inputFile = new File("order.xml");
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse(inputFile);
	         doc.getDocumentElement().normalize();
	        
	         NodeList nList = doc.getElementsByTagName("item");
	         
	         for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);
	            
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               if((v = createVehicle(eElement.getAttribute("type"))) != null) { //przesylamy typ pojazdu do metody createVehicle()
	            	   listOfVehicles.add(v);
	               }
	            }
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }
	
	public void doOrder() {
		Thread[] tab = new Thread[listOfVehicles.size()]; //tablica watkow produkujacych pojazdy
		finishedThreads = 0;
		for(int i=0;i<listOfVehicles.size();i++) {
			tab[i] = new Thread((Runnable) listOfVehicles.get(i));
			tab[i].start();
		}
		while(true) {
			if(finishedThreads==tab.length) {	//czekamy az wszystkie watki sie zakoncza, zeby wypisac total value
				System.out.println("All vehicles are done");
				break;
			}
		}
		System.out.println("Total value: "+ calculateValue());
		listOfVehicles.clear();
		finishedThreads = 0;
		
	}
	
	public int calculateValue() {
		this.value = 0;
		for(int i=0;i<listOfVehicles.size();i++) {
			this.value += listOfVehicles.get(i).getPrice();
		}
		
		return this.value;
	}
	
	public Vehicle createVehicle(String type) { //w zaleznosci od typu wpisanego przez uzytkownika tworzymy konkretny rodzaj pojazdu
            if(type.equals("car")) {
         	   return new Car();
            }else if(type.equals("motorcycle")) {
         	   return new Motorcycle();
            }else if(type.equals("truck")) {
         	   return new Truck();
            }else {
         	   System.out.println("Invalid type of vehicle");
         	   return null;
            }   
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public LinkedList<Vehicle> getListOfVehicles() {
		return listOfVehicles;
	}

	public void setListOfVehicles(LinkedList<Vehicle> listOfVehicles) {
		this.listOfVehicles = listOfVehicles;
	}

	public static int getFinishedThreads() {
		return finishedThreads;
	}

	public static void setFinishedThreads(int finishedThreads) {
		Factory.finishedThreads = finishedThreads;
	}
	public static void addFinishedThreads() {
		Factory.finishedThreads++;
	}
}
