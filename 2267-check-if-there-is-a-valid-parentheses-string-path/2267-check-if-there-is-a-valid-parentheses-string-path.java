class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }

        Boolean[][][] dp = new Boolean[m][n][m + n];

        return solve(grid, 0, 0, 0, dp);
    }

    private boolean solve(char[][] grid, int i, int j, int balance,
                          Boolean[][][] dp) {

        if (i >= grid.length || j >= grid[0].length) {
            return false;
        }

        if (grid[i][j] == '(') {
            balance++;
        } else {
            balance--;
        }

        if (balance < 0) {
            return false;
        }

        if (i == grid.length - 1 && j == grid[0].length - 1) {
            return balance == 0;
        }

        if (dp[i][j][balance] != null) {
            return dp[i][j][balance];
        }

        boolean down = solve(grid, i + 1, j, balance, dp);
        boolean right = solve(grid, i, j + 1, balance, dp);

        return dp[i][j][balance] = down || right;
    }
}