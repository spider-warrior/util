//package cn.t.util.doc.test;
//
//import cn.t.util.common.digital.IntUtil;
//import cn.t.util.common.digital.ShortUtil;
//import cn.t.util.doc.AbstractReadExcelCallBack;
//import cn.t.util.doc.ExcelUtil;
//import com.wxsk.xf.env.constant.DeviceTypeEnum;
//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.apache.poi.ss.usermodel.*;
//import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.IOException;
//import java.nio.ByteOrder;
//import java.text.DecimalFormat;
//import java.util.Arrays;
//
//
//
///**
// * other sql
// * 插入楼层单元: insert into xf_floor_unit (ID, FLOORUNITNAME, STATUS, FLOORID) values (ID, '全部', 0, FLOOR_ID);
// * 根据建筑楼层查询floor_unit ID: select id from xf_floor_unit where floorId = (select id from xf_building_floor where buildingId = (select id from xf_building where unitId = 6(UNIT_ID) and name = 'BUILDING_NAME') and floorName = 'FLOOR_NAME') and rownum = 1;
// * 根据建筑楼层查询floor ID: select id from xf_building_floor where buildingId = (select id from xf_building where unitId = 6 and name = '门诊楼') and floorName = '1层';
// * 插入building room: insert into xf_building_room (id, roomNumber, status, floorId, floorUnitId, floorUnitName, sort, xrate, yrate) values (ID, 'roomNumber', 1, floorId, floorUnitId, '济民医院', 0, 0, 0);
// * */
//public class ExcelTest {
//
//    private static final String UPDATE_DEVICE_BUILDING_ID_SQL = "update xf_device device set buildingid = (select id from xf_building building where unitId= and building.name = device.buildingname) where id >= ;";
//    private static final String UPDATE_DEVICE_FLOOR_ID_SQL = "update xf_device device set floorId = (select id from xf_building_floor floor where floor.buildingId = device.buildingId and floor.floorname = device.floornumber) where id >= ;";
//    private static final String UPDATE_DEVICE_ROOM_ID_SQL = "update xf_device device set roomId = (select id from xf_building_room where floorId = device.floorId and roomnumber = device.roomnumber) where unitId = and id >= ;";
//    private static final String UPDATE_DEVICE_COORDINATE_BY_BUILDING_ID_SQL = "update xf_device device set device.pointx = (select pointx from xf_building where id = device.buildingid), device.pointy = (select pointy from xf_building where id = device.buildingid) where unitId= and device.id >=6500;";
//    private static final String UPDATE_BUILDING_ROOM_FLOOR_UNIT_ID = "update xf_building_room building_room set building_room.floorunitid = (select id from xf_floor_unit where floorid = building_room.floorid) where floorid in(select floor.id from xf_building building left join xf_building_floor floor on building.id = floor.buildingId  where building.unitId = 5)";
//
//    /**
//     * 清理excel中的空白行
//     */
//    @Test
//    public void clearBlankRow() throws IOException, InvalidFormatException {
////        ExcelUtil.clearBlankRows("/home/amen/Desktop/area-code.xls", "/home/amen/Desktop/area-code.xls");
//        ExcelUtil.clearBlankRows("/home/amen/Desktop/正定服装城中文联动.xls", "/home/amen/Desktop/正定服装城中文联动-cleaned.xls");
//    }
//
//    @Test
//    public void readExcel() throws IOException, InvalidFormatException {
////        String path = "/home/amen/Desktop/sequenceNo.xlsx";
//        String path = "/home/amen/Desktop/正定服装城中文联动.xls";
//        ExcelUtil.printExcel(path);
//    }
//
//    @Test
//    public void printHeBeiDeviceSql() throws IOException, InvalidFormatException {
////        String path = "/home/amen/Desktop/正定服装城中文设备导入.xls";
////        String path = "/home/amen/Desktop/test-mac.xls";
////        String path = "/home/amen/Desktop/御景楼编码导入0304-tmp.xlsx";
////        String path = "/home/amen/Desktop/鑫盛能源设备编码表(2).xlsx";
////        String path = "/home/amen/Desktop/盛世王朝设备导入0415.xlsx";
////        String path = "/home/amen/Desktop/5.17配电室.xlsx";
////        String path = "/home/amen/Desktop/5.17油库设备编码.xlsx";
////        String path = "/home/amen/Desktop/5.17焦炉车间设备编码表.xlsx";
////        String path = "/home/amen/Desktop/6.5金都设备编码表 .xlsx";
//        String path = "/home/amen/Desktop/6.5正定服装城设备编码表.xlsx";
//
//        ExcelUtil.readWorkbook(path, new PrintHeBeiDeviceSqlCallBack());
//        System.out.println(UPDATE_DEVICE_BUILDING_ID_SQL);
//        System.out.println(UPDATE_DEVICE_FLOOR_ID_SQL);
//        System.out.println(UPDATE_DEVICE_ROOM_ID_SQL);
//        System.out.println(UPDATE_DEVICE_COORDINATE_BY_BUILDING_ID_SQL);
//    }
//
//
//    @Test
//    public void printXinShaoDeviceSql() throws IOException, InvalidFormatException {
//        String path = "/home/amen/Desktop/正定服装城中文设备导入.xls";
//        //String path = "/home/amen/Desktop/新邵设备导入0227-1.xls";
////        String path = "/home/amen/Desktop/新邵设备导入0227-1-test.xls";
//        ExcelUtil.readWorkbook(path, new PrintXinShaoDeviceSqlCallBack());
//        System.out.println(UPDATE_DEVICE_BUILDING_ID_SQL);
//        System.out.println(UPDATE_DEVICE_FLOOR_ID_SQL);
//        System.out.println(UPDATE_DEVICE_ROOM_ID_SQL);
//        System.out.println(UPDATE_DEVICE_COORDINATE_BY_BUILDING_ID_SQL);
//    }
//
//    @Test
//    public void printHuaTianJiuDianDeviceSql() throws IOException, InvalidFormatException {
////        String path = "/home/amen/Desktop/长沙华天酒店0410-1(1).xlsx";
////        String path = "/home/amen/Desktop/5.21长沙华天酒店设备编码表.xlsx";
////        String path = "/home/amen/Desktop/5.21长沙华天酒店设备编码表(2).xlsx";
////        String path = "/home/amen/Desktop/6.11华天新增的设备(1).xlsx";
////        String path = "/home/amen/Desktop/6.28华天酒店设备编码表.xlsx";
////        String path = "/home/amen/Desktop/人民医院编码导入0808.xls";
////        String path = "/home/amen/Desktop/济民医院编码导入0808.xls";
////        String path = "/home/amen/Desktop/济民医院编码导入0815.xls";
////        String path = "/home/amen/Desktop/溆浦中医院编码导入0819-2.xls";
////        String path = "/home/amen/Desktop/女子监狱编码导入0823.xls";
//        String path = "/home/amen/Desktop/邵阳第二人民医院编码表.xlsx";
//
//
//        ExcelUtil.readWorkbook(path, new PrintHuaTianJiuDianDeviceSqlCallBack());
//        System.out.println(UPDATE_DEVICE_BUILDING_ID_SQL);
//        System.out.println(UPDATE_DEVICE_FLOOR_ID_SQL);
//        System.out.println(UPDATE_DEVICE_ROOM_ID_SQL);
//        System.out.println(UPDATE_DEVICE_COORDINATE_BY_BUILDING_ID_SQL);
//    }
//
//    @Test
//    public void printFanHaiSanJiangDeviceSql() throws IOException, InvalidFormatException {
////        String path = "/home/amen/Desktop/4.18魏源医院lora烟感 .xlsx";
////        String path = "/home/amen/Desktop/坡子街项目（云服）设备导入0704.xlsx";
//        String path = "/home/amen/Desktop/溆浦县-红花园工业园设备导入0722-1.xlsx";
//        ExcelUtil.readWorkbook(path, new PrintFanHaiSanJiangDeviceSqlCallBack());
//        System.out.println(UPDATE_DEVICE_BUILDING_ID_SQL);
//        System.out.println(UPDATE_DEVICE_FLOOR_ID_SQL);
//        System.out.println(UPDATE_DEVICE_ROOM_ID_SQL);
//        System.out.println(UPDATE_DEVICE_COORDINATE_BY_BUILDING_ID_SQL);
//    }
//
//
//
//
//
//}
//
//class PrintHeBeiDeviceSqlCallBack extends AbstractReadExcelCallBack {
//
//    private static final Logger logger = LoggerFactory.getLogger(PrintHeBeiDeviceSqlCallBack.class);
//    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("000");
//    private static final DataFormatter FORMATTER = new DataFormatter();
//    private static final String SQL_TEMPLATE = "insert into XF_DEVICE (ID, NAME, DEVICETYPEID, DEVICETYPENAME, LOCATION, MAC, STARTDATE, LIFEMONTH, STATUS, BUILDINGID, POINTX, POINTY, FLOORID, XRATE, YRATE, HEIGHT, FHEIGHT, CHEIGHT, PHONE, LINKNAME, DEL, UNITID, IDCODE, MODELCODE, ROOMNUMBER, DEVICEURL, MANUFACTORID, CONTROLLERID, FLOORNUMBER, ROOMID, BUILDINGNAME, UNITNAME, FIRM, PLANTER, AGELIMIT, DEVICEBIGTYPEID, DEVICEBIGTYPENAME, ALARMCOUNT, MALFUNCTIONCOUNT, PRODUCTDATE, MAINTENANCEUNIT, MAINTENANCEUSER, MAINTENANCEPHONE, TRANSMISSIONID, CREATETIME, WATERPRESSURE) values({id}, '{name}', {deviceTypeId}, '{deviceTypeName}', '{location}', '{mac}', null, 0, 1, 2, 0.0, 0.0, 0, 0, 0, 0, 0, 0, null, null, 0, {unitId}, null, null, '{roomNumber}', null, 0, {controllerId}, '{floorNumber}', 4, '{buildingName}', '{unitName}', null, null, 0, 0, null, 0, 0, null, null, null, null, 0, to_timestamp('14-01-201914:37:20.012000', 'dd-mm-yyyyhh24:mi:ss.ff'), null);";
//    private static final int LAST_CELL_INDEX = 8;
//
//    private int id = 600;
//
//    @Override
//    public void readRow(Row row, int index) {
//        try {
//            if(row.getRowNum() != 0) {
//
//                String mac = "";
//                String name = "";
//                String location = "";
//                Integer deviceTypeId = 0;
//                String deviceTypeName = "";
//                String unitName = "";
//                String buildingName = "";
//                String floorNumber = "";
//                String roomNumber = "";
//
//                int hostNo = 1;
//                String controllerId = "142";
//                String unitId = "5";
//
//                int lastCellIndex = row.getLastCellNum();
//                if(lastCellIndex < LAST_CELL_INDEX) {
//                    throw new RuntimeException(String.format("row: %d, last cell index: %d, expect index: %d", index, lastCellIndex, LAST_CELL_INDEX));
//                } else {
//                    //append sys address
//                    StringBuilder deviceAddressBuilder = new StringBuilder(DECIMAL_FORMAT.format(hostNo));
//                    for (int i = 0; i < lastCellIndex; i++) {
//                        Cell currentCell = row.getCell(i);
//                        if(i == 0) {
//                            //append hui lu
//                            deviceAddressBuilder.append(DECIMAL_FORMAT.format(Long.valueOf(FORMATTER.formatCellValue(currentCell))));
//                        } else if(i == 1) {
//                            // append address
//                            deviceAddressBuilder.append(DECIMAL_FORMAT.format(Long.valueOf(FORMATTER.formatCellValue(currentCell))));
//                            name = String.valueOf(deviceAddressBuilder.toString());
//                            int intVal = Integer.parseInt(name);
//                            int[] bytesVal = IntUtil.intToPositiveBytes(intVal, ByteOrder.LITTLE_ENDIAN);
//                            StringBuilder macBuilder = new StringBuilder();
//                            for (int b : bytesVal) {
//                                macBuilder.append(((byte)b) & 0xFF).append(".");
//                            }
//                            macBuilder.deleteCharAt(macBuilder.length() -1);
//                            mac = macBuilder.toString();
//                        } else if(i == 2) {
//                            //location
//                            location = FORMATTER.formatCellValue(currentCell);
//                        } else if(i == 3) {
//                            //device type
//                            DeviceTypeEnum deviceTypeEnum = XfHelper.getDeviceTypeEnum(FORMATTER.formatCellValue(currentCell));
//                            deviceTypeId = deviceTypeEnum.getId();
//                            deviceTypeName = deviceTypeEnum.getName();
//                        } else if(i == 4) {
//                            //unit name
//                            unitName = FORMATTER.formatCellValue(currentCell);
//                        } else if(i == 5) {
//                            //building name
//                            buildingName = FORMATTER.formatCellValue(currentCell);
//                        } else if(i == 6) {
//                            //floor number
//                            floorNumber = FORMATTER.formatCellValue(currentCell);
//                        } else if(i == 7) {
//                            name = FORMATTER.formatCellValue(currentCell);
//                        } else if(i == 8) {
//                            roomNumber = FORMATTER.formatCellValue(currentCell);
//                        }
//                        else {
//                            break;
//                        }
//                    }
//                    String sql = SQL_TEMPLATE.replace("{id}", String.valueOf(id++));
//                    sql = sql.replace("{mac}", mac);
//                    sql = sql.replace("{name}", name);
//                    sql = sql.replace("{location}", location);
//                    sql = sql.replace("{deviceTypeId}", String.valueOf(deviceTypeId));
//                    sql = sql.replace("{deviceTypeName}", deviceTypeName);
//                    sql = sql.replace("{unitName}", unitName);
//                    sql = sql.replace("{buildingName}", buildingName);
//                    sql = sql.replace("{floorNumber}", floorNumber);
//                    sql = sql.replace("{roomNumber}", roomNumber);
//                    sql = sql.replace("{controllerId}", controllerId);
//                    sql = sql.replace("{unitId}", unitId);
//                    logger.info(sql);
//                }
//            }
//        } catch (Exception e) {
//            System.err.println(String.format("行: %s 解析失败", index + 1));
//            throw e;
//        }
//    }
//}
//
//
//class PrintXinShaoDeviceSqlCallBack extends AbstractReadExcelCallBack {
//
//    private static final Logger logger = LoggerFactory.getLogger(PrintXinShaoDeviceSqlCallBack.class);
//    private static final DataFormatter FORMATTER = new DataFormatter();
//    private static final String SQL_TEMPLATE = "insert into XF_DEVICE(ID, NAME, DEVICETYPEID, DEVICETYPENAME, LOCATION, MAC, STARTDATE, LIFEMONTH, STATUS, BUILDINGID, POINTX, POINTY, FLOORID, XRATE, YRATE, HEIGHT, FHEIGHT, CHEIGHT, PHONE, LINKNAME, DEL, UNITID, IDCODE, MODELCODE, ROOMNUMBER, DEVICEURL, MANUFACTORID, CONTROLLERID, FLOORNUMBER, ROOMID, BUILDINGNAME, UNITNAME, FIRM, PLANTER, AGELIMIT, DEVICEBIGTYPEID, DEVICEBIGTYPENAME, ALARMCOUNT, MALFUNCTIONCOUNT, PRODUCTDATE, MAINTENANCEUNIT, MAINTENANCEUSER, MAINTENANCEPHONE, TRANSMISSIONID, CREATETIME, WATERPRESSURE)values({id}, '{name}', {deviceTypeId}, '{deviceTypeName}', '{location}', '{mac}', null, 0, 1, 2, 0.0, 0.0, 0, 0, 0, 0, 0, 0, null, null, 0, {unitId}, null, null, null, null, 0, {controllerId}, '{floorNumber}', 4, '{buildingName}', '{unitName}', null, null, 0, 0, null, 0, 0, null, null, null, null, 0, to_timestamp('14-01-201914:37:20.012000', 'dd-mm-yyyyhh24:mi:ss.ff'), null);";
//    private static final int LAST_CELL_INDEX = 8;
//
//    private int id = 100;
//
//    @Override
//    public void readRow(Row row, int index) {
//        try {
//            if(row.getRowNum() != 0) {
//                String mac = "";
//                String name = "";
//                String location = "";
//                Integer deviceTypeId = 0;
//                String deviceTypeName = "";
//                String unitName = "";
//                String buildingName = "";
//                String floorNumber = "";
//                String controllerId = "5";
//                String unitId = "1";
//
//                int lastCellIndex = row.getLastCellNum();
//                if(lastCellIndex < LAST_CELL_INDEX) {
//                    throw new RuntimeException(String.format("row: %d, last cell index: %d, expect index: %d", index, lastCellIndex, LAST_CELL_INDEX));
//                } else {
//                    //append sys address
//                    StringBuilder deviceAddressBuilder = new StringBuilder();
//                    for (int i = 0; i < lastCellIndex; i++) {
//                        Cell currentCell = row.getCell(i);
//                        if(i == 0) {
//                            //回路
//                            short  huilu = Short.valueOf(FORMATTER.formatCellValue(currentCell));
//                            byte[] bs = ShortUtil.shortToBytes(huilu, ByteOrder.LITTLE_ENDIAN);
//                            deviceAddressBuilder.append(bs[0] & 0xFF).append(".");
//                            deviceAddressBuilder.append(bs[1] & 0xFF);
//                        } else if(i == 1) {
//                            //节点
//                            short address = Short.valueOf(FORMATTER.formatCellValue(currentCell));
//                            byte[] bs = ShortUtil.shortToBytes(address, ByteOrder.LITTLE_ENDIAN);
//                            String low = (bs[0] & 0xFF) + ".";
//                            String high = (bs[1] & 0xFF) + ".";
//                            deviceAddressBuilder.insert(0, low);
//                            deviceAddressBuilder.insert(low.length(), high);
//                            mac = deviceAddressBuilder.toString();
//                        } else if(i == 2) {
//                            //location
//                            location = FORMATTER.formatCellValue(currentCell);
//                        } else if(i == 3) {
//                            //device type
//                            DeviceTypeEnum deviceTypeEnum = XfHelper.getDeviceTypeEnum(FORMATTER.formatCellValue(currentCell));
//                            deviceTypeId = deviceTypeEnum.getId();
//                            deviceTypeName = deviceTypeEnum.getName();
//                        } else if(i == 4) {
//                            //unit name
//                            unitName = FORMATTER.formatCellValue(currentCell);
//                        } else if(i == 5) {
//                            //building name
//                            buildingName = FORMATTER.formatCellValue(currentCell);
//                        } else if(i == 6) {
//                            //floor number
//                            floorNumber = FORMATTER.formatCellValue(currentCell);
//                        } else if(i == 7) {
//                            if(currentCell.getCellTypeEnum() == CellType.FORMULA) {
//                                name = currentCell.getStringCellValue();
//                            } else {
//                                //device name
//                                name = FORMATTER.formatCellValue(currentCell);
//                            }
//                        }
//                        else {
//                            break;
//                        }
//                    }
//                    String sql = SQL_TEMPLATE.replace("{id}", String.valueOf(id++));
//                    sql = sql.replace("{mac}", mac);
//                    sql = sql.replace("{name}", name);
//                    sql = sql.replace("{location}", location);
//                    sql = sql.replace("{deviceTypeId}", String.valueOf(deviceTypeId));
//                    sql = sql.replace("{deviceTypeName}", deviceTypeName);
//                    sql = sql.replace("{unitName}", unitName);
//                    sql = sql.replace("{buildingName}", buildingName);
//                    sql = sql.replace("{floorNumber}", floorNumber);
//                    sql = sql.replace("{controllerId}", controllerId);
//                    sql = sql.replace("{unitId}", unitId);
//                    logger.info(sql);
//                }
//            }
//        } catch (RuntimeException e) {
//            System.err.println(String.format("行: %s 解析失败", index + 1));
//            throw e;
//        }
//    }
//
//}
//
//class PrintHuaTianJiuDianDeviceSqlCallBack extends AbstractReadExcelCallBack {
//
//    private static final Logger logger = LoggerFactory.getLogger(PrintHuaTianJiuDianDeviceSqlCallBack.class);
//    private static final DataFormatter FORMATTER = new DataFormatter();
//    private static final String SQL_TEMPLATE = "insert into XF_DEVICE (ID, NAME, DEVICETYPEID, DEVICETYPENAME, LOCATION, MAC, STARTDATE, LIFEMONTH, STATUS, BUILDINGID, POINTX, POINTY, FLOORID, XRATE, YRATE, HEIGHT, FHEIGHT, CHEIGHT, PHONE, LINKNAME, DEL, UNITID, IDCODE, MODELCODE, ROOMNUMBER, DEVICEURL, MANUFACTORID, CONTROLLERID, FLOORNUMBER, ROOMID, BUILDINGNAME, UNITNAME, FIRM, PLANTER, AGELIMIT, DEVICEBIGTYPEID, DEVICEBIGTYPENAME, ALARMCOUNT, MALFUNCTIONCOUNT, PRODUCTDATE, MAINTENANCEUNIT, MAINTENANCEUSER, MAINTENANCEPHONE, TRANSMISSIONID, CREATETIME, WATERPRESSURE) values({id}, '{name}', {deviceTypeId}, '{deviceTypeName}', '{location}', '{mac}', null, 0, 1, 2, 0.0, 0.0, 0, 0, 0, 0, 0, 0, null, null, 0, {unitId}, null, null, '{roomNumber}', null, 0, {controllerId}, '{floorNumber}', 4, '{buildingName}', '{unitName}', null, null, 0, 0, null, 0, 0, null, null, null, null, 0, to_timestamp('14-01-201914:37:20.012000', 'dd-mm-yyyyhh24:mi:ss.ff'), null);";
//    private static final int LAST_CELL_INDEX = 6;
//
//    private int id = 100;
//
//    @Override
//    public void readRow(Row row, int index) {
//        try {
//            if(row.getRowNum() != 0) {
//                String mac;
//                String name = "";
//                String location = "";
//                Integer deviceTypeId = null;
//                String deviceTypeName = "";
//                String unitName = "";
//                String buildingName = "";
//                String floorNumber = "";
//                String roomNumber = "";
//
//                String controllerId = "14";
//                String unitId = "4";
//
//                int lastCellIndex = row.getLastCellNum();
//                if(lastCellIndex < LAST_CELL_INDEX) {
//                    throw new RuntimeException(String.format("row: %d, last cell index: %d, expect index: %d", index, lastCellIndex, LAST_CELL_INDEX));
//                } else {
//                    String huilu = null;
//                    String deviceNo = null;
//                    for (int i = 0; i < lastCellIndex; i++) {
//                        Cell currentCell = row.getCell(i);
//                        if(i == 0) {
//                            //设备名称
//                            name = FORMATTER.formatCellValue(currentCell);
//                        } else if(i == 1) {
//                            //回路
//                            deviceNo = FORMATTER.formatCellValue(currentCell);
//                        } else if(i == 2) {
//                            //地址号
//                            huilu = FORMATTER.formatCellValue(currentCell);
//                        } else if(i == 3) {
//                            //设备类型
//                            DeviceTypeEnum deviceTypeEnum = XfHelper.getDeviceTypeEnum(FORMATTER.formatCellValue(currentCell));
//                            deviceTypeId = deviceTypeEnum.getId();
//                            deviceTypeName = deviceTypeEnum.getName();
//                        } else if(i == 4) {
//                            //建筑
//                            buildingName = FORMATTER.formatCellValue(currentCell);
//                        } else if(i == 5) {
//                            //楼层
//                            floorNumber = FORMATTER.formatCellValue(currentCell);
//                        } else if(i == 6) {
//                            //房间
//                            roomNumber = FORMATTER.formatCellValue(currentCell);
//                        } else {
//                            break;
//                        }
//                    }
//                    byte[] huiLuBytes = ShortUtil.shortToBytes(Short.parseShort(huilu), ByteOrder.LITTLE_ENDIAN);
//                    byte[] deviceAddressBytes = ShortUtil.shortToBytes(Short.parseShort(deviceNo), ByteOrder.LITTLE_ENDIAN);
//                    byte[] macBytes = Arrays.copyOf(huiLuBytes, huiLuBytes.length + deviceAddressBytes.length);
//                    System.arraycopy(deviceAddressBytes, 0, macBytes, huiLuBytes.length, deviceAddressBytes.length);
//                    StringBuilder builder = new StringBuilder();
//                    for(byte b: macBytes) {
//                        builder.append(b&0xFF).append('.');
//                    }
//                    builder.deleteCharAt(builder.length()-1);
//                    mac = builder.toString();
//
//
//                    String sql = SQL_TEMPLATE.replace("{id}", String.valueOf(id++));
//                    sql = sql.replace("{mac}", mac);
//                    sql = sql.replace("{name}", name);
//                    sql = sql.replace("{location}", location);
//                    sql = sql.replace("{deviceTypeId}", String.valueOf(deviceTypeId));
//                    sql = sql.replace("{deviceTypeName}", deviceTypeName);
//                    sql = sql.replace("{unitName}", unitName);
//                    sql = sql.replace("{buildingName}", buildingName);
//                    sql = sql.replace("{floorNumber}", floorNumber);
//                    sql = sql.replace("{roomNumber}", roomNumber);
//                    sql = sql.replace("{controllerId}", controllerId);
//                    sql = sql.replace("{unitId}", unitId);
////                    //for update
////                    if(!StringUtil.isEmpty(roomNumber)) {
//////                        sql = "update xf_device set roomNumber = '" + roomNumber + "' where unitId=2 and controllerId=36 and mac = '"+ mac +"';";
////                        sql = "update xf_device set name = '"+ name +"', devicetypeid = "+ deviceTypeId + ", devicetypename = '" + deviceTypeName + "' where mac = '"+ mac +"' and unitId=2 and controllerId=36 and (devicetypeid is null or devicetypeid = 202);";
////                        logger.info(sql);
////                    }
//                    logger.info(sql);
//                }
//            }
//        } catch (Exception e) {
//            System.err.println(String.format("行: %s 解析失败", index + 1));
//            throw e;
//        }
//    }
//}
//
//class PrintFanHaiSanJiangDeviceSqlCallBack extends AbstractReadExcelCallBack {
//    private static final Logger logger = LoggerFactory.getLogger(PrintFanHaiSanJiangDeviceSqlCallBack.class);
//    private static final DataFormatter FORMATTER = new DataFormatter();
//    private static final String SQL_TEMPLATE = "insert into XF_DEVICE(ID, NAME, DEVICETYPEID, DEVICETYPENAME, LOCATION, MAC, STARTDATE, LIFEMONTH, STATUS, BUILDINGID, POINTX, POINTY, FLOORID, XRATE, YRATE, HEIGHT, FHEIGHT, CHEIGHT, PHONE, LINKNAME, DEL, UNITID, IDCODE, MODELCODE, ROOMNUMBER, DEVICEURL, MANUFACTORID, CONTROLLERID, FLOORNUMBER, ROOMID, BUILDINGNAME, UNITNAME, FIRM, PLANTER, AGELIMIT, DEVICEBIGTYPEID, DEVICEBIGTYPENAME, ALARMCOUNT, MALFUNCTIONCOUNT, PRODUCTDATE, MAINTENANCEUNIT, MAINTENANCEUSER, MAINTENANCEPHONE, TRANSMISSIONID, CREATETIME, WATERPRESSURE)values({id}, '{name}', {deviceTypeId}, '{deviceTypeName}', '{location}', '{mac}', null, 0, 1, 2, 0.0, 0.0, 0, 0, 0, 0, 0, 0, null, null, 0, {unitId}, null, null, '{roomStr}', null, 0, {controllerId}, '{floorNumber}', 4, '{buildingName}', '{unitName}', null, null, 0, 0, null, 0, 0, null, null, null, null, 0, to_timestamp('14-01-201914:37:20.012000', 'dd-mm-yyyyhh24:mi:ss.ff'), null);";
//    private static final int LAST_CELL_INDEX = 6;
//
//    private int id = 300;
//
//    @Override
//    public void readRow(Row row, int index) {
//        try {
//            if(row.getRowNum() != 0) {
//                String mac = "";
//                String name = "";
//                String location = "";
//                Integer deviceTypeId = null;
//                String deviceTypeName = "";
//                String buildingName = "";
//                String floorNumber = "";
//                String roomStr = "";
//
//                String controllerId = "0";
//                String unitId = "9";
//                String unitName = "红花园工业园";
//
//                int lastCellIndex = row.getLastCellNum();
//                if(lastCellIndex < LAST_CELL_INDEX) {
//                    throw new RuntimeException(String.format("row: %d, last cell index: %d, expect index: %d", index, lastCellIndex, LAST_CELL_INDEX));
//                } else {
//                    for (int i = 0; i < lastCellIndex; i++) {
//                        Cell currentCell = row.getCell(i);
//                        if(i == 0) {
//                            //设备编码
//                            mac = "sanjiang-".concat(FORMATTER.formatCellValue(currentCell));
//                        } else if(i == 1) {
//                            //设备名称
//                            name = FORMATTER.formatCellValue(currentCell);
//                        } else if(i == 2) {
//                            //设备类型
//                            DeviceTypeEnum deviceTypeEnum = XfHelper.getDeviceTypeEnum(FORMATTER.formatCellValue(currentCell));
//                            deviceTypeId = deviceTypeEnum.getId();
//                            deviceTypeName = deviceTypeEnum.getName();
//                        } else if(i == 3) {
//                            //单位
//                            unitName = FORMATTER.formatCellValue(currentCell);
//                        } else if(i == 4) {
//                            //位置
//                            location = FORMATTER.formatCellValue(currentCell);
//                        } else if(i == 5) {
//                            //建筑
//                            buildingName = FORMATTER.formatCellValue(currentCell);
//                        } else if(i == 6) {
//                            //楼层
//                            floorNumber = FORMATTER.formatCellValue(currentCell);
//                        } else if(i == 7) {
//                            //房间
//                            roomStr = FORMATTER.formatCellValue(currentCell);
//                        } else {
//                            break;
//                        }
//                    }
//                    String sql = SQL_TEMPLATE.replace("{id}", String.valueOf(id++));
//                    sql = sql.replace("{mac}", mac);
//                    sql = sql.replace("{name}", name);
//                    sql = sql.replace("{location}", location);
//                    sql = sql.replace("{deviceTypeId}", String.valueOf(deviceTypeId));
//                    sql = sql.replace("{deviceTypeName}", deviceTypeName);
//                    sql = sql.replace("{unitName}", unitName);
//                    sql = sql.replace("{buildingName}", buildingName);
//                    sql = sql.replace("{floorNumber}", floorNumber);
//                    sql = sql.replace("{controllerId}", controllerId);
//                    sql = sql.replace("{unitId}", unitId);
//                    sql = sql.replace("{roomStr}", roomStr);
//                    logger.info(sql);
//                }
//            }
//        } catch (Exception e) {
//            System.err.println(String.format("行: %s 解析失败", index + 1));
//            throw e;
//        }
//    }
//}
//
//class XfHelper {
//    static DeviceTypeEnum getDeviceTypeEnum(String typeName) {
//        if(typeName != null && typeName.trim().length() > 0) {
//            typeName = typeName.trim();
//            for (DeviceTypeEnum typeEnum: DeviceTypeEnum.values()) {
//                if(typeEnum.getName().equalsIgnoreCase(typeName)) {
//                    return typeEnum;
//                }
//            }
//            throw new RuntimeException(String.format("未找到系统中的设备类型: %s", typeName));
//        }
//        return DeviceTypeEnum.UNKNOWN_TYPE;
//    }
//}
