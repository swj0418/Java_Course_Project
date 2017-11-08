package stat_c;
import java.util.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.*;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
 * USAGE:
 * 		request in "UPPERCASE"
 * 		I will fix this problem later
 * 		seems like toUpperCase() is not working properly.
 */

public class Stock {
	public String SYMBOL;
	public Boolean localAvailability = false;
	public String Stock_Name;
	public Integer Avail_Size;
	public String LastDate;
	public String FirtstDate;
	
	ArrayList Total = new ArrayList();
	public ArrayList<Double> Close = new ArrayList<Double>();
	public ArrayList<String> Date = new ArrayList<String>();
	public ArrayList<Double> Open = new ArrayList<Double>();
	public ArrayList<Double> Volume = new ArrayList<Double>();
	public ArrayList<Double> High = new ArrayList<Double>();
	public ArrayList<Double> Low = new ArrayList<Double>();
	
	private ArrayList<String> list = new ArrayList<String>();
	
	public Map<String, Double> Close_M = new HashMap<String, Double>();
	public Map<String, Double> Open_M = new HashMap<String, Double>();
	public Map<String, Double> Volume_M = new HashMap<String, Double>();
	public Map<String, Double> High_M = new HashMap<String, Double>();
	public Map<String, Double> Low_M = new HashMap<String, Double>();
	public Map<String, String> DATE_M = new HashMap<String, String>(); //For the ease of printing dates
	
	public Stock() {
		
	}
	public Stock(String SYMBOL) {
		this.SYMBOL = SYMBOL;
	}
	
