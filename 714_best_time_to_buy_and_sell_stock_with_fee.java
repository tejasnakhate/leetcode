class Solution {
    public int maxProfit(int[] prices, int fee) {
        int[] profitAfterSelling = new int[prices.length];
        int[] fundsafterBuying = new int[prices.length];
        fundsafterBuying[0] = -prices[0];
/*
profit is the max value of profit till i-1, and profit after selling at i which is obtained by adding funds at i-1 + price at i - fee. 
funds at i will be max value of funds at i-1 or funds remaining after buying at i which is obtained by substracting price at i from profit at i
*/            
        
        for(int i=1; i<prices.length; i++){
            profitAfterSelling[i] = Math.max(profitAfterSelling[i-1], fundsafterBuying[i-1]+prices[i]-fee);
            fundsafterBuying[i] = Math.max(fundsafterBuying[i-1], profitAfterSelling[i]-prices[i]);
        }
        return profitAfterSelling[prices.length-1];
    }
}