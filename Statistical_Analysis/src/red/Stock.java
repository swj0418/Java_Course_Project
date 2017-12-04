package red;
import java.util.*;
import black.DataRetriever;
import black.Stoculator;

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

public class Stock {
	public BasicInformation BasicInfo;
	public String SYMBOL;
	public String Stock_Name;
	public Integer Avail_Size;
	public String LastDate;
	public String FirstDate;
	public String[] column;
	
	public ArrayList<?> Total = new ArrayList();
	public ArrayList<Double> Close = new ArrayList<Double>();		
	public ArrayList<String> Date = new ArrayList<String>();
	public ArrayList<Double> Open = new ArrayList<Double>();
	public ArrayList<Double> Volume = new ArrayList<Double>();
	public ArrayList<Double> High = new ArrayList<Double>();
	public ArrayList<Double> Low = new ArrayList<Double>();
	public ArrayList<Double> Adj_Close = new ArrayList<Double>();
	public ArrayList<Double> Dividend = new ArrayList<Double>();
	public ArrayList<Double> StockSplit = new ArrayList<Double>();
	
	public LinkedHashMap<String, Double> Adj_Close_M = new LinkedHashMap<String, Double>();
	public LinkedHashMap<String, Double> Close_M = new LinkedHashMap<String, Double>();
	public LinkedHashMap<String, Double> Open_M = new LinkedHashMap<String, Double>();
	public LinkedHashMap<String, Double> Volume_M = new LinkedHashMap<String, Double>();
	public LinkedHashMap<String, Double> High_M = new LinkedHashMap<String, Double>();
	public LinkedHashMap<String, Double> Low_M = new LinkedHashMap<String, Double>();
	public LinkedHashMap<String, String> DATE_M = new LinkedHashMap<String, String>(); //For the ease of printing dates
	public LinkedHashMap<String, Double> Dividend_M = new LinkedHashMap<String, Double>();
	public LinkedHashMap<String, Double> StockSplit_M = new LinkedHashMap<String, Double>();
	
	//Test
	public ArrayList<Double> Adj_Open = new ArrayList<Double>();
	public LinkedHashMap<String, String> Adj_Open_M = new LinkedHashMap<String, String>();
	
	public Stock() {
		
	}
	public Stock(String SYMBOL) {
		this.SYMBOL = SYMBOL.trim();
		this.SYMBOL = SYMBOL.toUpperCase();
		DataRetriever DR = new DataRetriever("AlphaVantage");
		DR.retrieve(SYMBOL);
		
		this.Close = DR.Close;
		this.Date = DR.Date;
		this.Open = DR.Open;
		this.Volume = DR.Volume;
		this.High = DR.High;
		this.Low = DR.Low;
		this.Adj_Close = DR.Adj_Close;
		this.Dividend = DR.Dividend;
		this.StockSplit = DR.StockSplit;
		
		this.Adj_Close_M = DR.Adj_Close_M;
		this.Close_M = DR.Close_M;
		this.Open_M = DR.Open_M;
		this.Volume_M = DR.Volume_M;
		this.High_M = DR.High_M;
		this.Low_M = DR.Low_M;
		this.DATE_M = DR.DATE_M;
		this.Dividend_M = DR.Dividend_M;
		this.StockSplit_M = DR.StockSplit_M;
		
		this.Avail_Size = this.Adj_Close.size();
		this.column = DR.column;
		this.Total = DR.Total;
		
		this.Adj_Open = Stoculator.AdjustOpen(this.Adj_Close, this.Close, this.Open);
		
		BasicInfo = new BasicInformation(this);
		
		DR = null; //Nullify for memory.
 	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList request(String category, String start, String end) {
		ArrayList return_arr = new ArrayList();
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
		} else if (cat == "ADJ_OPEN") {
			return Adj_Open;
		}
		else {
			// May need to add more if date category increases
			System.out.println("No such data");
			return return_arr;
		}
	}

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