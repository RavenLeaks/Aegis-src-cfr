/*
 * Decompiled with CFR <Could not determine version>.
 */
package gnu.trove.impl.unmodifiable;

import gnu.trove.TCollections;
import gnu.trove.TLongCollection;
import gnu.trove.function.TLongFunction;
import gnu.trove.impl.unmodifiable.TUnmodifiableIntLongMap;
import gnu.trove.iterator.TIntLongIterator;
import gnu.trove.map.TIntLongMap;
import gnu.trove.procedure.TIntLongProcedure;
import gnu.trove.procedure.TIntProcedure;
import gnu.trove.procedure.TLongProcedure;
import gnu.trove.set.TIntSet;
import java.io.Serializable;
import java.util.Map;

public class TUnmodifiableIntLongMap
implements TIntLongMap,
Serializable {
    private static final long serialVersionUID = -1034234728574286014L;
    private final TIntLongMap m;
    private transient TIntSet keySet = null;
    private transient TLongCollection values = null;

    public TUnmodifiableIntLongMap(TIntLongMap m) {
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
    public boolean containsValue(long val) {
        return this.m.containsValue((long)val);
    }

    @Override
    public long get(int key) {
        return this.m.get((int)key);
    }

    @Override
    public long put(int key, long value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long remove(int key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(TIntLongMap m) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Long> map) {
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
    public TLongCollection valueCollection() {
        if (this.values != null) return this.values;
        this.values = TCollections.unmodifiableCollection((TLongCollection)this.m.valueCollection());
        return this.values;
    }

    @Override
    public long[] values() {
        return this.m.values();
    }

    @Override
    public long[] values(long[] array) {
        return this.m.values((long[])array);
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (this.m.equals((Object)o)) return true;
        return false;
    }

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
    public long getNoEntryValue() {
        return this.m.getNoEntryValue();
    }

    @Override
    public boolean forEachKey(TIntProcedure procedure) {
        return this.m.forEachKey((TIntProcedure)procedure);
    }

    @Override
    public boolean forEachValue(TLongProcedure procedure) {
        return this.m.forEachValue((TLongProcedure)procedure);
    }

    @Override
    public boolean forEachEntry(TIntLongProcedure procedure) {
        return this.m.forEachEntry((TIntLongProcedure)procedure);
    }

    @Override
    public TIntLongIterator iterator() {
        return new TIntLongIterator((TUnmodifiableIntLongMap)this){
            TIntLongIterator iter;
            final /* synthetic */ TUnmodifiableIntLongMap this$0;
            {
                this.this$0 = this$0;
                this.iter = TUnmodifiableIntLongMap.access$000((TUnmodifiableIntLongMap)this.this$0).iterator();
            }

            public int key() {
                return this.iter.key();
            }

            public long value() {
                return this.iter.value();
            }

            public void advance() {
                this.iter.advance();
            }

            public boolean hasNext() {
                return this.iter.hasNext();
            }

            public long setValue(long val) {
                throw new UnsupportedOperationException();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public long putIfAbsent(int key, long value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void transformValues(TLongFunction function) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainEntries(TIntLongProcedure procedure) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean increment(int key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean adjustValue(int key, long amount) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long adjustOrPutValue(int key, long adjust_amount, long put_amount) {
        throw new UnsupportedOperationException();
    }

    static /* synthetic */ TIntLongMap access$000(TUnmodifiableIntLongMap x0) {
        return x0.m;
    }
}

