package cn.t.util.society.area;

public class AdministrativeCodeWithB extends AdministrativeCode {

    public static final AdministrativeCode BEIJING = new AdministrativeCodeWithB("北京", "11", null);
    public static final AdministrativeCode BEIJING_XIAQU = new AdministrativeCodeWithB("北京-辖区", "1101", BEIJING);
    public static final AdministrativeCode BEIJING_XIAQU_DONGCHENGQU = new AdministrativeCodeWithB("北京-辖区-东城区", "110101", BEIJING_XIAQU);
    public static final AdministrativeCode BEIJING_XIAQU_XICHENGQU = new AdministrativeCodeWithB("北京-辖区-西城区", "110102", BEIJING_XIAQU);
    public static final AdministrativeCode BEIJING_XIAQU_CHAOYANGQU = new AdministrativeCodeWithB("北京-辖区-朝阳区", "110105", BEIJING_XIAQU);
    public static final AdministrativeCode BEIJING_XIAQU_FENGTAIQU = new AdministrativeCodeWithB("北京-辖区-丰台区", "110106", BEIJING_XIAQU);
    public static final AdministrativeCode BEIJING_XIAQU_SHIJINGSHANQU = new AdministrativeCodeWithB("北京-辖区-石景山区", "110107", BEIJING_XIAQU);
    public static final AdministrativeCode BEIJING_XIAQU_HAIDIANQU = new AdministrativeCodeWithB("北京-辖区-海淀区", "110108", BEIJING_XIAQU);
    public static final AdministrativeCode BEIJING_XIAQU_MENTOUGOUQU = new AdministrativeCodeWithB("北京-辖区-门头沟区", "110109", BEIJING_XIAQU);
    public static final AdministrativeCode BEIJING_XIAQU_FANGSHANQU = new AdministrativeCodeWithB("北京-辖区-房山区", "110111", BEIJING_XIAQU);
    public static final AdministrativeCode BEIJING_XIAQU_TONGZHOUQU = new AdministrativeCodeWithB("北京-辖区-通州区", "110112", BEIJING_XIAQU);
    public static final AdministrativeCode BEIJING_XIAQU_SHUNYIQU = new AdministrativeCodeWithB("北京-辖区-顺义区", "110113", BEIJING_XIAQU);
    public static final AdministrativeCode BEIJING_XIAQU_CHANGPINGQU = new AdministrativeCodeWithB("北京-辖区-昌平区", "110114", BEIJING_XIAQU);
    public static final AdministrativeCode BEIJING_XIAQU_DAXINGQU = new AdministrativeCodeWithB("北京-辖区-大兴区", "110115", BEIJING_XIAQU);
    public static final AdministrativeCode BEIJING_XIAQU_HUAIROUQU = new AdministrativeCodeWithB("北京-辖区-怀柔区", "110116", BEIJING_XIAQU);
    public static final AdministrativeCode BEIJING_XIAQU_PINGGUQU = new AdministrativeCodeWithB("北京-辖区-平谷区", "110117", BEIJING_XIAQU);
    public static final AdministrativeCode BEIJING_XIAN = new AdministrativeCodeWithB("北京-县", "1102", BEIJING);
    public static final AdministrativeCode BEIJING_XIAN_MIYUNXIAN = new AdministrativeCodeWithB("北京-县-密云县", "110228", BEIJING_XIAN);
    public static final AdministrativeCode BEIJING_XIAN_YANQINGXIAN = new AdministrativeCodeWithB("北京-县-延庆县", "110229", BEIJING_XIAN);


    public AdministrativeCodeWithB(String name, String value, AdministrativeCode parentCode) {
        super(name, value, parentCode);
    }
}
