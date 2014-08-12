package com.gajdulewicz.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rafal on 08/08/14.
 */
public class AssignToServers {

    public static boolean canAssign(List<Integer> tasks, List<Integer> servers) {
        if (tasks.stream().mapToInt(x -> x).sum() == 0) {
            return true;
        }
        for (int i = 0; i < tasks.size(); i++) {
            for (int j = 0; j < servers.size(); j++) {
                if (tasks.get(i) > 0 && tasks.get(i) <= servers.get(j)) {
                    final ArrayList<Integer> cpServers = new ArrayList<>(servers);
                    final ArrayList<Integer> cpTasks = new ArrayList<>(tasks);
                    cpServers.set(j, cpServers.get(j) - tasks.get(i));
                    cpTasks.set(i, 0);
                    if (canAssign(cpTasks, cpServers)) {
                        return true;
                    }
                }
            }

        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(AssignToServers.canAssign(new ArrayList<>(Arrays.asList(18, 4, 8, 4, 6, 6, 8, 8)), new ArrayList<>(Arrays.asList(8, 16, 8, 32))));
        System.out.println(AssignToServers.canAssign(new ArrayList<>(Arrays.asList(5, 4, 3, 2, 1)), new ArrayList<>(Arrays.asList(8, 6, 2))));

    }
}
