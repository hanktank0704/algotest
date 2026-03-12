import java.io.*;
import java.util.*;
public class b9663nqueen {
    static int n;
    static int[] xxx;
    static int[] yyy;
    static int[] leftright;
    static int[] rightleft;
    static int ans=0;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        leftright = new int[2*n];
        rightleft = new int[2*n];
        xxx = new int[n];
        yyy = new int[n];

        backtrack(0,0,0);
        System.out.println(ans);

    }
    public static void backtrack(int stage,int cx, int cy){
        if(stage == n){
            ans++;
        }
        else{
            for (int i = 0; i < n; i++) {
                int x = cx;
                int y = i;
                if(xxx[x] !=1 && yyy[y] != 1 && leftright[x+y]!=1 && rightleft[x-y +n-1]!=1){
                    xxx[x] = 1;
                    yyy[y] = 1;
                    leftright[x+y] = 1;
                    rightleft[x-y + n-1] = 1;

                    backtrack(stage+1, cx+1, 0);

                    xxx[x] = 0;
                    yyy[y] = 0;
                    leftright[x+y] = 0;
                    rightleft[x-y + n-1] = 0;
                }
            }

        }
    }
}
