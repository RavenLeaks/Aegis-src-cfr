/*
 * Decompiled with CFR <Could not determine version>.
 */
package io.netty.channel.nio;

import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;

public interface NioTask<C extends SelectableChannel> {
    public void channelReady(C var1, SelectionKey var2) throws Exception;

    public void channelUnregistered(C var1, Throwable var2) throws Exception;
}

