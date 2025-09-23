import java.io.*;
import java.util.*;
public class addonetwothree15989 {
    static int[][] dp;
    static int input;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        dp = new int[10001][4];
        dp[0][1] = 1;
        dp[0][2] = 1;
        dp[0][3] = 1;

        findsum();
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int temp = Integer.parseInt(br.readLine());
            System.out.println(dp[temp][3]);
        }
    }
    public static void findsum(){
        for (int i = 1; i < 10001; i++) {
            for (int j = 1; j < 4; j++) {
                for (int k = 1; k <= j; k++) {
                    if(i>=k)
                        dp[i][j] += dp[i-k][k];
                }
            }
        }
    }
}
// dp[1] = dp[0] + 1;       // 1
// dp[2] = dp[0] + dp[1] = 2;       // 1 1, 2
// dp[3] = dp[0] + dp[1] + dp[2] = 3; // 1 1 1 , 1 2, 3
// dp[4] = 4                            // 1 1 1 1, 1 1 2, 1 3, 2 2

// dp[ sum ][ biggestnum]
// dp[1][1] = 1  // 1
// dp[1][2] = 1  // 1 1
// dp[1][3] = 1  // 1 1 1

// dp[2][1] = 0  // 1
// dp[2][2] = 2  // 1 1, 2
// dp[2][3] = 2  // 1 1 1, 1 2
// dp[2][4] = 3  // 1 1 1 1,  1 1 2, 2 2

// dp[3][1] = 1  // 1
// dp[3][2] = 2  // 1 1, 2
// dp[3][3] = 3  // 1 1 1, 1 2, 3
// dp[3][4] = 4  // 1 1 1 1, 1 1 2, 1 3, 2 2
