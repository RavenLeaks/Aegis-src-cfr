/*
 * Decompiled with CFR <Could not determine version>.
 */
package gnu.trove.stack;

public interface TIntStack {
    public int getNoEntryValue();

    public void push(int var1);

    public int pop();

    public int peek();

    public int size();

    public void clear();

    public int[] toArray();

    public void toArray(int[] var1);
}

