package taxi_service;

import java.util.*;

public class ShortestPath {
	
	
	private List<Edge> edges;
	private Set<String> settledNodes;
    private Set<String> unSettledNodes;
    private Map<String, String> predecessors;
    private Map<String, Integer> distance;
	
	public ShortestPath( List<Edge> edges) {
		this.edges = edges;
	}
	
	
	public void execute(String source) {
        settledNodes = new HashSet<String>();
        unSettledNodes = new HashSet<String>();
        distance = new HashMap<String, Integer>();
        predecessors = new HashMap<String, String>();
        distance.put(source, 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
        	String node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(String node) {
    	
        List<String> adjacentNodes = getNeighbors(node);
        for (String target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node) + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }

    private int getDistance(String node, String target) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(node) && edge.getDestination().equals(target)) {
                return edge.getTime();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<String> getNeighbors(String node) {
        List<String> neighbors = new ArrayList<String>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(node) && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    private String getMinimum(Set<String> vertexes) {
    	String minimum = null;
        for (String vertex : vertexes) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(String vertex) {
        return settledNodes.contains(vertex);
    }

    public int getShortestDistance(String destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
    public ArrayList<String> getPath(String target) {
    	ArrayList<String> path = new ArrayList<String>();
    	String step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return path;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }

}
