/*
 * Decompiled with CFR <Could not determine version>.
 */
package io.netty.handler.codec.spdy;

import io.netty.handler.codec.spdy.SpdyFrame;

public interface SpdyPingFrame
extends SpdyFrame {
    public int id();

    public SpdyPingFrame setId(int var1);
}

