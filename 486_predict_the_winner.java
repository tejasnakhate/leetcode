/*

For each game, this  is how we will create a dp table. It will be a 3d dp with 3rd dimension holding
the sore of both the players 0th index holds p1 score, 1st index holds p2 score. 
ip = [1,2,3]

dp =    
[[[1,0], [2,1], [4,1]],
,[[x,x], [2,0], [3,2]],
,[[x,x], [x,x], [3,0]]
]

*/


class Solution {
    public boolean PredictTheWinner(int[] nums) {
        int n = nums.length;
        if(n==1) return true;
        int[][][] dp = new int[n][n][2];
        
        for(int i=0; i<n; i++) {
            dp[i][i][0] = nums[i];
        }
        
        int col = 1;
        while(col<n) {
            int j = col;
            int i = 0;
            while(j<n) {
                
                // the dp term dp[i+1][j][1] and dp[i][j-1][1] holds the optimal score for second move 
                // when we select nums[i] and nums[j] respectively in the first move. we are considering
                // p2 moves since those are the optimal moves for second turn since their p2 played 
                // second move so we will get optimal second move score
                
                // if we select i we will have to look for p2 score at i+1 and for j look for p2 score in
                // j-1
                
                if(nums[i]+dp[i+1][j][1] > nums[j]+dp[i][j-1][1]) {
                    dp[i][j][0] = nums[i]+dp[i+1][j][1];
                    
                    // the p2's current score will be p1 score at i+1 since we added his score to ours
                    dp[i][j][1] = dp[i+1][j][0];
                    
                } else {
                    dp[i][j][0] = nums[j]+dp[i][j-1][1];
                    // here p2's score will become p1 score at j-1
                    dp[i][j][1] = dp[i][j-1][0];
                }
                
                j++;
                i++;
            }
            col++;
        }
        
        return dp[0][n-1][0] >= dp[0][n-1][1];
    }
}