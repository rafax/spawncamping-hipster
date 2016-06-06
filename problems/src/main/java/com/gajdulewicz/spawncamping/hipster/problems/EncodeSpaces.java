package com.gajdulewicz.spawncamping.hipster.problems;

public class EncodeSpaces {

    public static void main(String[] args) {
        char[] encoded = encode("Mr John Smith    ".toCharArray());
        System.out.println(String.valueOf(encoded));
    }

    private static char[] encode(char[] chars) {
        boolean countingSpace = true;
        int buffer = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (countingSpace) {
                if (chars[i] == ' ') {
                    buffer++;
                } else {
                    countingSpace = false;
                }
            }
            if (!countingSpace) {
                if (chars[i] != ' ') {
                    chars[i + buffer] = chars[i];
                } else {
                    buffer -= 2;
                    chars[i + buffer] = '%';
                    chars[i + buffer + 1] = '2';
                    chars[i + buffer + 2] = '0';
                }
            }
            System.out.println(String.valueOf(chars) + " buffer " + buffer);
        }
        return chars;
    }
}
