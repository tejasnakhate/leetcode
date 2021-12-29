/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/
/*
We can do this simply by using bfs, just make the current element in queue point to q.peek
*/
class Solution {
    public Node connect(Node root) {
        if(root==null) return root;
        Queue<Node> q = new LinkedList();
        q.offer(root);
        while(!q.isEmpty()) {
            int sz = q.size();
            Node curr = null;
            
            while(sz-->0) {
                curr = q.poll();
                if(curr.left!=null) q.offer(curr.left);
                if(curr.right!=null) q.offer(curr.right);
                
                // this is where we are manipulating the next pointer
                if(!q.isEmpty()) {
                    curr.next = q.peek();
                }
            }
            
            // we deliberately defined curr node outside of the while loop because the we need 
            // that at the end of the loop to make next of last element top point to null
            if(curr!=null) curr.next = null;
        }
        
        return root;
    }
}
