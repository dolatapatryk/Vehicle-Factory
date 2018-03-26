import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

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
	
	public Factory() {
		this.value = 0;
		this.listOfVehicles = new LinkedList<>();
	}
	
	public String loadOrderFromUser() {
		System.out.print("Your order: ");
		Scanner scanner = new Scanner(System.in);
		String temp = null;
		StringBuilder order = new StringBuilder();
		while(true) {
			temp=scanner.nextLine();
			if(temp.equals("</order>")) {
				order.append(temp+"\n");
				break;
			}else {
				order.append(temp+"\n");
			}
		}
		scanner.close();
		
		return order.toString();
	}
	
	public void saveAsXML(String text) throws IOException {
		BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream("order.xml"));
		PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(output));
		printWriter.print(text);
		printWriter.close();
		output.close();
	}
	
	public void loadFromXML() {
		try {
	         File inputFile = new File("order.xml");
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse(inputFile);
	         doc.getDocumentElement().normalize();
	         System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	         NodeList nList = doc.getElementsByTagName("item");
	         System.out.println("----------------------------");
	         
	         for (int temp = 0; temp < nList.getLength(); temp++) {
	            Node nNode = nList.item(temp);
	            System.out.println("\nCurrent Element :" + nNode.getNodeName());
	            
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               System.out.println("type of vehicle : " 
	                  + eElement.getAttribute("type"));
	               if(eElement.getAttribute("type").equals("car")) {
	            	   listOfVehicles.add(new Car());
	               }else if(eElement.getAttribute("type").equals("motorcycle")) {
	            	   listOfVehicles.add(new Motorcycle());
	               }else if(eElement.getAttribute("type").equals("truck")) {
	            	   listOfVehicles.add(new Truck());
	               }
	            }
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }
	
	public void doOrder() {
		Thread[] tab = new Thread[listOfVehicles.size()];
		for(int i=0;i<listOfVehicles.size();i++) {
			tab[i] = new Thread((Runnable) listOfVehicles.get(i));
			tab[i].start();
		}
	}
	
	public int calculateValue() {
		int temp = 0;
		while(temp<)
		this.value = 0;
		for(int i=0;i<listOfVehicles.size();i++) {
			this.value += listOfVehicles.get(i).getPrice();
		}
		
		return this.value;
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
}
