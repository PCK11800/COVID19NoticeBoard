package com.coronavirusnotice.Main;

import com.coronavirusnotice.dataconnector.Connector;
import com.coronavirusnotice.covidextractor.COVIDExtractor;
import com.coronavirusnotice.userinterface.Application;
import com.coronavirusnotice.userinterface.CasesByPerson;
import com.coronavirusnotice.personextractor.ParamExtractor;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Application application = new Application();

        new Connector("http://www.chp.gov.hk/files/misc/building_list_eng.csv");
        COVIDExtractor dataExtractor = new COVIDExtractor(new File("data.csv"));
        ParamExtractor paramExtractor = new ParamExtractor(new File("Parameters.csv"));

        CasesByPerson casesByPerson = new CasesByPerson(dataExtractor.getList(), paramExtractor.getList());
    }
}
