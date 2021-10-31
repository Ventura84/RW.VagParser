import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ExcelFileCreator {


    private static void CreateHeader(Workbook workbook, Sheet sheet){

        sheet.createFreezePane( 0, 1);
        Row header = sheet.createRow(0);

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        XSSFFont font = (XSSFFont) workbook.createFont();
        font.setBold(true);
        font.setFontName("Courier NEW");
        headerCellStyle.setFont(font);


        Cell cell = header.createCell(0);
        cell.setCellValue("№ Вагона");
        cell.setCellStyle(headerCellStyle);

        cell = header.createCell(1);
        cell.setCellValue("Компания");
        cell.setCellStyle(headerCellStyle);

        cell = header.createCell(2);
        cell.setCellValue("Сообщ.");
        cell.setCellStyle(headerCellStyle);

        cell = header.createCell(3);
        cell.setCellValue("Ст. Форм. (КОД)");
        cell.setCellStyle(headerCellStyle);

        cell = header.createCell(4);
        cell.setCellValue("Ст. Форм.");
        cell.setCellStyle(headerCellStyle);

        cell = header.createCell(5);
        cell.setCellValue("Опер.");
        cell.setCellStyle(headerCellStyle);

        cell = header.createCell(6);
        cell.setCellValue("Дата");
        cell.setCellStyle(headerCellStyle);

        cell = header.createCell(7);
        cell.setCellValue("Время");
        cell.setCellStyle(headerCellStyle);

        cell = header.createCell(8);
        cell.setCellValue("Состояние");
        cell.setCellStyle(headerCellStyle);

        cell = header.createCell(9);
        cell.setCellValue("Ст. Назн. (КОД)");
        cell.setCellStyle(headerCellStyle);

        cell = header.createCell(10);
        cell.setCellValue("Ст. Назн.");
        cell.setCellStyle(headerCellStyle);

        cell = header.createCell(11);
        cell.setCellValue("Вес Груза");
        cell.setCellStyle(headerCellStyle);

        cell = header.createCell(12);
        cell.setCellValue("Груз (КОД)");
        cell.setCellStyle(headerCellStyle);

        cell = header.createCell(13);
        cell.setCellValue("Груз");
        cell.setCellStyle(headerCellStyle);

        cell = header.createCell(14);
        cell.setCellValue("Инд. поезда");
        cell.setCellStyle(headerCellStyle);

        cell = header.createCell(15);
        cell.setCellValue("Ном. Поезда");
        cell.setCellStyle(headerCellStyle);

        cell = header.createCell(16);
        cell.setCellValue("Контент");
        cell.setCellStyle(headerCellStyle);

        System.out.println("HEADER CREATED FOR SHEET " + sheet.getSheetName());

    }

    private static void CreateDataRowsForSheet(Workbook workbook, String[] stanCodesAr, String[] cargoCodesAr, List<List<List<String>>> mainOperations){

        XSSFFont font = (XSSFFont) workbook.createFont();
        font.setBold(true);
        font.setFontName("Courier NEW");

        CellStyle cellStyleDefault = workbook.createCellStyle();
        cellStyleDefault.setFont(font);

        CellStyle cellStyleForRightAligment = workbook.createCellStyle();
        cellStyleForRightAligment.setAlignment(HorizontalAlignment.RIGHT);
        cellStyleForRightAligment.setFont(font);

        int nextRow;



        Sheet sheet;
        String stForm = "";
        String stDest = "";
        String cargoName = "";
        int stFormCodeLen;
        int stDestCodeLen;



        for (List<List<String>> operations : mainOperations) {

            for (List<String> operation : operations) {

                sheet = workbook.getSheet(operation.get(1));
                nextRow = sheet.getLastRowNum();
                Row dataRow = sheet.createRow(nextRow + 1);


                if(operation.get(1).equals(sheet.getSheetName())){
                    for (String st : stanCodesAr) {
                        stFormCodeLen = operation.get(3).length();
                        if(st.substring(0, stFormCodeLen).equals(operation.get(3))){
                            stForm = st.substring(6);
                            break;
                        } else {
                            stForm = "";
                        }
                    }

                    for (String cName : cargoCodesAr){
                        if(cName.contains(operation.get(10)) && operation.get(10).length() > 0){
                            cargoName = cName.substring(7);
                            break;
                        } else {
                            cargoName = "";
                        }
                    }

                    for (String st : stanCodesAr) {
                        stDestCodeLen = operation.get(8).length();

                        if(st.substring(0, stDestCodeLen).equals(operation.get(8)) && stDestCodeLen > 0){
                            stDest = st.substring(6);
                            break;
                        } else {
                            stDest = "";
                        }
                    }

                    dataRow.createCell(0).setCellValue(operation.get(0));
                    dataRow.getCell(0).setCellStyle(cellStyleDefault);

                    dataRow.createCell(1).setCellValue(operation.get(1));
                    dataRow.getCell(1).setCellStyle(cellStyleDefault);

                    dataRow.createCell(2).setCellValue(operation.get(2));
                    dataRow.getCell(2).setCellStyle(cellStyleDefault);

                    dataRow.createCell(3).setCellValue(operation.get(3));
                    dataRow.getCell(3).setCellStyle(cellStyleForRightAligment);

                    dataRow.createCell(4).setCellValue(stForm);
                    dataRow.getCell(4).setCellStyle(cellStyleDefault);

                    dataRow.createCell(5).setCellValue(operation.get(4));
                    dataRow.getCell(5).setCellStyle(cellStyleDefault);

                    dataRow.createCell(6).setCellValue(operation.get(5));
                    dataRow.getCell(6).setCellStyle(cellStyleDefault);

                    dataRow.createCell(7).setCellValue(operation.get(6));
                    dataRow.getCell(7).setCellStyle(cellStyleDefault);

                    dataRow.createCell(8).setCellValue(operation.get(7));
                    dataRow.getCell(8).setCellStyle(cellStyleDefault);

                    dataRow.createCell(9).setCellValue(operation.get(8));
                    dataRow.getCell(9).setCellStyle(cellStyleForRightAligment);

                    dataRow.createCell(10).setCellValue(stDest);
                    dataRow.getCell(10).setCellStyle(cellStyleDefault);

                    dataRow.createCell(11).setCellValue(operation.get(9));
                    dataRow.getCell(11).setCellStyle(cellStyleForRightAligment);

                    dataRow.createCell(12).setCellValue(operation.get(10));
                    dataRow.getCell(12).setCellStyle(cellStyleForRightAligment);

                    dataRow.createCell(13).setCellValue(cargoName);
                    dataRow.getCell(13).setCellStyle(cellStyleDefault);

                    dataRow.createCell(14).setCellValue(operation.get(11));
                    dataRow.getCell(14).setCellStyle(cellStyleDefault);

                    dataRow.createCell(15).setCellValue(operation.get(12));
                    dataRow.getCell(15).setCellStyle(cellStyleDefault);

                    dataRow.createCell(16).setCellValue(operation.get(13));
                    dataRow.getCell(16).setCellStyle(cellStyleDefault);

                }
            }

        }


        System.out.println("DATA IMPORTED FOR SHEETS");

    }

    private static void SizingColumns(Workbook wb, List<String> sheetNames) {

        for (String sheet: sheetNames) {
            Sheet _sheet = wb.getSheet(sheet);

            for (int i = 0; i < 17 ; i++) {
                _sheet.autoSizeColumn(i);
            }

            System.out.println("SIZED " + sheet);

        }





    }


    public static ByteArrayInputStream infoToExcelFile(String[] vagonListAr, String[] stanCodesAr, String[] cargoCodesAr, List<List<List<String>>> mainOperations) {

        //List<Sheet> sheets = new ArrayList<>();
        List<String> sheetNames = new ArrayList<>();

        try(Workbook workbook = new XSSFWorkbook()){


            for (String s : vagonListAr) {
                if (workbook.getSheet(s.substring(8).trim()) == null) {
                    Sheet sheet = workbook.createSheet(s.substring(8).trim());
                    sheet.setDisplayGridlines(true);
                    CreateHeader(workbook, sheet);
                    //sheets.add(sheet);
                    sheetNames.add(s.substring(8).trim());

                }
            }


            CreateDataRowsForSheet(workbook, stanCodesAr, cargoCodesAr, mainOperations);

            SizingColumns(workbook, sheetNames);



            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }




}