class Solution1 {
    int cost = Integer.MAX_VALUE; 
    int temp = Integer.MAX_VALUE; 
    public int minCost(int[][] costs) {
        for(int i=0; i<costs[0].length; i++){
            temp = costs[0][i];
            dfs(costs, i, 1);
            // cost = Math.min(temp, cost);
        }
        return cost;
    }
    
    public void dfs(int[][] c, int i, int j){
        if(j==c.length-1){
            if (cost == Integer.MAX_VALUE){
                cost = temp;
            }
            else{
                cost = Math.min(temp, cost);
            }
            return;
        }
        
        for(int k=j; k<c[j].length; k++){
            if(k!=i){
                temp += c[j][k];
                dfs(c, k, j+1);
                temp -= c[j][k];
            }
        }
    }
}
class Solution{
    public int minCost(int[][] c) {
        if(c.length == 0){
            return 0;
        }
        
        for(int i=1; i<c.length; i++){
            c[i][0] += Math.min(c[i-1][1], c[i-1][2]);
            c[i][1] += Math.min(c[i-1][0], c[i-1][2]);
            c[i][2] += Math.min(c[i-1][0], c[i-1][1]);
        }
        
        return Math.min(Math.min(c[c.length-1][0], c[c.length-1][1]), c[c.length-1][2]);
    }
}