class Solution {

    public String encode(List<String> strs) {

        StringBuilder res = new StringBuilder();
        for (String curr : strs) {
            res.append(curr.length()).append("#").append(curr);
        }
        return res.toString();
    }

    public List<String> decode(String str) {
        // If we use the logic "length of string" + "#" + "string itself"
        // And the string might contain anyof deliminating characters or the 
        // or the characters itself that we are using for this logic say like ""length of string" + "#""
        // then still we don't care or worry because we simply gonna skip or copy paste
        // those length of characters and we will eventually end up to the next ""length of string" + "#"" of next string

        List<String> res = new ArrayList<>();

        int i=0;
        while (i<str.length()) {
            int j=i;
            while(str.charAt(j) != ('#')) {
                j++;
            }
            int len = Integer.parseInt(str.substring(i,j));
            res.add(str.substring(j+1, j+1+len));
            i = j+1+len;
        }
        return res;
    }
}
