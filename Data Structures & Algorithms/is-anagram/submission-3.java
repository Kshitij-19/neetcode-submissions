class Solution {
    public boolean isAnagram(String s, String t) {

        HashMap<Character, Integer> map = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();

        for(int i = 0; i<s.length(); i++) {
            if(map.containsKey(s.charAt(i))) map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
            else map.put(s.charAt(i), 1);
        }

        for(int i = 0; i<t.length(); i++) {
            if(map2.containsKey(t.charAt(i))) map2.put(t.charAt(i), map2.get(t.charAt(i)) + 1);
            else map2.put(t.charAt(i), 1);
        }

        System.out.println("map " + map);
        System.out.println("map2" + map2);

        // basic imp check
        if (map.size() != map2.size()) return false;

        for(Character c : map.keySet()) {
            if(map2.containsKey(c) && map.get(c).equals(map2.get(c))) continue;
            else return false;
        }

        return true;
    }
}
