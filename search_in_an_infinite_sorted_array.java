class Solution {
    public int searcnInInfiniteSortedArray(int[] nums) {
      // we cannot do plain binarysearch here as the array is infinite
      // hence, we do binary search to find the upper boundary and then
      // perform noamal bs over the range low to high
        int target = 10;
        int low = 0;
        int high = 1;
        
        int num = nums[high];
        
        while(num < target) {
            // make low as previous high and
            low = high;
            // double the high as new high
            high = 2*high;
            num = nums[high];
        }
        
        // in the end, perform normal bs over the found range
        int ans = bs(nums, low, high, target);
        
        return ans;
    }
    
    public int bs(int[] nums, int l, int h, int target) {
        
        while(l<h) {
            int mid = l + (h-l)/2;
            
            if(nums[mid]<target) l = mid+1;
            else h = mid;
        }
        
        return l;
    }
}
