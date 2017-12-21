package org.baize.utils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;

import java.nio.ByteOrder;
import java.util.Arrays;

/**
 * 作者： 白泽
 * 时间： 2017/12/9.
 * 描述：
 */
public class ByteArray {
    private static ByteOrder BYTE_ORDER = ByteOrder.BIG_ENDIAN;

    private static ByteBufAllocator bufAllocator = PooledByteBufAllocator.DEFAULT;
    private static ByteBuf bufArr = bufAllocator.heapBuffer().order(BYTE_ORDER);

    public static char readChar(){
        return bufArr.readChar();
    }
    public static byte readByte(){
        return bufArr.readByte();
    }
    public static short readShort(){
        return bufArr.readShort();
    }
    public static int readInt(){
        return bufArr.readInt();
    }
    public static long readLong(){
        return bufArr.readLong();
    }
    public static float readFloat(){
        return bufArr.readFloat();
    }
    public static double readDouble(){
        return bufArr.readDouble();
    }
    public static boolean readBoolean(){
        byte b = bufArr.readByte();
        return b == 1;
    }

    public static byte[] writeChar(char c){
        ByteBuf b = bufArr.writeChar(c);
        return write(b);
    }
    public static byte[] writeByte(byte b){
        ByteBuf by = bufArr.writeByte(b);
        return write(by);
    }
    public static byte[] writeShort(short s){
        ByteBuf b = bufArr.writeShort(s);
        return write(b);
    }
    public static byte[] writeInt(int i){
        ByteBuf b = bufArr.writeInt(i);
        return write(b);
    }
    public static byte[] writeLong(long l){
        ByteBuf b = bufArr.writeLong(l);
        return write(b);
    }
    public static byte[] writeFloat(float f){
        ByteBuf b = bufArr.writeFloat(f);
        return write(b);
    }
    public static byte[] writeDouble(double d){
        ByteBuf b = bufArr.writeDouble(d);
        return write(b);
    }
    public static byte[] writeBoolean(byte i){
        return writeByte(i);
    }
    private static byte[] write(ByteBuf b){
        byte[] by = new byte[b.readableBytes()];
        b.readBytes(by);
        return by;
    }

    public static void main(String[] args) {
        byte[] b = writeInt(100000);
        System.out.println(Arrays.toString(b));
        b = writeDouble(50000D);
        System.out.println(b);
        double d = readDouble();
        System.out.println(d);
    }
}
