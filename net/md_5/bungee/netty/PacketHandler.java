/*
 * Decompiled with CFR <Could not determine version>.
 */
package net.md_5.bungee.netty;

import net.md_5.bungee.netty.ChannelWrapper;
import net.md_5.bungee.protocol.AbstractPacketHandler;
import net.md_5.bungee.protocol.PacketWrapper;

public abstract class PacketHandler
extends AbstractPacketHandler {
    public abstract String toString();

    public boolean shouldHandle(PacketWrapper packet) throws Exception {
        return true;
    }

    public void exception(Throwable t) throws Exception {
    }

    public void handle(PacketWrapper packet) throws Exception {
    }

    public void connected(ChannelWrapper channel) throws Exception {
    }

    public void disconnected(ChannelWrapper channel) throws Exception {
    }

    public void writabilityChanged(ChannelWrapper channel) throws Exception {
    }
}

