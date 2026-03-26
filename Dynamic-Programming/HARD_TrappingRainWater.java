class Solution {
    public int trap(int[] a) {
        int N = a.length;
        long ans = 0;
        
        if (N <= 2) {
            return 0;
        }
        
        long[][] sort = new long[N][2];
        long[] pref = new long[N];
        
        pref[0] = (long) a[0];
        
        for (int i = 1; i < N; i++) {
            pref[i] = pref[i - 1] + (long) (a[i]);
        }
        
        for (int i = 0; i < N; i++) {
            sort[i][0] = (long) a[i];
            sort[i][1] = i;
        }
        
        Arrays.sort(sort, (x, y) -> Long.compare(x[0], y[0]));
        
        int L = 0;
        int R = 0;
        
        if (sort[N - 1][1] > sort[N - 2][1]) {
            R = (int) sort[N - 1][1];
            L = (int) sort[N - 2][1];
        }
        
        else {
            L = (int) sort[N - 1][1];
            R = (int) sort[N - 2][1];
        }
        
        ans += min(a[L], a[R]) * ((R - 1) - (L + 1) + 1) - (pref[R - 1] - pref[L]);
        
        for (int i = N - 3; i >= 0; i--) {
            int in = (int) sort[i][1];
            
            if (in > R) {
                ans += min(a[in], a[R]) * ((in - 1) - (R + 1) + 1) - (pref[in - 1] - pref[R]); 
                R = in;
            }
            
            else if (in < L) {
                ans += min(a[L], a[in]) * ((L - 1) - (in + 1) + 1) - (pref[L - 1] - pref[in]);
                L = in;
            }
        }
        
        return (int) (ans);
    }
    
    public static int min(int a, int b) {
        return a < b? a : b;
    }
}
