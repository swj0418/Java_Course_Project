package white;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MotherPanel extends JPanel{
	
	MainPanel_Mother mainpanelmother;
	GraphPanel_Mother graphpanelmother;
	GeneralInfo_Mother generalinfomother;
	
	JMenu mainpanelmothermenu;
	JMenu graphpanelmothermenu;
	JMenu generalinfomothermenu;
	
	JMenuItem mainpanelMI1;
	
	JMenuItem graphpanelMI1;
	JMenuItem graphpanelMI2;
	
	JMenuItem generalinfoMI1;
	JMenuItem generalinfoMI2;
	
	JMenuBar menubar;
	
	public MotherPanel() {
		setMenuBar();
	}
	
	private void setMenuBar() {
		menubar = new JMenuBar();
		
		mainpanelmothermenu = new JMenu("Main");
		graphpanelmothermenu = new JMenu("Graph");
		generalinfomothermenu = new JMenu("General Info");
		
		mainpanelMI1 = new JMenuItem("mainpanelMI1");
		
		graphpanelMI1 = new JMenuItem("graphpanelMI1");
		graphpanelMI2 = new JMenuItem("graphpanelMI2");
		generalinfoMI1 = new JMenuItem("generalinfoMI1");
		generalinfoMI2 = new JMenuItem("generalinfoMI2");
		
		
		
		
		
		mainpanelmothermenu.add(mainpanelMI1);
		mainpanelmothermenu.add(mainpanelMI2);
		
		graphpanelmothermenu.add(graphpanelMI1);
		graphpanelmothermenu.add(graphpanelMI2);
		
		generalinfomothermenu.add(generalinfoMI1);
		generalinfomothermenu.add(generalinfoMI2);
		
		menubar.add(mainpanelmothermenu);
		menubar.add(graphpanelmothermenu);
		menubar.add(generalinfomothermenu);
		
		add(menubar);
	}

}
