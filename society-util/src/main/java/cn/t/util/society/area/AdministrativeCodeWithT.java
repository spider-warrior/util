package cn.t.util.society.area;

public class AdministrativeCodeWithT extends AdministrativeCode {

    public static final AdministrativeCode TIANJIN = new AdministrativeCodeWithT("天津", "12", null);
    public static final AdministrativeCode TIANJIN_XIAQU = new AdministrativeCodeWithT("天津-辖区", "1201", TIANJIN);
    public static final AdministrativeCode TIANJIN_XIAQU_HEPINGQU = new AdministrativeCodeWithT("天津-辖区-和平区", "120101", TIANJIN_XIAQU);
    public static final AdministrativeCode TIANJIN_XIAQU_HEDONGQU = new AdministrativeCodeWithT("天津-辖区-河东区", "120102", TIANJIN_XIAQU);
    public static final AdministrativeCode TIANJIN_XIAQU_HEXIQU = new AdministrativeCodeWithT("天津-辖区-河西区", "120103", TIANJIN_XIAQU);
    public static final AdministrativeCode TIANJIN_XIAQU_NANKAIQU = new AdministrativeCodeWithT("天津-辖区-南开区", "120104", TIANJIN_XIAQU);
    public static final AdministrativeCode TIANJIN_XIAQU_HEBEIQU = new AdministrativeCodeWithT("天津-辖区-河北区", "120105", TIANJIN_XIAQU);
    public static final AdministrativeCode TIANJIN_XIAQU_HONGQIAOQU = new AdministrativeCodeWithT("天津-辖区-红桥区", "120106", TIANJIN_XIAQU);
    public static final AdministrativeCode TIANJIN_XIAQU_DONGLIQU = new AdministrativeCodeWithT("天津-辖区-东丽区", "120110", TIANJIN_XIAQU);
    public static final AdministrativeCode TIANJIN_XIAQU_XIQINGQU = new AdministrativeCodeWithT("天津-辖区-西青区", "120111", TIANJIN_XIAQU);
    public static final AdministrativeCode TIANJIN_XIAQU_JINNANQU = new AdministrativeCodeWithT("天津-辖区-津南区", "120112", TIANJIN_XIAQU);
    public static final AdministrativeCode TIANJIN_XIAQU_BEICHENQU = new AdministrativeCodeWithT("天津-辖区-北辰区", "120113", TIANJIN_XIAQU);
    public static final AdministrativeCode TIANJIN_XIAQU_WUQINGQU = new AdministrativeCodeWithT("天津-辖区-武清区", "120114", TIANJIN_XIAQU);
    public static final AdministrativeCode TIANJIN_XIAQU_BAOCHIQU = new AdministrativeCodeWithT("天津-辖区-宝坻区", "120115", TIANJIN_XIAQU);
    public static final AdministrativeCode TIANJIN_XIAQU_BINHAIXINQU = new AdministrativeCodeWithT("天津-辖区-滨海新区", "120116", TIANJIN_XIAQU);
    public static final AdministrativeCode TIANJIN_XIAN = new AdministrativeCodeWithT("天津-县", "1202", TIANJIN);
    public static final AdministrativeCode TIANJIN_XIAN_NINGHEXIAN = new AdministrativeCodeWithT("天津-县-宁河县", "120221", TIANJIN_XIAN);
    public static final AdministrativeCode TIANJIN_XIAN_JINGHAIXIAN = new AdministrativeCodeWithT("天津-县-静海县", "120223", TIANJIN_XIAN);
    public static final AdministrativeCode TIANJIN_XIAN_JIXIAN = new AdministrativeCodeWithT("天津-县-蓟县", "120225", TIANJIN_XIAN);

    public AdministrativeCodeWithT(String name, String value, AdministrativeCode parentCode) {
        super(name, value, parentCode);
    }
}
