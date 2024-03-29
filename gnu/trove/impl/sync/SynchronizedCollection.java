/*
 * Decompiled with CFR <Could not determine version>.
 */
package gnu.trove.impl.sync;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

class SynchronizedCollection<E>
implements Collection<E>,
Serializable {
    private static final long serialVersionUID = 3053995032091335093L;
    final Collection<E> c;
    final Object mutex;

    SynchronizedCollection(Collection<E> c, Object mutex) {
        this.c = c;
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
        return this.c.size();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean isEmpty() {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return this.c.isEmpty();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean contains(Object o) {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return this.c.contains((Object)o);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public Object[] toArray() {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return this.c.toArray();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public <T> T[] toArray(T[] a) {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return this.c.toArray(a);
    }

    @Override
    public Iterator<E> iterator() {
        return this.c.iterator();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean add(E e) {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return this.c.add(e);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean remove(Object o) {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return this.c.remove((Object)o);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean containsAll(Collection<?> coll) {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return this.c.containsAll(coll);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean addAll(Collection<? extends E> coll) {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return this.c.addAll(coll);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean removeAll(Collection<?> coll) {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return this.c.removeAll(coll);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public boolean retainAll(Collection<?> coll) {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return this.c.retainAll(coll);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void clear() {
        Object object = this.mutex;
        // MONITORENTER : object
        this.c.clear();
        // MONITOREXIT : object
        return;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public String toString() {
        Object object = this.mutex;
        // MONITORENTER : object
        // MONITOREXIT : object
        return this.c.toString();
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

