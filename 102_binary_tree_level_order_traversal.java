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
        private List<List<Integer>> levels = new ArrayList<List<Integer>>();

    public List<List<Integer>> levelOrder(TreeNode root) {
        rc(root, 0);
        return levels;
    }
    
    
    private void rc(TreeNode root, int level){
        if(root == null) return;
        if(level == levels.size()){
            levels.add(new ArrayList<Integer>());
        }
        
        levels.get(level).add(root.val);
        
        rc(root.left, level+1);
        rc(root.right, level+1);
    }
}