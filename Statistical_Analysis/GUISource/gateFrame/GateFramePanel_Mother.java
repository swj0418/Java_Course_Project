package gateFrame;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GateFramePanel_Mother extends JPanel implements Runnable{
	GateFramePanel_AuthorInfo authorinfo = new GateFramePanel_AuthorInfo();
	GateFramePanel_Time time = new GateFramePanel_Time();
	GateFramePanel_Intro intro = new GateFramePanel_Intro();
	
	GateFramePanel_Mother() {
		setLayout(null);
		
		authorinfo.setBounds(300, 400, 200, 50);
		add(authorinfo);
		time.setBounds(0, 400, 100, 50);
		add(time);
		intro.setBounds(0, 20, 200, 50);
		add(intro);
		
		setVisible(true);
	}
	
	public void run() {
		this.time = new GateFramePanel_Time();
	}
}
