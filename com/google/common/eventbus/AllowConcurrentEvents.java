/*
 * Decompiled with CFR <Could not determine version>.
 */
package com.google.common.eventbus;

import com.google.common.annotations.Beta;
import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.METHOD})
@Beta
public @interface AllowConcurrentEvents {
}

