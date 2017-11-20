package red;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Stock {
	String SYMBOL;
	Boolean LocalAvailability = false;
	Boolean UpdateNeeds = false;
	String StockName;
	Integer Avail_Size;
	String StartDate;
	String FirstDate;
	
	ArrayList<BasicData> BasicData = new ArrayList<BasicData>();
	
	public 
	
	public Stock() {
		
	}
	
	public Stock(String Symbol) {
		this.SYMBOL = Symbol;
	}
	
	private void Connect() {
		checkLocal(); //First, check if the data is there and is up-to-date.
		
		if(LocalAvailability != true) {
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
					System.out.println(SYMBOL + " Retrieval Done");
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
	

}

class BasicData <K, V1, V2, V3, V4, V5, V6> {
	public K  Date;
	public V1 Adj;
	public V2 Open;
	public V3 Close;
	public V4 High;
	public V5 Low;
	public V6 Volume;
	
	BasicData(K Date, V1 Adj, V2 Open, V3 Close, V4 High, V5 Low, V6 Volume) {
		this.Date = Date;
		this.Adj = Adj;
		this.Open = Open;
		this.High = High;
		this.Low = Low;
		this.Volume = Volume;
	}
}
