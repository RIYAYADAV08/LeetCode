class Solution {
    int preIndex = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, inorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int[] inorder, int start, int end) {
        if (start > end) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preIndex++]);

        int index = start;
        while (inorder[index] != root.val) {
            index++;
        }

        root.left = build(preorder, inorder, start, index - 1);
        root.right = build(preorder, inorder, index + 1, end);

        return root;
    }
}