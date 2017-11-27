package gateFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Timer;
import java.time.*;
import java.time.chrono.*;

public class GateFramePanel_Time extends JPanel {
	JLabel datelabel;
	JLabel timelabel;
	
	LocalDate date;
	Date clock;
	
	GridLayout layout = new GridLayout();
	
	GateFramePanel_Time() {
		layout.setColumns(1);
		layout.setRows(2);
		setLayout(layout);
		datelabel = new JLabel(date.now().toString() + "");
		timelabel = new JLabel("");
		
		add(datelabel);
		add(timelabel);
		
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				String string = new SimpleDateFormat("HH:mm:ss").format(new Date());
				timelabel.setText(string);
			}
		}, 0, 1000);
		
		setVisible(true);
	}
}
