package portfolioFramework;

import javax.swing.JPanel;

public class PortfolioManagerPanel_Mother extends JPanel{
	PortfolioManagerPanel_AddStock addstockpanel;
	PortfolioManagerPanel_StockPool stockpoolpanel;
	
	public PortfolioManagerPanel_Mother() {
		renderPanel();
	}
	
	public void renderPanel() {
		removeAll();
		
		setLayout(null);
		
		addstockpanel = new PortfolioManagerPanel_AddStock();
		stockpoolpanel = new PortfolioManagerPanel_StockPool();
		
		addstockpanel.setBounds(0, 0, 200, 500);
		stockpoolpanel.setBounds(200, 0, 300, 500);
		
		add(addstockpanel);
		add(stockpoolpanel);
		
		validate();
		repaint();		
	}
}
