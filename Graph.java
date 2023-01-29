package taxi_service;

import java.util.*;


public class Graph{
	
	private List<String> vertexes;
	private Map<String , Boolean> isVertex;
	private List<Edge> edges;
	private List<Taxi> taxis;
	
	public Graph(){
        vertexes = new ArrayList<>();
		edges = new ArrayList<>();
		taxis = new ArrayList<>();
		isVertex = new HashMap<>();
	}
    
    public List<Edge> getEdges() {
		return edges;
	}
    
    public List<String> getVertexes() {
		return vertexes;
	}
    
    public List<Taxi> getTaxis() {
		return taxis;
	}
    
    private void addVertex(String name){
    	vertexes.add(name);
    	isVertex.put(name, true);
    }
    
    private boolean isVertexPresent(String nodeName){
    	if(isVertex.containsKey(nodeName)) {
    		return true;
    	}
    	return false;
    }
    
    public void addEdge(String source , String destination , int time) {
		if(!isVertexPresent(source)){
			addVertex(source);
		}
		if(!isVertexPresent(destination)) {
			addVertex(destination);
		}
		
		Edge e1 = new Edge(source, destination, time);
		edges.add(e1);
		
		Edge e2 = new Edge(destination, source, time);
		edges.add(e2);
	}
    
    public void addTaxi(String name , String taxiPosition){
    	if(isVertex.containsKey(taxiPosition)){
    		Taxi taxi = new Taxi(name , taxiPosition , 0);
    		taxis.add(taxi);
    		return;
    	}
    	throw new RuntimeException();
    }
    
    public void customerRequest(String source , String destination , int time){
    	
    	// Minimum Time to reach Source to Destination
    	Launch l = new Launch(edges);
    	l.execute(source);
    	int destinationTime = l.getShortestDistance(destination);
    	ArrayList<String> path = l.getPath(destination);
    	
    	
    	// Minimum Time required from Taxi position to Source 
    	System.out.println("Available taxis:");
    	ArrayList<Taxi> taxiAvail = availableTaxi(time);
    	
    	int minTime = Integer.MAX_VALUE , minIndex = -1;
    	
    	for(int i=0 ; i<taxiAvail.size() ; i++) {	
    		Taxi taxi = taxiAvail.get(i);
    		int taxiTime = printTaxiPath(taxi,source);
    		if(taxiTime < minTime) {
    			minTime = taxiTime;
    			minIndex = i;
    		}			
    	}
    	if(minIndex == -1) {
    		System.out.println("No Taxi is Available !! Right Now");
    		return;
    	}
    	
    	Taxi chooseTaxi = taxiAvail.get(minIndex);
    	chooseTaxi.setAvailableTime(time + minTime + destinationTime);
    	chooseTaxi.setTaxiPosition(destination);
    	
    	System.out.println();
    	System.out.println("!!! Choose taxi *" + chooseTaxi.getName() + "* to service the customer request !!!");
    	
        System.out.print("Path of customer : ");
        for(int index = 0 ; index < path.size()-1 ; index++) {
        	System.out.print(path.get(index) + ",");
        }
        System.out.print(path.get(path.size()-1) + " -> time taken is " + (minTime+destinationTime) + " units.");
		System.out.println();
    }
    
    private int printTaxiPath(Taxi taxi , String source) {
    	
    	Launch l = new Launch(edges);
		l.execute(taxi.getTaxiPosition());
		int taxiTime = l.getShortestDistance(source);
		ArrayList<String> taxiPath = l.getPath(source);
	    System.out.print("Path of taxi " + taxi.getName() + ": ");
	    for(int index = 0 ; index < taxiPath.size()-1 ; index++) {
	    	System.out.print(taxiPath.get(index) + ",");
	    }
	    System.out.print(source + " : -> ");
		System.out.print(" time taken is " + taxiTime + " units.");
		System.out.println();
		return taxiTime;
    }
 

	private ArrayList<Taxi> availableTaxi(int time){
        
    	ArrayList<Taxi> taxiAvail = new ArrayList<>();
    	for(int index = 0 ; index < taxis.size() ; index++){
    		Taxi taxi = taxis.get(index);
    		if(taxi.isTaxiAvailable(time)){
    			taxiAvail.add(taxi);
    		}
    	}
    	return taxiAvail;
    }
    
    public void printTaxiPosition(int time){
    	
    	ArrayList<Taxi> taxiAvail = availableTaxi(time);
    	if(taxiAvail.isEmpty()) {
    		System.out.println("No Taxi is Available !! Right Now");
    		return;
    	}
    	for(int index = 0 ; index < taxiAvail.size() ; index++){
    		Taxi taxi = taxiAvail.get(index);
    		System.out.println("taxi " + taxi.getName() + " : " + taxi.getTaxiPosition());
    	}
    }
    
    
}    
