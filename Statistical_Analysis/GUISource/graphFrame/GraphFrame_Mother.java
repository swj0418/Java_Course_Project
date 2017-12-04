package graphFrame;

import java.awt.BorderLayout;
import javax.swing.*;

public class GraphFrame_Mother extends JInternalFrame {
	public GraphFramePanel_Mother motherpanel;

	BorderLayout layout;
	
	public GraphFrame_Mother() {
		super("Stock Data Graph", true, true, true, true);
		renderFrame();
	}
	
	public void renderFrame() {
		layout = new BorderLayout();
		
		setLayout(layout);
		setSize(930,500);
		setLocation(650,0);

		motherpanel = new GraphFramePanel_Mother();
		add(motherpanel);
		
		validate();
		repaint();
		
		setVisible(true);
	}
}
