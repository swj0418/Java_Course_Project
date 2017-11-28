package tickerLookUpFrame;
import javax.swing.*;
import java.awt.*;
import black.Utils;

public class TickerFramePanel_Search extends JPanel{
	JTextArea area;
	
	Font font = new Font("Serif", Font.BOLD, 25);
	TickerFramePanel_Search() {
		renderPanel();
	}
	
	public void renderPanel() {
		setLayout(new BorderLayout());
		area = new JTextArea();
		area.setBackground(Color.BLACK);
		area.setForeground(Color.WHITE);
		area.setSize(300, 30);
		area.setFont(font);
		
		add(area, BorderLayout.CENTER);
		setVisible(true);
	}

}
