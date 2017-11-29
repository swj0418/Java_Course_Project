package generalInfoFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import gray.Global;
import red.Stock;

public class GeneralInfoFramePanel_Mother extends JPanel {
	public GeneralInfoFramePanel_Basics basics;
	public GeneralInfoFramePanel_Controls controls;
	public GeneralInfoFramePanel_LatestData latest;
	public GeneralInfoFramePanel_PriceChart pricechart;
	
	GeneralInfoFramePanel_Mother() {
		renderFrame();
	}
	
	public void renderFrame() {
		removeAll();
		
		setLayout(null);
		
		basics = new GeneralInfoFramePanel_Basics();
		basics.setBounds(0, 0, 200, 100);
		
		controls = new GeneralInfoFramePanel_Controls();
		controls.setBounds(300, 0, 200, 50);
		
		pricechart = new GeneralInfoFramePanel_PriceChart();
		pricechart.setBounds(0, 100, 600, 200);
		
		ButtonControl();
		
		add(basics);
		add(controls);
		add(pricechart);
		
		validate();
		repaint();
	}
	
	private void ButtonControl() {
		//Connects to GeneralInfoFramePanel_Controls & GeneralInfoFramePanel_Basics
		controls.updatebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Global.SYMBOL = controls.symbolfield.getText();
				basics.renderPanel();
				pricechart.renderPanel();
			}
		});
	}
}

