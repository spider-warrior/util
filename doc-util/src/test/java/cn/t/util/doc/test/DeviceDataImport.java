package cn.t.util.doc.test;

import cn.t.util.doc.ExcelUtil;
import cn.t.util.doc.test.guobiao.DefaultGuoBiaoMacTranslator;
import cn.t.util.doc.test.guobiao.GuoBiaoReadExcelCallBack;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;

import java.io.IOException;

public class DeviceDataImport {

    private static final String UPDATE_DEVICE_BUILDING_ID_SQL = "update xf_device device set buildingid = (select id from xf_building building where unitId= and building.name = device.buildingname) where id >= ;";
    private static final String UPDATE_DEVICE_FLOOR_ID_SQL = "update xf_device device set floorId = (select id from xf_building_floor floor where floor.buildingId = device.buildingId and floor.floorname = device.floornumber) where id >= ;";
    private static final String UPDATE_DEVICE_ROOM_ID_SQL = "update xf_device device set roomId = (select id from xf_building_room where floorId = device.floorId and roomnumber = device.roomnumber) where unitId = and id >= ;";
    private static final String UPDATE_DEVICE_COORDINATE_BY_BUILDING_ID_SQL = "update xf_device device set device.pointx = (select pointx from xf_building where id = device.buildingid), device.pointy = (select pointy from xf_building where id = device.buildingid) where unitId= and device.id >=6500;";
    private static final String UPDATE_BUILDING_ROOM_FLOOR_UNIT_ID = "update xf_building_room building_room set building_room.floorunitid = (select id from xf_floor_unit where floorid = building_room.floorid) where floorid in(select floor.id from xf_building building left join xf_building_floor floor on building.id = floor.buildingId  where building.unitId = 5)";
    private static final String SELECT_DUPLICATED_ROOM_LOCATION = "select building.name||'-'||floor.floorname from xf_building_floor floor left join xf_building building on floor.buildingId = building.id where floor.id in(select floorId from xf_building_room group by floorId, roomnumber having count(1) > 1);";

    /**
     * 邵阳数据导入
     * */
    @Test
    public void testImportShaoYangData() throws IOException, InvalidFormatException {
        String path = "/home/amen/Desktop/邵阳第二人民医院编码表.xlsx";
        String unitName = "邵阳市第二人民医院";
        String unitId = "1848";
        String controllerId = "50051";
        int idStart = 50100;
        ExcelUtil.readWorkbook(path, new GuoBiaoReadExcelCallBack(new DefaultGuoBiaoMacTranslator(), unitName, unitId, controllerId, idStart));
        System.out.println(UPDATE_DEVICE_BUILDING_ID_SQL);
        System.out.println(UPDATE_DEVICE_FLOOR_ID_SQL);
        System.out.println(UPDATE_DEVICE_ROOM_ID_SQL);
        System.out.println(UPDATE_DEVICE_COORDINATE_BY_BUILDING_ID_SQL);
        System.out.println(UPDATE_BUILDING_ROOM_FLOOR_UNIT_ID);
    }

}
