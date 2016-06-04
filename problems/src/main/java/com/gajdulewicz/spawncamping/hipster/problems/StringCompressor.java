package com.gajdulewicz.spawncamping.hipster.problems;

/**
 * Created by rafal on 30/07/14.
 */
public class StringCompressor {

    public static String compress(char[] input) {
        StringBuilder sb = new StringBuilder();
        int curr = input[0], currLength = 1;
        for (int i = 1; i < input.length; i++) {
            if (input[i] == curr) {
                currLength++;
            } else {
                sb.append(String.valueOf(input[i - 1]));
                sb.append(Integer.toString(currLength));
                curr = input[i];
                currLength = 1;
            }
        }
        sb.append(String.valueOf(input[input.length - 1]));
        sb.append(Integer.toString(currLength));
        if (sb.length() < input.length) {
            return sb.toString();
        } else {
            return String.valueOf(input);
        }
    }

    public static void main(String[] args) {
        System.out.println(StringCompressor.compress("aabcccccaaa".toCharArray()));

    }
}
