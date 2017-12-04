package black;

import java.util.*;

import blue.UpdateControl;

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
			FileWriter fw = new FileWriter(file, true);
			FileOutputStream fos = new FileOutputStream(file, true);
			BufferedWriter bufferedwriter = new BufferedWriter(new OutputStreamWriter(fos));
			return bufferedwriter;
		} catch (IOException e) {
			System.out.println("An error occured while creating a buffered writer");
			e.printStackTrace();
			return null;
		} 
	}
	
	public final static BufferedWriter BufferedWriterCreatorRewrite(String filepath) {
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
		BufferedReader bufferedreader = null;
		if(!file.exists()) {
			UpdateControl.CreateAvailabilityFile();
		} else if(file.exists()) {
			try {
				FileReader fr = new FileReader(file);
				FileInputStream fis = new FileInputStream(file);
				bufferedreader = new BufferedReader(new InputStreamReader(fis));
				return bufferedreader;
			} catch (IOException e) {
				System.out.println("An error occured while creating a buffered writer");
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
		return bufferedreader;
	}
	
	public final static void GeneralLineExtractor(String locationtosave, ArrayList<String> rawdata) {
		BufferedWriter writer = Utils.BufferedWriterCreatorRewrite(locationtosave);
		int columns = 0;
		int rows    = 0;
		for(int i = 0; i < rawdata.size(); i++) {
			String tmp[] = rawdata.get(i).split(",");
			for(int k = 0; k < tmp.length; k++) {
				try {
					writer.write(tmp[k] + ",");
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			try {
				writer.write("\n");
			} catch(Exception e) {
				e.printStackTrace();
			}
			if(i == rawdata.size() - 1) {
				columns = tmp.length;
			}
			rows++;
		}
		System.out.println("Writing completed ::::: File created in [" + locationtosave + "]");
		System.out.println("Wrote " + rows + " rows, " + columns + " column data");
		try {
			writer.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public final static void DoubleMatrixPrinter(ArrayList<ArrayList<Double>> ArrayToPrint) {
		ArrayList<ArrayList<Double>> tmp = ArrayToPrint;
		for(int i = 0; i < ArrayToPrint.size(); i++) {
			for(int j = 0; j < ArrayToPrint.size(); j++) {
				if(ArrayToPrint.get(j).get(i) < 0) {
					System.out.print(ArrayToPrint.get(i).get(j) + "\t");
				} else {
					System.out.print(ArrayToPrint.get(i).get(j) + "\t\t");
				}
			}
			System.out.println();
		}
	}
	
	public final static String[][] ArrayListToPlain(ArrayList A1, int colsize) {
		int rowsize = 0;
		rowsize = A1.size() / colsize;
		
		String[][] retList = new String[rowsize][colsize];
		int j = 0;
		for(int i = 0; i < rowsize; i++) {
			for(int k = 0; k < colsize; k++) {
				retList[i][k] = A1.get(j).toString();
				j++;
			}
		}
		
		return retList;
	}
	
	public final static HashMap<String, String> BestMatchSearchAlgorithm(String string) {
		HashMap<String, String> possiblematch = new HashMap<String, String>();
		
		BufferedReader reader = Utils.BufferedReaderCreator("./DataMeta/SYMBOLS/MERGED.csv");
		String line = "";
		try {
			while((line = reader.readLine()) != null) {
				String[] tmpinside = line.split(",");
				
				for(int i = 0; i < tmpinside.length; i++) {
					if(tmpinside[i].contains(string)) {
						possiblematch.put(tmpinside[0], tmpinside[1]);
					}
				}
			}
		} catch (IOException e) {
			System.out.println("An error occurred while searching for the best match");
			e.printStackTrace();
		}
		
		return possiblematch;
	}
	
	public final static Double SmallNumberHandler(Double number, int digit) {
		double retnum = 0.d;
		retnum = number;
		
		 int tmpnum = (int) (number * (10*digit));
		retnum = tmpnum / (10*digit * 1.d);
		
		return retnum;
	}
	
	public final static Date[] StringToDate(ArrayList A1) {
		Date[] retarr = new Date[A1.size()];
		for(int i = 0; i < retarr.length; i++) {
			LocalDate d ;
			d = LocalDate.parse(A1.get(i).toString());
			Date date = Date.from(d.atStartOfDay(ZoneId.systemDefault()).toInstant());
			retarr[i] = date;
		}
		return retarr;
	}
}
