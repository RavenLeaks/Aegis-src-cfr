/*
 * Decompiled with CFR <Could not determine version>.
 */
package io.netty.handler.codec.spdy;

import io.netty.handler.codec.spdy.SpdyPingFrame;
import io.netty.util.internal.StringUtil;

public class DefaultSpdyPingFrame
implements SpdyPingFrame {
    private int id;

    public DefaultSpdyPingFrame(int id) {
        this.setId((int)id);
    }

    @Override
    public int id() {
        return this.id;
    }

    @Override
    public SpdyPingFrame setId(int id) {
        this.id = id;
        return this;
    }

    public String toString() {
        return StringUtil.simpleClassName((Object)this) + StringUtil.NEWLINE + "--> ID = " + this.id();
    }
}

