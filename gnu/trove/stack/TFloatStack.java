/*
 * Decompiled with CFR <Could not determine version>.
 */
package gnu.trove.stack;

public interface TFloatStack {
    public float getNoEntryValue();

    public void push(float var1);

    public float pop();

    public float peek();

    public int size();

    public void clear();

    public float[] toArray();

    public void toArray(float[] var1);
}

