package cn.t.util.internationalize.test;

import cn.t.util.internationalize.MessageUtil;
import cn.t.util.internationalize.ResourceBundleControl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.ResourceBundle;

public class MessageUtilTest {

    Locale chinaLocale;
    Locale americaLocale;

    @Before
    public void init() {
        chinaLocale = new Locale("zh", "CN");
        americaLocale = new Locale("en", "US");
    }

    @Test
    public void moneyFormat() {
        final double money = 2972.29d;

        NumberFormat format = NumberFormat.getCurrencyInstance(chinaLocale);
        System.out.println("中国：" + format.format(money));

        NumberFormat format2 = NumberFormat.getCurrencyInstance(americaLocale);
        System.out.println("美国：" + format2.format(money));
    }

    @Test
    public void dateFormat() {
        Date date=new Date();
        DateFormat format=DateFormat.getDateInstance(DateFormat.MEDIUM, chinaLocale);
        System.out.println("中国：" +format.format(date));

        DateFormat format2=DateFormat.getDateInstance(DateFormat.MEDIUM, americaLocale);
        System.out.println("美国：" +format2.format(date));
    }

    /**
     * pattern1 是简单形式的格式化信息串，通过 {n} 占位符来指定动态参数的替换位置索引，{0} 表示第一个参数， {1} 表示第二个参数，以此类推
     * pattern2 格式化信息串除了参数位置索引外，还指定了参数的类型和样式 。 这种语法很灵活，比如一个参数可以出现在多处：如 {1,time,short} 表示从第二个入参中获取时间部分的值，显示为短样式时间；而 {1,date,long} 表示从第二个入参中获取日期部分的值，显示为长样式时间。
     * */
    @Test
    public void placeHolderTest() {
        //定义占位符参数
        Object[] params={"Jack", new GregorianCalendar().getTime(), 238.2E5};
        //定义占位符参数
        String pattern1="{0}，您好！您的账户在 {1} 收到 {2} 元";
        System.out.println(MessageFormat.format(pattern1,params));//使用默认本地化对象

        String pattern2="{0}，hello！Your account have received {2, number, currency} at {1, time, short} on {1, date, long}";
        System.out.println(new MessageFormat(pattern2,Locale.US).format(params));//使用指定的本地化对象
    }

    @Test
    public void resourceBundleTest() {
        final String baseName = "i18n/content";
        final String key = "index.greeting";
        System.out.println("中国："+ ResourceBundle.getBundle(baseName, Locale.CHINA).getString(key));
        System.out.println("美国："+ResourceBundle.getBundle(baseName, Locale.US).getString(key));
    }

    @Test
    public void resourceBundleWithParamTest() {
//        Object[] params = {"Jack", new GregorianCalendar().getTime()};
        Object[] params = {"Jack", new Date()};

        ResourceBundle rbChina = ResourceBundle.getBundle("i18n/params", Locale.CHINA);
        System.out.println("中国：" + new MessageFormat(rbChina.getString("index.greeting"), Locale.CHINA).format(params));

        ResourceBundle rbUs = ResourceBundle.getBundle("i18n/params", Locale.US);
        System.out.println("美国：" + new MessageFormat(rbUs.getString("index.greeting"), Locale.US).format(params));
    }

    @Test
    public void resourceBundleControlTest() {

//        Locale locale = null;
        Locale locale = Locale.US;
        String BUNDLE_NAME = "i18n/params";
        ResourceBundleControl RESOURCE_BUNDLE_CONTROL = new ResourceBundleControl();

        ResourceBundle bundle;
        if (locale == null) {
            bundle = ResourceBundle.getBundle(BUNDLE_NAME, RESOURCE_BUNDLE_CONTROL);
        } else {
            bundle = ResourceBundle.getBundle(BUNDLE_NAME, locale, RESOURCE_BUNDLE_CONTROL);
        }
        System.out.println(bundle.getString("index.greeting"));
    }

    @Test
    public void getStringTest() {
//        String baseName = "i18n/params";
        String baseName = "i18n/params_1";
        String key = "index.greeting";
        System.out.println(MessageUtil.getString(baseName, key));
    }

    @After
    public void destroy() {

    }

}
