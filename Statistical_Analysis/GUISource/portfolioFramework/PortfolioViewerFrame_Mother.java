package portfolioFramework;

import javax.swing.JInternalFrame;

public class PortfolioViewerFrame_Mother extends JInternalFrame{
	PortfolioViewerPanel_Mother motherpanel;
	
	PortfolioViewerFrame_Mother() {
		super("Portfolio Viewer", true, true, true, true);
		
		renderFrame();
	}
	
	public void renderFrame() {
		motherpanel = new PortfolioViewerPanel_Mother();
		
		setSize(500, 500);
		setLocation(300, 300);
		motherpanel = new PortfolioViewerPanel_Mother();
		add(motherpanel);
		
		
		validate();
		repaint();
		
		setVisible(true);
	}

}
