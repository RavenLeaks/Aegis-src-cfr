/*
 * Decompiled with CFR <Could not determine version>.
 */
package gnu.trove.impl.unmodifiable;

import gnu.trove.TCollections;
import gnu.trove.function.TObjectFunction;
import gnu.trove.impl.unmodifiable.TUnmodifiableIntObjectMap;
import gnu.trove.iterator.TIntObjectIterator;
import gnu.trove.map.TIntObjectMap;
import gnu.trove.procedure.TIntObjectProcedure;
import gnu.trove.procedure.TIntProcedure;
import gnu.trove.procedure.TObjectProcedure;
import gnu.trove.set.TIntSet;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class TUnmodifiableIntObjectMap<V>
implements TIntObjectMap<V>,
Serializable {
    private static final long serialVersionUID = -1034234728574286014L;
    private final TIntObjectMap<V> m;
    private transient TIntSet keySet = null;
    private transient Collection<V> values = null;

    public TUnmodifiableIntObjectMap(TIntObjectMap<V> m) {
        if (m == null) {
            throw new NullPointerException();
        }
        this.m = m;
    }

    @Override
    public int size() {
        return this.m.size();
    }

    @Override
    public boolean isEmpty() {
        return this.m.isEmpty();
    }

    @Override
    public boolean containsKey(int key) {
        return this.m.containsKey((int)key);
    }

    @Override
    public boolean containsValue(Object val) {
        return this.m.containsValue((Object)val);
    }

    @Override
    public V get(int key) {
        return (V)this.m.get((int)key);
    }

    @Override
    public V put(int key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(int key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(TIntObjectMap<? extends V> m) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends V> map) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public TIntSet keySet() {
        if (this.keySet != null) return this.keySet;
        this.keySet = TCollections.unmodifiableSet((TIntSet)this.m.keySet());
        return this.keySet;
    }

    @Override
    public int[] keys() {
        return this.m.keys();
    }

    @Override
    public int[] keys(int[] array) {
        return this.m.keys((int[])array);
    }

    @Override
    public Collection<V> valueCollection() {
        if (this.values != null) return this.values;
        this.values = Collections.unmodifiableCollection(this.m.valueCollection());
        return this.values;
    }

    @Override
    public Object[] values() {
        return this.m.values();
    }

    @Override
    public V[] values(V[] array) {
        return this.m.values(array);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (this.m.equals((Object)o)) return true;
        return false;
    }

    @Override
    public int hashCode() {
        return this.m.hashCode();
    }

    public String toString() {
        return this.m.toString();
    }

    @Override
    public int getNoEntryKey() {
        return this.m.getNoEntryKey();
    }

    @Override
    public boolean forEachKey(TIntProcedure procedure) {
        return this.m.forEachKey((TIntProcedure)procedure);
    }

    @Override
    public boolean forEachValue(TObjectProcedure<? super V> procedure) {
        return this.m.forEachValue(procedure);
    }

    @Override
    public boolean forEachEntry(TIntObjectProcedure<? super V> procedure) {
        return this.m.forEachEntry(procedure);
    }

    @Override
    public TIntObjectIterator<V> iterator() {
        return new TIntObjectIterator<V>((TUnmodifiableIntObjectMap)this){
            TIntObjectIterator<V> iter;
            final /* synthetic */ TUnmodifiableIntObjectMap this$0;
            {
                this.this$0 = this$0;
                this.iter = TUnmodifiableIntObjectMap.access$000((TUnmodifiableIntObjectMap)this.this$0).iterator();
            }

            public int key() {
                return this.iter.key();
            }

            public V value() {
                return (V)this.iter.value();
            }

            public void advance() {
                this.iter.advance();
            }

            public boolean hasNext() {
                return this.iter.hasNext();
            }

            public V setValue(V val) {
                throw new UnsupportedOperationException();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public V putIfAbsent(int key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void transformValues(TObjectFunction<V, V> function) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainEntries(TIntObjectProcedure<? super V> procedure) {
        throw new UnsupportedOperationException();
    }

    static /* synthetic */ TIntObjectMap access$000(TUnmodifiableIntObjectMap x0) {
        return x0.m;
    }
}

