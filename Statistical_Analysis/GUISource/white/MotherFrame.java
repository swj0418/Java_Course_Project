package white;
import red.*;
import tickerLookUpFrame.TickerFrame_Mother;

import javax.swing.*;

import consoleFrame.ConsoleFrame_Mother;
import controlsFrame.ControlsFrame_Mother;
import gateFrame.GateFrame_Mother;
import generalInfoFrame.GeneralInfoFrame_Mother;
import graphFrame.GraphFramePanel_Mother;
import graphFrame.GraphFrame_Mother;
import gray.Global;
import portfolioFramework.PortfolioFrameWork_Father;
import portfolioFramework.PortfolioManagerFrame_Mother;
import portfolioFramework.PortfolioManagerPanel_Mother;

import java.awt.*;
import java.awt.event.*;

public class MotherFrame extends JFrame{
	JDesktopPane jdpDesktop;
	static int openframes = 0; // To keep track of how many frames are open at the moment
	
	public GateFrame_Mother gatemother;
	public GraphFrame_Mother graphmother;
	public GeneralInfoFrame_Mother generalinfomother;
	public ControlsFrame_Mother controlsmother; // 1 Approach and above 1
	public TickerFrame_Mother tickermother;  // 2 Approach 2
	public ConsoleFrame_Mother consolemother; // 2 Approach 2
	
	public PortfolioFrameWork_Father portfolioframework; // 3 Different Approach 3
	public PortfolioManagerFrame_Mother portfoliomanagermother;
	
	//Menu
	JMenu mainpanelmothermenu;
	JMenu graphpanelmothermenu;
	JMenu generalinfomothermenu;
	JMenu controlsmothermenu;
	JMenu tickerframemothermenu;
	JMenu consoleframemothermenu;
	JMenu portfolioframemothermenu;
	
	//Menu items
	JMenuItem menuitem_gate;
	
	JMenuItem pricegraph;
	JMenuItem graphpanelMI2;
	
	JMenuItem generalinfoMI1;
	JMenuItem generalinfoMI2;
	
	JMenuItem tickersearch;
	
	JMenuItem console;
	
	JMenuItem controller;
	
	JMenuItem portfoliomanager;
	JMenuItem portfolioviewer;
	JMenuItem portfoliocorrelation;
	
	//Menubar & etc...
	JMenuBar menubar;
	
	BorderLayout borderlayout;
	
