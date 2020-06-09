/*
 * Decompiled with CFR <Could not determine version>.
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractSetMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.google.common.collect.Serialization;
import com.google.common.collect.Sets;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@GwtCompatible(serializable=true, emulated=true)
public final class HashMultimap<K, V>
extends AbstractSetMultimap<K, V> {
    private static final int DEFAULT_VALUES_PER_KEY = 2;
    @VisibleForTesting
    transient int expectedValuesPerKey = 2;
    @GwtIncompatible
    private static final long serialVersionUID = 0L;

    public static <K, V> HashMultimap<K, V> create() {
        return new HashMultimap<K, V>();
    }

    public static <K, V> HashMultimap<K, V> create(int expectedKeys, int expectedValuesPerKey) {
        return new HashMultimap<K, V>((int)expectedKeys, (int)expectedValuesPerKey);
    }

    public static <K, V> HashMultimap<K, V> create(Multimap<? extends K, ? extends V> multimap) {
        return new HashMultimap<K, V>(multimap);
    }

    private HashMultimap() {
        super(new HashMap<K, V>());
    }

    private HashMultimap(int expectedKeys, int expectedValuesPerKey) {
        super(Maps.newHashMapWithExpectedSize((int)expectedKeys));
        Preconditions.checkArgument((boolean)(expectedValuesPerKey >= 0));
        this.expectedValuesPerKey = expectedValuesPerKey;
    }

    private HashMultimap(Multimap<? extends K, ? extends V> multimap) {
        super(Maps.newHashMapWithExpectedSize((int)multimap.keySet().size()));
        this.putAll(multimap);
    }

    @Override
    Set<V> createCollection() {
        return Sets.newHashSetWithExpectedSize((int)this.expectedValuesPerKey);
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        Serialization.writeMultimap(this, (ObjectOutputStream)stream);
    }

    @GwtIncompatible
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        this.expectedValuesPerKey = 2;
        int distinctKeys = Serialization.readCount((ObjectInputStream)stream);
        HashMap<K, V> map = Maps.newHashMap();
        this.setMap(map);
        Serialization.populateMultimap(this, (ObjectInputStream)stream, (int)distinctKeys);
    }
}

