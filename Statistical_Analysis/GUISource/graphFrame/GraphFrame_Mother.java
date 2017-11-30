package graphFrame;
import java.awt.BorderLayout;

import javax.swing.*;

public class GraphFrame_Mother extends JInternalFrame{
	GraphFramePanel_Mother motherpanel;
	
	BorderLayout layout = new BorderLayout();
	public GraphFrame_Mother() {
		super("Stock Data Graph", true, true, true, true);
		setLayout(layout);
		setSize(1050,600);
		setLocation(500,0);
		
		motherpanel = new GraphFramePanel_Mother();
		add(motherpanel);
		
		setVisible(true);
	}
}
