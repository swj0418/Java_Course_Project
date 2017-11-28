package tickerLookUpFrame;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class TickerFrame_Mother extends JInternalFrame{
	public TickerFramePanel_Mother motherpanel;
	public TickerFrame_Mother() {
		super("Ticker Searcher", true, true, true, true);
		renderFrame();
	}
	
	public void renderFrame() {
		setLayout(null);
		
		setSize(300, 500);
		setLocation(500, 0);
		
		motherpanel = new TickerFramePanel_Mother();
		motherpanel.setBounds(0, 0, 300, 500);
		add(motherpanel);
		
		setVisible(true);
	}
	
}
