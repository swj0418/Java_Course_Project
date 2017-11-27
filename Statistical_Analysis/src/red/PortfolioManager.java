package red;
import blue.*;
import black.*;

import java.util.*;
/*
 * Manager Class for Creating and Managing Portfolios
 */
public class PortfolioManager {
	Portfolio portfolio;	
	
	PortfolioManager() {
		
	}
	
	public void setPortfolioName(String PortfolioName) {portfolio.PortfolioName = PortfolioName;}
	public void setNumberofSecurities(int NumberofSecurities) {portfolio.Number_of_Securities = NumberofSecurities;}
	public void setExpectedReturnEstimatingMethod(String Method) {portfolio.ExpectedReturnEstimatingMethod = Method;}
	public void setWeights(ArrayList<Double> Weights) {portfolio.Weights = Weights; portfolio.Number_of_Securities = Weights.size();}
	public void setStocks(ArrayList<Stock> Stocks) {
		portfolio.StockPool = Stocks;
	}
	public void setStocksbySymbol(ArrayList<String> Symbols) {
		for(int i = 0; i < Symbols.size(); i++) {
			portfolio.StockPool.add(new Stock(Symbols.get(i)));
		}
	}
	public void deleteSecurity(Stock stocktodelete) {
		portfolio.StockPool.remove(stocktodelete);
	}
	public void addSecurity(Stock stocktoadd) {
		if(!portfolio.StockPool.contains(stocktoadd)) {
			portfolio.StockPool.add(stocktoadd);
		}
	}
}

enum Price_Expecting_Method {
	Historical;
}