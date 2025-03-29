package main;

import javax.swing.*;
import java.awt.*;
import graphObjects.Record;

public class GUI extends JFrame{
	int recordCount = 0;
	JTextArea list = new JTextArea();
	JScrollPane scrollPane = new JScrollPane(list);
	JPanel infoPanel = new JPanel();
	JLabel recordCounter = new JLabel();
	GUI(){
		list.setEditable(false);
		infoPanel.setPreferredSize(new Dimension(0,30));
		infoPanel.add(recordCounter,BorderLayout.WEST);
		add(scrollPane, BorderLayout.CENTER);
		add(infoPanel, BorderLayout.SOUTH);
		setSize(300,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void addList(Record r) {
		recordCount++;
		recordCounter.setText(Integer.toString(recordCount));
		list.append(r.toString()+"\n");
		setVisible(true);
	}
}