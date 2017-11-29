package tickerLookUpFrame;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;

import gray.Global;

public class TickerFramePanel_Result extends JPanel {
	public ArrayList<JButton> buttonarray = new ArrayList<JButton>();
	
	GridLayout layout = new GridLayout();
	
	TickerFramePanel_Result() {
		renderPanel();
	}
	
	public void renderPanel() {
		removeAll();
		
		layout.setColumns(1);
		layout.setRows(10);
		
		setLayout(layout);
		for(int i = 0; i < buttonarray.size(); i++) {
			buttonarray.get(i).setSize(300, 10);
			add(buttonarray.get(i));
		}
		ActionControl();
		
		validate();
		repaint();
		setVisible(true);
	}
	
	private void ActionControl() {
		for(int i = 0; i < buttonarray.size(); i++) {
			buttonarray.get(i).addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					JButton button = (JButton) e.getSource();
					Global.SYMBOL = button.getName();
					//Global.motherframe.generalinfomother.motherpanel.renderFrame();
					
					//Global.motherframe.generalinfomother.motherpanel.basics.renderPanel();
					//Global.motherframe.generalinfomother.motherpanel.pricechart.renderPanel();
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					JButton button = (JButton) e.getSource();
					
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
