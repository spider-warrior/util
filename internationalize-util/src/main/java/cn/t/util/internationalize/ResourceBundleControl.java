package cn.t.util.internationalize;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.*;

public class ResourceBundleControl extends ResourceBundle.Control {

    private static final List<String> FORMAT_LIST  = Collections.unmodifiableList(Collections.singletonList("java.properties"));

    @Override
    public List<String> getFormats(String baseName) {
        return FORMAT_LIST;
    }

    @Override
    public List<Locale> getCandidateLocales(String baseName, Locale locale) {
        return super.getCandidateLocales(baseName, locale);
    }

    @Override
    public Locale getFallbackLocale(String baseName, Locale locale) {
        return super.getFallbackLocale(baseName, locale);
    }

    @Override
    public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload) throws IOException {
        System.out.println("locale:" + locale);
        if (format.equals("java.properties")) {
            boolean presentLanguage = locale.getLanguage().length() > 0;
            boolean presentCountry = locale.getCountry().length() > 0;
            String baseNameToUse = baseName;
            if(presentLanguage) {
                baseNameToUse += ("_" + locale.getLanguage());
            }
            if(presentCountry) {
                baseNameToUse += ("_" + locale.getCountry());
            }
            String resourceName = toResourceName(baseNameToUse, "properties");
            try (
                InputStream stream = loadPrivilegedStream(reload, loader, resourceName)
            ) {
                if (stream != null) {
                    return new PropertyResourceBundle(stream);
                } else {
                    if(presentCountry && presentLanguage) {
                        return new EmptyPropertyResourceBundle();
                    } else {
                        return null;
                    }
                }
            } catch (PrivilegedActionException e) {
                throw (IOException) e.getException();
            }
        } else {
            return null;
        }
    }

    private InputStream loadPrivilegedStream(boolean reload, ClassLoader classLoader, String resourceName) throws PrivilegedActionException {
        return AccessController.doPrivileged(
            (PrivilegedExceptionAction<InputStream>) () -> {
                InputStream is = null;
                if (reload) {
                    URL url = classLoader.getResource(resourceName);
                    if (url != null) {
                        URLConnection connection = url.openConnection();
                        if (connection != null) {
                            connection.setUseCaches(false);
                            is = connection.getInputStream();
                        }
                    }
                } else {
                    is = classLoader.getResourceAsStream(resourceName);
                }
                return is;
            });
    }

    @Override
    public long getTimeToLive(String baseName, Locale locale) {
        return super.getTimeToLive(baseName, locale);
    }

    @Override
    public boolean needsReload(String baseName, Locale locale, String format, ClassLoader loader, ResourceBundle bundle, long loadTime) {
        return super.needsReload(baseName, locale, format, loader, bundle, loadTime);
    }

    @Override
    public String toBundleName(String baseName, Locale locale) {
        return super.toBundleName(baseName, locale);
    }
}
