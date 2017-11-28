package generalInfoFrame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import gray.Global;

public class GeneralInfoFramePanel_Controls extends JPanel {
	JLabel symboltolookfor = new JLabel("Find by Symbol : ");
	JTextField symbolfield = new JTextField();
	JButton updatebutton = new JButton("Search!");
	
	GridLayout layout = new GridLayout();
	GeneralInfoFramePanel_Controls() {
		layout.setColumns(2);
		layout.setRows(2);
		setLayout(layout);
		
		add(symboltolookfor); add(symbolfield);
		add(updatebutton);
	}
}