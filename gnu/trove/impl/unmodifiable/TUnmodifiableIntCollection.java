/*
 * Decompiled with CFR <Could not determine version>.
 */
package gnu.trove.impl.unmodifiable;

import gnu.trove.TIntCollection;
import gnu.trove.impl.unmodifiable.TUnmodifiableIntCollection;
import gnu.trove.iterator.TIntIterator;
import gnu.trove.procedure.TIntProcedure;
import java.io.Serializable;
import java.util.Collection;

public class TUnmodifiableIntCollection
implements TIntCollection,
Serializable {
    private static final long serialVersionUID = 1820017752578914078L;
    final TIntCollection c;

    public TUnmodifiableIntCollection(TIntCollection c) {
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
    public boolean contains(int o) {
        return this.c.contains((int)o);
    }

    @Override
    public int[] toArray() {
        return this.c.toArray();
    }

    @Override
    public int[] toArray(int[] a) {
        return this.c.toArray((int[])a);
    }

    public String toString() {
        return this.c.toString();
    }

    @Override
    public int getNoEntryValue() {
        return this.c.getNoEntryValue();
    }

    @Override
    public boolean forEach(TIntProcedure procedure) {
        return this.c.forEach((TIntProcedure)procedure);
    }

    @Override
    public TIntIterator iterator() {
        return new TIntIterator((TUnmodifiableIntCollection)this){
            TIntIterator i;
            final /* synthetic */ TUnmodifiableIntCollection this$0;
            {
                this.this$0 = this$0;
                this.i = this.this$0.c.iterator();
            }

            public boolean hasNext() {
                return this.i.hasNext();
            }

            public int next() {
                return this.i.next();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public boolean add(int e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(int o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> coll) {
        return this.c.containsAll(coll);
    }

    @Override
    public boolean containsAll(TIntCollection coll) {
        return this.c.containsAll((TIntCollection)coll);
    }

    @Override
    public boolean containsAll(int[] array) {
        return this.c.containsAll((int[])array);
    }

    @Override
    public boolean addAll(TIntCollection coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends Integer> coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int[] array) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(TIntCollection coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(int[] array) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(TIntCollection coll) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(int[] array) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }
}

