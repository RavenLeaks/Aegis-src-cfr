/*
 * Decompiled with CFR <Could not determine version>.
 */
package gnu.trove.impl.hash;

import gnu.trove.impl.Constants;
import gnu.trove.impl.HashFunctions;
import gnu.trove.impl.hash.TPrimitiveHash;
import gnu.trove.procedure.TFloatProcedure;
import java.util.Arrays;

public abstract class TFloatHash
extends TPrimitiveHash {
    static final long serialVersionUID = 1L;
    public transient float[] _set;
    protected float no_entry_value;
    protected boolean consumeFreeSlot;

    public TFloatHash() {
        this.no_entry_value = Constants.DEFAULT_FLOAT_NO_ENTRY_VALUE;
        if (this.no_entry_value == 0.0f) return;
        Arrays.fill((float[])this._set, (float)this.no_entry_value);
    }

    public TFloatHash(int initialCapacity) {
        super((int)initialCapacity);
        this.no_entry_value = Constants.DEFAULT_FLOAT_NO_ENTRY_VALUE;
        if (this.no_entry_value == 0.0f) return;
        Arrays.fill((float[])this._set, (float)this.no_entry_value);
    }

    public TFloatHash(int initialCapacity, float loadFactor) {
        super((int)initialCapacity, (float)loadFactor);
        this.no_entry_value = Constants.DEFAULT_FLOAT_NO_ENTRY_VALUE;
        if (this.no_entry_value == 0.0f) return;
        Arrays.fill((float[])this._set, (float)this.no_entry_value);
    }

    public TFloatHash(int initialCapacity, float loadFactor, float no_entry_value) {
        super((int)initialCapacity, (float)loadFactor);
        this.no_entry_value = no_entry_value;
        if (no_entry_value == 0.0f) return;
        Arrays.fill((float[])this._set, (float)no_entry_value);
    }

    public float getNoEntryValue() {
        return this.no_entry_value;
    }

    @Override
    protected int setUp(int initialCapacity) {
        int capacity = super.setUp((int)initialCapacity);
        this._set = new float[capacity];
        return capacity;
    }

    public boolean contains(float val) {
        if (this.index((float)val) < 0) return false;
        return true;
    }

    public boolean forEach(TFloatProcedure procedure) {
        byte[] states = this._states;
        float[] set = this._set;
        int i = set.length;
        do {
            if (i-- <= 0) return true;
        } while (states[i] != 1 || procedure.execute((float)set[i]));
        return false;
    }

    @Override
    protected void removeAt(int index) {
        this._set[index] = this.no_entry_value;
        super.removeAt((int)index);
    }

    protected int index(float val) {
        byte[] states = this._states;
        float[] set = this._set;
        int length = states.length;
        int hash = HashFunctions.hash((float)val) & Integer.MAX_VALUE;
        int index = hash % length;
        byte state = states[index];
        if (state == 0) {
            return -1;
        }
        if (state != 1) return this.indexRehashed((float)val, (int)index, (int)hash, (byte)state);
        if (set[index] != val) return this.indexRehashed((float)val, (int)index, (int)hash, (byte)state);
        return index;
    }

    int indexRehashed(float key, int index, int hash, byte state) {
        int length = this._set.length;
        int probe = 1 + hash % (length - 2);
        int loopIndex = index;
        do {
            if ((index -= probe) < 0) {
                index += length;
            }
            if ((state = this._states[index]) == 0) {
                return -1;
            }
            if (key != this._set[index] || state == 2) continue;
            return index;
        } while (index != loopIndex);
        return -1;
    }

    protected int insertKey(float val) {
        int hash = HashFunctions.hash((float)val) & Integer.MAX_VALUE;
        int index = hash % this._states.length;
        byte state = this._states[index];
        this.consumeFreeSlot = false;
        if (state == 0) {
            this.consumeFreeSlot = true;
            this.insertKeyAt((int)index, (float)val);
            return index;
        }
        if (state != 1) return this.insertKeyRehash((float)val, (int)index, (int)hash, (byte)state);
        if (this._set[index] != val) return this.insertKeyRehash((float)val, (int)index, (int)hash, (byte)state);
        return -index - 1;
    }

    int insertKeyRehash(float val, int index, int hash, byte state) {
        int length = this._set.length;
        int probe = 1 + hash % (length - 2);
        int loopIndex = index;
        int firstRemoved = -1;
        do {
            if (state == 2 && firstRemoved == -1) {
                firstRemoved = index;
            }
            if ((index -= probe) < 0) {
                index += length;
            }
            if ((state = this._states[index]) == 0) {
                if (firstRemoved != -1) {
                    this.insertKeyAt((int)firstRemoved, (float)val);
                    return firstRemoved;
                }
                this.consumeFreeSlot = true;
                this.insertKeyAt((int)index, (float)val);
                return index;
            }
            if (state != 1 || this._set[index] != val) continue;
            return -index - 1;
        } while (index != loopIndex);
        if (firstRemoved == -1) throw new IllegalStateException((String)"No free or removed slots available. Key set full?!!");
        this.insertKeyAt((int)firstRemoved, (float)val);
        return firstRemoved;
    }

    void insertKeyAt(int index, float val) {
        this._set[index] = val;
        this._states[index] = 1;
    }
}

