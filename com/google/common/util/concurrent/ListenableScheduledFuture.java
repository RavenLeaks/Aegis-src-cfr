/*
 * Decompiled with CFR <Could not determine version>.
 */
package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ScheduledFuture;

@Beta
@GwtIncompatible
public interface ListenableScheduledFuture<V>
extends ScheduledFuture<V>,
ListenableFuture<V> {
}

