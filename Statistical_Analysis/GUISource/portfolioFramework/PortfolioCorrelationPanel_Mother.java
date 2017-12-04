package portfolioFramework;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import gray.Global;

public class PortfolioCorrelationPanel_Mother extends JPanel {
	JTable correlationtable;
	JScrollPane scrollabletable;
	
	PortfolioCorrelationPanel_Mother() {
		renderPanel();
	}
	
	public void renderPanel() {
		removeAll();
		
		correlationtable = new JTable(Global.corr_data, Global.corr_column);
		scrollabletable = new JScrollPane(correlationtable);
		scrollabletable.setHorizontalScrollBar(new JScrollBar());
		
		add(scrollabletable);
		
		validate();
		repaint();
	}
	

}
