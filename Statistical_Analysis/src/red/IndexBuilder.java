package red;
import java.util.ArrayList;
import java.util.Iterator;


/*
 * Adjustable Index!
 * To match the time frame I am to analyze
 * Will have to give two indices.
 * But I must ignore stock splits for that I do not have in my hand those informations
 * And Market Capitalization... Don't have that info too.
 */

public class IndexBuilder {
	private int SmallestDataSetSize;
	private Double Weight_Equal;
	
	public ArrayList<Stock> Basket = new ArrayList<Stock>();
	
	private ArrayList<Double> PriceIndex = new ArrayList<Double>(); // ex) Dow Jones, Nikkei Dow Jones
	private ArrayList<Double> EqualWeightIndex = new ArrayList<Double>(); 
	private ArrayList<Double> MarketCapitalizationIndex = new ArrayList<Double>(); // ex) S&P 500, KOSPI
	
	private ArrayList<Double> PriceIndexWhole = new ArrayList<Double>();
	private ArrayList<Double> EqualWeightIndexWhole = new ArrayList<Double>();
	private ArrayList<Double> MarketCapitalizationIndexWhole = new ArrayList<Double>();
	
	IndexBuilder() {
		
	}
	public IndexBuilder(ArrayList<Stock> stocks) {
		Basket = stocks;
		SmallestDataMatcher();
		PriceIndex();
		//EqualWeightIndex();
	}
	
	private void PriceIndex() {
		ArrayList<Double> tmpArr = new ArrayList<Double>();
		for(int i = 0; i < SmallestDataSetSize; i++) {
			Double tmp = 0.d;
			for(int j = 0; j < Basket.size(); j ++) {
				tmp += Basket.get(j).Adj_Close.get(i);
			}
			tmpArr.add(tmp / Basket.size());
		}
		PriceIndex = tmpArr;
		ReverseArray(PriceIndex);
	}
	
	@Deprecated
	private void EqualWeightIndex() {
		Weight_Equal = (double) (1.d / Basket.size());
		ArrayList<Double> tmpArr = new ArrayList<Double>();
		
		for(int i = 0; i < SmallestDataSetSize; i++) {
			Double tmp = 0.d;
			for(int j = 0; j < Basket.size(); j ++) {
				tmp += (Basket.get(j).Adj_Close.get(i) * Weight_Equal);
			}
			tmpArr.add(tmp);
		}
		EqualWeightIndex = tmpArr;
		ReverseArray(EqualWeightIndex);
	}
	
	
	
	
	
	private void SmallestDataMatcher() {
		this.SmallestDataSetSize = Basket.get(0).Adj_Close.size();
		for(int i = 0; i < Basket.size(); i++) {
			if(SmallestDataSetSize > Basket.get(i).Adj_Close.size()) {
				SmallestDataSetSize = Basket.get(i).Adj_Close.size();
			}
		}
	}
	
	private void ReverseArray(ArrayList A) {
		ArrayList tmp = new ArrayList();
		for(int i = A.size() - 1; i >= 0; i--) {
			tmp.add(A.get(i));
		}
		A.clear();
		A.addAll(tmp);
	}
	
	public ArrayList<Double> getPriceIndex() {
		return PriceIndex;
	}
	@Deprecated
	public ArrayList<Double> getEqualWeightIndex() {
		return EqualWeightIndex;
	}
	@Deprecated
	public ArrayList<Double> getMarketCapitalizationIndex() {
		return this.MarketCapitalizationIndex;
	}
}
