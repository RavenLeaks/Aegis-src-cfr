/*
 * Decompiled with CFR <Could not determine version>.
 */
package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.MapMaker;
import java.lang.reflect.Array;

@GwtCompatible(emulated=true)
final class Platform {
    static <T> T[] newArray(T[] reference, int length) {
        Class<?> type = reference.getClass().getComponentType();
        return (Object[])Array.newInstance(type, (int)length);
    }

    static MapMaker tryWeakKeys(MapMaker mapMaker) {
        return mapMaker.weakKeys();
    }

    private Platform() {
    }
}

