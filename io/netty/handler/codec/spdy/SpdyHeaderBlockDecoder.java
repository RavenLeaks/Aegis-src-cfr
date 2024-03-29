/*
 * Decompiled with CFR <Could not determine version>.
 */
package io.netty.handler.codec.spdy;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.handler.codec.spdy.SpdyHeaderBlockZlibDecoder;
import io.netty.handler.codec.spdy.SpdyHeadersFrame;
import io.netty.handler.codec.spdy.SpdyVersion;

abstract class SpdyHeaderBlockDecoder {
    SpdyHeaderBlockDecoder() {
    }

    static SpdyHeaderBlockDecoder newInstance(SpdyVersion spdyVersion, int maxHeaderSize) {
        return new SpdyHeaderBlockZlibDecoder((SpdyVersion)spdyVersion, (int)maxHeaderSize);
    }

    abstract void decode(ByteBufAllocator var1, ByteBuf var2, SpdyHeadersFrame var3) throws Exception;

    abstract void endHeaderBlock(SpdyHeadersFrame var1) throws Exception;

    abstract void end();
}

