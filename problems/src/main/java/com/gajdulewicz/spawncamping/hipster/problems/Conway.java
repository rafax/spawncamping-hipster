package com.gajdulewicz.spawncamping.hipster.problems;

/**
 * Created by rafal on 16/05/16.
 */
public class Conway {

    private boolean[][] data;
    private final int size;

    public Conway(int size) {
        this.size = size;
        data = new boolean[size][size];
        data[3][3] = true;
        data[3][4] = true;
        data[3][5] = true;
    }

    public void start(Stage s) {
        s.evolved(data);
        while (true) {
            boolean[][] n = new boolean[size][size];
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data.length; j++) {
                    int alive = aliveNeighbors(i, j);
                    if (!data[i][j] && alive == 3) n[i][j] = true;
                    if (data[i][j] && alive == 2 || alive == 3) n[i][j] = true;
                    else n[i][j] = false;
                }
            }
            this.data = n;
            s.evolved(n);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int aliveNeighbors(int i, int j) {
        int alive = 0;
        if (i > 0) {
            if (j > 0) {
                alive += data[i - 1][j - 1] ? 1 : 0;
            }
            alive += data[i - 1][j] ? 1 : 0;
            if (j < size - 1) {
                alive += data[i - 1][j + 1] ? 1 : 0;
            }
        }
        if (j > 0) {
            alive += data[i][j - 1] ? 1 : 0;
        }
        if (j < size - 1) {
            alive += data[i][j + 1] ? 1 : 0;
        }
        if (i < size - 1) {
            if (j > 0) {
                alive += data[i + 1][j - 1] ? 1 : 0;
            }
            alive += data[i + 1][j] ? 1 : 0;
            if (j < size - 1) {
                alive += data[i + 1][j + 1] ? 1 : 0;
            }
        }
        return alive;
    }

    public interface Stage {
        void evolved(boolean[][] data);
    }

    public static void main(String[] args) {
        new Conway(10).start(b -> {
            for (int i = 0; i < b.length; i++) {
                for (int j = 0; j < b[0].length; j++) {
                    if (b[i][j]) System.out.print("x");
                    else System.out.print("o");
                }
                System.out.println();
            }
            System.out.println();
        });

    }


}
