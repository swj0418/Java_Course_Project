package graphFrame;

import javax.swing.*;

public class GraphFramePanel_Mother extends JPanel{
	GraphFramePanel_Chart chart;
	
	GraphFramePanel_Mother() {
		setLayout(null);
		
		chart = new GraphFramePanel_Chart();
		chart.setBounds(0,0,200,100);
		
		add(chart);
	}

}
