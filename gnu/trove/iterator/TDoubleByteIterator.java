/*
 * Decompiled with CFR <Could not determine version>.
 */
package gnu.trove.iterator;

import gnu.trove.iterator.TAdvancingIterator;

public interface TDoubleByteIterator
extends TAdvancingIterator {
    public double key();

    public byte value();

    public byte setValue(byte var1);
}

