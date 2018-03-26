import java.util.concurrent.TimeUnit;

public abstract class Vehicle {
	
	private int time;
	private int price;
	
	public void makeVehicle() {
		System.out.println("I'm making a "+this.toString()+"...");
		try {
			TimeUnit.SECONDS.sleep(this.getTime());
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(this.toString()+" is done");
	}
	
	
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
