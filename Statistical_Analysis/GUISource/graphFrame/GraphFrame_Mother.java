package graphFrame;
import javax.swing.*;

public class GraphFrame_Mother extends JInternalFrame{
	GraphFramePanel_Mother motherpanel;
	
	public GraphFrame_Mother() {
		super("Stock Data Graph", true, true, true, true);
		setSize(1100,600);
		setLocation(500,0);
		
		motherpanel = new GraphFramePanel_Mother();
		add(motherpanel);
		
		setVisible(true);
	}
}
