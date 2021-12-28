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
class Solution1 {
    private boolean result = true;
    public boolean isCompleteTree(TreeNode root) {
        rc(root);
        return result;
    }
    private void rc(TreeNode root){
        if(root == null) return;
        if(root.left == null && root.right != null){
            result = false;
        }
        
        rc(root.left);
        rc(root.right);
    }
}
class Solution {
    private boolean result = false;
    public boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        
        while(!q.isEmpty()){
            TreeNode curr = q.poll();
            if(curr == null){
                result = true;
            }
            else{
                if(result) return false;
                else{
                    q.offer(curr.left);
                    q.offer(curr.right);
                }
            }
        }
        return true;
    }
}