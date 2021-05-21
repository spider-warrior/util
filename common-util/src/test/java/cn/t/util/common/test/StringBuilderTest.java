package cn.t.util.common.test;

import org.junit.Test;

public class StringBuilderTest {

    @Test
    public void insertStringTest() {
        int dotIndex = 3;
        String numberStr = "48000";

        StringBuilder builder = new StringBuilder(numberStr);
        builder.insert(builder.length() - dotIndex, ".");

        System.out.println("result: " + builder.toString());
    }

}
