package cn.t.util.common.math;

public class LogarithmUtil {

    /**
     * 换底公式: logx(y) = loge(y) / loge(x)
     * @param value 计算的值
     * @param base 底
     * @return value
     * */
    public static double log(double value, double base) {
        return Math.log(value) / Math.log(base);
    }

    /**
     * 求指数(以底为准)
     * create time: 9/9/19
     * @author   yj
     * @param value 底数
     * @param base 指数
     * @return value
     */
    public static int logDown(double value, double base) {
        return (int)log(value, base);
    }

    /**
     * 求指数(以顶为准)
     * @param value 底数
     * @param base 指数
     * @return value
     */
    public static int logUp(double value, double base) {
        return (int) Math.ceil(log(value, base));
    }
}
