/*
 * Decompiled with CFR <Could not determine version>.
 * 
 * Could not load the following classes:
 *  com.google.errorprone.annotations.CanIgnoreReturnValue
 */
package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.ForwardingQueue;
import com.google.common.collect.Iterables;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Queue;

@Beta
@GwtCompatible
public final class EvictingQueue<E>
extends ForwardingQueue<E>
implements Serializable {
    private final Queue<E> delegate;
    @VisibleForTesting
    final int maxSize;
    private static final long serialVersionUID = 0L;

    private EvictingQueue(int maxSize) {
        Preconditions.checkArgument((boolean)(maxSize >= 0), (String)"maxSize (%s) must >= 0", (int)maxSize);
        this.delegate = new ArrayDeque<E>((int)maxSize);
        this.maxSize = maxSize;
    }

    public static <E> EvictingQueue<E> create(int maxSize) {
        return new EvictingQueue<E>((int)maxSize);
    }

    public int remainingCapacity() {
        return this.maxSize - this.size();
    }

    @Override
    protected Queue<E> delegate() {
        return this.delegate;
    }

    @CanIgnoreReturnValue
    @Override
    public boolean offer(E e) {
        return this.add(e);
    }

    @CanIgnoreReturnValue
    @Override
    public boolean add(E e) {
        Preconditions.checkNotNull(e);
        if (this.maxSize == 0) {
            return true;
        }
        if (this.size() == this.maxSize) {
            this.delegate.remove();
        }
        this.delegate.add(e);
        return true;
    }

    @CanIgnoreReturnValue
    @Override
    public boolean addAll(Collection<? extends E> collection) {
        int size = collection.size();
        if (size < this.maxSize) return this.standardAddAll(collection);
        this.clear();
        return Iterables.addAll(this, Iterables.skip(collection, (int)(size - this.maxSize)));
    }

    @Override
    public boolean contains(Object object) {
        return this.delegate().contains((Object)Preconditions.checkNotNull(object));
    }

    @CanIgnoreReturnValue
    @Override
    public boolean remove(Object object) {
        return this.delegate().remove((Object)Preconditions.checkNotNull(object));
    }
}

