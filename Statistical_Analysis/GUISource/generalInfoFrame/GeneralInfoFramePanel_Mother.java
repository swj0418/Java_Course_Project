package generalInfoFrame;
import javax.swing.*;

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
		
		
		add(basics);
		add(controls);
	}
}
