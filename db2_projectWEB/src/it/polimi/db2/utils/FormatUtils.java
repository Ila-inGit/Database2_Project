package it.polimi.db2.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Formatting utilities used in jsp to make better rendering of pages
 */
public class FormatUtils {

	/**
	 * Return formatted date
	 */
	public static String dateToString(Date d) {
		return (new SimpleDateFormat("yyyy-MM-dd")).format(d);
	}
	
	/**
	 * Return true if the date passed as parameter is before today
	 * @return
	 */
	public static boolean isNextOrTodayDate(Date d) {
		var today = new Date();
		return d.after(today) || isToday(d);
	}
	
	
	public static boolean isToday(Date date){
        Calendar today = Calendar.getInstance();
        Calendar specifiedDate  = Calendar.getInstance();
        specifiedDate.setTime(date);

        return today.get(Calendar.DAY_OF_MONTH) == specifiedDate.get(Calendar.DAY_OF_MONTH)
                &&  today.get(Calendar.MONTH) == specifiedDate.get(Calendar.MONTH)
                &&  today.get(Calendar.YEAR) == specifiedDate.get(Calendar.YEAR);
    }
	
	
	public static String replaceNullable(Object o)
	{
		if(o == null)
			return "";
		else
			return o.toString();
	}
}
