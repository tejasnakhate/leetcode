/*
This is basically a graph coloring problem. We have to divide graph into two sets of nodes
such that each edge lies in between a node in one set to a node in another. Their should be
no edges pointing to nodes of same sets.


Approach - 
    we color all nodes in two colors only
    we iterate through all nodes in a graph and colour them (1) if they have not been visited
    then, we color the adjacent nodes with different color (-1)
    if we cannot colour them then we return false
*/

class Solution {
    public boolean isBipartite(int[][] graph) {
        int[] colors = new int[graph.length];
        Arrays.fill(colors, 0);
        for(int i=0; i<graph.length; i++) {
            if(colors[i]==0) {
                // paint the unvisited node as 1
                colors[i] = 1;
                if(!dfs(graph, i, colors, 1)) return false;
            }
        }
        return true;
    }
    
    public boolean dfs(int[][] graph, int ind, int[] colors, int curr) {
        if(ind==graph.length) return true;
        
        for(int i : graph[ind]) {
            if(colors[i]==0) {
                // paint the unvisited neighbours opposite to curr and make recurssive call
                colors[i] = -curr;
                if(!dfs(graph, i, colors, -curr)) return false;
            } 
            // if the neighbour is already visited and its color is equal to curr then false
            else if(colors[i]==curr) return false;
        }
        
        return true;
    }
}
