package com.yd.jd.internal.JSON;

abstract class AbstractLogger implements Logger {
    AbstractLogger() {
    }

    public final Logger getLogger(String name) {
        if (isBlank(name)) {
            return this;
        } else {
            String basename = this.getName();
            String fullname = !isBlank(basename) && Log.getRootLogger() != this ? basename + "." + name : name;
            Logger logger = (Logger)Log.getLoggers().get(fullname);
            if (logger == null) {
                Logger newlog = this.newLogger(fullname);
                logger = (Logger)Log.getMutableLoggers().putIfAbsent(fullname, newlog);
                if (logger == null) {
                    logger = newlog;
                }
            }

            return logger;
        }
    }

    protected abstract Logger newLogger(String var1);

    private static boolean isBlank(String name) {
        if (name == null) {
            return true;
        } else {
            int size = name.length();

            for(int i = 0; i < size; ++i) {
                char c = name.charAt(i);
                if (!Character.isWhitespace(c)) {
                    return false;
                }
            }

            return true;
        }
    }
}
