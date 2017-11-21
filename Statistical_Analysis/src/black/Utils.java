package black;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {
	
	public static String APIString(String SYMBOL) {
		return "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=" +
				SYMBOL.trim() +"&apikey=KIUYG7J8UA5MIMQI&datatype=csv&outputsize=full";
	}
	
	@SuppressWarnings("static-access")
	public static String TodayString() {
		LocalDate checkdate = null;
		return checkdate.now().toString();
	}
}
