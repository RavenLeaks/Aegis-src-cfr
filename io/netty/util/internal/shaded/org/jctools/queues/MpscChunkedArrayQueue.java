/*
 * Decompiled with CFR <Could not determine version>.
 */
package io.netty.util.internal.shaded.org.jctools.queues;

import io.netty.util.internal.shaded.org.jctools.queues.LinkedArrayQueueUtil;
import io.netty.util.internal.shaded.org.jctools.queues.MpscChunkedArrayQueueColdProducerFields;
import io.netty.util.internal.shaded.org.jctools.util.Pow2;

public class MpscChunkedArrayQueue<E>
extends MpscChunkedArrayQueueColdProducerFields<E> {
    long p0;
    long p1;
    long p2;
    long p3;
    long p4;
    long p5;
    long p6;
    long p7;
    long p10;
    long p11;
    long p12;
    long p13;
    long p14;
    long p15;
    long p16;
    long p17;

    public MpscChunkedArrayQueue(int maxCapacity) {
        super((int)Math.max((int)2, (int)Math.min((int)1024, (int)Pow2.roundToPowerOfTwo((int)(maxCapacity / 8)))), (int)maxCapacity);
    }

    public MpscChunkedArrayQueue(int initialCapacity, int maxCapacity) {
        super((int)initialCapacity, (int)maxCapacity);
    }

    @Override
    protected long availableInQueue(long pIndex, long cIndex) {
        return this.maxQueueCapacity - (pIndex - cIndex);
    }

    @Override
    public int capacity() {
        return (int)(this.maxQueueCapacity / 2L);
    }

    @Override
    protected int getNextBufferSize(E[] buffer) {
        return LinkedArrayQueueUtil.length((Object[])buffer);
    }

    @Override
    protected long getCurrentBufferCapacity(long mask) {
        return mask;
    }
}
