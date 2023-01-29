package taxi_service;

public class Taxi{
	
	private final String name;
	private String taxiPosition;
	private int availableTime;
	
	public Taxi(String name , String taxiPosition , int currentTime) {
		this.name = name;
		this.taxiPosition = taxiPosition;
		this.availableTime = currentTime;
	}
	
	public boolean isTaxiAvailable(int currentTime){
		return currentTime >= availableTime;
	}
	
	public void setTaxiPosition(String taxiPosition) {
		this.taxiPosition = taxiPosition;
	}

	public void setAvailableTime(int availableTime) {
		this.availableTime = availableTime;
	}
	
	public int getAvailableTime() {
		return availableTime;
	}
	
	public String getTaxiPosition() {
		return taxiPosition;
	}
	
	public String getName() {
		return name;
	}
	
	
}

