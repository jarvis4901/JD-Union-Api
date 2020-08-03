


package com.yd.jd.internal.JSON;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class JSON {
    static final Logger LOG = Log.getLogger(JSON.class);
    public static final JSON DEFAULT = new JSON();
    private Map<String, JSON.Convertor> _convertors = new ConcurrentHashMap();
    private int _stringBufferSize = 1024;

    public JSON() {
    }

    public int getStringBufferSize() {
        return this._stringBufferSize;
    }

    public void setStringBufferSize(int stringBufferSize) {
        this._stringBufferSize = stringBufferSize;
    }

    public static void registerConvertor(Class forClass, JSON.Convertor convertor) {
        DEFAULT.addConvertor(forClass, convertor);
    }

    public static JSON getDefault() {
        return DEFAULT;
    }

    public static String toString(Object object) {
        StringBuilder buffer = new StringBuilder(DEFAULT.getStringBufferSize());
        DEFAULT.append(buffer, object);
        return buffer.toString();
    }

    public static String toString(Map object) {
        StringBuilder buffer = new StringBuilder(DEFAULT.getStringBufferSize());
        DEFAULT.appendMap(buffer, object);
        return buffer.toString();
    }

    public static String toString(Object[] array) {
        StringBuilder buffer = new StringBuilder(DEFAULT.getStringBufferSize());
        DEFAULT.appendArray(buffer, (Object)array);
        return buffer.toString();
    }

    public static Object parse(String s) {
        return DEFAULT.parse((JSON.Source)(new JSON.StringSource(s)), false);
    }

    public static Object parse(String s, boolean stripOuterComment) {
        return DEFAULT.parse((JSON.Source)(new JSON.StringSource(s)), stripOuterComment);
    }

    public static Object parse(Reader in) throws IOException {
        return DEFAULT.parse((JSON.Source)(new JSON.ReaderSource(in)), false);
    }

    public static Object parse(Reader in, boolean stripOuterComment) throws IOException {
        return DEFAULT.parse((JSON.Source)(new JSON.ReaderSource(in)), stripOuterComment);
    }

    public String toJSON(Object object) {
        StringBuilder buffer = new StringBuilder(this.getStringBufferSize());
        this.append(buffer, object);
        return buffer.toString();
    }

    public Object fromJSON(String json) {
        JSON.Source source = new JSON.StringSource(json);
        return this.parse((JSON.Source)source);
    }

    public void append(Appendable buffer, Object object) {
        try {
            if (object == null) {
                buffer.append("null");
            } else if (object instanceof JSON.Convertible) {
                this.appendJSON(buffer, (JSON.Convertible)object);
            } else if (object instanceof JSON.Generator) {
                this.appendJSON(buffer, (JSON.Generator)object);
            } else if (object instanceof Map) {
                this.appendMap(buffer, (Map)object);
            } else if (object instanceof Collection) {
                this.appendArray(buffer, (Collection)object);
            } else if (object.getClass().isArray()) {
                this.appendArray(buffer, object);
            } else if (object instanceof Number) {
                this.appendNumber(buffer, (Number)object);
            } else if (object instanceof Boolean) {
                this.appendBoolean(buffer, (Boolean)object);
            } else if (object instanceof Character) {
                this.appendString(buffer, object.toString());
            } else if (object instanceof String) {
                this.appendString(buffer, (String)object);
            } else {
                JSON.Convertor convertor = this.getConvertor(object.getClass());
                if (convertor != null) {
                    this.appendJSON(buffer, convertor, object);
                } else {
                    this.appendString(buffer, object.toString());
                }
            }

        } catch (IOException var4) {
            throw new RuntimeException(var4);
        }
    }

    public void appendNull(Appendable buffer) {
        try {
            buffer.append("null");
        } catch (IOException var3) {
            throw new RuntimeException(var3);
        }
    }

    public void appendJSON(Appendable buffer, final JSON.Convertor convertor, final Object object) {
        this.appendJSON(buffer, new JSON.Convertible() {
            public void fromJSON(Map objectx) {
            }

            public void toJSON(JSON.Output out) {
                convertor.toJSON(object, out);
            }
        });
    }

    public void appendJSON(Appendable buffer, JSON.Convertible converter) {
        JSON.ConvertableOutput out = new JSON.ConvertableOutput(buffer);
        converter.toJSON(out);
        out.complete();
    }

    public void appendJSON(Appendable buffer, JSON.Generator generator) {
        generator.addJSON(buffer);
    }

    public void appendMap(Appendable buffer, Map<?, ?> map) {
        try {
            if (map == null) {
                this.appendNull(buffer);
            } else {
                buffer.append('{');
                Iterator iter = map.entrySet().iterator();

                while(iter.hasNext()) {
                    Entry<?, ?> entry = (Entry)iter.next();
                    QuotedStringTokenizer.quote(buffer, entry.getKey().toString());
                    buffer.append(':');
                    this.append(buffer, entry.getValue());
                    if (iter.hasNext()) {
                        buffer.append(',');
                    }
                }

                buffer.append('}');
            }
        } catch (IOException var5) {
            throw new RuntimeException(var5);
        }
    }

    public void appendArray(Appendable buffer, Collection collection) {
        try {
            if (collection == null) {
                this.appendNull(buffer);
            } else {
                buffer.append('[');
                Iterator iter = collection.iterator();
                boolean first = true;

                while(iter.hasNext()) {
                    if (!first) {
                        buffer.append(',');
                    }

                    first = false;
                    this.append(buffer, iter.next());
                }

                buffer.append(']');
            }
        } catch (IOException var5) {
            throw new RuntimeException(var5);
        }
    }

    public void appendArray(Appendable buffer, Object array) {
        try {
            if (array == null) {
                this.appendNull(buffer);
            } else {
                buffer.append('[');
                int length = Array.getLength(array);

                for(int i = 0; i < length; ++i) {
                    if (i != 0) {
                        buffer.append(',');
                    }

                    this.append(buffer, Array.get(array, i));
                }

                buffer.append(']');
            }
        } catch (IOException var5) {
            throw new RuntimeException(var5);
        }
    }

    public void appendBoolean(Appendable buffer, Boolean b) {
        try {
            if (b == null) {
                this.appendNull(buffer);
            } else {
                buffer.append(b ? "true" : "false");
            }
        } catch (IOException var4) {
            throw new RuntimeException(var4);
        }
    }

    public void appendNumber(Appendable buffer, Number number) {
        try {
            if (number == null) {
                this.appendNull(buffer);
            } else {
                buffer.append(String.valueOf(number));
            }
        } catch (IOException var4) {
            throw new RuntimeException(var4);
        }
    }

    public void appendString(Appendable buffer, String string) {
        if (string == null) {
            this.appendNull(buffer);
        } else {
            QuotedStringTokenizer.quote(buffer, string);
        }
    }

    protected String toString(char[] buffer, int offset, int length) {
        return new String(buffer, offset, length);
    }

    protected Map<String, Object> newMap() {
        return new HashMap();
    }

    protected Object[] newArray(int size) {
        return new Object[size];
    }

    protected JSON contextForArray() {
        return this;
    }

    protected JSON contextFor(String field) {
        return this;
    }

    protected Object convertTo(Class type, Map map) {
        if (type != null && JSON.Convertible.class.isAssignableFrom(type)) {
            try {
                JSON.Convertible conv = (JSON.Convertible)type.newInstance();
                conv.fromJSON(map);
                return conv;
            } catch (Exception var4) {
                throw new RuntimeException(var4);
            }
        } else {
            JSON.Convertor convertor = this.getConvertor(type);
            return convertor != null ? convertor.fromJSON(map) : map;
        }
    }

    public void addConvertor(Class forClass, JSON.Convertor convertor) {
        this._convertors.put(forClass.getName(), convertor);
    }

    protected JSON.Convertor getConvertor(Class forClass) {
        Class cls = forClass;
        JSON.Convertor convertor = (JSON.Convertor)this._convertors.get(forClass.getName());
        if (convertor == null && this != DEFAULT) {
            convertor = DEFAULT.getConvertor(forClass);
        }

        while(convertor == null && cls != null && cls != Object.class) {
            Class[] ifs = cls.getInterfaces();

            for(int i = 0; convertor == null && ifs != null && i < ifs.length; convertor = (JSON.Convertor)this._convertors.get(ifs[i++].getName())) {
            }

            if (convertor == null) {
                cls = cls.getSuperclass();
                convertor = (JSON.Convertor)this._convertors.get(cls.getName());
            }
        }

        return convertor;
    }

    public void addConvertorFor(String name, JSON.Convertor convertor) {
        this._convertors.put(name, convertor);
    }

    public JSON.Convertor getConvertorFor(String name) {
        JSON.Convertor convertor = (JSON.Convertor)this._convertors.get(name);
        if (convertor == null && this != DEFAULT) {
            convertor = DEFAULT.getConvertorFor(name);
        }

        return convertor;
    }

    public Object parse(JSON.Source source, boolean stripOuterComment) {
        int comment_state = 0;
        if (!stripOuterComment) {
            return this.parse(source);
        } else {
            int strip_state = 1;
            Object o = null;

            while(true) {
                while(source.hasNext()) {
                    char c = source.peek();
                    if (comment_state == 1) {
                        switch(c) {
                            case '*':
                                comment_state = 2;
                                if (strip_state == 1) {
                                    comment_state = 0;
                                    strip_state = 2;
                                }
                                break;
                            case '/':
                                comment_state = -1;
                        }
                    } else if (comment_state > 1) {
                        switch(c) {
                            case '*':
                                comment_state = 3;
                                break;
                            case '/':
                                if (comment_state == 3) {
                                    comment_state = 0;
                                    if (strip_state == 2) {
                                        return o;
                                    }
                                } else {
                                    comment_state = 2;
                                }
                                break;
                            default:
                                comment_state = 2;
                        }
                    } else if (comment_state < 0) {
                        switch(c) {
                            case '\n':
                            case '\r':
                                comment_state = 0;
                        }
                    } else if (!Character.isWhitespace(c)) {
                        if (c == '/') {
                            comment_state = 1;
                        } else if (c == '*') {
                            comment_state = 3;
                        } else if (o == null) {
                            o = this.parse(source);
                            continue;
                        }
                    }

                    source.next();
                }

                return o;
            }
        }
    }

    public Object parse(JSON.Source source) {
        for(byte comment_state = 0; source.hasNext(); source.next()) {
            char c = source.peek();
            if (comment_state == 1) {
                switch(c) {
                    case '*':
                        comment_state = 2;
                        break;
                    case '/':
                        comment_state = -1;
                }
            } else if (comment_state > 1) {
                switch(c) {
                    case '*':
                        comment_state = 3;
                        break;
                    case '/':
                        if (comment_state == 3) {
                            comment_state = 0;
                        } else {
                            comment_state = 2;
                        }
                        break;
                    default:
                        comment_state = 2;
                }
            } else if (comment_state < 0) {
                switch(c) {
                    case '\n':
                    case '\r':
                        comment_state = 0;
                }
            } else {
                switch(c) {
                    case '"':
                        return this.parseString(source);
                    case '-':
                        return this.parseNumber(source);
                    case '/':
                        comment_state = 1;
                        break;
                    case 'N':
                        complete("NaN", source);
                        return null;
                    case '[':
                        return this.parseArray(source);
                    case 'f':
                        complete("false", source);
                        return Boolean.FALSE;
                    case 'n':
                        complete("null", source);
                        return null;
                    case 't':
                        complete("true", source);
                        return Boolean.TRUE;
                    case 'u':
                        complete("undefined", source);
                        return null;
                    case '{':
                        return this.parseObject(source);
                    default:
                        if (Character.isDigit(c)) {
                            return this.parseNumber(source);
                        }

                        if (!Character.isWhitespace(c)) {
                            return this.handleUnknown(source, c);
                        }
                }
            }
        }

        return null;
    }

    protected Object handleUnknown(JSON.Source source, char c) {
        throw new IllegalStateException("unknown char '" + c + "'(" + c + ") in " + source);
    }

    protected Object parseObject(JSON.Source source) {
        if (source.next() != '{') {
            throw new IllegalStateException();
        } else {
            Map<String, Object> map = this.newMap();

            String classname;
            for(char next = this.seekTo("\"}", source); source.hasNext(); next = this.seekTo("\"}", source)) {
                if (next == '}') {
                    source.next();
                    break;
                }

                classname = this.parseString(source);
                this.seekTo(':', source);
                source.next();
                Object value = this.contextFor(classname).parse(source);
                map.put(classname, value);
                this.seekTo(",}", source);
                next = source.next();
                if (next == '}') {
                    break;
                }
            }

            classname = (String)map.get("class");
            if (classname != null) {
                try {
                    Class c = Loader.loadClass(JSON.class, classname);
                    return this.convertTo(c, map);
                } catch (ClassNotFoundException var6) {
                    LOG.warn(var6);
                }
            }

            return map;
        }
    }

    protected Object parseArray(JSON.Source source) {
        if (source.next() != '[') {
            throw new IllegalStateException();
        } else {
            int size = 0;
            ArrayList list = null;
            Object item = null;
            boolean coma = true;

            while(source.hasNext()) {
                char c = source.peek();
                switch(c) {
                    case ',':
                        if (coma) {
                            throw new IllegalStateException();
                        }

                        coma = true;
                        source.next();
                        break;
                    case ']':
                        source.next();
                        switch(size) {
                            case 0:
                                return this.newArray(0);
                            case 1:
                                Object array = this.newArray(1);
                                Array.set(array, 0, item);
                                return array;
                            default:
                                return list.toArray(this.newArray(list.size()));
                        }
                    default:
                        if (Character.isWhitespace(c)) {
                            source.next();
                        } else {
                            coma = false;
                            if (size++ == 0) {
                                item = this.contextForArray().parse(source);
                            } else if (list == null) {
                                list = new ArrayList();
                                list.add(item);
                                item = this.contextForArray().parse(source);
                                list.add(item);
                                item = null;
                            } else {
                                item = this.contextForArray().parse(source);
                                list.add(item);
                                item = null;
                            }
                        }
                }
            }

            throw new IllegalStateException("unexpected end of array");
        }
    }

    protected String parseString(JSON.Source source) {
        if (source.next() != '"') {
            throw new IllegalStateException();
        } else {
            boolean escape = false;
            StringBuilder b = null;
            char[] scratch = source.scratchBuffer();
            char c;
            char uc;
            if (scratch != null) {
                int i = 0;

                while(source.hasNext()) {
                    if (i >= scratch.length) {
                        b = new StringBuilder(scratch.length * 2);
                        b.append(scratch, 0, i);
                        break;
                    }

                    c = source.next();
                    if (escape) {
                        escape = false;
                        switch(c) {
                            case '"':
                                scratch[i++] = '"';
                                break;
                            case '/':
                                scratch[i++] = '/';
                                break;
                            case '\\':
                                scratch[i++] = '\\';
                                break;
                            case 'b':
                                scratch[i++] = '\b';
                                break;
                            case 'f':
                                scratch[i++] = '\f';
                                break;
                            case 'n':
                                scratch[i++] = '\n';
                                break;
                            case 'r':
                                scratch[i++] = '\r';
                                break;
                            case 't':
                                scratch[i++] = '\t';
                                break;
                            case 'u':
                                uc = (char)((TypeUtil.convertHexDigit((byte)source.next()) << 12) + (TypeUtil.convertHexDigit((byte)source.next()) << 8) + (TypeUtil.convertHexDigit((byte)source.next()) << 4) + TypeUtil.convertHexDigit((byte)source.next()));
                                scratch[i++] = uc;
                                break;
                            default:
                                scratch[i++] = c;
                        }
                    } else if (c == '\\') {
                        escape = true;
                    } else {
                        if (c == '"') {
                            return this.toString(scratch, 0, i);
                        }

                        scratch[i++] = c;
                    }
                }

                if (b == null) {
                    return this.toString(scratch, 0, i);
                }
            } else {
                b = new StringBuilder(this.getStringBufferSize());
            }

            StringBuilder builder = b;

            while(source.hasNext()) {
                c = source.next();
                if (escape) {
                    escape = false;
                    switch(c) {
                        case '"':
                            builder.append('"');
                            break;
                        case '/':
                            builder.append('/');
                            break;
                        case '\\':
                            builder.append('\\');
                            break;
                        case 'b':
                            builder.append('\b');
                            break;
                        case 'f':
                            builder.append('\f');
                            break;
                        case 'n':
                            builder.append('\n');
                            break;
                        case 'r':
                            builder.append('\r');
                            break;
                        case 't':
                            builder.append('\t');
                            break;
                        case 'u':
                            uc = (char)((TypeUtil.convertHexDigit((byte)source.next()) << 12) + (TypeUtil.convertHexDigit((byte)source.next()) << 8) + (TypeUtil.convertHexDigit((byte)source.next()) << 4) + TypeUtil.convertHexDigit((byte)source.next()));
                            builder.append(uc);
                            break;
                        default:
                            builder.append(c);
                    }
                } else if (c == '\\') {
                    escape = true;
                } else {
                    if (c == '"') {
                        break;
                    }

                    builder.append(c);
                }
            }

            return builder.toString();
        }
    }

    public Number parseNumber(JSON.Source source) {
        boolean minus = false;
        long number = 0L;
        StringBuilder buffer = null;

        char c;
        label49:
        while(source.hasNext()) {
            c = source.peek();
            switch(c) {
                case '+':
                case '-':
                    if (number != 0L) {
                        throw new IllegalStateException("bad number");
                    }

                    minus = true;
                    source.next();
                    break;
                case ',':
                case '/':
                case ':':
                case ';':
                case '<':
                case '=':
                case '>':
                case '?':
                case '@':
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case 'F':
                case 'G':
                case 'H':
                case 'I':
                case 'J':
                case 'K':
                case 'L':
                case 'M':
                case 'N':
                case 'O':
                case 'P':
                case 'Q':
                case 'R':
                case 'S':
                case 'T':
                case 'U':
                case 'V':
                case 'W':
                case 'X':
                case 'Y':
                case 'Z':
                case '[':
                case '\\':
                case ']':
                case '^':
                case '_':
                case '`':
                case 'a':
                case 'b':
                case 'c':
                case 'd':
                default:
                    break label49;
                case '.':
                case 'E':
                case 'e':
                    buffer = new StringBuilder(16);
                    if (minus) {
                        buffer.append('-');
                    }

                    buffer.append(number);
                    buffer.append(c);
                    source.next();
                    break label49;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    number = number * 10L + (long)(c - 48);
                    source.next();
            }
        }

        if (buffer == null) {
            return minus ? -1L * number : number;
        } else {
            while(source.hasNext()) {
                c = source.peek();
                switch(c) {
                    case '+':
                    case '-':
                    case '.':
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                    case 'E':
                    case 'e':
                        buffer.append(c);
                        source.next();
                        break;
                    case ',':
                    case '/':
                    case ':':
                    case ';':
                    case '<':
                    case '=':
                    case '>':
                    case '?':
                    case '@':
                    case 'A':
                    case 'B':
                    case 'C':
                    case 'D':
                    case 'F':
                    case 'G':
                    case 'H':
                    case 'I':
                    case 'J':
                    case 'K':
                    case 'L':
                    case 'M':
                    case 'N':
                    case 'O':
                    case 'P':
                    case 'Q':
                    case 'R':
                    case 'S':
                    case 'T':
                    case 'U':
                    case 'V':
                    case 'W':
                    case 'X':
                    case 'Y':
                    case 'Z':
                    case '[':
                    case '\\':
                    case ']':
                    case '^':
                    case '_':
                    case '`':
                    case 'a':
                    case 'b':
                    case 'c':
                    case 'd':
                    default:
                        return new Double(buffer.toString());
                }
            }

            return new Double(buffer.toString());
        }
    }

    protected void seekTo(char seek, JSON.Source source) {
        while(source.hasNext()) {
            char c = source.peek();
            if (c == seek) {
                return;
            }

            if (!Character.isWhitespace(c)) {
                throw new IllegalStateException("Unexpected '" + c + " while seeking '" + seek + "'");
            }

            source.next();
        }

        throw new IllegalStateException("Expected '" + seek + "'");
    }

    protected char seekTo(String seek, JSON.Source source) {
        while(source.hasNext()) {
            char c = source.peek();
            if (seek.indexOf(c) >= 0) {
                return c;
            }

            if (!Character.isWhitespace(c)) {
                throw new IllegalStateException("Unexpected '" + c + "' while seeking one of '" + seek + "'");
            }

            source.next();
        }

        throw new IllegalStateException("Expected one of '" + seek + "'");
    }

    protected static void complete(String seek, JSON.Source source) {
        int i = 0;

        while(source.hasNext() && i < seek.length()) {
            char c = source.next();
            if (c != seek.charAt(i++)) {
                throw new IllegalStateException("Unexpected '" + c + " while seeking  \"" + seek + "\"");
            }
        }

        if (i < seek.length()) {
            throw new IllegalStateException("Expected \"" + seek + "\"");
        }
    }

    public static class Literal implements JSON.Generator {
        private String _json;

        public Literal(String json) {
            if (JSON.LOG.isDebugEnabled()) {
                JSON.parse(json);
            }

            this._json = json;
        }

        public String toString() {
            return this._json;
        }

        public void addJSON(Appendable buffer) {
            try {
                buffer.append(this._json);
            } catch (IOException var3) {
                throw new RuntimeException(var3);
            }
        }
    }

    public interface Generator {
        void addJSON(Appendable var1);
    }

    public interface Convertor {
        void toJSON(Object var1, JSON.Output var2);

        Object fromJSON(Map var1);
    }

    public interface Convertible {
        void toJSON(JSON.Output var1);

        void fromJSON(Map var1);
    }

    public interface Output {
        void addClass(Class var1);

        void add(Object var1);

        void add(String var1, Object var2);

        void add(String var1, double var2);

        void add(String var1, long var2);

        void add(String var1, boolean var2);
    }

    public static class ReaderSource implements JSON.Source {
        private Reader _reader;
        private int _next = -1;
        private char[] scratch;

        public ReaderSource(Reader r) {
            this._reader = r;
        }

        public void setReader(Reader reader) {
            this._reader = reader;
            this._next = -1;
        }

        public boolean hasNext() {
            this.getNext();
            if (this._next < 0) {
                this.scratch = null;
                return false;
            } else {
                return true;
            }
        }

        public char next() {
            this.getNext();
            char c = (char)this._next;
            this._next = -1;
            return c;
        }

        public char peek() {
            this.getNext();
            return (char)this._next;
        }

        private void getNext() {
            if (this._next < 0) {
                try {
                    this._next = this._reader.read();
                } catch (IOException var2) {
                    throw new RuntimeException(var2);
                }
            }

        }

        public char[] scratchBuffer() {
            if (this.scratch == null) {
                this.scratch = new char[1024];
            }

            return this.scratch;
        }
    }

    public static class StringSource implements JSON.Source {
        private final String string;
        private int index;
        private char[] scratch;

        public StringSource(String s) {
            this.string = s;
        }

        public boolean hasNext() {
            if (this.index < this.string.length()) {
                return true;
            } else {
                this.scratch = null;
                return false;
            }
        }

        public char next() {
            return this.string.charAt(this.index++);
        }

        public char peek() {
            return this.string.charAt(this.index);
        }

        public String toString() {
            return this.string.substring(0, this.index) + "|||" + this.string.substring(this.index);
        }

        public char[] scratchBuffer() {
            if (this.scratch == null) {
                this.scratch = new char[this.string.length()];
            }

            return this.scratch;
        }
    }

    public interface Source {
        boolean hasNext();

        char next();

        char peek();

        char[] scratchBuffer();
    }

    private final class ConvertableOutput implements JSON.Output {
        private final Appendable _buffer;
        char c;

        private ConvertableOutput(Appendable buffer) {
            this.c = '{';
            this._buffer = buffer;
        }

        public void complete() {
            try {
                if (this.c == '{') {
                    this._buffer.append("{}");
                } else if (this.c != 0) {
                    this._buffer.append("}");
                }

            } catch (IOException var2) {
                throw new RuntimeException(var2);
            }
        }

        public void add(Object obj) {
            if (this.c == 0) {
                throw new IllegalStateException();
            } else {
                JSON.this.append(this._buffer, obj);
                this.c = 0;
            }
        }

        public void addClass(Class type) {
            try {
                if (this.c == 0) {
                    throw new IllegalStateException();
                } else {
                    this._buffer.append(this.c);
                    this._buffer.append("\"class\":");
                    JSON.this.append(this._buffer, type.getName());
                    this.c = ',';
                }
            } catch (IOException var3) {
                throw new RuntimeException(var3);
            }
        }

        public void add(String name, Object value) {
            try {
                if (this.c == 0) {
                    throw new IllegalStateException();
                } else {
                    this._buffer.append(this.c);
                    QuotedStringTokenizer.quote(this._buffer, name);
                    this._buffer.append(':');
                    JSON.this.append(this._buffer, value);
                    this.c = ',';
                }
            } catch (IOException var4) {
                throw new RuntimeException(var4);
            }
        }

        public void add(String name, double value) {
            try {
                if (this.c == 0) {
                    throw new IllegalStateException();
                } else {
                    this._buffer.append(this.c);
                    QuotedStringTokenizer.quote(this._buffer, name);
                    this._buffer.append(':');
                    JSON.this.appendNumber(this._buffer, new Double(value));
                    this.c = ',';
                }
            } catch (IOException var5) {
                throw new RuntimeException(var5);
            }
        }

        public void add(String name, long value) {
            try {
                if (this.c == 0) {
                    throw new IllegalStateException();
                } else {
                    this._buffer.append(this.c);
                    QuotedStringTokenizer.quote(this._buffer, name);
                    this._buffer.append(':');
                    JSON.this.appendNumber(this._buffer, value);
                    this.c = ',';
                }
            } catch (IOException var5) {
                throw new RuntimeException(var5);
            }
        }

        public void add(String name, boolean value) {
            try {
                if (this.c == 0) {
                    throw new IllegalStateException();
                } else {
                    this._buffer.append(this.c);
                    QuotedStringTokenizer.quote(this._buffer, name);
                    this._buffer.append(':');
                    JSON.this.appendBoolean(this._buffer, value ? Boolean.TRUE : Boolean.FALSE);
                    this.c = ',';
                }
            } catch (IOException var4) {
                throw new RuntimeException(var4);
            }
        }
    }
}
