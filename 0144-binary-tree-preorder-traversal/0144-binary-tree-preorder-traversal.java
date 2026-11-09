class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        solve(root, result);
        return result;
    }

    void solve(TreeNode root, List<Integer> result) {
        if (root == null) return;

        result.add(root.val);
        solve(root.left, result);
        solve(root.right, result);
    }
}