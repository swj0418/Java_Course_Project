package generalInfoFrame;
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
	
	GeneralInfoFramePanel_PriceChart() {
		renderPanel();
	}
	
	public void renderPanel() {
		removeAll();
		
		stock = new Stock(Global.SYMBOL);
		
		setTableContents();
		
		pricetable = new JTable(data, column);
		pricetable.setPreferredScrollableViewportSize(pricetable.getPreferredSize());
		pricetable.setFillsViewportHeight(false);
		
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
