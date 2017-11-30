package portfolioFramework;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gray.Global;

/*
 * This class is not for showing. This class is a barrier between MotherFrame & Internal Frames under the roof.
 * When drawing frames under the roof of the father class, it must bypass this very class.
 */
public class PortfolioFrameWork_Father {
	public PortfolioManagerFrame_Mother manager;
	public PortfolioViewerFrame_Mother viewer;
	public PortfolioCorrelationFrame_Mother correlation;
	
	public PortfolioFrameWork_Father() {
		renderFrameWork();
	}
	

	public void renderFrameWork() {
		manager = new PortfolioManagerFrame_Mother();
		viewer = new PortfolioViewerFrame_Mother();
		correlation = new PortfolioCorrelationFrame_Mother();
		
		ActionControl();
	}
	
	public void ActionControl() {
		manager.motherpanel.addstockpanel.addstock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Global.weightleft = Global.weightleft - Double.valueOf(manager.motherpanel.addstockpanel.weightfield.getText());
				System.out.println(Global.weightleft + "");
				manager.motherpanel.renderPanel();
				
				renderFrameWork();
			}
		});
	}
}
