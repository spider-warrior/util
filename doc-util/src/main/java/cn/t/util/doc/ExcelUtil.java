package cn.t.util.doc;

import cn.t.util.common.CollectionUtil;
import cn.t.util.common.StringUtil;
import cn.t.util.common.reflect.ReflectUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ExcelUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);
    private static final int MIN_CELL_WIDTH = 15 * 256;
    private static final int MAX_CELL_WIDTH = 50 * 256;


    /**
     * 读取excel文档
     * @param path              xxx
     * @param readExcelCallBack xxx
     * @throws IOException xxx
     */
    public static void readWorkbook(String path, ReadExcelCallBack readExcelCallBack) throws IOException {
        readWorkbook(new File(path), readExcelCallBack);
    }

    /**
     * 读取excel文档
     * @param file              xxx
     * @param readExcelCallBack xxx
     * @throws IOException xxx
     */
    private static void readWorkbook(File file, ReadExcelCallBack readExcelCallBack) throws IOException {
        readSheet(WorkbookFactory.create(new FileInputStream(file)), readExcelCallBack);
    }

    /**
     * 读取sheet
     * @param workbook          xxx
     * @param readExcelCallBack xxx
     * @throws IOException xxx
     */
    private static void readSheet(Workbook workbook, ReadExcelCallBack readExcelCallBack) throws IOException {
        int totalSheet = workbook.getNumberOfSheets();
        for (int i = 0; i < totalSheet; i++) {
            Sheet currentSheet = workbook.getSheetAt(i);
            //读取当前sheet
            readExcelCallBack.readSheet(currentSheet);
            readRow(currentSheet, readExcelCallBack);
            //读取sheet完成
            readExcelCallBack.readSheetComplete(currentSheet);
        }
        readExcelCallBack.readWorkbookComplete(workbook);
    }

    /**
     * 读取row
     * @param sheet             xxx
     * @param readExcelCallBack xxx
     */
    private static void readRow(Sheet sheet, ReadExcelCallBack readExcelCallBack) {
        int totalRow = sheet.getLastRowNum() + 1;
        for (int i = 0; i < totalRow; i++) {
            Row currentRow = sheet.getRow(i);
            readExcelCallBack.readRow(currentRow, i);
            if (currentRow != null) {
                //读取当前row
                readCell(currentRow, readExcelCallBack);
                //读取Row完成
                readExcelCallBack.readRowComplete(currentRow);
            }
        }
    }

    /**
     * 读取cell
     * @param row               xxx
     * @param readExcelCallBack xxx
     */
    private static void readCell(Row row, ReadExcelCallBack readExcelCallBack) {
        int totalCell = row.getLastCellNum();
        for (int i = 0; i < totalCell; i++) {
            Cell currentCell = row.getCell(i);
            if (currentCell != null) {
                readExcelCallBack.readCell(currentCell, i);
            }
        }
    }

    /**
     * 清理空白行
     * @param sourceExcel xxx
     * @param targetExcel xxx
     * @throws IOException xxx
     */
    public static void clearBlankRows(String sourceExcel, String targetExcel) throws IOException {
        readWorkbook(sourceExcel, new AbstractReadExcelCallBack() {
            private final DataFormatter formatter = new DataFormatter();
            private int blankCellCount;
            private final List<Integer> rowLIndexToBeRemoved = new ArrayList<>();

            @Override
            public void readRow(Row row, int index) {
                logger.debug("read row: {}", index);
                if (row == null) {
                    rowLIndexToBeRemoved.add(index);
                }
            }

            @Override
            public void readCell(Cell cell, int index) {
                if (StringUtil.isEmpty(formatter.formatCellValue(cell))) {
                    blankCellCount++;
                }
            }

            @Override
            public void readRowComplete(Row row) {
                if (row.getLastCellNum() == blankCellCount) {
                    rowLIndexToBeRemoved.add(row.getRowNum());
                }
            }

            @Override
            public void readSheetComplete(Sheet sheet) {
                logger.debug("sheet: {} read complete", sheet.getSheetName());
                for (int i = rowLIndexToBeRemoved.size() - 1; i > -1; i--) {
                    Row row = sheet.getRow(rowLIndexToBeRemoved.get(i));
                    if (row == null) {
                        if (rowLIndexToBeRemoved.get(i) < sheet.getLastRowNum()) {
                            sheet.shiftRows(rowLIndexToBeRemoved.get(i) + 1, sheet.getLastRowNum(), -1);
                        }
                    } else {
                        try {
                            if (row.getRowNum() < sheet.getLastRowNum()) {
                                sheet.shiftRows(row.getRowNum() + 1, sheet.getLastRowNum(), -1);
                            } else {
                                sheet.removeRow(row);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                rowLIndexToBeRemoved.clear();
            }

            @Override
            public void readWorkbookComplete(Workbook workbook) throws IOException {
                try(
                    FileOutputStream fos = new FileOutputStream(targetExcel)
                ) {
                    workbook.write(fos);
                }
            }
        });
    }

    public static Workbook exportWorkbook(String sheetName, List<String> headerList, List<String> keyList, Collection<Object> objectCollection) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet;
        if(StringUtil.isEmpty(sheetName)) {
            sheet = workbook.createSheet();
        } else {
            sheet = workbook.createSheet(sheetName);
        }
        int rowIndex = 0;
        //header
        if(!CollectionUtil.isEmpty(headerList)) {
            Row row = sheet.createRow(rowIndex++);
            for(int i=0; i<headerList.size(); i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(headerList.get(i));
                CellStyle cellStyle = workbook.createCellStyle();
                Font font = workbook.createFont();
                font.setBold(true);
                cellStyle.setFont(font);
                cellStyle.setAlignment(HorizontalAlignment.CENTER);
                cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                cellStyle.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
                cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                cellStyle.setBorderBottom(BorderStyle.THIN); //下边框
                cellStyle.setBorderLeft(BorderStyle.THIN);//左边框
                cellStyle.setBorderTop(BorderStyle.THIN);//上边框
                cellStyle.setBorderRight(BorderStyle.THIN);//右边框
                cell.setCellStyle(cellStyle);
            }
        }
        //content
        if(!CollectionUtil.isEmpty(keyList) && !CollectionUtil.isEmpty(objectCollection)) {
            for(Object object: objectCollection) {
                if(object != null) {
                    Row row = sheet.createRow(rowIndex++);
                    for(int i=0; i<keyList.size(); i++) {
                        Cell cell = row.createCell(i);
                        Object obj = ReflectUtil.accessValue(object, keyList.get(i));
                        cell.setCellValue(obj == null ? "" : obj.toString());
                    }
                }
            }
        }
        //auto size column
        for(int i=0; i<headerList.size(); i++) {
            int oldColumnWidth = sheet.getColumnWidth(i);
            if(oldColumnWidth < MAX_CELL_WIDTH) {
                sheet.autoSizeColumn(i);
                int autoSizeColumnWidth = sheet.getColumnWidth(i);
                if(autoSizeColumnWidth > MAX_CELL_WIDTH) {
                    sheet.setColumnWidth(i, MAX_CELL_WIDTH);
                } else if(autoSizeColumnWidth < MIN_CELL_WIDTH) {
                    sheet.setColumnWidth(i, MIN_CELL_WIDTH);
                }
            }
        }
        return workbook;
    }

    public static void printExcel(String path) throws IOException {
        ExcelUtil.readWorkbook(path, new AbstractReadExcelCallBack() {
            private final DataFormatter formatter = new DataFormatter();

            @Override
            public void readCell(Cell cell, int index) {
                System.out.print(formatter.formatCellValue(cell) + "\t");
            }

            @Override
            public void readRowComplete(Row row) {
                System.out.println();
            }
        });
    }
}
