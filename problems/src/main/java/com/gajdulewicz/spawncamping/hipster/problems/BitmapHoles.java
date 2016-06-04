package com.gajdulewicz.spawncamping.hipster.problems;

import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by rafal on 26/07/14.
 */
public class BitmapHoles {


    @Test
    public void itShouldReturn2ForEyes() {
        assertEquals(2, BitmapHoles.numberOfHoles(new byte[]{1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1}, 3));
        assertEquals(2, BitmapHoles.numberOfHoles(new byte[]{1, 1, 1, 1, 1, 1, 1,
                1, 0, 0, 1, 0, 0, 1,
                1, 1, 1, 1, 1, 1, 1,}, 3));

    }

    private static int numberOfHoles(byte[] src, int rowCount) {
        byte[] bitmap = new byte[src.length];
        Arrays.fill(bitmap, (byte) 0);
        byte lastColor = 0;
        int rowLength = bitmap.length / rowCount;
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < rowCount; j++) {
                // edges
                if (i == 0 || i == rowLength - 1 || j == 0 || j == rowCount - 1) {
                    bitmap[(j * rowLength) + i] = 0;
                    continue;
                }
                int myInd = (j * rowLength) + i, northInd = (j - 1) * rowLength + i, westInd = j * rowLength + (i - 1);
                if (src[myInd] == src[northInd] && src[myInd] == src[westInd]) {
                    bitmap[myInd] = bitmap[northInd];
                } else if (src[myInd] == src[northInd] && src[myInd] != src[westInd]) {
                    bitmap[myInd] = bitmap[northInd];
                } else if (src[myInd] != src[northInd] && src[myInd] == src[westInd]) {
                    bitmap[myInd] = bitmap[westInd];
                } else {
                    bitmap[myInd] = ++lastColor;
                }
            }
        }
        System.out.println(Arrays.toString(bitmap));
        return lastColor;
    }


}
