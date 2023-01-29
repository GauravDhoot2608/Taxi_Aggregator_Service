package taxi_service;

public class Edge {
	
	private final String source;
	private final String destination;
	private final int time;
	
	public Edge(String source , String destination , int time) {
		this.source = source;
		this.destination = destination;
		this.time = time;
	}
	
	
	public String getDestination() {
		return destination;
	}
	
	public String getSource() {
		return source;
	}
	
	public int getTime() {
		return time;
	}
	
	@Override
	public String toString() {
		return source + " " + destination;
	}

}
