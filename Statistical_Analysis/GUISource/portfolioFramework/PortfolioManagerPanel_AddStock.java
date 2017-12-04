package portfolioFramework;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import gray.Global;

public class PortfolioManagerPanel_AddStock extends JPanel{
	JLabel symbol;
	JLabel weight;
	
	JTextField symbolfield;
	JTextField weightfield;
	
	JButton addstock;
	
	JLabel weightleft;
	
	//Settings
	JLabel timeslice;
	JComboBox timeslicebox;
	JLabel returntype;
	JRadioButton arithmeticbutton;
	JRadioButton geometricbutton;
	ButtonGroup typebuttongroup;
	
	JTextField startdate;
	JLabel to;
	JTextField enddate;
	
	JButton calculate;
	JButton correlate;
	JButton clear;
	
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
		timeslice = new JLabel("Time Slice : ");
		timeslicebox = new JComboBox(); timeslicebox.addItem("1");
										timeslicebox.addItem("10");
										timeslicebox.addItem("20");
										timeslicebox.addItem("30");
										timeslicebox.addItem("60");
										timeslicebox.addItem("90");
										timeslicebox.addItem("250");
										timeslicebox.addItem("365"); timeslicebox.setSelectedIndex(3);
										
		returntype = new JLabel("Return Type : ");
		arithmeticbutton = new JRadioButton("Arithmetic");
		geometricbutton = new JRadioButton("Geometric");
		typebuttongroup = new ButtonGroup(); typebuttongroup.add(arithmeticbutton);
											 typebuttongroup.add(geometricbutton);
											 
		startdate = new JTextField(); startdate.setText("2016-01-01");
		to = new JLabel(" ~ ");
		enddate = new JTextField(); enddate.setText("2017-10-01");
		
		calculate = new JButton("Calculate!");
		correlate = new JButton("Correlation Matrix");
		correlate.setBackground(Color.BLACK);
		correlate.setForeground(Color.WHITE);
		clear = new JButton("Clear");
		clear.setForeground(Color.RED);
		clear.setBackground(Color.BLACK);
		
		symbol.setBounds(0, 0, 100, 50);
		weight.setBounds(0, 50, 100, 50);
		symbolfield.setBounds(100, 0, 100, 50);
		weightfield.setBounds(100, 50, 100, 50);
		addstock.setBounds(0, 100, 200, 50);
		weightleft.setBounds(0, 150, 200, 50);
		
		timeslice.setBounds(0, 200, 100, 30);
		timeslicebox.setBounds(100, 200, 100, 30);
		returntype.setBounds(0, 230, 100, 50);
		arithmeticbutton.setBounds(100, 230, 100, 25);
		geometricbutton.setBounds(100, 255, 100, 25);
		startdate.setBounds(0, 280, 80, 30);
		to.setBounds(80, 280, 40, 30);
		enddate.setBounds(120, 280, 80, 30);
		
		calculate.setBounds(0, 320, 200, 30);
		correlate.setBounds(0, 350, 200, 30);
		clear.setBounds(0, 400, 200, 50);
		
		add(symbol);
		add(weight);
		add(symbolfield);
		add(weightfield);
		add(addstock);
		add(weightleft);
		add(timeslice);
		add(timeslicebox);
		add(returntype);
		add(arithmeticbutton);
		add(geometricbutton);
		add(startdate);
		add(to);
		add(enddate);
		
		add(calculate);
		add(correlate);
		add(clear);
		
		validate();
		repaint();
	}
}
