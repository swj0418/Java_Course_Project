package red;
import java.util.*;

import black.DataRetriever;

import java.math.*;

/*
 * SStat Class
 * Services provided :
 * 
 * 
 * Calculations with one stock data :
 * 
 * ArithmeticMean
 * GeometricMean
 * 
 * PercentageChange (By range, and time scale)
 * 
 * Variance
 * Beta
 * 
 * Calculations with two stock data :
 * 
 * Covariance
 * Correlation
 * 
 * 
 * USAGE:
 * 		Don't call a method and create a new class at the same time.
 * 		For example) new SStat(S).mean(); <== Don't do this.
 * 		Instead, create a class first and do whatever calculation needed after.
 *
 *		Now Containing only one Stock
 * 
 */
public class Statistics {
	Stock S;
	
	Double total = 0.d; // Being used by Geometric and Arithmetic mean. Variable should be declared inside for totalling purpose.
	public Double Geomean = 0.d;
	public Double Arimean = 0.d;
	public Double ReturnGeomean = 0.d;
	public Double ReturnArimean = 0.d;
	public Double S_Variance_Return = 0.d;
	public Double S_StandardDeviation_Return = 0.d;
	
	//For predictions
	public Double ReturnMax = 0.d;
	public Double ReturnMin = 0.d;
	
	//Percentage Arrays
	public ArrayList<Double> S_Percentage_Change = new ArrayList<Double>();
	//Variance Arrays ::::: Variance is a difference between individual stock prices and its mean.
	public ArrayList<Double> S_ReturnDeviation = new ArrayList<Double>();
	public ArrayList<Double> S_PriceDeviation = new ArrayList<Double>();
	
	//Beta Arrays  ::::: Beta is a variance of an individual stock price derived from the index.
	ArrayList<Double> S_Beta = new ArrayList<Double>();
	
	//Might not have to use these. These lists are all stored in a stock class
	public ArrayList<Double> S_Close = new ArrayList<Double>();
	public ArrayList<Double> S_Open = new ArrayList<Double>();
	public ArrayList<Double> S_High = new ArrayList<Double>();
	public ArrayList<Double> S_Low = new ArrayList<Double>();
	public ArrayList<Double> S_Volume = new ArrayList<Double>();
	public ArrayList<Double> S_Adj_Close = new ArrayList<Double>();
	//
	
	public Statistics(Stock S) {
		this.S = S;
	}
	
	public Statistics(Stock S, String Category, int TimeSlice, String startdate, String enddate, String Type, Boolean autocomplete) {
		/*
		 * This function is only for those who knows. I just don't have time right now to apply this to a whole class
		 */
		this.S = S;
		this.S.request(Category, startdate, enddate);
		new DataRetriever("AlphaVantage").retrieve(S.SYMBOL);
		
		if(autocomplete.equals(true)) {
			Arithmeticmean(Category, startdate, enddate);
			Geometricmean(Category, startdate, enddate);
			
			PercentageChange(Category, TimeSlice, startdate, enddate);
			MeanReturnGeometric(Category, TimeSlice, startdate, enddate);
			MeanReturnArithmetic(Category, TimeSlice, startdate, enddate);
			ReturnDeviation(Category, TimeSlice, startdate, enddate, Type);
			Variance_Return(Category, TimeSlice, startdate, enddate, Type);
			StandardDeviation_Return(Category, TimeSlice, startdate, enddate, Type);
			MinMax();
		}
		else if(autocomplete.equals(false)) {
			this.S = S;
			this.S.request(Category, startdate, enddate);
			new DataRetriever("AlphaVantage").retrieve(S.SYMBOL);
		}
	}
	Statistics(Stock S, Stock S2, String Category) {
		this.S = S;
	}
	
