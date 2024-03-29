/*
 * Decompiled with CFR <Could not determine version>.
 */
package com.google.common.cache;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.cache.AbstractCache;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.UncheckedExecutionException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.ExecutionException;

@GwtIncompatible
public abstract class AbstractLoadingCache<K, V>
extends AbstractCache<K, V>
implements LoadingCache<K, V> {
    protected AbstractLoadingCache() {
    }

    @Override
    public V getUnchecked(K key) {
        try {
            return (V)this.get(key);
        }
        catch (ExecutionException e) {
            throw new UncheckedExecutionException((Throwable)e.getCause());
        }
    }

    @Override
    public ImmutableMap<K, V> getAll(Iterable<? extends K> keys) throws ExecutionException {
        LinkedHashMap<K, V> result = Maps.newLinkedHashMap();
        Iterator<K> i$ = keys.iterator();
        while (i$.hasNext()) {
            K key = i$.next();
            if (result.containsKey(key)) continue;
            result.put(key, this.get(key));
        }
        return ImmutableMap.copyOf(result);
    }

    @Override
    public final V apply(K key) {
        return (V)this.getUnchecked(key);
    }

    @Override
    public void refresh(K key) {
        throw new UnsupportedOperationException();
    }
}

