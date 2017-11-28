package generalInfoFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import gray.Global;
import red.Stock;

public class GeneralInfoFramePanel_Mother extends JPanel {
	GeneralInfoFramePanel_Basics basics;
	GeneralInfoFramePanel_Controls controls;
	GeneralInfoFramePanel_LatestData latest;
	GeneralInfoFramePanel_PriceChart pricechart;
	
	GeneralInfoFramePanel_Mother() {
		setLayout(null);
		
		basics = new GeneralInfoFramePanel_Basics();
		basics.setBounds(0, 0, 200, 100);
		
		controls = new GeneralInfoFramePanel_Controls();
		controls.setBounds(300, 0, 200, 50);
		
		pricechart = new GeneralInfoFramePanel_PriceChart();
		pricechart.setBounds(0, 100, 500, 300);
		
		ButtonControl();
		
		add(basics);
		add(controls);
		add(pricechart);
	}
	
	private void ButtonControl() {
		//Connects to GeneralInfoFramePanel_Controls & GeneralInfoFramePanel_Basics
		controls.updatebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Global.SYMBOL = controls.symbolfield.getText();
				basics.renderPanel();
			}
		});
	}
}
