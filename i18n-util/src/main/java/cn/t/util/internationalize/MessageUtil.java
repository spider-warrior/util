package cn.t.util.internationalize;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageUtil {

    private static final Locale EMPTY_LOCALE = new Locale("", "");
    private static final ResourceBundleControl RESOURCE_BUNDLE_CONTROL = new ResourceBundleControl();

    public static String getString(String baseName, String key) {
        return getString(baseName, EMPTY_LOCALE, key);
    }

    public static String getString(String baseName, Locale locale, String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(baseName, locale, RESOURCE_BUNDLE_CONTROL);
        if(resourceBundle.containsKey(key)) {
            return resourceBundle.getString(key);
        } else {
            return "";
        }
    }
}
