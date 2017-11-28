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
		stock = new Stock(Global.SYMBOL);
		
		setTableContents();
		
		pricetable = new JTable(data, column);
		pricetable.setBounds(0, 0, 500, 300);
		scrollablepricechartarea = new JScrollPane(pricetable);
		
		add(scrollablepricechartarea);
	}
	
	private void setTableContents() {
		column = stock.column;
		data = (Utils.ArrayListToPlain(stock.Total, 8));
	}
}
