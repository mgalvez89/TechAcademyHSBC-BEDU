package com.bedu.tarjetas.helper;

import com.bedu.tarjetas.entities.Branch;
import com.bedu.tarjetas.entities.Delivery;
import com.bedu.tarjetas.entities.Location;
import com.bedu.tarjetas.entities.Package;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ExcelHelper {

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String SHEET = "Packages";
    static String SHEET_LOCATION = "Locations";

    static String SHEET_DELIVERY = "Deliveries";

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }



    public static List<Package> excelToStorage(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<Package> packageList = new ArrayList<Package>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Package aPackage = new Package();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx)
                    {
                        case 0:
                            aPackage.setNamePackage(currentCell.getStringCellValue());
                            break;
                        case 1:
                            aPackage.setNameProduct(currentCell.getStringCellValue());
                            break;
                        case 2:
                            aPackage.setNumberCards((int) currentCell.getNumericCellValue());
                            break;

                        default:
                            break;
                    }

                    cellIdx++;
                }

                packageList.add(aPackage);
            }

            workbook.close();

            return packageList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<Location> excelToLocations(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET_LOCATION);
            Iterator<Row> rows = sheet.iterator();

            List<Location> locationList = new ArrayList<Location>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Location location = new Location();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx)
                    {
                        case 0:
                            location.setNameLocation(currentCell.getStringCellValue());
                            break;
                        case 1:
                            location.setCapacity((int) currentCell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }

                    cellIdx++;
                }

                locationList.add(location);
            }

            workbook.close();

            return locationList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<Delivery> excelToDelivery(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET_DELIVERY);
            Iterator<Row> rows = sheet.iterator();

            List<Delivery> deliveryList = new ArrayList<Delivery>();

            int rowNumber = 0;
            int total = 0;
            while (rows.hasNext())
            {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0)
                {
                    rowNumber++;
                    total++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Delivery delivery = new Delivery();
                Branch branch = new Branch();
                Package aPackage = new Package();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx)
                    {
                        case 0:
                            branch.setNumberBranch(currentCell.getStringCellValue());
                            delivery.setBranch(branch);
                            //delivery.setNumberBranch(currentCell.getStringCellValue());
                            break;
                        case 1:
                            aPackage.setNamePackage(currentCell.getStringCellValue());
                            delivery.setAPackage(aPackage);
                            //delivery.setNamePackage(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }

                    cellIdx++;
                }

                deliveryList.add(delivery);
                total++;
            }
            System.out.println("Filas Totales: " + total);
            workbook.close();

            return deliveryList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }




   /* public static ByteArrayInputStream tutorialsToExcel(List<Tutorial> tutorials) {

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }

            int rowIdx = 1;
            for (Tutorial tutorial : tutorials) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(tutorial.getId());
                row.createCell(1).setCellValue(tutorial.getTitle());
                row.createCell(2).setCellValue(tutorial.getDescription());
                row.createCell(3).setCellValue(tutorial.isPublished());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }*/


}
