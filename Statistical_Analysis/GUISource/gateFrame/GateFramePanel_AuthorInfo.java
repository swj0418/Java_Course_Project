package gateFrame;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GateFramePanel_AuthorInfo extends JPanel {
	JLabel versioninfo = new JLabel("Version                :            0.1.0");
	JLabel authors = new JLabel("Author : Skywalker & Tiruss");
	
	GridLayout layout = new GridLayout();

	GateFramePanel_AuthorInfo() {
		layout.setColumns(1);
		layout.setRows(2);
		
		setLayout(layout);
		add(versioninfo);
		add(authors);
		
		setVisible(true);
	}
}
