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
	JLabel start_search = new JLabel();
	JLabel end_search = new JLabel();
	JLabel blank = new JLabel();
	JTextField start_date = new JTextField();
	JTextField end_date = new JTextField();
	JButton button = new JButton();
	
	String start;
	String end;

	GraphFramePanel_Chart refresh;

	GridLayout layout = new GridLayout();
	
	
	GraphFramePanel_Search() {
		renderPanel();
	}
	
	private void renderPanel() {
		removeAll();
		
		layout.setColumns(2);
		layout.setRows(3);
		setLayout(layout);
		
		start_search = new JLabel("Start Date : ");
		end_search = new JLabel("  End Date : ");
		blank = new JLabel("");
		start_date = new JTextField();
		end_date = new JTextField();
		button = new JButton("Search!");
		
		
		add(start_search); add(start_date);
		add(end_search); add(end_date);
		add(blank); add(button);
		
		
		validate();
		repaint();
		
	}
}
