package main;

import graphObjects.*;
import graphObjects.Record;

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
	public static ArrayList<Record> records = new ArrayList<Record>();

	public static void sortRecords() {
		for (int i = 0; i < records.size() - 1; i++) { // Exclude last element
			int smallest = i;
			for (int j = i + 1; j < records.size(); j++) {
				if (records.get(j).getLength() > records.get(smallest).getLength()) { // Find smallest
					smallest = j;
				}
			}
			if (smallest != i) {
				Record temp = records.get(i);
				records.set(i, records.get(smallest));
				records.set(smallest, temp);
			}
		}
	}

	public static void parseNode(Node n, boolean checkList[], Record record) {
		record.addNode(n);
		checkList[n.id] = false;
		boolean end = true;
		for (int i = 0; i < n.adjacentNodes.length; i++) {
			if(n.adjacentNodes[i]==null) continue;
			if (checkList[n.adjacentNodes[i].id] == false) {
			} else {
				end = false;
				boolean checkListCopy[] = new boolean[checkList.length];
				System.arraycopy(checkList, 0, checkListCopy, 0, checkListCopy.length);
				Record recordCopy = record.clone();
				parseNode(n.adjacentNodes[i], checkListCopy, recordCopy);
			}
		}
		if (end == true) {
			records.add(record);
			return;
		}
	}
	
	public static void cullRecords() {
		for(int i = 0; i < records.size(); i++) {
			String currentString = records.get(i).toString();
			for(int j = 1; j < currentString.length(); j++) {
				String removalString = currentString.substring(j);
				for(int k = 0; k < records.size(); k++) {
					if(records.get(k).toString().equals(removalString)) {
						records.remove(k);
					}
				}
			}
		}
	}
	public static void padRecords() {
		for(int i = 0; i < records.size(); i++) {
//			System.out.println(nodeCount - records.get(i).getLength());
		}
	}
	public static void fillOccurences() {
		for(int i = 0; i < records.size(); i++) {
			int size = records.get(i).getLength()-1;
			for(int j = 0; j < records.get(i).getLength(); j++) {
				Node n = records.get(i).recordData.get(j);
				n.occurences[size]++;
			}
		}
	}
	
	public static void main(String[] args) {
		File inputFile = new File(JOptionPane.showInputDialog("Input File:"));
		try {
			Scanner fileScanner = new Scanner(inputFile);
			nodeCount = Integer.parseInt(fileScanner.nextLine());
			nodes = new Node[nodeCount];
			String lineData;
			for (int i = 0; i < nodeCount; i++) {
				Node newNode = new Node();
				nodes[i] = newNode;
				newNode.occurences = new int[nodeCount];
			}
			for (int i = 0; i < nodeCount; i++) {
				lineData = fileScanner.nextLine();
				String nodeID = lineData.substring(0, lineData.indexOf(':'));
				String adjacentIDs[] = lineData.substring(lineData.indexOf(':') + 1, lineData.length()).trim()
						.split("[\\s,]+");
				Node currentNode = nodes[Integer.parseInt(nodeID)];
				currentNode.id = Integer.parseInt(nodeID);
				currentNode.adjacentNodes = new Node[adjacentIDs.length];
				for (int j = 0; j < adjacentIDs.length; j++) {
					if (!adjacentIDs[j].equals(""))
						currentNode.adjacentNodes[j] = nodes[Integer.parseInt(adjacentIDs[j])];
				}
			}
			fileScanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < nodes.length; i++) {
			System.out.print(nodes[i].id + "| ");
			nodes[i].printAdjacent();
		}
		for (int i = 0; i < nodes.length; i++) {
			Node node = nodes[i];
			boolean checkList[] = new boolean[nodeCount];
			Record record = new Record();
			Arrays.fill(checkList, true);
			parseNode(node, checkList, record);
		}
//		cullRecords();
		sortRecords();
		fillOccurences();
//		padRecords();
		for (int i = 0; i < records.size(); i++) {
//			System.out.println(records.get(i).getLength());
			newGUI.addList(records.get(i));
		}
		for(int i = 0; i < nodeCount; i++) {
			System.out.print(i + "| ");
			for(int j = 0; j < nodes.length; j++) {
				System.out.print(nodes[i].occurences[j] + "\t");
			}
			System.out.println();
		}
	}
}
