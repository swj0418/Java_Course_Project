package generalInfoFrame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import gray.Global;

public class GeneralInfoFramePanel_Controls extends JPanel {
	JLabel symboltolookfor;
	public JTextField symbolfield;
	public JButton updatebutton;
	
	GridLayout layout = new GridLayout();
	GeneralInfoFramePanel_Controls() {
		renderPanel();
	}
	
	public void renderPanel() {
		removeAll();
		
		layout.setColumns(2);
		layout.setRows(2);
		setLayout(layout);
		
		symboltolookfor = new JLabel("Find by Symbol : ");
		symbolfield = new JTextField();
		updatebutton = new JButton("Search!");
		
		add(symboltolookfor); add(symbolfield);
		add(updatebutton);
		
		validate();
		repaint();
	}
}
