package graphObjects;

import java.util.ArrayList;

public class Record implements Cloneable{
	public ArrayList<Node> recordData = new ArrayList<Node>();
	public void addNode(Node n) {
		recordData.add(n);
	}
	public int getLength() {
		return recordData.size();
	}
	@Override
	public String toString() {
		String s = "";
		for(int i = 0; i < recordData.size(); i++) {
			s = s + recordData.get(i).id;
		}
		return s;
	}
	public void listRecord() {
		for(int i = 0; i < recordData.size(); i++) {
			System.out.print(recordData.get(i).id);
		}
		System.out.println();
	}
	@Override
	public Record clone(){
		Record clone = new Record();
		for(int i = 0; i < recordData.size(); i++) {
			clone.recordData.add(recordData.get(i));
		}
		return clone;
	}
	public Record(){
		
	}
}
