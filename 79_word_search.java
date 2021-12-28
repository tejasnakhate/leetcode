class Solution {
    boolean result = false;
    public boolean exist(char[][] board, String word) {
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                // check if word starts from position i,j by applying dfs to that position
                // here i,j are index of board and index is the index of string index we are looking for which is zero at the start of string
                dfs(board, word, 0, i, j);
                if(result) break;
            }
        }
        return result;
    }
    
    private void dfs(char[][] b, String w, int index, int i, int j){
        // return if index we are looking for is outside of string meaning we found all chars in string
        if (index==w.length()) {
            result = true;
            return;
        }
        // return if index of char board is out of bounds or if string char we are looking for is not present at i,j
        if(i<0 || j<0 || i>=b.length || j>=b[0].length || b[i][j]!= w.charAt(index))
            return;
        
        // here is a little trick, since we cannot consider same char twice, we need to mark that char as we go and hence we set that char to a ' ' and before doing that we need to save it to a temp variable
        char temp = b[i][j];
        b[i][j] = ' ';
        dfs(b, w, index+1, i+1, j);
        dfs(b, w, index+1, i, j+1);
        dfs(b, w, index+1, i-1, j);
        dfs(b, w, index+1, i, j-1);
        // but we cannot modify the input board also so we need to put the char back where it was
        b[i][j] = temp;
    }
}