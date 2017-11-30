package portfolioFramework;

import javax.swing.JInternalFrame;

public class PortfolioManagerFrame_Mother extends JInternalFrame{
	PortfolioManagerPanel_Mother motherpanel;
	
	public PortfolioManagerFrame_Mother() {
		super("Portfolio Manager", true, true, true, true);
		renderFrame();
	}
	
	public void renderFrame() {
		motherpanel = new PortfolioManagerPanel_Mother();
		
		setSize(500, 500);
		setLocation(300, 300);
		add(motherpanel);
		
		validate();
		repaint();
		
		setVisible(true);
	}

}
