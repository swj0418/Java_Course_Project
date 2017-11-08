package stat_c;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {
	
	Test() {
		
		/*
		Stock S = new Stock("TSLA");
		S.retrieve();
		//S.printClose();
		System.out.println(S.Close_M.get("2011-04-15"));
		
		ArrayList testArr = null;
		ArrayList testArr2 = null;
		testArr = S.request("CLOSE", "2014-01-01", "2015-12-30");
		testArr2 = S.request("DATE", "2014-01-01", "2015-12-30");
		
		for(int i = 0; i < testArr.size(); i++) {
			System.out.println(testArr2.get(i) + " :::: " + testArr.get(i));
		}
		System.out.println("Price at target date : " + S.Close_M.get("2015-12-30"));
		*/ //These tests are done. Stock class finished.
		
		//SStat class test

		Stock S = new Stock("AAPL");
		Stock S_TSLA = new Stock("TSLA");
		Stock S_GENE = new Stock("GENE");
		Stock S_ADBE = new Stock("ADBE");
		
		S.retrieve();
		S_TSLA.retrieve();
		S_GENE.retrieve();
		S_ADBE.retrieve();
		
		SStat St = new SStat(S);
		SStat St2 = new SStat(S_TSLA);
		SStat St3 = new SStat(S_GENE);
		
		
		System.out.println("<<<" + S.SYMBOL + ">>>");
		System.out.println(St.Geometricmean("CLOSE", "2015-12-25", "2016-01-01"));
		ArrayList tmpArray1 = St.PercentageChange("CLOSE", 100, "2012-12-15", "2016-01-01");
		System.out.println(tmpArray1);
		ArrayList tmpArray2 = St.PercentageChange("OPEN", 300, "2012-12-15", "2016-01-01");
		System.out.println(tmpArray2);
		
		System.out.println("<<<" + S_TSLA.SYMBOL + ">>>");
		System.out.println(St2.Geometricmean("CLOSE", "2015-12-25", "2016-01-01"));
		tmpArray1 = St2.PercentageChange("CLOSE", 100, "2012-12-15", "2016-01-01");
		System.out.println(tmpArray1);
		tmpArray2 = St2.PercentageChange("OPEN", 300, "2012-12-15", "2016-01-01");
		System.out.println(tmpArray2);
		
		System.out.println("<<<" + S_GENE.SYMBOL + ">>>");
		System.out.println(St3.Geometricmean("CLOSE", "2015-12-25", "2016-01-01"));
		
		tmpArray1 = St3.PercentageChange("CLOSE", 100, "2012-12-15", "2016-01-01");
		System.out.println(tmpArray1);
		tmpArray2 = St3.PercentageChange("OPEN", 300, "2012-12-15", "2016-01-01");
		System.out.println(tmpArray2);
		
		System.out.println("<<<" + S_ADBE.SYMBOL + ">>>");
	}
	
	public static void main(String[] ar) {
		new Test();
	}
}
