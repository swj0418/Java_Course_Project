package red;

import java.util.*;
import org.apache.commons.math3.*;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import black.*;
import blue.*;

public class Portfolio {
	public ArrayList<Stock> StockPool = new ArrayList<Stock>();
	public ArrayList<Double> Weights = new ArrayList<Double>();
	public LinkedHashMap<Stock, Double> LinkedStockPool = new LinkedHashMap<Stock, Double>();
	
	public Portfolio(ArrayList<Stock> StockPool, ArrayList<Double> Weights) {
		//Basic Form
		this.StockPool.addAll(StockPool);
		this.Weights.addAll(Weights);
		
		for(int i = 0; i < StockPool.size(); i++) {
			LinkedStockPool.put(this.StockPool.get(i), this.Weights.get(i));
		}
	}
	
	public Portfolio(String SYMBOL[]) { //Default Operation. Assumes equal weighted portfolio.
		for(int i = 0; i < SYMBOL.length; i++) {
			this.StockPool.add(new Stock(SYMBOL[i]));
			Weights.add(((Double.parseDouble(i + "") / Double.parseDouble("" + SYMBOL.length))));
		}
	}
	
	public Portfolio(ArrayList<String> Symbols) { // Default Operation 2.
		for(int i = 0; i < Symbols.size(); i++) {
			this.StockPool.add(new Stock(Symbols.get(i)));
			Weights.add(((Double.parseDouble(i + "") / Double.parseDouble("" + Symbols.size()))));
		}
	}
	
	
	
	public static void main(String[] ar) {
		
	}
}



// P.LinkedStockPool.keySet().forEach(Stock->System.out.println(Stock.SYMBOL));
