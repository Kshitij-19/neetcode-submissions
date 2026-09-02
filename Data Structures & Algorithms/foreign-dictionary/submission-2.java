class Solution {
    public String foreignDictionary(String[] words) {

        notes();
        
        return solutionOne(words);
      
    }

    public void notes() {
        // Input
        /* Think of input: wrt, wrf => this means t comes before f, so t->f
        wrtf, er => this means w comes before e, so w->e ... and so on
        then solution would be=> t,f,w,e or w,e,t,f or t,w,f,e or so on
        if there is another input er, tz then e->t so now sol becomes w,e,t,f

        think of input: ab,abe => this is valid
        but if abe, ab => this is invalid, if substring comes later in given input then its invalid input

        now think of this: a,c => a->c
        a,b => a->b
        b,c => b->c
        then the grapgh could become a->b->c and a->c, like the graphs shown in Dijktra's and krushkal's algorithm, which are valid graphs

        in that case we either need to omit a->c or we need to proceed with such graphs

        also if input is: we,ee => w->e and then ee,we then=> e->w which creates a cysle and is invalid no return ""

        */
    }



    public String solutionOne(String[] words) {

        if(words.length==1) return words[0];

        HashMap<Character, ArrayList<Character>> adj = buildGraph(words);
        // HashMap<Character, ArrayList<Character>> adj = buildGraphAlsoContainingAmbigiousAlphabets(words);
        if (adj==null) return "";

        for(char key: adj.keySet()) {
            System.out.println("for key " + key);
            for(char node: adj.get(key)) System.out.print(" -> " + node);
            System.out.println("");
        }

        // its an ordering problem so topological sort is needed
        HashMap<Character, Integer> inorder = calculateInorder(adj);

        for (String word : words) {
            for (char c : word.toCharArray()) {
                inorder.putIfAbsent(c, 0);
            }
        }

        // there could be multiple nodes with inorder 0, not a good way to find using hashmap, in hashmap in order to find character with inorder 0 we need to make sure it does not comes in any other values 

        // there could be multiple individual graphs like w->e and r->t->f so we need to start with all zero inorder nodes

        Deque<Character> queue = new ArrayDeque<>();
        for(Character key: inorder.keySet()) if(inorder.get(key)==0) queue.offer(key);

        // while(!queue.isEmpty()) {
        //     // pass all zero degree nodes and run dfs for each this will give w->e and then r->t->f or just do the below one this will mixmatch it but keep the order
        // }
        StringBuilder res = new StringBuilder();
        while(!queue.isEmpty()) {
            char alphabet = queue.poll();
            res.append(alphabet);
            ArrayList<Character> nodes = adj.get(alphabet);
            if(nodes==null || nodes.isEmpty()) continue;
            for(char node: nodes) {
                if(inorder.containsKey(node) && inorder.get(node)>0) {
                    inorder.put(node, inorder.get(node)-1);
                    if (inorder.get(node)==0) queue.offer(node);
                }
            }
        }
        // IMPPP I missed this as well in my solution earlier
        // AT THE VERY END OF solutionOne:
        if (res.length() != inorder.size()) {
            return ""; // A cycle was detected, or a rule was broken
        }
        return res.toString();
    }

    public HashMap<Character, ArrayList<Character>> buildGraph(String[] words) {
        HashMap<Character, ArrayList<Character>> adj = new HashMap<>();

        String prevWord = words[0];
        for(int i=1;i<words.length;i++) {
            String currWord = words[i];

            // int prevIndex = 0, currIndex=0;
            int index = 0;
            while(index<prevWord.length() && index<currWord.length()) {
                if (prevWord.charAt(index)!=currWord.charAt(index)) {
                    adj.computeIfAbsent(prevWord.charAt(index), k->new ArrayList<>()).add(currWord.charAt(index));
                    // Its an undirected graph so no need to add other side
                    break;
                } else index++;
            }

            // either mapping was done or index crossed one of the word length
            if(index==currWord.length() && index<prevWord.length()) return null; // because then its invalid
            // else mapping is done or there were words exactly same like abc and abc

            prevWord = currWord;
        }

        return adj;
    }

    public HashMap<Character, Integer> calculateInorder(HashMap<Character, ArrayList<Character>> adj) {
        HashMap<Character, Integer> inorder = new HashMap<>();

        // Pre-populate EVERY character so no letters are left behind -> I didn't do this earlier
        // for (String word : words) {
        //     for (char c : word.toCharArray()) {
        //         inorder.putIfAbsent(c, 0);
        //     }
        // }

        for(Character key: adj.keySet()) {
            ArrayList<Character> nodes = adj.get(key);
            for(Character node: nodes) inorder.put(node, inorder.getOrDefault(node,0)+1);
            if(!inorder.containsKey(key)) inorder.put(key,0);
        }
        return inorder;
    }


    public HashMap<Character, ArrayList<Character>> buildGraphAlsoContainingAmbigiousAlphabets(String[] words) {
        // previous build graph used to ignore alphabets which could not determine order like for input ape and dzy, my previous used to just determine a->d (a should be before d) and used to completely ignore p,e,z,y because order cannot be determined for these alphabets just using this input. But its expcted in answer so...
        HashMap<Character, ArrayList<Character>> adj = new HashMap<>();

        String prevWord = words[0];
        for(int i=1;i<words.length;i++) {
            String currWord = words[i];

            // int prevIndex = 0, currIndex=0;
            int index = 0;
            while(index<prevWord.length() && index<currWord.length()) {
                if (prevWord.charAt(index)!=currWord.charAt(index)) {
                    adj.computeIfAbsent(prevWord.charAt(index), k->new ArrayList<>()).add(currWord.charAt(index));
                    // Its an undirected graph so no need to add other side
                    break;
                } else index++;
            }

            // either mapping was done or index crossed one of the word length
            if(index==currWord.length() && index<prevWord.length()) return null; // because then its invalid
            // else mapping is done or there were words exactly same like abc and abc

            prevWord = currWord;
        }
        return adj;
    }

}
