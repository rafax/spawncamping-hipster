package com.gajdulewicz.spawncamping.hipster.problems;

public class RuneValidator {

    public static void main(String[] args) {
        //System.out.println(getFollowers((byte)Integer.parseInt("11001111",2)));
        System.out.println(isValid(parse("01111111 11101111 10111111 10111111")));
    }

    private static boolean isValid(byte[] parsed) {
        int continuation = 0;
        for (int i = 0; i < parsed.length; i++) {
            byte b = parsed[i];
            if (continuation > 0) {
                if (!isContinuation(b)) {
                    return false;
                } else continuation--;
            } else {
                if (isContinuation(b)) {
                    return false;
                }
                if (isSingleByte(b)) {
                    continue;
                }
                continuation = getFollowers(b);
            }
        }
        return continuation == 0;
    }

    private static int getFollowers(byte b) {
        int cnt = 0;
        for (int i = 0; i < 8; i++) {
            if ((b & (1 << (7 - i))) != 0 || i == 7) {
                cnt++;
            } else {
                break;
            }

        }
        return cnt - 1;
    }

    private static boolean isSingleByte(byte b) {
        return (b & (1 << 7)) == 0;
    }

    private static boolean isContinuation(byte b) {
        return (b & (1 << 7)) != 0 && (b & (1 << 6)) == 0;
    }

    private static byte[] parse(String in) {
        String[] split = in.split(" ");
        byte[] res = new byte[split.length];
        for (int i = 0; i < split.length; i++) {
            res[i] = (byte) Integer.parseInt(split[i], 2);
        }
        return res;
    }
}
