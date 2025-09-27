import java.io.*;
import java.util.*;
public class tilefill2133 {
    static int n;
    static int[] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new int[31];

        dp[0] =1;
        dp[1] =0;
        dp[2] =3;
        dp[3] =0;
        for (int i = 4; i < n+1; i++) {
//          dp[i] = dp[i-2] * 3 + dp[i-4] * 2 + dp[i-6]*2 ......
            dp[i] += dp[i-2] * 3;
            for (int j = 2; 2*j <= i; j++) {
                dp[i] += dp[i - 2*j]*2;
            }
        }
//        for (int i = 0; i <= n; i++) {
//            System.out.print(dp[i] + " ");
//        }
//        System.out.println();
        System.out.println(dp[n]);
    }
}
