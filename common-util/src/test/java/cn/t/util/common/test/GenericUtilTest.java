package cn.t.util.common.test;

import cn.t.util.common.reflect.GenericUtil;
import cn.t.util.common.JsonUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GenericUtilTest {

    public static void main(String[] args) throws Exception {
//        System.out.println(getGeneric(StudentServiceImpl.class).toJsonString());
//        System.out.println(getGeneric(ArrayList.class).toJsonString());
        SubParent<Date> subParent = new SubParent<>();
        System.out.println(JsonUtil.serialize(GenericUtil.getGeneric(subParent.getClass())));
    }
}


class GenSuper<S> {

}

class GenParent<T, X> extends GenSuper<Double> {

}

class SubParent<Sub> extends GenParent<List<Map<String, Map<Integer, Long>>>, Set<Map<String, Integer>>> {

}
