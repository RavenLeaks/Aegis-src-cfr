/*
 * Decompiled with CFR <Could not determine version>.
 */
package io.netty.handler.codec.http.websocketx;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.ContinuationWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.stream.ChunkedInput;
import io.netty.util.internal.ObjectUtil;

public final class WebSocketChunkedInput
implements ChunkedInput<WebSocketFrame> {
    private final ChunkedInput<ByteBuf> input;
    private final int rsv;

    public WebSocketChunkedInput(ChunkedInput<ByteBuf> input) {
        this(input, (int)0);
    }

    public WebSocketChunkedInput(ChunkedInput<ByteBuf> input, int rsv) {
        this.input = ObjectUtil.checkNotNull(input, (String)"input");
        this.rsv = rsv;
    }

    @Override
    public boolean isEndOfInput() throws Exception {
        return this.input.isEndOfInput();
    }

    @Override
    public void close() throws Exception {
        this.input.close();
    }

    @Deprecated
    @Override
    public WebSocketFrame readChunk(ChannelHandlerContext ctx) throws Exception {
        return this.readChunk((ByteBufAllocator)ctx.alloc());
    }

    @Override
    public WebSocketFrame readChunk(ByteBufAllocator allocator) throws Exception {
        ByteBuf buf = this.input.readChunk((ByteBufAllocator)allocator);
        if (buf != null) return new ContinuationWebSocketFrame((boolean)this.input.isEndOfInput(), (int)this.rsv, (ByteBuf)buf);
        return null;
    }

    @Override
    public long length() {
        return this.input.length();
    }

    @Override
    public long progress() {
        return this.input.progress();
    }
}

