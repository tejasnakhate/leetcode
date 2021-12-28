public class Solution {
    public List<List<String>> solveNQueens(int n) {
        char b[][] = new char[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                b[i][j] = '.';
            }
        }
        List<List<String>> result = new ArrayList<List<String>>();
        dfs(b, 0, result);
        return result;
    }
    
    private void dfs(char[][] b, int colInd, List<List<String>> result){
        if(colInd == b.length){
            result.add(convert(b));
            return;
        }
        for(int i=0; i<b.length; i++){
            // validate the placement of queen for each row in given column Index
            if(validate(b, i, colInd)){
                b[i][colInd] = 'Q';
                dfs(b, colInd+1, result);
                b[i][colInd] = '.';
            }
        }
    }
    
    private boolean validate(char[][] b, int x, int y){
        for(int i=0; i<b.length; i++){
            for(int j=0; j<b.length; j++){
            // (non_primary diagonal || primary diagonal || samerow || samecol)
                if(b[i][j]=='Q' && (x+j==y+i || x+y==i+j || x==i || y==j)){
                    return false;
                }
            }
        }
        return true;
    }
    
    private List<String> convert(char[][] b){
        List<String> res = new ArrayList<String>();
        for(int i=0; i<b.length; i++){
            String s = new String(b[i]);
            res.add(s);
        }
        return res;
    }
}