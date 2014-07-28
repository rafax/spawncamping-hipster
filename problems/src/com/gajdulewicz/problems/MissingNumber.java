package com.gajdulewicz.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Supplier;

/**
 * Created by rafal on 27/07/14.
 */
public class MissingNumber {

    public static int missingNumber(Supplier<BufferedReader> readerFactory) throws IOException {
        int firstEmptyIndex;
        try (BufferedReader reader = readerFactory.get()) {
            firstEmptyIndex = firstEmptyRange(reader);
        }
        boolean[] ends;
        try (BufferedReader reader = readerFactory.get()) {
            String line;
            ends = new boolean[2 << 16];
            while ((line = reader.readLine()) != null) {
                int value = Integer.valueOf(line);
                if (((value & 0xFFFF0000) >> 16) == firstEmptyIndex) {
                    ends[value & 0x0000FFFF] = true;
                }
            }
            for (int i = 0; i < ends.length; i++) {
                if (ends[i] == false) {
                    return firstEmptyIndex << 16 | i;
                }
            }
        }
        return -1;
    }

    private static int firstEmptyRange(BufferedReader reader) throws IOException {
        int[] mostSignificant = new int[2 << 16];
        String line;
        int readCount = 0;
        while ((line = reader.readLine()) != null) {
            readCount++;
            int value = Integer.valueOf(line);
            mostSignificant[(value & 0xFFFF0000) >> 16] += 1;
        }
        for (int i = 0; i < mostSignificant.length; i++) {
            if (mostSignificant[i] < 65536) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(MissingNumber.missingNumber(new Supplier<BufferedReader>() {
            @Override
            public BufferedReader get() {

                try {
                    return Files.newBufferedReader(Paths.get("/Users/rafal/dev/spawncamping-hipster/sin.txt"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;

            }

        }));

    }
}
