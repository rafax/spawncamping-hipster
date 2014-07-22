package com.gajdulewicz.algo.sort;

import java.util.List;

/**
 * Created by rafal on 22/07/14.
 */
public interface Sorter<T extends Comparable> {
    List<T> sort(List<T> input);
}
