class CombinationIterator {
    Stack<Integer> stack;
    char[] chars;
    int len, maxind, k;
    public CombinationIterator(String characters, int combinationLength) {
        // a stack to keep track of indexes
        this.stack = new Stack();
        // char array of the input string
        this.chars = characters.toCharArray();
        // keep track of length of input
        this.len = characters.length();
        // max index in input (this var is unnecessary but it was a bit confusing for me dealing with len-1)
        this.maxind = characters.length()-1;
        // the length of combination we need to find
        this.k = combinationLength;
    }
    
    public String next() {
        // we need to keep track of the previous element we are removing to make next combo
        int prev;
        // prev is initialied to -1 for the very first time when next is called i.e. when stack is empty
        // else it is stack top element
        if(!stack.isEmpty())prev = stack.pop();
        else prev = -1;
        
        // now, we need to iterate until stack size is not equal to combination size we need to find
        while(stack.size()<k) {
            // the ideal way to find the next combo is by adding next element in combo since this is the
            // the index of next lexographically bigger character in input. This is what is done here,
            // every time we try to add prev+1 to stack and if prev+1==maxind, this means we reached end
            // of indexes and we leave that element and pop the next element as prev
            if(prev+1<=maxind) {
                stack.push(++prev);
            }
            else {
                prev = stack.pop();
            }
        }
        
        // in the end we call this function which traverses stack from bottom to top and creates a string
        return getString();
    }
    
    public boolean hasNext() {
        // if stack is empty this means it is the very firs time this func is called. Means more combos exist
        if(stack.isEmpty()) return true;
        
        // else, we check if the element at the bottom of stack is equals to len-k. if it is then their
        // are no more combos possible eg - for [0,1,2,3] k=2, the last combo will be [2,3]. Here stack
        // top is 3 and bottom is 2. 2=len(ip)-k = 4-2.
        Iterator<Integer> it = stack.iterator();
        if(!it.hasNext()) return false;
        int last = it.next().intValue();
        if(last == len-k) return false;
        
        // else we return true
        return true;
    }
    
    // this function just traverses stack from bottom to top and appends the chars at corresponding index
    // to the output
    public String getString() {
        Iterator<Integer> it = stack.iterator();
        StringBuilder sb = new StringBuilder();
        while(it.hasNext()) {
            sb.append(chars[it.next().intValue()]);
        }
        return sb.toString();
    }
}

/**
 * Your CombinationIterator object will be instantiated and called as such:
 * CombinationIterator obj = new CombinationIterator(characters, combinationLength);
 * String param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
