package com.yd.jd.internal.JSON;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

class TypeUtil {
    private static final Logger LOG = Log.getLogger(TypeUtil.class);
    public static int CR = 13;
    public static int LF = 10;
    private static final HashMap<String, Class<?>> name2Class = new HashMap();
    private static final HashMap<Class<?>, String> class2Name;
    private static final HashMap<Class<?>, Method> class2Value;

    TypeUtil() {
    }

    public static <T> List<?> asList(T[] a) {
        return a == null ? Collections.emptyList() : Arrays.asList(a);
    }

    public static Class<?> fromName(String name) {
        return (Class) name2Class.get(name);
    }

    public static String toName(Class<?> type) {
        return (String) class2Name.get(type);
    }

    public static Object valueOf(Class<?> type, String value) {
        try {
            if (type.equals(String.class)) {
                return value;
            }

            Method m = (Method) class2Value.get(type);
            if (m != null) {
                return m.invoke((Object) null, value);
            }

            if (!type.equals(Character.TYPE) && !type.equals(Character.class)) {
                Constructor<?> c = type.getConstructor(String.class);
                return c.newInstance(value);
            }

            return new Character(value.charAt(0));
        } catch (NoSuchMethodException var4) {
        } catch (IllegalAccessException var5) {
        } catch (InstantiationException var6) {
        } catch (InvocationTargetException var7) {
            if (var7.getTargetException() instanceof Error) {
                throw (Error) ((Error) var7.getTargetException());
            }
        }

        return null;
    }

    public static Object valueOf(String type, String value) {
        return valueOf(fromName(type), value);
    }

    public static int parseInt(String s, int offset, int length, int base) throws NumberFormatException {
        int value = 0;
        if (length < 0) {
            length = s.length() - offset;
        }

        for (int i = 0; i < length; ++i) {
            char c = s.charAt(offset + i);
            int digit = c - 48;
            if (digit < 0 || digit >= base || digit >= 10) {
                digit = 10 + c - 65;
                if (digit < 10 || digit >= base) {
                    digit = 10 + c - 97;
                }
            }

            if (digit < 0 || digit >= base) {
                throw new NumberFormatException(s.substring(offset, offset + length));
            }

            value = value * base + digit;
        }

        return value;
    }

    public static int parseInt(byte[] b, int offset, int length, int base) throws NumberFormatException {
        int value = 0;
        if (length < 0) {
            length = b.length - offset;
        }

        for (int i = 0; i < length; ++i) {
            char c = (char) (255 & b[offset + i]);
            int digit = c - 48;
            if (digit < 0 || digit >= base || digit >= 10) {
                digit = 10 + c - 65;
                if (digit < 10 || digit >= base) {
                    digit = 10 + c - 97;
                }
            }

            if (digit < 0 || digit >= base) {
                throw new NumberFormatException(new String(b, offset, length));
            }

            value = value * base + digit;
        }

        return value;
    }

    public static byte[] parseBytes(String s, int base) {
        byte[] bytes = new byte[s.length() / 2];

        for (int i = 0; i < s.length(); i += 2) {
            bytes[i / 2] = (byte) parseInt((String) s, i, 2, base);
        }

        return bytes;
    }

    public static String toString(byte[] bytes, int base) {
        StringBuilder buf = new StringBuilder();
        byte[] var3 = bytes;
        int var4 = bytes.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            byte b = var3[var5];
            int bi = 255 & b;
            int c = 48 + bi / base % base;
            if (c > 57) {
                c = 97 + (c - 48 - 10);
            }

            buf.append((char) c);
            c = 48 + bi % base;
            if (c > 57) {
                c = 97 + (c - 48 - 10);
            }

            buf.append((char) c);
        }

