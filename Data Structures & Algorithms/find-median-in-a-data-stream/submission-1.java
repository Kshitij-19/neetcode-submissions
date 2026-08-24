class MedianFinder {

    ArrayList<Integer> list;

    public MedianFinder() {
        this.list = new ArrayList<>();
    }
    
    public void addNum(int num) {
        if(list.isEmpty()) {
            list.add(num);
            return;
        }

        int i = 0, j = list.size();
        while(i<j) {
            int mid = i + (j-i)/2;
            if(list.get(mid)>num) {
                j = mid;
            } else {
                i = mid+1;
            }
        }
        // if (i-1>=0) list.add(i-1,num);
        // else list.add(0,num);
        list.add(i,num);

        // System.out.println("for num " + num);
        // for(int e:list) {
        //     System.out.print(" " + e);
        // }
        // System.out.println("");
    }

    
    public double findMedian() {
        if (list==null || list.size()==0) return (double) 0.0;
        if (list.size()%2==0) {
            return (double) (list.get((int)list.size()/2) + list.get((int) (list.size()/2) - 1))/2;
        } else {
            return (double) list.get((int) (list.size()-1)/2 );
        }
    }
}
