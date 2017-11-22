package black;

import java.util.ArrayList;

public final class Stoculator {
	public final static ArrayList<Double> Return(ArrayList<Double> A1) {
		int TimeSlice = 1; //Default
		return Return(A1, TimeSlice);
	}
	
	public final static ArrayList<Double> Return(ArrayList<Double> A1, int TimeSlice) {
		ArrayList<Double> P_Change = new ArrayList<Double>();
		int k = A1.size() - 1;
		if(A1.size() % TimeSlice == 0) {
			for(int i = 0; i < (A1.size() / TimeSlice) - 1; i++) {
				P_Change.add(   (A1.get(k) - A1.get(k - TimeSlice)) / (A1.get(k - TimeSlice))   );
				k -= TimeSlice;
			}
		} else if (A1.size() % TimeSlice != 0){
			for(int i = 0; i < A1.size() / TimeSlice; i++) {
				P_Change.add(   (A1.get(k) - A1.get(k - TimeSlice)) / (A1.get(k - TimeSlice))   );
				k -= TimeSlice;
			}
		}
		return P_Change;
	}
	public final static Double ArithmeticMeanReturn(ArrayList<Double> A1) {
		int defaulttimeslice = 1;
		return ArithmeticMeanReturn(A1, defaulttimeslice);
	}
	
	@SuppressWarnings("unchecked")
	public final static Double ArithmeticMeanReturn(ArrayList<Double> A1, int TimeSlice) {
		Double arithmeticmeanreturn = 0.d;
		Double tmptotal = 0.d;
		ArrayList<Double> tmp = new ArrayList<Double>();
		tmp = Return(A1, TimeSlice);
		
		for(int i = 0; i < tmp.size(); i++) {
			tmptotal += tmp.get(i);
		}
		arithmeticmeanreturn = (tmptotal / (tmp.size() - 1));
		return arithmeticmeanreturn;
	}
	
	public final static Double GeometricMeanReturn(ArrayList<Double> A1) {
		int defaulttimeslice = 1;
		return GeometricMeanReturn(A1, defaulttimeslice);
	}
	
	@SuppressWarnings("unchecked")
	public final static Double GeometricMeanReturn(ArrayList<Double> A1, int TimeSlice) {
		Double geometricmeanreturn = 0.d;
		Double tmptotal = 1.0d;
		ArrayList<Double> tmp = new ArrayList<Double>();
		tmp = Return(A1, TimeSlice);
		
		for(int i = 0; i < tmp.size(); i++) {
			tmptotal *= (1.d + tmp.get(i));
		}
		geometricmeanreturn = Math.pow(tmptotal, (1.d / (tmp.size() - 1.d))) - 1.d;
		return geometricmeanreturn;
	}
	
	public final static ArrayList ReturnDeviation(ArrayList<Double> A1) {
		String defaulttype = "ARITHMETIC";
		int defaulttimeslice = 1;
		return ReturnDeviation(A1, defaulttimeslice, defaulttype);
	}
	
	@SuppressWarnings("unchecked")
	public final static ArrayList ReturnDeviation(ArrayList<Double> A1, int TimeSlice, String type) {
		ArrayList<Double> returndeviation = new ArrayList<Double>();
		ArrayList<Double> tmp = new ArrayList<Double>();
		Double mean = 0.d;
		type = type.toUpperCase();
		
		switch(type) {
		case "ARITHMETIC" :
			mean = ArithmeticMeanReturn(A1, TimeSlice);
		case "GEOMETRIC" :
			mean = GeometricMeanReturn(A1, TimeSlice);
		case "ARI" :
			mean = ArithmeticMeanReturn(A1, TimeSlice);
		case "GEO" :
			mean = GeometricMeanReturn(A1, TimeSlice);
		}
		
		tmp = (ArrayList<Double>) Return(A1, TimeSlice).clone();
		
		for(int i = 0; i < tmp.size(); i++) {
			returndeviation.add(tmp.get(i) - mean);
		}
		return returndeviation;
	}
	
	public final static Double ReturnVariance(ArrayList<Double> A1) {
		String defaulttype = "ARITHMETIC";
		int defaulttimeslice = 1;
		return ReturnVariance(A1, defaulttimeslice, defaulttype);
	}
	
	@SuppressWarnings("unchecked")
	public final static Double ReturnVariance(ArrayList<Double> A1, int TimeSlice, String type) {
		Double returnvariance = 0.d;
		ArrayList<Double> deviation = new ArrayList<Double>();
		ArrayList<Double> variance = new ArrayList<Double>();
		deviation = (ArrayList<Double>) ReturnDeviation(A1, TimeSlice, type).clone();
		
		for(int i = 0; i < deviation.size(); i++) {
			variance.add(Math.pow(deviation.get(i), 2));
			returnvariance += variance.get(i);
		}
		returnvariance = returnvariance / (deviation.size() - 1);
		
		return returnvariance;
	}
	
	public final static Double StandardDeviation(ArrayList<Double> A1) {
		String defaulttype = "ARITHMETIC";
		int defaulttimeslice = 1;
		return StandardDeviation(A1, defaulttimeslice, defaulttype);
	}
	
	public final static Double StandardDeviation(ArrayList<Double> A1, int TimeSlice, String type) {

		return Math.pow(ReturnVariance(A1, TimeSlice, type), 0.5d);
	}
	
	public final static Double Covariance(ArrayList<Double> A1, ArrayList<Double> A2) {
		Double covariance = 0.d;
		
		return covariance;
	}

}
