package com.yd.jd.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringUtil {
    public static final String[] EMPTY_STRING_ARRAY = new String[0];
    private static final TimeZone TZ_GMT8 = TimeZone.getTimeZone("GMT+8");
    private static final Pattern PATTERN_CIDR = Pattern.compile("^(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})/(\\d{1,2})$");
    private static final String QUOT = "&quot;";
    private static final String AMP = "&amp;";
    private static final String APOS = "&apos;";
    private static final String GT = "&gt;";
    private static final String LT = "&lt;";

    private StringUtil() {
    }

    public static boolean isEmpty(String value) {
        int strLen;
        if (value != null && (strLen = value.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(value.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static boolean isNumeric(Object obj) {
        if (obj == null) {
            return false;
        } else {
            char[] chars = obj.toString().toCharArray();
            int length = chars.length;
            if (length < 1) {
                return false;
            } else {
                int i = 0;
                if (length > 1 && chars[0] == '-') {
                    i = 1;
                }

                while (i < length) {
                    if (!Character.isDigit(chars[i])) {
                        return false;
                    }

                    ++i;
                }

                return true;
            }
        }
    }

    public static boolean areNotEmpty(String... values) {
        boolean result = true;
        if (values != null && values.length != 0) {
            String[] var2 = values;
            int var3 = values.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                String value = var2[var4];
                result &= !isEmpty(value);
            }
        } else {
            result = false;
        }

        return result;
    }

    public static String unicodeToChinese(String unicode) {
        StringBuilder out = new StringBuilder();
        if (!isEmpty(unicode)) {
            for (int i = 0; i < unicode.length(); ++i) {
                out.append(unicode.charAt(i));
            }
        }

        return out.toString();
    }

    public static String toUnderlineStyle(String name) {
        StringBuilder newName = new StringBuilder();
        int len = name.length();

        for (int i = 0; i < len; ++i) {
            char c = name.charAt(i);
            if (Character.isUpperCase(c)) {
                if (i > 0) {
                    newName.append("_");
                }

                newName.append(Character.toLowerCase(c));
            } else {
                newName.append(c);
            }
        }

        return newName.toString();
    }

    public static String toCamelStyle(String name) {
        StringBuilder newName = new StringBuilder();
        int len = name.length();

        for (int i = 0; i < len; ++i) {
            char c = name.charAt(i);
            if (i == 0) {
                newName.append(Character.toLowerCase(c));
            } else {
                newName.append(c);
            }
        }

        return newName.toString();
    }

    public static Date parseDateTime(String str) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.setTimeZone(TZ_GMT8);

        try {
            return format.parse(str);
        } catch (ParseException var3) {
            throw new RuntimeException(var3);
        }
    }

    public static String formatDateTime(Date date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format.setTimeZone(TZ_GMT8);
        return format.format(date);
    }

    public static String formatDateTime(Date date, String pattern) {
        DateFormat format = new SimpleDateFormat(pattern);
        format.setTimeZone(TZ_GMT8);
        return format.format(date);
    }

    public static String escapeXml(String value) {
        StringBuilder writer = new StringBuilder();
        char[] chars = value.trim().toCharArray();

        for (int i = 0; i < chars.length; ++i) {
            char c = chars[i];
            switch (c) {
                case '"':
                    writer.append("&quot;");
                    continue;
                case '&':
                    writer.append("&amp;");
                    continue;
                case '\'':
                    writer.append("&apos;");
                    continue;
                case '<':
                    writer.append("&lt;");
                    continue;
                case '>':
                    writer.append("&gt;");
                    continue;
            }

            if (c == '\t' || c == '\n' || c == '\r' || c >= ' ' && c <= '\ud7ff' || c >= '\ue000' && c <= '�' || c >= 65536 && c <= 1114111) {
                writer.append(c);
            }
        }

        return writer.toString();
    }

    public static Set<String> getClassProperties(Class<?> clazz, boolean isGet) {
        HashSet propNames = new HashSet();

        try {
            BeanInfo info = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] props = info.getPropertyDescriptors();
            PropertyDescriptor[] var5 = props;
            int var6 = props.length;

            for (int var7 = 0; var7 < var6; ++var7) {
                PropertyDescriptor prop = var5[var7];
                String name = prop.getName();
                Method method;
                if (isGet) {
                    method = prop.getReadMethod();
                } else {
                    method = prop.getWriteMethod();
                }

                if (!"class".equals(name) && method != null) {
                    propNames.add(name);
                }
            }

            return propNames;
        } catch (Exception var11) {
            throw new RuntimeException(var11);
        }
    }

    public static boolean isDigits(String str) {
        if (isEmpty(str)) {
            return false;
        } else {
            for (int i = 0; i < str.length(); ++i) {
                if (!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        }
    }

    public static String[] split(String str, char separatorChar) {
        return splitWorker(str, separatorChar, false);
    }

    private static String[] splitWorker(String str, char separatorChar, boolean preserveAllTokens) {
        if (str == null) {
            return null;
        } else {
            int len = str.length();
            if (len == 0) {
                return EMPTY_STRING_ARRAY;
            } else {
                List<String> list = new ArrayList();
                int i = 0;
                int start = 0;
                boolean match = false;
                boolean lastMatch = false;

                while (true) {
                    while (i < len) {
                        if (str.charAt(i) == separatorChar) {
                            if (match || preserveAllTokens) {
                                list.add(str.substring(start, i));
                                match = false;
                                lastMatch = true;
                            }

                            ++i;
                            start = i;
                        } else {
                            lastMatch = false;
                            match = true;
                            ++i;
                        }
                    }

                    if (match || preserveAllTokens && lastMatch) {
                        list.add(str.substring(start, i));
                    }

                    return (String[]) ((String[]) list.toArray(new String[list.size()]));
                }
            }
        }
    }

    private static String[] splitWorker(String str, String separatorChars, int max, boolean preserveAllTokens) {
        if (str == null) {
            return null;
        } else {
            int len = str.length();
            if (len == 0) {
                return EMPTY_STRING_ARRAY;
            } else {
                List<String> list = new ArrayList();
                int sizePlus1 = 1;
                int i = 0;
                int start = 0;
                boolean match = false;
                boolean lastMatch = false;
                if (separatorChars != null) {
                    if (separatorChars.length() != 1) {
                        label87:
                        while (true) {
                            while (true) {
                                if (i >= len) {
                                    break label87;
                                }

                                if (separatorChars.indexOf(str.charAt(i)) >= 0) {
                                    if (match || preserveAllTokens) {
                                        lastMatch = true;
                                        if (sizePlus1++ == max) {
                                            i = len;
                                            lastMatch = false;
                                        }

                                        list.add(str.substring(start, i));
                                        match = false;
                                    }

                                    ++i;
                                    start = i;
                                } else {
                                    lastMatch = false;
                                    match = true;
                                    ++i;
                                }
                            }
                        }
                    } else {
                        char sep = separatorChars.charAt(0);

                        label71:
                        while (true) {
                            while (true) {
                                if (i >= len) {
                                    break label71;
                                }

                                if (str.charAt(i) == sep) {
                                    if (match || preserveAllTokens) {
                                        lastMatch = true;
                                        if (sizePlus1++ == max) {
                                            i = len;
                                            lastMatch = false;
                                        }

                                        list.add(str.substring(start, i));
                                        match = false;
                                    }

                                    ++i;
                                    start = i;
                                } else {
                                    lastMatch = false;
                                    match = true;
                                    ++i;
                                }
                            }
                        }
                    }
                } else {
                    label103:
                    while (true) {
                        while (true) {
                            if (i >= len) {
                                break label103;
                            }

                            if (Character.isWhitespace(str.charAt(i))) {
                                if (match || preserveAllTokens) {
                                    lastMatch = true;
                                    if (sizePlus1++ == max) {
                                        i = len;
                                        lastMatch = false;
                                    }

                                    list.add(str.substring(start, i));
                                    match = false;
                                }

                                ++i;
                                start = i;
                            } else {
                                lastMatch = false;
                                match = true;
                                ++i;
                            }
                        }
                    }
                }

                if (match || preserveAllTokens && lastMatch) {
                    list.add(str.substring(start, i));
                }

                return (String[]) ((String[]) list.toArray(new String[list.size()]));
            }
        }
    }

    public static String[] split(String str, String separatorChars) {
        return splitWorker(str, separatorChars, -1, false);
    }

    public static boolean isIpInRange(String ipAddr, String cidrAddr) {
        Matcher matcher = PATTERN_CIDR.matcher(cidrAddr);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid CIDR address: " + cidrAddr);
        } else {
            int[] minIpParts = new int[4];
            int[] maxIpParts = new int[4];
            String[] ipParts = matcher.group(1).split("\\.");
            int intMask = Integer.parseInt(matcher.group(2));
            int i;
            for (i = 0; i < ipParts.length; ++i) {
                i = Integer.parseInt(ipParts[i]);
                if (intMask >= 8) {
                    minIpParts[i] = i;
                    maxIpParts[i] = i;
                    intMask -= 8;
                } else if (intMask > 0) {
                    minIpParts[i] = i >> intMask;
                    maxIpParts[i] = i | 255 >> intMask;
                    intMask = 0;
                } else {
                    minIpParts[i] = 1;
                    maxIpParts[i] = 254;
                }
            }

            String[] realIpParts = ipAddr.split("\\.");

            for (i = 0; i < realIpParts.length; ++i) {
                int realIp = Integer.parseInt(realIpParts[i]);
                if (realIp < minIpParts[i] || realIp > maxIpParts[i]) {
                    return false;
                }
            }

            return true;
        }
    }

    public static String join(Iterable<?> objs, String sep) {
        StringBuilder buf = new StringBuilder();
        join(buf, objs, sep);
        return buf.toString();
    }

    public static void join(StringBuilder buf, Iterable<?> objs, String sep) {
        try {
            join((Appendable) buf, objs, sep);
        } catch (IOException var4) {
            throw new RuntimeException(var4);
        }
    }

    public static void join(Appendable buf, Iterable<?> objs, String sep) throws IOException {
        if (objs != null) {
            if (sep == null) {
                sep = "";
            }

            Iterator i = objs.iterator();

            while (i.hasNext()) {
                buf.append(String.valueOf(i.next()));
                if (i.hasNext()) {
                    buf.append(sep);
                }
            }

        }
    }
}
