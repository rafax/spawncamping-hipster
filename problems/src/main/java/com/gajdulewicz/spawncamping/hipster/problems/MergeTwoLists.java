package com.gajdulewicz.spawncamping.hipster.problems;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by rafal on 28/07/14.
 */
public class MergeTwoLists {


    @Test
    public void itShouldNotContainDuplicates() {
        List<Integer> results = MergeTwoLists.merge(Arrays.asList(1, 2, 3, 4, 5), Arrays.asList(5, 4, 3, 2, 1));

        assertEquals(5, results.size());
        assertTrue(results.stream().allMatch(x -> results.indexOf(x) == results.lastIndexOf(x)));

    }

    @Test
    public void binarySearchShouldReturnTheIndexWhenFound() {
        List<Integer> input = IntStream.range(0, 100).boxed().collect(Collectors.toList());
        int index = binarySearch(input, 73);

        assertEquals(Collections.binarySearch(input, 73), index);
        assertEquals(73, index);
    }

    @Test
    public void resultsShouldBeEqual() {
        Random r = new Random();
        List<Integer> left = IntStream.range(0, 10000).map(x -> r.nextInt()).boxed().collect(Collectors.toList());
        List<Integer> right = IntStream.range(0, 10000).map(x -> r.nextInt()).boxed().collect(Collectors.toList());

        List<Integer> normal, sorted, optimized;
        normal = merge(left, right);
        sorted = mergeSorted(left, right);
        optimized = mergeSortedOptimized(left, right);

        Collections.sort(normal);
        Collections.sort(sorted);
        Collections.sort(optimized);

        assertEquals(normal, sorted);
        assertEquals(sorted, optimized);
    }

    @Test
    public void binarySearchShouldReturnNullWhenNotFound() {
        List<Integer> input = IntStream.range(0, 100).boxed().collect(Collectors.toList());
        Integer index = binarySearch(input, 121);

        assertEquals(null, index);
    }

    public static List<Integer> merge(List<Integer> left, List<Integer> right) {
        List<Integer> result = new ArrayList<>(left);
        for (int i = 0; i < right.size(); i++) {
            if (!left.contains(right.get(i))) {
                result.add(right.get(i));
            }
        }
        return result;
    }

    public static List<Integer> mergeSorted(List<Integer> left, List<Integer> right) {
        List<Integer> result = new ArrayList<>(left);
        for (int i = 0; i < right.size(); i++) {
            if (binarySearch(left, right.get(i)) == null) {
                result.add(right.get(i));
            }
        }
        return result;
    }

    public static List<Integer> mergeSortedOptimized(List<Integer> left, List<Integer> right) {
        if (left.size() > right.size()) {
            return mergeSorted(left, right);
        } else {
            return mergeSorted(right, left);
        }
    }

    private static Integer binarySearch(List<Integer> list, int key) {
        int a = 0, b = list.size() - 1;
        while (b - a > 1) {
            int mid = a + (b - a) / 2;
            if (mid == key)
                return mid;
            if (mid < key) {
                a = mid;
            } else {
                b = mid;
            }
        }
        return null;
    }

}
