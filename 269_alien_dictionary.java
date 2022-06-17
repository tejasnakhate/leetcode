class Solution {
    Map<Character, List<Character>> adj = new HashMap();
    // this list will hold character and the ones that were adjacent to it in input sorted string
    
    Map<Character, Boolean> visit = new HashMap();
    // we will add to visit and set it to true when we are considering that char in possible sequence
    // once the entire sequence is formed we will set visit value of char to false and add that to sb
    // if char is not present in visit then it is initialized with true
    // if in recurssive call if we hit a char whose visit value is true that means it was counted before in seq
    // in such cases return empty string as their is a conflict
    
    StringBuilder sb = new StringBuilder();
    public String alienOrder(String[] words) {
        for(String word : words){
            for(char c : word.toCharArray()){
                adj.put(c, new ArrayList());
            }
        }
        
        // populate the adjacency table
        for(int i=0; i<words.length-1; i++){
            String w1 = words[i];
            String w2 = words[i+1];
            int minLen = Math.min(w1.length(), w2.length());
            
            // this condition represents input is not sorted hence return ""
            if (w1.length() > w2.length() && w1.substring(0, minLen).equals(w2.substring(0, minLen))){
                return "";
            }
            
            // add to the adjacency list of char at words[i] the char at words[i+1]
            // since word[i] char is bigger than word[i+1] as input is sorted
            // also only the first differrent character is considered during sorting
            for(int j=0; j<minLen; j++){
                if(w1.charAt(j)!=w2.charAt(j)){
                    adj.get(w1.charAt(j)).add(w2.charAt(j));
                    break;
                }
            }
        }
        
        // make dfs calls over all keys in adj
        for (char c : adj.keySet()){
            if(!visit.containsKey(c)) if(dfs(c)) return "";
        }
        
        // The topo sort created the list in the stringbuilder, reverse that
        // and then return since topo sort created it in reverse order just return that
        return sb.reverse().toString();
    }
    
    // returns true if found loops in the graph
    public boolean dfs(char c){
        // check if input char is present in visit, if present return its value from visit
        if(visit.containsKey(c)){
            return visit.get(c);
        }
        
        // if not present then initiallize it with true, this means we are considering it in currrent sequence
        visit.put(c, true);
        
        // make a recurssive call over all of its adjacent chars 
        List<Character> currList = adj.get(c);
        for(int i=0; i<currList.size(); i++){
            // if recurssive call returns true, this means loop is found
            if(dfs(currList.get(i))){
                return true;
            }
        }
        
        // once iteration through all adjacents is done then add it to the result 
        // and mark its value in visit as false this ensures that it can be reused 
        // to calculate other sequences too
        sb.append(c);
        visit.put(c, false);
        return false;
    }
}
