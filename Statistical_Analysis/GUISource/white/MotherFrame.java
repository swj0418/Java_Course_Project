package white;
import red.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MotherFrame extends JFrame{
	MotherPanel motherpanel;
	
	public MotherFrame() {
		setMotherFrame();
		createMotherPanel();
	}
	
	private void setMotherFrame() {
		setSize(1024, 768);
		this.setBounds(0, 0, 1600, 900);
		this.setVisible(true);
	}

	private void createMotherPanel() {
		motherpanel = new MotherPanel();
		
		add(motherpanel);
	}
	


}
