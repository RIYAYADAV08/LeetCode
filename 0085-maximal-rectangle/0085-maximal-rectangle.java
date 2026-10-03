class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) return 0;

        int n = matrix[0].length;
        int[] heights = new int[n];
        int max = 0;

        for (char[] row : matrix) {
            for (int j = 0; j < n; j++) {
                if (row[j] == '1') {
                    heights[j]++;
                } else {
                    heights[j] = 0;
                }
            }

            max = Math.max(max, largestRectangle(heights));
        }

        return max;
    }

    private int largestRectangle(int[] heights) {
        Stack<Integer> st = new Stack<>();
        int max = 0;

        for (int i = 0; i <= heights.length; i++) {
            int curr = (i == heights.length) ? 0 : heights[i];

            while (!st.isEmpty() && curr < heights[st.peek()]) {
                int height = heights[st.pop()];
                int width = st.isEmpty() ? i : i - st.peek() - 1;

                max = Math.max(max, height * width);
            }

            st.push(i);
        }

        return max;
    }
}