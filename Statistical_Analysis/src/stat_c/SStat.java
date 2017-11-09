package stat_c;
import java.util.*;
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
 * 
 */
public class SStat {
	Stock S1;
	Stock S2;
	
	Double total = 0.d; // Being used by Geometric and Arithmetic mean. Variable should be declared inside for totalling purpose.
	Double Geomean = 0.d;
	Double Arimean = 0.d;
	Double ReturnGeomean = 0.d;
	Double ReturnArimean = 0.d;
	Double S1_MeanVariance = 0.d;
	Double S2_MeanVariance = 0.d;
	
	//Percentage Arrays
	ArrayList<Double> S1_Percentage_Change = new ArrayList<Double>();
	ArrayList<Double> S2_Percentage_Change = new ArrayList<Double>();
	
	//Variance Arrays ::::: Variance is a difference between individual stock prices and its mean.
	ArrayList<Double> S1_ReturnVariance = new ArrayList<Double>();
	ArrayList<Double> S2_ReturnVariance = new ArrayList<Double>();
	ArrayList<Double> S1_PriceVariance = new ArrayList<Double>();
	ArrayList<Double> S2_PriceVariance = new ArrayList<Double>();
	
	//Beta Arrays  ::::: Beta is a variance of an individual stock price derived from the index.
	ArrayList<Double> S1_Beta = new ArrayList<Double>();
	ArrayList<Double> S2_Beta = new ArrayList<Double>();
	
	//Might not have to use these. These lists are all stored in a stock class
	ArrayList<Double> S1_Close = new ArrayList<Double>();
	ArrayList<Double> S2_Close = new ArrayList<Double>();
	ArrayList<Double> S1_Open = new ArrayList<Double>();
	ArrayList<Double> S2_Open = new ArrayList<Double>();
	ArrayList<Double> S1_High = new ArrayList<Double>();
	ArrayList<Double> S2_High = new ArrayList<Double>();
	ArrayList<Double> S1_Low = new ArrayList<Double>();
	ArrayList<Double> S2_Low = new ArrayList<Double>();
	ArrayList<Double> S1_Volume = new ArrayList<Double>();
	ArrayList<Double> S2_Volume = new ArrayList<Double>();
	//
	
	public SStat(Stock S1) {
		this.S1 = S1;
		
		//Default operations with "Close" Price, 1 year
		//Arithmeticmean("CLOSE", )
	}
	SStat(Stock S1, Stock S2, String Category) {
		this.S1 = S1;
		this.S2 = S2;
	}
	
