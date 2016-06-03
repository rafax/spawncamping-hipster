package com.gajdulewicz.spawncamping.hipster.problems;

import org.junit.Test;

import java.util.Stack;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by rafal on 31/07/14.
 */
public class QueueWithTwoStacks<T> {

    private Stack<T> left = new Stack<>();
    private Stack<T> right = new Stack<>();
    int size = 0;

    public void push(T elem) {
        size++;
        left.push(elem);
    }

    public T pop() {
        if (right.size() == 0) {
            for (int i = 0; i < size; i++) {
                right.push(left.pop());
            }
        }
        size--;
        return right.pop();


    }

    @Test
    public void isFifo() {
        QueueWithTwoStacks<Integer> q = new QueueWithTwoStacks<>();
        for (int i = 0; i < 200; i++) {
            q.push(i);
        }
        for (int i = 0; i < 100; i++) {
            assertEquals(Integer.valueOf(i), q.pop());
        }
        for (int i = 200; i < 300; i++) {
            q.push(i);
        }
        for (int i = 100; i < 300; i++) {
            assertEquals(Integer.valueOf(i), q.pop());
        }
    }

}
