/*
 * Decompiled with CFR <Could not determine version>.
 */
package io.netty.util.internal;

import io.netty.util.internal.PlatformDependent0;
import java.lang.reflect.AccessibleObject;

public final class ReflectionUtil {
    private ReflectionUtil() {
    }

    public static Throwable trySetAccessible(AccessibleObject object, boolean checkAccessible) {
        if (checkAccessible && !PlatformDependent0.isExplicitTryReflectionSetAccessible()) {
            return new UnsupportedOperationException((String)"Reflective setAccessible(true) disabled");
        }
        try {
            object.setAccessible((boolean)true);
            return null;
        }
        catch (SecurityException e) {
            return e;
        }
        catch (RuntimeException e) {
            return ReflectionUtil.handleInaccessibleObjectException((RuntimeException)e);
        }
    }

    private static RuntimeException handleInaccessibleObjectException(RuntimeException e) {
        if (!"java.lang.reflect.InaccessibleObjectException".equals((Object)e.getClass().getName())) throw e;
        return e;
    }
}

