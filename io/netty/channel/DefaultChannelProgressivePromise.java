/*
 * Decompiled with CFR <Could not determine version>.
 */
package io.netty.channel;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFlushPromiseNotifier;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelProgressiveFuture;
import io.netty.channel.ChannelProgressivePromise;
import io.netty.channel.ChannelPromise;
import io.netty.channel.EventLoop;
import io.netty.util.concurrent.DefaultProgressivePromise;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.ProgressiveFuture;
import io.netty.util.concurrent.ProgressivePromise;
import io.netty.util.concurrent.Promise;

public class DefaultChannelProgressivePromise
extends DefaultProgressivePromise<Void>
implements ChannelProgressivePromise,
ChannelFlushPromiseNotifier.FlushCheckpoint {
    private final Channel channel;
    private long checkpoint;

    public DefaultChannelProgressivePromise(Channel channel) {
        this.channel = channel;
    }

    public DefaultChannelProgressivePromise(Channel channel, EventExecutor executor) {
        super((EventExecutor)executor);
        this.channel = channel;
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
    public ChannelProgressivePromise setSuccess() {
        return this.setSuccess(null);
    }

    @Override
    public ChannelProgressivePromise setSuccess(Void result) {
        super.setSuccess((Object)result);
        return this;
    }

    @Override
    public boolean trySuccess() {
        return this.trySuccess(null);
    }

    @Override
    public ChannelProgressivePromise setFailure(Throwable cause) {
        super.setFailure((Throwable)cause);
        return this;
    }

    @Override
    public ChannelProgressivePromise setProgress(long progress, long total) {
        super.setProgress((long)progress, (long)total);
        return this;
    }

    @Override
    public ChannelProgressivePromise addListener(GenericFutureListener<? extends Future<? super Void>> listener) {
        super.addListener(listener);
        return this;
    }

    @Override
    public ChannelProgressivePromise addListeners(GenericFutureListener<? extends Future<? super Void>> ... listeners) {
        super.addListeners((GenericFutureListener[])listeners);
        return this;
    }

    @Override
    public ChannelProgressivePromise removeListener(GenericFutureListener<? extends Future<? super Void>> listener) {
        super.removeListener(listener);
        return this;
    }

    @Override
    public ChannelProgressivePromise removeListeners(GenericFutureListener<? extends Future<? super Void>> ... listeners) {
        super.removeListeners((GenericFutureListener[])listeners);
        return this;
    }

    @Override
    public ChannelProgressivePromise sync() throws InterruptedException {
        super.sync();
        return this;
    }

    @Override
    public ChannelProgressivePromise syncUninterruptibly() {
        super.syncUninterruptibly();
        return this;
    }

    @Override
    public ChannelProgressivePromise await() throws InterruptedException {
        super.await();
        return this;
    }

    @Override
    public ChannelProgressivePromise awaitUninterruptibly() {
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
    public ChannelProgressivePromise promise() {
        return this;
    }

    @Override
    protected void checkDeadLock() {
        if (!this.channel().isRegistered()) return;
        super.checkDeadLock();
    }

    @Override
    public ChannelProgressivePromise unvoid() {
        return this;
    }

    @Override
    public boolean isVoid() {
        return false;
    }
}

