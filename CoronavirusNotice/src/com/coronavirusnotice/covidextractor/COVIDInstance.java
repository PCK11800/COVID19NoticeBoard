package com.coronavirusnotice.covidextractor;

import java.util.ArrayList;

public class COVIDInstance {

    /*
     * A class that holds location and case number of
     * each positive covid-19 case
     */

    private String district;
    private String name;
    private ArrayList<Integer> relatedCaseNumber = new ArrayList<>();

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Integer> getRelatedCaseNumber() {
        return relatedCaseNumber;
    }

    public void setRelatedCaseNumber(String number) {
        String[] numberArray = number.trim().split("\\s*,\\s*");
        for(String str : numberArray)
        {
            try{
                relatedCaseNumber.add(Integer.parseInt(str));
            } catch (NumberFormatException e) {
                relatedCaseNumber.add(-1);
            }
        }
    }
}
