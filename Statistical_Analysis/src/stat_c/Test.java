package stat_c;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Test {
	Test() {
		Stock S = new Stock("TSLA");
		S.retrieve();
		SStat Stat = new SStat(S);
		System.out.println("Test Value");
		S.printAdjClose();
		Stat.PercentageChange("ADJ_CLOSE", 500, "2015-10-02", "2017-11-13");
		System.out.println(Stat.S1_Percentage_Change);
		System.out.println(Stat.MeanReturnGeometric("ADJ_CLOSE", 100, "2015-11-02", "2017-11-13"));
		System.out.println(Stat.MeanReturnArithmetic("ADJ_CLOSE", 100, "2015-11-02", "2017-11-13"));
		System.out.println(Stat.ReturnDeviation("ADJ_CLOSE", 100, "2015-11-02", "2017-11-13", "ARI"));
		System.out.println(Stat.Variance_Return("ADJ_CLOSE", 100, "2015-11-02", "2017-11-13", "ARI"));
		System.out.println(Stat.StandardDeviation_Return("ADJ_CLOSE", 100, "2015-11-02", "2017-11-13", "ARI"));
		Double VR = Stat.Variance_Return("ADJ_CLOSE", 100, "2015-11-02", "2017-11-13", "ARI");
		String String_VR = Double.toString(((int)(VR * 100000000.0d)) / 100000000.0d);
		System.out.println(String_VR);
	}
	
	public static void main(String[] ar) {
		new Test();
	}
}
