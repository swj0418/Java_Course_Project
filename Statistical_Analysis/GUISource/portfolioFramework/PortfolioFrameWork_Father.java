package portfolioFramework;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JInternalFrame;

import black.Utils;
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
		this.manager = new PortfolioManagerFrame_Mother();
		this.viewer = new PortfolioViewerFrame_Mother();
		this.correlation = new PortfolioCorrelationFrame_Mother();
		
		ActionControl();
	}
	
	public void ActionControl() {
		this.manager.motherpanel.addstockpanel.addstock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Global.weightleft = Global.weightleft - Double.valueOf(manager.motherpanel.addstockpanel.weightfield.getText());
				Global.weightleft = Utils.SmallNumberHandler(Global.weightleft, 3);
				
				System.out.println(Global.weightleft + "");
				manager.motherpanel.renderPanel();
				
				//Of course you have to recursively add the actionlistener again after deleting everything and adding them all again.
				ActionControl();
			}
		});
	}
}
