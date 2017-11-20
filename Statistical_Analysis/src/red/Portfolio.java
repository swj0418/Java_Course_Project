package red;
import java.util.ArrayList;

import org.apache.commons.math3.*;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/*
 * Portfolio Class.
 * I originally designed SStat Class to convey statistics for two stock data, but it would incur too much lines of code in one class.
 * It turns out, however, I remembered that if I wanted to construct a portfolio that contains more than two stocks,
 *  I didn't have to put every stocks altogether, but rather, I could update them by adding in single stock data.
 *  
 *  
 *  Covariance
 *  Correlation Coefficient
 */
public class Portfolio {
	public Portfolio    P1; public Statistics P1S;
	public Portfolio    P2; public Statistics P2S;
	public Stock S1; public Statistics S1S; public Double S1_Weight = .5d;
	public Stock S2; public Statistics S2S; public Double S2_Weight = .5d;
	public Double S1_Expected_Return = 0.d;
	public Double S2_Expected_Return = 0.d;
	
	public Double S1_meanReturn = 0.d;
	public Double S2_meanReturn = 0.d;

	private int Expectation1;
	private int Expectation2;
	private String Category; private int TimeSlice; private String startdate; private String enddate; private String Type;
	
	public Double Corr;
	public Double Cov;
	public Double Portfolio_ExpectedReturn;
	public Double Portfolio_Variance;
	public Double Portfolio_StandardDeviation;
	
	public ArrayList<Double> Risk_Return_CurveArray_Return;
	public ArrayList<Double> Risk_Return_CurveArray_Risk;
	

	public Portfolio(Stock S1, Stock S2, String Category, int TimeSlice, String startdate, String enddate, String Type, Boolean autocomplete,
			Double S1_Weight, Double S2_Weight, int ExpectationS1, int ExpectationS2) { 
		// Expectation range 0~ 100 (0 : Stock Crash 100 : BoomBoom
		this.Expectation1 = Expectation1;
		this.Expectation2 = Expectation2;
		this.Category = Category; this.TimeSlice = TimeSlice; this.startdate = startdate; this.enddate = enddate; this.Type = Type;
		
		this.S1 = S1; S1S = new Statistics(S1, Category, TimeSlice, startdate, enddate, Type, autocomplete);
		this.S2 = S2; S2S = new Statistics(S2, Category, TimeSlice, startdate, enddate, Type, autocomplete);
		this.S1_Weight = S1_Weight;
		this.S2_Weight = S2_Weight;
		
		if(Type.equals("ARI")) {
			this.S1_meanReturn = S1S.ReturnArimean;
			this.S2_meanReturn = S2S.ReturnArimean;
		} else if(Type.equals("GEO")) {
			this.S1_meanReturn = S1S.ReturnGeomean;
			this.S2_meanReturn = S2S.ReturnGeomean;
		}
		Covariance();
		Correlation();
		
		//One time usage
		DescriptiveStatistics DesS1 = new DescriptiveStatistics();
		DescriptiveStatistics DesS2 = new DescriptiveStatistics();
		
		for(int i = 0; i < this.S1S.S_Percentage_Change.size(); i++) {
			DesS1.addValue(S1S.S_Percentage_Change.get(i));
			DesS2.addValue(S2S.S_Percentage_Change.get(i));
		}
		S1_Expected_Return = DesS1.getPercentile(ExpectationS1);
		S2_Expected_Return = DesS2.getPercentile(ExpectationS2);
		/*
		 * Note : This kind of stock price expectation is not an established theory.
		 * 		  Not an algorithm for serious stock analysis, but still, it can be developed as a theory
		 * 		  if I can prove grounds.
		 */
		PortfolioReturn();
		PortfolioVariance();
		PortfolioStandardDeviation();
		
		Risk_Return();
	}
	
	//For more than two stocks
	public Portfolio(Portfolio port, Stock addingS) {
		
	}
	
	//Merging Portfolios
	public Portfolio(Portfolio port1, Portfolio port2) {
		
	}
	
	public Double Covariance() {
		Double tmp = 0.d;
		
		for(int i = 0; i < S1S.S_Percentage_Change.size(); i++) {
			tmp += (S1S.S_Percentage_Change.get(i) - S1_meanReturn) * (S2S.S_Percentage_Change.get(i) - S2_meanReturn);
		}
		Cov = tmp / (S1S.S_Percentage_Change.size() - 1);
		tmp = (double) ((int)(Cov * 1000000));
		Cov = tmp / 1000000;
		
		Correlation();
		
		return Cov;
	}
	
	public Double Correlation() {
		Double tmp = 0.d;
		
		tmp = Cov / (S1S.S_StandardDeviation_Return * S2S.S_StandardDeviation_Return);
		Corr = tmp;
		
		return Corr;
	}
	private Double PortfolioReturn() {
		this.Portfolio_ExpectedReturn = (this.S1_Expected_Return * S1_Weight) + (this.S2_Expected_Return * S2_Weight);
		Double tmp = Portfolio_ExpectedReturn;
		return tmp;
	}
	private Double PortfolioVariance() {
		this.Portfolio_Variance = (Math.pow(S1_Weight, 2) * this.S1S.S_Variance_Return) + 
				(Math.pow(S2_Weight, 2) * this.S2S.S_Variance_Return) +
				(2.d * S1_Weight * S2_Weight * Cov);
		Double tmp = Portfolio_Variance;
		return tmp;
	}
	private Double PortfolioReturn(Double W1, Double W2) {
		Double tmpReturn = (this.S1_Expected_Return * W1) + (this.S2_Expected_Return * W2);
		return tmpReturn;
	}
	private Double PortfolioVariance(Double W1, Double W2) {
		Double tmpVar = (Math.pow(W1, 2) * this.S1S.S_Variance_Return) + 
				(Math.pow(W2, 2) * this.S2S.S_Variance_Return) +
				(2.d * W1 * W2 * Cov);
		return tmpVar;
	}
	private void PortfolioStandardDeviation() {
		Portfolio_StandardDeviation = Math.pow(Portfolio_Variance, 0.5d);
	}
	
	public ArrayList Risk_Return() {
		Risk_Return_CurveArray_Return = new ArrayList<Double>();
		Risk_Return_CurveArray_Risk   = new ArrayList<Double>();
		for(int i = 0; i < 101; i++) {
			Risk_Return_CurveArray_Return.add(PortfolioReturn(i / 100.d, 1.d - (i / 100.d)));
			Risk_Return_CurveArray_Risk.add(Math.pow(PortfolioVariance(i / 100.d, 1.d - (i / 100.d)), 0.5d));
		}
		return this.Risk_Return_CurveArray_Return;
	}
}
