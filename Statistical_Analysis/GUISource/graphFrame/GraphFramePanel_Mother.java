package graphFrame;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.*;



import gray.Global;

public class GraphFramePanel_Mother extends JPanel{
	public GraphFramePanel_Chart chart;
	public GraphFramePanel_Search search;
	//public GraphFramePanel_Compare compare;

	public GraphFramePanel_Mother() {
		renderpanel();
	}

	public void renderpanel() {
		removeAll();
		setLayout(null);

		chart = new GraphFramePanel_Chart();
		chart.setBounds(0, 0, 700, 465);

		search = new GraphFramePanel_Search();
		search.setBounds(710, 20, 200, 50);
		
		//compare = new GraphFramePanel_Compare();
		//compare.setBounds(710, 100, 200, 80);

		ActionControl();
		//CompareControl();

		add(chart);
		add(search);
		//add(compare);

		validate();
		repaint();
	}

	public void ActionControl() {
		
		search.button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(0 > LocalDate.parse(search.start_date.getText()).compareTo(LocalDate.parse(chart.stock.Date.get(chart.stock.Date.size() - 1)))) {
						JFrame f = new JFrame();
						JOptionPane.showMessageDialog(f, "please input correct date!");
					} else {
						Global.GraphStart = search.start_date.getText();
						Global.GraphEnd = search.end_date.getText();

						renderpanel(); //Must render this very panel again.
					}
				} catch(DateTimeParseException r) {
					JFrame f = new JFrame();
					JOptionPane.showMessageDialog(f, "please input correct number (You must check IPO Date)");
				}
			}
		});
	}
	
	/*public void CompareControl() {
		compare.Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Global.Compare_SYMBOL = compare.Field.getText();
				
				renderpanel();
				
			}
		});
	}*/
}
