package gateFrame;
import javax.swing.*;
import java.awt.*;

public class GateFrame_Mother extends JInternalFrame {
	GateFramePanel_Mother motherpanel;
	static final int xPosition = 0, yPosition = 0;
	
	public GateFrame_Mother() {
		super("Gate Frame", true, true, true, true);
		setSize(500, 500);
		setLocation(xPosition, yPosition);
		
		
		motherpanel = new GateFramePanel_Mother();
		add(motherpanel);
		
		
		
		
		setVisible(true);
	}
}
