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
					Global.weightpool.add(Double.valueOf(manager.motherpanel.addstockpanel.weightfield.getText()) / 100.d);
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
		
		this.manager.motherpanel.addstockpanel.clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Global.stockpool = new ArrayList<Stock>();
				Global.weightpool = new ArrayList<Double>();
				Global.weightleft = 100.0;
				Global.calculatingtimeslice = 30;
				Global.calculatingtype = "ARITHMETIC";
				
				Global.corr_column = null;
				Global.corr_data = null;
				
				
				portfolio = new Portfolio(Global.stockpool, Global.weightpool);
				manager.motherpanel.renderPanel();
				
				ActionControl();
			}
		});
		
		this.manager.motherpanel.addstockpanel.calculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int conflicts = 0;
				if(manager.motherpanel.addstockpanel.startdate.getText().equals("") ||
						manager.motherpanel.addstockpanel.enddate.getText().equals("")) {
					JFrame f = new JFrame();
					JOptionPane.showMessageDialog(f, "Specify the time window for calculating portfolio.");
					conflicts++;
				}
				if(conflicts > 0) {
					JFrame f = new JFrame();
					JOptionPane.showMessageDialog(f, conflicts + " Conflicts still remain check the fields");
				}
				if(conflicts == 0) {
					int TimeSlice = Integer.parseInt(manager.motherpanel.addstockpanel.timeslicebox.getSelectedItem().toString());
					String startdate = manager.motherpanel.addstockpanel.startdate.getText();
					String enddate = manager.motherpanel.addstockpanel.enddate.getText();
					String datatype = "";
					
					if(manager.motherpanel.addstockpanel.arithmeticbutton.isSelected()) {
						datatype = "ARITHMETIC";
					} else if(manager.motherpanel.addstockpanel.geometricbutton.isSelected()) {
						datatype = "GEOMETRIC";
					}
					System.out.println(Global.stockpool.get(0).SYMBOL + "  " + Global.stockpool.get(1).SYMBOL);
					System.out.println(Global.weightpool.get(0) + "    " + Global.weightpool.get(1));
					Global.portfolio = new Portfolio(Global.stockpool, Global.weightpool, TimeSlice, datatype, startdate, enddate);
					
					viewer.motherpanel.renderPanel();
					viewer.motherpanel.info.renderPanel();
				}
			}
		});
		
		this.manager.motherpanel.addstockpanel.correlate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Global.corr_data = new String[Global.stockpool.size()][Global.stockpool.size() + 1];
				Global.corr_column = new String[Global.stockpool.size() + 1];
				String startdate = manager.motherpanel.addstockpanel.startdate.getText();
				String enddate = manager.motherpanel.addstockpanel.enddate.getText();
				
				Global.corr_column[0] = "";
				for(int i = 1; i <= Global.stockpool.size(); i++) {
					Global.corr_column[i] = Global.stockpool.get(i - 1).SYMBOL;
				}
				
				
				for(int i = 0; i < Global.stockpool.size(); i++) {
					Global.corr_data[i][0] = Global.stockpool.get(i).SYMBOL;
				}
				
				ArrayList<ArrayList<Double>> tmpArr = new ArrayList<ArrayList<Double>>();
				for(int i = 0; i < Global.stockpool.size(); i++) {
					tmpArr.add(new Stock(Global.stockpool.get(i).SYMBOL).request("ADJ_CLOSE", startdate, enddate));
				}
				ArrayList<ArrayList<Double>> recArr = new ArrayList<ArrayList<Double>>();
				
				recArr = black.Stoculator.CorrelationMatrix(tmpArr, 1, "ARITHMETIC");
				
				for(int i = 0; i < Global.stockpool.size(); i++) {
					for(int j = 1; j < Global.stockpool.size() + 1; j++) {
						Global.corr_data[i][j] = recArr.get(i).get(j - 1).toString();
					}
				}
				
				correlation.motherpanel.renderPanel();
				
			}
		});
	}
}
