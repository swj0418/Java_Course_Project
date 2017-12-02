package black;

import java.util.ArrayList;
import java.util.Iterator;

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
		
		tmp = Return(A1, TimeSlice);
		
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
	
	public final static Double MinMax(ArrayList A1, String type) {
		type = type.toUpperCase();
		
		Double ReturnMax = 0.d;
		Double ReturnMin = 0.d;
		Double tmp = 0.d;
		Iterator itr = A1.iterator();
		while(itr.hasNext()) {
			tmp = (Double) itr.next();
			if(tmp >= ReturnMax) {
				ReturnMax = tmp;
			}
			if(tmp <= ReturnMin) {
				ReturnMin = tmp;
			}
		}
		
		if(type.equals("MAX")) {
			return ReturnMax;
		} 
		else if(type.equals("MIN")) {
			return ReturnMin;
		}
		else {
			System.out.println("Wrong input for Min_Max");
			return null;
		}
	}
	public final static Double Covariance(ArrayList<Double> Price1, ArrayList<Double> Price2) {
		String defaulttype = "ARITHMETIC";
		int defaulttimeslice = 1;
		return Covariance(Price1, Price2, defaulttimeslice, defaulttype);
	}
	
	public final static Double Covariance(ArrayList<Double> Price1, ArrayList<Double> Price2, int TimeSlice) {
		String defaulttype = "ARITHMETIC";
		return Covariance(Price1, Price2, TimeSlice, defaulttype);
	}
	
	public final static Double Covariance(ArrayList<Double> Price1, ArrayList<Double> Price2, int TimeSlice, String Type) {
		Double covariance = 0.d;
		ArrayList<Double> tmpDev1 = ReturnDeviation(Price1, TimeSlice, Type);
		ArrayList<Double> tmpDev2 = ReturnDeviation(Price2, TimeSlice, Type);
		
		for(int i = 0; i < tmpDev1.size(); i++) {
			covariance += (tmpDev1.get(i) * tmpDev2.get(i));
		}
		covariance = covariance / (tmpDev1.size() - 1.d);
		
		return covariance;
	}
	
	public final static Double Correlation(ArrayList<Double> Price1, ArrayList<Double> Price2) {
		int defaulttimeslice = 1;
		String defaulttype = "ARITHMETIC";
		return Correlation(Price1, Price2, defaulttimeslice, defaulttype);
	}
	
	public final static Double Correlation(ArrayList<Double> Price1, ArrayList<Double> Price2, int TimeSlice) {
		String defaulttype = "ARITHMETIC";
		return Correlation(Price1, Price2, TimeSlice, defaulttype);
	}
	
	public final static Double Correlation(ArrayList<Double> Price1, ArrayList<Double> Price2, int TimeSlice, String Type) {
		Double tmpCov = Covariance(Price1, Price2, TimeSlice, Type);
		Double SD1 = StandardDeviation(Price1, TimeSlice, Type);
		Double SD2 = StandardDeviation(Price2, TimeSlice, Type);
		
		Double correlation = tmpCov / (SD1*SD2);
		if(correlation > 0.9999999) {
			correlation = 1.0d;
		}
		
		int tmp = 0;
		tmp = (int) (correlation * 100000.d);
		correlation = tmp / 100000.d;
		
		return correlation;
	}
	
	public final static ArrayList<ArrayList<Double>> CorrelationMatrix(ArrayList<ArrayList<Double>> PriceMatrix) {
		int defaulttimeslice = 1;
		String defaulttype = "ARITHMETIC";
		return CorrelationMatrix(PriceMatrix, defaulttimeslice, defaulttype);
	}
	
	public final static ArrayList<ArrayList<Double>> CorrelationMatrix(ArrayList<ArrayList<Double>> PriceMatrix, int TimeSlice) {
		String defaulttype = "ARITHMETIC";
		return CorrelationMatrix(PriceMatrix, TimeSlice, defaulttype);
	}
	
	public final static ArrayList<ArrayList<Double>> CorrelationMatrix(ArrayList<ArrayList<Double>> PriceMatrix, int TimeSlice, String Type) {
		ArrayList<ArrayList<Double>> retArr = new ArrayList<ArrayList<Double>>();
		
		for(int i = 0; i < PriceMatrix.size(); i++) {
			ArrayList<Double> tmp = new ArrayList<Double>();
			
			for(int j = 0; j < PriceMatrix.size(); j++) {
				tmp.add(Correlation(PriceMatrix.get(i), PriceMatrix.get(j), TimeSlice, Type));
			}
			retArr.add(tmp);
		}
		
		return retArr;
	}
	
	public final static ArrayList<Double> AdjustOpen(ArrayList<Double> ADJ_CLOSE, ArrayList<Double> CLOSE, ArrayList<Double> OPEN) {
		ArrayList<Double> retArr = new ArrayList<Double>();
		for(int i = 0; i < ADJ_CLOSE.size(); i++) {
			retArr.add(OPEN.get(i) + ((CLOSE.get(i) - ADJ_CLOSE.get(i))));
		}
		return retArr;
	}
}
