package com.coronavirusnotice.covidextractor;

import com.github.greycode.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

public class COVIDExtractor {

    COVIDList list = null;

    public COVIDExtractor(File file)
    {
        COVIDConverter covidConverter = new COVIDConverter(file);
        run(covidConverter.getOutputFile());
        covidConverter.getOutputFile().delete();
        covidConverter.getInputFile().delete();
    }

    public void run(File file)
    {
        loadWorkbook(file);
    }

    /*
     * Loads workbook file as a workbook class and extracts
     * data from it.
     */
    private void loadWorkbook(File file)
    {
        try{
            FileInputStream fis = new FileInputStream(file);

            Workbook workbook = StreamingReader.builder()
                    .rowCacheSize(100)
                    .bufferSize(4096)
                    .open(fis);

            list = extractData(workbook);
            workbook.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Extracts data from workbook.
     */
    private COVIDList extractData(Workbook workbook)
    {
        Sheet sheet = workbook.getSheetAt(0);
        boolean firstRow = true;

        COVIDList list = new COVIDList();

        for(Row r : sheet)
        {
            if(firstRow){
                firstRow = false;
            }
            else{
                list.add(loadRowData(r));
            }
        }
        return list;
    }

    /*
     * Extracts data from a single row
     */
    private COVIDInstance loadRowData(Row r)
    {
        Iterator<Cell> cellIterator = r.cellIterator();
        COVIDInstance instance = new COVIDInstance();

        int columnNumber = 1;
        while(cellIterator.hasNext())
        {
            Cell cell = cellIterator.next();
            switch(columnNumber)
            {
                case 1: //District
                    instance.setDistrict(cell.getStringCellValue());
                    break;
                case 2: //Building Name
                    instance.setName(cell.getStringCellValue());
                    break;
                case 3: //Related/Probable case number
                    instance.setRelatedCaseNumber(cell.getStringCellValue());
                    break;
                default:
                    break;
            }
            columnNumber++;
        }
        return instance;
    }

    public COVIDList getList()
    {
        return list;
    }
}
