/*
So the idea here is quiet simple. We are first sorting courses with the one ending earliest
occuring first. Now, we also need a maxheap to keep track of the max duration course we have 
taken so far. Then, we iterate through courses list and we keep track of the total duration 
of the courses so far. We try to take the current course by adding it to total duration and
checking if we are exceding the last day of that course. If we dont then we take that course,
add its duration to total duration and add course to maxHeap. 
If we we are then we first poll the max duration course from the heap, substract its duration 
and then add current duration and check if we are exceding the max duration. If we are then 
we leave that course, else we take that course and drop the course with max duration.
*/

class Solution {
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (c1, c2) -> (c1[1] - c2[1]));
        
        PriorityQueue<int[]> maxHeap = new PriorityQueue<int[]> ((c1, c2) -> (c2[0]-c1[0]));
        
        int prev = 0;
        
        for(int[] c : courses) {
            
            // if we can take the course without exceding the last day then just take it
            if(prev+c[0] <= c[1]) {
                prev+=c[0];
                maxHeap.add(new int[]{c[0], c[1]});
            } 
            
            // else, we try to take the course by removing the max duration course and taking
            // current course if that is possible. else we just ignore current course
            else if(!maxHeap.isEmpty()) {
                int[] prevCourse = maxHeap.peek();
                
                
                // this is an important step, we are comparing with prev here and not c[1]*********
                if(prev-prevCourse[0]+c[0] <= prev) {
                    maxHeap.poll();
                    maxHeap.add(new int[]{c[0], c[1]});
                    prev-=prevCourse[0];
                    prev+=c[0];
                }
            }
        }
        
        // the total courses taken so far are stored in maxHeap so we just return maxheap size
        return maxHeap.size();
    }
}