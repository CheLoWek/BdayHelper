package com.lowek.che.bdayhelper.support_classes;

//import com.lilywei.che.bdayhelper_v1.MainActivity;
//import com.lilywei.che.bdayhelper_v1.R;

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

        // + 1 is because I want to count current day too. day X is not included
        return (int) ((date2.getTimeInMillis() - date1.getTimeInMillis()) / (1000 * 60 * 60 * 24) + 1);
    }

    public static Calendar nextBirthday(Calendar date) {
        Calendar today = Calendar.getInstance();

        // get this year's birthday
        Calendar next = new GregorianCalendar(today.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DATE));

        // check
        if (getDaysDifference(today, next) < 0) {
            next = new GregorianCalendar(today.get(Calendar.YEAR) + 1, date.get(Calendar.MONTH), date.get(Calendar.DATE));
        }

        return next;
    }

    public static String getDayOfWeek(Calendar date, String[] days_of_week) {
        String day = "";

        int dayOfWeek = date.get(Calendar.DAY_OF_WEEK);
//        String [] days = MainActivity.applicationResources.getStringArray(R.array.days_of_week);
        day = days_of_week[dayOfWeek - 1];  // чтобы не зависеть от mainactivity

        return day;
    }
}