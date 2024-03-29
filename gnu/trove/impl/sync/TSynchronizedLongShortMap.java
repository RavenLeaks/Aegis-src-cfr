/*
 * Decompiled with CFR <Could not determine version>.
 */
package gnu.trove.impl.sync;

import gnu.trove.TShortCollection;
import gnu.trove.function.TShortFunction;
import gnu.trove.impl.sync.TSynchronizedLongSet;
import gnu.trove.impl.sync.TSynchronizedShortCollection;
import gnu.trove.iterator.TLongShortIterator;
import gnu.trove.map.TLongShortMap;
import gnu.trove.procedure.TLongProcedure;
import gnu.trove.procedure.TLongShortProcedure;
import gnu.trove.procedure.TShortProcedure;
import gnu.trove.set.TLongSet;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;

public class TSynchronizedLongShortMap
implements TLongShortMap,
Serializable {
    private static final long serialVersionUID = 1978198479659022715L;
    private final TLongShortMap m;
    final Object mutex;
    private transient TLongSet keySet = null;
    private transient TShortCollection values = null;

    public TSynchronizedLongShortMap(TLongShortMap m) {
        if (m == null) {
            throw new NullPointerException();
        }
        this.m = m;
        this.mutex = this;
    }

    public TSynchronizedLongShortMap(TLongShortMap m, Object mutex) {
        this.m = m;
        this.mutex = mutex;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public int size() {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return this.m.size();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isEmpty() {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return this.m.isEmpty();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean containsKey(long key) {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return this.m.containsKey((long)key);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean containsValue(short value) {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return this.m.containsValue((short)value);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public short get(long key) {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return this.m.get((long)key);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public short put(long key, short value) {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return this.m.put((long)key, (short)value);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public short remove(long key) {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return this.m.remove((long)key);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void putAll(Map<? extends Long, ? extends Short> map) {
        Object object = this.mutex;
        // MONITORENTER : object
        this.m.putAll(map);
        // MONITOREXIT : object
        return;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void putAll(TLongShortMap map) {
        Object object = this.mutex;
        // MONITORENTER : object
        this.m.putAll((TLongShortMap)map);
        // MONITOREXIT : object
        return;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void clear() {
        Object object = this.mutex;
        // MONITORENTER : object
        this.m.clear();
        // MONITOREXIT : object
        return;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public TLongSet keySet() {
        Object object = this.mutex;
        // MONITORENTER : object
        if (this.keySet == null) {
            this.keySet = new TSynchronizedLongSet((TLongSet)this.m.keySet(), (Object)this.mutex);
        }
        // MONITOREXIT : object
        return this.keySet;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long[] keys() {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return this.m.keys();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public long[] keys(long[] array) {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return this.m.keys((long[])array);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public TShortCollection valueCollection() {
        Object object = this.mutex;
        // MONITORENTER : object
        if (this.values == null) {
            this.values = new TSynchronizedShortCollection((TShortCollection)this.m.valueCollection(), (Object)this.mutex);
        }
        // MONITOREXIT : object
        return this.values;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public short[] values() {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return this.m.values();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public short[] values(short[] array) {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return this.m.values((short[])array);
    }

    @Override
    public TLongShortIterator iterator() {
        return this.m.iterator();
    }

    @Override
    public long getNoEntryKey() {
        return this.m.getNoEntryKey();
    }

    @Override
    public short getNoEntryValue() {
        return this.m.getNoEntryValue();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public short putIfAbsent(long key, short value) {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return this.m.putIfAbsent((long)key, (short)value);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean forEachKey(TLongProcedure procedure) {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return this.m.forEachKey((TLongProcedure)procedure);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean forEachValue(TShortProcedure procedure) {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return this.m.forEachValue((TShortProcedure)procedure);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean forEachEntry(TLongShortProcedure procedure) {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return this.m.forEachEntry((TLongShortProcedure)procedure);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void transformValues(TShortFunction function) {
        Object object = this.mutex;
        // MONITORENTER : object
        this.m.transformValues((TShortFunction)function);
        // MONITOREXIT : object
        return;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean retainEntries(TLongShortProcedure procedure) {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return this.m.retainEntries((TLongShortProcedure)procedure);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean increment(long key) {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return this.m.increment((long)key);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean adjustValue(long key, short amount) {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return this.m.adjustValue((long)key, (short)amount);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public short adjustOrPutValue(long key, short adjust_amount, short put_amount) {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return this.m.adjustOrPutValue((long)key, (short)adjust_amount, (short)put_amount);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean equals(Object o) {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return this.m.equals((Object)o);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public int hashCode() {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return this.m.hashCode();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public String toString() {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return this.m.toString();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void writeObject(ObjectOutputStream s) throws IOException {
        Object object = this.mutex;
        // MONITORENTER : object
        s.defaultWriteObject();
        // MONITOREXIT : object
        return;
    }
}

