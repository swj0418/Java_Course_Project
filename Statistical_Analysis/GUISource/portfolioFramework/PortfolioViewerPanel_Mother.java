package portfolioFramework;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PortfolioViewerPanel_Mother extends JPanel{
	JLabel portfolioname;
	
	public PortfolioViewerPanel_Info info;
	

	Font font;
	PortfolioViewerPanel_Mother() {
		renderPanel();
	}
	
	public void renderPanel() {
		removeAll();
		
		setLayout(null);
		info = new PortfolioViewerPanel_Info();
		info.setBounds(0, 50, 300, 200);
		
		
		portfolioname = new JLabel("Portfolio");
		font = new Font("Serif", Font.BOLD, 20);
		portfolioname.setFont(font);
		portfolioname.setBounds(200, 0, 100, 30);
		
		
		
		
		add(portfolioname);
		add(info);
		
		
		validate();
		repaint();
	}
}
