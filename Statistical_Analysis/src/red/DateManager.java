package red;
import java.util.*;
import java.time.*;

public class DateManager {
	LocalDate start;
	LocalDate end;
	int size;
	String Year = "";
	String Month = "";
	String Day = "";
	
	Calendar startCalendar = Calendar.getInstance();
	Calendar endCalendar = Calendar.getInstance();
	
	ArrayList<LocalDate> Dates = new ArrayList<LocalDate>();
	ArrayList<Calendar> CalenDates = new ArrayList<Calendar>();
	ArrayList<LocalDate> TradingDay = new ArrayList<LocalDate>();
	/*
	 * A class designed to get trading day
	 */
	DateManager() {
		
	}
	DateManager(String start, String end) {
		this.start = LocalDate.parse(start);
		this.end = LocalDate.parse(end);
		
		//For Start
		String[] tmpList = start.split("");
		for(int i = 0; i < tmpList.length; i++) {
			if(i >= 0 && i <= 3) {
				Year += tmpList[i];
			}
			if(i >= 5 && i <= 6) {
				Month += tmpList[i];
			}
			if(i >= 8 && i <= 9) {
				Day += tmpList[i];
			}
		}
		System.out.println(Year + "       " + Integer.parseInt(Month) + "           " + Integer.parseInt(Day));
		Date startd = new Date(); startd.toInstant();
		//Shit. Calendar uses 0 as January...
		String Year2 = "";
		String Month2 = "";
		String Day2 = "";
		
		//For Ends
		String[] tmpList2 = end.split("");
		for(int i = 0; i < tmpList2.length; i++) {
			if(i >= 0 && i <= 3) {
				Year2 += tmpList2[i];
			}
			if(i >= 5 && i <= 6) {
				Month2 += tmpList2[i];
			}
			if(i >= 8 && i <= 9) {
				Day2 += tmpList2[i];
			}
		}
		System.out.println(Year2 + "       " + Month2 + "           " + Day2);
		endCalendar.clear();
		endCalendar.set(Integer.parseInt(Year2), Integer.parseInt(Month2) - 1, Integer.parseInt(Day2));
		Dates = datelister(this.start, this.end);
		size = Dates.size(); // Must be allocated earlier than CalenDates. CalenDates uses this variable.
		CalenDates = calendatelister(startCalendar, endCalendar);
		System.out.println(startCalendar.getTime() + "           " + endCalendar.getTime());
	}
	
	public ArrayList datelister(LocalDate start, LocalDate end) {
		ArrayList<LocalDate> tmpList = new ArrayList<LocalDate>();
		for(LocalDate d = start; !d.isAfter(end); d = d.plusDays(1)) {
			tmpList.add(d);
		}
		return tmpList;
	}
	
	public ArrayList calendatelister(Calendar start, Calendar end) {
		ArrayList<Calendar> tmpList = new ArrayList<Calendar>();
		Calendar c = Calendar.getInstance();
		c = start;
		for(int i = 0; i < size; i++) {
			tmpList.add(c);
			c.add(Calendar.DATE, 1);
		}
		return tmpList;
	}
	
	public ArrayList tradingday() {
		ArrayList<LocalDate> returnlist = new ArrayList<LocalDate>();
		Calendar tmpCalen = null; tmpCalen.getInstance();
		System.out.println(CalenDates.get(1).getTime());
		/*
		Iterator itr = tmpList.iterator();
		int i = 0;
		try {
			while((tmpCalen = (Calendar) itr.next()) != null) {
				System.out.println(tmpCalen.getTime());
				if(tmpCalen.SUNDAY != 1 || tmpCalen.SATURDAY != 7) {
					TradingDay.add(Dates.get(i));
				}
				i++;
			}
			
		} catch(Exception e) {}
		*/
		for(int i = 0; i < size; i++) {
			//System.out.println(tmpList.get(i).DAY_OF_WEEK);
			//System.out.println(tmpList.get(i).getTime());
			if(CalenDates.get(i).DAY_OF_WEEK != 1 || CalenDates.get(i).DAY_OF_WEEK != 7) {
				//returnlist.add(Dates.get(i));
			} else if(CalenDates.get(i).DAY_OF_WEEK == 1) {
				System.out.println("It's Sunday");
			} else if(CalenDates.get(i).DAY_OF_WEEK == 7) {
				System.out.println("It's Saturday");
			}
		}
		return returnlist;
	}

}
