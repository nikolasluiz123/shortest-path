package br.com.shortest.path.graph;

import java.util.StringJoiner;

public class Edge {

	private Vertex start;
	private Vertex end;
	private Integer weight;

	public Edge(Vertex start, Vertex end, Integer weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	public Vertex getStart() {
		return start;
	}

	public void setStart(Vertex start) {
		this.start = start;
	}

	public Vertex getEnd() {
		return end;
	}

	public void setEnd(Vertex end) {
		this.end = end;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	
	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner("\r\n");
		
		joiner.add("Start: ".concat(this.start.getValue().toString()));
		joiner.add("End: ".concat(this.end.getValue().toString()));
		joiner.add("Weight: ".concat(this.weight.toString()));
		
		return joiner.toString();		
	}

}