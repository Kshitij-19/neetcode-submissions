class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        
        // Do I need to create a graph -> no

        // Dijktra's Algorithm

        int[] distance = new int[n+1]; // this will also act as a visited array
        for(int i=0;i<=n;i++) distance[i]=Integer.MAX_VALUE;

        PriorityQueue<int[]> queue = new PriorityQueue<>((a,b) -> Integer.compare(a[1],b[1]));

        queue.offer(new int[] {k,0});
        distance[k]=0;

        // wait, how will I get the neighbors -> I'll require the adj list

        ArrayList<ArrayList<int[]>> adj = new ArrayList<>();
        for(int i=0;i<=n;i++) adj.add(new ArrayList<>());
        for(int i=0;i<times.length;i++) {
            adj.get(times[i][0]).add(new int[]{times[i][1], times[i][2]});
        }
        
        while(!queue.isEmpty()) {
            int[] node = queue.poll();
            // System.out.println("node = " + node[0]);
            for(int[] nei: adj.get(node[0])) {
                int cost = distance[node[0]]+nei[1];
                if(cost<distance[nei[0]]) {
                    distance[nei[0]] = cost;
                    queue.offer(new int[]{nei[0], cost});
                    // System.out.println("new node added " + nei[0] + " with cost " + cost);
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for(int i=1;i<distance.length;i++) max=Math.max(max,distance[i]);

        return max==Integer.MAX_VALUE? -1: max;
    }
}
