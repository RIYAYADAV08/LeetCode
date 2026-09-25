class Solution {

    public List<String> braceExpansionII(String expression) {
        Set<String> result = parse(expression, 0, expression.length());

        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);

        return ans;
    }

    private Set<String> parse(String s, int start, int end) {

        Set<String> result = new HashSet<>();
        Set<String> current = new HashSet<>();

        current.add("");

        int i = start;

        while (i < end) {

            char ch = s.charAt(i);

            // UNION: comma
            if (ch == ',') {
                result.addAll(current);
                current = new HashSet<>();
                current.add("");
                i++;
            }

            // BRACES
            else if (ch == '{') {

                int balance = 1;
                int j = i + 1;

                while (balance > 0) {

                    if (s.charAt(j) == '{') {
                        balance++;
                    } else if (s.charAt(j) == '}') {
                        balance--;
                    }

                    j++;
                }

                // Expand content inside {}
                Set<String> inside = parse(s, i + 1, j - 1);

                // Concatenate current × inside
                current = combine(current, inside);

                i = j;
            }

            // NORMAL CHARACTER
            else {

                Set<String> single = new HashSet<>();
                single.add(String.valueOf(ch));

                current = combine(current, single);

                i++;
            }
        }

        result.addAll(current);

        return result;
    }

    private Set<String> combine(Set<String> a, Set<String> b) {

        Set<String> result = new HashSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}