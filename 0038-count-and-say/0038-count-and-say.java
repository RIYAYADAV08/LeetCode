class Solution {
    public String countAndSay(int n) {

        String result = "1";

        for (int i = 1; i < n; i++) {
            result = next(result);
        }

        return result;
    }

    private String next(String s) {

        StringBuilder sb = new StringBuilder();

        int i = 0;

        while (i < s.length()) {

            char current = s.charAt(i);
            int count = 0;

            while (i < s.length() && s.charAt(i) == current) {
                count++;
                i++;
            }

            sb.append(count);
            sb.append(current);
        }

        return sb.toString();
    }
}