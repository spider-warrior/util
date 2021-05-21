package cn.t.util.doc;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public abstract class AbstractReadExcelCallBack implements ReadExcelCallBack {

    private static final Logger logger = LoggerFactory.getLogger(AbstractReadExcelCallBack.class);

    @Override
    public void readWorkbook(Workbook workbook) {
        logger.debug("read file: {}", workbook);
    }

    @Override
    public void readSheet(Sheet sheet) {
        if (sheet != null) {
            logger.debug("read sheet: {}", sheet.getSheetName());
        }
    }

    @Override
    public void readRow(Row row, int index) {
        logger.debug("read row: {}", index);
    }

    @Override
    public void readCell(Cell cell, int index) {
    }

    @Override
    public void readRowComplete(Row row) {
    }

    @Override
    public void readSheetComplete(Sheet sheet) {
    }

    @Override
    public void readWorkbookComplete(Workbook workbook) throws IOException {
    }
}
