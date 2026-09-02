class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        // return usingDijktra(times, n,k);
        return usingBellmanFord(times, n,k);
    }

    public int usingDijktra(int[][] times, int n, int k) {

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

    public int usingBellmanFord(int[][] times, int n, int k) {

        // No need to create graph
        // Initialize distance array and update it n-1 times

        // why n-1 times -> beacuse the order of edges can be in any order in input edges array and if source is at bottom then it will require one complete iteration to unlock distance of node corresponding to source array, and if that node is in second last location in input edges array then it takes another complete iteration to unlock node corresponding to that ans so on until total n-1 times in worst case where we will unlock all nodes from infinite distance

        // Most Imp -> Bellman Ford works for both negative weights and cycle even with negative weight
        // negative weight cycle using Dijktra's algorithm will end up in TLE beacuse new updated distance will be more nagative means more smaller and this continues infinitely
        // If we want to detect negative weight cycle then after running it n-1 times, run it 1 more time (total n time), if the distance array updates then there is negative cycle beacuse running it n-1 times garruntes the smallest distance, and if distance furthur reduces on nth time then this will keep continuing as there is a negative cycle present

        int[] distance = new int[n+1];
        for(int i=0;i<=n;i++) distance[i] = Integer.MAX_VALUE;

        distance[k]=0;

        for(int i=0;i<n-1;i++) {
            for(int[] time: times) {
                int u=time[0], v=time[1], cost=time[2];
                if(distance[u]==Integer.MAX_VALUE) continue;
                else if (distance[u]+cost<distance[v]) distance[v] = distance[u]+cost;
            }
        }

        int max = Integer.MIN_VALUE;
        for(int i=1;i<distance.length;i++) max=Math.max(max,distance[i]);

        return max==Integer.MAX_VALUE? -1: max;
    }
}
