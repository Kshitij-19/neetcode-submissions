class Solution {
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length-1;
        // int lele = nums[0];
        // int rele = nums[r];
        int MinEle = nums[0];

        // consider 3 examples: 6 elements, from idx 0 to 5, mid is "2"
        // 0,1,2,3,4,5
        // 3,4,5,0,1,2
        // 5,0,1,2,3,4

        // if arr[mid] < arr[low] or arr[mid] > arr[low] element can still be in the left side, cannot differentiate properly
        // if arr[mid] > arr[high] then definetley minimum ele is on right
        // else it is on left

        while(low<=high) {
            int mid = low + (high-low)/2;
            if(nums[mid] > nums[high]) {
                low = mid + 1;
            } else { // mid can be lowest element or lowest le can be to the left
                MinEle = Math.min(MinEle, nums[mid]);
                high = mid-1;
            }
        }
        if (high+1>=0 && high+1<nums.length) MinEle = Math.min(MinEle, nums[high+1]);
        return MinEle;
    }
}
