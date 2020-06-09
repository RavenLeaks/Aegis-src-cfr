/*
 * Decompiled with CFR <Could not determine version>.
 */
package io.netty.channel.socket.oio;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.AddressedEnvelope;
import io.netty.channel.Channel;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPromise;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.oio.AbstractOioMessageChannel;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.DatagramChannelConfig;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.oio.DefaultOioDatagramChannelConfig;
import io.netty.channel.socket.oio.OioDatagramChannelConfig;
import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.channels.NotYetConnectedException;
import java.util.List;
import java.util.Locale;

@Deprecated
public class OioDatagramChannel
extends AbstractOioMessageChannel
implements DatagramChannel {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance(OioDatagramChannel.class);
    private static final ChannelMetadata METADATA = new ChannelMetadata((boolean)true);
    private static final String EXPECTED_TYPES = " (expected: " + StringUtil.simpleClassName(DatagramPacket.class) + ", " + StringUtil.simpleClassName(AddressedEnvelope.class) + '<' + StringUtil.simpleClassName(ByteBuf.class) + ", " + StringUtil.simpleClassName(SocketAddress.class) + ">, " + StringUtil.simpleClassName(ByteBuf.class) + ')';
    private final MulticastSocket socket;
    private final OioDatagramChannelConfig config;
    private final java.net.DatagramPacket tmpPacket = new java.net.DatagramPacket((byte[])EmptyArrays.EMPTY_BYTES, (int)0);

    private static MulticastSocket newSocket() {
        try {
            return new MulticastSocket(null);
        }
        catch (Exception e) {
            throw new ChannelException((String)"failed to create a new socket", (Throwable)e);
        }
    }

    public OioDatagramChannel() {
        this((MulticastSocket)OioDatagramChannel.newSocket());
    }

    public OioDatagramChannel(MulticastSocket socket) {
        super(null);
        boolean success = false;
        try {
            socket.setSoTimeout((int)1000);
            socket.setBroadcast((boolean)false);
            success = true;
        }
        catch (SocketException e) {
            throw new ChannelException((String)"Failed to configure the datagram socket timeout.", (Throwable)e);
        }
        finally {
            if (!success) {
                socket.close();
            }
        }
        this.socket = socket;
        this.config = new DefaultOioDatagramChannelConfig((DatagramChannel)this, (DatagramSocket)socket);
    }

    @Override
    public ChannelMetadata metadata() {
        return METADATA;
    }

    @Override
    public DatagramChannelConfig config() {
        return this.config;
    }

    @Override
    public boolean isOpen() {
        if (this.socket.isClosed()) return false;
        return true;
    }

    @Override
    public boolean isActive() {
        if (!this.isOpen()) return false;
        if (this.config.getOption(ChannelOption.DATAGRAM_CHANNEL_ACTIVE_ON_REGISTRATION).booleanValue()) {
            if (this.isRegistered()) return true;
        }
        if (!this.socket.isBound()) return false;
        return true;
    }

    @Override
    public boolean isConnected() {
        return this.socket.isConnected();
    }

    @Override
    protected SocketAddress localAddress0() {
        return this.socket.getLocalSocketAddress();
    }

    @Override
    protected SocketAddress remoteAddress0() {
        return this.socket.getRemoteSocketAddress();
    }

    @Override
    protected void doBind(SocketAddress localAddress) throws Exception {
        this.socket.bind((SocketAddress)localAddress);
    }

    @Override
    public InetSocketAddress localAddress() {
        return (InetSocketAddress)super.localAddress();
    }

    @Override
    public InetSocketAddress remoteAddress() {
        return (InetSocketAddress)super.remoteAddress();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    protected void doConnect(SocketAddress remoteAddress, SocketAddress localAddress) throws Exception {
        if (localAddress != null) {
            this.socket.bind((SocketAddress)localAddress);
        }
        boolean success = false;
        try {
            this.socket.connect((SocketAddress)remoteAddress);
            success = true;
            return;
        }
        finally {
            if (!success) {
                try {
                    this.socket.close();
                }
                catch (Throwable t) {
                    logger.warn((String)"Failed to close a socket.", (Throwable)t);
                }
            }
        }
    }

    @Override
    protected void doDisconnect() throws Exception {
        this.socket.disconnect();
    }

    @Override
    protected void doClose() throws Exception {
        this.socket.close();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    protected int doReadMessages(List<Object> buf) throws Exception {
        DatagramChannelConfig config = this.config();
        RecvByteBufAllocator.Handle allocHandle = this.unsafe().recvBufAllocHandle();
        ByteBuf data = config.getAllocator().heapBuffer((int)allocHandle.guess());
        boolean free = true;
        try {
            this.tmpPacket.setAddress(null);
            this.tmpPacket.setData((byte[])data.array(), (int)data.arrayOffset(), (int)data.capacity());
            this.socket.receive((java.net.DatagramPacket)this.tmpPacket);
            InetSocketAddress remoteAddr = (InetSocketAddress)this.tmpPacket.getSocketAddress();
            allocHandle.lastBytesRead((int)this.tmpPacket.getLength());
            buf.add((Object)new DatagramPacket((ByteBuf)data.writerIndex((int)allocHandle.lastBytesRead()), (InetSocketAddress)this.localAddress(), (InetSocketAddress)remoteAddr));
            free = false;
            int n = 1;
            return n;
        }
        catch (SocketTimeoutException e) {
            int n = 0;
            return n;
        }
        catch (SocketException e) {
            if (!e.getMessage().toLowerCase((Locale)Locale.US).contains((CharSequence)"socket closed")) {
                throw e;
            }
            int n = -1;
            return n;
        }
        catch (Throwable cause) {
            PlatformDependent.throwException((Throwable)cause);
            int n = -1;
            return n;
        }
        finally {
            if (free) {
                data.release();
            }
        }
    }

    @Override
    protected void doWrite(ChannelOutboundBuffer in) throws Exception {
        Object o;
        while ((o = in.current()) != null) {
            ByteBuf data;
            SocketAddress remoteAddress;
            if (o instanceof AddressedEnvelope) {
                AddressedEnvelope envelope = (AddressedEnvelope)o;
                remoteAddress = (SocketAddress)envelope.recipient();
                data = (ByteBuf)envelope.content();
            } else {
                data = (ByteBuf)o;
                remoteAddress = null;
            }
            int length = data.readableBytes();
            try {
                if (remoteAddress != null) {
                    this.tmpPacket.setSocketAddress(remoteAddress);
                } else {
                    if (!this.isConnected()) {
                        throw new NotYetConnectedException();
                    }
                    this.tmpPacket.setAddress(null);
                }
                if (data.hasArray()) {
                    this.tmpPacket.setData((byte[])data.array(), (int)(data.arrayOffset() + data.readerIndex()), (int)length);
                } else {
                    this.tmpPacket.setData((byte[])ByteBufUtil.getBytes((ByteBuf)data, (int)data.readerIndex(), (int)length));
                }
                this.socket.send((java.net.DatagramPacket)this.tmpPacket);
                in.remove();
            }
            catch (Exception e) {
                in.remove((Throwable)e);
                continue;
            }
            break;
        }
        return;
    }

    @Override
    protected Object filterOutboundMessage(Object msg) {
        if (msg instanceof DatagramPacket) return msg;
        if (msg instanceof ByteBuf) {
            return msg;
        }
        if (!(msg instanceof AddressedEnvelope)) throw new UnsupportedOperationException((String)("unsupported message type: " + StringUtil.simpleClassName((Object)msg) + EXPECTED_TYPES));
        AddressedEnvelope e = (AddressedEnvelope)msg;
        if (!(e.content() instanceof ByteBuf)) throw new UnsupportedOperationException((String)("unsupported message type: " + StringUtil.simpleClassName((Object)msg) + EXPECTED_TYPES));
        return msg;
    }

    @Override
    public ChannelFuture joinGroup(InetAddress multicastAddress) {
        return this.joinGroup((InetAddress)multicastAddress, (ChannelPromise)this.newPromise());
    }

    @Override
    public ChannelFuture joinGroup(InetAddress multicastAddress, ChannelPromise promise) {
        this.ensureBound();
        try {
            this.socket.joinGroup((InetAddress)multicastAddress);
            promise.setSuccess();
            return promise;
        }
        catch (IOException e) {
            promise.setFailure((Throwable)e);
        }
        return promise;
    }

    @Override
    public ChannelFuture joinGroup(InetSocketAddress multicastAddress, NetworkInterface networkInterface) {
        return this.joinGroup((InetSocketAddress)multicastAddress, (NetworkInterface)networkInterface, (ChannelPromise)this.newPromise());
    }

    @Override
    public ChannelFuture joinGroup(InetSocketAddress multicastAddress, NetworkInterface networkInterface, ChannelPromise promise) {
        this.ensureBound();
        try {
            this.socket.joinGroup((SocketAddress)multicastAddress, (NetworkInterface)networkInterface);
            promise.setSuccess();
            return promise;
        }
        catch (IOException e) {
            promise.setFailure((Throwable)e);
        }
        return promise;
    }

    @Override
    public ChannelFuture joinGroup(InetAddress multicastAddress, NetworkInterface networkInterface, InetAddress source) {
        return this.newFailedFuture((Throwable)new UnsupportedOperationException());
    }

    @Override
    public ChannelFuture joinGroup(InetAddress multicastAddress, NetworkInterface networkInterface, InetAddress source, ChannelPromise promise) {
        promise.setFailure((Throwable)new UnsupportedOperationException());
        return promise;
    }

    private void ensureBound() {
        if (this.isActive()) return;
        throw new IllegalStateException((String)(DatagramChannel.class.getName() + " must be bound to join a group."));
    }

    @Override
    public ChannelFuture leaveGroup(InetAddress multicastAddress) {
        return this.leaveGroup((InetAddress)multicastAddress, (ChannelPromise)this.newPromise());
    }

    @Override
    public ChannelFuture leaveGroup(InetAddress multicastAddress, ChannelPromise promise) {
        try {
            this.socket.leaveGroup((InetAddress)multicastAddress);
            promise.setSuccess();
            return promise;
        }
        catch (IOException e) {
            promise.setFailure((Throwable)e);
        }
        return promise;
    }

    @Override
    public ChannelFuture leaveGroup(InetSocketAddress multicastAddress, NetworkInterface networkInterface) {
        return this.leaveGroup((InetSocketAddress)multicastAddress, (NetworkInterface)networkInterface, (ChannelPromise)this.newPromise());
    }

    @Override
    public ChannelFuture leaveGroup(InetSocketAddress multicastAddress, NetworkInterface networkInterface, ChannelPromise promise) {
        try {
            this.socket.leaveGroup((SocketAddress)multicastAddress, (NetworkInterface)networkInterface);
            promise.setSuccess();
            return promise;
        }
        catch (IOException e) {
            promise.setFailure((Throwable)e);
        }
        return promise;
    }

    @Override
    public ChannelFuture leaveGroup(InetAddress multicastAddress, NetworkInterface networkInterface, InetAddress source) {
        return this.newFailedFuture((Throwable)new UnsupportedOperationException());
    }

    @Override
    public ChannelFuture leaveGroup(InetAddress multicastAddress, NetworkInterface networkInterface, InetAddress source, ChannelPromise promise) {
        promise.setFailure((Throwable)new UnsupportedOperationException());
        return promise;
    }

    @Override
    public ChannelFuture block(InetAddress multicastAddress, NetworkInterface networkInterface, InetAddress sourceToBlock) {
        return this.newFailedFuture((Throwable)new UnsupportedOperationException());
    }

    @Override
    public ChannelFuture block(InetAddress multicastAddress, NetworkInterface networkInterface, InetAddress sourceToBlock, ChannelPromise promise) {
        promise.setFailure((Throwable)new UnsupportedOperationException());
        return promise;
    }

    @Override
    public ChannelFuture block(InetAddress multicastAddress, InetAddress sourceToBlock) {
        return this.newFailedFuture((Throwable)new UnsupportedOperationException());
    }

    @Override
    public ChannelFuture block(InetAddress multicastAddress, InetAddress sourceToBlock, ChannelPromise promise) {
        promise.setFailure((Throwable)new UnsupportedOperationException());
        return promise;
    }
}

