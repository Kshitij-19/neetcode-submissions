class Solution {
    public int orangesRotting(int[][] grid) {

        int row_size = grid.length;
        int col_size = grid[0].length;

        Deque<int[]> queue = new ArrayDeque<>();
        int freshFruitsCount = 0;
        
        for(int i=0;i<row_size;i++) {
            for(int j=0;j<col_size;j++) {
                if(grid[i][j]==2) queue.offer(new int[]{i,j});
                else if (grid[i][j]==1) freshFruitsCount++;
            }
        }
        // System.out.println("initially " + freshFruitsCount);
        // if(queue.isEmpty()) return -1;

        int minElapsed = 0;
        int[][] directions = new int[][] {{1,0}, {0,1}, {-1,0}, {0,-1}};
        boolean initialPhase = true;
        while(!queue.isEmpty()) {

            int size = queue.size();
            for(int i=0;i<size;i++) {
                int[] node = queue.poll();
                for(int[] direction: directions) {
                    int neighbor_row = node[0]+direction[0];
                    int neighbor_col = node[1]+direction[1];
                    if(isValid(neighbor_row, neighbor_col, row_size, col_size) && grid[neighbor_row][neighbor_col]==1) {
                        grid[neighbor_row][neighbor_col]=2;
                        queue.offer(new int[] {neighbor_row, neighbor_col} );
                        freshFruitsCount--;
                    }
                }
            }
            if(!queue.isEmpty()) minElapsed++;
        }
        // System.out.println("later " + freshFruitsCount);
        // return minElapsed==0? -1: minElapsed;
        return freshFruitsCount==0? minElapsed: -1;
    }

    public boolean isValid(int r, int c, int row_size, int col_size) {
        if(r>=0 && r<row_size && c>=0 && c<col_size) return true;
        return false;
    }
}
