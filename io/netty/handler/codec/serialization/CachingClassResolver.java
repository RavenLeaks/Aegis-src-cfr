/*
 * Decompiled with CFR <Could not determine version>.
 */
package io.netty.handler.codec.serialization;

import io.netty.handler.codec.serialization.ClassResolver;
import java.util.Map;

class CachingClassResolver
implements ClassResolver {
    private final Map<String, Class<?>> classCache;
    private final ClassResolver delegate;

    CachingClassResolver(ClassResolver delegate, Map<String, Class<?>> classCache) {
        this.delegate = delegate;
        this.classCache = classCache;
    }

    @Override
    public Class<?> resolve(String className) throws ClassNotFoundException {
        Class<?> clazz = this.classCache.get((Object)className);
        if (clazz != null) {
            return clazz;
        }
        clazz = this.delegate.resolve((String)className);
        this.classCache.put((String)className, clazz);
        return clazz;
    }
}

