/*
 * Decompiled with CFR <Could not determine version>.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.BoundType;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedMultiset;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Multiset;
import com.google.common.collect.SortedMultiset;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;
import javax.annotation.Nullable;

@GwtIncompatible
final class DescendingImmutableSortedMultiset<E>
extends ImmutableSortedMultiset<E> {
    private final transient ImmutableSortedMultiset<E> forward;

    DescendingImmutableSortedMultiset(ImmutableSortedMultiset<E> forward) {
        this.forward = forward;
    }

    @Override
    public int count(@Nullable Object element) {
        return this.forward.count((Object)element);
    }

    @Override
    public Multiset.Entry<E> firstEntry() {
        return this.forward.lastEntry();
    }

    @Override
    public Multiset.Entry<E> lastEntry() {
        return this.forward.firstEntry();
    }

    @Override
    public int size() {
        return this.forward.size();
    }

    @Override
    public ImmutableSortedSet<E> elementSet() {
        return ((ImmutableSortedSet)this.forward.elementSet()).descendingSet();
    }

    @Override
    Multiset.Entry<E> getEntry(int index) {
        return (Multiset.Entry)((ImmutableSet)this.forward.entrySet()).asList().reverse().get((int)index);
    }

    @Override
    public ImmutableSortedMultiset<E> descendingMultiset() {
        return this.forward;
    }

    @Override
    public ImmutableSortedMultiset<E> headMultiset(E upperBound, BoundType boundType) {
        return ((ImmutableSortedMultiset)this.forward.tailMultiset(upperBound, (BoundType)boundType)).descendingMultiset();
    }

    @Override
    public ImmutableSortedMultiset<E> tailMultiset(E lowerBound, BoundType boundType) {
        return ((ImmutableSortedMultiset)this.forward.headMultiset(lowerBound, (BoundType)boundType)).descendingMultiset();
    }

    @Override
    boolean isPartialView() {
        return this.forward.isPartialView();
    }
}

