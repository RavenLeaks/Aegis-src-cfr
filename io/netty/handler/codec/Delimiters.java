/*
 * Decompiled with CFR <Could not determine version>.
 */
package io.netty.handler.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public final class Delimiters {
    public static ByteBuf[] nulDelimiter() {
        return new ByteBuf[]{Unpooled.wrappedBuffer((byte[])new byte[]{0})};
    }

    public static ByteBuf[] lineDelimiter() {
        return new ByteBuf[]{Unpooled.wrappedBuffer((byte[])new byte[]{13, 10}), Unpooled.wrappedBuffer((byte[])new byte[]{10})};
    }

    private Delimiters() {
    }
}

