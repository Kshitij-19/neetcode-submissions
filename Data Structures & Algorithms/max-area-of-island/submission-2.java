class Solution {

    int[] parent;
    int[] size;
    int max;
    // private class DisjointSet {
    //     int[] parent;
    //     int[] size;
    //     public DisjointSet(int n) {
    //         this.parent = new int[n+1];
    //         this.size = new int[n+1];

    //         for(int i = 0; i<n; i++) {
    //             parent[i] = i;
    //             size[i]=1;
    //         }
    //     }
    // }
    public void setData(int n) {
        parent = new int[n+1];
        size = new int[n+1];

        for(int i = 0; i<=n; i++) {
            parent[i] = i;
            size[i]=1;
        }
        max = 0;
    }

    public int maxAreaOfIsland(int[][] grid) {
        return SolutionUsingUnionDisjointSet(grid);
    }

    public int SolutionUsingUnionDisjointSet(int[][] grid) {

        int row_size = grid.length;
        int col_size = grid[0].length;
        //DisjointSet set = new DisjointSet(row_size * col_size);
        setData(row_size * col_size);
        int[][] directions = new int[][] {{1,0}, {0,1}, {-1,0}, {0,-1}};

        for(int r = 0; r<row_size; r++) {
            for(int c = 0; c<col_size; c++) {
                if(grid[r][c] == 1) { // we may repeatedly come to already visited node using this
                    for(int[] direction: directions) {
                        int neighbor_row = r+direction[0];
                        int neighbor_col = c+direction[1];
                        if(isValid(neighbor_row, neighbor_col, row_size, col_size) && grid[neighbor_row][neighbor_col]==1) {
                            performUnion(r*col_size + c , neighbor_row*col_size + neighbor_col);
                        }
                    }

                    max = Math.max(max, findSize(r*col_size + c));
                }
            }
        }
        return max;
    }

    public boolean isValid(int r, int c, int row_size, int col_size) {
        if(r<0 || r>=row_size || c<0 || c>=col_size) return false;
        return true;
    }

    public void performUnion(int u, int v) {
        int parent_u = findParent(u);
        int parent_v = findParent(v);
        if(parent_u==parent_v) return; // they are already in a same graph

        // else connect these nodes and make them under same graph

        // if(size[u] > size[v]) {
        //     parent[v] = parent_u;
        //     size[u] += size[v];
        //     max = Math.max(max, size[u]);
        // } else { // "==" can be handeled in either of the case
        //     parent[u] = parent_v;
        //     size[v] += size[u];
        //     max = Math.max(max, size[v]);
        // }

        // Above code was WRONG because: parent[v] = parent_u; Attaching to v instead of v's root
        // size[u] += size[v];   // WRONG: Updating u's size instead of u's root

        if(size[parent_u] > size[parent_v]) { // if graph u has bigger size then we are attaching smaller graph v to bigger graph u
            parent[parent_v] = parent_u;
            size[parent_u] += size[parent_v];
        } else { 
            parent[parent_u] = parent_v;
            size[parent_v] += size[parent_u];
        }
    }

    public int findParent(int node) {
        if(node == parent[node]) return node;
        return parent[node] = findParent(parent[node]);
    }

    public int findSize(int node) {
        int parent = findParent(node);
        return size[parent];
    }
}
