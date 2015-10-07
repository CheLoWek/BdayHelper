package com.lowek.che.bdayhelper.utils;

//import com.lilywei.che.bdayhelper_v1.MainActivity;
//import com.lilywei.che.bdayhelper_v1.R;

import android.util.Log;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateMethods {

    public static String getStringDateMonth(Calendar date, String[] months) {
        String result = "";
        result += date.get(Calendar.DATE) + " ";
        // plus month
//        result += MainActivity.applicationResources.getStringArray(R.array.months)[date.get(Calendar.MONTH)];
        result += months[date.get(Calendar.MONTH)]; // чтобы не зависеть от mainactivity

        return result;
    }

    public static int getDaysDifference(Calendar date1, Calendar date2) {

        if (date1 == null || date2 == null)
            return 0;

        String stringDate1 = getStringDate(date1);
        String stringDate2 = getStringDate(date2);
        Log.d("1", "date1: " + stringDate1 + "   date2: " + stringDate2);
        if (date1.after(date2)){
            if (stringDate1.equals(stringDate2)){
                return 0;
            }
            return -1;
        }

        int result = (int) ((date2.getTimeInMillis() - date1.getTimeInMillis()) / (1000 * 60 * 60 * 24));
        result++;
        return result;
    }


    public static Calendar nextBirthday(Calendar date) {
        Calendar today = Calendar.getInstance();

        // get this year's birthday
        Calendar next = new GregorianCalendar(today.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DATE));

        // check
        if (getDaysDifference(today, next) < 0) {
            next = new GregorianCalendar(today.get(Calendar.YEAR) + 1, date.get(Calendar.MONTH), date.get(Calendar.DATE));

        }
        Log.d("3 next", getStringDate(next));
        return next;
    }

    public static String getStringDate(Calendar date){
        return date.get(Calendar.DATE) + "." + date.get(Calendar.MONTH) + "." + date.get(Calendar.YEAR);
    }

    public static String getDayOfWeek(Calendar date, String[] days_of_week) {
        String day = "";

        int dayOfWeek = date.get(Calendar.DAY_OF_WEEK);
//        String [] days = MainActivity.applicationResources.getStringArray(R.array.days_of_week);
        day = days_of_week[dayOfWeek - 1];  // чтобы не зависеть от mainactivity

        return day;
    }
}