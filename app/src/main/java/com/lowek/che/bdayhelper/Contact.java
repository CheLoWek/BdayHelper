package com.lowek.che.bdayhelper;

import java.util.Calendar;

public class Contact {

    private String name;
    private String lastName;
    private int picture;
    private Calendar birthDate;
    private String presentIdea;
    private boolean haspresentIdea;
    private boolean hasPresent;

    public Contact(String name, String lastName, int picture, Calendar birthDate, String presentIdea){
        this.name = name;
        this.lastName = lastName;
        this.picture = picture;
        this.birthDate = birthDate;
        this.presentIdea = presentIdea;

        if (!this.presentIdea.equals("")){
            haspresentIdea = true;
        }
    }

    public String getPresentIdea() {
        return presentIdea;
    }

    public void setPresentIdea(String presentIdea) {
        this.presentIdea = presentIdea;
        if (!this.presentIdea.equals("")){
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

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public Calendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Calendar birthDate) {
        this.birthDate = birthDate;
    }

}
