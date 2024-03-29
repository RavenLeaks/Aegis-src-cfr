/*
 * Decompiled with CFR <Could not determine version>.
 */
package gnu.trove.impl.unmodifiable;

import gnu.trove.TCollections;
import gnu.trove.TDoubleCollection;
import gnu.trove.function.TDoubleFunction;
import gnu.trove.impl.unmodifiable.TUnmodifiableObjectDoubleMap;
import gnu.trove.iterator.TObjectDoubleIterator;
import gnu.trove.map.TObjectDoubleMap;
import gnu.trove.procedure.TDoubleProcedure;
import gnu.trove.procedure.TObjectDoubleProcedure;
import gnu.trove.procedure.TObjectProcedure;
import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class TUnmodifiableObjectDoubleMap<K>
implements TObjectDoubleMap<K>,
Serializable {
    private static final long serialVersionUID = -1034234728574286014L;
    private final TObjectDoubleMap<K> m;
    private transient Set<K> keySet = null;
    private transient TDoubleCollection values = null;

    public TUnmodifiableObjectDoubleMap(TObjectDoubleMap<K> m) {
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
    public boolean containsKey(Object key) {
        return this.m.containsKey((Object)key);
    }

    @Override
    public boolean containsValue(double val) {
        return this.m.containsValue((double)val);
    }

    @Override
    public double get(Object key) {
        return this.m.get((Object)key);
    }

    @Override
    public double put(K key, double value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public double remove(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(TObjectDoubleMap<? extends K> m) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(Map<? extends K, ? extends Double> map) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<K> keySet() {
        if (this.keySet != null) return this.keySet;
        this.keySet = Collections.unmodifiableSet(this.m.keySet());
        return this.keySet;
    }

    @Override
    public Object[] keys() {
        return this.m.keys();
    }

    @Override
    public K[] keys(K[] array) {
        return this.m.keys(array);
    }

    @Override
    public TDoubleCollection valueCollection() {
        if (this.values != null) return this.values;
        this.values = TCollections.unmodifiableCollection((TDoubleCollection)this.m.valueCollection());
        return this.values;
    }

    @Override
    public double[] values() {
        return this.m.values();
    }

    @Override
    public double[] values(double[] array) {
        return this.m.values((double[])array);
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
    public double getNoEntryValue() {
        return this.m.getNoEntryValue();
    }

    @Override
    public boolean forEachKey(TObjectProcedure<? super K> procedure) {
        return this.m.forEachKey(procedure);
    }

    @Override
    public boolean forEachValue(TDoubleProcedure procedure) {
        return this.m.forEachValue((TDoubleProcedure)procedure);
    }

    @Override
    public boolean forEachEntry(TObjectDoubleProcedure<? super K> procedure) {
        return this.m.forEachEntry(procedure);
    }

    @Override
    public TObjectDoubleIterator<K> iterator() {
        return new TObjectDoubleIterator<K>((TUnmodifiableObjectDoubleMap)this){
            TObjectDoubleIterator<K> iter;
            final /* synthetic */ TUnmodifiableObjectDoubleMap this$0;
            {
                this.this$0 = this$0;
                this.iter = TUnmodifiableObjectDoubleMap.access$000((TUnmodifiableObjectDoubleMap)this.this$0).iterator();
            }

            public K key() {
                return (K)this.iter.key();
            }

            public double value() {
                return this.iter.value();
            }

            public void advance() {
                this.iter.advance();
            }

            public boolean hasNext() {
                return this.iter.hasNext();
            }

            public double setValue(double val) {
                throw new UnsupportedOperationException();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public double putIfAbsent(K key, double value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void transformValues(TDoubleFunction function) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainEntries(TObjectDoubleProcedure<? super K> procedure) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean increment(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean adjustValue(K key, double amount) {
        throw new UnsupportedOperationException();
    }

    @Override
    public double adjustOrPutValue(K key, double adjust_amount, double put_amount) {
        throw new UnsupportedOperationException();
    }

    static /* synthetic */ TObjectDoubleMap access$000(TUnmodifiableObjectDoubleMap x0) {
        return x0.m;
    }
}

