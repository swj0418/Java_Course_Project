package black;

import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.*;

public class Utils {
	
	public static String APIString(String SYMBOL) {
		return "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=" +
				SYMBOL.trim() +"&apikey=KIUYG7J8UA5MIMQI&datatype=csv&outputsize=full";
	}
	
	@SuppressWarnings("static-access")
	public static String TodayString() {
		LocalDate checkdate = null;
		return checkdate.now().toString();
	}
	
	public static String GetLastDayOfTheTrade(String SYMBOL) {
		return StringFinderCSV("./Data/Historical/" + SYMBOL + ".csv", 1, 0);
	}
	public final static void SwapReverser(ArrayList A1) {
		ArrayList tmp = new ArrayList();
		for(int i = A1.size() - 1; i >= 0; i--) {
			tmp.add(A1.get(i));
		}
		A1.clear();
		for(int i = 0; i < tmp.size(); i++) {
			A1.add(tmp.get(i));
		}
	}
	
	public final static ArrayList Reverser(ArrayList A1) {
		ArrayList tmp = new ArrayList();
		for(int i = A1.size() - 1; i >= 0; i--) {
			tmp.add(A1.get(i));
		}
		return tmp;
	}
	
	public static final String StringFinderCSV(String filepath, String key, int target) {
		BufferedReader reader = Utils.BufferedReaderCreator(filepath);
		String line = "";
		try {
			while((line = reader.readLine()) != null) {
				String[] tmp = line.split(",");
				for(int i = 0; i < tmp.length; i++) {
					if(tmp[i].equals(key)) {
						return tmp[target];
					}
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			
		}
		return null;
	}
	
	public static final String StringFinderCSV(File file, int targetrow, int targetcolumn) {
		BufferedReader reader = Utils.BufferedReaderCreator(file.getPath());
		String line = "";
		try {
			int row = 0;
			while((line = reader.readLine()) != null) {
				String[] tmp = line.split(",");
				if(targetrow == row) {
					return tmp[targetcolumn];
				}
				row++;
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("An error occurred while searching for your data at " + targetrow + " " + targetcolumn);
			e.printStackTrace();
			return null;
		} finally {
			
		}
		return null;
	}
	
	public static final String StringFinderCSV(String filepath, int targetrow, int targetcolumn) {
		BufferedReader reader = Utils.BufferedReaderCreator(filepath);
		String line = "";
		try {
			int row = 0;
			while((line = reader.readLine()) != null) {
				String[] tmp = line.split(",");
				if(targetrow == row) {
					return tmp[targetcolumn];
				}
				row++;
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("An error occurred while searching for your data at " + targetrow + " " + targetcolumn);
			e.printStackTrace();
			return null;
		} finally {
			
		}
		return null;
	}
	
	public final static BufferedWriter BufferedWriterCreator(String filepath) {
		File file = new File(filepath);
		try {
			FileWriter fw = new FileWriter(file);
			FileOutputStream fos = new FileOutputStream(file);
			BufferedWriter bufferedwriter = new BufferedWriter(new OutputStreamWriter(fos));
			return bufferedwriter;
		} catch (IOException e) {
			System.out.println("An error occured while creating a buffered writer");
			e.printStackTrace();
			return null;
		} 
	}
	public final static BufferedReader BufferedReaderCreator(String filepath) {
		File file = new File(filepath);
		try {
			FileReader fr = new FileReader(file);
			FileInputStream fis = new FileInputStream(file);
			BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(fis));
			return bufferedreader;
		} catch (IOException e) {
			System.out.println("An error occured while creating a buffered writer");
			e.printStackTrace();
			return null;
		} 
	}
}
