package billy.domain;

import java.util.Calendar;
import java.util.Date;

public class InvoiceUtils {

	public static int getPeriod(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR) * 100 + calendar.get(Calendar.MONTH) + 1;
	}
	
}
