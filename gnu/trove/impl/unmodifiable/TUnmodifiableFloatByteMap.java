/*
 * Decompiled with CFR <Could not determine version>.
 */
package gnu.trove.impl.unmodifiable;

import gnu.trove.TByteCollection;
import gnu.trove.TCollections;
import gnu.trove.function.TByteFunction;
import gnu.trove.impl.unmodifiable.TUnmodifiableFloatByteMap;
import gnu.trove.iterator.TFloatByteIterator;
import gnu.trove.map.TFloatByteMap;
import gnu.trove.procedure.TByteProcedure;
import gnu.trove.procedure.TFloatByteProcedure;
import gnu.trove.procedure.TFloatProcedure;
import gnu.trove.set.TFloatSet;
import java.io.Serializable;
import java.util.Map;

public class TUnmodifiableFloatByteMap
implements TFloatByteMap,
Serializable {
    private static final long serialVersionUID = -1034234728574286014L;
    private final TFloatByteMap m;
    private transient TFloatSet keySet = null;
    private transient TByteCollection values = null;

    public TUnmodifiableFloatByteMap(TFloatByteMap m) {
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
    public boolean containsKey(float key) {
        return this.m.containsKey((float)key);
    }

    @Override
    public boolean containsValue(byte val) {
        return this.m.containsValue((byte)val);
    }

    @Override
    public byte get(float key) {
        return this.m.get((float)key);
    }

    @Override
    public byte put(float key, byte value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte remove(float key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(TFloatByteMap m) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(Map<? extends Float, ? extends Byte> map) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public TFloatSet keySet() {
        if (this.keySet != null) return this.keySet;
        this.keySet = TCollections.unmodifiableSet((TFloatSet)this.m.keySet());
        return this.keySet;
    }

    @Override
    public float[] keys() {
        return this.m.keys();
    }

    @Override
    public float[] keys(float[] array) {
        return this.m.keys((float[])array);
    }

    @Override
    public TByteCollection valueCollection() {
        if (this.values != null) return this.values;
        this.values = TCollections.unmodifiableCollection((TByteCollection)this.m.valueCollection());
        return this.values;
    }

    @Override
    public byte[] values() {
        return this.m.values();
    }

    @Override
    public byte[] values(byte[] array) {
        return this.m.values((byte[])array);
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
    public float getNoEntryKey() {
        return this.m.getNoEntryKey();
    }

    @Override
    public byte getNoEntryValue() {
        return this.m.getNoEntryValue();
    }

    @Override
    public boolean forEachKey(TFloatProcedure procedure) {
        return this.m.forEachKey((TFloatProcedure)procedure);
    }

    @Override
    public boolean forEachValue(TByteProcedure procedure) {
        return this.m.forEachValue((TByteProcedure)procedure);
    }

    @Override
    public boolean forEachEntry(TFloatByteProcedure procedure) {
        return this.m.forEachEntry((TFloatByteProcedure)procedure);
    }

    @Override
    public TFloatByteIterator iterator() {
        return new TFloatByteIterator((TUnmodifiableFloatByteMap)this){
            TFloatByteIterator iter;
            final /* synthetic */ TUnmodifiableFloatByteMap this$0;
            {
                this.this$0 = this$0;
                this.iter = TUnmodifiableFloatByteMap.access$000((TUnmodifiableFloatByteMap)this.this$0).iterator();
            }

            public float key() {
                return this.iter.key();
            }

            public byte value() {
                return this.iter.value();
            }

            public void advance() {
                this.iter.advance();
            }

            public boolean hasNext() {
                return this.iter.hasNext();
            }

            public byte setValue(byte val) {
                throw new UnsupportedOperationException();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public byte putIfAbsent(float key, byte value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void transformValues(TByteFunction function) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainEntries(TFloatByteProcedure procedure) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean increment(float key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean adjustValue(float key, byte amount) {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte adjustOrPutValue(float key, byte adjust_amount, byte put_amount) {
        throw new UnsupportedOperationException();
    }

    static /* synthetic */ TFloatByteMap access$000(TUnmodifiableFloatByteMap x0) {
        return x0.m;
    }
}

