package portfolioFramework;

import javax.swing.JInternalFrame;

public class PortfolioCorrelationFrame_Mother extends JInternalFrame {
	PortfolioCorrelationPanel_Mother motherpanel;
	
	PortfolioCorrelationFrame_Mother() {
		super("Correlation Matrix", true, true, true, true);
		
		renderFrame();
	}
	
	public void renderFrame() {
		motherpanel = new PortfolioCorrelationPanel_Mother();
		
		setSize(500, 500);
		setLocation(900, 300);
		motherpanel = new PortfolioCorrelationPanel_Mother();
		add(motherpanel);
		
		
		validate();
		repaint();
		
		setVisible(true);
	}
	
	
}
