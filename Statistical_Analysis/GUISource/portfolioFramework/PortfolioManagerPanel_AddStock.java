package portfolioFramework;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gray.Global;

public class PortfolioManagerPanel_AddStock extends JPanel{
	JLabel symbol;
	JLabel weight;
	
	JTextField symbolfield;
	JTextField weightfield;
	
	JButton addstock;
	
	JLabel weightleft;
	
	PortfolioManagerPanel_AddStock() {
		renderPanel();
	}
	
	public void renderPanel() {
		removeAll();
		
		setLayout(null);
		
		symbol = new JLabel("Symbol : ");
		weight = new JLabel("Weight (in %) : ");
		symbolfield = new JTextField();
		weightfield = new JTextField();
		addstock = new JButton("Add Stock");
		weightleft = new JLabel("Weights left : " + Global.weightleft + "%");
		
		symbol.setBounds(0, 0, 100, 50);
		weight.setBounds(0, 50, 100, 50);
		symbolfield.setBounds(100, 0, 100, 50);
		weightfield.setBounds(100, 50, 100, 50);
		addstock.setBounds(0, 100, 200, 50);
		weightleft.setBounds(0, 150, 200, 50);
		
		add(symbol);
		add(weight);
		add(symbolfield);
		add(weightfield);
		add(addstock);
		add(weightleft);
		
		validate();
		repaint();
	}
}
