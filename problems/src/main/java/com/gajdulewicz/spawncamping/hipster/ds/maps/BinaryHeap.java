package com.gajdulewicz.spawncamping.hipster.ds.maps;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * Created by rafal on 29/07/14.
 */
public class BinaryHeap<T> {

    private T[] data;
    int nextPos = 0;
    private final Comparator<T> comparator;

    public BinaryHeap() {
        this(new Comparator<T>() {
            @SuppressWarnings("unchecked")
            @Override
            public int compare(T o1, T o2) {
                return ((Comparable) o1).compareTo(o2);
            }
        });
    }

    public BinaryHeap(Comparator<T> comparator) {
        this.comparator = comparator;
        setCapacity(10);
    }

    @SuppressWarnings("unchecked")
    private void setCapacity(int size) {
        if (nextPos > 0) {
            data = Arrays.copyOf(data, size);
        } else {
            data = (T[]) new Object[size];
        }
    }

    public void add(T value) {
        data[nextPos++] = value;
        if (nextPos == data.length) {
            setCapacity(data.length * 2);
        }
        if (nextPos == 1) {
            return;
        }
        int index = nextPos - 1, parentIndex = parentIndex(index);
        while (comparator.compare(data[index], data[parentIndex]) < 0) {
            T tmp = data[parentIndex];
            data[parentIndex] = data[index];
            data[index] = tmp;
            if (parentIndex == 0) {
                return;
            }
            index = parentIndex;
            parentIndex = parentIndex(index);
        }
    }

    public T min() {
        return data[0];
    }

    public T removeMin() {
        T result = data[0];
        data[0] = data[--nextPos];
        int index = 0, leftInd = leftChildIndex(index), rightInd = rightChildIndex(index);
        while ((leftInd < nextPos && comparator.compare(data[index], data[leftInd]) > 0) || (rightInd < nextPos && comparator.compare(data[index], data[rightInd]) > 0)) {
            int smallerChildIndex = comparator.compare(data[leftChildIndex(index)], data[rightChildIndex(index)]) < 0 ? leftChildIndex(index) : rightChildIndex(index);
            T tmp = data[smallerChildIndex];
            data[smallerChildIndex] = data[index];
            data[index] = tmp;
            index = smallerChildIndex;
            leftInd = leftChildIndex(index);
            rightInd = rightChildIndex(index);
        }
        return result;
    }

    public Stream<T> entrySet() {
        return Stream.empty();
    }

    private static int parentIndex(int index) {
        return Double.valueOf(Math.floor((index - 1) / 2.0d)).intValue();
    }

    private static int leftChildIndex(int index) {
        return 2 * index + 1;
    }

    private static int rightChildIndex(int index) {
        return 2 * index + 2;
    }

    public int size() {
        return nextPos;
    }

    public boolean isValid() {
        for (int i = 0; i < nextPos; i++) {
            int leftInd = leftChildIndex(i), rightInd = rightChildIndex(i);
            if (leftInd < nextPos ? comparator.compare(data[i], data[leftInd]) > 0 : false || rightInd < nextPos ? comparator.compare(data[i], data[rightInd]) > 0 : false) {
                return false;
            }
        }
        return true;
    }
}
