package graphObjects;

public class Node {
	public int id;
	public Node adjacentNodes[];
	public int occurences[];
	public Node(int i){
		id = i;
	}
	public Node() {
		
	}
	public void printAdjacent() {
		if(adjacentNodes.length != 0) {
			for(int i = 0; i < adjacentNodes.length; i++) {
				if(adjacentNodes[i]==null) continue;
				System.out.print(adjacentNodes[i].id + " ");
			}
		}
		System.out.print('\n');
	}
}
