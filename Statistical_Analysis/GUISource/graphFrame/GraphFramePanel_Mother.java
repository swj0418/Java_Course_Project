package graphFrame;

import javax.swing.*;

public class GraphFramePanel_Mother extends JPanel{
	GraphFramePanel_Chart chart;
	GraphFramePanel_Search search;
	
	GraphFramePanel_Mother() {
		renderpanel();
	}
	
	public void renderpanel() {
		setLayout(null);
		
		chart = new GraphFramePanel_Chart();
		chart.setBounds(0, 0, 800, 500);
		
		search = new GraphFramePanel_Search();
		search.setBounds(820, 20, 200, 50);
		
		add(chart);
		add(search);
	}

}
