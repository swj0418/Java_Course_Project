package graphFrame;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.*;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.DefaultHighLowDataset;

public class GraphFramePanel_Search extends JPanel{
	JLabel start_search = new JLabel("Start Date : ");
	JLabel end_search = new JLabel("  End Date : ");
	JLabel blank = new JLabel("");
	JTextField start_date = new JTextField();
	JTextField end_date = new JTextField();
	JButton button = new JButton("Search!");
	
	String start;
	String end;

	GraphFramePanel_Chart refresh;

	GridLayout layout = new GridLayout();
	
	
	GraphFramePanel_Search() {
		Search();
	}
	
	private void Search() {
		layout.setColumns(2);
		layout.setRows(3);
		setLayout(layout);
		
		
		add(start_search); add(start_date);
		add(end_search); add(end_date);
		add(blank); add(button);
		
		ButtonListener listener = new ButtonListener();
		
		button.addActionListener(listener);
		
	}
	
	private class ButtonListener implements ActionListener {
		

		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == button || e.getSource() == start_date || e.getSource() == end_date) {
				refresh = new GraphFramePanel_Chart();
				
				start = start_date.getText();
				end = end_date.getText();
				
				//LocalDate begin = LocalDate.parse(start, DateTimeFormatter.ISO_DATE); //Correct
				//LocalDate fin = LocalDate.parse(end, DateTimeFormatter.ISO_DATE);
				refresh.renderPanel(start, end);
				System.out.println(start);
				System.out.println(end);
			}
		}
	}

}
