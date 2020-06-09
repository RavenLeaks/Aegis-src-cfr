/*
 * Decompiled with CFR <Could not determine version>.
 */
package io.netty.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.SimpleLeakAwareByteBuf;
import io.netty.util.ByteProcessor;
import io.netty.util.ReferenceCounted;
import io.netty.util.ResourceLeakDetector;
import io.netty.util.ResourceLeakTracker;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;

final class AdvancedLeakAwareByteBuf
extends SimpleLeakAwareByteBuf {
    private static final String PROP_ACQUIRE_AND_RELEASE_ONLY = "io.netty.leakDetection.acquireAndReleaseOnly";
    private static final boolean ACQUIRE_AND_RELEASE_ONLY;
    private static final InternalLogger logger;

    AdvancedLeakAwareByteBuf(ByteBuf buf, ResourceLeakTracker<ByteBuf> leak) {
        super((ByteBuf)buf, leak);
    }

    AdvancedLeakAwareByteBuf(ByteBuf wrapped, ByteBuf trackedByteBuf, ResourceLeakTracker<ByteBuf> leak) {
        super((ByteBuf)wrapped, (ByteBuf)trackedByteBuf, leak);
    }

    static void recordLeakNonRefCountingOperation(ResourceLeakTracker<ByteBuf> leak) {
        if (ACQUIRE_AND_RELEASE_ONLY) return;
        leak.record();
    }

    @Override
    public ByteBuf order(ByteOrder endianness) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.order((ByteOrder)endianness);
    }

    @Override
    public ByteBuf slice() {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.slice();
    }

    @Override
    public ByteBuf slice(int index, int length) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.slice((int)index, (int)length);
    }

    @Override
    public ByteBuf retainedSlice() {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.retainedSlice();
    }

    @Override
    public ByteBuf retainedSlice(int index, int length) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.retainedSlice((int)index, (int)length);
    }

    @Override
    public ByteBuf retainedDuplicate() {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.retainedDuplicate();
    }

    @Override
    public ByteBuf readRetainedSlice(int length) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.readRetainedSlice((int)length);
    }

    @Override
    public ByteBuf duplicate() {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.duplicate();
    }

    @Override
    public ByteBuf readSlice(int length) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.readSlice((int)length);
    }

    @Override
    public ByteBuf discardReadBytes() {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.discardReadBytes();
    }

    @Override
    public ByteBuf discardSomeReadBytes() {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.discardSomeReadBytes();
    }

    @Override
    public ByteBuf ensureWritable(int minWritableBytes) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.ensureWritable((int)minWritableBytes);
    }

    @Override
    public int ensureWritable(int minWritableBytes, boolean force) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.ensureWritable((int)minWritableBytes, (boolean)force);
    }

    @Override
    public boolean getBoolean(int index) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.getBoolean((int)index);
    }

    @Override
    public byte getByte(int index) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.getByte((int)index);
    }

    @Override
    public short getUnsignedByte(int index) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.getUnsignedByte((int)index);
    }

    @Override
    public short getShort(int index) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.getShort((int)index);
    }

    @Override
    public int getUnsignedShort(int index) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.getUnsignedShort((int)index);
    }

    @Override
    public int getMedium(int index) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.getMedium((int)index);
    }

    @Override
    public int getUnsignedMedium(int index) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.getUnsignedMedium((int)index);
    }

    @Override
    public int getInt(int index) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.getInt((int)index);
    }

    @Override
    public long getUnsignedInt(int index) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.getUnsignedInt((int)index);
    }

    @Override
    public long getLong(int index) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.getLong((int)index);
    }

    @Override
    public char getChar(int index) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.getChar((int)index);
    }

    @Override
    public float getFloat(int index) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.getFloat((int)index);
    }

    @Override
    public double getDouble(int index) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.getDouble((int)index);
    }

    @Override
    public ByteBuf getBytes(int index, ByteBuf dst) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.getBytes((int)index, (ByteBuf)dst);
    }

    @Override
    public ByteBuf getBytes(int index, ByteBuf dst, int length) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.getBytes((int)index, (ByteBuf)dst, (int)length);
    }

    @Override
    public ByteBuf getBytes(int index, ByteBuf dst, int dstIndex, int length) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.getBytes((int)index, (ByteBuf)dst, (int)dstIndex, (int)length);
    }

    @Override
    public ByteBuf getBytes(int index, byte[] dst) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.getBytes((int)index, (byte[])dst);
    }

    @Override
    public ByteBuf getBytes(int index, byte[] dst, int dstIndex, int length) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.getBytes((int)index, (byte[])dst, (int)dstIndex, (int)length);
    }

    @Override
    public ByteBuf getBytes(int index, ByteBuffer dst) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.getBytes((int)index, (ByteBuffer)dst);
    }

    @Override
    public ByteBuf getBytes(int index, OutputStream out, int length) throws IOException {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.getBytes((int)index, (OutputStream)out, (int)length);
    }

    @Override
    public int getBytes(int index, GatheringByteChannel out, int length) throws IOException {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.getBytes((int)index, (GatheringByteChannel)out, (int)length);
    }

    @Override
    public CharSequence getCharSequence(int index, int length, Charset charset) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.getCharSequence((int)index, (int)length, (Charset)charset);
    }

    @Override
    public ByteBuf setBoolean(int index, boolean value) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.setBoolean((int)index, (boolean)value);
    }

    @Override
    public ByteBuf setByte(int index, int value) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.setByte((int)index, (int)value);
    }

    @Override
    public ByteBuf setShort(int index, int value) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.setShort((int)index, (int)value);
    }

    @Override
    public ByteBuf setMedium(int index, int value) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.setMedium((int)index, (int)value);
    }

    @Override
    public ByteBuf setInt(int index, int value) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.setInt((int)index, (int)value);
    }

    @Override
    public ByteBuf setLong(int index, long value) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.setLong((int)index, (long)value);
    }

    @Override
    public ByteBuf setChar(int index, int value) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.setChar((int)index, (int)value);
    }

    @Override
    public ByteBuf setFloat(int index, float value) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.setFloat((int)index, (float)value);
    }

    @Override
    public ByteBuf setDouble(int index, double value) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.setDouble((int)index, (double)value);
    }

    @Override
    public ByteBuf setBytes(int index, ByteBuf src) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.setBytes((int)index, (ByteBuf)src);
    }

    @Override
    public ByteBuf setBytes(int index, ByteBuf src, int length) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.setBytes((int)index, (ByteBuf)src, (int)length);
    }

    @Override
    public ByteBuf setBytes(int index, ByteBuf src, int srcIndex, int length) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.setBytes((int)index, (ByteBuf)src, (int)srcIndex, (int)length);
    }

    @Override
    public ByteBuf setBytes(int index, byte[] src) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.setBytes((int)index, (byte[])src);
    }

    @Override
    public ByteBuf setBytes(int index, byte[] src, int srcIndex, int length) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.setBytes((int)index, (byte[])src, (int)srcIndex, (int)length);
    }

    @Override
    public ByteBuf setBytes(int index, ByteBuffer src) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.setBytes((int)index, (ByteBuffer)src);
    }

    @Override
    public int setBytes(int index, InputStream in, int length) throws IOException {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.setBytes((int)index, (InputStream)in, (int)length);
    }

    @Override
    public int setBytes(int index, ScatteringByteChannel in, int length) throws IOException {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.setBytes((int)index, (ScatteringByteChannel)in, (int)length);
    }

    @Override
    public ByteBuf setZero(int index, int length) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.setZero((int)index, (int)length);
    }

    @Override
    public int setCharSequence(int index, CharSequence sequence, Charset charset) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.setCharSequence((int)index, (CharSequence)sequence, (Charset)charset);
    }

    @Override
    public boolean readBoolean() {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.readBoolean();
    }

    @Override
    public byte readByte() {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.readByte();
    }

    @Override
    public short readUnsignedByte() {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.readUnsignedByte();
    }

    @Override
    public short readShort() {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.readShort();
    }

    @Override
    public int readUnsignedShort() {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.readUnsignedShort();
    }

    @Override
    public int readMedium() {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.readMedium();
    }

    @Override
    public int readUnsignedMedium() {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.readUnsignedMedium();
    }

    @Override
    public int readInt() {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.readInt();
    }

    @Override
    public long readUnsignedInt() {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.readUnsignedInt();
    }

    @Override
    public long readLong() {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.readLong();
    }

    @Override
    public char readChar() {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.readChar();
    }

    @Override
    public float readFloat() {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.readFloat();
    }

    @Override
    public double readDouble() {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.readDouble();
    }

    @Override
    public ByteBuf readBytes(int length) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.readBytes((int)length);
    }

    @Override
    public ByteBuf readBytes(ByteBuf dst) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.readBytes((ByteBuf)dst);
    }

    @Override
    public ByteBuf readBytes(ByteBuf dst, int length) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.readBytes((ByteBuf)dst, (int)length);
    }

    @Override
    public ByteBuf readBytes(ByteBuf dst, int dstIndex, int length) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.readBytes((ByteBuf)dst, (int)dstIndex, (int)length);
    }

    @Override
    public ByteBuf readBytes(byte[] dst) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.readBytes((byte[])dst);
    }

    @Override
    public ByteBuf readBytes(byte[] dst, int dstIndex, int length) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.readBytes((byte[])dst, (int)dstIndex, (int)length);
    }

    @Override
    public ByteBuf readBytes(ByteBuffer dst) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.readBytes((ByteBuffer)dst);
    }

    @Override
    public ByteBuf readBytes(OutputStream out, int length) throws IOException {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.readBytes((OutputStream)out, (int)length);
    }

    @Override
    public int readBytes(GatheringByteChannel out, int length) throws IOException {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.readBytes((GatheringByteChannel)out, (int)length);
    }

    @Override
    public CharSequence readCharSequence(int length, Charset charset) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.readCharSequence((int)length, (Charset)charset);
    }

    @Override
    public ByteBuf skipBytes(int length) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.skipBytes((int)length);
    }

    @Override
    public ByteBuf writeBoolean(boolean value) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.writeBoolean((boolean)value);
    }

    @Override
    public ByteBuf writeByte(int value) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.writeByte((int)value);
    }

    @Override
    public ByteBuf writeShort(int value) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.writeShort((int)value);
    }

    @Override
    public ByteBuf writeMedium(int value) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.writeMedium((int)value);
    }

    @Override
    public ByteBuf writeInt(int value) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.writeInt((int)value);
    }

    @Override
    public ByteBuf writeLong(long value) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.writeLong((long)value);
    }

    @Override
    public ByteBuf writeChar(int value) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.writeChar((int)value);
    }

    @Override
    public ByteBuf writeFloat(float value) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.writeFloat((float)value);
    }

    @Override
    public ByteBuf writeDouble(double value) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.writeDouble((double)value);
    }

    @Override
    public ByteBuf writeBytes(ByteBuf src) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.writeBytes((ByteBuf)src);
    }

    @Override
    public ByteBuf writeBytes(ByteBuf src, int length) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.writeBytes((ByteBuf)src, (int)length);
    }

    @Override
    public ByteBuf writeBytes(ByteBuf src, int srcIndex, int length) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.writeBytes((ByteBuf)src, (int)srcIndex, (int)length);
    }

    @Override
    public ByteBuf writeBytes(byte[] src) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.writeBytes((byte[])src);
    }

    @Override
    public ByteBuf writeBytes(byte[] src, int srcIndex, int length) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.writeBytes((byte[])src, (int)srcIndex, (int)length);
    }

    @Override
    public ByteBuf writeBytes(ByteBuffer src) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.writeBytes((ByteBuffer)src);
    }

    @Override
    public int writeBytes(InputStream in, int length) throws IOException {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.writeBytes((InputStream)in, (int)length);
    }

    @Override
    public int writeBytes(ScatteringByteChannel in, int length) throws IOException {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.writeBytes((ScatteringByteChannel)in, (int)length);
    }

    @Override
    public ByteBuf writeZero(int length) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.writeZero((int)length);
    }

    @Override
    public int indexOf(int fromIndex, int toIndex, byte value) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.indexOf((int)fromIndex, (int)toIndex, (byte)value);
    }

    @Override
    public int bytesBefore(byte value) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.bytesBefore((byte)value);
    }

    @Override
    public int bytesBefore(int length, byte value) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.bytesBefore((int)length, (byte)value);
    }

    @Override
    public int bytesBefore(int index, int length, byte value) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.bytesBefore((int)index, (int)length, (byte)value);
    }

    @Override
    public int forEachByte(ByteProcessor processor) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.forEachByte((ByteProcessor)processor);
    }

    @Override
    public int forEachByte(int index, int length, ByteProcessor processor) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.forEachByte((int)index, (int)length, (ByteProcessor)processor);
    }

    @Override
    public int forEachByteDesc(ByteProcessor processor) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.forEachByteDesc((ByteProcessor)processor);
    }

    @Override
    public int forEachByteDesc(int index, int length, ByteProcessor processor) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.forEachByteDesc((int)index, (int)length, (ByteProcessor)processor);
    }

    @Override
    public ByteBuf copy() {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.copy();
    }

    @Override
    public ByteBuf copy(int index, int length) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.copy((int)index, (int)length);
    }

    @Override
    public int nioBufferCount() {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.nioBufferCount();
    }

    @Override
    public ByteBuffer nioBuffer() {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.nioBuffer();
    }

    @Override
    public ByteBuffer nioBuffer(int index, int length) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.nioBuffer((int)index, (int)length);
    }

    @Override
    public ByteBuffer[] nioBuffers() {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.nioBuffers();
    }

    @Override
    public ByteBuffer[] nioBuffers(int index, int length) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.nioBuffers((int)index, (int)length);
    }

    @Override
    public ByteBuffer internalNioBuffer(int index, int length) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.internalNioBuffer((int)index, (int)length);
    }

    @Override
    public String toString(Charset charset) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.toString((Charset)charset);
    }

    @Override
    public String toString(int index, int length, Charset charset) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.toString((int)index, (int)length, (Charset)charset);
    }

    @Override
    public ByteBuf capacity(int newCapacity) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.capacity((int)newCapacity);
    }

    @Override
    public short getShortLE(int index) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.getShortLE((int)index);
    }

    @Override
    public int getUnsignedShortLE(int index) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.getUnsignedShortLE((int)index);
    }

    @Override
    public int getMediumLE(int index) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.getMediumLE((int)index);
    }

    @Override
    public int getUnsignedMediumLE(int index) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.getUnsignedMediumLE((int)index);
    }

    @Override
    public int getIntLE(int index) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.getIntLE((int)index);
    }

    @Override
    public long getUnsignedIntLE(int index) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.getUnsignedIntLE((int)index);
    }

    @Override
    public long getLongLE(int index) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.getLongLE((int)index);
    }

    @Override
    public ByteBuf setShortLE(int index, int value) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.setShortLE((int)index, (int)value);
    }

    @Override
    public ByteBuf setIntLE(int index, int value) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.setIntLE((int)index, (int)value);
    }

    @Override
    public ByteBuf setMediumLE(int index, int value) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.setMediumLE((int)index, (int)value);
    }

    @Override
    public ByteBuf setLongLE(int index, long value) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.setLongLE((int)index, (long)value);
    }

    @Override
    public short readShortLE() {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.readShortLE();
    }

    @Override
    public int readUnsignedShortLE() {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.readUnsignedShortLE();
    }

    @Override
    public int readMediumLE() {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.readMediumLE();
    }

    @Override
    public int readUnsignedMediumLE() {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.readUnsignedMediumLE();
    }

    @Override
    public int readIntLE() {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.readIntLE();
    }

    @Override
    public long readUnsignedIntLE() {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.readUnsignedIntLE();
    }

    @Override
    public long readLongLE() {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.readLongLE();
    }

    @Override
    public ByteBuf writeShortLE(int value) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.writeShortLE((int)value);
    }

    @Override
    public ByteBuf writeMediumLE(int value) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.writeMediumLE((int)value);
    }

    @Override
    public ByteBuf writeIntLE(int value) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.writeIntLE((int)value);
    }

    @Override
    public ByteBuf writeLongLE(long value) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.writeLongLE((long)value);
    }

    @Override
    public int writeCharSequence(CharSequence sequence, Charset charset) {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.writeCharSequence((CharSequence)sequence, (Charset)charset);
    }

    @Override
    public int getBytes(int index, FileChannel out, long position, int length) throws IOException {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.getBytes((int)index, (FileChannel)out, (long)position, (int)length);
    }

    @Override
    public int setBytes(int index, FileChannel in, long position, int length) throws IOException {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.setBytes((int)index, (FileChannel)in, (long)position, (int)length);
    }

    @Override
    public int readBytes(FileChannel out, long position, int length) throws IOException {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.readBytes((FileChannel)out, (long)position, (int)length);
    }

    @Override
    public int writeBytes(FileChannel in, long position, int length) throws IOException {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.writeBytes((FileChannel)in, (long)position, (int)length);
    }

    @Override
    public ByteBuf asReadOnly() {
        AdvancedLeakAwareByteBuf.recordLeakNonRefCountingOperation((ResourceLeakTracker<ByteBuf>)this.leak);
        return super.asReadOnly();
    }

    @Override
    public ByteBuf retain() {
        this.leak.record();
        return super.retain();
    }

    @Override
    public ByteBuf retain(int increment) {
        this.leak.record();
        return super.retain((int)increment);
    }

    @Override
    public boolean release() {
        this.leak.record();
        return super.release();
    }

    @Override
    public boolean release(int decrement) {
        this.leak.record();
        return super.release((int)decrement);
    }

    @Override
    public ByteBuf touch() {
        this.leak.record();
        return this;
    }

    @Override
    public ByteBuf touch(Object hint) {
        this.leak.record((Object)hint);
        return this;
    }

    @Override
    protected AdvancedLeakAwareByteBuf newLeakAwareByteBuf(ByteBuf buf, ByteBuf trackedByteBuf, ResourceLeakTracker<ByteBuf> leakTracker) {
        return new AdvancedLeakAwareByteBuf((ByteBuf)buf, (ByteBuf)trackedByteBuf, leakTracker);
    }

    static {
        logger = InternalLoggerFactory.getInstance(AdvancedLeakAwareByteBuf.class);
        ACQUIRE_AND_RELEASE_ONLY = SystemPropertyUtil.getBoolean((String)PROP_ACQUIRE_AND_RELEASE_ONLY, (boolean)false);
        if (logger.isDebugEnabled()) {
            logger.debug((String)"-D{}: {}", (Object)PROP_ACQUIRE_AND_RELEASE_ONLY, (Object)Boolean.valueOf((boolean)ACQUIRE_AND_RELEASE_ONLY));
        }
        ResourceLeakDetector.addExclusions(AdvancedLeakAwareByteBuf.class, (String[])new String[]{"touch", "recordLeakNonRefCountingOperation"});
    }
}

