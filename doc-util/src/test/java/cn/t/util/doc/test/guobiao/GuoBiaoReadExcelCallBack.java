package cn.t.util.doc.test.guobiao;

import cn.t.util.doc.AbstractReadExcelCallBack;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GuoBiaoReadExcelCallBack extends AbstractReadExcelCallBack {

    private GuoBiaoMacTranslator guoBiaoMacTranslator;

    private String unitName;
    private String unitId;
    private String controllerId;
    private int idStart;


    private static final Logger logger = LoggerFactory.getLogger(GuoBiaoReadExcelCallBack.class);
    private static final DataFormatter FORMATTER = new DataFormatter();
    private static final String SQL_TEMPLATE = "insert into XF_DEVICE (ID,NAME, DEVICETYPEID,DEVICETYPENAME,LOCATION,MAC, STARTDATE, LIFEMONTH, STATUS, BUILDINGID, POINTX, POINTY, FLOORID, XRATE, YRATE, HEIGHT, FHEIGHT, CHEIGHT, PHONE, LINKNAME, DEL, UNITID, IDCODE, MODELCODE, ROOMNUMBER, DEVICEURL, MANUFACTORID, CONTROLLERID,FLOORNUMBER,ROOMID, BUILDINGNAME, UNITNAME,FIRM, PLANTER, AGELIMIT, DEVICEBIGTYPEID, DEVICEBIGTYPENAME, ALARMCOUNT, MALFUNCTIONCOUNT, PRODUCTDATE, MAINTENANCEUNIT, MAINTENANCEUSER, MAINTENANCEPHONE, TRANSMISSIONID, CREATETIME, WATERPRESSURE) values ({id}, '{name}', {deviceTypeId}, '{deviceTypeName}', '{location}', '{mac}', null, 0,1,0,0.0,0.0,0, 0, 0, 0,0, 0, '','', 0, {unitId}, '', '', '{roomNumber}','',0,{controllerId}, '{floorNumber}', 0, '{buildingName}', '{unitName}', '', '',0, {bigDeviceTypeId}, '{bigDeviceTypeName}', 0,0,'','','','', 0,null, null);";
    private static final int LAST_CELL_INDEX = 6;

    @Override
    public void readRow(Row row, int index) {
        try {
            if(row.getRowNum() != 0) {
                String mac = "";
                String name = "";
                String location = "";
                String bigDeviceTypeId = "";
                String bigDeviceTypeName = "";
                String deviceTypeId = "";
                String deviceTypeName = "";
                String buildingName = "";
                String floorNumber = "";
                String roomNumber = "";

                int lastCellIndex = row.getLastCellNum();
                if(lastCellIndex < LAST_CELL_INDEX) {
                    throw new RuntimeException(String.format("row: %d, last cell index: %d, expect index: %d", index, lastCellIndex, LAST_CELL_INDEX));
                } else {
                    String huiluNo = null;
                    String deviceNo = null;
                    for (int i = 0; i < lastCellIndex; i++) {
                        Cell currentCell = row.getCell(i);
                        if(i == 0) {
                            //设备名称
                            name = FORMATTER.formatCellValue(currentCell);
                        } else if(i == 1) {
                            //回路
                            deviceNo = FORMATTER.formatCellValue(currentCell);
                        } else if(i == 2) {
                            //地址号
                            huiluNo = FORMATTER.formatCellValue(currentCell);
                        } else if(i == 3) {
                            //设备类型
                            deviceTypeName = FORMATTER.formatCellValue(currentCell);
                            deviceTypeId = "(NVL((select type from xf_device_type where name = '"+ deviceTypeName +"'), -1))";
                            bigDeviceTypeId = "(NVL((select grouptype from xf_device_type where name = '"+ deviceTypeName +"'),-1))";
                        } else if(i == 4) {
                            //建筑
                            buildingName = FORMATTER.formatCellValue(currentCell);
                        } else if(i == 5) {
                            //楼层
                            floorNumber = FORMATTER.formatCellValue(currentCell);
                        } else if(i == 6) {
                            //房间
                            roomNumber = FORMATTER.formatCellValue(currentCell);
                        } else {
                            break;
                        }
                    }
                    mac = guoBiaoMacTranslator.translate(controllerId, huiluNo, deviceNo);
                    String sql = SQL_TEMPLATE.replace("{id}", String.valueOf(idStart++));
                    sql = sql.replace("{mac}", mac);
                    sql = sql.replace("{name}", name);
                    sql = sql.replace("{location}", location);
                    sql = sql.replace("{deviceTypeId}", String.valueOf(deviceTypeId));
                    sql = sql.replace("{deviceTypeName}", deviceTypeName);
                    sql = sql.replace("{bigDeviceTypeId}", bigDeviceTypeId);
                    sql = sql.replace("{bigDeviceTypeName}", bigDeviceTypeName);
                    sql = sql.replace("{unitName}", unitName);
                    sql = sql.replace("{buildingName}", buildingName);
                    sql = sql.replace("{floorNumber}", floorNumber);
                    sql = sql.replace("{roomNumber}", roomNumber);
                    sql = sql.replace("{controllerId}", controllerId);
                    sql = sql.replace("{unitId}", unitId);
                    logger.info(sql);
                }
            }
        } catch (Exception e) {
            System.err.println(String.format("行: %s 解析失败", index + 1));
            throw e;
        }
    }

    public GuoBiaoReadExcelCallBack(GuoBiaoMacTranslator guoBiaoMacTranslator, String unitName, String unitId, String controllerId, int idStart) {
        if(guoBiaoMacTranslator == null || unitName == null || unitId== null || controllerId == null) {
            throw new NullPointerException();
        }
        this.guoBiaoMacTranslator = guoBiaoMacTranslator;
        this.unitName = unitName;
        this.unitId = unitId;
        this.controllerId = controllerId;
        this.idStart = idStart;
    }
}
