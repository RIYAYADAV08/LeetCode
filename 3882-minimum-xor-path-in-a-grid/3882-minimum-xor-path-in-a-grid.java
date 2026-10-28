class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int total = 1024;

        boolean[][][] dp = new boolean[m][n][total];
        dp[0][0][grid[0][0]] = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                for (int x = 0; x < total; x++) {
                    int prev = x ^ grid[i][j];

                    if (i > 0 && dp[i - 1][j][prev]) {
                        dp[i][j][x] = true;
                    }

                    if (j > 0 && dp[i][j - 1][prev]) {
                        dp[i][j][x] = true;
                    }
                }
            }
        }

        for (int x = 0; x < total; x++) {
            if (dp[m - 1][n - 1][x]) {
                return x;
            }
        }

        return 0;
    }
}