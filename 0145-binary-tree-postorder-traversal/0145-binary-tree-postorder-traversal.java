class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        solve(root, result);
        return result;
    }

    void solve(TreeNode root, List<Integer> result) {
        if (root == null) return;

        solve(root.left, result);
        solve(root.right, result);
        result.add(root.val);
    }
}