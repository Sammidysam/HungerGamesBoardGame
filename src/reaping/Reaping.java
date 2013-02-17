package reaping;

import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Random;

public class Reaping extends enemyAI.PlayerCreator {
	Random rand = new Random();
    public String getDateTime(){
        DateFormat dateFormat = new SimpleDateFormat("MM/dd");
//        		+
//        		"/yyyy");
//        		+ "HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
    public int getCurrentYear(){
    	Calendar calendar = Calendar.getInstance();
    	int change = calendar.get(Calendar.YEAR);
    	return change;
    }
    public String changeYear(){
    	Calendar calendar = Calendar.getInstance();
    	int change = calendar.get(Calendar.YEAR);
    	int PlayerChange = PlayerA;
    	PlayerChange -= 12;
    	change += PlayerChange;
    	String changedy = Integer.toString(change);
    	return changedy;
    }
    public boolean isDrawn(){
    	int drawn = 50;
    	boolean isDrawn = false;
    	for(;PlayerA < 19; PlayerA++){
    	if(PlayerA == 12){
    		drawn = rand.nextInt(11);
    		if(drawn < 1)isDrawn = true;
    		System.out.println(drawn);
    	}
    	if(PlayerA == 13){
    		drawn = rand.nextInt(11);
    		if(drawn < 2)isDrawn = true;
    		System.out.println(drawn);
    	}
    	if(PlayerA == 14){
    		drawn = rand.nextInt(11);
    		if(drawn < 3)isDrawn = true;
    		System.out.println(drawn);
    	}
    	if(PlayerA == 15){
    		drawn = rand.nextInt(11);
    		if(drawn < 4)isDrawn = true;
    		System.out.println(drawn);
    	}
    	if(PlayerA == 16){
    		drawn = rand.nextInt(11);
    		if(drawn < 5)isDrawn = true;
    		System.out.println(drawn);
    	}
    	if(PlayerA == 17){
    		drawn = rand.nextInt(11);
    		if(drawn < 6)isDrawn = true;
    		System.out.println(drawn);
    	}
    	if(PlayerA == 18){
    		drawn = rand.nextInt(11);
    		if(drawn < 7)isDrawn = true;
    		System.out.println(drawn);
    	}
    	if(isDrawn == true){
    		System.out.println("Chosen at age" + PlayerA);
    		break;
    	}
    	if(isDrawn == false && PlayerA == 18){
    		System.out.println("Force-Chosen at age" + PlayerA);
    		break;
    	}
    	}
    	return isDrawn;
    }
    public String getRealDateTime(){
//        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//        Date date = new Date();
//        return dateFormat.format(date);
    	Calendar calendar = Calendar.getInstance();
    	int hours = calendar.get(Calendar.HOUR);
    	if(hours == 0)hours = 12;
    	int minutes = calendar.get(Calendar.MINUTE);
    	String minute = "we screwed up";
    	if(minutes > 9)minute = Integer.toString(minutes);
    	if(minutes < 10)minute = "0" + Integer.toString(minutes);
    	int seconds = calendar.get(Calendar.SECOND);
    	String second = "we screwed up";
    	if(seconds > 9)second = Integer.toString(seconds);
    	if(seconds < 10)second = "0" + Integer.toString(seconds);
    	int additionI = calendar.get(Calendar.AM_PM);
    	String addition = "we screwed up";
    	if(additionI == 0)addition = "AM";
    	if(additionI == 1)addition = "PM";
    	String time = Integer.toString(hours);
    	time += ":";
    	time += minute;
    	time += ":";
    	time += second;
    	time += " ";
    	time += addition;
    	return time;
    }
    public String getDayMonthYear(){
    	Calendar calendar = Calendar.getInstance();
    	String daymonthyear = "";
    	String month = Integer.toString(calendar.get(Calendar.MONTH) + 1);
    	daymonthyear += month + "/";
    	String day = Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
    	daymonthyear += day + "/";
    	String year = Integer.toString(getCurrentYear());
    	daymonthyear += year;
    	return daymonthyear;
    }
}
