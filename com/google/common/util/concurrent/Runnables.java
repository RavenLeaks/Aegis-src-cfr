/*
 * Decompiled with CFR <Could not determine version>.
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.util.concurrent.Runnables;

@Beta
@GwtCompatible
public final class Runnables {
    private static final Runnable EMPTY_RUNNABLE = new Runnable(){

        public void run() {
        }
    };

    public static Runnable doNothing() {
        return EMPTY_RUNNABLE;
    }

    private Runnables() {
    }
}

