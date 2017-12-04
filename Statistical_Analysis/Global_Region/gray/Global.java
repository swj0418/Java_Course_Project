package gray;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import red.Portfolio;
import red.Stock;

import javax.swing.event.*;

import white.MotherFrame;

public class Global {
	public static Boolean Validation = false;
	
	public static String SYMBOL = "GOOG";
	public static MotherFrame motherframe;
	
	//Portfolio Global
	public static Portfolio portfolio = new Portfolio();
	public static double weightleft = 100.0d;
	public static ArrayList<Stock> stockpool = new ArrayList<Stock>();
	public static ArrayList<Double> weightpool = new ArrayList<Double>();
	public static int calculatingtimeslice = 30;
	public static String calculatingtype = "ARITHMETIC";
	
	//Portfolio Correlation Global
	public static String[][] corr_data = {{"AAPL", "1.0", "0.7"}, {"TSLA", "0.7", "1.0"}};
	public static String[] corr_column = {" ", "AAPL", "TSLA"};
	
	//Graph Global
	public static String GraphStart = "2017-01-01";
	public static String GraphEnd = "2017-11-30";
	public static String Graph_SYMBOL = "AAPL";
	
	//Graph Compare Global
	public static String Compare_SYMBOL = "GOOG";
	
	public Global() {
		
	}
}
