class Solution {
    public boolean isValidSudoku(char[][] board) {
        ArrayList<HashSet<Integer>> lauda = new ArrayList<>();
        for(int i = 0; i<9; i++) lauda.add(new HashSet<>());

        for(int i = 0; i<9; i++) {
            HashSet<Integer> check = new HashSet<>();

            for(int j = 0; j<9; j++) {

                if(board[i][j] == '.') continue;
                int val = Integer.parseInt(String.valueOf(board[i][j]));

                if(check.contains(val)) return false;
                check.add(val);

                int idx = getBlock(i,j);
                HashSet<Integer> set = lauda.get(idx);
                if(set.contains(val)) return false;
                set.add(val);
            }
        }

        for(int j = 0; j<9; j++) {
            HashSet<Integer> check = new HashSet<>();
            for(int i = 0; i<9; i++) {
                if(board[i][j] == '.') continue;
                int val = Integer.parseInt(String.valueOf(board[i][j]));
                if(check.contains(val)) return false;
                check.add(val);
            }
        }

        return true;
    }

    public int getBlock(int i, int j) {
        if(i<=2) {
            if(j<=2) return 0;
            else if (j<=5) return 1;
            return 2;
        } else if(i<=5) {
            if(j<=2) return 3;
            else if (j<=5) return 4;
            return 5;
        } else {
            if(j<=2) return 6;
            else if (j<=5) return 7;
            return 8;
        }
    }
}
