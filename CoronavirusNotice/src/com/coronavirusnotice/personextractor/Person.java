package com.coronavirusnotice.personextractor;

import com.coronavirusnotice.covidextractor.COVIDInstance;

import java.util.ArrayList;

public class Person {

    String name = null;
    String district = null;
    String estate = null;
    String building = null;
    String block = null;

    ArrayList<Integer> caseNums = new ArrayList<>();
    ArrayList<COVIDInstance> estateInstances = new ArrayList<>();
    ArrayList<COVIDInstance> buildingInstances = new ArrayList<>();
    ArrayList<COVIDInstance> blockInstances = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getEstate() {
        return estate;
    }

    public void setEstate(String estate) {
        this.estate = estate;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }
    public void addCaseNum(int caseNum)
    {
        caseNums.add(caseNum);
    }

    public ArrayList<Integer> getCaseNums() {
        return caseNums;
    }

    public void setCaseNums(ArrayList<Integer> caseNums) {
        this.caseNums = caseNums;
    }

    public ArrayList<COVIDInstance> getEstateInstances() { return estateInstances; }

    public void setEstateInstances(ArrayList<COVIDInstance> estateInstances)
    {
        this.estateInstances = estateInstances;
    }

    public ArrayList<COVIDInstance> getBuildingInstances() {
        return buildingInstances;
    }

    public void setBuildingInstances(ArrayList<COVIDInstance> buildingInstances) {
        this.buildingInstances = buildingInstances;
    }

    public ArrayList<COVIDInstance> getBlockInstances() {
        return blockInstances;
    }

    public void setBlockInstances(ArrayList<COVIDInstance> blockInstances) {
        this.blockInstances = blockInstances;
    }

}
