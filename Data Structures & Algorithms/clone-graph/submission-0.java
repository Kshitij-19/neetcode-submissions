/*
Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {

        if(node==null) return null;

        HashMap<Integer, Node> map = new HashMap<>();
        bfs(node, map);
        return map.get(node.val);
    }

    public void bfs(Node node, HashMap<Integer, Node> map) {

        Deque<Node> queue = new LinkedList<>();

        queue.offer(node);

        while(!queue.isEmpty()) {

            Node currNode = queue.pop();

            if(!map.containsKey(currNode.val)) {
                map.put(currNode.val, new Node(currNode.val));
            }
            Node newNode = map.get(currNode.val);
            
            for(Node neighbor: currNode.neighbors) {
                // HashMap will act as visited array
                if(!map.containsKey(neighbor.val)) {
                    map.put(neighbor.val, new Node(neighbor.val));
                    queue.offer(neighbor);
                }
                newNode.neighbors.add(map.get(neighbor.val));
            }
        }
    }

}