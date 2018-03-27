/**
 * Motorcycle class
 * @author Patryk
 *
 */
public class Motorcycle extends Vehicle implements Runnable {
	
	
	public Motorcycle() {
		this.setTime(5);
		this.setPrice(600);
	}


	@Override
	public void run() {
		makeVehicle();
	}
	
	@Override
	public String toString() {
		return "Motorcycle";
	}

}
