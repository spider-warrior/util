package cn.t.util.doc;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;

public interface ReadExcelCallBack {

    /**
     * 读取workbook
     * @param workbook 工作簿
     */
    void readWorkbook(Workbook workbook);

    /**
     * 读取sheet
     * @param sheet 工作表
     */
    void readSheet(Sheet sheet);

    /**
     * 读取行
     * @param row   行
     * @param index 下标
     */
    void readRow(Row row, int index);

    /**
     * 读取单元格
     * @param cell  单元格
     * @param index 下标
     */
    void readCell(Cell cell, int index);

    /**
     * 读取完一个row
     * @param row 行
     */
    void readRowComplete(Row row);

    /**
     * 读取完一个sheet
     * @param sheet 工作表
     */
    void readSheetComplete(Sheet sheet);

    /**
     * 读取完一个workbook
     * @param workbook 工作簿
     * @throws IOException io异常
     */
    void readWorkbookComplete(Workbook workbook) throws IOException;

}
