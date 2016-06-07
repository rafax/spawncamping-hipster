package com.gajdulewicz.spawncamping.hipster.problems;

import java.util.Stack;

/**
 * Created by rafal on 31/07/14.
 */
public class HanoiTowers {


    public static void moveToTower3(Stack<Integer> tower1, Stack<Integer> tower2, Stack<Integer> tower3) {
        move(tower1.size(), tower1, tower3, tower2);
    }

    static void move(int n, Stack<Integer> source, Stack<Integer> destination, Stack<Integer> buffer) {
        if (n <= 0) {
            return;
        }
        move(n - 1, source, buffer, destination);
        destination.push(source.pop());
        move(n - 1, buffer, destination, source);
    }

    public static void main(String[] args) {
        Stack<Integer> s1 = new Stack<>(), s2 = new Stack<>(), s3 = new Stack<>();
        for (int i = 10; i >= 0; i--) {
            s1.push(i);
        }
        moveToTower3(s1, s2, s3);
        while (!s3.isEmpty()) {
            System.out.println(s3.pop());
        }
    }


}
