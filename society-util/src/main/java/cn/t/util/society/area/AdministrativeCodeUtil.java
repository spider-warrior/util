package cn.t.util.society.area;

import cn.t.util.common.StringUtil;
import cn.t.util.doc.AbstractReadExcelCallBack;
import cn.t.util.doc.ExcelUtil;
import cn.t.util.society.ChineseUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;

import java.io.IOException;

/**
 * 行政编码工具
 */
public class AdministrativeCodeUtil {

    public static void generateAdministrativeCode(String path) throws IOException, InvalidFormatException {

        ExcelUtil.readWorkbook(path, new AbstractReadExcelCallBack() {

            private DataFormatter formatter = new DataFormatter();
            private String provinceCode;
            private String cityCode;
            private String countyCode;

            private String provinceAdministrative;
            private String provinceAdministrativeName;
            private String cityAdministrative;
            private String cityAdministrativeName;

            @Override
            public void readCell(Cell cell, int index) {
                //行政编码
                if (index == 0) {
                    String code = formatter.formatCellValue(cell).trim();
                    code = code.replaceAll("\\u00A0", "");
                    if (!StringUtil.isEmpty(code) && StringUtil.isAllNumeric(code)) {
                        //首先填充省
                        if (provinceCode == null) {
                            provinceCode = code.substring(0, 2);
                        } else if (cityCode == null) {
                            //然后填充市
                            cityCode = code.substring(2, 4);
                        } else {
                            //对比判断是否有所改变
                            String pCode = code.substring(0, 2);
                            //省编码不同
                            if (!provinceCode.equals(pCode)) {
                                provinceCode = pCode;
                                cityCode = null;
                                provinceAdministrative = null;
                                provinceAdministrativeName = null;
                                cityAdministrative = null;
                                cityAdministrative = null;
                            } else {
                                //市编码不同
                                String cCode = code.substring(2, 4);
                                if (!cityCode.equals(cCode)) {
                                    cityCode = cCode;
                                    cityAdministrative = null;
                                    cityAdministrative = null;
                                } else {
                                    countyCode = code;
                                }
                            }
                        }
                    }
                } else if (index == 1) {
                    //地区名称
                    String name = formatter.formatCellValue(cell).trim();
                    name = name.replaceAll("\\u00A0", "");
                    if (StringUtil.isEmpty(name)) {
                        throw new RuntimeException("地区名称为空");
                    }


                    // ===> code too large
//                    //县级行政编码
//                    if(countyCode != null) {
//                        System.out.println(String.format("%s(\"%s\", \"%s\", %s)", cityAdministrative + "_" +ChineseUtil.toPinyin(name), cityAdministrativeName + "-" + name, countyCode, cityAdministrative));
//                        countyCode = null;
//                    } else if(cityCode != null) {
//                        cityAdministrative = provinceAdministrative + "_" +ChineseUtil.toPinyin(name);
//                        cityAdministrativeName = provinceAdministrativeName + "-" + name;
//                        //市级行政编码
//                        System.out.println(String.format("%s(\"%s\", \"%s\", %s)", cityAdministrative, cityAdministrativeName, provinceCode + cityCode, provinceAdministrative));
//
//                    } else {
//                        provinceAdministrative = ChineseUtil.toPinyin(name);
//                        provinceAdministrativeName = name;
//                        System.out.println(String.format("%s(\"%s\", \"%s\", null)", provinceAdministrative, name, provinceCode));
//                    }

                    // ===> code too large
//                    //县级行政编码
//                    if(countyCode != null) {
//                        System.out.println(String.format("%s,", cityAdministrative + "_" +ChineseUtil.toPinyin(name), cityAdministrativeName + "-" + name, countyCode, cityAdministrative));
//                        countyCode = null;
//                    } else if(cityCode != null) {
//                        cityAdministrative = provinceAdministrative + "_" +ChineseUtil.toPinyin(name);
//                        cityAdministrativeName = provinceAdministrativeName + "-" + name;
//                        //市级行政编码
//                        System.out.println(String.format("%s,", cityAdministrative, cityAdministrativeName, provinceCode + cityCode, provinceAdministrative));
//
//                    } else {
//                        provinceAdministrative = ChineseUtil.toPinyin(name);
//                        provinceAdministrativeName = name;
//                        System.out.println(String.format("%s,", provinceAdministrative, name, provinceCode));
//                    }


                    //县级行政编码
                    if (countyCode != null) {
                        System.out.println(String.format("private static final AdministrativeCode %s = new AdministrativeCode(\"%s\", \"%s\", %s);", cityAdministrative + "_" + ChineseUtil.toPinyin(name), cityAdministrativeName + "-" + name, countyCode, cityAdministrative));
                        countyCode = null;
                    } else if (cityCode != null) {
                        cityAdministrative = provinceAdministrative + "_" + ChineseUtil.toPinyin(name);
                        cityAdministrativeName = provinceAdministrativeName + "-" + name;
                        //市级行政编码
                        System.out.println(String.format("private static final AdministrativeCode %s = new AdministrativeCode(\"%s\", \"%s\", %s);", cityAdministrative, cityAdministrativeName, provinceCode + cityCode, provinceAdministrative));

                    } else {
                        provinceAdministrative = ChineseUtil.toPinyin(name);
                        provinceAdministrativeName = name;
                        System.out.println(String.format("private static final AdministrativeCode %s = new AdministrativeCode(\"%s\", \"%s\", null);", provinceAdministrative, name, provinceCode));
                    }
                }
            }
        });
    }
}
