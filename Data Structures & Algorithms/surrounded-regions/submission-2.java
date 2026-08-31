class Solution {

    // DFS GAVE STACK OVERFLOW

    private static final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    // DFS Approach 1

    // public void solve(char[][] board) {
    //     int row_size = board.length;
    //     int col_size = board[0].length;
    //     for(int r=0; r<row_size; r++) {
    //         for(int c=0; c<col_size; c++) {
    //             if(board[r][c]=='O') {
    //                 dfs(r,c,board);
    //             }
    //         }
    //     }
    // }

    // public boolean dfs(int r, int c, char[][] board) {
    //     if(r<0 || r>=board.length || c<0 || c>=board[0].length) return false;

    //     board[r][c] = 'X';

    //     for(int[] direction: directions) {
    //         int neighbor_row = r+direction[0];
    //         int neighbor_col = c+direction[1];

    //         if(neighbor_row<0 || neighbor_row>=board.length || neighbor_col<0 || neighbor_col>=board[0].length) {
    //             board[r][c] = 'O';
    //             return false;
    //         } else if (neighbor_row>=0 && neighbor_row<board.length && neighbor_col>=0 && neighbor_col<board[0].length && board[neighbor_row][neighbor_col]=='O') {
    //             boolean isSurrounded = dfs(neighbor_row, neighbor_col, board);
    //             if (!isSurrounded) {
    //                 board[r][c] = 'O';
    //                 return false;
    //             }
    //         }
    //     }
    //     return true;

    // }




    // DFS Approach 2


    // public void solve(char[][] board) {
    //     int row_size = board.length;
    //     int col_size = board[0].length;
    //     boolean[][] visited = new boolean[row_size][col_size];
        
    //     for(int r = 0; r < row_size; r++) {
    //         for(int c = 0; c < col_size; c++) {
    //             // If we find an unvisited 'O', explore its entire region
    //             if(board[r][c] == 'O' && !visited[r][c]) {
    //                 List<int[]> region = new ArrayList<>();
                    
    //                 // Run the DFS to check if surrounded AND collect all cells
    //                 boolean isSurrounded = dfs(r, c, board, visited, region);
                    
    //                 // If the region never touched a border, flip them all to 'X'
    //                 if (isSurrounded) {
    //                     for (int[] cell : region) {
    //                         board[cell[0]][cell[1]] = 'X';
    //                     }
    //                 }
    //             }
    //         }
    //     }
    // }

    // public boolean dfs(int r, int c, char[][] board, boolean[][] visited, List<int[]> region) {
    //     visited[r][c] = true;
    //     region.add(new int[]{r, c});
        
    //     boolean isSurrounded = true;
        
    //     // If the current cell is on the border, this region is NOT surrounded.
    //     if (r == 0 || r == board.length - 1 || c == 0 || c == board[0].length - 1) {
    //         isSurrounded = false; 
    //         // Note: We DO NOT return here! We must finish visiting the rest of the 
    //         // connected 'O's so they get marked as visited in our boolean array.
    //     }
        
    //     for(int[] direction: directions) {
    //         int neighbor_row = r + direction[0];
    //         int neighbor_col = c + direction[1];
            
    //         if(neighbor_row >= 0 && neighbor_row < board.length && 
    //            neighbor_col >= 0 && neighbor_col < board[0].length && 
    //            board[neighbor_row][neighbor_col] == 'O' && !visited[neighbor_row][neighbor_col]) {
                
    //             // We recursively check the neighbors. 
    //             boolean childSurrounded = dfs(neighbor_row, neighbor_col, board, visited, region);
                
    //             // If any child touches a border, the whole region touches a border.
    //             if (!childSurrounded) {
    //                 isSurrounded = false;
    //             }
    //         }
    //     }
        
    //     return isSurrounded;
    // }



    // BFS

    public void solve(char[][] board) {
        int ROWS = board.length;
        int COLS = board[0].length;

        capture(board);

        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (board[r][c] == 'O') {
                    board[r][c] = 'X';
                } else if (board[r][c] == 'T') {
                    board[r][c] = 'O';
                }
            }
        }
    }

    private void capture(char[][] board) {
        Queue<int[]> q = new LinkedList<>();
        int ROWS = board.length;
        int COLS = board[0].length; 
        // 1. Mark and queue border 'O's immediately
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if ((r == 0 || r == ROWS - 1 || c == 0 || c == COLS - 1) && board[r][c] == 'O') {
                    board[r][c] = 'T'; // Mark immediately!
                    q.offer(new int[]{r, c});
                }
            }
        }
        
        // 2. Process queue
        while (!q.isEmpty()) {
            int[] cell = q.poll();
            int r = cell[0], c = cell[1];
            
            // We already know it's a 'T', so we just check neighbors
            for (int[] direction : directions) {
                int nr = r + direction[0], nc = c + direction[1];
                
                // ONLY push if it's within bounds AND is an 'O'
                if (nr >= 0 && nr < ROWS && nc >= 0 && nc < COLS && board[nr][nc] == 'O') {
                    board[nr][nc] = 'T'; // Mark immediately before queuing!
                    q.offer(new int[]{nr, nc});
                }
            }
        }
    }
}
