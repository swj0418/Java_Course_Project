package white;
import red.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GeneralInfoModule extends Panel {
	String ModuleName = "General Information Module";
	Stock stock;
	GeneralInfoModule(String symbol) {
		this.stock = new Stock(symbol);
		
		this.setName(ModuleName);
		this.setSize(600, 600);
		this.setLayout(null);
		
		JTextArea Symbol = new JTextArea();
		Symbol.setText(stock.SYMBOL);
		Symbol.setBounds(10, 10, 100, 30);
		this.add(Symbol);
		
		JTextArea StockName = new JTextArea();
		StockName.setText(stock.BasicInfo.StockName);
		StockName.setBounds(110, 10, 100, 30);
		this.add(StockName);
		
		JLabel LatestPriceLabel = new JLabel("Latest Price");
		LatestPriceLabel.setBounds(10, 100, 100, 30);
		this.add(LatestPriceLabel);
		
		JTextArea LatestPrice = new JTextArea();
		LatestPrice.setText("" + stock.Adj_Close.get(0));
		LatestPrice.setBounds(10, 150, 100, 30);
		this.add(LatestPrice);
		
		this.setVisible(true);
	}
}
