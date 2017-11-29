package consoleFrame;
import javax.swing.JInternalFrame;

public class ConsoleFrame_Mother extends JInternalFrame {
	public ConsoleFramePanel_Mother motherpanel;
	
	public ConsoleFrame_Mother() {
		super("Console", true, true, true, true);
		renderFrame();
	}
	
	public void renderFrame() {
		setLayout(null);
		
		motherpanel = new ConsoleFramePanel_Mother();
		
		setSize(885, 335);
		setLocation(700, 500);
		
		motherpanel.setBounds(0, 0, 875, 305);
		
		
		add(motherpanel);
		setVisible(true);
	}
}
