class Solution {
    public int maximumGap(int[] nums) {
        if (nums.length < 2) return 0;

        int min = nums[0];
        int max = nums[0];

        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        int n = nums.length;
        int bucketSize = Math.max(1, (max - min) / (n - 1));
        int bucketCount = (max - min) / bucketSize + 1;

        int[] bucketMin = new int[bucketCount];
        int[] bucketMax = new int[bucketCount];
        boolean[] used = new boolean[bucketCount];

        for (int num : nums) {
            int index = (num - min) / bucketSize;

            bucketMin[index] = used[index]
                    ? Math.min(bucketMin[index], num) : num;

            bucketMax[index] = used[index]
                    ? Math.max(bucketMax[index], num) : num;

            used[index] = true;
        }

        int ans = 0;
        int prevMax = min;

        for (int i = 0; i < bucketCount; i++) {
            if (!used[i]) continue;

            ans = Math.max(ans, bucketMin[i] - prevMax);
            prevMax = bucketMax[i];
        }

        return ans;
    }
}