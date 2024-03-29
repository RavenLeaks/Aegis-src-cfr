/*
 * Decompiled with CFR <Could not determine version>.
 * 
 * Could not load the following classes:
 *  com.google.errorprone.annotations.CanIgnoreReturnValue
 *  javax.annotation.Nullable
 */
package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Ordering;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import javax.annotation.Nullable;

@GwtCompatible(emulated=true)
public abstract class FluentIterable<E>
implements Iterable<E> {
    private final Optional<Iterable<E>> iterableDelegate;

    protected FluentIterable() {
        this.iterableDelegate = Optional.absent();
    }

    FluentIterable(Iterable<E> iterable) {
        Preconditions.checkNotNull(iterable);
        this.iterableDelegate = Optional.fromNullable(this != iterable ? iterable : null);
    }

    private Iterable<E> getDelegate() {
        return (Iterable)this.iterableDelegate.or(this);
    }

    public static <E> FluentIterable<E> from(Iterable<E> iterable) {
        FluentIterable fluentIterable;
        if (iterable instanceof FluentIterable) {
            fluentIterable = (FluentIterable)iterable;
            return fluentIterable;
        }
        fluentIterable = new FluentIterable<E>(iterable, iterable){
            final /* synthetic */ Iterable val$iterable;
            {
                this.val$iterable = iterable;
                super(x0);
            }

            public Iterator<E> iterator() {
                return this.val$iterable.iterator();
            }
        };
        return fluentIterable;
    }

    @Beta
    public static <E> FluentIterable<E> from(E[] elements) {
        return FluentIterable.from(Arrays.asList(elements));
    }

    @Deprecated
    public static <E> FluentIterable<E> from(FluentIterable<E> iterable) {
        return Preconditions.checkNotNull(iterable);
    }

    @Beta
    public static <T> FluentIterable<T> concat(Iterable<? extends T> a, Iterable<? extends T> b) {
        return FluentIterable.concat(ImmutableList.of(a, b));
    }

    @Beta
    public static <T> FluentIterable<T> concat(Iterable<? extends T> a, Iterable<? extends T> b, Iterable<? extends T> c) {
        return FluentIterable.concat(ImmutableList.of(a, b, c));
    }

    @Beta
    public static <T> FluentIterable<T> concat(Iterable<? extends T> a, Iterable<? extends T> b, Iterable<? extends T> c, Iterable<? extends T> d) {
        return FluentIterable.concat(ImmutableList.of(a, b, c, d));
    }

    @Beta
    public static <T> FluentIterable<T> concat(Iterable<? extends T> ... inputs) {
        return FluentIterable.concat(ImmutableList.copyOf(inputs));
    }

    @Beta
    public static <T> FluentIterable<T> concat(Iterable<? extends Iterable<? extends T>> inputs) {
        Preconditions.checkNotNull(inputs);
        return new FluentIterable<T>(inputs){
            final /* synthetic */ Iterable val$inputs;
            {
                this.val$inputs = iterable;
            }

            public Iterator<T> iterator() {
                return com.google.common.collect.Iterators.concat(Iterables.transform(this.val$inputs, Iterables.<? extends T>toIterator()).iterator());
            }
        };
    }

    @Beta
    public static <E> FluentIterable<E> of() {
        return FluentIterable.from(ImmutableList.<E>of());
    }

    @Deprecated
    @Beta
    public static <E> FluentIterable<E> of(E[] elements) {
        return FluentIterable.from(Lists.newArrayList(elements));
    }

    @Beta
    public static <E> FluentIterable<E> of(@Nullable E element, E ... elements) {
        return FluentIterable.from(Lists.asList(element, elements));
    }

    public String toString() {
        return Iterables.toString(this.getDelegate());
    }

    public final int size() {
        return Iterables.size(this.getDelegate());
    }

    public final boolean contains(@Nullable Object target) {
        return Iterables.contains(this.getDelegate(), (Object)target);
    }

    public final FluentIterable<E> cycle() {
        return FluentIterable.from(Iterables.cycle(this.getDelegate()));
    }

    @Beta
    public final FluentIterable<E> append(Iterable<? extends E> other) {
        return FluentIterable.from(FluentIterable.concat(this.getDelegate(), other));
    }

    @Beta
    public final FluentIterable<E> append(E ... elements) {
        return FluentIterable.from(FluentIterable.concat(this.getDelegate(), Arrays.asList(elements)));
    }

    public final FluentIterable<E> filter(Predicate<? super E> predicate) {
        return FluentIterable.from(Iterables.filter(this.getDelegate(), predicate));
    }

    @GwtIncompatible
    public final <T> FluentIterable<T> filter(Class<T> type) {
        return FluentIterable.from(Iterables.filter(this.getDelegate(), type));
    }

    public final boolean anyMatch(Predicate<? super E> predicate) {
        return Iterables.any(this.getDelegate(), predicate);
    }

    public final boolean allMatch(Predicate<? super E> predicate) {
        return Iterables.all(this.getDelegate(), predicate);
    }

    public final Optional<E> firstMatch(Predicate<? super E> predicate) {
        return Iterables.tryFind(this.getDelegate(), predicate);
    }

    public final <T> FluentIterable<T> transform(Function<? super E, T> function) {
        return FluentIterable.from(Iterables.transform(this.getDelegate(), function));
    }

    public <T> FluentIterable<T> transformAndConcat(Function<? super E, ? extends Iterable<? extends T>> function) {
        return FluentIterable.from(FluentIterable.concat(this.transform(function)));
    }

    public final Optional<E> first() {
        Optional<Object> optional;
        Iterator<E> iterator = this.getDelegate().iterator();
        if (iterator.hasNext()) {
            optional = Optional.of(iterator.next());
            return optional;
        }
        optional = Optional.absent();
        return optional;
    }

    public final Optional<E> last() {
        E current;
        Iterable<E> iterable = this.getDelegate();
        if (iterable instanceof List) {
            List list = (List)iterable;
            if (!list.isEmpty()) return Optional.of(list.get((int)(list.size() - 1)));
            return Optional.absent();
        }
        Iterator<E> iterator = iterable.iterator();
        if (!iterator.hasNext()) {
            return Optional.absent();
        }
        if (iterable instanceof SortedSet) {
            SortedSet sortedSet = (SortedSet)iterable;
            return Optional.of(sortedSet.last());
        }
        do {
            current = iterator.next();
        } while (iterator.hasNext());
        return Optional.of(current);
    }

    public final FluentIterable<E> skip(int numberToSkip) {
        return FluentIterable.from(Iterables.skip(this.getDelegate(), (int)numberToSkip));
    }

    public final FluentIterable<E> limit(int maxSize) {
        return FluentIterable.from(Iterables.limit(this.getDelegate(), (int)maxSize));
    }

    public final boolean isEmpty() {
        if (this.getDelegate().iterator().hasNext()) return false;
        return true;
    }

    public final ImmutableList<E> toList() {
        return ImmutableList.copyOf(this.getDelegate());
    }

    public final ImmutableList<E> toSortedList(Comparator<? super E> comparator) {
        return Ordering.from(comparator).immutableSortedCopy(this.getDelegate());
    }

    public final ImmutableSet<E> toSet() {
        return ImmutableSet.copyOf(this.getDelegate());
    }

    public final ImmutableSortedSet<E> toSortedSet(Comparator<? super E> comparator) {
        return ImmutableSortedSet.copyOf(comparator, this.getDelegate());
    }

    public final ImmutableMultiset<E> toMultiset() {
        return ImmutableMultiset.copyOf(this.getDelegate());
    }

    public final <V> ImmutableMap<E, V> toMap(Function<? super E, V> valueFunction) {
        return Maps.toMap(this.getDelegate(), valueFunction);
    }

    public final <K> ImmutableListMultimap<K, E> index(Function<? super E, K> keyFunction) {
        return Multimaps.index(this.getDelegate(), keyFunction);
    }

    public final <K> ImmutableMap<K, E> uniqueIndex(Function<? super E, K> keyFunction) {
        return Maps.uniqueIndex(this.getDelegate(), keyFunction);
    }

    @GwtIncompatible
    public final E[] toArray(Class<E> type) {
        return Iterables.toArray(this.getDelegate(), type);
    }

    @CanIgnoreReturnValue
    public final <C extends Collection<? super E>> C copyInto(C collection) {
        Preconditions.checkNotNull(collection);
        Iterable<E> iterable = this.getDelegate();
        if (iterable instanceof Collection) {
            collection.addAll(Collections2.cast(iterable));
            return (C)((C)collection);
        }
        Iterator<E> i$ = iterable.iterator();
        while (i$.hasNext()) {
            E item = i$.next();
            collection.add(item);
        }
        return (C)collection;
    }

    @Beta
    public final String join(Joiner joiner) {
        return joiner.join(this);
    }

    public final E get(int position) {
        return (E)Iterables.get(this.getDelegate(), (int)position);
    }
}