	public MotherFrame() {
		setMotherFrame();
		createMothers();
		
		createFrameWork();
		
		createMenuBar();
		addMenuBar();
		
		//Internal Frames Configuration
		setInternalFramework();
		createDefaultInternalFrames();
		
		Graph_General_Bridge();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void createMothers() {
		generalinfomother = new GeneralInfoFrame_Mother();
		graphmother = new GraphFrame_Mother();
		tickermother = new TickerFrame_Mother();
		gatemother = new GateFrame_Mother();
		consolemother = new ConsoleFrame_Mother();
	}
	
	private void createDefaultInternalFrames() {
		jdpDesktop.add(gatemother);
		jdpDesktop.add(consolemother);
		jdpDesktop.add(generalinfomother);
		jdpDesktop.add(tickermother);
		jdpDesktop.add(graphmother);
	}
	
	private void setInternalFramework() {
		jdpDesktop = new JDesktopPane();
		setContentPane(jdpDesktop);
		
		jdpDesktop.putClientProperty("JDesktopPane.dragMode", "outline");
	}
	
	private void setMotherFrame() {
		borderlayout = new BorderLayout();
		setLayout(borderlayout);
		this.setBounds(0, 0, 1600, 900);
		this.setVisible(true);
	}
	
	private void addMenuBar() {
		add(menubar, BorderLayout.NORTH);
		setJMenuBar(menubar);
	}
	
	private void createMenuBar() {
		menubar = new JMenuBar();
		
		//Create Menu
		mainpanelmothermenu = new JMenu("Main");
		graphpanelmothermenu = new JMenu("Graph");
		generalinfomothermenu = new JMenu("General Info");
		controlsmothermenu = new JMenu("Control Center");
		tickerframemothermenu = new JMenu("Ticker Search");
		consoleframemothermenu = new JMenu("Console");
		portfolioframemothermenu = new JMenu("Portfolio");
		
		
		//Create menuitems
		menuitem_gate = new JMenuItem("Gate");
		menuitem_gate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Creating GateFrame_Mother");
				if(e.getSource() != null) {
					jdpDesktop.add(new GateFrame_Mother());
				}
			}
		});
		
		
		pricegraph = new JMenuItem("Price Graph");
		pricegraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jdpDesktop.add(graphmother);
			}
		});
		graphpanelMI2 = new JMenuItem("graphpanelMI2");
		
		generalinfoMI1 = new JMenuItem("General Stock Information");
		generalinfoMI1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Creating GeneralInfoFrame_Mother");
				jdpDesktop.add(generalinfomother);
				//Graph_General_Bridge();
			}
		});
		generalinfoMI2 = new JMenuItem("Price Information");
		
		controller = new JMenuItem("Controller");
		
		tickersearch = new JMenuItem("Ticker Search");
		tickersearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Creating TickerFrame_Mother");
				jdpDesktop.add(tickermother);
			}
		});
		
		console = new JMenuItem("Console");
		console.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Creating ConsoleFrame_Mother");
				jdpDesktop.add(new ConsoleFrame_Mother());
			}
		});
		
		portfoliomanager = new JMenuItem("Portfolio Manager");
		portfoliomanager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Creating PorfolioManagerFrame_Mother");
				jdpDesktop.add(portfolioframework.manager, 0);
			}
		});
		portfolioviewer = new JMenuItem("Portfolio Viewer");
		portfolioviewer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Creating Portfolio Viewer");
				jdpDesktop.add(portfolioframework.viewer, 0);
			}
		});
		portfoliocorrelation = new JMenuItem("Correlation");
		portfoliocorrelation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Creating Correlation Matrix");
				jdpDesktop.add(portfolioframework.correlation, 0);
			}
		});
		
		
		//Add menuitems to menu.
		mainpanelmothermenu.add(menuitem_gate);
		
		graphpanelmothermenu.add(pricegraph);
		graphpanelmothermenu.add(graphpanelMI2);
		
		generalinfomothermenu.add(generalinfoMI1);
		generalinfomothermenu.add(generalinfoMI2);
		
		controlsmothermenu.add(controller);
		
		tickerframemothermenu.add(tickersearch);
		
		consoleframemothermenu.add(console);
		
		portfolioframemothermenu.add(portfoliomanager);
		portfolioframemothermenu.add(portfolioviewer);
		portfolioframemothermenu.addSeparator();
		portfolioframemothermenu.add(portfoliocorrelation);
		
		//Add menu to the menubar
		menubar.add(mainpanelmothermenu);
		menubar.add(graphpanelmothermenu);
		menubar.add(generalinfomothermenu);
		menubar.add(controlsmothermenu);
		menubar.add(tickerframemothermenu);
		menubar.add(consoleframemothermenu);
		menubar.add(portfolioframemothermenu);
	}
	
	public void createFrameWork() {
		portfolioframework = new PortfolioFrameWork_Father();
	}
	
	public void Graph_General_Bridge() {
		generalinfomother.motherpanel.controls.updatebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Global.SYMBOL = generalinfomother.motherpanel.controls.symbolfield.getText();
				Global.Graph_SYMBOL = generalinfomother.motherpanel.controls.symbolfield.getText();
				
				generalinfomother.motherpanel.basics.renderPanel();
				generalinfomother.motherpanel.pricechart.renderPanel();
				graphmother.motherpanel.renderpanel();
				
				Graph_General_Bridge();
			}
		});
	}
}
