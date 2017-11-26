package red;
import blue.*;
import black.*;

import java.util.*;
/*
 * Manager Class for Creating and Managing Portfolios
 */
public class PortfolioManager {
	String PortfolioName = "Default Portfolio";
	int Number_of_Securities = 0;
	String ExpectedReturnEstimatingMethod = "HISTORICAL";
	ArrayList<Double> Weights;
	
	//Set stocks, or set Symbols=============
	ArrayList<Stock> Stocks;
	ArrayList<String> Symbols;
	//=======================================
	
	public Double PortfolioReturn = 0.d;
	public Double PortfolioVariance = 0.d;
	
	
	PortfolioManager() {
		
	}
	
	public void setPortfolioName(String PortfolioName) {this.PortfolioName = PortfolioName;}
	public void setNumberofSecurities(int NumberofSecurities) {this.Number_of_Securities = NumberofSecurities;}
	public void setExpectedReturnEstimatingMethod(String Method) {this.ExpectedReturnEstimatingMethod = Method;}
	public void setWeights(ArrayList<Double> Weights) {this.Weights = Weights; Number_of_Securities = Weights.size();}
	public void setStocks(ArrayList<Stock> Stocks) {
		this.Stocks = Stocks;
		for(int i = 0; i < Stocks.size(); i++) {
			this.Symbols.add(Stocks.get(i).SYMBOL);
		}
	}
	public void setStocksbySymbol(ArrayList<String> Stocks) {
		for(int i = 0; i < Stocks.size(); i++) {
			this.Stocks.add(new Stock(Stocks.get(i)));
		}
	}
	
	private void ShapeUpPortfolio() {
		
	}

}

enum Price_Expecting_Method {
	Historical;
}