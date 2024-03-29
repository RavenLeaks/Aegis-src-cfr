/*
 * Decompiled with CFR <Could not determine version>.
 */
package gnu.trove.impl.unmodifiable;

import gnu.trove.TShortCollection;
import gnu.trove.impl.unmodifiable.TUnmodifiableShortCollection;
import gnu.trove.iterator.TShortIterator;
import gnu.trove.procedure.TShortProcedure;
import java.io.Serializable;
import java.util.Collection;

public class TUnmodifiableShortCollection
implements TShortCollection,
Serializable {
    private static final long serialVersionUID = 1820017752578914078L;
    final TShortCollection c;

    public TUnmodifiableShortCollection(TShortCollection c) {
        if (c == null) {
            throw new NullPointerException();
        }
        this.c = c;
    }

    @Override
    public int size() {
        return this.c.size();
    }

    @Override
    public boolean isEmpty() {
        return this.c.isEmpty();
    }

    @Override
    public boolean contains(short o) {
        return this.c.contains((short)o);
    }

    @Override
    public short[] toArray() {
        return this.c.toArray();
    }

    @Override
    public short[] toArray(short[] a) {
        return this.c.toArray((short[])a);
    }

    public String toString() {
        return this.c.toString();
    }

    @Override
    public short getNoEntryValue() {
        return this.c.getNoEntryValue();
    }

    @Override
    public boolean forEach(TShortProcedure procedure) {
        return this.c.forEach((TShortProcedure)procedure);
    }

    @Override
    public TShortIterator iterator() {
        return new TShortIterator((TUnmodifiableShortCollection)this){
            TShortIterator i;
            final /* synthetic */ TUnmodifiableShortCollection this$0;
            {
                this.this$0 = this$0;
                this.i = this.this$0.c.iterator();
            }

            public boolean hasNext() {
                return this.i.hasNext();
            }

            public short next() {
                return this.i.next();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public boolean add(short e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(short o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> coll) {
        return this.c.containsAll(coll);
    }

    @Override
    public boolean containsAll(TShortCollection coll) {
        return this.c.containsAll((TShortCollection)coll);
    }

    @Override
    public boolean containsAll(short[] array) {
        return this.c.containsAll((short[])array);
    }

    @Override
    public boolean addAll(TShortCollection coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends Short> coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(short[] array) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(TShortCollection coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(short[] array) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(TShortCollection coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(short[] array) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }
}

