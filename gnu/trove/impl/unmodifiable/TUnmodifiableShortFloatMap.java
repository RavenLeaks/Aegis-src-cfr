/*
 * Decompiled with CFR <Could not determine version>.
 */
package gnu.trove.impl.unmodifiable;

import gnu.trove.TCollections;
import gnu.trove.TFloatCollection;
import gnu.trove.function.TFloatFunction;
import gnu.trove.impl.unmodifiable.TUnmodifiableShortFloatMap;
import gnu.trove.iterator.TShortFloatIterator;
import gnu.trove.map.TShortFloatMap;
import gnu.trove.procedure.TFloatProcedure;
import gnu.trove.procedure.TShortFloatProcedure;
import gnu.trove.procedure.TShortProcedure;
import gnu.trove.set.TShortSet;
import java.io.Serializable;
import java.util.Map;

public class TUnmodifiableShortFloatMap
implements TShortFloatMap,
Serializable {
    private static final long serialVersionUID = -1034234728574286014L;
    private final TShortFloatMap m;
    private transient TShortSet keySet = null;
    private transient TFloatCollection values = null;

    public TUnmodifiableShortFloatMap(TShortFloatMap m) {
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
    public boolean containsKey(short key) {
        return this.m.containsKey((short)key);
    }

    @Override
    public boolean containsValue(float val) {
        return this.m.containsValue((float)val);
    }

    @Override
    public float get(short key) {
        return this.m.get((short)key);
    }

    @Override
    public float put(short key, float value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public float remove(short key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(TShortFloatMap m) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(Map<? extends Short, ? extends Float> map) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public TShortSet keySet() {
        if (this.keySet != null) return this.keySet;
        this.keySet = TCollections.unmodifiableSet((TShortSet)this.m.keySet());
        return this.keySet;
    }

    @Override
    public short[] keys() {
        return this.m.keys();
    }

    @Override
    public short[] keys(short[] array) {
        return this.m.keys((short[])array);
    }

    @Override
    public TFloatCollection valueCollection() {
        if (this.values != null) return this.values;
        this.values = TCollections.unmodifiableCollection((TFloatCollection)this.m.valueCollection());
        return this.values;
    }

    @Override
    public float[] values() {
        return this.m.values();
    }

    @Override
    public float[] values(float[] array) {
        return this.m.values((float[])array);
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
    public short getNoEntryKey() {
        return this.m.getNoEntryKey();
    }

    @Override
    public float getNoEntryValue() {
        return this.m.getNoEntryValue();
    }

    @Override
    public boolean forEachKey(TShortProcedure procedure) {
        return this.m.forEachKey((TShortProcedure)procedure);
    }

    @Override
    public boolean forEachValue(TFloatProcedure procedure) {
        return this.m.forEachValue((TFloatProcedure)procedure);
    }

    @Override
    public boolean forEachEntry(TShortFloatProcedure procedure) {
        return this.m.forEachEntry((TShortFloatProcedure)procedure);
    }

    @Override
    public TShortFloatIterator iterator() {
        return new TShortFloatIterator((TUnmodifiableShortFloatMap)this){
            TShortFloatIterator iter;
            final /* synthetic */ TUnmodifiableShortFloatMap this$0;
            {
                this.this$0 = this$0;
                this.iter = TUnmodifiableShortFloatMap.access$000((TUnmodifiableShortFloatMap)this.this$0).iterator();
            }

            public short key() {
                return this.iter.key();
            }

            public float value() {
                return this.iter.value();
            }

            public void advance() {
                this.iter.advance();
            }

            public boolean hasNext() {
                return this.iter.hasNext();
            }

            public float setValue(float val) {
                throw new UnsupportedOperationException();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public float putIfAbsent(short key, float value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void transformValues(TFloatFunction function) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainEntries(TShortFloatProcedure procedure) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean increment(short key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean adjustValue(short key, float amount) {
        throw new UnsupportedOperationException();
    }

    @Override
    public float adjustOrPutValue(short key, float adjust_amount, float put_amount) {
        throw new UnsupportedOperationException();
    }

    static /* synthetic */ TShortFloatMap access$000(TUnmodifiableShortFloatMap x0) {
        return x0.m;
    }
}

