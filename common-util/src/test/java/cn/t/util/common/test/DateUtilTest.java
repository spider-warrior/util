package cn.t.util.common.test;

import cn.t.util.common.DateUtil;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.util.Date;

public class DateUtilTest {

    @Test
    public void nowDateStringTest() {
        System.out.println(DateUtil.nowDateString().concat(":").concat("1"));
    }

    @Test
    public void nowDateTimeStringTest() {
        System.out.println(DateUtil.nowDateTimeString().concat(":").concat("1"));
    }

    @Test
    public void stringToLocalDateTimeTest() {
        String str1 = "1991-10-10";
        String str2 = "1991-10-10 10";
        String str3 = "1991-10-10 10:10";
        String str4 = "1991-10-10 10:10:10";
        String str5 = "1991-10-10 10:10:10.100";
        DateTimeFormatter dateTimeFormatter =
            new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd[ [HH][:mm][:ss][.SSS]]")
//                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
//                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
//                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .toFormatter();
        dateTimeFormatter.withResolverStyle(ResolverStyle.STRICT);
        System.out.println(LocalDateTime.parse(str1, dateTimeFormatter));
        System.out.println(LocalDateTime.parse(str2, dateTimeFormatter));
        System.out.println(LocalDateTime.parse(str3, dateTimeFormatter));
        System.out.println(LocalDateTime.parse(str4, dateTimeFormatter));
        System.out.println(LocalDateTime.parse(str5, dateTimeFormatter));
    }


    @Test
    public void getBeginTimeOfMonth() {
        LocalDateTime localDateTime = DateUtil.getBeginDateTimeOfMonth();
        System.out.println(localDateTime);
        System.out.println(DateUtil.localDateTimeToMills(localDateTime));

        System.out.println(System.currentTimeMillis());
        System.out.println(DateUtil.localDateTimeToMills(LocalDateTime.now()));

        LocalDate localDate = DateUtil.getBeginDateOfMonth();
        System.out.println(localDate);
        System.out.println(localDate.minusMonths(3));

    }

    @Test
    public void test() {
        System.out.println(DateUtil.getBeginTimestampOfYesterday());
        System.out.println(DateUtil.getBeginTimestampOfToday());
    }

    @Test
    public void convertToZonedDateTimeStringTest() {
        System.out.println(DateUtil.convertToZonedDateTimeString(new Date()));
    }
}
