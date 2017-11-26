package black;
import java.util.*;


/*
 * Calculations used in construction portfolios.
 */
public class Portoculator {
	Portoculator() {};
	
	public static final Double PortfolioExpectedReturn(ArrayList<Double> Er, ArrayList<Double> W) {
		Double ExpectedPortfolioReturn = 0.d;
		
		for(int i = 0; i < Er.size(); i++) {
			ExpectedPortfolioReturn += (Er.get(i) * W.get(i));
		}
		return ExpectedPortfolioReturn;
	}
	
	public static final Double PortfolioVariance(ArrayList<Double> Var, ArrayList<Double> W) {
		Double PortfolioVariance = 0.d;
		
		for(int i = 0; i < Var.size(); i++) {
			PortfolioVariance += (Var.get(i) * Math.pow(W.get(i), 2.d));
		}
		
		return PortfolioVariance;
	}
	
	public static final Double PortfolioVariance(ArrayList<ArrayList<Double>> Price, ArrayList<Double> W, int TimeSlice, String Type)  {
		Double PortfolioVariance = 0.d;
		Double Covariance = 0.d;
		ArrayList<Double> Variances = new ArrayList<Double>();
		for(int i = 0; i < Price.size(); i++) {
			Variances.add(Stoculator.ReturnVariance(Price.get(i), TimeSlice, Type));
		}
		Covariance = CovarianceMultiple(Price, TimeSlice, Type);
		
		for(int i = 0; i < Price.size(); i++) {
			PortfolioVariance += (Variances.get(i) * Math.pow(W.get(i), 2.d));
		}
		//Temporary value to save weights multiplied
		Double tmpvalue = 1.d;
		for(int i = 0; i < Variances.size(); i++) {
			tmpvalue *= W.get(i);
		}
		tmpvalue = tmpvalue * Covariance;
		
		//Final Calculation
		PortfolioVariance = Math.pow(PortfolioVariance + tmpvalue, 0.5d);
		
		return PortfolioVariance;
	}
	
	public static final Double CovarianceMultiple(ArrayList<ArrayList<Double>> Price) {
		String defaulttype = "ARI";
		int defaulttimeslice = 1;
		return CovarianceMultiple(Price, defaulttimeslice, defaulttype);
	}
	
	public static final Double CovarianceMultiple(ArrayList<ArrayList<Double>> Price, int TimeSlice) {
		String defaulttype = "ARI";
		return CovarianceMultiple(Price, TimeSlice, defaulttype);
	}
	
	public static final Double CovarianceMultiple(ArrayList<ArrayList<Double>> Price, int TimeSlice, String Type) {
		ArrayList<Double> tmpmeanreturns = new ArrayList<Double>();
		ArrayList<ArrayList<Double>> tmpreturns = new ArrayList<ArrayList<Double>>();
		Double Covariance = 0.d;
		
		//Returns Calculation
		for(int i = 0; i < Price.size(); i++) {
			tmpreturns.add(Stoculator.Return(Price.get(i), TimeSlice));
		}
		
		//Mean Return Calculation
		Type = Type.toUpperCase();	
		if(Type == "ARI" || Type == "ARITHMETIC") {
			for(int i = 0; i < Price.size(); i++) {
				tmpmeanreturns.add(Stoculator.ArithmeticMeanReturn(Price.get(i), TimeSlice));
			}
		} else if(Type == "GEO" || Type == "GEOMETRIC") {
			for(int i = 0; i < Price.size(); i++) {
				tmpmeanreturns.add(Stoculator.GeometricMeanReturn(Price.get(i), TimeSlice));
			}
		} else {
			System.out.println("Wrong Parameter " + Type);
		}
		System.out.println("Mean Return : " + tmpmeanreturns); 
		
		//Covariance Calculation
		for(int i = 0; i < tmpreturns.get(0).size(); i++) {
			Double tmp = 1.d;
			for(int k = 0; k < tmpreturns.size(); k++) {
				tmp = tmp * (tmpreturns.get(k).get(i) - tmpmeanreturns.get(k));
			}
			Covariance += tmp;
		}
		Covariance = Covariance / (tmpreturns.get(0).size());
		return Covariance;
	}
}
