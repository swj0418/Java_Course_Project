package red;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class BasicInformation {
	String Trade;
	Integer Number;
	String StockName;
	String StockSymbol;
	
	HashMap SymbolList = new HashMap<String, String>();
	NameInfo symbolList = new NameInfo();
	public BasicInformation() {
		
	}
	public BasicInformation(Stock S) {
		this.StockSymbol = S.SYMBOL;
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
	public HashMap Symbolgetter(String Index) {
		System.out.println("Getting Symbols");
		File file = new File("./Data/INDEX_NAME/" + Index + "_Modified" + ".csv");
		try {
			FileInputStream fis = new FileInputStream(file);
			String line = null;
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			
			while((line = br.readLine()) != null) {
				String tmp[] = line.split(",");
				SymbolList.put(tmp[1], tmp[2]);
				symbolList.add(tmp[0].trim(), tmp[1].trim(), tmp[2].trim());
			}
 		} catch (IOException e) {
			e.printStackTrace();
		}
		return SymbolList;
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