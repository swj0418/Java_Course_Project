package consoleFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.Console;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import black.ConsoleOutputCapturer;

public class ConsoleFramePanel_Console extends ConsoleFramePanel_Mother {
	JTextArea consolearea;
	JScrollPane scrollableconsole;
	Console console;
	ConsoleOutputCapturer capturer = new ConsoleOutputCapturer();
	
	ConsoleFramePanel_Console() {
		renderPanel();
		TimerTaskHandler();
		TimerTaskHandlerTerminal();
	}
	
	public void renderPanel() {
		setLayout(new BorderLayout());
		
		consolearea = new JTextArea();
		
		consolearea.setBackground(Color.BLACK);
		consolearea.setForeground(Color.WHITE);
		
		scrollableconsole = new JScrollPane(consolearea);
		
		add(scrollableconsole, BorderLayout.CENTER);
		
		
	}
	
	public void TimerTaskHandler() {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				capturer.start();
			}
		}, 0, 500);
	}
	
	public void TimerTaskHandlerTerminal() {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				String str = capturer.stop();
				consolearea.append(str);
			}
		}, 0, 1000);
	}
}
