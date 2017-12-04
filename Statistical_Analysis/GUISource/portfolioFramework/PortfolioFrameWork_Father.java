package portfolioFramework;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import org.jfree.data.statistics.Statistics;

import black.Utils;
import gray.Global;
import red.Portfolio;
import red.Stock;

/*
 * This class is not for showing. This class is a barrier between MotherFrame & Internal Frames under the roof.
 * When drawing frames under the roof of the father class, it must bypass this very class.
 */
public class PortfolioFrameWork_Father {
	public PortfolioManagerFrame_Mother manager;
	public PortfolioViewerFrame_Mother viewer;
	public PortfolioCorrelationFrame_Mother correlation;
	
	public Portfolio portfolio;
	
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
				if(new Stock(manager.motherpanel.addstockpanel.symbolfield.getText().toUpperCase()).Avail_Size == 0) {
					JFrame f = new JFrame();
					JOptionPane.showMessageDialog(f, "Wrong Symbol. Check your symbol availability");
				} else if(manager.motherpanel.addstockpanel.weightfield.getText().equals("")) {
					JFrame f = new JFrame();
					JOptionPane.showMessageDialog(f, "Did not specify the weight of the stock in your portfolio \n"
							+ "you have " + Global.weightleft + "% left");
				}
				else {
					Stock s = new Stock(manager.motherpanel.addstockpanel.symbolfield.getText().toUpperCase());
					
					Global.weightleft = Global.weightleft - Double.valueOf(manager.motherpanel.addstockpanel.weightfield.getText());
					Global.weightleft = Utils.SmallNumberHandler(Global.weightleft, 3);
					Global.stockpool.add(s);
					Global.weightpool.add(Double.valueOf(manager.motherpanel.addstockpanel.weightfield.getText()));
					Global.calculatingtimeslice = Integer.parseInt(manager.motherpanel.addstockpanel.timeslicebox.getSelectedItem().toString());
					
					System.out.println(Global.weightleft + "");
					manager.motherpanel.renderPanel();
					
					//Porfolio portion
					portfolio = new Portfolio(Global.stockpool, Global.weightpool);
					System.out.println(portfolio.PortfolioReturn);
					
					//Of course you have to recursively add the actionlistener again after deleting everything and adding them all again.
					ActionControl();
				}
			}
		});
		
		this.manager.motherpanel.addstockpanel.calculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String defaultstartdate = "2017-01-01";
				String defaultenddate = LocalDate.now().toString();
				int defaulttimeslice = 30;
				String defaulttype = "ARITHMETIC";
				
				Statistics stat;
				
			}
		});
		
		this.manager.motherpanel.addstockpanel.clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Global.stockpool = new ArrayList<Stock>();
				Global.weightpool = new ArrayList<Double>();
				Global.weightleft = 100.0;
				Global.calculatingtimeslice = 30;
				Global.calculatingtype = "ARITHMETIC";
				
				portfolio = new Portfolio(Global.stockpool, Global.weightpool);
				manager.motherpanel.renderPanel();
				
				ActionControl();
			}
		});
	}
}
