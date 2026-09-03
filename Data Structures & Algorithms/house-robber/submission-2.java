class Solution {
    public int rob(int[] nums) {
        // return solutionRec(nums);
        // return topDownIterative(nums);
        return bottomUp(nums);
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

    public int topDownIterative(int[] nums) {
        int n = nums.length;
        int plusTwo=0;
        int plusOne=0;
        int res=0;
        for(int i=n-1;i>=0;i--) {
            // res += Math.max(0+plusOne, nums[i]+plusTwo);
            res = Math.max(0+plusOne, nums[i]+plusTwo);
            plusTwo = plusOne;
            // plusOne = nums[i];
            plusOne = res;
        }
        return res;
    }

    public int bottomUp(int[] nums) {
        int[] dp = new int[nums.length+2];
        Arrays.fill(dp,0);
        for(int i=2;i<nums.length+2;i++) {
            dp[i] = Math.max(0+dp[i-1], nums[i-2]+dp[i-2]);
        }
        return dp[nums.length+1];
    }
}
