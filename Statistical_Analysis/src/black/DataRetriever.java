package black;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import blue.UpdateControl;
import red.BadDataException;

public class DataRetriever {
	String FirstDate = null;
    String LastDate = null;
	String API = "";
	Boolean localAvailability = false;
	Boolean UpdateNeeds = false;
	String SYMBOL;
	public String[] column = new String[9];
	
	public ArrayList Total = new ArrayList();
	public ArrayList<Double> Close = new ArrayList<Double>();		
	public ArrayList<String> Date = new ArrayList<String>();
	public ArrayList<Double> Open = new ArrayList<Double>();
	public ArrayList<Double> Volume = new ArrayList<Double>();
	public ArrayList<Double> High = new ArrayList<Double>();
	public ArrayList<Double> Low = new ArrayList<Double>();
	public ArrayList<Double> Adj_Close = new ArrayList<Double>();
	public ArrayList<Double> Dividend = new ArrayList<Double>();
	public ArrayList<Double> StockSplit = new ArrayList<Double>();
	
	
	private ArrayList<String> list = new ArrayList<String>();
	
	public LinkedHashMap<String, Double> Adj_Close_M = new LinkedHashMap<String, Double>();
	public LinkedHashMap<String, Double> Close_M = new LinkedHashMap<String, Double>();
	public LinkedHashMap<String, Double> Open_M = new LinkedHashMap<String, Double>();
	public LinkedHashMap<String, Double> Volume_M = new LinkedHashMap<String, Double>();
	public LinkedHashMap<String, Double> High_M = new LinkedHashMap<String, Double>();
	public LinkedHashMap<String, Double> Low_M = new LinkedHashMap<String, Double>();
	public LinkedHashMap<String, String> DATE_M = new LinkedHashMap<String, String>(); //For the ease of printing dates
	public LinkedHashMap<String, Double> Dividend_M = new LinkedHashMap<String, Double>();
	public LinkedHashMap<String, Double> StockSplit_M = new LinkedHashMap<String, Double>();
	
	//Experimental
	public ArrayList<Double> Adj_Open = new ArrayList<Double>();
	public LinkedHashMap<String, String> Adj_Open_M = new LinkedHashMap<String, String>();
	
	public DataRetriever(String API) {
		API = API.toUpperCase();
		this.API = API;
	}
	
