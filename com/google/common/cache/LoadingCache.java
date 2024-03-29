/*
 * Decompiled with CFR <Could not determine version>.
 */
package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Function;
import com.google.common.cache.Cache;
import com.google.common.collect.ImmutableMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;

@GwtCompatible
public interface LoadingCache<K, V>
extends Cache<K, V>,
Function<K, V> {
    public V get(K var1) throws ExecutionException;

    public V getUnchecked(K var1);

    public ImmutableMap<K, V> getAll(Iterable<? extends K> var1) throws ExecutionException;

    @Deprecated
    @Override
    public V apply(K var1);

    public void refresh(K var1);

    @Override
    public ConcurrentMap<K, V> asMap();
}

