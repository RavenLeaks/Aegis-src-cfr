/*
 * Decompiled with CFR <Could not determine version>.
 */
package io.netty.handler.codec.http.websocketx;

import io.netty.handler.codec.http.websocketx.WebSocketDecoderConfig;

public final class WebSocketDecoderConfig {
    private final int maxFramePayloadLength;
    private final boolean expectMaskedFrames;
    private final boolean allowMaskMismatch;
    private final boolean allowExtensions;
    private final boolean closeOnProtocolViolation;
    private final boolean withUTF8Validator;

    private WebSocketDecoderConfig(int maxFramePayloadLength, boolean expectMaskedFrames, boolean allowMaskMismatch, boolean allowExtensions, boolean closeOnProtocolViolation, boolean withUTF8Validator) {
        this.maxFramePayloadLength = maxFramePayloadLength;
        this.expectMaskedFrames = expectMaskedFrames;
        this.allowMaskMismatch = allowMaskMismatch;
        this.allowExtensions = allowExtensions;
        this.closeOnProtocolViolation = closeOnProtocolViolation;
        this.withUTF8Validator = withUTF8Validator;
    }

    public int maxFramePayloadLength() {
        return this.maxFramePayloadLength;
    }

    public boolean expectMaskedFrames() {
        return this.expectMaskedFrames;
    }

    public boolean allowMaskMismatch() {
        return this.allowMaskMismatch;
    }

    public boolean allowExtensions() {
        return this.allowExtensions;
    }

    public boolean closeOnProtocolViolation() {
        return this.closeOnProtocolViolation;
    }

    public boolean withUTF8Validator() {
        return this.withUTF8Validator;
    }

    public String toString() {
        return "WebSocketDecoderConfig [maxFramePayloadLength=" + this.maxFramePayloadLength + ", expectMaskedFrames=" + this.expectMaskedFrames + ", allowMaskMismatch=" + this.allowMaskMismatch + ", allowExtensions=" + this.allowExtensions + ", closeOnProtocolViolation=" + this.closeOnProtocolViolation + ", withUTF8Validator=" + this.withUTF8Validator + "]";
    }

    public Builder toBuilder() {
        return new Builder((WebSocketDecoderConfig)this, null);
    }

    public static Builder newBuilder() {
        return new Builder(null);
    }
}

