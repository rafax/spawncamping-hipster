package com.gajdulewicz.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by rafal on 12/08/14.
 */
public class ChargingChaos {

    public static int minFlippedSwitches(int l, List<BitSet> outlets, List<BitSet> devices) {
        Collections.sort(devices, (x, y) -> x.toString().compareTo(y.toString()));
        for (int i = 0; i < Math.pow(2, l); i++) {
            String flipped = toBinaryStringOfLength(i, l);
            List<BitSet> outletsCp = outlets.stream().map(x -> copyOf(x)).collect(Collectors.toList());
            for (int j = 0; j < flipped.length(); j++) {
                if (flipped.charAt(j) == '1') {
                    for (BitSet bitSet : outletsCp) {
                        bitSet.flip(j);
                    }
                }
            }
            Collections.sort(outletsCp, (x, y) -> x.toString().compareTo(y.toString()));
            System.out.println(outletsCp + " to power " + devices + " according to " + flipped);

        }
        return -1;
    }

    private static BitSet copyOf(BitSet x) {
        BitSet res = new BitSet(x.length());
        for (int i = 0; i < x.length(); i++) {
            res.set(i, x.get(i) == true);
        }
        return res;
    }

    private static String toBinaryStringOfLength(int i, int l) {
        StringBuilder sb = new StringBuilder();
        String binary = Integer.toBinaryString(i);
        for (int j = 0; j < l - binary.length(); j++) {
            sb.append('0');
        }
        sb.append(binary);
        return sb.toString();
    }

    private static BitSet toBitSet(String input) {
        BitSet res = new BitSet(input.length());
        for (int i = 0; i < input.length(); i++) {
            res.set(i, input.charAt(i) == '1');
        }
        return res;
    }

    public static void main(String[] args) {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(args[0]))) {
            int testCases = Integer.parseInt(br.readLine());
            for (int i = 0; i < testCases; i++) {
                br.readLine();
                List<BitSet> outlets = Arrays.asList(br.readLine().split(" ")).stream().map(x -> toBitSet(x)).collect(Collectors.toList());
                List<BitSet> devices = Arrays.asList(br.readLine().split(" ")).stream().map(x -> toBitSet(x)).collect(Collectors.toList());
                System.out.println(String.format("Case #%s : %s", i + 1, ChargingChaos.minFlippedSwitches(outlets.get(0).length(), outlets, devices)));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
