/*
 * Decompiled with CFR <Could not determine version>.
 */
package io.netty.util;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.Promise;

public interface AsyncMapping<IN, OUT> {
    public Future<OUT> map(IN var1, Promise<OUT> var2);
}

