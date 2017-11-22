package red;

import java.time.LocalDate;
import java.util.*;
import black.*;

public class Statistics {
	private Stock stock;
	private String SYMBOL = "";
	private String DataType = "";
	private String CalcType = "ARITHMETIC";
	int TimeSlice = 1;
	String Startdate = "";
	String Enddate = "";
	
	Double ArithmeticMeanReturn = 0.d;
	Double GeometricMeanReturn = 0.d;
	Double Variance = 0.d;
	Double StandardDeviation = 0.d;
	
	private ArrayList<Double> PriceData;
	
	ArrayList<Double> PriceReturn;
	ArrayList<Double> TotalReturn; // Not yet
	ArrayList<Double> PriceDeviation;
	ArrayList<Double> ReturnDeviation;
	
	public Statistics() {
		System.out.println("Specify your stock by its Symbol");
	}
	
	public Statistics(String SYMBOL) {
		String defaultstartdate = "2016-01-01";
		String defaultenddate = Utils.GetLastDayOfTheTrade(SYMBOL);
		int defaulttimeslice = 1;
		String defaultdatatype = "ADJ_CLOSE";
		this.SYMBOL = SYMBOL;
		this.TimeSlice = defaulttimeslice;
		this.DataType = defaultdatatype;
		this.Startdate = defaultstartdate;
		this.Enddate = defaultenddate;
		
		AutoCalculate();
	}
	
	public Statistics(String SYMBOL, int TimeSlice) {
		String defaultstartdate = "2016-01-01";
		String defaultenddate = Utils.GetLastDayOfTheTrade(SYMBOL);
		String defaultdatatype = "ADJ_CLOSE";
		this.SYMBOL = SYMBOL;
		this.TimeSlice = TimeSlice;
		this.DataType = defaultdatatype;
		this.Startdate = defaultstartdate;
		this.Enddate = defaultenddate;
		
		AutoCalculate();
	}
	
	public Statistics(String SYMBOL, int TimeSlice, String DataType) {
		String defaultstartdate = "2016-01-01";
		String defaultenddate = Utils.GetLastDayOfTheTrade(SYMBOL);
		this.SYMBOL = SYMBOL;
		this.TimeSlice = TimeSlice;
		this.DataType = DataType;
		this.Startdate = defaultstartdate;
		this.Enddate = defaultenddate;
		
		AutoCalculate();
	}
	
	public Statistics(String SYMBOL, String Startdate, String Enddate, int TimeSlice, String DataType) {
		this.SYMBOL = SYMBOL;
		this.TimeSlice = TimeSlice;
		this.DataType = DataType;
		this.Startdate = Startdate;
		this.Enddate = Enddate;
		
		AutoCalculate();
	}
	
	@SuppressWarnings("unchecked")
	private void AutoCalculate() {
		Stock S = new Stock(SYMBOL);
		PriceData = S.request(DataType, Startdate, Enddate);
		
		ArithmeticMeanReturn = Stoculator.ArithmeticMeanReturn(PriceData, TimeSlice);
		GeometricMeanReturn = Stoculator.GeometricMeanReturn(PriceData, TimeSlice);
		Variance = Stoculator.ReturnVariance(PriceData, TimeSlice, CalcType);
		StandardDeviation = Stoculator.StandardDeviation(PriceData, TimeSlice, CalcType);
		
		ReturnDeviation = Stoculator.ReturnDeviation(PriceData, TimeSlice, CalcType);
		PriceReturn = Stoculator.Return(PriceData, TimeSlice);
	}
	
	public Double getArithmeticMeanReturn() {
		int tmp = (int) (ArithmeticMeanReturn * 10000.d);
		Double ret = tmp / 10000.d;
		return ret;
	}
	
	public Double getGeometricMeanReturn() {
		int tmp = (int) (GeometricMeanReturn * 10000.d);
		Double ret = tmp / 10000.d;
		return ret;
	}
	
	public Double getVariance() {
		int tmp = (int) (Variance * 10000.d);
		Double ret = tmp / 10000.d;
		return ret;
	}
	
	public Double getStandardDeviation() {
		int tmp = (int) (StandardDeviation * 10000.d);
		Double ret = tmp / 10000.d;
		return ret;
	}
}
