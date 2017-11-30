package generalInfoFrame;
import java.awt.BorderLayout;

import javax.swing.*;

import black.Utils;
import gray.Global;
import red.Stock;

public class GeneralInfoFramePanel_PriceChart extends JPanel{
	Stock stock;
	
	JScrollPane scrollablepricechartarea;
	JTable pricetable;
	
	String[]   column;
	String[][] data;
	
	BorderLayout layout = new BorderLayout(); //JTable and ScrollPane has some serious problem. They are said to be hard-coded. To override, add borderlayout.
	GeneralInfoFramePanel_PriceChart() {
		renderPanel();
	}
	
	public void renderPanel() {
		removeAll();
		setLayout(layout);
		stock = new Stock(Global.SYMBOL);
		
		
		setTableContents();
		
		pricetable = new JTable(data, column);
		pricetable.setPreferredScrollableViewportSize(pricetable.getPreferredSize());
		//pricetable.setFillsViewportHeight(false);
		
		scrollablepricechartarea = new JScrollPane(pricetable);
		
		add(scrollablepricechartarea);
		
		validate();
		repaint();
	}
	
	private void setTableContents() {
		column = stock.column;
		data = (Utils.ArrayListToPlain(stock.Total, 8));
	}
}