	public Double Arithmeticmean(String Category, String startdate, String enddate) {
		total = 0.d; //Initialize
		Arimean = 0.d;
		Category = Category.toUpperCase();
		if(S.request(Category, startdate, enddate) == null) {
			System.out.println("Error occurred");
			return 0.d;
		} else if (Category == "OPEN" || Category == "Open"){
			S_Open = S.request(Category, startdate, enddate);
			for(int i = 0; i < S_Open.size(); i++) {
				total += S_Open.get(i);
			}
			Arimean = total / S_Open.size();
			
			return Arimean;
		} else if (Category == "CLOSE" || Category == "Close"){
			S_Close = S.request(Category, startdate, enddate);
			for(int i = 0; i < S_Close.size(); i++) {
				total += S_Close.get(i);
			}
			Arimean = total / S_Close.size();
			
			return Arimean;
		} else if (Category == "High" || Category == "HIGH"){
			S_High = S.request(Category, startdate, enddate);
			for(int i = 0; i < S_High.size(); i++) {
				total += S_High.get(i);
			}
			Arimean = total / S_High.size();
			
			return Arimean;
		} else if (Category == "Volume" || Category == "VOLUME"){
			S_Volume = S.request(Category, startdate, enddate);
			for(int i = 0; i < S_Volume.size(); i++) {
				total += S_Volume.get(i);
			}
			Arimean = total / S_Volume.size();
			
			return Arimean;
		} else if (Category == "Low" || Category == "LOW"){
			S_Low = S.request(Category, startdate, enddate);
			for(int i = 0; i < S_Low.size(); i++) {
				total += S_Low.get(i);
			}
			Arimean = total / S_Low.size();
			
			return Arimean;
		} else if (Category == "Adj_Close" || Category == "ADJ_CLOSE"){
			S_Adj_Close = S.request(Category, startdate, enddate);
			for(int i = 0; i < S_Adj_Close.size(); i++) {
				total += S_Adj_Close.get(i);
			}
			Arimean = total / S_Adj_Close.size();
			
			return Arimean;
		}
		else {
			return 0.d;
		}
	}
	public Double Geometricmean(String Category, String startdate, String enddate) {
		total = 1.d; //Initialize
		Geomean = 0.d;
		Category = Category.toUpperCase();
		if(S.request(Category, startdate, enddate) == null) {
			System.out.println("Error occurred");
			return 0.d;
		} else if (Category == "OPEN" || Category == "Open"){
			S_Open = S.request(Category, startdate, enddate);
			for(int i = 0; i < S_Open.size(); i++) {
				total *= S_Open.get(i);
			}
			Geomean = Math.pow(total, (1.d / S_Open.size()));
			
			total = 0.d;
			return Geomean;
		} else if (Category == "CLOSE" || Category == "Close"){
			S_Close = S.request("CLOSE", startdate, enddate);
			for(int i = 0; i < S_Close.size(); i++) {
				total *= S_Close.get(i);
			}
			Geomean = Math.pow(total, Double.valueOf(1.0d / Double.valueOf(S_Close.size())));
			
			total = 0.d;
			return Geomean;
		} else if (Category == "High" || Category == "HIGH"){
			S_High = S.request(Category, startdate, enddate);
			for(int i = 0; i < S_High.size(); i++) {
				total *= S_High.get(i);
			}
			Geomean = Math.pow(total, (1.0d / S_High.size()));
			
			total = 0.d;
			return Geomean;
		} else if (Category == "Volume" || Category == "VOLUME"){
			S_Volume = S.request(Category, startdate, enddate);
			for(int i = 0; i < S_Volume.size(); i++) {
				total *= S_Volume.get(i);
			}
			Geomean = Math.pow(total, (1.0d / S_Volume.size()));
			
			total = 0.d;
			return Geomean;
		} else if (Category == "Low" || Category == "LOW"){
			S_Low = S.request(Category, startdate, enddate);
			for(int i = 0; i < S_Low.size(); i++) {
				total *= S_Low.get(i);
			}
			Geomean = Math.pow(total, (1.d / S_Low.size()));
			
			total = 0.d;
			return Geomean;
		} else if (Category == "Adj_Close" || Category == "ADJ_CLOSE"){
			S_Adj_Close = S.request(Category, startdate, enddate);
			for(int i = 0; i < S_Adj_Close.size(); i++) {
				total *= S_Adj_Close.get(i);
			}
			Geomean = Math.pow(total, (1.d / S_Adj_Close.size()));
			
			total = 0.d;
			return Geomean;
		}
		else {
			return 0.d;
		}
	}
	public Double MeanReturnGeometric(String Category, int TimeSlice, String startdate, String enddate) {
		this.S_ReturnDeviation = new ArrayList<Double>();
		ReturnGeomean = 1.d;
		
		Category = Category.toUpperCase();
		if(S.request(Category, startdate, enddate) == null) {
			System.out.println("An Error Occured in MeanReturnGeometric()");
		} else if (S.request(Category, startdate, enddate) != null) {
			S_Percentage_Change = PercentageChange(Category, TimeSlice, startdate, enddate);
			
			for(int i = 0; i < S_Percentage_Change.size(); i++) {
				ReturnGeomean *= (1 + S_Percentage_Change.get(i)); //When calculating a geometric mean, you have to add 1 to each returns
			}
			ReturnGeomean = Math.pow(ReturnGeomean, (1.0d / S_Percentage_Change.size())) - 1;
			return ReturnGeomean;
		}
		else {
			System.out.print("Wrong parameters in MeanReturnGeometric()");
		}
		
		
		return ReturnGeomean;
	}
	public Double MeanReturnArithmetic(String Category, int TimeSlice, String startdate, String enddate) {
		this.S_ReturnDeviation = new ArrayList<Double>();
		ReturnArimean = 0.d;
		
		Category = Category.toUpperCase();
		if(S.request(Category, startdate, enddate) == null) {
			System.out.println("An Error Occured in MeanReturnGeometric()");
		} else if (S.request(Category, startdate, enddate) != null) {
			S_Percentage_Change = PercentageChange(Category, TimeSlice, startdate, enddate);
			
			for(int i = 0; i < S_Percentage_Change.size(); i++) {
				ReturnArimean += S_Percentage_Change.get(i);
			}
			ReturnArimean = ReturnArimean / S_Percentage_Change.size();
			return ReturnArimean;
		}
		return ReturnArimean;
	}
	public ArrayList PercentageChange(String Category, int TimeSlice, String startdate, String enddate) {
		S_Percentage_Change = new ArrayList<Double>();
		/*
		 * TimeSlice : Monthly, Daily, Yearly ... give it by numbers(days)
		 * Local Variable must be nullified.
		 */
		
		if(S.request(Category, startdate, enddate) == null) {
			System.out.println("An Error Occured");
		} else if (Category == "CLOSE") {
			S_Close = S.request("CLOSE", startdate, enddate);
			
			/*
			 * Divided into two cases where the first case there is no problem with indexing, but the second case,
			 * if the size of (range of) price data from which we want to draw percentage change info is correctly
			 * divided by a time slice without any leftover.
			 * 
			 * Dealt with it.
			 * 
			 * No, I thought wrong. For example, drawing 20 days gap percentage from 100 price data means that I can only draw
			 * 4 equally-sized gap.
			 * 
			 * Small number problems occur... What 2 do with this problem
			 */
			//System.out.println(S_Close.size());
			
			int k = S_Close.size() - 1;
			if(S_Close.size() % TimeSlice == 0) {
				for(int i = 0; i < (S_Close.size() / TimeSlice) - 1; i++) {
					//System.out.println(S_Close.get(k) + "   " + S_Close.get(k - TimeSlice));
					S_Percentage_Change.add(   (S_Close.get(k) - S_Close.get(k - TimeSlice)) / (S_Close.get(k - TimeSlice))   );
					k -= TimeSlice;
				}
			} else if (S_Close.size() % TimeSlice != 0){
				for(int i = 0; i < S_Close.size() / TimeSlice; i++) {
					//System.out.println(S_Close.get(k) + "   " + S_Close.get(k - TimeSlice));
					S_Percentage_Change.add(   (S_Close.get(k) - S_Close.get(k - TimeSlice)) / (S_Close.get(k - TimeSlice))   );
					k -= TimeSlice;
				}
			}
		} else if (Category == "OPEN") {
			S_Open = S.request("OPEN", startdate, enddate);

			//System.out.println(S_Open.size());
			
			int k = S_Open.size() - 1;
			if(S_Open.size() % TimeSlice == 0) {
				for(int i = 0; i < (S_Open.size() / TimeSlice) - 1; i++) {
					//System.out.println(S_Open.get(k) + "   " + S_Open.get(k - TimeSlice));
					S_Percentage_Change.add(   (S_Open.get(k) - S_Open.get(k - TimeSlice)) / (S_Open.get(k - TimeSlice))   );
					k -= TimeSlice;
				}
			} else if (S_Open.size() % TimeSlice != 0){
				for(int i = 0; i < S_Open.size() / TimeSlice; i++) {
					//System.out.println(S_Open.get(k) + "   " + S_Open.get(k - TimeSlice));
					S_Percentage_Change.add(   (S_Open.get(k) - S_Open.get(k - TimeSlice)) / (S_Open.get(k - TimeSlice))   );
					k -= TimeSlice;
				}
			}
		} else if (Category == "HIGH") {
			S_High = S.request("HIGH", startdate, enddate);

			int k = S_High.size() - 1;
			if(S_High.size() % TimeSlice == 0) {
				for(int i = 0; i < (S_High.size() / TimeSlice) - 1; i++) {
					S_Percentage_Change.add(   (S_High.get(k) - S_High.get(k - TimeSlice)) / (S_High.get(k - TimeSlice))   );
					k -= TimeSlice;
				}
			} else if (S_High.size() % TimeSlice != 0){
				for(int i = 0; i < S_High.size() / TimeSlice; i++) {
					S_Percentage_Change.add(   (S_High.get(k) - S_High.get(k - TimeSlice)) / (S_High.get(k - TimeSlice))   );
					k -= TimeSlice;
				}
			}
		} else if (Category == "LOW") {
			S_Low = S.request("LOW", startdate, enddate);

			int k = S_Low.size() - 1;
			if(S_Low.size() % TimeSlice == 0) {
				for(int i = 0; i < (S_Low.size() / TimeSlice) - 1; i++) {
					S_Percentage_Change.add(   (S_Low.get(k) - S_Low.get(k - TimeSlice)) / (S_Low.get(k - TimeSlice))   );
					k -= TimeSlice;
				}
			} else if (S_Low.size() % TimeSlice != 0){
				for(int i = 0; i < S_Low.size() / TimeSlice; i++) {
					S_Percentage_Change.add(   (S_Low.get(k) - S_Low.get(k - TimeSlice)) / (S_Low.get(k - TimeSlice))   );
					k -= TimeSlice;
				}
			}
		} else if (Category == "VOLUME") {
			S_Volume = S.request("VOLUME", startdate, enddate);

			int k = S_Volume.size() - 1;
			if(S_Volume.size() % TimeSlice == 0) {
				for(int i = 0; i < (S_Volume.size() / TimeSlice) - 1; i++) {
					S_Percentage_Change.add(   (S_Volume.get(k) - S_Volume.get(k - TimeSlice)) / (S_Volume.get(k - TimeSlice))   );
					k -= TimeSlice;
				}
			} else if (S_Volume.size() % TimeSlice != 0){
				for(int i = 0; i < S_Volume.size() / TimeSlice; i++) {
					S_Percentage_Change.add(   (S_Volume.get(k) - S_Volume.get(k - TimeSlice)) / (S_Volume.get(k - TimeSlice))   );
					k -= TimeSlice;
				}
			}
		} else if (Category == "ADJ_CLOSE") {
			S_Adj_Close = S.request(Category, startdate, enddate);

			int k = S_Adj_Close.size() - 1;
			if(S_Adj_Close.size() % TimeSlice == 0) {
				for(int i = 0; i < (S_Adj_Close.size() / TimeSlice) - 1; i++) {
					S_Percentage_Change.add(   (S_Adj_Close.get(k) - S_Adj_Close.get(k - TimeSlice)) / (S_Adj_Close.get(k - TimeSlice))   );
					k -= TimeSlice;
				}
			} else if (S_Adj_Close.size() % TimeSlice != 0){
				for(int i = 0; i < S_Adj_Close.size() / TimeSlice; i++) {
					S_Percentage_Change.add(   (S_Adj_Close.get(k) - S_Adj_Close.get(k - TimeSlice)) / (S_Adj_Close.get(k - TimeSlice))   );
					k -= TimeSlice;
				}
			}
		}
		
		/*
		 * Reversing
		 */
		
		ArrayList<Double> revtmp = new ArrayList<Double>();
		for(int i = S_Percentage_Change.size() - 1; i >= 0; i--) {
			revtmp.add(S_Percentage_Change.get(i));
		}
		S_Percentage_Change.clear();
		for(int i = 0; i < revtmp.size(); i++) {
			S_Percentage_Change.add(revtmp.get(i));
		}
		return S_Percentage_Change;
	}
	public ArrayList ReturnDeviation(String Category, int TimeSlice, String startdate, String enddate, String Type) {
		System.out.println("Calculating Return Deviation for " + S.SYMBOL);
		S_ReturnDeviation = new ArrayList<Double>();
		ReturnGeomean = 0.d;
		ReturnArimean = 0.d;
		/*
		 * I could have a price variance and return variance.
		 * Normally, a return variance is required, so return variance first,
		 * and if I will have some more time, make calculations for price variance
		 * 
		 * Gotta make mean return function.
		 * 
		 * String Type is for Geometric or Arithmetic
		 */
		if(S.request(Category, startdate, enddate) == null) {
			System.out.println("Could not request the stock data => Error from Variance()");
		} else if(S.request(Category, startdate, enddate) != null && Type.equals("GEO")) {
			this.MeanReturnGeometric(Category, TimeSlice, startdate, enddate); // Geometric mean will be stored in the instance
			// Sigma^2 = addall(  (Xi - u)^2  ) / N
			for(int i = 0; i < S_Percentage_Change.size(); i++) {
				Double tmp = 0.d;
				tmp = (S_Percentage_Change.get(i) - ReturnGeomean);
				S_ReturnDeviation.add(tmp);
			}
			return S_ReturnDeviation;
		} else if(S.request(Category, startdate, enddate) != null && Type.equals("ARI")) {
			this.MeanReturnArithmetic(Category, TimeSlice, startdate, enddate);
			for(int i = 0; i < S_Percentage_Change.size(); i++) {
				Double tmp = 0.d;
				tmp = (S_Percentage_Change.get(i) - ReturnArimean);
				S_ReturnDeviation.add(tmp);
			}
			return S_ReturnDeviation;
		}
		return S_ReturnDeviation;
	}
	public Double Variance_Return(String Category, int TimeSlice, String startdate, String enddate, String Type) {
		ReturnGeomean = 0.d;
		ReturnArimean = 0.d;
		ReturnDeviation(Category, TimeSlice, startdate, enddate, Type);
		Double ReturnTotal = 0.d;
		for(int i = 0; i < S_ReturnDeviation.size(); i++) {
			ReturnTotal += Math.pow(S_ReturnDeviation.get(i), 2.d);
		}
		S_Variance_Return = ReturnTotal / (S_ReturnDeviation.size() - 1); // -1 because it is a sample
		
		return S_Variance_Return;
	}
	public Double StandardDeviation_Return(String Category, int TimeSlice, String startdate, String enddate, String Type) {
		S_StandardDeviation_Return = 0.d;
		Variance_Return(Category, TimeSlice, startdate, enddate, Type);
		S_StandardDeviation_Return = Math.pow(S_Variance_Return, 0.5d);
		
		return S_StandardDeviation_Return;
	}
	
	private void MinMax() {
		//Max / Min
		ReturnMax = 0.d;
		ReturnMin = 0.d;
		Double tmp = 0.d;
		Iterator itr = S_Percentage_Change.iterator();
		while(itr.hasNext()) {
			tmp = (Double) itr.next();
			if(tmp >= ReturnMax) {
				ReturnMax = tmp;
			}
			if(tmp <= ReturnMin) {
				ReturnMin = tmp;
			}
		}
	}
}
