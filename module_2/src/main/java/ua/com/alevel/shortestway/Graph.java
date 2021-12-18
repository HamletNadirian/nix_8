package ua.com.alevel.shortestway;

import java.util.ArrayList;
import java.util.List;

public class Graph {
	public final int MAX_VERTS = 1000;
	public final int INFINITY = 100000000;
	public Vertex vertexList[];
	public int relationMatrix[][];
	public int countOfVertices;
	public int countOfVertexInTree;
	public List<Path> shortestPaths;
	public int currentVertex;
	public int startToCurrent;
	static ArrayList<String> saveShortestDistance = new ArrayList();

	public Graph() {
		vertexList = new Vertex[MAX_VERTS];
		relationMatrix = new int[MAX_VERTS][MAX_VERTS];
		countOfVertices = 0;
		countOfVertexInTree = 0;
		for (int i = 0; i < MAX_VERTS; i++) {
			for (int k = 0; k < MAX_VERTS; k++) {
				relationMatrix[i][k] = INFINITY;
				shortestPaths = new ArrayList<>();
			}
		}
	}

	public void addVertex(String lab) {
		vertexList[countOfVertices++] = new Vertex(lab);
	}

	public void addEdge(int start, int end, int weight) {
		relationMatrix[start][end] = weight;
	}

	public void path() throws Exception {
		int startTree = 0;
		vertexList[startTree].setInTree(true);
		countOfVertexInTree = 1;

		for (int i = 0; i < countOfVertices; i++) {
			int tempDist = relationMatrix[startTree][i];
			Path path = new Path(tempDist);
			path.getParentVertices().add(0);
			shortestPaths.add(path);
		}

		while (countOfVertexInTree < countOfVertices) {
			int indexMin = getMin();
			int minDist = shortestPaths.get(indexMin).getDistance();

			if (minDist == INFINITY) {
				System.out.println("В графе пристувствуют недостижимые вершины");
				break;
			} else {
				currentVertex = indexMin;
				startToCurrent = shortestPaths.get(indexMin).getDistance();
			}

			vertexList[currentVertex].setInTree(true);
			countOfVertexInTree++;
			updateShortestPaths();
		}

		displayPaths();
	}

	public void clean() {
		countOfVertexInTree = 0;
		for (int i = 0; i < countOfVertices; i++) {
			vertexList[i].setInTree(false);
		}
	}

	private int getMin() {
		int minDist = INFINITY;
		int indexMin = 0;
		for (int i = 1; i < countOfVertices; i++) {
			if (!vertexList[i].isInTree() && shortestPaths.get(i).getDistance() < minDist) {
				minDist = shortestPaths.get(i).getDistance();
				indexMin = i;
			}
		}
		return indexMin;
	}

	private void updateShortestPaths() {
		int vertexIndex = 1;
		while (vertexIndex < countOfVertices) {

			if (vertexList[vertexIndex].isInTree()) {
				vertexIndex++;
				continue;
			}

			int currentToFringe = relationMatrix[currentVertex][vertexIndex];
			int startToFringe = startToCurrent + currentToFringe;
			int shortPathDistance = shortestPaths.get(vertexIndex).getDistance();

			if (startToFringe < shortPathDistance) {
				List<Integer> newParents = new ArrayList<>(shortestPaths.get(currentVertex).getParentVertices());
				newParents.add(currentVertex);
				shortestPaths.get(vertexIndex).setParentVertices(newParents);
				shortestPaths.get(vertexIndex).setDistance(startToFringe);
			}
			vertexIndex++;
		}
	}

	public static ArrayList<String> saveDist(Path shortest) {
		String str = String.valueOf(shortest.getDistance());
		saveShortestDistance.add(str);
		return saveShortestDistance;
	}

	private void displayPaths() throws Exception {
		for (int i = 0; i < countOfVertices; i++) {
			System.out.print(vertexList[i].getLabel() + " = ");
			if (shortestPaths.get(i).getDistance() == INFINITY) {
				System.out.println("0");
			} else {
				String result = shortestPaths.get(i).getDistance() + " (";
				List<Integer> parents = shortestPaths.get(i).getParentVertices();
				for (int j = 0; j < parents.size(); j++) {
					result += vertexList[parents.get(j)].getLabel() + " -> ";
				}
				System.out.println(result + vertexList[i].getLabel() + ")");
			}
		}
		saveDist(shortestPaths.get(shortestPaths.size() - 1));
	}
}