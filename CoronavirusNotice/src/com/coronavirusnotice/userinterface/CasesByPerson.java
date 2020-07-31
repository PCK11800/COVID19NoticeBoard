package com.coronavirusnotice.userinterface;

import com.coronavirusnotice.covidextractor.COVIDInstance;
import com.coronavirusnotice.covidextractor.COVIDList;

import java.io.FileWriter;

import com.coronavirusnotice.covidextractor.COVIDFilter;
import com.coronavirusnotice.personextractor.Person;
import com.coronavirusnotice.personextractor.PersonList;

public class CasesByPerson {

    COVIDFilter filter = new COVIDFilter();
 	StringBuilder str = new StringBuilder();

    public CasesByPerson(COVIDList covidList, PersonList personList)
    {
        covidList = filter.filterNonResidential(covidList);
        loadPersonData(covidList, personList);
    }

    private void loadPersonData(COVIDList covidList, PersonList personList)
    {
        for(Person person : personList)
        {
            COVIDList filteredList;
            filteredList = filter.filterByDistrict(covidList, person.getDistrict());

            filteredList = filter.filterByEstate(filteredList, person.getEstate());
            person.setEstateInstances(filteredList);

            for(COVIDInstance instance : filteredList)
            {
                for(Integer n : instance.getRelatedCaseNumber())
                {
                    person.addCaseNum(n);
                }
            }

            if(!person.getBuilding().equals(""))
            {
                filteredList = filter.filterByBuilding(filteredList, person.getBuilding());
                person.setBuildingInstances(filteredList);
            }

            if(!person.getBlock().equals(""))
            {
                filteredList = filter.filterByBlock(filteredList, person.getBlock());
                person.setBlockInstances(filteredList);
            }

            printData(person);
        }

        System.out.println(str.toString());
        
        try {       
            FileWriter writer = new FileWriter("output.txt");
            writer.append(str.toString());
            writer.flush();
            writer.close();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    private void printData(Person person)
    {
        //Print to console
    	str.append("Person: " + person.getName() + "\n");
        str.append("CaseNum: " + person.getCaseNums().toString() + "\n");

        if(person.getBuildingInstances().size() != 0 || person.getBlockInstances().size() != 0) {
            str.append("\n");
            str.append("    Same Building Or/And Block:  \n");
            if(person.getBuildingInstances() == null){
                for(COVIDInstance instance : person.getBlockInstances())
                {
                    str.append("    " + instance.getName() + " " + instance.getRelatedCaseNumber().toString() + "\n");
                }
            }
            else{
                for(COVIDInstance instance : person.getBuildingInstances())
                {
                    str.append("    " + instance.getName() + " " + instance.getRelatedCaseNumber().toString() + "\n");
                }
            }
        }

        if(person.getEstateInstances().size() != 0) {
            str.append("\n");
            str.append("    All cases: \n");
            for(COVIDInstance instance : person.getEstateInstances())
            {
                str.append("    " + instance.getName() + " " + instance.getRelatedCaseNumber().toString() + "\n");
            }
        }

        str.append("\n");
    }
}
