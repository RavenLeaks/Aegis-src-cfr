/*
 * Decompiled with CFR <Could not determine version>.
 */
package io.netty.util;

import io.netty.util.ByteProcessor;

public interface ByteProcessor {
    public static final ByteProcessor FIND_NUL = new IndexOfProcessor((byte)0);
    public static final ByteProcessor FIND_NON_NUL = new IndexNotOfProcessor((byte)0);
    public static final ByteProcessor FIND_CR = new IndexOfProcessor((byte)13);
    public static final ByteProcessor FIND_NON_CR = new IndexNotOfProcessor((byte)13);
    public static final ByteProcessor FIND_LF = new IndexOfProcessor((byte)10);
    public static final ByteProcessor FIND_NON_LF = new IndexNotOfProcessor((byte)10);
    public static final ByteProcessor FIND_SEMI_COLON = new IndexOfProcessor((byte)59);
    public static final ByteProcessor FIND_COMMA = new IndexOfProcessor((byte)44);
    public static final ByteProcessor FIND_ASCII_SPACE = new IndexOfProcessor((byte)32);
    public static final ByteProcessor FIND_CRLF = new ByteProcessor(){

        public boolean process(byte value) {
            if (value == 13) return false;
            if (value == 10) return false;
            return true;
        }
    };
    public static final ByteProcessor FIND_NON_CRLF = new ByteProcessor(){

        public boolean process(byte value) {
            if (value == 13) return true;
            if (value == 10) return true;
            return false;
        }
    };
    public static final ByteProcessor FIND_LINEAR_WHITESPACE = new ByteProcessor(){

        public boolean process(byte value) {
            if (value == 32) return false;
            if (value == 9) return false;
            return true;
        }
    };
    public static final ByteProcessor FIND_NON_LINEAR_WHITESPACE = new ByteProcessor(){

        public boolean process(byte value) {
            if (value == 32) return true;
            if (value == 9) return true;
            return false;
        }
    };

    public boolean process(byte var1) throws Exception;
}

