class Solution {
    ArrayList<ArrayList<Integer>> adj;
    public void buildAdjacencyList(int[][] edges) {
        adj = new ArrayList<>();
        for(int i=0;i<edges.length;i++) adj.add(new ArrayList<>());
        adj.add(new ArrayList<>()); // extra add due to 1 based indexing
        for(int i=0;i<edges.length;i++) {
            adj.get(edges[i][0]).add(edges[i][1]);
            adj.get(edges[i][1]).add(edges[i][0]);
        }
    }

    public int[] findRedundantConnection(int[][] edges) {
        // buildAdjacencyList(edges);

        // return bfs(edges);
        return topologicalSort(edges);
    }

    // Never use BFS for detecting cycle or topological sort
    // public int[] bfs(int[][] edges) {
    //     Deque<Integer> queue = new ArrayDeque<>();

    // }

    public int[] topologicalSort(int[][] edges) {
        int n = edges.length;
        int[] indegree = new int[n + 1];
        List<List<Integer>> adj = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
            indegree[u]++;
            indegree[v]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 1) q.offer(i);
        }

        while (!q.isEmpty()) { // -> this removes leaf nodes
            int node = q.poll();
            indegree[node]--;
            for (int nei : adj.get(node)) {
                indegree[nei]--;
                if (indegree[nei] == 1) q.offer(nei);
            }
        }

        for (int i = edges.length - 1; i >= 0; i--) {
            int u = edges[i][0], v = edges[i][1];
            if (indegree[u] == 2 && indegree[v] == 2) // Every single cycle node ends up with a final degree of exactly 2
                return new int[]{u, v};
        }
        return new int[0];
    }

}
