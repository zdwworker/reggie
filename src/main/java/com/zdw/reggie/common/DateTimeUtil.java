package com.zdw.reggie.common;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeUtil {
	
	public static LocalDateTime getSysTime(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date date = new Date();
		String dateStr = sdf.format(date);
		Instant instant = date.toInstant();
		LocalDateTime gmt = LocalDateTime.ofInstant(instant, ZoneId.ofOffset("GMT", ZoneOffset.ofHours(8)));

		return gmt;
		
	}



}
