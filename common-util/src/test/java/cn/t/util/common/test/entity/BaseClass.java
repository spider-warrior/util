package cn.t.util.common.test.entity;

import cn.t.util.common.reflect.GenericUtil;

public abstract class BaseClass<T> {

    protected Class<T> clazz;

    @SuppressWarnings("unchecked")
    public BaseClass() {
        clazz = (Class<T>)GenericUtil.findTypeParam(this, BaseClass.class, "T");
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }
}
