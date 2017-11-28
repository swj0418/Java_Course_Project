package tickerLookUpFrame;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import black.Utils;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

public class TickerFramePanel_Mother extends JPanel{
	TickerFramePanel_Search search;
	public TickerFramePanel_Result result;
	
	
	TickerFramePanel_Mother() {
		renderPanel();
	}
	
	public void renderPanel() {
		removeAll();
		setLayout(null);
		
		search = new TickerFramePanel_Search();
		result = new TickerFramePanel_Result();
		
		search.setBounds(0, 0, 300, 30);
		result.setBounds(0, 30, 300, 470);
		
		
		ActionControl();
		add(search);
		add(result);
		
		validate();
		repaint();
		
		setVisible(true);
	}
	
	private void ActionControl() {
		search.area.addCaretListener(new CaretListener() {
			//Listens to the field every time it is updated.
			public void caretUpdate(CaretEvent e) {
				int number = 0;
				String str = search.area.getText();
				HashMap<String, String> resultmap = Utils.BestMatchSearchAlgorithm(str);
				
				ArrayList<String> StockName = new ArrayList<String>();
				ArrayList<String> StockSymbol = new ArrayList<String>();
				
			    resultmap.keySet().forEach(String->StockName.add(String));
			    resultmap.values().forEach(String->StockSymbol.add(String));
			    number = resultmap.values().size();
				
			    result.buttonarray.clear(); //Make sure it is reset.
			    if(number < 20) { //Too many buttons will freeze the program.
			    	for(int i = 0; i < StockName.size(); i++) {
						JButton button = new JButton();
						button.setName(StockSymbol.get(i));
						button.setText(StockName.get(i) + "   :::   " + StockSymbol.get(i));
						result.buttonarray.add(button);
					}
					result.renderPanel();
			    }
			}
		});
	}
}
