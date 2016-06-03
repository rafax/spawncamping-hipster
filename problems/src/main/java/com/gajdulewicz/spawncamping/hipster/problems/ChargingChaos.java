package com.gajdulewicz.spawncamping.hipster.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by rafal on 12/08/14.
 */
public class ChargingChaos {

    public static int minFlippedSwitches(int l, List<BitSet> outlets, List<BitSet> devices) {

        int minFlipped = Integer.MAX_VALUE;
        for (BitSet outlet : outlets) {
            BitSet device = devices.get(0);
            String flipped = flipString(outlet, device, l);
            List<BitSet> outletsFlipped = flipBits(outlets, flipped);
            if (match(outletsFlipped, devices)) {
                int nOfOnes = numberOfOnes(flipped);
                if (nOfOnes < minFlipped) {
                    minFlipped = nOfOnes;
                }
            }
        }
        return minFlipped == Integer.MAX_VALUE ? -1 : minFlipped;
    }

    private static String flipString(BitSet outlet, BitSet device, int l) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < l; i++) {
            sb.append(outlet.get(i) == device.get(i) ? "0" : "1");
        }
        return sb.toString();
    }

    private static boolean match(List<BitSet> outletsFlipped, List<BitSet> devices) {
        for (BitSet device : devices) {
            boolean matched = false;
            for (BitSet outlet : outletsFlipped) {
                if (outlet.equals(device)) {
                    matched = true;
                    break;
                }
            }
            if (!matched) {
                return false;
            }
        }
        return true;
    }

    private static int numberOfOnes(String flipped) {
        int sum = 0;
        for (int i = 0; i < flipped.length(); i++) {
            if (flipped.charAt(i) == '1') {
                sum++;
            }
        }
        return sum;
    }

    private static List<BitSet> flipBits(List<BitSet> outlets, String flipped) {
        List<BitSet> outletsCp = outlets.stream().map(x -> copyOf(x)).collect(Collectors.toList());
        for (int j = 0; j < flipped.length(); j++) {
            if (flipped.charAt(j) == '1') {
                for (BitSet bitSet : outletsCp) {
                    bitSet.flip(j);
                }
            }
        }
        return outletsCp;
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
                int l = Integer.parseInt(br.readLine().split(" ")[1]);
                List<BitSet> outlets = Arrays.asList(br.readLine().split(" ")).stream().map(x -> toBitSet(x)).collect(Collectors.toList());
                List<BitSet> devices = Arrays.asList(br.readLine().split(" ")).stream().map(x -> toBitSet(x)).collect(Collectors.toList());
                int res = ChargingChaos.minFlippedSwitches(l, outlets, devices);
                System.out.println(String.format("Case #%s: %s", i + 1, res == -1 ? "NOT POSSIBLE" : res));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
