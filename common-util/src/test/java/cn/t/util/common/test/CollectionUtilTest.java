package cn.t.util.common.test;

import cn.t.util.common.CollectionUtil;
import cn.t.util.common.RandomUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yj
 * @since 2019-11-20 19:04
 **/
public class CollectionUtilTest {

    @Test
    public void splitListTest() {
        int size = RandomUtil.randomInt(20, 89);
        System.out.println("list size: " + size);
        List<String> list = new ArrayList<>();
        for(int i=10; i<size + 10; i++) {
            list.add(String.valueOf(i));
        }
        System.out.println(list);
        List<List<String>> result = CollectionUtil.splitList(list);
        for(List<String> l: result) {
            System.out.println(l);
        }
    }
}
