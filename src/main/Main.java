package main;

import graphObjects.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
	public static int nodeCount = 0;
	public static Node nodes[] = {};
	public static GUI newGUI = new GUI();
	public static ArrayList<String> records = new ArrayList<String>();
	
	public static void sortRecords() {
		for(int i = 0; i < records.size(); i++) {
			int smallest = i;
			for(int j = i; j < records.size(); j++) {
				if(records.get(j).compareTo(records.get(i)) < 0) {
					smallest = j;
				}
			}
			String temp = records.get(i);
			records.set(i, records.get(smallest));
			records.set(smallest, temp);
		}
	}
	
	public static void parseNode(int n, boolean checkList[], String record) {
		record = record + Integer.toString(n);
		Node nodeObject = nodes[n];
		checkList[n] = false;
		boolean end = true;
		for(int i = 0; i < nodeObject.adjacentNodes.length; i++) {
			if(checkList[nodeObject.adjacentNodes[i]]==false) {
			}else {
				end = false;
				boolean checkListCopy[] = new boolean[checkList.length];
				System.arraycopy(checkList, 0, checkListCopy, 0, checkListCopy.length);
				parseNode(nodeObject.adjacentNodes[i],checkListCopy,record);
			}
		}
		if(end == true) {
			records.add(record);
			return;
		}
	}
	
	public static void main(String[] args) {
		File inputFile = new File(JOptionPane.showInputDialog("Input File:" ));
		try {
			Scanner fileScanner = new Scanner(inputFile);
			nodeCount = Integer.parseInt(fileScanner.nextLine());
			nodes = new Node[nodeCount];
			String lineData;
			for(int i = 0; i < nodeCount; i++) {
				lineData = fileScanner.nextLine();
				Node newNode = new Node(i);
				String nodeID = lineData.substring(0, lineData.indexOf(':'));
				String nodeIDs[] = lineData.substring(lineData.indexOf(':')+1,lineData.length()).trim().split("[\\s,]+");
				nodes[Integer.parseInt(nodeID)] = newNode;
				newNode.adjacentNodes = new int[nodeIDs.length];
				for(int j = 0; j < nodeIDs.length; j++) {
					if(!nodeIDs[j].equals(""))
					newNode.adjacentNodes[j] = Integer.parseInt(nodeIDs[j]);
				}
			}
			fileScanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for(int i = 0; i < nodes.length; i++) {
			System.out.print(nodes[i].id + "| ");
			nodes[i].printAdjacent();
		}
		for(int i = 0; i < nodes.length; i++) {
			int node = nodes[i].id;
			boolean checkList[] = new boolean[nodeCount];
			String record = "";
			Arrays.fill(checkList, true);
			parseNode(node,checkList,record);
		}
		sortRecords();
		for(int i = 0; i < records.size(); i++) {
			newGUI.addList(records.get(i));
		}
	}
}
