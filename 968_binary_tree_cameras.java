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

/*
This is sort of greedy approach. We avoid placing cameras at the leaf nodes as
the last level in the tree contains highest number of nodes and we wont get
min number of cameras if we place cameras at leafs. 
*/
class Solution {
    int ans = 0;
    public int minCameraCover(TreeNode root) {
        if(root==null) return 0;
        Set<TreeNode> seen = new HashSet();
        // we need to add null to seen set to refrain ourselves from placing
        // a camera at the leaf nodes.
        seen.add(null);
        dfs(root, null, seen);
        return ans;
        
        // this optimised approach will run in O(1) space. Instead of keeping
        // track of the covered nodes, we just return the state of the nodes
        // return optimised(root)==0 ? ans+1 : ans;
    }
    
    public void dfs(TreeNode node, TreeNode parent, Set<TreeNode> seen) {
        if(node==null) return;
        
        dfs(node.left, node, seen);
        dfs(node.right, node, seen);
        // the first condition covers the root of the tree, second and third conditions covers
        // the rest of the nodes
        if(parent==null && !seen.contains(node) || !seen.contains(node.left) || !seen.contains(node.right)) {
            ans++;
            seen.add(parent);
            seen.add(node.left);
            seen.add(node.right);
            seen.add(node);
        }
    }
    
    // returns: 0 - uncovered node, 1 - covered node, 2 - node with camera
    public int optimised (TreeNode node) {
        // this is tricky one. We will return 1 for null nodes as this will help
        // us avoid placing cameras at the leaf nodes since they will think their 
        // their children are covered and return 0 to their parent where we will 
        // place the cameras
        if(node==null) return 1;
        int l = optimised(node.left);
        int r = optimised(node.right);
        
        // if either of the child is not covered, then we need to place camera at parent
        // to cover that child
        if(l==0 || r==0) {
            ans++;
            return 2;
        } 
        
        // if either of child has camera, then current node is covered
        else if(l==2 || r==2) return 1;
        
        // else the current node is not covered but its child are covered
        // so we return 0 which will prompt parent of curr to add a camera
        else return 0; 
    }
}
