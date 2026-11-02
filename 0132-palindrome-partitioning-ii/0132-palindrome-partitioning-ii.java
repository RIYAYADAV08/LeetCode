class Solution {
    public int minCut(String s) {
        int n = s.length();

        boolean[][] dp = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], true);
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j)
                        && dp[i + 1][j - 1];
            }
        }

        int[] cuts = new int[n];

        for (int i = 0; i < n; i++) {
            cuts[i] = i;

            for (int j = 0; j <= i; j++) {
                if (dp[j][i]) {
                    cuts[i] = Math.min(
                        cuts[i],
                        j == 0 ? 0 : cuts[j - 1] + 1
                    );
                }
            }
        }

        return cuts[n - 1];
    }
}