/*
 * Decompiled with CFR <Could not determine version>.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import javax.annotation.Nullable;

@GwtCompatible
final class Count
implements Serializable {
    private int value;

    Count(int value) {
        this.value = value;
    }

    public int get() {
        return this.value;
    }

    public void add(int delta) {
        this.value += delta;
    }

    public int addAndGet(int delta) {
        return this.value += delta;
    }

    public void set(int newValue) {
        this.value = newValue;
    }

    public int getAndSet(int newValue) {
        int result = this.value;
        this.value = newValue;
        return result;
    }

    public int hashCode() {
        return this.value;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Count)) return false;
        if (((Count)obj).value != this.value) return false;
        return true;
    }

    public String toString() {
        return Integer.toString((int)this.value);
    }
}

