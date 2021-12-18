package ua.com.alevel.shortestway;

public class Vertex {
	public String label;
	public boolean isInTree;

	public Vertex(String label) {
		this.label = label;
		this.isInTree = false;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public boolean isInTree() {
		return isInTree;
	}

	public void setInTree(boolean inTree) {
		isInTree = inTree;
	}
}