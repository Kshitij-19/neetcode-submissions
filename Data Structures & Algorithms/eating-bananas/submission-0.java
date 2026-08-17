class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        // h>=piles.length
        // when h = piles.length, k should be max(piles)
        // so k should range from 1 to max(piles)

        int MaxEle = Integer.MIN_VALUE;
        for(int pile : piles) MaxEle = Integer.max(MaxEle, pile);

        int low = 1;
        int high = MaxEle;
        int minSumOfHours = MaxEle;
        while(low<=high) {
            int k = low + (high-low)/2;
            // System.out.println("low " + low + " high " + high);
            // System.out.println("k " + k);
            int SumOfHours = 0;
            for(int i = 0; i <piles.length; i++) {
                SumOfHours += Math.ceil((double) piles[i]/k);
            }
            //System.out.println("SumOfHours " + SumOfHours);
            if (SumOfHours>h) low = k+1; // if I move towards max(piles) then minSumOfHours will decrease
            else {
                minSumOfHours = Math.min(minSumOfHours, k);
                high = k-1;
            }
        }
        return minSumOfHours;
    }
}
