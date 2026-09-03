class Solution {
    private Integer[] memo;

    public int numDecodings(String s) {
        return recursionSolution(s);
    }

    public int recursionSolution(String s) {
        memo = new Integer[s.length()];
        return rec(s,0);
    }

    // wrong
    // public int rec(String s, int i) {
    //     // if (i>=s.length) return 0;
    //     if (i>=s.length) return 1; // beacuse when you reach end it a valid string

    //     // another base case, cannot start with 0 -> 0,05 both are invalid
    //     if(s.charAt(i)=='0') return 0;

    //     // take one character
    //     int singleChar = s.charAt(i)-'0';
    //     if (singleChar<1 || singleChar>2) return 0;

    //     int countOne = 1+rec(s,i+1);

    // }

    private int rec(String s, int i) {
        // BASE CASE 1: We successfully reached the end of the string
        if (i == s.length()) {
            return 1;
        }

        // BASE CASE 2: A string cannot start with a '0'
        if (s.charAt(i) == '0') {
            return 0;
        }

        // CACHE LOOKUP: If we already calculated this index, return it in O(1)
        if (memo[i] != null) {
            return memo[i];
        }

        // CHOICE 1: Take a single digit
        int ways = rec(s, i + 1);

        // CHOICE 2: Take two digits (if valid)
        // Check if there is a next digit, and if they form a number <= 26
        if (i + 1 < s.length()) {
            // Check if it's "10" to "19" OR "20" to "26"
            if (s.charAt(i) == '1' || (s.charAt(i) == '2' && s.charAt(i + 1) <= '6')) {
                ways += rec(s, i + 2);
            }
        }

        // SAVE & RETURN
        return memo[i] = ways;
    }
}
