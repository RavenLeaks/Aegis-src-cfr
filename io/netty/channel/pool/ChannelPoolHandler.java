/*
 * Decompiled with CFR <Could not determine version>.
 */
package io.netty.channel.pool;

import io.netty.channel.Channel;

public interface ChannelPoolHandler {
    public void channelReleased(Channel var1) throws Exception;

    public void channelAcquired(Channel var1) throws Exception;

    public void channelCreated(Channel var1) throws Exception;
}

