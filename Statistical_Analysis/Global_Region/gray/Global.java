package gray;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import red.Stock;

import javax.swing.event.*;

import white.MotherFrame;

public class Global {
	public static String SYMBOL = "GOOG";
	public static MotherFrame motherframe;
	
	//Portfolio Global
	public static double weightleft = 100;
	public static ArrayList<Stock> stockpool;
	
	//Graph Global
	public static String GraphStart = "2016-01-01";
	public static String GraphEnd = "2017-10-01";
	public static String Graph_SYMBOL = "AAPL";
	
	public Global() {
		
	}
}
