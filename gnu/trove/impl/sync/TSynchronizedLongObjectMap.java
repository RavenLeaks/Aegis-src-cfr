/*
 * Decompiled with CFR <Could not determine version>.
 */
package gnu.trove.impl.sync;

import gnu.trove.function.TObjectFunction;
import gnu.trove.impl.sync.SynchronizedCollection;
import gnu.trove.impl.sync.TSynchronizedLongSet;
import gnu.trove.iterator.TLongObjectIterator;
import gnu.trove.map.TLongObjectMap;
import gnu.trove.procedure.TLongObjectProcedure;
import gnu.trove.procedure.TLongProcedure;
import gnu.trove.procedure.TObjectProcedure;
import gnu.trove.set.TLongSet;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

public class TSynchronizedLongObjectMap<V>
implements TLongObjectMap<V>,
Serializable {
    private static final long serialVersionUID = 1978198479659022715L;
    private final TLongObjectMap<V> m;
    final Object mutex;
    private transient TLongSet keySet = null;
    private transient Collection<V> values = null;

    public TSynchronizedLongObjectMap(TLongObjectMap<V> m) {
        if (m == null) {
            throw new NullPointerException();
        }
        this.m = m;
        this.mutex = this;
    }

    public TSynchronizedLongObjectMap(TLongObjectMap<V> m, Object mutex) {
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
    public boolean containsValue(Object value) {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return this.m.containsValue((Object)value);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public V get(long key) {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return (V)this.m.get((long)key);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public V put(long key, V value) {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return (V)this.m.put((long)key, value);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public V remove(long key) {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return (V)this.m.remove((long)key);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void putAll(Map<? extends Long, ? extends V> map) {
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
    public void putAll(TLongObjectMap<? extends V> map) {
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
    public Collection<V> valueCollection() {
        Object object = this.mutex;
        // MONITORENTER : object
        if (this.values == null) {
            this.values = new SynchronizedCollection<V>(this.m.valueCollection(), (Object)this.mutex);
        }
        // MONITOREXIT : object
        return this.values;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Object[] values() {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return this.m.values();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public V[] values(V[] array) {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return this.m.values(array);
    }

    @Override
    public TLongObjectIterator<V> iterator() {
        return this.m.iterator();
    }

    @Override
    public long getNoEntryKey() {
        return this.m.getNoEntryKey();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public V putIfAbsent(long key, V value) {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return (V)this.m.putIfAbsent((long)key, value);
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
    public boolean forEachValue(TObjectProcedure<? super V> procedure) {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return this.m.forEachValue(procedure);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean forEachEntry(TLongObjectProcedure<? super V> procedure) {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return this.m.forEachEntry(procedure);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void transformValues(TObjectFunction<V, V> function) {
        Object object = this.mutex;
        // MONITORENTER : object
        this.m.transformValues(function);
        // MONITOREXIT : object
        return;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean retainEntries(TLongObjectProcedure<? super V> procedure) {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return this.m.retainEntries(procedure);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean equals(Object o) {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return this.m.equals((Object)o);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
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

