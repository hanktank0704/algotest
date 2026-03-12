import java.io.*;
import java.util.*;
public class tile11727 {
    static int n;
    static int[] ans;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        ans = new int[n+3];


        ans[0] = 0;
        ans[1] = 1;
        ans[2] = 3;
        for (int i = 3; i < n+1; i++) {
            ans[i] = ans[i-1]  + (ans[i-2] * 2);
            ans[i] = ans[i] % 10007;
        }
//        for (int val : ans) {
//            System.out.print(val +  " ");
//        }
//        System.out.println();
        System.out.println(ans[n] % 10007);
    }
}
