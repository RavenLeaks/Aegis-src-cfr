/*
 * Decompiled with CFR <Could not determine version>.
 */
package org.yaml.snakeyaml.external.biz.base64Coder;

public class Base64Coder {
    private static final String systemLineSeparator;
    private static char[] map1;
    private static byte[] map2;

    public static String encodeString(String s) {
        return new String((char[])Base64Coder.encode((byte[])s.getBytes()));
    }

    public static String encodeLines(byte[] in) {
        return Base64Coder.encodeLines((byte[])in, (int)0, (int)in.length, (int)76, (String)systemLineSeparator);
    }

    public static String encodeLines(byte[] in, int iOff, int iLen, int lineLen, String lineSeparator) {
        int blockLen = lineLen * 3 / 4;
        if (blockLen <= 0) {
            throw new IllegalArgumentException();
        }
        int lines = (iLen + blockLen - 1) / blockLen;
        int bufLen = (iLen + 2) / 3 * 4 + lines * lineSeparator.length();
        StringBuilder buf = new StringBuilder((int)bufLen);
        int ip = 0;
        while (ip < iLen) {
            int l = Math.min((int)(iLen - ip), (int)blockLen);
            buf.append((char[])Base64Coder.encode((byte[])in, (int)(iOff + ip), (int)l));
            buf.append((String)lineSeparator);
            ip += l;
        }
        return buf.toString();
    }

    public static char[] encode(byte[] in) {
        return Base64Coder.encode((byte[])in, (int)0, (int)in.length);
    }

    public static char[] encode(byte[] in, int iLen) {
        return Base64Coder.encode((byte[])in, (int)0, (int)iLen);
    }

    public static char[] encode(byte[] in, int iOff, int iLen) {
        int oDataLen = (iLen * 4 + 2) / 3;
        int oLen = (iLen + 2) / 3 * 4;
        char[] out = new char[oLen];
        int ip = iOff;
        int iEnd = iOff + iLen;
        int op = 0;
        while (ip < iEnd) {
            int i0 = in[ip++] & 255;
            int i1 = ip < iEnd ? in[ip++] & 255 : 0;
            int i2 = ip < iEnd ? in[ip++] & 255 : 0;
            int o0 = i0 >>> 2;
            int o1 = (i0 & 3) << 4 | i1 >>> 4;
            int o2 = (i1 & 15) << 2 | i2 >>> 6;
            int o3 = i2 & 63;
            out[op++] = map1[o0];
            out[op++] = map1[o1];
            out[op] = op < oDataLen ? map1[o2] : 61;
            int n = ++op < oDataLen ? map1[o3] : 61;
            out[op] = n;
            ++op;
        }
        return out;
    }

    public static String decodeString(String s) {
        return new String((byte[])Base64Coder.decode((String)s));
    }

    public static byte[] decodeLines(String s) {
        char[] buf = new char[s.length()];
        int p = 0;
        int ip = 0;
        while (ip < s.length()) {
            char c = s.charAt((int)ip);
            if (c != ' ' && c != '\r' && c != '\n' && c != '\t') {
                buf[p++] = c;
            }
            ++ip;
        }
        return Base64Coder.decode((char[])buf, (int)0, (int)p);
    }

    public static byte[] decode(String s) {
        return Base64Coder.decode((char[])s.toCharArray());
    }

    public static byte[] decode(char[] in) {
        return Base64Coder.decode((char[])in, (int)0, (int)in.length);
    }

    public static byte[] decode(char[] in, int iOff, int iLen) {
        if (iLen % 4 != 0) {
            throw new IllegalArgumentException((String)"Length of Base64 encoded input string is not a multiple of 4.");
        }
        while (iLen > 0 && in[iOff + iLen - 1] == '=') {
            --iLen;
        }
        int oLen = iLen * 3 / 4;
        byte[] out = new byte[oLen];
        int ip = iOff;
        int iEnd = iOff + iLen;
        int op = 0;
        while (ip < iEnd) {
            char i0 = in[ip++];
            char i1 = in[ip++];
            int i2 = ip < iEnd ? in[ip++] : 65;
            int i3 = ip < iEnd ? in[ip++] : 65;
            if (i0 > '') throw new IllegalArgumentException((String)"Illegal character in Base64 encoded data.");
            if (i1 > '') throw new IllegalArgumentException((String)"Illegal character in Base64 encoded data.");
            if (i2 > 127) throw new IllegalArgumentException((String)"Illegal character in Base64 encoded data.");
            if (i3 > 127) {
                throw new IllegalArgumentException((String)"Illegal character in Base64 encoded data.");
            }
            byte b0 = map2[i0];
            byte b1 = map2[i1];
            byte b2 = map2[i2];
            byte b3 = map2[i3];
            if (b0 < 0) throw new IllegalArgumentException((String)"Illegal character in Base64 encoded data.");
            if (b1 < 0) throw new IllegalArgumentException((String)"Illegal character in Base64 encoded data.");
            if (b2 < 0) throw new IllegalArgumentException((String)"Illegal character in Base64 encoded data.");
            if (b3 < 0) {
                throw new IllegalArgumentException((String)"Illegal character in Base64 encoded data.");
            }
            int o0 = b0 << 2 | b1 >>> 4;
            int o1 = (b1 & 15) << 4 | b2 >>> 2;
            int o2 = (b2 & 3) << 6 | b3;
            out[op++] = (byte)o0;
            if (op < oLen) {
                out[op++] = (byte)o1;
            }
            if (op >= oLen) continue;
            out[op++] = (byte)o2;
        }
        return out;
    }

    private Base64Coder() {
    }

    static {
        int c;
        systemLineSeparator = System.getProperty((String)"line.separator");
        map1 = new char[64];
        int i = 0;
        for (c = 65; c <= 90; c = (int)((char)(c + 1))) {
            Base64Coder.map1[i++] = c;
        }
        for (c = 97; c <= 122; c = (int)((char)(c + 1))) {
            Base64Coder.map1[i++] = c;
        }
        for (c = 48; c <= 57; c = (int)((char)(c + 1))) {
            Base64Coder.map1[i++] = c;
        }
        Base64Coder.map1[i++] = 43;
        Base64Coder.map1[i++] = 47;
        map2 = new byte[128];
        for (i = 0; i < map2.length; ++i) {
            Base64Coder.map2[i] = -1;
        }
        i = 0;
        while (i < 64) {
            Base64Coder.map2[Base64Coder.map1[i]] = (byte)i;
            ++i;
        }
    }
}

