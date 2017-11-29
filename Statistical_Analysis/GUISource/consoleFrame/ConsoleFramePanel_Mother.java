package consoleFrame;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class ConsoleFramePanel_Mother extends JPanel {
	ConsoleFramePanel_Console console;
	
	ConsoleFramePanel_Mother() {
		renderPanel();
	}
	
	public void renderPanel() {
		setLayout(new BorderLayout());
		console = new ConsoleFramePanel_Console();
		
		add(console, BorderLayout.CENTER);
	}

}
