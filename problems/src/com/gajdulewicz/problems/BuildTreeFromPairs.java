package com.gajdulewicz.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by rafal on 07/08/2014.
 */
public class BuildTreeFromPairs {

    public static void main(String[] args) {
        List<Pair<Integer>> pairs = new ArrayList<>();
        pairs.add(new Pair<>(1, 2));
        pairs.add(new Pair<>(2, 3));
        pairs.add(new Pair<>(3, 4));
        pairs.add(new Pair<>(5, 4));
        System.out.println(BuildTreeFromPairs.build(pairs));
    }

    private static Node build(List<Pair<Integer>> pairs) {
        Node<Integer> root = null;
        HashMap<Integer, Node<Integer>> nodes = new HashMap<>();
        for (Pair<Integer> pair : pairs) {
            Node<Integer> parent, child;
            if (!nodes.containsKey(pair.right)) {
                parent = new Node<>(pair.right);
                nodes.put(parent.value, parent);
            } else {
                parent = nodes.get(pair.right);
            }
            if (!nodes.containsKey(pair.left)) {
                child = new Node<>(pair.left);
                nodes.put(child.value, child);
            } else {
                child = nodes.get(pair.left);
            }
            child.setParent(parent);
            parent.addChild(child);
            nodes.put(child.value, child);
            if (root == null) {
                root = parent;
            }
        }
        while (root.getParent() != null) {
            root = root.getParent();
        }
        return root;
    }

    static class Node<T> {
        Node<T> parent;

        Node(T value) {
            this.value = value;
            children =new ArrayList<>();
        }

        public final T value;
        List<Node> children;

       public void addChild(Node<T> child){
           children.add(child);
       }

        public Node<T> getParent() {
            return parent;
        }

        public void setParent(Node<T> parent) {
            this.parent = parent;
        }

        @Override
        public String toString() {
            return String.format(children.isEmpty()?"(%s)":"(%s,\n%s)", value, children);
        }
    }

    static class Pair<T> {
        final T left;
        final T right;

        Pair(T left, T right) {
            this.left = left;
            this.right = right;
        }
    }

}
