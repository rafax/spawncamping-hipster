package com.gajdulewicz.problems;

import java.util.*;


public class Pancakes {
    public static void main(String[] args) {

        PancakeStack p = parse("--+-");
        int moves = solve(p.copy(), 0);
        System.out.println(p + " -> " + moves);
    }

    private static int solve(PancakeStack bs, int moves) {
        if (bs.isEmpty()) {
            return moves;
        }
        if (bs.get(bs.length() - 1)) {
            bs.popRight();
            return solve(bs, moves);
        } else {
            if (bs.get(0)) {
                bs.flip(1);
                return solve(bs, moves + 1);
            } else {
                bs.flip();
                return solve(bs, moves + 1);
            }
        }
    }

    private static PancakeStack parse(String s) {
        ArrayList<Boolean> bs = new ArrayList<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            bs.add(i, s.charAt(i) == '+');
        }
        return new PancakeStack(bs);
    }

    private static class PancakeStack {
        private final ArrayList<Boolean> data;

        public PancakeStack(ArrayList<Boolean> bs) {
            this.data = bs;
        }

        public Boolean popRight() {
            return data.remove(data.size() - 1);
        }

        public void flip(int num) {
            ArrayList<Boolean> flipped = new ArrayList<>(num);
            for (int i = num - 1; i >= 0; i--) {
                flipped.add(!data.get(i));
            }
            for (int i = 0; i < num; i++) {
                data.set(i, flipped.get(i));
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i)) sb.append("+");
                else sb.append("-");
            }
            return sb.toString();
        }

        public PancakeStack copy() {
            return new PancakeStack(new ArrayList<>(this.data));
        }

        public boolean isEmpty() {
            return data.isEmpty();
        }

        public int length() {
            return data.size();
        }

        public boolean get(int i) {
            return data.get(i);
        }

        public void flip() {
            flip(length());
        }
    }
}
