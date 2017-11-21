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

import red.BadDataException;

public class DataRetriever {
	
	String FirstDate = null;
    String LastDate = null;
	String API = "";
	Boolean localAvailability = false;
	Boolean UpdateNeeds = false;
	String SYMBOL;
	
	ArrayList Total = new ArrayList();
	public ArrayList<Double> Close = new ArrayList<Double>();		
	public ArrayList<String> Date = new ArrayList<String>();
	public ArrayList<Double> Open = new ArrayList<Double>();
	public ArrayList<Double> Volume = new ArrayList<Double>();
	public ArrayList<Double> High = new ArrayList<Double>();
	public ArrayList<Double> Low = new ArrayList<Double>();
	public ArrayList<Double> Adj_Close = new ArrayList<Double>();
	
	private ArrayList<String> list = new ArrayList<String>();
	
	public LinkedHashMap<String, Double> Adj_Close_M = new LinkedHashMap<String, Double>();
	public LinkedHashMap<String, Double> Close_M = new LinkedHashMap<String, Double>();
	public LinkedHashMap<String, Double> Open_M = new LinkedHashMap<String, Double>();
	public LinkedHashMap<String, Double> Volume_M = new LinkedHashMap<String, Double>();
	public LinkedHashMap<String, Double> High_M = new LinkedHashMap<String, Double>();
	public LinkedHashMap<String, Double> Low_M = new LinkedHashMap<String, Double>();
	public LinkedHashMap<String, String> DATE_M = new LinkedHashMap<String, String>(); //For the ease of printing dates
	
	public DataRetriever(String API) {
		API = API.toUpperCase();
		this.API = API;
	}
	
	@SuppressWarnings("unchecked")
	public void retrieve(String SYMBOL) {
		this.SYMBOL = SYMBOL;
		System.out.println("Retrieving " + SYMBOL);
		checkLocal(); //First, check if the data is there and is up-to-date.

		if(localAvailability != true) {
			System.out.println(SYMBOL + " is not locally available");
			URL url;
		    URLConnection urlConn = null;
		    BufferedReader br = null;
			String msg;
			StringBuilder bd;
			InputStream ins = null;
			
			String line = "";
			String csvSplitBy = ",";
			String urlString = new Utils().APIString(SYMBOL);
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
					list.clear();
					int u = 0; // This variable is just for skipping the first line of the data which all are strings
					while((msg = br.readLine()) != null)
					{
						String tmpList[] = msg.split(csvSplitBy);
						
						bd.append(msg);
						Total.add(msg);
						Total.add("\n");
						bd.append("\n");
						
						if(msg == "{") {
							System.out.println("Bad Data");
							throw new ArrayIndexOutOfBoundsException();
						}
						if(msg == "    \"Error Message\": \"Invalid API call. Please retry or visit the documentation (https://www.alphavantage.co/documentation/) for TIME_SERIES_DAILY_ADJUSTED.\"") {
							System.out.println("Bad Data");
							throw new BadDataException();
						}
						if(msg == "}") {
							System.out.println("Bad Data");
							throw new BadDataException();
						}
						
						try {
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
						} catch(ArrayIndexOutOfBoundsException e) {
							System.out.println("Bad Data");
						}
						
					}
				} catch (IOException | BadDataException e) {
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
			}
			saveCSV(); //Saving data as a file
			Extractor(); //Extracting and saving separated datasets in an instance
			recordLocal();
		}
		
		else if(localAvailability == true) {
			System.out.println(this.SYMBOL + " is locally available");
			File file = new File("./Data/Historical/" + SYMBOL + ".csv");
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
					System.out.println("Loading " + SYMBOL + " Failed");
					e.printStackTrace();
				} finally {
					System.out.println("Loading " + SYMBOL + " Done");
				}
				Extractor(); //Extracting and saving separated datasets in an instance
			}
			}
	}

	public void recordLocal() {
		File file = new File("./DataMeta");
		if(file.exists() != true) {
			file.mkdir();
			System.out.println("Metadata directory created");
		} else if(file.exists() == true) {
			System.out.println("Updating metadata, Availability");
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
			} catch (Exception e) {
				System.out.println("An error writing a metadata, Availability");
				e.printStackTrace();}
		} 
	}
	
	public Boolean checkLocal() {
		System.out.println("Checking for local availability");
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
			} catch (Exception e) { 
				System.out.println("An error occured while checking local availability");
				e.printStackTrace();
			}
			
		}
		return localAvailability;
	}
	
	private void Extractor() { //This method extracts sub data. Will be called within the method retrieve
		System.out.println("Extracting data for " + SYMBOL);
		System.out.println("List size : " + list.size());
		for(int i = 8; i < list.size(); i++) { // "i" here must be changed when using another data or on a data size
											   // The reason i starts from 6 is because the first 6 elements are indexes thus they are string
			if(i % 8 == 0)
			{
				Date.add(list.get(i).toString());
			} else if (i % 8 == 1) {
				try {
					Open.add(Double.valueOf(list.get(i)));
				} catch(NumberFormatException e) {System.out.println("Error in list line " + i);}
			} else if(i % 8 == 2) {
				try {
					High.add(Double.valueOf(list.get(i)));
				} catch(NumberFormatException e) {System.out.println("Error in list line " + i);}
			} else if(i % 8 == 3) {
				try {
					Low.add(Double.valueOf(list.get(i)));
				} catch(NumberFormatException e) {}
			} else if (i % 8 == 4) {
				try {
					Close.add(Double.valueOf(list.get(i)));
				} catch(NumberFormatException e) {}
			} else if (i % 8 == 5) {
				try {
					Adj_Close.add(Double.valueOf(list.get(i)));
				} catch(NumberFormatException e) {}
			} else if (i % 8 == 6) {
				try {
					Volume.add(Double.valueOf(list.get(i)));
				} catch(NumberFormatException e) {}
			} else if (i % 8 == 7) {
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
			System.out.println("An error in Writing data to a file");
			e.printStackTrace();
		} finally {
			System.out.println(SYMBOL + " Writing complete");
		}
	}
}
