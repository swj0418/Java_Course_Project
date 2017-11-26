package black;
import java.util.*;
import java.net.*;
import java.io.*;
import java.nio.*;

public class DataRetriever2 {
	
	public DataRetriever2() {
		
	}
	
	public static final void ReadTicker(String trade) {
		/*
		 * Only NASDAQ and NYSE supported currently
		 */
		String Trade = trade.toUpperCase();
		ArrayList<String> RawData = new ArrayList<String>();
		
		String path = "http://www.nasdaq.com/screening/companies-by-industry.aspx?exchange=" + Trade + "&render=download";
		
		BufferedReader    reader;
		URLConnection     Conn;
		InputStream       stream;
		InputStreamReader ISreader;
		
		try {
			URL url = new URL(path);
			Conn = url.openConnection();
			System.out.println(">>>>>>>>>>>>>>>>>>" + url.getProtocol() + "<<<<<<<<<<<<<<<<<<<");
			stream = url.openStream();
			ISreader = new InputStreamReader(stream);
			
			reader = new BufferedReader(ISreader);
			String line = "";
			
			while((line = reader.readLine()) != null) {
				RawData.add(line);
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Utils.GeneralLineExtractor("./DataMeta/SYMBOLS/" + Trade + ".csv", RawData);
	}
	
	public static void main(String[] ar) {
		new DataRetriever2();
	}

}
