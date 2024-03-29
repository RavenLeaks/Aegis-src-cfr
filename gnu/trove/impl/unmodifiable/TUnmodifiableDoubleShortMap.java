/*
 * Decompiled with CFR <Could not determine version>.
 */
package gnu.trove.impl.unmodifiable;

import gnu.trove.TCollections;
import gnu.trove.TShortCollection;
import gnu.trove.function.TShortFunction;
import gnu.trove.impl.unmodifiable.TUnmodifiableDoubleShortMap;
import gnu.trove.iterator.TDoubleShortIterator;
import gnu.trove.map.TDoubleShortMap;
import gnu.trove.procedure.TDoubleProcedure;
import gnu.trove.procedure.TDoubleShortProcedure;
import gnu.trove.procedure.TShortProcedure;
import gnu.trove.set.TDoubleSet;
import java.io.Serializable;
import java.util.Map;

public class TUnmodifiableDoubleShortMap
implements TDoubleShortMap,
Serializable {
    private static final long serialVersionUID = -1034234728574286014L;
    private final TDoubleShortMap m;
    private transient TDoubleSet keySet = null;
    private transient TShortCollection values = null;

    public TUnmodifiableDoubleShortMap(TDoubleShortMap m) {
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
    public boolean containsKey(double key) {
        return this.m.containsKey((double)key);
    }

    @Override
    public boolean containsValue(short val) {
        return this.m.containsValue((short)val);
    }

    @Override
    public short get(double key) {
        return this.m.get((double)key);
    }

    @Override
    public short put(double key, short value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public short remove(double key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(TDoubleShortMap m) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(Map<? extends Double, ? extends Short> map) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public TDoubleSet keySet() {
        if (this.keySet != null) return this.keySet;
        this.keySet = TCollections.unmodifiableSet((TDoubleSet)this.m.keySet());
        return this.keySet;
    }

    @Override
    public double[] keys() {
        return this.m.keys();
    }

    @Override
    public double[] keys(double[] array) {
        return this.m.keys((double[])array);
    }

    @Override
    public TShortCollection valueCollection() {
        if (this.values != null) return this.values;
        this.values = TCollections.unmodifiableCollection((TShortCollection)this.m.valueCollection());
        return this.values;
    }

    @Override
    public short[] values() {
        return this.m.values();
    }

    @Override
    public short[] values(short[] array) {
        return this.m.values((short[])array);
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
    public double getNoEntryKey() {
        return this.m.getNoEntryKey();
    }

    @Override
    public short getNoEntryValue() {
        return this.m.getNoEntryValue();
    }

    @Override
    public boolean forEachKey(TDoubleProcedure procedure) {
        return this.m.forEachKey((TDoubleProcedure)procedure);
    }

    @Override
    public boolean forEachValue(TShortProcedure procedure) {
        return this.m.forEachValue((TShortProcedure)procedure);
    }

    @Override
    public boolean forEachEntry(TDoubleShortProcedure procedure) {
        return this.m.forEachEntry((TDoubleShortProcedure)procedure);
    }

    @Override
    public TDoubleShortIterator iterator() {
        return new TDoubleShortIterator((TUnmodifiableDoubleShortMap)this){
            TDoubleShortIterator iter;
            final /* synthetic */ TUnmodifiableDoubleShortMap this$0;
            {
                this.this$0 = this$0;
                this.iter = TUnmodifiableDoubleShortMap.access$000((TUnmodifiableDoubleShortMap)this.this$0).iterator();
            }

            public double key() {
                return this.iter.key();
            }

            public short value() {
                return this.iter.value();
            }

            public void advance() {
                this.iter.advance();
            }

            public boolean hasNext() {
                return this.iter.hasNext();
            }

            public short setValue(short val) {
                throw new UnsupportedOperationException();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public short putIfAbsent(double key, short value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void transformValues(TShortFunction function) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainEntries(TDoubleShortProcedure procedure) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean increment(double key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean adjustValue(double key, short amount) {
        throw new UnsupportedOperationException();
    }

    @Override
    public short adjustOrPutValue(double key, short adjust_amount, short put_amount) {
        throw new UnsupportedOperationException();
    }

    static /* synthetic */ TDoubleShortMap access$000(TUnmodifiableDoubleShortMap x0) {
        return x0.m;
    }
}

