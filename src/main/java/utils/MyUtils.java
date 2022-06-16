package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MyUtils {
	public static final DateFormat DF_DATE = new SimpleDateFormat("yyyy-MM-dd");
	public static final DateFormat DF_TIME = new SimpleDateFormat("HH:mm");
	public static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	public static final String SPECIAL_CHARACTERS = "[!@#$%^&*()-=]";

	public static String formatString(String s) {
		s = s.trim();
		String des = s.toLowerCase();
		String[] splitStr = des.split(" ");
		des = "";
		for(String i : splitStr) {
			if(i.isEmpty()) continue;
			i = ((i.charAt(0) + "").toUpperCase()) + i.substring(1, i.length());
			des += i + " "; 
		}
		
		return des.substring(0, des.length()-1);
	}
	
	public static String formatDate(DateFormat df, Date date) {
		return df.format(date);
	}
	
	public static Date parseDate(DateFormat df, String str) throws Exception {
		return df.parse(str);
	}
}
