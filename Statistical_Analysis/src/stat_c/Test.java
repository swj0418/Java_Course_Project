package stat_c;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {
	
	Test() {

		//SStat Test 2
		Stock S_EDIG = new Stock("EDIG");
		S_EDIG.retrieve();
		SStat St = new SStat(S_EDIG);
		
		System.out.println("<<<" + S_EDIG.SYMBOL + ">>>");
		System.out.println("Geometric mean Return: " + St.MeanReturnGeometric("CLOSE", 30, "2012-12-25", "2016-01-01"));
		
		ArrayList tmpArray1 = St.PercentageChange("CLOSE", 100, "2012-12-15", "2016-01-01");
		System.out.println("Percentage Change (Close, 100 from 2012/12/15 to 2016/01/01)\n" + tmpArray1);
		
		ArrayList ReturnVariance_tmp_Array = St.ReturnVariance("CLOSE", 30, "2012-12-15", "2016-01-01");
		System.out.println("Return Variance (Close, 100 from 2012/12/15 to 2016/01/01)\n" + ReturnVariance_tmp_Array);
		
		System.out.println(St.MeanReturnVariance("CLOSE", 30, "2012-12-15", "2016-01-01"));
	}
	
	public static void main(String[] ar) {
		new Test();
	}
}
