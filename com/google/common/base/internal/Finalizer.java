/*
 * Decompiled with CFR <Could not determine version>.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package com.google.common.base.internal;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

public class Finalizer
implements Runnable {
    private static final Logger logger = Logger.getLogger((String)Finalizer.class.getName());
    private static final String FINALIZABLE_REFERENCE = "com.google.common.base.FinalizableReference";
    private final WeakReference<Class<?>> finalizableReferenceClassReference;
    private final PhantomReference<Object> frqReference;
    private final ReferenceQueue<Object> queue;
    private static final Field inheritableThreadLocals = Finalizer.getInheritableThreadLocalsField();

    public static void startFinalizer(Class<?> finalizableReferenceClass, ReferenceQueue<Object> queue, PhantomReference<Object> frqReference) {
        if (!finalizableReferenceClass.getName().equals((Object)FINALIZABLE_REFERENCE)) {
            throw new IllegalArgumentException((String)"Expected com.google.common.base.FinalizableReference.");
        }
        Finalizer finalizer = new Finalizer(finalizableReferenceClass, queue, frqReference);
        Thread thread = new Thread((Runnable)finalizer);
        thread.setName((String)Finalizer.class.getName());
        thread.setDaemon((boolean)true);
        try {
            if (inheritableThreadLocals != null) {
                inheritableThreadLocals.set((Object)thread, null);
            }
        }
        catch (Throwable t) {
            logger.log((Level)Level.INFO, (String)"Failed to clear thread local values inherited by reference finalizer thread.", (Throwable)t);
        }
        thread.start();
    }

    private Finalizer(Class<?> finalizableReferenceClass, ReferenceQueue<Object> queue, PhantomReference<Object> frqReference) {
        this.queue = queue;
        this.finalizableReferenceClassReference = new WeakReference<Class<?>>(finalizableReferenceClass);
        this.frqReference = frqReference;
    }

    @Override
    public void run() {
        do {
            try {
                while (this.cleanUp(this.queue.remove())) {
                }
                return;
            }
            catch (InterruptedException e) {
                continue;
            }
            break;
        } while (true);
    }

    private boolean cleanUp(Reference<?> reference) {
        Method finalizeReferentMethod = this.getFinalizeReferentMethod();
        if (finalizeReferentMethod == null) {
            return false;
        }
        do {
            reference.clear();
            if (reference == this.frqReference) {
                return false;
            }
            try {
                finalizeReferentMethod.invoke(reference, (Object[])new Object[0]);
            }
            catch (Throwable t) {
                logger.log((Level)Level.SEVERE, (String)"Error cleaning up after reference.", (Throwable)t);
            }
        } while ((reference = this.queue.poll()) != null);
        return true;
    }

    @Nullable
    private Method getFinalizeReferentMethod() {
        Class finalizableReferenceClass = (Class)this.finalizableReferenceClassReference.get();
        if (finalizableReferenceClass == null) {
            return null;
        }
        try {
            return finalizableReferenceClass.getMethod((String)"finalizeReferent", new Class[0]);
        }
        catch (NoSuchMethodException e) {
            throw new AssertionError((Object)e);
        }
    }

    @Nullable
    public static Field getInheritableThreadLocalsField() {
        try {
            Field inheritableThreadLocals = Thread.class.getDeclaredField((String)"inheritableThreadLocals");
            inheritableThreadLocals.setAccessible((boolean)true);
            return inheritableThreadLocals;
        }
        catch (Throwable t) {
            logger.log((Level)Level.INFO, (String)"Couldn't access Thread.inheritableThreadLocals. Reference finalizer threads will inherit thread local values.");
            return null;
        }
    }
}

