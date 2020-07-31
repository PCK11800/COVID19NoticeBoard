package com.coronavirusnotice.personextractor;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.prefs.CsvPreference;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class ParamConverter {

    File inputFile;
    String savePath;
    String fileName, saveName;

    public ParamConverter(File csvFile){
        this.inputFile = csvFile;
        initConverter();
        convert();
    }

    private void initConverter()
    {
        fileName = inputFile.getName();
        if(fileName.endsWith(".csv")){
            saveName = fileName.replace(".csv", ".xlsx");
        }
        savePath = inputFile.getAbsolutePath();
        savePath = savePath.replace(fileName, saveName);
    }

    private void convert()
    {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("sheet1");

            ICsvMapReader csvMapReader = new CsvMapReader(new FileReader(inputFile), CsvPreference.EXCEL_PREFERENCE);
            final String[] headers = csvMapReader.getHeader(true);
            Map csvRow;

            int rowNo = 0;
            createExcelHeader(sheet, rowNo);

            while ((csvRow = csvMapReader.read(headers)) != null) {
                rowNo++;
                XSSFRow excelRow = sheet.createRow(rowNo);
                createExcelRow(csvRow, excelRow);
            }

            FileOutputStream fos = new FileOutputStream(savePath);
            csvMapReader.close();
            workbook.write(fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createExcelHeader(Sheet sheet, int rowNo)
    {
        XSSFRow excelRow = (XSSFRow) sheet.createRow(rowNo);
        excelRow.createCell(0).setCellValue("Person");
        excelRow.createCell(1).setCellValue("District");
        excelRow.createCell(2).setCellValue("Estate");
        excelRow.createCell(3).setCellValue("Building");
        excelRow.createCell(4).setCellValue("Block");
    }

    public void createExcelRow(Map csvRow, XSSFRow excelRow) {
        excelRow.createCell(0).setCellValue((String) csvRow.get("Person"));
        excelRow.createCell(1).setCellValue((String) csvRow.get("District"));
        excelRow.createCell(2).setCellValue((String) csvRow.get("Estate"));
        excelRow.createCell(3).setCellValue((String) csvRow.get("Building"));
        excelRow.createCell(4).setCellValue((String) csvRow.get("Block"));
    }

    public File getOutputFile()
    {
        File file = new File(savePath);
        return file;
    }
}
