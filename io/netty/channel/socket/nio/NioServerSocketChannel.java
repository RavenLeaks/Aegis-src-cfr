/*
 * Decompiled with CFR <Could not determine version>.
 */
package io.netty.channel.socket.nio;

import io.netty.channel.Channel;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.nio.AbstractNioMessageChannel;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.ServerSocketChannelConfig;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.SocketUtils;
import io.netty.util.internal.SuppressJava6Requirement;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.List;

public class NioServerSocketChannel
extends AbstractNioMessageChannel
implements ServerSocketChannel {
    private static final ChannelMetadata METADATA = new ChannelMetadata((boolean)false, (int)16);
    private static final SelectorProvider DEFAULT_SELECTOR_PROVIDER = SelectorProvider.provider();
    private static final InternalLogger logger = InternalLoggerFactory.getInstance(NioServerSocketChannel.class);
    private final ServerSocketChannelConfig config = new NioServerSocketChannelConfig((NioServerSocketChannel)this, (NioServerSocketChannel)this, (ServerSocket)this.javaChannel().socket(), null);

    private static java.nio.channels.ServerSocketChannel newSocket(SelectorProvider provider) {
        try {
            return provider.openServerSocketChannel();
        }
        catch (IOException e) {
            throw new ChannelException((String)"Failed to open a server socket.", (Throwable)e);
        }
    }

    public NioServerSocketChannel() {
        this((java.nio.channels.ServerSocketChannel)NioServerSocketChannel.newSocket((SelectorProvider)DEFAULT_SELECTOR_PROVIDER));
    }

    public NioServerSocketChannel(SelectorProvider provider) {
        this((java.nio.channels.ServerSocketChannel)NioServerSocketChannel.newSocket((SelectorProvider)provider));
    }

    public NioServerSocketChannel(java.nio.channels.ServerSocketChannel channel) {
        super(null, (SelectableChannel)channel, (int)16);
    }

    @Override
    public InetSocketAddress localAddress() {
        return (InetSocketAddress)super.localAddress();
    }

    @Override
    public ChannelMetadata metadata() {
        return METADATA;
    }

    @Override
    public ServerSocketChannelConfig config() {
        return this.config;
    }

    @Override
    public boolean isActive() {
        if (!this.isOpen()) return false;
        if (!this.javaChannel().socket().isBound()) return false;
        return true;
    }

    @Override
    public InetSocketAddress remoteAddress() {
        return null;
    }

    @Override
    protected java.nio.channels.ServerSocketChannel javaChannel() {
        return (java.nio.channels.ServerSocketChannel)super.javaChannel();
    }

    @Override
    protected SocketAddress localAddress0() {
        return SocketUtils.localSocketAddress((ServerSocket)this.javaChannel().socket());
    }

    @SuppressJava6Requirement(reason="Usage guarded by java version check")
    @Override
    protected void doBind(SocketAddress localAddress) throws Exception {
        if (PlatformDependent.javaVersion() >= 7) {
            this.javaChannel().bind((SocketAddress)localAddress, (int)this.config.getBacklog());
            return;
        }
        this.javaChannel().socket().bind((SocketAddress)localAddress, (int)this.config.getBacklog());
    }

    @Override
    protected void doClose() throws Exception {
        this.javaChannel().close();
    }

    @Override
    protected int doReadMessages(List<Object> buf) throws Exception {
        SocketChannel ch = SocketUtils.accept((java.nio.channels.ServerSocketChannel)this.javaChannel());
        try {
            if (ch == null) return 0;
            buf.add((Object)new NioSocketChannel((Channel)this, (SocketChannel)ch));
            return 1;
        }
        catch (Throwable t) {
            logger.warn((String)"Failed to create a new channel from an accepted socket.", (Throwable)t);
            try {
                ch.close();
                return 0;
            }
            catch (Throwable t2) {
                logger.warn((String)"Failed to close a socket.", (Throwable)t2);
            }
        }
        return 0;
    }

    @Override
    protected boolean doConnect(SocketAddress remoteAddress, SocketAddress localAddress) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void doFinishConnect() throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    protected SocketAddress remoteAddress0() {
        return null;
    }

    @Override
    protected void doDisconnect() throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    protected boolean doWriteMessage(Object msg, ChannelOutboundBuffer in) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    protected final Object filterOutboundMessage(Object msg) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    protected boolean closeOnReadError(Throwable cause) {
        return super.closeOnReadError((Throwable)cause);
    }

    static /* synthetic */ void access$100(NioServerSocketChannel x0) {
        x0.clearReadPending();
    }
}

