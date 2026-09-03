class Solution {
    public int countSubstrings(String s) {
        int resLen = 0, resIdx = 0;
        int res=0;
        for (int i = 0; i < s.length(); i++) {
            // odd length
            int l = i, r = i;
            while (l >= 0 && r < s.length() &&
                   s.charAt(l) == s.charAt(r)) {
                if (r - l + 1 > resLen) {
                    resIdx = l;
                    resLen = r - l + 1;
                }
                res++;
                l--;
                r++;
            }

            // even length
            l = i;
            r = i + 1;
            while (l >= 0 && r < s.length() &&
                   s.charAt(l) == s.charAt(r)) {
                if (r - l + 1 > resLen) {
                    resIdx = l;
                    resLen = r - l + 1;
                }
                res++;
                l--;
                r++;
            }
        }

        // return s.substring(resIdx, resIdx + resLen);
        return res;
    }
}
