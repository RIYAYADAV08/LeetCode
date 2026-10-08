class Solution {
    HashMap<String, Boolean> map = new HashMap<>();

    public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2)) return true;

        String key = s1 + "#" + s2;
        if (map.containsKey(key)) return map.get(key);

        int n = s1.length();

        for (int i = 1; i < n; i++) {
            // Without swapping
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) &&
                isScramble(s1.substring(i), s2.substring(i))) {
                map.put(key, true);
                return true;
            }

            // With swapping
            if (isScramble(s1.substring(0, i), s2.substring(n - i)) &&
                isScramble(s1.substring(i), s2.substring(0, n - i))) {
                map.put(key, true);
                return true;
            }
        }

        map.put(key, false);
        return false;
    }
}