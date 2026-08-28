class Solution {

    private static final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int numIslands(char[][] grid) {
        return dfs(grid);
    }

    public int dfs(char[][] grid) {
        int islands = 0;
        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[0].length; j++) {
                if(grid[i][j]=='1') {
                    dfs(grid,i,j);
                    islands++;
                }
            }
        }
        return islands;
    }

    public void dfs(char[][] grid, int r, int c) {

        if(!isValid(grid, r, c)) return;

        // for(int[] direction: directions) {
        //     if (grid[r+direction[0]][c+direction[1]]=='1') {
        //         grid[r+direction[0]][c+direction[1]] = '0';
        //         dfs(grid, r+direction[0], c+direction[1]);
        //     }
        // }

        grid[r][c] = '0';
        for(int[] direction: directions) {
            dfs(grid, r+direction[0], c+direction[1]);
        }
    }

    public boolean isValid(char[][] grid, int r, int c) {
        if(r<0 || c<0 || r>=grid.length || c>=grid[0].length || grid[r][c]=='0') return false;
        return true;
    }
}
