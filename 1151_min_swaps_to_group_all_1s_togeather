/*
Here, we will be using sliding window to keep track of minimum swaps. The length of all ones
grouped togeather will be equal to count of all ones in the input. we are using a window equal to
length of the ones in input and sliding it across input to fit max ones in window. The window having
max one is the idel position for other ones outside the window to swap in. Hence, the total number
of swaps needed will be equal to number of zeros in this window
*/

class Solution {
    public int minSwaps(int[] nums) {
        int ones = 0;
        for(int i : nums) {
            if(i==1) ones++;
        }
        
        // this vari will keep track of the answer
        int ans = Integer.MAX_VALUE;
        // this variable will count the number of ones in the window as we slide it
        int countone = 0;
        
        int j = 0;
        // initially, we count ones in the window which we will slide later
        for(;j<ones; j++) if(nums[j]==1) countone++; 
        
        // this variable will keep track of the zeros in the window
        int zeros = ones - countone;
        for(int i = 0; i<nums.length-ones; i++, j++) {
            // our answer will be window with minimum zeros
            ans = Math.min(ans, zeros);
            // the valid window is from i to j. i is excluded and j is included hence we are modifying
            // countone accordingly
            if(nums[i]==1) countone--;
            if(nums[j]==1) countone++;
            
            // updating zeros variable by zero count in our new window
            zeros = ones - countone;
        }
        ans = Math.min(ans, zeros);
        return ans;
    }
}
