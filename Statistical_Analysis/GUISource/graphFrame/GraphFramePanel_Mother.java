package graphFrame;



import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;



import javax.swing.*;



import gray.Global;



public class GraphFramePanel_Mother extends JPanel{

	GraphFramePanel_Chart chart;

	GraphFramePanel_Search search;

	

	GraphFramePanel_Mother() {

		renderpanel();

	}

	

	

	

	public void renderpanel() {

		removeAll();

		

		setLayout(null);

		

		chart = new GraphFramePanel_Chart();

		chart.setBounds(0, 0, 700, 465);

		

		search = new GraphFramePanel_Search();

		search.setBounds(710, 20, 200, 50);

		

		ActionControl();

		

		add(chart);

		add(search);

		

		validate();

		repaint();

	}

	

	public void ActionControl() {

		search.button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

					chart = new GraphFramePanel_Chart();

					

					Global.GraphStart = search.start_date.getText();

					Global.GraphEnd = search.end_date.getText();

					

					renderpanel(); //Must render this very panel again.

			}

		});

	}

}
