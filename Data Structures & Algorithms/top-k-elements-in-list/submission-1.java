class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        ArrayList<int[]> list = new ArrayList<>();

        for(int i = 0; i<nums.length; i++) {
            if (map.containsKey(nums[i])) map.put(nums[i], map.get(nums[i]) + 1);
            else map.put(nums[i], 1);
        }

        //System.out.println(map);

        for(Integer key: map.keySet()) {
            int i;
            for(i = 0; i<list.size(); i++) {
                if(list.get(i)[1] > map.get(key)) {
                    //list.add(i-1, new int[]{key, map.get(key)});
                    list.add(i, new int[]{key, map.get(key)});
                    break;
                }
            }
            if(i == list.size()) list.add(new int[]{key, map.get(key)});
            // System.out.println("new");
            // for(int j = 0; j<list.size(); j++) {
            //     System.out.println(list.get(j)[0] + " " + list.get(j)[1]);
            // }
        }

        int[] res = new int[k];
        int n = list.size()-1;
        for(int i = 0; i<k; i++) {
            res[i] = list.get(n-i)[0];
        }
        return res;
    }
}
