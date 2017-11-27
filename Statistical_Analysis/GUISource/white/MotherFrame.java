package white;
import red.*;


import javax.swing.*;

import gateFrame.GateFrame_Mother;
import generalInfoFrame.GeneralInfo_Mother;
import graphFrame.GraphFrame_Mother;

import java.awt.*;
import java.awt.event.*;

public class MotherFrame extends JFrame{
	JDesktopPane jdpDesktop;
	static int openframes = 0;
	
	
	GateFrame_Mother mainpanelmother;
	GraphFrame_Mother graphpanelmother;
	GeneralInfo_Mother generalinfomother;
	
	JMenu mainpanelmothermenu;
	JMenu graphpanelmothermenu;
	JMenu generalinfomothermenu;
	
	JMenuItem menuitem_gate;
	
	JMenuItem graphpanelMI1;
	JMenuItem graphpanelMI2;
	
	JMenuItem generalinfoMI1;
	JMenuItem generalinfoMI2;
	
	JMenuBar menubar;
	
	BorderLayout borderlayout;
	
	public MotherFrame() {
		setMotherFrame();
		createMenuBar();
		addMenuBar();
		setInternalFramework();
		createDefaultInternalFrames();
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void createDefaultInternalFrames() {
		jdpDesktop.add(new GateFrame_Mother());
	}
	
	private void setInternalFramework() {
		jdpDesktop = new JDesktopPane();
		setContentPane(jdpDesktop);
		
		jdpDesktop.putClientProperty("JDesktopPane.dragMode", "outline");
	}
	
	private void setMotherFrame() {
		borderlayout = new BorderLayout();
		setLayout(borderlayout);
		setSize(1024, 768);
		this.setBounds(0, 0, 1600, 900);
		this.setVisible(true);
	}
	
	private void addMenuBar() {
		add(menubar, BorderLayout.NORTH);
		setJMenuBar(menubar);
	}
	
	private void createMenuBar() {
		menubar = new JMenuBar();
		
		mainpanelmothermenu = new JMenu("Main");
		graphpanelmothermenu = new JMenu("Graph");
		generalinfomothermenu = new JMenu("General Info");
		
		menuitem_gate = new JMenuItem("Gate");
		menuitem_gate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Creating GateFrame_Mother");
				new GateFrame_Mother();
			}
		});
		
		
		graphpanelMI1 = new JMenuItem("graphpanelMI1");
		graphpanelMI2 = new JMenuItem("graphpanelMI2");
		
		generalinfoMI1 = new JMenuItem("generalinfoMI1");
		generalinfoMI2 = new JMenuItem("generalinfoMI2");
				
		mainpanelmothermenu.add(menuitem_gate);
		
		graphpanelmothermenu.add(graphpanelMI1);
		graphpanelmothermenu.add(graphpanelMI2);
		
		generalinfomothermenu.add(generalinfoMI1);
		generalinfomothermenu.add(generalinfoMI2);
		
		menubar.add(mainpanelmothermenu);
		menubar.add(graphpanelmothermenu);
		menubar.add(generalinfomothermenu);
		
	}
}
