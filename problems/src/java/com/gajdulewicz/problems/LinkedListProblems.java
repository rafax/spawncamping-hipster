package com.gajdulewicz.problems;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by rafal on 30/07/14.
 */
public class LinkedListProblems {

    static class Node {
        Node next = null;
        int data;

        public Node(int d) {
            data = d;
        }

        void appendToTail(int d) {
            Node end = new Node(d);
            Node n = this;
            while (n.next != null) {
                n = n.next;
            }
            n.next = end;
        }

        public List<Integer> values() {
            List<Integer> res = new ArrayList<>();
            Node n = this;
            while (n.next != null) {
                res.add(n.data);
                n = n.next;
            }
            return res;
        }
    }

    public static void removeDuplicates(Node n) {
        HashSet<Integer> seen = new HashSet<>();
        Node previous = null;
        while (n.next != null) {
            if (seen.contains(n.data)) {
                previous.next = n.next;
            } else {
                seen.add(n.data);
                previous = n;
            }
            n = n.next;
        }
    }

    private static void removeDuplicatesNoExtraSpace(Node n) {
        Node current = n;
        while (current.next != null) {
            Node runner = current.next;
            Node prevRunner = current;
            while (runner.next != null) {
                if (current.data == runner.data) {
                    prevRunner.next = runner.next;
                } else {
                    prevRunner = runner;
                }
                runner = runner.next;
            }
            current = current.next;
        }
    }

    public static void main(String[] args) {
        Node n1 = new Node(0), n2 = new Node(0);
        for (int i = 0; i < 10000; i++) {
            n1.appendToTail(i);
            n1.appendToTail(i);
            n2.appendToTail(i);
            n2.appendToTail(i);
        }
        try (Timer t = new Timer("Time")) {
            LinkedListProblems.removeDuplicates(n1);
        }
        try (Timer t = new Timer("Space")) {
            LinkedListProblems.removeDuplicatesNoExtraSpace(n2);
        }
        System.out.println(n1.values());
        System.out.println(n2.values());
    }

}
