/*
Their are is a property - 
    1) All elements except one will exist in pairs
    2) if we have not found single element yet, then the pair will start at
    even index
    3) past the single element, the pairs will start at odd index
    
These properties are used to make decisions to reduce search space.
*/
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int n = nums.length;
        if(n==1) return nums[0];
        
        // boundary checks
        if(nums[0]!=nums[1]) return nums[0];
        else if(nums[n-1]!=nums[n-2]) return nums[n-1];
        
        int low = 0, high = n-1;
        
        while(low<high) {
            int mid = low + (high-low)/2;
            
            // case where we found the element
            if(nums[mid]!=nums[mid-1] && nums[mid]!=nums[mid+1]) return nums[mid];
            
            // here, we are checking if the pair we are looking at is starting at even index
            // if it is then single element is still ahead of mid. 
            else if(nums[mid-1]==nums[mid] && (mid-1)%2==0 || nums[mid]==nums[mid+1] && mid%2==0) {
                // search towards right
                low = mid+1;
            } else {
                // search towards left
                high = mid-1;
            }
        }
        
        return nums[low];
    }
}
