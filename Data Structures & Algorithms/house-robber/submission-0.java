class Solution {
    public int rob(int[] nums) {
        return solutionRec(nums);
    }

    // in recursion we get first parial solution when we reach end
    public int solutionRec(int[] nums) {
        int[] dp = new int[nums.length+1];
        Arrays.fill(dp, -1);
        return rec(nums,0,dp);
    }
    public int rec(int[] nums, int i, int[] dp) {
        if(i>=nums.length) return 0;
        if(dp[i]!=-1) return dp[i];
        // return dp[i] = nums[i] + Math.max(rec(nums,i+1,dp),rec(nums,i+2,dp));
        return dp[i] = Math.max(0+rec(nums,i+1,dp),nums[i] + rec(nums,i+2,dp));
    }
}
