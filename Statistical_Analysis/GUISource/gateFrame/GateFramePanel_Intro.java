package gateFrame;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GateFramePanel_Intro extends JPanel{
	private JLabel introlabel = new JLabel("Stock Analyzer");
	
	private BorderLayout layout = new BorderLayout();
	private Font font = new Font("font", Font.PLAIN|Font.BOLD, 25);
	
	GateFramePanel_Intro() {
		setLayout(layout);
		
		introlabel.setFont(font);
		add(introlabel);
	}
	
}
