/*
 * Decompiled with CFR <Could not determine version>.
 */
package gnu.trove.iterator;

import gnu.trove.iterator.TAdvancingIterator;

public interface TCharByteIterator
extends TAdvancingIterator {
    public char key();

    public byte value();

    public byte setValue(byte var1);
}

