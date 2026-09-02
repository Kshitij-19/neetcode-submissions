class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        for (int i = 2; i<=n-1; i++) {
            cost[i] += Math.min(cost[i - 1], cost[i - 2]);
        }
        int costAtN = Math.min(cost[n - 1], cost[n - 2]);
        return costAtN;
    }
}
