import java.util.concurrent.TimeUnit;

public class Truck extends Vehicle implements Runnable {

	
	public Truck() {
		this.setTime(15);
		this.setPrice(2000);
	}


	@Override
	public void run() {
		makeVehicle();
	}
	
	@Override
	public String toString() {
		return "Truck";
	}

}
