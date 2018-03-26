import java.util.concurrent.TimeUnit;

public class Car extends Vehicle implements Runnable{
	
	
	public Car() {
		this.setTime(10);
		this.setPrice(1000);
	}
	

	@Override
	public void run() {
		makeVehicle();
	}

	@Override
	public String toString() {
		return "Car";
	}
	
	

}
