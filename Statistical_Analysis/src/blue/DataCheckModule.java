package blue;

import java.awt.Color;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import black.DataRetriever2;
import black.Utils;
import gray.Global;

public class DataCheckModule {
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JTextArea text;
	JScrollPane scroll;
	
	public DataCheckModule() {
		frame = new JFrame("Checker");
		createPanel();
		frame.setSize(400, 600);
		frame.add(scroll);
		
		
		frame.setVisible(true);
		
		checkDataIntact();
	}
	
	public void createPanel() {
		panel = new JPanel();
		text = new JTextArea();
		text.setEditable(false);
		text.setBackground(Color.BLACK);
		text.setForeground(Color.WHITE);
		//text.setLineWrap(true);
		scroll = new JScrollPane(text);
	}
	
	public void checkDataIntact() {
		text.append("====================Checking File System=================\n");
		//Checking Data Directory.
		File datadirectory = new File("./Data");
		if(!datadirectory.exists()) {
			text.append("Data Directory not present.\n");
			datadirectory.mkdirs();
			text.append("Created Data Directory\n");
		} else if(datadirectory.exists()){
			text.append("Data Directory present\n");
		}
		
		datadirectory = new File("./Data/Historical");
		if(!datadirectory.exists()) {
			text.append("Historical data directory not present\n");
			datadirectory.mkdir();
			text.append("Created historical data directory\n");
		} else if(datadirectory.exists()) {
			text.append("Historical data present\n");
		}
		
		datadirectory = new File("./DataMeta");
		if(!datadirectory.exists()) {
			text.append("MetaData Directory not present\n");
			datadirectory.mkdirs();
			text.append("MetaData Directory created\n");
		} else if(datadirectory.exists()) {
			text.append("MetaData Directory present\n");
		}
		
		datadirectory = new File("./DataMeta/Availability.csv");
		if(!datadirectory.exists()) {
			text.append("Availability file not present\n");
			UpdateControl.CreateAvailabilityFile();
			text.append("Availability file created\n");
		} else if(datadirectory.exists()) {
			text.append("Availability file present\n");
		}
		
		datadirectory = new File("./DataMeta/SYMBOLS");
		if(!datadirectory.exists()) {
			text.append("MetaData Symbol Directory not present\n");
			datadirectory.mkdirs();
			text.append("Symbol directory created\n");
			DataRetriever2.ReadTicker("NASDAQ");
			DataRetriever2.ReadTicker("NYSE");
			text.append("Symbols updated");
		} else if(datadirectory.exists()) {
			text.append("Symbol directory present\n");
			DataRetriever2.ReadTicker("NASDAQ");
			DataRetriever2.ReadTicker("NYSE");
			text.append("Symbols updated");
		}
		DataRetriever2.mergetickers();
		
		Global.Validation = true;
	}
}
