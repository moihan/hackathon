 package team1.cabBooking;

import java.util.Date;

public class datepicker {
	public String date(){
		Date date=new Date();
		return date.toString().replaceAll(":","_").replaceAll(" "," -");
	}

}
