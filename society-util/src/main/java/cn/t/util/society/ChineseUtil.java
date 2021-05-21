package cn.t.util.society;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChineseUtil {

    private static final Logger logger = LoggerFactory.getLogger(ChineseUtil.class);

    /**
     * 汉字转拼音
     * @param hanzi xxx
     * @return xxx
     */
    public static String toPinyin(String hanzi) {
        char[] chars = hanzi.trim().toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);// 输出拼音全部小写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
        defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);//'ü'用"v"代替
        StringBuilder sb = new StringBuilder();
        try {
            for (int i = 0; i < chars.length; i++) {
                if (String.valueOf(chars[i]).matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音
                    sb.append(PinyinHelper.toHanyuPinyinStringArray(chars[i], defaultFormat)[0]);
                } else {// 如果字符不是中文,则不转换
                    sb.append(chars[i]);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            System.out.println("字符不能转成汉语拼音");
        }
        return sb.toString();
    }

    public static String getFirstLettersUp(String hanzi) {
        return getFirstLetters(hanzi, HanyuPinyinCaseType.UPPERCASE);
    }

    public static String getFirstLettersLo(String hanzi) {
        return getFirstLetters(hanzi, HanyuPinyinCaseType.LOWERCASE);
    }

    private static String getFirstLetters(String hanzi, HanyuPinyinCaseType caseType) {
        char[] chars = hanzi.trim().toCharArray();
        StringBuilder sb = new StringBuilder();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(caseType);// 输出拼音全部大写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
        try {
            for (char c : chars) {
                String str = String.valueOf(c);
                if (str.matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音,并取第一个字母
                    sb.append(PinyinHelper.toHanyuPinyinStringArray(c, defaultFormat)[0].substring(0, 1));
                } else if (str.matches("[0-9]+")) {// 如果字符是数字,取数字
                    sb.append(c);
                } else if (str.matches("[a-zA-Z]+")) {// 如果字符是字母,取字母
                    sb.append(c);
                } else {// 否则不转换
                    sb.append(c);//如果是标点符号的话，带着
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            logger.error("字符不能转成汉语拼音", e);
        }
        return sb.toString();
    }
}