	@SuppressWarnings("unchecked")
	public void retrieve(String SYMBOL) {
		this.SYMBOL = SYMBOL.trim();
		System.out.println("Retrieving " + SYMBOL);

		if(!UpdateControl.CheckValidationData(SYMBOL)) {
			System.out.println(SYMBOL + " is not locally available");
			URL url;
		    @SuppressWarnings("unused")
			URLConnection urlConn = null;
		    BufferedReader br = null;
			String msg;
			StringBuilder bd;
			InputStream ins = null;
			String csvSplitBy = ",";
			boolean baddatacheck = false;
			boolean badconnectioncheck = false;
			String urlString = Utils.APIString(SYMBOL);
			
			int count = 0;
			int maxtry = 3;
			try {
				url = new URL(urlString);
				urlConn = url.openConnection();
				ins = url.openStream();
				
			} catch (IOException e) {
				count++;
				e.printStackTrace();
				System.out.println("An error occured while establishing connection with AlphaVantage");
				badconnectioncheck = true;
				try {
					Thread.sleep(10000);
					System.out.println("Retrying to establish connection ... " + count);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if(count == maxtry) {
					try {
						System.out.println("Maximum tryout reached. Giving up. Try later");
						throw e;
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			} finally {
				if(badconnectioncheck == false) {
					br = new BufferedReader(new InputStreamReader(ins));
					bd = new StringBuilder();
					msg = null;
					try {
						list.clear();
						int u = 0; // This variable is just for skipping the first line of the data which all are strings
						while((msg = br.readLine()) != null)
						{
							String tmpList[] = msg.split(csvSplitBy);
							
							if(msg.startsWith("{")) {
								System.out.println("Bad Data");
								baddatacheck = true;
								break;
							}
							if(msg == "    \"Error Message\": \"Invalid API call. Please retry or visit the documentation (https://www.alphavantage.co/documentation/) for TIME_SERIES_DAILY_ADJUSTED.\"") {
								System.out.println("Bad Data");
								baddatacheck = true;
								break;
							}
							if(msg == "}") {
								System.out.println("Bad Data");
								baddatacheck = true;
								break;
							}
							try {
								list.add(tmpList[0]); //Date
								list.add(tmpList[1]); //Open
								list.add(tmpList[2]); //High
								list.add(tmpList[3]); //Low
								list.add(tmpList[4]); //Close
								list.add(tmpList[5]); //Volume
								list.add(tmpList[6]); //Adjusted Close
								list.add(tmpList[7]); //Dividend
								list.add(tmpList[8]); //StockSplit
								
								if(u != 0) {
									Close_M.put(tmpList[0], Double.valueOf(tmpList[4]));
									Open_M.put(tmpList[0], Double.valueOf(tmpList[1]));
									Volume_M.put(tmpList[0], Double.valueOf(tmpList[6]));
									High_M.put(tmpList[0], Double.valueOf(tmpList[2]));
									Low_M.put(tmpList[0], Double.valueOf(tmpList[3]));
									DATE_M.put(tmpList[0], tmpList[0]);
									Adj_Close_M.put(tmpList[0], Double.valueOf(tmpList[5]));
									Dividend_M.put(tmpList[0], Double.valueOf(tmpList[7]));
									StockSplit_M.put(tmpList[0], Double.valueOf(tmpList[8]));
								}
								u++;
							} catch(ArrayIndexOutOfBoundsException e) {
								System.out.println("Bad Data");
							}
						}
					} catch (IOException e) {
						System.out.println("An error in Retreving " + this.SYMBOL);
						//e.printStackTrace();
					} finally {
						System.out.println(SYMBOL + " Retrieval Done");
					}
					
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("Could not connect to AlphaVantage");
				}
				
			}
			if(baddatacheck == false && badconnectioncheck == false) {
				saveCSV(); //Saving data as a file
				Extractor(); //Extracting and saving separated datasets in an instance
				UpdateControl.WriteValidationData(SYMBOL);
			} else {
				System.out.println("Did not retreive " + SYMBOL + " Correctly");
			}
		}
		
		else if(UpdateControl.CheckValidationData(SYMBOL)) {
			System.out.println(this.SYMBOL + " is locally available");
			File file = new File("./Data/Historical/" + SYMBOL + ".csv");
			boolean baddatacheck = false;
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
					list.clear();
					int u = 0; // This variable is just for skipping the first line of the data which all are strings
					while((msg = br.readLine()) != null)
					{
						String tmpList[] = msg.split(",");
						Total.add(msg);
						Total.add("\n");
						if(msg.startsWith("{")) {
							baddatacheck = true;
							System.out.println("Bad Data!");
							break;
						}

						list.add(tmpList[0]); //Date
						list.add(tmpList[1]); //Open
						list.add(tmpList[2]); //High
						list.add(tmpList[3]); //Low
						list.add(tmpList[4]); //Close
						list.add(tmpList[5]); //Volume
						list.add(tmpList[6]); //Adjusted Close
						list.add(tmpList[7]); //Dividend
						list.add(tmpList[8]); //StockSplit
						
						if(u != 0) {
							DATE_M.put(tmpList[0], tmpList[0]);
							Close_M.put(tmpList[0], Double.valueOf(tmpList[4]));
							Open_M.put(tmpList[0], Double.valueOf(tmpList[1]));
							Volume_M.put(tmpList[0], Double.valueOf(tmpList[6]));
							High_M.put(tmpList[0], Double.valueOf(tmpList[2]));
							Low_M.put(tmpList[0], Double.valueOf(tmpList[3]));
							Adj_Close_M.put(tmpList[0], Double.valueOf(tmpList[5]));
							Dividend_M.put(tmpList[0], Double.valueOf(tmpList[7]));
							StockSplit_M.put(tmpList[0], Double.valueOf(tmpList[8]));
						}
						u++;
					}
				} catch (IOException e) {
					System.out.println("Loading " + SYMBOL + " Failed");
					e.printStackTrace();
				} finally {
					System.out.println("Loading " + SYMBOL + " Done");
				}
				if(baddatacheck != true) {
					Extractor(); //Extracting and saving separated datasets in an instance
					System.out.println("Updating ..... " + SYMBOL);
					UpdateControl.UpdateValidationData(SYMBOL);
				}
			}
			}
	}
	
	private void Extractor() { //This method extracts sub data. Will be called within the method retrieve
		System.out.println("Extracting data for " + SYMBOL);
		System.out.println("List size : " + list.size());
		int blocksize = 9;
		
		for(int i = 0; i < blocksize; i++) {
			column[i] = list.get(i);
		}
		Total = list;
		for(int i = blocksize; i < list.size(); i++) { // "i" here must be changed when using another data or on a data size
											   // The reason i starts from 6 is because the first 6 elements are indexes thus they are string
			if(i % blocksize == 0)
			{
				Date.add(list.get(i).toString());
			} else if (i % blocksize == 1) {
				try {
					Open.add(Double.valueOf(list.get(i)));
				} catch(NumberFormatException e) {System.out.println("Error in list line " + i);}
			} else if(i % blocksize == 2) {
				try {
					High.add(Double.valueOf(list.get(i)));
				} catch(NumberFormatException e) {System.out.println("Error in list line " + i);}
			} else if(i % blocksize == 3) {
				try {
					Low.add(Double.valueOf(list.get(i)));
				} catch(NumberFormatException e) {}
			} else if (i % blocksize == 4) {
				try {
					Close.add(Double.valueOf(list.get(i)));
				} catch(NumberFormatException e) {}
			} else if (i % blocksize == 5) {
				try {
					Adj_Close.add(Double.valueOf(list.get(i)));
				} catch(NumberFormatException e) {}
			} else if (i % blocksize == 6) {
				try {
					Volume.add(Double.valueOf(list.get(i)));
				} catch(NumberFormatException e) {}
			} else if (i % blocksize == 7) {
				try {
					Dividend.add(Double.valueOf(list.get(i)));
				} catch(NumberFormatException e) {}
			} else if (i % blocksize == 8) {
				try {
					StockSplit.add(Double.valueOf(list.get(i)));
				} catch(NumberFormatException e) {}
			} else {
				continue;
			}
		}
	}
	
	private void saveCSV() {
		/*
		 * File being saved from the latest info, to be last info.
//		 * Should I reverse?
		 */
		FileWriter fw;
		File file = new File("./Data/Historical/"); // CSV form
		if(!file.exists()) {
			file.mkdirs();
			file = new File("./Data/Historical/" + SYMBOL + ".csv");
			System.out.println("Created a directory for data storage");
		} else {
			file = new File("./Data/Historical/" + SYMBOL + ".csv");
		}
		
		//Core writing
		try {
			fw = new FileWriter(file);
			int blocksize = 9; // Might need to be adjusted when data source is changed
			/*
			 * Block size 9 for call with adjusted close data
			 * Block size 6 for a call without any adjusted close data
			 */
			int j = 1;
			//System.out.println(list.size());
			for(int i = 0; i < list.size(); i++)
			{
				fw.write(list.get(i));
				fw.write(",");
				
				if(i == blocksize - 1)
				{
					j++;
					fw.write(",");
					fw.write("\n");
				}
				
				else if(i == blocksize * j - 1)
				{
					j++;
					fw.write(",");
					fw.write("\n");
				}
			}
			
			fw.close();
		} catch (IOException e) {
			System.out.println("An error in Writing data to a file");
			e.printStackTrace();
		} finally {
			System.out.println(SYMBOL + " Writing complete");
		}
	}
}
