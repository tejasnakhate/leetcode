/*
Here, we use dp.
base case = when it is last day and we have completed j jobs, return max of jobs from j to end
state cariables = jobs completed so far(index of jobs array), current day
recurrence relation = 
    
    dfs(ind, day) = min(min, maxJob betwn day and j + dfs(ind+1, day+1))
        for j in range day to end of jobs array.
        and min is prev min
*/

class Solution {
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if(d>n) return -1;
        
        // populating the hardest array
        int[] hardest = new int[n];
        hardest[n-1] = jobDifficulty[n-1];
        for(int i=n-2; i>=0; i--) {
            hardest[i] = Math.max(jobDifficulty[i], hardest[i+1]);
        }
        
        int[][] dp = new int[n][d+1];
        for(int[] arr : dp) Arrays.fill(arr, -1);
        return dfs(jobDifficulty, d, hardest, dp, 0, 1);
    }
    
    
    public int dfs(int[] jobs, int d, int[] hardest, int[][] dp, int ind, int curr) {
        if(curr == d) return hardest[ind];
        
        if(dp[ind][curr]!=-1) return dp[ind][curr];
        
        // set the min to max int
        int min = Integer.MAX_VALUE;
        // this will hold the max difficulty job from ind to j
        int maxJob = 0;
                        // this condition ensures we at least have one job left for remaining days
        for(int i = ind; i<jobs.length - (d-curr); i++) {
            // update the maxJob
            maxJob = Math.max(maxJob, jobs[i]);
            // make dfs job with maxJob + job from remining days
            min = Math.min(min, maxJob + dfs(jobs, d, hardest, dp, i+1, curr+1));
        }
        
        // return the minimum
        dp[ind][curr] = min;
        return min;
    }
}
