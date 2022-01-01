/*

The recurrence relation here is 
if(matrix[i][j]==1) dp[i+1][j+1] = Math.min(Math.min(d[i][j+1], dp[i+1][j]), dp[i][j]) + 1;
else do nothing

T = O(mn); S = O(mn);

*/

class Solution {
    int[][] dp;
    int n, m;
    public int maximalSquare(char[][] matrix) {
        n = matrix.length;
        m = matrix[0].length;
        dp = new int[n+1][m+1];
        int max = 0;
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=m; j++) {
                if(matrix[i-1][j-1]=='1') {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                    max = Math.max(dp[i][j], max);
                }
            }
        }
        return max*max;
    }
}
