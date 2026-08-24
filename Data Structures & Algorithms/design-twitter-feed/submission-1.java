class Twitter {

    HashMap<Integer, ArrayList<long[]>> posts;
    HashMap<Integer, HashSet<Integer>> followers;
    long time;

    public Twitter() {
        this.posts = new HashMap<>();
        this.followers = new HashMap<>();
        this.time = 0;
    }
    
    public void postTweet(int userId, int tweetId) {
        ArrayList<long[]> list;
        if(!posts.containsKey(userId)) {
            list = new ArrayList<>();
        } else {
            list = posts.get(userId);
        }
        time++;
        long[] data = new long[]{tweetId, time};
        list.add(data);
        posts.put(userId, list);

        // if(!followers.containsKey(followerId)) {
        //     ArrayList<Integer> list = new ArrayList<>();
        //     list.add(followerId);
        //     followers.put(followerId, list);
        // }
    }
    
    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<long[]> queue = new PriorityQueue<>((a,b) -> Long.compare(a[1], b[1]));
        HashSet<Integer> followersSet = followers.get(userId);
        if(followersSet!=null) {
            for(int follower: followersSet) {
                ArrayList<long[]> list = posts.get(follower);
                for(long[] data: list) {
                    if(queue.size()>10 && data[1] < queue.peek()[1]) continue;
                    queue.offer(data);
                    if(queue.size()>10) queue.poll();
                }
            }
        }
        ArrayList<long[]> list = posts.get(userId);
        if(list!=null) {
            for(long[] data: list) {
                if(queue.size()>10 && data[1] < queue.peek()[1]) continue;
                queue.offer(data);
                if(queue.size()>10) queue.poll();
            }
        }

        List<Integer> res = new ArrayList<>();
        for(int i = queue.size(); i>0; i--) {
            res.add((int) queue.poll()[0]);
        }
        int i = 0, j = res.size()-1;
        while(i<j) {
            int temp = res.get(j);
            res.set(j,res.get(i));
            res.set(i,temp);
            i++;
            j--;
        }
        return res;
    }
    
    public void follow(int followerId, int followeeId) {
        HashSet<Integer> set = followers.get(followerId);
        if(set==null) {
            set = new HashSet<>();
            followers.put(followerId, set);
        }
        set.add(followeeId);
        followers.put(followerId, set);
    }
    
    public void unfollow(int followerId, int followeeId) {
        HashSet<Integer> set = followers.get(followerId);
        if(!set.contains(followeeId)) return;
        set.remove(followeeId);
        followers.put(followerId, set);
    }
}
