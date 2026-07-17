class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // Deduce every string to character count
        // you will find area of objects each object being the character count
        // clup together character count which are similar * by keeping character count as the key of has HashMap

        int n = strs.length;

        HashMap<ArrayList<Integer>, ArrayList<String>> map = new HashMap<ArrayList<Integer>, ArrayList<String>>();

        for(int i = 0; i<n; i++) {
            ArrayList<Integer> key = new ArrayList<Integer>(26);
            for (int j = 0; j<26; j++) key.add(0);
            for(Character c : strs[i].toCharArray()) {
                int asciiValue = (int) c - 97;
                //System.out.println("character is " + c + " (int) c " + (int) c + " asciiValue " + asciiValue);
                key.set(asciiValue, key.get(asciiValue)+1);
            }

            //System.out.println("key " + key);

            if (map.containsKey(key)) {
                //System.out.println("key " + key + " value " + map.get(key));
                ArrayList<String> currValue = map.get(key);
                currValue.add(strs[i]);
                map.put(key, currValue);
            } else {
                map.put(key, new ArrayList<String>(List.of(strs[i])));
            }
        }

        List<List<String>> res = new ArrayList<>();

        for(ArrayList<Integer> key : map.keySet()) {
            res.add(map.get(key));
        }

        return res;
    }
}
