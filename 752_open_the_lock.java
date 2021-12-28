class Solution {
    public int openLock(String[] deadends, String target) {
        HashSet<String> dead_ends = new HashSet(Arrays.asList(deadends));
        HashSet<String> visited = new HashSet();
        
        Queue<String> q = new LinkedList();
        q.offer("0000");
        int level = 0;
        
        while(!q.isEmpty()){
            int size = q.size();
            while(size>0){
                String lock_pos = q.poll();
                if(dead_ends.contains(lock_pos)){
                    size--;
                    continue;
                }
                
                if(lock_pos.equals(target)){
                    return level;
                }
                
                StringBuilder sb = new StringBuilder(lock_pos);
                
                for(int i=0; i<4; i++){
                    char curr = sb.charAt(i);
                    String s1 = sb.substring(0, i) + ((curr == '9') ? 0 : curr - '0' + 1) + sb.substring(i+1);
                    String s2 = sb.substring(0, i) + ((curr == '0') ? 9 : curr - '0' - 1) + sb.substring(i+1);
                    if(!visited.contains(s1) && !dead_ends.contains(s1)){
                        q.offer(s1);
                        visited.add(s1);
                    }
                    if(!visited.contains(s2) && !dead_ends.contains(s2)){
                        q.offer(s2);
                        visited.add(s2);
                    }
                }
                size--;
            }
            level++;
        }
        return -1;
    }
}