/*
 * Decompiled with CFR <Could not determine version>.
 */
package io.netty.util.collection;

import io.netty.util.collection.CharObjectMap;
import java.util.Map;

public interface CharObjectMap<V>
extends Map<Character, V> {
    public V get(char var1);

    @Override
    public V put(char var1, V var2);

    public V remove(char var1);

    public Iterable<PrimitiveEntry<V>> entries();

    public boolean containsKey(char var1);
}

