/*
 * Decompiled with CFR <Could not determine version>.
 */
package io.netty.buffer;

import io.netty.buffer.AbstractByteBuf;
import io.netty.buffer.AbstractReferenceCountedByteBuf;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PoolArena;
import io.netty.buffer.PoolChunk;
import io.netty.buffer.PoolThreadCache;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.PooledDuplicatedByteBuf;
import io.netty.buffer.PooledSlicedByteBuf;
import io.netty.util.Recycler;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;

abstract class PooledByteBuf<T>
extends AbstractReferenceCountedByteBuf {
    private final Recycler.Handle<PooledByteBuf<T>> recyclerHandle;
    protected PoolChunk<T> chunk;
    protected long handle;
    protected T memory;
    protected int offset;
    protected int length;
    int maxLength;
    PoolThreadCache cache;
    ByteBuffer tmpNioBuf;
    private ByteBufAllocator allocator;

    protected PooledByteBuf(Recycler.Handle<? extends PooledByteBuf<T>> recyclerHandle, int maxCapacity) {
        super((int)maxCapacity);
        this.recyclerHandle = recyclerHandle;
    }

    void init(PoolChunk<T> chunk, ByteBuffer nioBuffer, long handle, int offset, int length, int maxLength, PoolThreadCache cache) {
        this.init0(chunk, (ByteBuffer)nioBuffer, (long)handle, (int)offset, (int)length, (int)maxLength, (PoolThreadCache)cache);
    }

    void initUnpooled(PoolChunk<T> chunk, int length) {
        this.init0(chunk, null, (long)0L, (int)chunk.offset, (int)length, (int)length, null);
    }

    private void init0(PoolChunk<T> chunk, ByteBuffer nioBuffer, long handle, int offset, int length, int maxLength, PoolThreadCache cache) {
        assert (handle >= 0L);
        assert (chunk != null);
        this.chunk = chunk;
        this.memory = chunk.memory;
        this.tmpNioBuf = nioBuffer;
        this.allocator = chunk.arena.parent;
        this.cache = cache;
        this.handle = handle;
        this.offset = offset;
        this.length = length;
        this.maxLength = maxLength;
    }

    final void reuse(int maxCapacity) {
        this.maxCapacity((int)maxCapacity);
        this.resetRefCnt();
        this.setIndex0((int)0, (int)0);
        this.discardMarks();
    }

    @Override
    public final int capacity() {
        return this.length;
    }

    @Override
    public int maxFastWritableBytes() {
        return Math.min((int)this.maxLength, (int)this.maxCapacity()) - this.writerIndex;
    }

    @Override
    public final ByteBuf capacity(int newCapacity) {
        if (newCapacity == this.length) {
            this.ensureAccessible();
            return this;
        }
        this.checkNewCapacity((int)newCapacity);
        if (!this.chunk.unpooled) {
            if (newCapacity > this.length) {
                if (newCapacity <= this.maxLength) {
                    this.length = newCapacity;
                    return this;
                }
            } else if (newCapacity > this.maxLength >>> 1 && (this.maxLength > 512 || newCapacity > this.maxLength - 16)) {
                this.length = newCapacity;
                this.trimIndicesToCapacity((int)newCapacity);
                return this;
            }
        }
        this.chunk.arena.reallocate(this, (int)newCapacity, (boolean)true);
        return this;
    }

    @Override
    public final ByteBufAllocator alloc() {
        return this.allocator;
    }

    @Override
    public final ByteOrder order() {
        return ByteOrder.BIG_ENDIAN;
    }

    @Override
    public final ByteBuf unwrap() {
        return null;
    }

    @Override
    public final ByteBuf retainedDuplicate() {
        return PooledDuplicatedByteBuf.newInstance((AbstractByteBuf)this, (ByteBuf)this, (int)this.readerIndex(), (int)this.writerIndex());
    }

    @Override
    public final ByteBuf retainedSlice() {
        int index = this.readerIndex();
        return this.retainedSlice((int)index, (int)(this.writerIndex() - index));
    }

    @Override
    public final ByteBuf retainedSlice(int index, int length) {
        return PooledSlicedByteBuf.newInstance((AbstractByteBuf)this, (ByteBuf)this, (int)index, (int)length);
    }

    protected final ByteBuffer internalNioBuffer() {
        ByteBuffer tmpNioBuf = this.tmpNioBuf;
        if (tmpNioBuf != null) return tmpNioBuf;
        this.tmpNioBuf = tmpNioBuf = this.newInternalNioBuffer(this.memory);
        return tmpNioBuf;
    }

    protected abstract ByteBuffer newInternalNioBuffer(T var1);

    @Override
    protected final void deallocate() {
        if (this.handle < 0L) return;
        long handle = this.handle;
        this.handle = -1L;
        this.memory = null;
        this.chunk.arena.free(this.chunk, (ByteBuffer)this.tmpNioBuf, (long)handle, (int)this.maxLength, (PoolThreadCache)this.cache);
        this.tmpNioBuf = null;
        this.chunk = null;
        this.recycle();
    }

    private void recycle() {
        this.recyclerHandle.recycle(this);
    }

    protected final int idx(int index) {
        return this.offset + index;
    }

    final ByteBuffer _internalNioBuffer(int index, int length, boolean duplicate) {
        index = this.idx((int)index);
        ByteBuffer buffer = duplicate ? this.newInternalNioBuffer(this.memory) : this.internalNioBuffer();
        buffer.limit((int)(index + length)).position((int)index);
        return buffer;
    }

    ByteBuffer duplicateInternalNioBuffer(int index, int length) {
        this.checkIndex((int)index, (int)length);
        return this._internalNioBuffer((int)index, (int)length, (boolean)true);
    }

    @Override
    public final ByteBuffer internalNioBuffer(int index, int length) {
        this.checkIndex((int)index, (int)length);
        return this._internalNioBuffer((int)index, (int)length, (boolean)false);
    }

    @Override
    public final int nioBufferCount() {
        return 1;
    }

    @Override
    public final ByteBuffer nioBuffer(int index, int length) {
        return this.duplicateInternalNioBuffer((int)index, (int)length).slice();
    }

    @Override
    public final ByteBuffer[] nioBuffers(int index, int length) {
        return new ByteBuffer[]{this.nioBuffer((int)index, (int)length)};
    }

    @Override
    public final int getBytes(int index, GatheringByteChannel out, int length) throws IOException {
        return out.write((ByteBuffer)this.duplicateInternalNioBuffer((int)index, (int)length));
    }

    @Override
    public final int readBytes(GatheringByteChannel out, int length) throws IOException {
        this.checkReadableBytes((int)length);
        int readBytes = out.write((ByteBuffer)this._internalNioBuffer((int)this.readerIndex, (int)length, (boolean)false));
        this.readerIndex += readBytes;
        return readBytes;
    }

    @Override
    public final int getBytes(int index, FileChannel out, long position, int length) throws IOException {
        return out.write((ByteBuffer)this.duplicateInternalNioBuffer((int)index, (int)length), (long)position);
    }

    @Override
    public final int readBytes(FileChannel out, long position, int length) throws IOException {
        this.checkReadableBytes((int)length);
        int readBytes = out.write((ByteBuffer)this._internalNioBuffer((int)this.readerIndex, (int)length, (boolean)false), (long)position);
        this.readerIndex += readBytes;
        return readBytes;
    }

    @Override
    public final int setBytes(int index, ScatteringByteChannel in, int length) throws IOException {
        try {
            return in.read((ByteBuffer)this.internalNioBuffer((int)index, (int)length));
        }
        catch (ClosedChannelException ignored) {
            return -1;
        }
    }

    @Override
    public final int setBytes(int index, FileChannel in, long position, int length) throws IOException {
        try {
            return in.read((ByteBuffer)this.internalNioBuffer((int)index, (int)length), (long)position);
        }
        catch (ClosedChannelException ignored) {
            return -1;
        }
    }
}

