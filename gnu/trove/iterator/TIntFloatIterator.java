/*
 * Decompiled with CFR <Could not determine version>.
 */
package gnu.trove.iterator;

import gnu.trove.iterator.TAdvancingIterator;

public interface TIntFloatIterator
extends TAdvancingIterator {
    public int key();

    public float value();

    public float setValue(float var1);
}

