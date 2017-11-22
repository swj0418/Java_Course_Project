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
	
	
	public static void main(String[] ar) {
		
	}
}



// P.LinkedStockPool.keySet().forEach(Stock->System.out.println(Stock.SYMBOL));
