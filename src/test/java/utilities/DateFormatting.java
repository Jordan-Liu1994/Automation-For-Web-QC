package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatting {

	public void dateAndTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM - HH:mm:ss");
		String timeStamp = sdf.format(new Date());
		System.out.println(timeStamp);
	}
}
