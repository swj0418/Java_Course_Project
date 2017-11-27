package gateFrame;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GateFramePanel_Mother extends JPanel{
	JLabel versioninfo = new JLabel("Version 0.1.0");
	JLabel authors = new JLabel("Skywalker & Tiruss");
	
	GateFramePanel_Mother() {
		setLayout(null);
		
		versioninfo.setBounds(400, 420, 100, 20);
		authors.setBounds(380, 450, 150, 20);
		add(versioninfo);
		add(authors);
		
		setVisible(true);
	}
}
