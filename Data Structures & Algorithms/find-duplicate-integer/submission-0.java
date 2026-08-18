class Solution {
    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;
        do {
            fast = nums[nums[fast]];
            slow = nums[slow];
        } while (fast!=slow);

        slow = 0;
        while(fast!=slow) {
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }
}
