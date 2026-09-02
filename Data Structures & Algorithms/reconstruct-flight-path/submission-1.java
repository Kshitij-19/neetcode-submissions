class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        // return firstSolution(tickets);
        return secondSolution(tickets);

    }

    public LinkedList<String> secondSolution(List<List<String>> tickets) {
        HashMap<String, PriorityQueue<String>> map = new HashMap<>();

        for (List<String> ticket : tickets) {
            map.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>())
            .offer(ticket.get(1));
        } // since we are printing in reverse order we don't need to store data in PriorityQueue in reverse order

        Deque<String> stack = new ArrayDeque<>();
        stack.push("JFK");
        LinkedList<String> res = new LinkedList<>();

        // Only print when you use all the tickets or when no tickets are reamining

        while(!stack.isEmpty()) {
            String node = stack.peek();
            
            if(!map.containsKey(node) || map.get(node).isEmpty()) res.addFirst(stack.pop()); // It reaches to the dead end where there are no flights left leaving from here, then we print in backward direction and doing that is Hierholzer's Algorithm
            // addFirst is very imp here beacuse we are storing it in reverse order and storing at first location takes O(1) time in LinkedList, while ArrayList takes O(n)

            // else we don't check all neighbors, we just push smallest value neighbor
            // fetch the smallet value from PriorityQueue
            else {
                String nextNode = map.get(node).poll();
                stack.push(nextNode);
            }
            // and this will take care of printing in the best order
        }
        return res;
    }

    public ArrayList<String> firstSolution(List<List<String>> tickets) {
        HashMap<String, PriorityQueue<String>> map = new HashMap<>();

        for (List<String> ticket : tickets) {
            map.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>((a, b) -> b.compareTo(a)))
            .offer(ticket.get(1));
        }

        // Since this question asks ordering we should use topological sort
        // between dfs and bfs, we should use dfs beacuse as per example 2 we need to proceed furthur in one path

        Deque<String> stack = new ArrayDeque<>();
        stack.push("JFK");
        ArrayList<String> res = new ArrayList<>();

        while(!stack.isEmpty()) {
            String node = stack.pop();
            res.add(node);

            PriorityQueue<String> neighbors = map.get(node);
            if (neighbors != null) {
                while (!neighbors.isEmpty()) {
                    String nei = neighbors.poll();
                    stack.push(nei);
                }
            }
        }
        // Above code was very similar to final best answer, but had one flaw, lets fix it
        // Only print when you use all the tickets or when no tickets are reamining

        return res;
    }
}
