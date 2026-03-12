import java.io.*;
import java.util.*;
public class b14501job {
    // 뭔가 뒤에서 부터 시작하는 그리디 알고리즘인것 같다?
    static int n;
    static int[] day;
    static int[] pay;
    static int[] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        day = new int[n+1];
        pay = new int[n+1];
        dp = new int[n+2];
        for (int i = 1; i < n+1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            day[i] = Integer.parseInt(st.nextToken());
            pay[i] = Integer.parseInt(st.nextToken());
        }

        dp[n] = 0;
        if(day[n] == 1)
            dp[n] = pay[n];

        for (int i = n-1; i >= 1; i--) {
            int cand1 = dp[i+1];
            int cand2=0;
            //i =8, n=10
            if(i + day[i] -1 <= n){
                cand2 = pay[i] + dp[i + day[i]];
            }
            // i i+1 i+2 i+3
            // 3  x   x   o

            dp[i] = Math.max(cand1,cand2);
        }

        System.out.println(dp[1]);
    }
}
