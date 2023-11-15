package br.com.shortest.path.graph;

public class Vertex {

	private String value;

	public Vertex(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.value.equals(((Vertex) obj).getValue());
	}
}