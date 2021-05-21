package cn.t.util.media.video.mp4.modal.level1;

import cn.t.util.media.video.mp4.enums.BoxType;
import cn.t.util.media.video.mp4.modal.Box;

/********************************************************************************************
 **                            File Type Box (ftyp)
 **
 --------------------------------------------------------------------------------------------
 **        字段名称            　　|    长度(bytes)   |        有关描述
 --------------------------------------------------------------------------------------------
 **        boxsize               |    4            |        box的长度
 **        boxtype               |    4            |        box的类型
 **        major_brand           |    4            |
 **        minor_version         |    4            |        版本号
 **        compatible_brands     |    4 * N        |        本文件遵从的多种协议（ismo, iso2, mp41）
 * ********************************************************************************************
 *
 * BoxSize         :32
 * BoxType         :ftyp
 * MajorBrand      :isom
 * MinorVersion    :512
 * CompatibleBrand :isom iso2 avc1 mp41
 *
 ********************************************************************************************/
public class FtypBox extends Box {

    private String majorBrand;

    /**
     * 版本号
     */
    private int minorVersion;

    /**
     * 本文件遵从的多种协议（ismo, iso2, mp41）
     */
    private String compatibleBrands;

    public FtypBox() {
        type = BoxType.FTYP.value;
    }

    public String getMajorBrand() {
        return majorBrand;
    }

    public void setMajorBrand(String majorBrand) {
        this.majorBrand = majorBrand;
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public void setMinorVersion(int minorVersion) {
        this.minorVersion = minorVersion;
    }

    public String getCompatibleBrands() {
        return compatibleBrands;
    }

    public void setCompatibleBrands(String compatibleBrands) {
        this.compatibleBrands = compatibleBrands;
    }
}
