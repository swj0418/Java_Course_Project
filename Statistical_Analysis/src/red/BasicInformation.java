package red;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import black.Utils;

public class BasicInformation {
	public String Trade = "";
	public Integer Number = 0;
	public String StockName = "";
	public String StockSymbol = "";
	
	HashMap SymbolList = new HashMap<String, String>();
	NameInfo symbolList = new NameInfo();
	public BasicInformation() {
		
	}
	public BasicInformation(Stock S) {
		this.StockSymbol = S.SYMBOL;
		StockNamegetter();
	}
	public BasicInformation(String SymbolorTrade) {
		if(SymbolorTrade == "NYSE" || SymbolorTrade == "AMEX" || SymbolorTrade == "NASDAQ") {
			this.Trade = SymbolorTrade;
			Symbolgetter(SymbolorTrade);
		} else {
			this.StockSymbol = SymbolorTrade;
			System.out.println("Symbol not supported");
		}
	}
	private void TradeFinder() {
		
	}
	
	public HashMap Symbolgetter(String Index) {
		System.out.println("Getting Symbols");
		Index = Index.toUpperCase();
		File file = new File("./DataMeta/SYMBOLS/" + Index + ".csv");
		try {
			FileInputStream fis = new FileInputStream(file);
			String line = null;
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			
			while((line = br.readLine()) != null) {
				String tmp[] = line.split(",");
				SymbolList.put(tmp[0], tmp[1]);
				symbolList.add(tmp[0].trim(), tmp[1].trim(), tmp[2].trim());
			}
 		} catch (IOException e) {
			e.printStackTrace();
		}
		return SymbolList;
	}
	public String StockNamegetter() {
		BufferedReader reader = Utils.BufferedReaderCreator("./DataMeta/SYMBOLS/MERGED.csv");
		String line = "";
		String name = "";
		try {
			while((line = reader.readLine()) != null) {
				String[] tmp = line.split(",");
				if(tmp[1].equals(this.StockSymbol)) {
					this.StockName = tmp[2];
					name = tmp[2];
					break;
				}
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("An error occurred while retrieving a stock name for " + this.StockSymbol);
			e.printStackTrace();
		}

		return name;
	}
}


class NameInfo<K, V1, V2> extends HashMap{
	public K Number;
	public V1 Symbol;
	public V2 Name;
	
	NameInfo() {
		
	}
	NameInfo(K number, V1 symbol, V2 name) {
		this.Number = number;
		this.Symbol = symbol;
		this.Name = name;
	}
	public void add(Object obj, Object obj2, Object obj3) {
		this.Number = (K) obj;
		this.Symbol = (V1) obj2;
		this.Name = (V2) obj3;
	}
	
	public K getNumber() {
		return Number;
	}
	public V1 getSymbol() {
		return Symbol;
	}
	public V2 getName() {
		return Name;
	}
}