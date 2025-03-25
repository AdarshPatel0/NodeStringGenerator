package graphObjects;

public class Node {
	public int id;
	public int adjacentNodes[];
	public Node(int i){
		id = i;
	}
	public void printAdjacent() {
		for(int i = 0; i < adjacentNodes.length; i++) {
			System.out.print(adjacentNodes[i] + " ");
		}
		System.out.print('\n');
	}
}