        return buf.toString();
    }

    public static byte convertHexDigit(byte b) {
        if (b >= 48 && b <= 57) {
            return (byte) (b - 48);
        } else if (b >= 97 && b <= 102) {
            return (byte) (b - 97 + 10);
        } else if (b >= 65 && b <= 70) {
            return (byte) (b - 65 + 10);
        } else {
            throw new IllegalArgumentException("!hex:" + Integer.toHexString(255 & b));
        }
    }

    public static void toHex(byte b, Appendable buf) {
        try {
            int bi = 255 & b;
            int c = 48 + bi / 16 % 16;
            if (c > 57) {
                c = 65 + (c - 48 - 10);
            }

            buf.append((char) c);
            c = 48 + bi % 16;
            if (c > 57) {
                c = 65 + (c - 48 - 10);
            }

            buf.append((char) c);
        } catch (IOException var4) {
            throw new RuntimeException(var4);
        }
    }

    public static String toHexString(byte b) {
        return toHexString(new byte[]{b}, 0, 1);
    }

    public static String toHexString(byte[] b) {
        return toHexString(b, 0, b.length);
    }

    public static String toHexString(byte[] b, int offset, int length) {
        StringBuilder buf = new StringBuilder();

        for (int i = offset; i < offset + length; ++i) {
            int bi = 255 & b[i];
            int c = 48 + bi / 16 % 16;
            if (c > 57) {
                c = 65 + (c - 48 - 10);
            }

            buf.append((char) c);
            c = 48 + bi % 16;
            if (c > 57) {
                c = 97 + (c - 48 - 10);
            }

            buf.append((char) c);
        }

        return buf.toString();
    }

    public static byte[] fromHexString(String s) {
        if (s.length() % 2 != 0) {
            throw new IllegalArgumentException(s);
        } else {
            byte[] array = new byte[s.length() / 2];

            for (int i = 0; i < array.length; ++i) {
                int b = Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16);
                array[i] = (byte) (255 & b);
            }

            return array;
        }
    }

    public static void dump(Class<?> c) {
        System.err.println("Dump: " + c);
        dump(c.getClassLoader());
    }

    public static void dump(ClassLoader cl) {
        System.err.println("Dump Loaders:");

        while (cl != null) {
            System.err.println("  loader " + cl);
            cl = cl.getParent();
        }

    }

    public static byte[] readLine(InputStream in) throws IOException {
        byte[] buf = new byte[256];
        int i = 0;
        int loops = 0;
        boolean var4 = false;

        byte[] old_buf;
        int ch;
        while (true) {
            ch = in.read();
            if (ch < 0) {
                break;
            }

            ++loops;
            if (loops != 1 || ch != LF) {
                if (ch == CR || ch == LF) {
                    break;
                }

                if (i >= buf.length) {
                    old_buf = buf;
                    buf = new byte[buf.length + 256];
                    System.arraycopy(old_buf, 0, buf, 0, old_buf.length);
                }

                buf[i++] = (byte) ch;
            }
        }

        if (ch == -1 && i == 0) {
            return null;
        } else {
            if (ch == CR && in.available() >= 1 && in.markSupported()) {
                in.mark(1);
                ch = in.read();
                if (ch != LF) {
                    in.reset();
                }
            }

            old_buf = buf;
            buf = new byte[i];
            System.arraycopy(old_buf, 0, buf, 0, i);
            return buf;
        }
    }

    public static URL jarFor(String className) {
        try {
            className = className.replace('.', '/') + ".class";
            URL url = Loader.getResource((Class) null, className, false);
            String s = url.toString();
            if (s.startsWith("jar:file:")) {
                return new URL(s.substring(4, s.indexOf("!/")));
            }
        } catch (Exception var3) {
            LOG.ignore(var3);
        }

        return null;
    }

    public static Object call(Class<?> oClass, String method, Object obj, Object[] arg) throws InvocationTargetException, NoSuchMethodException {
        Method[] methods = oClass.getMethods();

        for (int c = 0; methods != null && c < methods.length; ++c) {
            if (methods[c].getName().equals(method) && methods[c].getParameterTypes().length == arg.length && Modifier.isStatic(methods[c].getModifiers()) == (obj == null) && (obj != null || methods[c].getDeclaringClass() == oClass)) {
                try {
                    return methods[c].invoke(obj, arg);
                } catch (IllegalAccessException var7) {
                    LOG.ignore(var7);
                } catch (IllegalArgumentException var8) {
                    LOG.ignore(var8);
                }
            }
        }

        throw new NoSuchMethodException(method);
    }

    static {
        name2Class.put("boolean", Boolean.TYPE);
        name2Class.put("byte", Byte.TYPE);
        name2Class.put("char", Character.TYPE);
        name2Class.put("double", Double.TYPE);
        name2Class.put("float", Float.TYPE);
        name2Class.put("int", Integer.TYPE);
        name2Class.put("long", Long.TYPE);
        name2Class.put("short", Short.TYPE);
        name2Class.put("void", Void.TYPE);
        name2Class.put("java.lang.Boolean.TYPE", Boolean.TYPE);
        name2Class.put("java.lang.Byte.TYPE", Byte.TYPE);
        name2Class.put("java.lang.Character.TYPE", Character.TYPE);
        name2Class.put("java.lang.Double.TYPE", Double.TYPE);
        name2Class.put("java.lang.Float.TYPE", Float.TYPE);
        name2Class.put("java.lang.Integer.TYPE", Integer.TYPE);
        name2Class.put("java.lang.Long.TYPE", Long.TYPE);
        name2Class.put("java.lang.Short.TYPE", Short.TYPE);
        name2Class.put("java.lang.Void.TYPE", Void.TYPE);
        name2Class.put("java.lang.Boolean", Boolean.class);
        name2Class.put("java.lang.Byte", Byte.class);
        name2Class.put("java.lang.Character", Character.class);
        name2Class.put("java.lang.Double", Double.class);
        name2Class.put("java.lang.Float", Float.class);
        name2Class.put("java.lang.Integer", Integer.class);
        name2Class.put("java.lang.Long", Long.class);
        name2Class.put("java.lang.Short", Short.class);
        name2Class.put("Boolean", Boolean.class);
        name2Class.put("Byte", Byte.class);
        name2Class.put("Character", Character.class);
        name2Class.put("Double", Double.class);
        name2Class.put("Float", Float.class);
        name2Class.put("Integer", Integer.class);
        name2Class.put("Long", Long.class);
        name2Class.put("Short", Short.class);
        name2Class.put((String) null, Void.TYPE);
        name2Class.put("string", String.class);
        name2Class.put("String", String.class);
        name2Class.put("java.lang.String", String.class);
        class2Name = new HashMap();
        class2Name.put(Boolean.TYPE, "boolean");
        class2Name.put(Byte.TYPE, "byte");
        class2Name.put(Character.TYPE, "char");
        class2Name.put(Double.TYPE, "double");
        class2Name.put(Float.TYPE, "float");
        class2Name.put(Integer.TYPE, "int");
        class2Name.put(Long.TYPE, "long");
        class2Name.put(Short.TYPE, "short");
        class2Name.put(Void.TYPE, "void");
        class2Name.put(Boolean.class, "java.lang.Boolean");
        class2Name.put(Byte.class, "java.lang.Byte");
        class2Name.put(Character.class, "java.lang.Character");
        class2Name.put(Double.class, "java.lang.Double");
        class2Name.put(Float.class, "java.lang.Float");
        class2Name.put(Integer.class, "java.lang.Integer");
        class2Name.put(Long.class, "java.lang.Long");
        class2Name.put(Short.class, "java.lang.Short");
        class2Name.put((Class<?>) null, "void");
        class2Name.put(String.class, "java.lang.String");
        class2Value = new HashMap();

        try {
            Class<?>[] s = new Class[]{String.class};
            class2Value.put(Boolean.TYPE, Boolean.class.getMethod("valueOf", s));
            class2Value.put(Byte.TYPE, Byte.class.getMethod("valueOf", s));
            class2Value.put(Double.TYPE, Double.class.getMethod("valueOf", s));
            class2Value.put(Float.TYPE, Float.class.getMethod("valueOf", s));
            class2Value.put(Integer.TYPE, Integer.class.getMethod("valueOf", s));
            class2Value.put(Long.TYPE, Long.class.getMethod("valueOf", s));
            class2Value.put(Short.TYPE, Short.class.getMethod("valueOf", s));
            class2Value.put(Boolean.class, Boolean.class.getMethod("valueOf", s));
            class2Value.put(Byte.class, Byte.class.getMethod("valueOf", s));
            class2Value.put(Double.class, Double.class.getMethod("valueOf", s));
            class2Value.put(Float.class, Float.class.getMethod("valueOf", s));
            class2Value.put(Integer.class, Integer.class.getMethod("valueOf", s));
            class2Value.put(Long.class, Long.class.getMethod("valueOf", s));
            class2Value.put(Short.class, Short.class.getMethod("valueOf", s));
        } catch (Exception var1) {
            throw new Error(var1);
        }
    }
}

