class MinStack {

    ArrayList<Integer> stack;
    int top;

    ArrayList<Integer> prefix;

    // MinStack(ArrayList<Integer> stack) {
    //     this.stack = stack;
    //     top=-1;
    // }
    // MinStack() {
    //     this.stack = new ArrayList<>();
    //     top=-1;
    // }

    public MinStack() {
        this.stack = new ArrayList<>();
        top=-1;

        this.prefix = new ArrayList<>();
    }
    
    public void push(int val) {
        stack.add(val);
        top++;
        
        if(top==0) prefix.add(val);
        else prefix.add(Math.min(val, prefix.get(top-1)));
    }
    
    public void pop() {
        if(top!=-1) {
            stack.remove(top);
            prefix.remove(top);
            top--;
        }
    }
    
    public int top() {
        if(top!=-1) return stack.get(top);
        else return 0;
    }
    
    public int getMin() {
        if(top!=-1) return prefix.get(top);
        else return 0;
    }
}
