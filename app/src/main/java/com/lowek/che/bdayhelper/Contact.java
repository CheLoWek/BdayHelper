package com.lowek.che.bdayhelper;

import android.util.Log;

import com.lowek.che.bdayhelper.utils.DateMethods;

import java.util.Calendar;

public class Contact implements Comparable<Contact> {

    private String name;
    private String lastName;
    private String picturePath;
    private Calendar birthDate;
    private Calendar nextBirthday;
    private int daysLeft;
    private String presentIdea;
    private boolean haspresentIdea;
    private boolean hasPresent;

    public Calendar getNextBirthday() {
        return nextBirthday;
    }

    public int getDaysLeft() {
        return daysLeft;
    }

    public Contact(String name, String lastName, String picturePath, Calendar birthDate, String presentIdea) {
        this.name = name;
        this.lastName = lastName;
        this.picturePath = picturePath;
        this.birthDate = birthDate;

        nextBirthday = DateMethods.nextBirthday(this.getBirthDate());
        daysLeft = DateMethods.getDaysDifference(Calendar.getInstance(), nextBirthday);
//        Log.d("DaysLeft", daysLeft + "");
        this.presentIdea = presentIdea;

        if (!this.presentIdea.equals("")) {
            haspresentIdea = true;
        }
    }

    public String getPresentIdea() {
        return presentIdea;
    }

    public void setPresentIdea(String presentIdea) {
        this.presentIdea = presentIdea;
        if (!this.presentIdea.equals("")) {
            haspresentIdea = true;
        }
    }

    public boolean isHaspresentIdea() {
        return haspresentIdea;
    }

    public void setHaspresentIdea(boolean haspresentIdea) {
        this.haspresentIdea = haspresentIdea;
    }

    public boolean isHasPresent() {
        return hasPresent;
    }

    public void setHasPresent(boolean hasPresent) {
        this.hasPresent = hasPresent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public Calendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Calendar birthDate) {
        this.birthDate = birthDate;
    }

    public int compareTo(Contact other) {
        return getDaysLeft() - other.getDaysLeft();
    }

    public String getContactInformation(){
        return  getName() + " " +
                getLastName() + " " +
                DateMethods.getStringDate(getBirthDate()) + " " +
                DateMethods.getStringDate(getNextBirthday());
    }
}
