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
	JLabel marketcap;
	JLabel sector;
	
	JLabel availabledatasize;
	
	GridLayout layout = new GridLayout();
	
	GeneralInfoFramePanel_Basics() {
		renderPanel();
	}
	
	public void renderPanel() {
		this.removeAll();
		
		stock = new Stock(Global.SYMBOL);
		
		layout.setColumns(1);
		layout.setRows(4);
		setLayout(layout);
		
		stockname = new JLabel("Stock Name : " + stock.BasicInfo.StockName);
		stocksymbol = new JLabel("Stock Symbol : " + stock.SYMBOL);
		IPO_year = new JLabel("Date of the IPO : " + stock.Date.get(stock.Date.size() - 1));
		availabledatasize = new JLabel("Available data size : " + stock.Avail_Size);
		marketcap = new JLabel("Market Captal : $" + stock.BasicInfo.MarketCap);
		sector = new JLabel("Sector : " + stock.BasicInfo.Sector);
		
		add(stockname); add(stocksymbol); add(IPO_year); add(marketcap); add(sector); add(availabledatasize);
		
		System.out.println(stock.Dividend_M);
		
		this.validate();
		repaint();
	}
}
