package com.yd.jd.internal.JSON;

import java.net.URL;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

class Loader {
    Loader() {
    }

    public static URL getResource(Class<?> loadClass, String name, boolean checkParents) throws ClassNotFoundException {
        URL url = null;

        ClassLoader loader;
        for(loader = Thread.currentThread().getContextClassLoader(); url == null && loader != null; loader = url == null && checkParents ? loader.getParent() : null) {
            url = loader.getResource(name);
        }

        for(loader = loadClass == null ? null : loadClass.getClassLoader(); url == null && loader != null; loader = url == null && checkParents ? loader.getParent() : null) {
            url = loader.getResource(name);
        }

        if (url == null) {
            url = ClassLoader.getSystemResource(name);
        }

        return url;
    }

    public static Class loadClass(Class loadClass, String name) throws ClassNotFoundException {
        return loadClass(loadClass, name, false);
    }

    public static Class loadClass(Class loadClass, String name, boolean checkParents) throws ClassNotFoundException {
        ClassNotFoundException ex = null;
        Class<?> c = null;

        ClassLoader loader;
        for(loader = Thread.currentThread().getContextClassLoader(); c == null && loader != null; loader = c == null && checkParents ? loader.getParent() : null) {
            try {
                c = loader.loadClass(name);
            } catch (ClassNotFoundException var9) {
                if (ex == null) {
                    ex = var9;
                }
            }
        }

        for(loader = loadClass == null ? null : loadClass.getClassLoader(); c == null && loader != null; loader = c == null && checkParents ? loader.getParent() : null) {
            try {
                c = loader.loadClass(name);
            } catch (ClassNotFoundException var8) {
                if (ex == null) {
                    ex = var8;
                }
            }
        }

        if (c == null) {
            try {
                c = Class.forName(name);
            } catch (ClassNotFoundException var7) {
                if (ex == null) {
                    ex = var7;
                }
            }
        }

        if (c != null) {
            return c;
        } else {
            throw ex;
        }
    }

    public static ResourceBundle getResourceBundle(Class<?> loadClass, String name, boolean checkParents, Locale locale) throws MissingResourceException {
        MissingResourceException ex = null;
        ResourceBundle bundle = null;

        ClassLoader loader;
        for(loader = Thread.currentThread().getContextClassLoader(); bundle == null && loader != null; loader = bundle == null && checkParents ? loader.getParent() : null) {
            try {
                bundle = ResourceBundle.getBundle(name, locale, loader);
            } catch (MissingResourceException var10) {
                if (ex == null) {
                    ex = var10;
                }
            }
        }

        for(loader = loadClass == null ? null : loadClass.getClassLoader(); bundle == null && loader != null; loader = bundle == null && checkParents ? loader.getParent() : null) {
            try {
                bundle = ResourceBundle.getBundle(name, locale, loader);
            } catch (MissingResourceException var9) {
                if (ex == null) {
                    ex = var9;
                }
            }
        }

        if (bundle == null) {
            try {
                bundle = ResourceBundle.getBundle(name, locale);
            } catch (MissingResourceException var8) {
                if (ex == null) {
                    ex = var8;
                }
            }
        }

        if (bundle != null) {
            return bundle;
        } else {
            throw ex;
        }
    }
}
