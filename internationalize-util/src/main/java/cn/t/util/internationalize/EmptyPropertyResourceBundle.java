package cn.t.util.internationalize;

import java.util.Enumeration;
import java.util.ResourceBundle;

/**
 * @author yj
 * @since 2020-03-05 18:48
 **/
public class EmptyPropertyResourceBundle extends ResourceBundle {


    @Override
    protected Object handleGetObject(String key) {
        return "";
    }

    @Override
    public Enumeration<String> getKeys() {
        return new Enumeration<String>() {

            @Override
            public boolean hasMoreElements() {
                return false;
            }

            @Override
            public String nextElement() {
                return null;
            }
        };
    }
}
