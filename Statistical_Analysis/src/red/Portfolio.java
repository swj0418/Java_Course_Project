package red;

import java.util.*;
import black.*;
import blue.*;

public class Portfolio {
	String PortfolioName = "Default Portfolio";
	
	public ArrayList<Stock> StockPool = new ArrayList<Stock>();
	public ArrayList<Double> Weights = new ArrayList<Double>();
	public LinkedHashMap<Stock, Double> LinkedStockPool = new LinkedHashMap<Stock, Double>();
	public ArrayList<Statistics> StockStat = new ArrayList<Statistics>();
	
	int Number_of_Securities = 0;
	String ExpectedReturnEstimatingMethod = "HISTORICAL";
	
	public Double PortfolioReturn = 0.d;
	public Double PortfolioVariance = 0.d;
	
	public Portfolio() {
		
	}
	
	public Portfolio(ArrayList<Stock> StockPool, ArrayList<Double> Weights) {
		//Basic Form
		this.StockPool.addAll(StockPool);
		this.Weights.addAll(Weights);
		
		for(int i = 0; i < StockPool.size(); i++) {
			LinkedStockPool.put(this.StockPool.get(i), this.Weights.get(i));
		}
		
		ArrayList<Double> tmpER = new ArrayList<Double>();
		ArrayList<Double> tmpV = new ArrayList<Double>();
		
		for(int i = 0; i < StockPool.size(); i++) {
			tmpER.add(Stoculator.ArithmeticMeanReturn(StockPool.get(i).Adj_Close));
			tmpV.add(Stoculator.ReturnVariance(StockPool.get(i).Adj_Close));
		}
		
		PortfolioReturn = Portoculator.PortfolioExpectedReturn(tmpER, Weights);
		PortfolioVariance = Portoculator.PortfolioVariance(tmpV, Weights);
	}
	
	public Portfolio(ArrayList<Stock> StockPool, ArrayList<Double> Weights, int TimeSlice, String datatype, String startdate, String enddate) {
		//Basic Form
		this.StockPool.addAll(StockPool);
		this.Weights.addAll(Weights);
		
		if(datatype.equals("")) {
			datatype = "ARITHMETIC";
		}
		if(startdate.equals("") || enddate.equals("")) {
			startdate = "2015-01-01";
			enddate = "2017-11-01";
		}
		
		for(int i = 0; i < StockPool.size(); i++) {
			LinkedStockPool.put(this.StockPool.get(i), this.Weights.get(i));
		}
		
		for(int i = 0; i < StockPool.size(); i++) {
			StockStat.add(new Statistics(StockPool.get(i).SYMBOL, startdate, enddate, TimeSlice, "ADJ_CLOSE"));
			
			//Need some revision. The parameter "ADJ_CLOSE" was not what I meant by a datatype. I intended it be calculating methods.
		}
		
		ArrayList<Double> tmpER = new ArrayList<Double>();
		ArrayList<Double> tmpV = new ArrayList<Double>();
		
		for(int i = 0; i < StockPool.size(); i++) {
			if(datatype.equals("GEOMETRIC")) {
				tmpER.add(Stoculator.GeometricMeanReturn(StockPool.get(i).request("ADJ_CLOSE", startdate, enddate), TimeSlice));
				tmpV.add(Stoculator.ReturnVariance(StockPool.get(i).request("ADJ_CLOSE", startdate, enddate)));
				System.out.println(Stoculator.GeometricMeanReturn(StockStat.get(i).PriceReturn, TimeSlice));
			} else {
				tmpER.add(Stoculator.ArithmeticMeanReturn(StockPool.get(i).request("ADJ_CLOSE", startdate, enddate), TimeSlice));
				tmpV.add(Stoculator.ReturnVariance(StockPool.get(i).request("ADJ_CLOSE", startdate, enddate)));
				System.out.println(Stoculator.ArithmeticMeanReturn(StockPool.get(i).request("ADJ_CLOSE", startdate, enddate), TimeSlice));
			}
		}
		
		PortfolioReturn = Portoculator.PortfolioExpectedReturn(tmpER, Weights);
		PortfolioVariance = Portoculator.PortfolioVariance(tmpV, Weights);
		System.out.println(PortfolioReturn + "    " + PortfolioVariance);
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
	
	
}



// P.LinkedStockPool.keySet().forEach(Stock->System.out.println(Stock.SYMBOL));
