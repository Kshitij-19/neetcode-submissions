class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        ArrayList<int[]> list = new ArrayList<>();
        for(int i = 0; i<position.length; i++) {
            list.add(new int[]{position[i], speed[i]});
        }
        Collections.sort(list, (a,b) -> Integer.compare(b[0], a[0]));
        double[] timeTaken = new double[list.size()];
        for(int i = 0; i<list.size(); i++) {
            timeTaken[i] = (double) (target-list.get(i)[0])/list.get(i)[1];
        }
        int fleet = 1;
        double currTime = timeTaken[0];
        for(int i = 1; i<timeTaken.length; i++) {
            //System.out.println("currTime " + currTime + " timeTaken[i] " + timeTaken[i]);
            if(list.get(i)[0]<list.get(i-1)[0] && currTime>=timeTaken[i]) {
                // add to currFleet

            } else {
                fleet++;
                currTime = timeTaken[i];
            }
        }
        return fleet;
    }
}
