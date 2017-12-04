package portfolioFramework;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import gray.Global;

public class PortfolioViewerPanel_Info extends JPanel {
	JLabel constituent;
	JLabel expectedreturn;
	JLabel variance;
	JLabel standarddeviation;
	
	GridLayout layout;
	
	PortfolioViewerPanel_Info() {
		renderPanel();
	}

	
	public void renderPanel() {
		removeAll();
		
		layout = new GridLayout();
		layout.setColumns(1);
		layout.setRows(4);
		setLayout(layout);
		
		constituent = new JLabel("Constituent : " + Global.stockpool.size());
		expectedreturn = new JLabel("Portfolio Return : " + Global.portfolio.PortfolioReturn);
		variance = new JLabel("Portfolio Variance : " + Global.portfolio.PortfolioVariance);
		standarddeviation = new JLabel("Portfolio S.D. : " + Math.pow(Global.portfolio.PortfolioVariance, 0.5d));
		
		add(constituent);
		add(expectedreturn);
		add(variance);
		add(standarddeviation);
		
		validate();
		repaint();
	}
}
