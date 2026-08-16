class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // find target row using binary search then target column using binary search

        /*
        int rl = 0;
        int rr = matrix.length-1;
        while(rl<=rr) {
            int m = rl + (rr-rl)/2;
            if(target<matrix[m][0]) rr = m-1;
            else if target>matrix[m][0] rl = m+1;
            // we need to solve this with upper/lower bound because target is not necessarlily be matrix[m][0]
        } */

        int rl = 0;
        int rr = matrix.length;
        while(rl<rr) {
            int m = rl + (rr-rl)/2;
            if(matrix[m][0] > target) rr = m;
            else rl = m+1;
        }
        //System.out.println(rl);
        int checkRow = rl-1;
        if(checkRow == -1) return false;
        int cl = 0;
        int cr = matrix[checkRow].length-1;
        while(cl<=cr) {
            int m = cl + (cr-cl)/2;
            // System.out.println("cl " + cl + " cr " + cr + " m " + m);
            // System.out.println("target " + target + " cell " + matrix[checkRow][m]);
            if(matrix[checkRow][m] > target) cr = m-1;
            else if (matrix[checkRow][m] < target) cl = m+1;
            else return true;
        }
        return false;
    }
}
