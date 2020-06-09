/*
 * Decompiled with CFR <Could not determine version>.
 */
package io.netty.channel.socket.nio;

import io.netty.channel.ChannelException;
import io.netty.channel.ChannelOption;
import io.netty.util.internal.SuppressJava6Requirement;
import java.io.IOException;
import java.net.SocketOption;
import java.net.StandardSocketOptions;
import java.nio.channels.Channel;
import java.nio.channels.NetworkChannel;
import java.nio.channels.ServerSocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

@SuppressJava6Requirement(reason="Usage explicit by the user")
public final class NioChannelOption<T>
extends ChannelOption<T> {
    private final SocketOption<T> option;

    private NioChannelOption(SocketOption<T> option) {
        super((String)option.name());
        this.option = option;
    }

    public static <T> ChannelOption<T> of(SocketOption<T> option) {
        return new NioChannelOption<T>(option);
    }

    @SuppressJava6Requirement(reason="Usage guarded by java version check")
    static <T> boolean setOption(Channel jdkChannel, NioChannelOption<T> option, T value) {
        NetworkChannel channel = (NetworkChannel)jdkChannel;
        if (!channel.supportedOptions().contains(option.option)) {
            return false;
        }
        if (channel instanceof ServerSocketChannel && option.option == StandardSocketOptions.IP_TOS) {
            return false;
        }
        try {
            channel.setOption(option.option, value);
            return true;
        }
        catch (IOException e) {
            throw new ChannelException((Throwable)e);
        }
    }

    @SuppressJava6Requirement(reason="Usage guarded by java version check")
    static <T> T getOption(Channel jdkChannel, NioChannelOption<T> option) {
        NetworkChannel channel = (NetworkChannel)jdkChannel;
        if (!channel.supportedOptions().contains(option.option)) {
            return (T)null;
        }
        if (channel instanceof ServerSocketChannel && option.option == StandardSocketOptions.IP_TOS) {
            return (T)null;
        }
        try {
            return (T)channel.getOption(option.option);
        }
        catch (IOException e) {
            throw new ChannelException((Throwable)e);
        }
    }

    @SuppressJava6Requirement(reason="Usage guarded by java version check")
    static ChannelOption[] getOptions(Channel jdkChannel) {
        NetworkChannel channel = (NetworkChannel)jdkChannel;
        Set<SocketOption<?>> supportedOpts = channel.supportedOptions();
        if (!(channel instanceof ServerSocketChannel)) {
            ChannelOption[] extraOpts = new ChannelOption[supportedOpts.size()];
            int i = 0;
            Iterator<SocketOption<?>> opt = supportedOpts.iterator();
            while (opt.hasNext()) {
                SocketOption<?> opt2 = opt.next();
                extraOpts[i++] = new NioChannelOption<?>(opt2);
            }
            return extraOpts;
        }
        ArrayList<NioChannelOption<?>> extraOpts = new ArrayList<NioChannelOption<?>>((int)supportedOpts.size());
        Iterator<SocketOption<?>> iterator = supportedOpts.iterator();
        while (iterator.hasNext()) {
            SocketOption<?> opt = iterator.next();
            if (opt == StandardSocketOptions.IP_TOS) continue;
            extraOpts.add(new NioChannelOption<?>(opt));
        }
        return extraOpts.toArray(new ChannelOption[0]);
    }
}