	/*
	 * Connection Methods
	 * So far,
	 * AlphaVantage only ==> Too slow
	 */
	@SuppressWarnings("unchecked")
	public void retrieve() {
		URL url;
	    URLConnection urlConn = null;
	    BufferedReader br = null;
		String msg;
		StringBuilder bd;
		InputStream ins = null;
		
		String line = "";
		String csvSplitBy = ",";
		
		//Using AlphaVantage API to retrieve information
		String urlString = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" +
				SYMBOL +"&apikey=QX75TG29OOA9H7CJ&datatype=csv&outputsize=full";
		
		//Connecting to AlphaVantage with my apikey
		try {
			url = new URL(urlString);
			urlConn = url.openConnection();
			ins = url.openStream();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			br = new BufferedReader(new InputStreamReader(ins));
			bd = new StringBuilder();
			msg = null;
			try {
				int u = 0; // This variable is just for skipping the first line of the data which all are strings
				while((msg = br.readLine()) != null)
				{
					String tmpList[] = msg.split(csvSplitBy);
					
					bd.append(msg);
					Total.add(msg);
					Total.add("\n");
					bd.append("\n");
					
					list.add(tmpList[0]); 
					list.add(tmpList[1]); 
					list.add(tmpList[2]); 
					list.add(tmpList[3]); 
					list.add(tmpList[4]);
					list.add(tmpList[5]);
					if(u != 0) {
						Close_M.put(tmpList[0], Double.valueOf(tmpList[4]));
						Open_M.put(tmpList[0], Double.valueOf(tmpList[1]));
						Volume_M.put(tmpList[0], Double.valueOf(tmpList[5]));
						High_M.put(tmpList[0], Double.valueOf(tmpList[2]));
						Low_M.put(tmpList[0], Double.valueOf(tmpList[3]));
						DATE_M.put(tmpList[0], tmpList[0]);
					}
					u++;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				System.out.println("Retrieval Done");
			}
			
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		saveCSV(); //Saving data as a file
		Extractor(); //Extracting and saving separated datasets in an instance
		recordLocal();
	}
	/*
	 * Data saving and manipulating methods.
	 */
	private void saveCSV() {
		/*
		 * File being saved from the latest info, to be last info.
//		 * Should I reverse?
		 */
		FileWriter fw;
		String path = "./Data/" + SYMBOL.trim(); // trim is crucial for that a user might accidentally add space
		
		//Batch save. Retrieval methods will be also added for the ease of use.
		File file = new File("./Data/"); // CSV form
		if(file.exists() != true) {
			file.mkdirs();
			file = new File("./Data/" + SYMBOL + ".csv");
		} else {
			file = new File("./Data/" + SYMBOL + ".csv");
		}
		
		//Core writing
		try {
			fw = new FileWriter(file);
			int blocksize = 6; // Might need to be adjusted when data source is changed
			int j = 1;
			//System.out.println(list.size());
			for(int i = 0; i < list.size(); i++)
			{
				fw.write(list.get(i));
				fw.write(",");
				
				if(i == 5)
				{
					j++;
					fw.write(",");
					fw.write("\n");
				}
				
				else if(i == 6 * j - 1)
				{
					j++;
					fw.write(",");
					fw.write("\n");
				}
			}
			
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Data Accessing Methods
	 * Now that all the data has been saved before having to be accessed,
	 * Data accessing will be done by accessing those files that have been saved in the directory
	 * I am thinking a method call design to be like the below
	 * Stock("AAPL").Retrieve("Close", "2014-01-01", "2017-01-01");
	 * than the retrieve method returns "Close" data starting from 2014-01-01, until "2017-01-01"
	 * Return type... maybe the basic String[] ?
	 * Not sure yet.
	 */
	
	public ArrayList request(String category, String start, String end) {
		ArrayList return_arr = new ArrayList();
		//Date begin = null; begin.parse(start); Deprecated?
		//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		
		/*
		 * To be perfect, I have to make a relational database, but if the data source is perfect,
		 * I don't have to. I just can match the date and the cat(Category).
		 */
		
		LocalDate time = null;
		LocalDate begin = LocalDate.parse(start, DateTimeFormatter.ISO_DATE); //Correct
		LocalDate fin = LocalDate.parse(end, DateTimeFormatter.ISO_DATE);
		String cat = category.toUpperCase();
		if(cat == "CLOSE") {
			while(! begin.isAfter(fin)) {
				if(Close_M.get(begin.toString()) != null) {
					return_arr.add(Close_M.get(begin.toString()));
				}
				begin = begin.plusDays(1);
			}
			return return_arr;
		} else if (cat == "HIGH") {
			while(! begin.isAfter(fin)) {
				if(High_M.get(begin.toString()) != null) {
					return_arr.add(High_M.get(begin.toString()));
				}
				begin = begin.plusDays(1);
			}
			return return_arr;
			
		} else if (cat == "LOW") {
			while(! begin.isAfter(fin)) {
				if(Low_M.get(begin.toString()) != null) {
					return_arr.add(Low_M.get(begin.toString()));
				}
				begin = begin.plusDays(1);
			}
			return return_arr;
			
		} else if (cat == "OPEN") {
			while(! begin.isAfter(fin)) {
				if(Open_M.get(begin.toString()) != null) {
					return_arr.add(Open_M.get(begin.toString()));
				}
				begin = begin.plusDays(1);
			}
			return return_arr;
			
		} else if (cat == "VOLUME") {
			while(! begin.isAfter(fin)) {
				if(Volume_M.get(begin.toString()) != null) {
					return_arr.add(Volume_M.get(begin.toString()));
				}
				begin = begin.plusDays(1);
			}
			return return_arr;
		} else if (cat == "DATE") {
			while(! begin.isAfter(fin)) {
				if(DATE_M.get(begin.toString()) != null) {
					return_arr.add(DATE_M.get(begin.toString()));
				}
				begin = begin.plusDays(1);
			}
			return return_arr;
		} else {
			// May need to add more if date category increases
			System.out.println("No such data");
			return return_arr;
		}
	}
	
	
	
	/*
	 * Data Carving Methods
	 */
	
	private void Extractor() { //This method extracts sub data. Will be called within the method retrieve
		for(int i = 6; i < list.size(); i++) { // "i" here must be changed when using another data or on a data size
											   // The reason i starts from 6 is because the first 6 elements are indexes thus they are string
			if(i % 6 == 0)
			{
				Date.add(list.get(i).toString());	
			} else if (i % 6 == 1) {
				Open.add(Double.valueOf(list.get(i)));
			} else if(i % 6 == 2) {
				High.add(Double.valueOf(list.get(i)));
			} else if(i % 6 == 3) {
				Low.add(Double.valueOf(list.get(i)));
			} else if (i % 6 == 4) {
				Close.add(Double.valueOf(list.get(i)));
			} else if (i % 6 == 5) {
				Volume.add(Double.valueOf(list.get(i)));
			}
		}
	}
	
	/*
	 * Local Data Availability Check
	 */
	public void recordLocal() {
		File file = new File("./DataMeta");
		if(file.exists() != true) {
			file.mkdir();
			recordLocal(); //Call again
		} else if(file.exists()) {
			file = new File("./DataMeta/Availability.csv"); // 0 : Symbol 1 : fisrt available date 2 : last available date
			try {
				FileOutputStream fos = new FileOutputStream(file);
				FileWriter fw = new FileWriter(file);
				if(checkLocal() == false) {
					fw.write(SYMBOL);
				} else if(checkLocal() == true) {
					return;
				}
				
				fw.close();
			} catch (Exception e) { e.printStackTrace();}
		} 
	}
	public Boolean checkLocal() {
		File file = new File("./DataMeta");
		if(file.exists() != true) {
			file.mkdir();
			recordLocal(); //Call again
		} else if(file.exists()) {
			file = new File("./DataMeta/Availability.csv"); // 0 : Symbol 1 : fisrt available date 2 : last available date
			try {
				FileReader fr = new FileReader(file);
				FileInputStream fis = new FileInputStream(file);
				StringBuilder builder = new StringBuilder();
				String line = null;
				BufferedReader br = new BufferedReader(new InputStreamReader(fis));
				while((line = br.readLine()) != null) {
					String tmp[] = line.split(",");
					for(int i = 0; i < tmp.length; i++) {
						if(tmp[0] == SYMBOL) {
							localAvailability = true;
							return true;
						} else {
							localAvailability = false;
							return false;
						}
					}
				}
			} catch (Exception e) { e.printStackTrace();
			}
			
		}
		return localAvailability;
	}
	
	
	/*
	 * Printing Methods
	 */
	public void printRaw() {
		for(int i = 0; i < Total.size(); i++) {
			System.out.println(Total.get(i));
		}
	}
	public void printDate() {
		for(int i = 0; i < Date.size(); i++) {
			System.out.println(Date.get(i));
		}
	}
	public void printClose() {
		for(int i = 0; i < Close.size(); i++) {
			System.out.println(Date.get(i) + " ::::: " + Close.get(i));
		}
	}
	public void printVolume() {
		for(int i = 0; i < Volume.size(); i++) {
			System.out.println(Date.get(i) + " ::::: " + Volume.get(i));
		}
	}
	public void printHigh() {
		for(int i = 0; i < High.size(); i++) {
			System.out.println(Date.get(i) + " ::::: " + High.get(i));
		}
	}
	public void printLow() {
		for(int i = 0; i < Low.size(); i++) {
			System.out.println(Date.get(i) + " ::::: " + Low.get(i));
		}
	}
}
