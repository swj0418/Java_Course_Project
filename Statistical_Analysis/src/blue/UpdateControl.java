package blue;
import black.*;
import red.BasicInformation;
import red.Stock;

import java.util.*;
import java.io.*;
import java.time.LocalDate;

public class UpdateControl {
	public static final void CreateMetaDataDirectory() {
		File metafile = new File("./DataMeta/");
		if(!metafile.exists()) {
			metafile.mkdirs();
		} else {
			return;
		}
	}
	
	public static final void CreatePriceDataDirectory() {
		File stockfile = new File("./Data/Historical/");
		if(!stockfile.exists()) {
			stockfile.mkdirs();
		} else {
			return;
		}
	}
	
	public static final boolean CheckValidationData(String Symbol) {
		CreateMetaDataDirectory();
		File metafile = new File("./DataMeta/Availability.csv");
		boolean check = false;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(metafile));
			String line = "";
			
			while((line = reader.readLine()) != null) {
				String tmp[] = line.split(",");
				if(tmp[0].equals(Symbol) && tmp[1].equals(LocalDate.now().toString())) {
					reader.close();
					check = true;
					return true;
				} else if(!tmp[0].equals(Symbol)) {
					continue;
				} else if(!tmp[0].equals(Symbol) && !tmp[1].equals(LocalDate.now().toString())){
					reader.close();
					check = false;
					return false;
				}
			}
		} catch (IOException e) {
			System.out.println("An error occurred while checking an availability data");
			e.printStackTrace();
		} finally {

		}
		return check;
	}
	
	public static final void UpdateValidationData(String SYMBOL) {
		File metafile = new File("./DataMeta/Availability.csv");
		try {
			BufferedReader reader = Utils.BufferedReaderCreator("./DataMeta/Availability.csv");
			File tmpfile = new File("./Data/Historical/" + SYMBOL + ".csv");
			String firstdatetosearch = Utils.StringFinderCSV(tmpfile, 1, 0);
			String lastdatetosearch = "";
			BufferedReader reader2 = Utils.BufferedReaderCreator("./Data/Historical/" + SYMBOL + ".csv");
			String line = "";
			while((line = reader2.readLine()) != null) {
				String tmp[] = line.split(",");
				lastdatetosearch = tmp[0];
			}
			
			line = "";
			boolean checker = false;
			ArrayList<String> tmp_memory = new ArrayList<String>();
			while((line = reader.readLine()) != null) {
				String tmp[] = line.split(",");
				if(tmp[0].equals(SYMBOL)) {
					tmp_memory.add(SYMBOL);
					//tmp_memory.add(firstdatetosearch);
					tmp_memory.add(LocalDate.now().toString()); //This method needs improvement Varies by my relative timezone.
					tmp_memory.add(lastdatetosearch);
					checker = true;
					continue;
				} else if(checker == false){
					for(int i = 0; i < tmp.length; i++) {
						tmp_memory.add(tmp[i]);
					}
				} else if(checker == true) {
					for(int i = 0; i < tmp.length; i++) {
						tmp_memory.add(tmp[i]);
					}
				}
			}
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(metafile));
			for(int i = 0; i < tmp_memory.size(); i++) {
				writer.write(tmp_memory.get(i) + ",");
				if(i % 3 == 2) {
					writer.write("\n");
				}
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("An error occurred while updating an availability data");
			e.printStackTrace();
		}
	}
	
	
	
	public static final void WriteValidationData(String SYMBOL) {
	}
	
	public static final String StringFinderCSV(File file, String key, int target) {
		BufferedReader reader = Utils.BufferedReaderCreator(file.getPath());
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
	
	public static final void CreateTempFile(File file) {
		BufferedReader reader = Utils.BufferedReaderCreator(file.getPath());
		File tmpfile = new File(file.getParent() + "/~TempFile.csv");
		BufferedWriter writer = Utils.BufferedWriterCreator(tmpfile.getPath());
		String line = "";
		try {
			while((line = reader.readLine()) != null) {
				writer.write(line);
			}
			writer.close();
			reader.close();
		} catch (IOException e) {
			System.out.println("An error occured while creating a temporary file");
			e.printStackTrace();
		}
	}
	
	public static final void DeleteTempFile(File file) {
		File tmpfile = new File(file.getParent() + "/~TempFile.csv");
		tmpfile.delete();
	}
	
	public static final void UpdateTotal(String Index) {
		HashMap List = new BasicInformation().Symbolgetter(Index);
		ArrayList<String> keys = new ArrayList<String>();
		for(int i = 0; i < List.size(); i++) {
			keys.addAll(List.keySet()); //I don't know why but the key size is all weird;
		}
		ArrayList<Stock> StockPool = new ArrayList<Stock>();
		long start = System.currentTimeMillis();
		for(int i = 0; i < List.size(); i++) {
			StockPool.add(new Stock(keys.get(i)));
			System.out.println("++++++++++++++++++++++++++++++++++KEYSIZE : " + List.size());
			int tmpp = (int) (1000000.d * ((i+0.d) / (List.size()+0.d)));
			Double p = tmpp / 10000.d;
			long now = System.currentTimeMillis();
			long time = (now - start) / 1000;
			System.out.println("\n" + p + "% Downloaded :::::" + " Time taken : " + time + " seconds ::::: " + i + "th" + "\n");
			StockPool.clear();
		}
	}
}
