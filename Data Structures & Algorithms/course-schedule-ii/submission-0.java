class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // topological sort

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        int[] indegree = new int[numCourses];

        for(int i=0;i<numCourses;i++) list.add(new ArrayList<>());

        // for [a,b] where a requires b, store b->a which means a has 1 inward arrow or 1 requirement
        for(int[] pair: prerequisites) {
            list.get(pair[1]).add(pair[0]);
            indegree[pair[0]]++;
        }

        Deque<Integer> queue = new ArrayDeque<>();
        for(int i=0;i<numCourses;i++) {
            if (indegree[i]==0) queue.offer(i);
        }

        int[] res = new int[numCourses];
        int courseDone = 0;
        while(!queue.isEmpty()) {
            int course = queue.pop();
            res[courseDone] = course;
            courseDone++;
            for(int neighbor: list.get(course)) {
                indegree[neighbor]--;
                if(indegree[neighbor]==0) queue.offer(neighbor);
            }
        }
        return courseDone==numCourses? res : new int[0];
    }
}