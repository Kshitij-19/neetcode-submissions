class Solution {
    private int[][] memo;
    public int rob(int[] nums) {
        // return recursiveSol(nums);
        // return bottomUp(nums);
        return topDown(nums);
    }

    // public int recursiveSol(int[] nums) {
    //     if(nums.length==1) return nums[0];
    //     return rec(nums,0);
    // }
    // public int rec(int[] nums, int i) {
    //     if(i>=nums.length) return 0;

    //     return Math
    // }

    public int bottomUp(int[] nums) {
        if(nums.length==1) return nums[0];

        int[] dp = new int[nums.length+2];
        Arrays.fill(dp,0);
        dp[0] = (-1)*nums[nums.length-1];
        for(int i=2;i<nums.length+2;i++) {
            dp[i] = Math.max(0+dp[i-1], nums[i-2]+dp[i-2]);
        }
        return dp[nums.length+1];
    }

    public int topDown(int[] nums) {
        if (nums.length == 1) return nums[0];

        memo = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            memo[i][0] = -1;
            memo[i][1] = -1;
        }

        return Math.max(dfs(0, 1, nums), dfs(1, 0, nums));
    }

    private int dfs(int i, int flag, int[] nums) {
        if (i >= nums.length || (flag == 1 && i == nums.length - 1))
            return 0;
        if (memo[i][flag] != -1)
            return memo[i][flag];
        memo[i][flag] = Math.max(dfs(i + 1, flag, nums),
                        nums[i] + dfs(i + 2, flag, nums));
        return memo[i][flag];
    }
}
