/*
 * Decompiled with CFR <Could not determine version>.
 */
package io.netty.channel;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelPromise;
import io.netty.util.concurrent.Promise;
import io.netty.util.concurrent.PromiseNotifier;

public final class ChannelPromiseNotifier
extends PromiseNotifier<Void, ChannelFuture>
implements ChannelFutureListener {
    public ChannelPromiseNotifier(ChannelPromise ... promises) {
        super(promises);
    }

    public ChannelPromiseNotifier(boolean logNotifyFailure, ChannelPromise ... promises) {
        super((boolean)logNotifyFailure, promises);
    }
}

