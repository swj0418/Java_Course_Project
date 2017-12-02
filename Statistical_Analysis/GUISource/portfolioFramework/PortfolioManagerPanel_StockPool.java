package portfolioFramework;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PortfolioManagerPanel_StockPool extends JPanel{
	StockPoolInfoPanel categories;
	
	PortfolioManagerPanel_StockPool() {
		
	}
	
	public void renderPanel() {
		removeAll();
		
		categories = new StockPoolInfoPanel();
		
		
		
		
		
		
		validate();
		repaint();
	}
	

}

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
		StockLabel = new JLabel("Stock Symbol");
		WeightLabel = new JLabel("Weights");
		DeleteButtonLabel = new JLabel("Delete Stock");
		
		add(StockLabel);
		add(WeightLabel);
		add(DeleteButtonLabel);
	}
}
