class Solution {
    public int longestValidParentheses(String S) {
        int N = S.length();
        int empty_add = 0;
        Stack<int[]> dp = new Stack<>(); // 0 = left bracket, 1 = right bracket
        //{bracket type, pre-stored length}
        int ans = 0;
        //int cur = 0; Stack does this for every position
        
        for (int i = 0; i < N; i++) {
            if (S.charAt(i) == '(') {
                int[] add = {0, 2}; // A pair with the fresh '(' would do length 2 at best
                dp.push(add);
                
                /*if (cur > 0) {
                    empty_add = cur;    
                }*/
                continue;
            }
            
            if (dp.isEmpty()) {
                int[] add = {1, 0}; // 0 is maximum length substring ending in ')'
                dp.push(add);
                continue;
            }
            
            if (dp.peek()[0] == 0) {
                int len = dp.peek()[1];
                ans = Math.max(ans, len);
                dp.pop();
                
                if (dp.isEmpty()) {
                    len += empty_add;
                    ans = Math.max(ans, len);
                    empty_add = len;
                }else {
                    if (dp.peek()[0] == 0) { // 
                        dp.peek()[1] += len;  
                        ans = Math.max(ans, dp.peek()[1] - 2);
                    }else {
                        ans = Math.max(ans, dp.peek()[1] + len);
                        dp.peek()[1] += len; // The dead end marking the longest substring
                        //that occured right in front of it
                    }
                }
                continue;
            }
            
            int[] triv = {1, 0};
            dp.push(triv);
        }
        
        return ans;
    }
}