	public Double Arithmeticmean(String Category, String startdate, String enddate) {
		total = 0.d; //Initialize
		Arimean = 0.d;
		String cat = Category.toUpperCase();
		if(S1.request(Category, startdate, enddate) == null) {
			System.out.println("Error occurred");
			return 0.d;
		} else if (Category == "OPEN" || Category == "Open"){
			S1_Open = S1.request(Category, startdate, enddate);
			for(int i = 0; i < S1_Open.size(); i++) {
				total += S1_Open.get(i);
			}
			Arimean = total / S1_Open.size();
			
			return Arimean;
		} else if (Category == "CLOSE" || Category == "Close"){
			S1_Close = S1.request(Category, startdate, enddate);
			for(int i = 0; i < S1_Close.size(); i++) {
				total += S1_Close.get(i);
			}
			Arimean = total / S1_Close.size();
			
			return Arimean;
		} else if (Category == "High" || Category == "HIGH"){
			S1_High = S1.request(Category, startdate, enddate);
			for(int i = 0; i < S1_High.size(); i++) {
				total += S1_High.get(i);
			}
			Arimean = total / S1_High.size();
			
			return Arimean;
		} else if (Category == "Volume" || Category == "VOLUME"){
			S1_Volume = S1.request(Category, startdate, enddate);
			for(int i = 0; i < S1_Volume.size(); i++) {
				total += S1_Volume.get(i);
			}
			Arimean = total / S1_Volume.size();
			
			return Arimean;
		} else if (Category == "Low" || Category == "LOW"){
			S1_Low = S1.request(Category, startdate, enddate);
			for(int i = 0; i < S1_Low.size(); i++) {
				total += S1_Low.get(i);
			}
			Arimean = total / S1_Low.size();
			
			return Arimean;
		} else {
			return 0.d;
		}
	}
	public Double Geometricmean(String Category, String startdate, String enddate) {
		total = 1.d; //Initialize
		Geomean = 0.d;
		String cat = Category.toUpperCase();
		if(S1.request(Category, startdate, enddate) == null) {
			System.out.println("Error occurred");
			return 0.d;
		} else if (Category == "OPEN" || Category == "Open"){
			S1_Open = S1.request(Category, startdate, enddate);
			for(int i = 0; i < S1_Open.size(); i++) {
				total *= S1_Open.get(i);
			}
			Geomean = Math.pow(total, (1.d / S1_Open.size()));
			
			total = 0.d;
			return Geomean;
		} else if (Category == "CLOSE" || Category == "Close"){
			S1_Close = S1.request("CLOSE", startdate, enddate);
			for(int i = 0; i < S1_Close.size(); i++) {
				total *= S1_Close.get(i);
			}
			Geomean = Math.pow(total, Double.valueOf(1.0d / Double.valueOf(S1_Close.size())));
			
			total = 0.d;
			return Geomean;
		} else if (Category == "High" || Category == "HIGH"){
			S1_High = S1.request(Category, startdate, enddate);
			for(int i = 0; i < S1_High.size(); i++) {
				total *= S1_High.get(i);
			}
			Geomean = Math.pow(total, (1.0d / S1_High.size()));
			
			total = 0.d;
			return Geomean;
		} else if (Category == "Volume" || Category == "VOLUME"){
			S1_Volume = S1.request(Category, startdate, enddate);
			for(int i = 0; i < S1_Volume.size(); i++) {
				total *= S1_Volume.get(i);
			}
			Geomean = Math.pow(total, (1.0d / S1_Volume.size()));
			
			total = 0.d;
			return Geomean;
		} else if (Category == "Low" || Category == "LOW"){
			S1_Low = S1.request(Category, startdate, enddate);
			for(int i = 0; i < S1_Low.size(); i++) {
				total *= S1_Low.get(i);
			}
			Geomean = Math.pow(total, (1.d / S1_Low.size()));
			
			total = 0.d;
			return Geomean;
		} else {
			return 0.d;
		}
	}
	public Double MeanReturnGeometric(String Category, int TimeSlice, String startdate, String enddate) {
		this.S1_ReturnVariance = new ArrayList<Double>();
		ReturnGeomean = 1.d;
		
		if(S1.request(Category, startdate, enddate) == null) {
			System.out.println("An Error Occured in MeanReturnGeometric()");
		} else if (S1.request(Category, startdate, enddate) != null && Category == "CLOSE") {
			S1_Percentage_Change = PercentageChange(Category, TimeSlice, startdate, enddate);
			
			for(int i = 0; i < S1_Percentage_Change.size(); i++) {
				ReturnGeomean *= (1 + S1_Percentage_Change.get(i)); //When calculating a geometric mean, you have to add 1 to each returns
			}
			ReturnGeomean = Math.pow(ReturnGeomean, (1.0d / S1_Percentage_Change.size())) - 1;
			return ReturnGeomean;
		} else if (S1.request(Category, startdate, enddate) != null && Category == "OPEN") {
			S1_Percentage_Change = PercentageChange(Category, TimeSlice, startdate, enddate);
			
			for(int i = 0; i < S1_Percentage_Change.size(); i++) {
				ReturnGeomean *= (1 + S1_Percentage_Change.get(i));
			}
			ReturnGeomean = Math.pow(ReturnGeomean, (1.0d / S1_Percentage_Change.size()));
			return ReturnGeomean;
		} else if (S1.request(Category, startdate, enddate) != null && Category == "HIGH") {
			S1_Percentage_Change = PercentageChange(Category, TimeSlice, startdate, enddate);
			
			for(int i = 0; i < S1_Percentage_Change.size(); i++) {
				ReturnGeomean *= (1 + S1_Percentage_Change.get(i));
			}
			ReturnGeomean = Math.pow(ReturnGeomean, (1.0d / S1_Percentage_Change.size()));
			return ReturnGeomean;
		} else if (S1.request(Category, startdate, enddate) != null && Category == "LOW") {
			S1_Percentage_Change = PercentageChange(Category, TimeSlice, startdate, enddate);
			
			for(int i = 0; i < S1_Percentage_Change.size(); i++) {
				ReturnGeomean *= (1 + S1_Percentage_Change.get(i));
			}
			ReturnGeomean = Math.pow(ReturnGeomean, (1.0d / S1_Percentage_Change.size()));
			return ReturnGeomean;
		} else if (S1.request(Category, startdate, enddate) != null && Category == "VOLUME") { 
			// There is no such thing as a return in volume, but I am putting this here just because I want to use
			// Volume percentage change as a research data.
			S1_Percentage_Change = PercentageChange(Category, TimeSlice, startdate, enddate);
			
			for(int i = 0; i < S1_Percentage_Change.size(); i++) {
				ReturnGeomean *= (1 + S1_Percentage_Change.get(i));
			}
			ReturnGeomean = Math.pow(ReturnGeomean, (1.0d / S1_Percentage_Change.size()));
			return ReturnGeomean;
		} else {
			System.out.print("Wrong parameters in MeanReturnGeometric()");
		}
		
		
		return ReturnGeomean;
	}
	public ArrayList PercentageChange(String Category, int TimeSlice, String startdate, String enddate) {
		S1_Percentage_Change = new ArrayList<Double>();
		/*
		 * TimeSlice : Monthly, Daily, Yearly ... give it by numbers(days)
		 * Local Variable must be nullified.
		 */
		
		if(S1.request(Category, startdate, enddate) == null) {
			System.out.println("An Error Occured");
		} else if (Category == "CLOSE") {
			S1_Close = S1.request("CLOSE", startdate, enddate);
			
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
			//System.out.println(S1_Close.size());
			
			int k = S1_Close.size() - 1;
			if(S1_Close.size() % TimeSlice == 0) {
				for(int i = 0; i < (S1_Close.size() / TimeSlice) - 1; i++) {
					//System.out.println(S1_Close.get(k) + "   " + S1_Close.get(k - TimeSlice));
					S1_Percentage_Change.add(   (S1_Close.get(k) - S1_Close.get(k - TimeSlice)) / (S1_Close.get(k - TimeSlice))   );
					k -= TimeSlice;
				}
			} else if (S1_Close.size() % TimeSlice != 0){
				for(int i = 0; i < S1_Close.size() / TimeSlice; i++) {
					//System.out.println(S1_Close.get(k) + "   " + S1_Close.get(k - TimeSlice));
					S1_Percentage_Change.add(   (S1_Close.get(k) - S1_Close.get(k - TimeSlice)) / (S1_Close.get(k - TimeSlice))   );
					k -= TimeSlice;
				}
			}
			return S1_Percentage_Change;
		} else if (Category == "OPEN") {
			S1_Open = S1.request("OPEN", startdate, enddate);

			//System.out.println(S1_Open.size());
			
			int k = S1_Open.size() - 1;
			if(S1_Open.size() % TimeSlice == 0) {
				for(int i = 0; i < (S1_Open.size() / TimeSlice) - 1; i++) {
					//System.out.println(S1_Open.get(k) + "   " + S1_Open.get(k - TimeSlice));
					S1_Percentage_Change.add(   (S1_Open.get(k) - S1_Open.get(k - TimeSlice)) / (S1_Open.get(k - TimeSlice))   );
					k -= TimeSlice;
				}
			} else if (S1_Open.size() % TimeSlice != 0){
				for(int i = 0; i < S1_Open.size() / TimeSlice; i++) {
					//System.out.println(S1_Open.get(k) + "   " + S1_Open.get(k - TimeSlice));
					S1_Percentage_Change.add(   (S1_Open.get(k) - S1_Open.get(k - TimeSlice)) / (S1_Open.get(k - TimeSlice))   );
					k -= TimeSlice;
				}
			}
			return S1_Percentage_Change;
		} else if (Category == "HIGH") {
			S1_High = S1.request("HIGH", startdate, enddate);

			int k = S1_High.size() - 1;
			if(S1_High.size() % TimeSlice == 0) {
				for(int i = 0; i < (S1_High.size() / TimeSlice) - 1; i++) {
					S1_Percentage_Change.add(   (S1_High.get(k) - S1_High.get(k - TimeSlice)) / (S1_High.get(k - TimeSlice))   );
					k -= TimeSlice;
				}
			} else if (S1_High.size() % TimeSlice != 0){
				for(int i = 0; i < S1_High.size() / TimeSlice; i++) {
					S1_Percentage_Change.add(   (S1_High.get(k) - S1_High.get(k - TimeSlice)) / (S1_High.get(k - TimeSlice))   );
					k -= TimeSlice;
				}
			}
		} else if (Category == "LOW") {
			S1_Low = S1.request("LOW", startdate, enddate);

			int k = S1_Low.size() - 1;
			if(S1_Low.size() % TimeSlice == 0) {
				for(int i = 0; i < (S1_Low.size() / TimeSlice) - 1; i++) {
					S1_Percentage_Change.add(   (S1_Low.get(k) - S1_Low.get(k - TimeSlice)) / (S1_Low.get(k - TimeSlice))   );
					k -= TimeSlice;
				}
			} else if (S1_Low.size() % TimeSlice != 0){
				for(int i = 0; i < S1_Low.size() / TimeSlice; i++) {
					S1_Percentage_Change.add(   (S1_Low.get(k) - S1_Low.get(k - TimeSlice)) / (S1_Low.get(k - TimeSlice))   );
					k -= TimeSlice;
				}
			}
		} else if (Category == "VOLUME") {
			S1_Volume = S1.request("VOLUME", startdate, enddate);

			int k = S1_Volume.size() - 1;
			if(S1_Volume.size() % TimeSlice == 0) {
				for(int i = 0; i < (S1_Volume.size() / TimeSlice) - 1; i++) {
					S1_Percentage_Change.add(   (S1_Volume.get(k) - S1_Volume.get(k - TimeSlice)) / (S1_Volume.get(k - TimeSlice))   );
					k -= TimeSlice;
				}
			} else if (S1_Volume.size() % TimeSlice != 0){
				for(int i = 0; i < S1_Volume.size() / TimeSlice; i++) {
					S1_Percentage_Change.add(   (S1_Volume.get(k) - S1_Volume.get(k - TimeSlice)) / (S1_Volume.get(k - TimeSlice))   );
					k -= TimeSlice;
				}
			}
		}
		return S1_Percentage_Change;
	}
	public ArrayList ReturnVariance(String Category, int TimeSlice, String startdate, String enddate) {
		S1_ReturnVariance = new ArrayList<Double>();
		/*
		 * I could have a price variance and return variance.
		 * Normally, a return variance is required, so return variance first,
		 * and if I will have some more time, make calculations for price variance
		 * 
		 * Gotta make mean return function.
		 */
		if(S1.request(Category, startdate, enddate) == null) {
			System.out.println("Could not request the stock data => Error from Variance()");
		} else if(S1.request(Category, startdate, enddate) != null) {
			this.MeanReturnGeometric(Category, TimeSlice, startdate, enddate); // Geometric mean will be stored in the instance
			
			for(int i = 0; i < S1_Percentage_Change.size(); i++) {
				S1_ReturnVariance.add(S1_Percentage_Change.get(i) - ReturnGeomean); 
			}
			return S1_ReturnVariance;
			
		}
		return S1_ReturnVariance;
	}
	public Double MeanReturnVariance(String Category, int TimeSlice, String startdate, String enddate) {
		ReturnVariance(Category, TimeSlice, startdate, enddate);
		Double ReturnTotal = 0.d;
		for(int i = 0; i < S1_ReturnVariance.size(); i++) {
			ReturnTotal += Math.pow(S1_ReturnVariance.get(i), 2.d);
		}
		S1_MeanVariance = Math.pow(ReturnTotal, (1.0d /S1_ReturnVariance.size()));
		
		return S1_MeanVariance;
	}
	
}
