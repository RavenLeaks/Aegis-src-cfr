/*
 * Decompiled with CFR <Could not determine version>.
 * 
 * Could not load the following classes:
 *  org.apache.log4j.Level
 *  org.apache.log4j.Logger
 *  org.apache.log4j.Priority
 */
package io.netty.util.internal.logging;

import io.netty.util.internal.logging.AbstractInternalLogger;
import io.netty.util.internal.logging.FormattingTuple;
import io.netty.util.internal.logging.MessageFormatter;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

class Log4JLogger
extends AbstractInternalLogger {
    private static final long serialVersionUID = 2851357342488183058L;
    final transient Logger logger;
    static final String FQCN = Log4JLogger.class.getName();
    final boolean traceCapable;

    Log4JLogger(Logger logger) {
        super((String)logger.getName());
        this.logger = logger;
        this.traceCapable = this.isTraceCapable();
    }

    private boolean isTraceCapable() {
        try {
            this.logger.isTraceEnabled();
            return true;
        }
        catch (NoSuchMethodError ignored) {
            return false;
        }
    }

    @Override
    public boolean isTraceEnabled() {
        if (!this.traceCapable) return this.logger.isDebugEnabled();
        return this.logger.isTraceEnabled();
    }

    @Override
    public void trace(String msg) {
        this.logger.log((String)FQCN, (Priority)(this.traceCapable ? Level.TRACE : Level.DEBUG), (Object)msg, null);
    }

    @Override
    public void trace(String format, Object arg) {
        if (!this.isTraceEnabled()) return;
        FormattingTuple ft = MessageFormatter.format((String)format, (Object)arg);
        this.logger.log((String)FQCN, (Priority)(this.traceCapable ? Level.TRACE : Level.DEBUG), (Object)ft.getMessage(), (Throwable)ft.getThrowable());
    }

    @Override
    public void trace(String format, Object argA, Object argB) {
        if (!this.isTraceEnabled()) return;
        FormattingTuple ft = MessageFormatter.format((String)format, (Object)argA, (Object)argB);
        this.logger.log((String)FQCN, (Priority)(this.traceCapable ? Level.TRACE : Level.DEBUG), (Object)ft.getMessage(), (Throwable)ft.getThrowable());
    }

    @Override
    public void trace(String format, Object ... arguments) {
        if (!this.isTraceEnabled()) return;
        FormattingTuple ft = MessageFormatter.arrayFormat((String)format, (Object[])arguments);
        this.logger.log((String)FQCN, (Priority)(this.traceCapable ? Level.TRACE : Level.DEBUG), (Object)ft.getMessage(), (Throwable)ft.getThrowable());
    }

    @Override
    public void trace(String msg, Throwable t) {
        this.logger.log((String)FQCN, (Priority)(this.traceCapable ? Level.TRACE : Level.DEBUG), (Object)msg, (Throwable)t);
    }

    @Override
    public boolean isDebugEnabled() {
        return this.logger.isDebugEnabled();
    }

    @Override
    public void debug(String msg) {
        this.logger.log((String)FQCN, (Priority)Level.DEBUG, (Object)msg, null);
    }

    @Override
    public void debug(String format, Object arg) {
        if (!this.logger.isDebugEnabled()) return;
        FormattingTuple ft = MessageFormatter.format((String)format, (Object)arg);
        this.logger.log((String)FQCN, (Priority)Level.DEBUG, (Object)ft.getMessage(), (Throwable)ft.getThrowable());
    }

    @Override
    public void debug(String format, Object argA, Object argB) {
        if (!this.logger.isDebugEnabled()) return;
        FormattingTuple ft = MessageFormatter.format((String)format, (Object)argA, (Object)argB);
        this.logger.log((String)FQCN, (Priority)Level.DEBUG, (Object)ft.getMessage(), (Throwable)ft.getThrowable());
    }

    @Override
    public void debug(String format, Object ... arguments) {
        if (!this.logger.isDebugEnabled()) return;
        FormattingTuple ft = MessageFormatter.arrayFormat((String)format, (Object[])arguments);
        this.logger.log((String)FQCN, (Priority)Level.DEBUG, (Object)ft.getMessage(), (Throwable)ft.getThrowable());
    }

    @Override
    public void debug(String msg, Throwable t) {
        this.logger.log((String)FQCN, (Priority)Level.DEBUG, (Object)msg, (Throwable)t);
    }

    @Override
    public boolean isInfoEnabled() {
        return this.logger.isInfoEnabled();
    }

    @Override
    public void info(String msg) {
        this.logger.log((String)FQCN, (Priority)Level.INFO, (Object)msg, null);
    }

    @Override
    public void info(String format, Object arg) {
        if (!this.logger.isInfoEnabled()) return;
        FormattingTuple ft = MessageFormatter.format((String)format, (Object)arg);
        this.logger.log((String)FQCN, (Priority)Level.INFO, (Object)ft.getMessage(), (Throwable)ft.getThrowable());
    }

    @Override
    public void info(String format, Object argA, Object argB) {
        if (!this.logger.isInfoEnabled()) return;
        FormattingTuple ft = MessageFormatter.format((String)format, (Object)argA, (Object)argB);
        this.logger.log((String)FQCN, (Priority)Level.INFO, (Object)ft.getMessage(), (Throwable)ft.getThrowable());
    }

    @Override
    public void info(String format, Object ... argArray) {
        if (!this.logger.isInfoEnabled()) return;
        FormattingTuple ft = MessageFormatter.arrayFormat((String)format, (Object[])argArray);
        this.logger.log((String)FQCN, (Priority)Level.INFO, (Object)ft.getMessage(), (Throwable)ft.getThrowable());
    }

    @Override
    public void info(String msg, Throwable t) {
        this.logger.log((String)FQCN, (Priority)Level.INFO, (Object)msg, (Throwable)t);
    }

    @Override
    public boolean isWarnEnabled() {
        return this.logger.isEnabledFor((Priority)Level.WARN);
    }

    @Override
    public void warn(String msg) {
        this.logger.log((String)FQCN, (Priority)Level.WARN, (Object)msg, null);
    }

    @Override
    public void warn(String format, Object arg) {
        if (!this.logger.isEnabledFor((Priority)Level.WARN)) return;
        FormattingTuple ft = MessageFormatter.format((String)format, (Object)arg);
        this.logger.log((String)FQCN, (Priority)Level.WARN, (Object)ft.getMessage(), (Throwable)ft.getThrowable());
    }

    @Override
    public void warn(String format, Object argA, Object argB) {
        if (!this.logger.isEnabledFor((Priority)Level.WARN)) return;
        FormattingTuple ft = MessageFormatter.format((String)format, (Object)argA, (Object)argB);
        this.logger.log((String)FQCN, (Priority)Level.WARN, (Object)ft.getMessage(), (Throwable)ft.getThrowable());
    }

    @Override
    public void warn(String format, Object ... argArray) {
        if (!this.logger.isEnabledFor((Priority)Level.WARN)) return;
        FormattingTuple ft = MessageFormatter.arrayFormat((String)format, (Object[])argArray);
        this.logger.log((String)FQCN, (Priority)Level.WARN, (Object)ft.getMessage(), (Throwable)ft.getThrowable());
    }

    @Override
    public void warn(String msg, Throwable t) {
        this.logger.log((String)FQCN, (Priority)Level.WARN, (Object)msg, (Throwable)t);
    }

    @Override
    public boolean isErrorEnabled() {
        return this.logger.isEnabledFor((Priority)Level.ERROR);
    }

    @Override
    public void error(String msg) {
        this.logger.log((String)FQCN, (Priority)Level.ERROR, (Object)msg, null);
    }

    @Override
    public void error(String format, Object arg) {
        if (!this.logger.isEnabledFor((Priority)Level.ERROR)) return;
        FormattingTuple ft = MessageFormatter.format((String)format, (Object)arg);
        this.logger.log((String)FQCN, (Priority)Level.ERROR, (Object)ft.getMessage(), (Throwable)ft.getThrowable());
    }

    @Override
    public void error(String format, Object argA, Object argB) {
        if (!this.logger.isEnabledFor((Priority)Level.ERROR)) return;
        FormattingTuple ft = MessageFormatter.format((String)format, (Object)argA, (Object)argB);
        this.logger.log((String)FQCN, (Priority)Level.ERROR, (Object)ft.getMessage(), (Throwable)ft.getThrowable());
    }

    @Override
    public void error(String format, Object ... argArray) {
        if (!this.logger.isEnabledFor((Priority)Level.ERROR)) return;
        FormattingTuple ft = MessageFormatter.arrayFormat((String)format, (Object[])argArray);
        this.logger.log((String)FQCN, (Priority)Level.ERROR, (Object)ft.getMessage(), (Throwable)ft.getThrowable());
    }

    @Override
    public void error(String msg, Throwable t) {
        this.logger.log((String)FQCN, (Priority)Level.ERROR, (Object)msg, (Throwable)t);
    }
}

