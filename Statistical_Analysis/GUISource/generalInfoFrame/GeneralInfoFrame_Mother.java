package generalInfoFrame;
import javax.swing.*;

import gateFrame.GateFramePanel_Mother;

public class GeneralInfoFrame_Mother extends JInternalFrame{
	GeneralInfoFramePanel_Mother motherpanel;
	
	public GeneralInfoFrame_Mother() {
		super("General Stock Information", true, true, true, true);
		setSize(500, 400);
		setLocation(0, 500);
		
		
		motherpanel = new GeneralInfoFramePanel_Mother();
		add(motherpanel);
		
		
		
		
		setVisible(true);
	}
}
