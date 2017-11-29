package graphFrame;

import javax.swing.*;

import gray.Global;
import red.Stock;

public class GraphFramePanel_Chart extends JPanel{
	Stock stock;
	
	String[] column;
	String[][] data;
	
	GraphFramePanel_Chart(){
		stock_price_graph();
	}
	
	
}
