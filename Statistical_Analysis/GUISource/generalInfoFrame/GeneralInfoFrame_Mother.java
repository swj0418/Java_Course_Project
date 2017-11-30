package generalInfoFrame;
import java.awt.BorderLayout;

import javax.swing.*;

import gateFrame.GateFramePanel_Mother;

public class GeneralInfoFrame_Mother extends JInternalFrame{
	public GeneralInfoFramePanel_Mother motherpanel;
	
	BorderLayout layout = new BorderLayout();
	
	public GeneralInfoFrame_Mother() {
		super("General Stock Information", true, true, true, true);
		setLayout(layout);
		setSize(700, 335);
		setLocation(0, 500);
		
		
		motherpanel = new GeneralInfoFramePanel_Mother();
		add(motherpanel);
		
		
		
		setVisible(true);
	}
}
