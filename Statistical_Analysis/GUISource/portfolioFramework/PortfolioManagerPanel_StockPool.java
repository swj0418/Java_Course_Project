package portfolioFramework;

import java.awt.GridLayout;
import java.time.LocalDate;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import black.Stoculator;
import gray.Global;

public class PortfolioManagerPanel_StockPool extends JPanel{
	//StockPoolInfoPanel categories;
	
	String data[][] = {{null, null, null, null}};
	String column[] = {"Symbol", "Weight", "ExpectedR", "SD"};;
	
	JTable pooltable;
	JScrollPane scrollablepooltable;
	
	PortfolioManagerPanel_StockPool() {
		renderPanel();
	}
	
	public void renderPanel() {
		removeAll();
		setLayout(null);
		
		/*
		categories = new StockPoolInfoPanel();
		categories.setBounds(0, 0, 350, 20);
		add(categories);
		*/
		
		setTable();
		
		
		
		
		
		validate();
		repaint();
	}
	
	private void setTable() {
		setColumn();
		setData();
		pooltable = new JTable(data, column);
		
		
		scrollablepooltable = new JScrollPane(pooltable);
		scrollablepooltable.setBounds(0, 20, 250, 400);
		add(scrollablepooltable);
	}
	
	private void setColumn() {
		column = new String[4];
		column[0] = "Symbol";
		column[1] = "Weight";
		column[2] = "ExpectedR";
		column[3] = "Sd";
	}
	
	private void setData() {
		if(!Global.stockpool.isEmpty()) {
			data = new String[Global.stockpool.size()][4];
			for(int i = 0; i < Global.stockpool.size(); i++) {
				data[i][0] = Global.stockpool.get(i).SYMBOL;
				data[i][1] = Global.weightpool.get(i) + "";
				if(Global.calculatingtype.equals("ARITHMETIC")) {
					data[i][2] = Stoculator.ArithmeticMeanReturn(Global.stockpool.get(i).Adj_Close, Global.calculatingtimeslice) + "";
				} else if(Global.calculatingtype.equals("GEOMETRIC")) {
					data[i][2] = Stoculator.GeometricMeanReturn(Global.stockpool.get(i).Adj_Close, Global.calculatingtimeslice) + "";
				}
				data[i][3] = Stoculator.StandardDeviation(Global.stockpool.get(i).request("ADJ_CLOSE", "2017-01-01", LocalDate.now().toString()),
						Global.calculatingtimeslice, Global.calculatingtype) + "";
			}
		}
		/*
		data = new String[1][4];
		data[0][0] = "TSLA";
		data[0][1] = "50.0%";
		data[0][2] = "23.0%";
		data[0][3] = "8.50%";
		*/
	}
}

/*
class StockPoolInfoPanel extends JPanel {
	JLabel StockLabel; JLabel WeightLabel; JLabel DeleteButtonLabel;
	GridLayout layout = new GridLayout();
	StockPoolInfoPanel() {
		renderPanel();
	}
	
	public void renderPanel() {
		removeAll();
		
		layout.setColumns(3);
		setLayout(layout);
		
		addFixedLabels();
		
		validate();
		repaint();
	}
	
	public void addFixedLabels() {
		StockLabel = new JLabel("Stock Symbol     ");
		WeightLabel = new JLabel("Weights");
		DeleteButtonLabel = new JLabel("Delete");
		
		add(StockLabel);
		add(WeightLabel);
		add(DeleteButtonLabel);
	}
}
*/
