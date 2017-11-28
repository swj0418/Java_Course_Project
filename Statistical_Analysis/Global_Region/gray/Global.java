package gray;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.*;

import white.MotherFrame;

public class Global {
	public static String SYMBOL = "AAPL";
	public static MotherFrame motherframe;
	
	public Global() {
		LinkedActionListener1();
	}
	
	public void LinkedActionListener1() {
		for(int i = 0; i < motherframe.tickerframemother.motherpanel.result.buttonarray.size(); i++) {
			motherframe.tickerframemother.motherpanel.result.buttonarray.get(i).addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JButton button = (JButton) e.getSource();
					SYMBOL = button.getName();
					motherframe.generalinfomother.motherpanel.basics.renderPanel();
					motherframe.generalinfomother.motherpanel.pricechart.renderPanel();
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
		}
	}
}
