class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int[][] cost = new int[n][n];
        int[][] dp = new int[n + 1][k + 1];

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int len = j - i + 1;
                cost[i][j] = len;

                for (int d = 1; d < len; d++) {
                    if (len % d == 0) {
                        int cnt = 0;

                        for (int offset = 0; offset < d; offset++) {
                            int l = i + offset;
                            int r = j - d + 1 + offset;

                            while (l < r) {
                                if (s.charAt(l) != s.charAt(r)) {
                                    cnt++;
                                }
                                l += d;
                                r -= d;
                            }
                        }

                        cost[i][j] = Math.min(cost[i][j], cnt);
                    }
                }
            }
        }

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], 200);
        }

        dp[n][0] = 0;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 1; j <= k; j++) {
                for (int end = i + 1; end < n; end++) {
                    dp[i][j] = Math.min(
                        dp[i][j],
                        cost[i][end] + dp[end + 1][j - 1]
                    );
                }
            }
        }

        return dp[0][k];
    }
}