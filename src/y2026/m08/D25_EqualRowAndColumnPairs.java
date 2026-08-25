package y2026.m08;

import utils.TestCase;
import utils.TestRunner;

class D25_EqualRowAndColumnPairs {

    public static void main(String[] args) {
        D25_EqualRowAndColumnPairs solution = new D25_EqualRowAndColumnPairs();

        TestRunner.run("d",
                solution::equalPairs,
                new TestCase<>(new int[][]{{3, 2, 1}, {1, 7, 6}, {2, 7, 7}}, 1),
                new TestCase<>(new int[][]{{3, 1, 2, 2}, {1, 4, 4, 5}, {2, 4, 2, 2}, {2, 4, 2, 2}}, 3)
        );
    }

    public int equalPairs(int[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (check(grid, i, j))
                    count++;
            }
        }
        return count;
    }

    private boolean check(int[][] grid, int r, int c) {
        for (int i = 0; i < grid.length; i++) {
            if (grid[r][i] != grid[i][c])
                return false;
        }
        return true;
    }
}
