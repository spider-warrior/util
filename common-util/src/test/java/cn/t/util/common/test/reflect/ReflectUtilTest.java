package cn.t.util.common.test.reflect;

import cn.t.util.common.reflect.ReflectUtil;

/**
 * @author yj
 * @since 2020-02-08 10:02
 **/
public class ReflectUtilTest {

    public static void main(String[] args) {
        Entity entity = new Entity();
        entity.id = 100;
        entity.name = "xiaoming";
        System.out.println(ReflectUtil.accessValue(entity, "id"));
        System.out.println(ReflectUtil.accessValue(entity, "name"));
        System.out.println(ReflectUtil.accessValue(entity, "id"));
        System.out.println(ReflectUtil.accessValue(entity, "name"));
    }

    private static class Entity {
        private long id;
        public String name;
    }
}
