/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int result = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if(root == null)
            return 0;
        mc(root);
        return result;
    }
    
    private int rc(TreeNode root){
        if (root == null) return 0;
        int left = Math.max(0, rc(root.left));
        int right = Math.max(0, rc(root.right));
        result = Math.max(result, root.val+ left + right);
        return Math.max(left, right) + root.val;
    }
    private int lc(TreeNode root){
        if (root == null) return 0;
        
        int left = Math.max(0, lc(root.left));
        int right = Math.max(0, lc(root.right));
        
        result = Math.max(result, root.val + left + right);
        
        return Math.max(left, right)+root.val;
    }
    
    private int mc(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = Math.max(0, mc(root.left));
        int right = Math.max(0, mc(root.right));
        result = Math.max(result, left+right+root.val);
        return Math.max(left, right) + root.val;
    }
}