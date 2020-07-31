package com.coronavirusnotice.personextractor;

import com.github.greycode.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

public class ParamExtractor {

    PersonList list = null;

    public ParamExtractor(File file)
    {
        ParamConverter paramConverter = new ParamConverter(file);
        run(paramConverter.getOutputFile());
        paramConverter.getOutputFile().delete();
    }

    public void run(File file) { loadWorkbook(file); }

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
    private PersonList extractData(Workbook workbook)
    {
        Sheet sheet = workbook.getSheetAt(0);
        boolean firstRow = true;

        PersonList list = new PersonList();

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
    private Person loadRowData(Row r)
    {
        Iterator<Cell> cellIterator = r.cellIterator();
        Person person = new Person();

        int columnNumber = 1;
        while(cellIterator.hasNext())
        {
            Cell cell = cellIterator.next();
            switch(columnNumber)
            {
                case 1: //Person
                    person.setName(cell.getStringCellValue());
                    break;
                case 2: //District
                    person.setDistrict(cell.getStringCellValue());
                    break;
                case 3: //Estate
                    person.setEstate(cell.getStringCellValue());
                    break;
                case 4: //Building
                    person.setBuilding(cell.getStringCellValue());
                    break;
                case 5: //Block
                    person.setBlock(cell.getStringCellValue());
                    break;
                default:
                    break;
            }
            columnNumber++;
        }
        return person;
    }

    public PersonList getList() { return list; }
}
