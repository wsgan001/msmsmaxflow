package graph;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class BasicFace implements Face {

    private final Collection<Edge> edges; 
    private Set<Vertex> vertices;
    
    public BasicFace(Collection<Edge> edges){
	this.edges = edges;
    }
    
    @Override
    public Collection<Edge> getEdges() {
	return edges;
    }
    
    private void populateVertices(){
	vertices = new HashSet<Vertex> ();

	for (Edge edge : edges){
	    vertices.add(edge.getHead());
	    vertices.add(edge.getTail());
	}
    }
    
    @Override
    public Collection<Vertex> getVertices() {
	if (vertices == null){
	    populateVertices();
	}
	return vertices;
    }

    @Override
    public boolean isInsideFace(Edge edge) {
	return edges.contains(edge);
    }

    @Override
    public boolean isInsideFace(Vertex vertex) {
	if (vertices == null){
	    populateVertices();
	}
	return vertices.contains(vertex);
    }

    @Override
    public boolean isAdjacentToFace(Edge edge) {
	Vertex head = edge.getHead();
	Vertex tail = edge.getTail();
	
	if (vertices == null){
	    populateVertices();
	}
	boolean headInside = vertices.contains(head);
	boolean tailInside = vertices.contains(tail);
	if (headInside){
	    return isAdjacentToFace(tail);
	} else if (tailInside){
	    return isAdjacentToFace(head);
	} 
	return false;
    }

    @Override
    public boolean isAdjacentToFace(Vertex vertex) {
	if (vertices == null){
	    populateVertices();
	}
	for (Vertex v : vertices){
	    for (Vertex neighbor : v.getNeighboringVertices()){
		if (vertex.equals(neighbor)){
		    return true;
		}
	    }
	}
	return false;
    }


}