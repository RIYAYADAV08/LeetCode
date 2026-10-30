class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // BFS + Backtracking approach
        Set<String> dict = new HashSet<>(wordList);
        List<List<String>> result = new ArrayList<>();

        if (!dict.contains(endWord)) return result;

        Map<String, List<String>> parents = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        Set<String> visited = new HashSet<>();
        boolean found = false;

        while (!queue.isEmpty() && !found) {
            int size = queue.size();
            Set<String> levelVisited = new HashSet<>();

            for (int i = 0; i < size; i++) {
                String word = queue.poll();

                char[] chars = word.toCharArray();

                for (int j = 0; j < chars.length; j++) {
                    char original = chars[j];

                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[j] = c;
                        String next = new String(chars);

                        if (dict.contains(next) && !visited.contains(next)) {
                            if (next.equals(endWord)) found = true;

                            parents.computeIfAbsent(next, k -> new ArrayList<>()).add(word);

                            if (levelVisited.add(next)) {
                                queue.offer(next);
                            }
                        }
                    }
                    chars[j] = original;
                }
            }
            visited.addAll(levelVisited);
        }

        if (found) {
            List<String> path = new ArrayList<>();
            path.add(endWord);
            backtrack(endWord, beginWord, parents, path, result);
        }

        return result;
    }

    private void backtrack(String word, String beginWord,
                           Map<String, List<String>> parents,
                           List<String> path,
                           List<List<String>> result) {
        if (word.equals(beginWord)) {
            List<String> temp = new ArrayList<>(path);
            Collections.reverse(temp);
            result.add(temp);
            return;
        }

        for (String parent : parents.getOrDefault(word, new ArrayList<>())) {
            path.add(parent);
            backtrack(parent, beginWord, parents, path, result);
            path.remove(path.size() - 1);
        }
    }
}