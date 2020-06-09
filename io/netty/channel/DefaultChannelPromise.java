/*
 * Decompiled with CFR <Could not determine version>.
 */
package io.netty.channel;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFlushPromiseNotifier;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelPromise;
import io.netty.channel.EventLoop;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.Promise;
import io.netty.util.internal.ObjectUtil;

public class DefaultChannelPromise
extends DefaultPromise<Void>
implements ChannelPromise,
ChannelFlushPromiseNotifier.FlushCheckpoint {
    private final Channel channel;
    private long checkpoint;

    public DefaultChannelPromise(Channel channel) {
        this.channel = ObjectUtil.checkNotNull(channel, (String)"channel");
    }

    public DefaultChannelPromise(Channel channel, EventExecutor executor) {
        super((EventExecutor)executor);
        this.channel = ObjectUtil.checkNotNull(channel, (String)"channel");
    }

    @Override
    protected EventExecutor executor() {
        EventExecutor e = super.executor();
        if (e != null) return e;
        return this.channel().eventLoop();
    }

    @Override
    public Channel channel() {
        return this.channel;
    }

    @Override
    public ChannelPromise setSuccess() {
        return this.setSuccess(null);
    }

    @Override
    public ChannelPromise setSuccess(Void result) {
        super.setSuccess(result);
        return this;
    }

    @Override
    public boolean trySuccess() {
        return this.trySuccess(null);
    }

    @Override
    public ChannelPromise setFailure(Throwable cause) {
        super.setFailure((Throwable)cause);
        return this;
    }

    @Override
    public ChannelPromise addListener(GenericFutureListener<? extends Future<? super Void>> listener) {
        super.addListener(listener);
        return this;
    }

    @Override
    public ChannelPromise addListeners(GenericFutureListener<? extends Future<? super Void>> ... listeners) {
        super.addListeners((GenericFutureListener[])listeners);
        return this;
    }

    @Override
    public ChannelPromise removeListener(GenericFutureListener<? extends Future<? super Void>> listener) {
        super.removeListener(listener);
        return this;
    }

    @Override
    public ChannelPromise removeListeners(GenericFutureListener<? extends Future<? super Void>> ... listeners) {
        super.removeListeners((GenericFutureListener[])listeners);
        return this;
    }

    @Override
    public ChannelPromise sync() throws InterruptedException {
        super.sync();
        return this;
    }

    @Override
    public ChannelPromise syncUninterruptibly() {
        super.syncUninterruptibly();
        return this;
    }

    @Override
    public ChannelPromise await() throws InterruptedException {
        super.await();
        return this;
    }

    @Override
    public ChannelPromise awaitUninterruptibly() {
        super.awaitUninterruptibly();
        return this;
    }

    @Override
    public long flushCheckpoint() {
        return this.checkpoint;
    }

    @Override
    public void flushCheckpoint(long checkpoint) {
        this.checkpoint = checkpoint;
    }

    @Override
    public ChannelPromise promise() {
        return this;
    }

    @Override
    protected void checkDeadLock() {
        if (!this.channel().isRegistered()) return;
        super.checkDeadLock();
    }

    @Override
    public ChannelPromise unvoid() {
        return this;
    }

    @Override
    public boolean isVoid() {
        return false;
    }
}

