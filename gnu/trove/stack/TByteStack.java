/*
 * Decompiled with CFR <Could not determine version>.
 */
package gnu.trove.stack;

public interface TByteStack {
    public byte getNoEntryValue();

    public void push(byte var1);

    public byte pop();

    public byte peek();

    public int size();

    public void clear();

    public byte[] toArray();

    public void toArray(byte[] var1);
}

