/*
 * Decompiled with CFR <Could not determine version>.
 */
package io.netty.util.internal.logging;

import io.netty.util.internal.logging.AbstractInternalLogger;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import io.netty.util.internal.logging.LocationAwareSlf4JLogger;
import io.netty.util.internal.logging.Slf4JLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.NOPLoggerFactory;
import org.slf4j.spi.LocationAwareLogger;

public class Slf4JLoggerFactory
extends InternalLoggerFactory {
    public static final InternalLoggerFactory INSTANCE = new Slf4JLoggerFactory();

    @Deprecated
    public Slf4JLoggerFactory() {
    }

    Slf4JLoggerFactory(boolean failIfNOP) {
        assert (failIfNOP);
        if (!(LoggerFactory.getILoggerFactory() instanceof NOPLoggerFactory)) return;
        throw new NoClassDefFoundError((String)"NOPLoggerFactory not supported");
    }

    @Override
    public InternalLogger newInstance(String name) {
        return Slf4JLoggerFactory.wrapLogger((Logger)LoggerFactory.getLogger((String)name));
    }

    static InternalLogger wrapLogger(Logger logger) {
        AbstractInternalLogger abstractInternalLogger;
        if (logger instanceof LocationAwareLogger) {
            abstractInternalLogger = new LocationAwareSlf4JLogger((LocationAwareLogger)((LocationAwareLogger)logger));
            return abstractInternalLogger;
        }
        abstractInternalLogger = new Slf4JLogger((Logger)logger);
        return abstractInternalLogger;
    }
}

