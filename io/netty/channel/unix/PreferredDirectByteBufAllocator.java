/*
 * Decompiled with CFR <Could not determine version>.
 */
package io.netty.channel.unix;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;

public final class PreferredDirectByteBufAllocator
implements ByteBufAllocator {
    private ByteBufAllocator allocator;

    public void updateAllocator(ByteBufAllocator allocator) {
        this.allocator = allocator;
    }

    @Override
    public ByteBuf buffer() {
        return this.allocator.directBuffer();
    }

    @Override
    public ByteBuf buffer(int initialCapacity) {
        return this.allocator.directBuffer((int)initialCapacity);
    }

    @Override
    public ByteBuf buffer(int initialCapacity, int maxCapacity) {
        return this.allocator.directBuffer((int)initialCapacity, (int)maxCapacity);
    }

    @Override
    public ByteBuf ioBuffer() {
        return this.allocator.directBuffer();
    }

    @Override
    public ByteBuf ioBuffer(int initialCapacity) {
        return this.allocator.directBuffer((int)initialCapacity);
    }

    @Override
    public ByteBuf ioBuffer(int initialCapacity, int maxCapacity) {
        return this.allocator.directBuffer((int)initialCapacity, (int)maxCapacity);
    }

    @Override
    public ByteBuf heapBuffer() {
        return this.allocator.heapBuffer();
    }

    @Override
    public ByteBuf heapBuffer(int initialCapacity) {
        return this.allocator.heapBuffer((int)initialCapacity);
    }

    @Override
    public ByteBuf heapBuffer(int initialCapacity, int maxCapacity) {
        return this.allocator.heapBuffer((int)initialCapacity, (int)maxCapacity);
    }

    @Override
    public ByteBuf directBuffer() {
        return this.allocator.directBuffer();
    }

    @Override
    public ByteBuf directBuffer(int initialCapacity) {
        return this.allocator.directBuffer((int)initialCapacity);
    }

    @Override
    public ByteBuf directBuffer(int initialCapacity, int maxCapacity) {
        return this.allocator.directBuffer((int)initialCapacity, (int)maxCapacity);
    }

    @Override
    public CompositeByteBuf compositeBuffer() {
        return this.allocator.compositeDirectBuffer();
    }

    @Override
    public CompositeByteBuf compositeBuffer(int maxNumComponents) {
        return this.allocator.compositeDirectBuffer((int)maxNumComponents);
    }

    @Override
    public CompositeByteBuf compositeHeapBuffer() {
        return this.allocator.compositeHeapBuffer();
    }

    @Override
    public CompositeByteBuf compositeHeapBuffer(int maxNumComponents) {
        return this.allocator.compositeHeapBuffer((int)maxNumComponents);
    }

    @Override
    public CompositeByteBuf compositeDirectBuffer() {
        return this.allocator.compositeDirectBuffer();
    }

    @Override
    public CompositeByteBuf compositeDirectBuffer(int maxNumComponents) {
        return this.allocator.compositeDirectBuffer((int)maxNumComponents);
    }

    @Override
    public boolean isDirectBufferPooled() {
        return this.allocator.isDirectBufferPooled();
    }

    @Override
    public int calculateNewCapacity(int minNewCapacity, int maxCapacity) {
        return this.allocator.calculateNewCapacity((int)minNewCapacity, (int)maxCapacity);
    }
}

