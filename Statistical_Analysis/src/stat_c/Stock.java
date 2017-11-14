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
	public Boolean UpdateNeeds = false;
	public String Stock_Name;
	public Integer Avail_Size;
	public String LastDate;
	public String FirstDate;
	
	ArrayList Total = new ArrayList();
	public ArrayList<Double> Close = new ArrayList<Double>();		
	public ArrayList<String> Date = new ArrayList<String>();
	public ArrayList<Double> Open = new ArrayList<Double>();
	public ArrayList<Double> Volume = new ArrayList<Double>();
	public ArrayList<Double> High = new ArrayList<Double>();
	public ArrayList<Double> Low = new ArrayList<Double>();
	public ArrayList<Double> Adj_Close = new ArrayList<Double>();
	
	private ArrayList<String> list = new ArrayList<String>();
	
	public Map<String, Double> Adj_Close_M = new HashMap<String, Double>();
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
		checkLocal(); //First, check if the data is there and is up-to-date.
		
		if(localAvailability != true) {
			URL url;
		    URLConnection urlConn = null;
		    BufferedReader br = null;
			String msg;
			StringBuilder bd;
			InputStream ins = null;
			
			String line = "";
			String csvSplitBy = ",";
			
			//Using AlphaVantage API to retrieve information
			
			/*
			String urlString = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" +
					SYMBOL +"&apikey=QX75TG29OOA9H7CJ&datatype=csv&outputsize=full";
			*/
			String urlString = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=" +
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
						
						list.add(tmpList[0]); //Date
						list.add(tmpList[1]); //Open
						list.add(tmpList[2]); //High
						list.add(tmpList[3]); //Low
						list.add(tmpList[4]); //Close
						list.add(tmpList[5]); //Volume
						list.add(tmpList[6]); //Adjusted Close
						list.add(tmpList[7]); //stock split event
						if(u != 0) {
							Close_M.put(tmpList[0], Double.valueOf(tmpList[4]));
							Open_M.put(tmpList[0], Double.valueOf(tmpList[1]));
							Volume_M.put(tmpList[0], Double.valueOf(tmpList[6]));
							High_M.put(tmpList[0], Double.valueOf(tmpList[2]));
							Low_M.put(tmpList[0], Double.valueOf(tmpList[3]));
							DATE_M.put(tmpList[0], tmpList[0]);
							Adj_Close_M.put(tmpList[0], Double.valueOf(tmpList[5]));
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
		
		else if(localAvailability == true) {
			File file = new File("./Data/" + SYMBOL + ".csv");
			String msg = null;
			BufferedReader br = null;
			try {
				FileReader fr = new FileReader(file);
				FileInputStream fis = new FileInputStream(file);
				br = new BufferedReader(new InputStreamReader(fis));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally {
				try {
					int u = 0; // This variable is just for skipping the first line of the data which all are strings
					while((msg = br.readLine()) != null)
					{
						String tmpList[] = msg.split(",");
						Total.add(msg);
						Total.add("\n");

						list.add(tmpList[0]); 
						list.add(tmpList[1]); 
						list.add(tmpList[2]); 
						list.add(tmpList[3]); 
						list.add(tmpList[4]);
						list.add(tmpList[5]); //Adj Close
						list.add(tmpList[6]); //Volume
						list.add(tmpList[7]); //stock split event
						if(u != 0) {
							Close_M.put(tmpList[0], Double.valueOf(tmpList[4]));
							Open_M.put(tmpList[0], Double.valueOf(tmpList[1]));
							Volume_M.put(tmpList[0], Double.valueOf(tmpList[6]));
							High_M.put(tmpList[0], Double.valueOf(tmpList[2]));
							Low_M.put(tmpList[0], Double.valueOf(tmpList[3]));
							DATE_M.put(tmpList[0], tmpList[0]);
							Adj_Close_M.put(tmpList[0], Double.valueOf(tmpList[5]));
						}
						u++;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					System.out.println("Loading " + SYMBOL + " Done");
				}
				Extractor(); //Extracting and saving separated datasets in an instance
			}
			}
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
			int blocksize = 8; // Might need to be adjusted when data source is changed
			/*
			 * Block size 8 for call with adjusted close data
			 * Block size 6 for a call without any adjusted close data
			 */
			int j = 1;
			//System.out.println(list.size());
			for(int i = 0; i < list.size(); i++)
			{
				fw.write(list.get(i));
				fw.write(",");
				
				if(i == 7)
				{
					j++;
					fw.write(",");
					fw.write("\n");
				}
				
				else if(i == 8 * j - 1)
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
		} else if (cat == "ADJ_CLOSE") {
			while(! begin.isAfter(fin)) {
				if(Adj_Close_M.get(begin.toString()) != null) {
					return_arr.add(Adj_Close_M.get(begin.toString()));
				}
				begin = begin.plusDays(1);
			}
			return return_arr;
		} 
		else {
			// May need to add more if date category increases
			System.out.println("No such data");
			return return_arr;
		}
	}
	
	
	
	/*
	 * Data Carving Methods
	 */
	
	private void Extractor() { //This method extracts sub data. Will be called within the method retrieve
		for(int i = 8; i < list.size(); i++) { // "i" here must be changed when using another data or on a data size
											   // The reason i starts from 6 is because the first 6 elements are indexes thus they are string
			if(i % 8 == 0)
			{
				Date.add(list.get(i).toString());	
			} else if (i % 8 == 1) {
				Open.add(Double.valueOf(list.get(i)));
			} else if(i % 8 == 2) {
				High.add(Double.valueOf(list.get(i)));
			} else if(i % 8 == 3) {
				Low.add(Double.valueOf(list.get(i)));
			} else if (i % 8 == 4) {
				Close.add(Double.valueOf(list.get(i)));
			} else if (i % 8 == 5) {
				Adj_Close.add(Double.valueOf(list.get(i)));
			} else if (i % 8 == 6) {
				Volume.add(Double.valueOf(list.get(i)));
			} else if (i % 8 == 7) {
				continue;
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
		} else if(file.exists() == true) {
			file = new File("./DataMeta/Availability.csv"); // 0 : Symbol 1 : fisrt available date 2 : last available date
			try {
				//FileOutputStream fos = new FileOutputStream(file); //It is this fileoutputstream that forcedly overwrites on a file
				//Remember not to make the same mistake
				FileWriter fw = new FileWriter(file, true);
				BufferedWriter buffw = new BufferedWriter(fw);
				if(localAvailability == false && UpdateNeeds != true) {
					LocalDate D = null; D.parse(D.now().toString(), DateTimeFormatter.ISO_DATE);
					fw.append(SYMBOL + "," + D.now().toString() + "," + this.Date.get(Date.size() - 1) + "\n");
					FirstDate = this.Date.get(0);
					LastDate = this.Date.get(Date.size() - 1);
				} else if(localAvailability == true && UpdateNeeds == false) {
					System.out.println("Local File Available");
				} else if(localAvailability == false && UpdateNeeds == true) { //When only updating the last date of data availability
					FileReader fr = new FileReader(file);
					FileInputStream fis = new FileInputStream(file);
					String line = null;
					BufferedReader br = new BufferedReader(new InputStreamReader(fis));
					while((line = br.readLine()) != null) {
						String[] tmpLine = line.split(",");
						if(tmpLine[0].equals(SYMBOL)) {
							LocalDate D = null; D.parse(D.now().toString(), DateTimeFormatter.ISO_DATE);
							fw = new FileWriter(file, false); // Renewing the file... I can't come up with a better solution.
							fw.write(SYMBOL + "," + D.now().toString() + "," + this.Date.get(Date.size() - 1) + "\n");
							FirstDate = this.Date.get(0);
							LastDate = this.Date.get(Date.size() - 1);
							// LastDate = D.toString(); should I use this as the last date..?
							System.out.println(tmpLine[0] + "   " + tmpLine[1] + "   " + tmpLine[2]);
						}
					}
				}
				fw.close();
			} catch (Exception e) { e.printStackTrace();}
		} 
	}
	
	public Boolean checkLocal() {
		File file = new File("./DataMeta");
		if(file.exists() != true) {
			file.mkdir();
		} else if(file.exists() == true) {
			file = new File("./DataMeta/Availability.csv"); // 0 : Symbol 1 : fisrt available date 2 : last available date
			try {
				FileReader fr = new FileReader(file);
				FileInputStream fis = new FileInputStream(file);
				String line = null;
				BufferedReader br = new BufferedReader(new InputStreamReader(fis));
				
				LocalDate checkdate = null;
				checkdate.parse(checkdate.now().toString(), DateTimeFormatter.ISO_DATE);
				
				while((line = br.readLine()) != null) {
					String tmp[] = line.split(",");
					if(tmp[0].equals(SYMBOL) && checkdate.now().toString().equals(tmp[1]) == true) { // tmp[0] == SYMBOL did not work. Remember not to make the same mistake
																							   //Also, I have to find out a way to know which date is the latest date for stock information
						localAvailability = true;
						UpdateNeeds = false;
						System.out.println(tmp[0] + " First Data : " + tmp[2] + " Last Data : " + tmp[1]);
						break;
					} else if (tmp[0].equals(SYMBOL) != true){
						continue;
					} else if (tmp[0].equals(SYMBOL) == true && checkdate.now().toString().equals(tmp[1]) != true) {
						localAvailability = false;
						UpdateNeeds = true;
						break;
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
	public void printAdjClose() {
		for(int i = 0; i < Adj_Close.size(); i++) {
			System.out.println(Date.get(i) + " ::::: " + Adj_Close.get(i));
		}
	}
}
