package taxi_service;

public class TaxiService{

	private Graph g;
	
	public TaxiService() {	
		g = new Graph();
	}

	public void performAction(String actionMessage) {
		System.out.println();
		System.out.println("action to be performed: " + actionMessage);
		System.out.println();
		String[] arr = actionMessage.split(" ");
		
		String action = arr[0];
		
		if(action.equals("edge")) {
			g.addEdge(arr[1], arr[2], Integer.valueOf(arr[3]));
		}else if(action.equals("taxi")) {
			g.addTaxi(arr[1], arr[2]);
		}else if(action.equals("customer")) {
			g.customerRequest(arr[1], arr[2] , Integer.valueOf(arr[3]));
		}else if(action.equals("printTaxiPosition")) {
			g.printTaxiPosition(Integer.valueOf(arr[1]));
		}
		System.out.println("----------------------------------------------------------------------");
	}
	
}

