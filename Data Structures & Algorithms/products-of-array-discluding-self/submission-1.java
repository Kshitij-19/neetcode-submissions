class Solution {
    public int[] productExceptSelf(int[] nums) {
        // we shouldn't multiply everything because:
        // 1. the end product can cross the storage limit
        // 2. a single zero can make entire product invalid

        // remember each position needs product of left and product of right
        // so lets just calculate that


        // Solution 1
        /*
        int[] prefix = new int[nums.length];
        int[] suffix = new int[nums.length];

        prefix[0] = 1;
        for(int i = 1; i<nums.length; i++) {
            prefix[i] = prefix[i-1]*nums[i-1];
        }

        suffix[suffix.length - 1] = 1;
        for(int i = suffix.length-2; i>=0; i--) {
            suffix[i] = suffix[i+1]*nums[i+1];
        }

        int[] res = new int[nums.length];
        for(int i = 0; i<nums.length; i++) {
            res[i] = prefix[i]*suffix[i];
        }
        return res;

        */



        // better version

        int[] res = new int[nums.length];

        res[0] = 1;
        for(int i = 1; i<nums.length; i++) {
            res[i] = res[i-1]*nums[i-1];
        }

        
        // //res[nums.length-1] = res[nums.length-1]*1;
        // for(int i = nums.length-2; i>0; i++) {
        //     res[i] = res[i+1]*res[i-1];
        // }
        // // res[0] = 1 * res[]
        // This is bullshit
        // Don't generate suffix array and res at the same time -> use postfix!!

        int postfix = 1;
        for(int i = nums.length-1; i>=0; i--) {
            res[i] *= postfix;
            postfix *= nums[i];
        }

        return res;
    }
}  
