package generalInfoFrame;
import red.*;

import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

import gray.Global;

public class GeneralInfoFramePanel_Basics extends JPanel{
	public Stock stock;
	
	JLabel stockname;
	JLabel stocksymbol;
	JLabel IPO_year;
	
	JLabel availabledatasize;
	
	GridLayout layout = new GridLayout();
	
	GeneralInfoFramePanel_Basics() {
		stock = new Stock(Global.SYMBOL);
		
		layout.setColumns(1);
		layout.setRows(4);
		setLayout(layout);
		
		stockname = new JLabel("Stock Name : " + stock.BasicInfo.StockName);
		stocksymbol = new JLabel("Stock Symbol : " + stock.SYMBOL);
		IPO_year = new JLabel("Date of the IPO : " + stock.Date.get(stock.Date.size() - 1));
		availabledatasize = new JLabel("Available data size : " + stock.Avail_Size);
		
		add(stockname); add(stocksymbol); add(IPO_year); add(availabledatasize);
		
	}
}
