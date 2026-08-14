class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {

        PriorityQueue<Integer> queue = new PriorityQueue<>((a,b)->Integer.compare(b,a));

        for (int i = 0; i<k; i++) queue.offer(nums[i]);
        // logn for each insertion, klogn for overall which is klogk

        int max = queue.peek();
        int[] res = new int[nums.length-k+1];
        int l = 0;
        res[l] = max;

        for(int r = k; r<nums.length; r++) {

            queue.remove(nums[l]);
            l++;

            queue.offer(nums[r]);

            res[l] = queue.peek();
        }
        return res;
    }
}
