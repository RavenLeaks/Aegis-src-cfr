/*
 * Decompiled with CFR <Could not determine version>.
 */
package io.netty.handler.codec;

import io.netty.handler.codec.ProtocolDetectionState;
import io.netty.util.internal.ObjectUtil;

public final class ProtocolDetectionResult<T> {
    private static final ProtocolDetectionResult NEEDS_MORE_DATE = new ProtocolDetectionResult<Object>((ProtocolDetectionState)ProtocolDetectionState.NEEDS_MORE_DATA, null);
    private static final ProtocolDetectionResult INVALID = new ProtocolDetectionResult<Object>((ProtocolDetectionState)ProtocolDetectionState.INVALID, null);
    private final ProtocolDetectionState state;
    private final T result;

    public static <T> ProtocolDetectionResult<T> needsMoreData() {
        return NEEDS_MORE_DATE;
    }

    public static <T> ProtocolDetectionResult<T> invalid() {
        return INVALID;
    }

    public static <T> ProtocolDetectionResult<T> detected(T protocol) {
        return new ProtocolDetectionResult<T>((ProtocolDetectionState)ProtocolDetectionState.DETECTED, ObjectUtil.checkNotNull(protocol, (String)"protocol"));
    }

    private ProtocolDetectionResult(ProtocolDetectionState state, T result) {
        this.state = state;
        this.result = result;
    }

    public ProtocolDetectionState state() {
        return this.state;
    }

    public T detectedProtocol() {
        return (T)this.result;
    }
}

