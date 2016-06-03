package com.gajdulewicz.spawncamping.hipster.problems;

/**
 * Created by rafal on 03/08/14.
 */
public class FlipBitsInByte {


    public static byte flip(byte input) {
        return (byte) (input ^ 0b11111111);
    }

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(flip((byte) 0b11110000)));
    }
}
