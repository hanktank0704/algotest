import java.io.*;
import java.util.*;
public class c2661goodnumsequence {
    static int n;
    static int[] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        backtrack(0);

    }
    public static void backtrack(int stage){
        if(stage == n){
            if(check(arr,stage) == false)
                return;
//            System.out.println(Arrays.toString(arr));
            for (int val : arr) {
                System.out.print(val);
            }
            System.exit(0);
        }
        else{
            for (int i = 1; i <= 3; i++) {
                arr[stage] = i;
                if(check(arr, stage) == false)
                    continue;
                backtrack(stage+1);
            }

        }
    }
    public static boolean check(int[] arr ,int stage){
        boolean aaa = true;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stage; i++) {
            sb.append(arr[i]);
        }

        String s = sb.toString();
        for (int i = 1; i <= stage/2; i++) {
            String s1 = s.substring(stage - i, stage);
            String s2 = s.substring(stage - i * 2, stage - i);

            if(s1.equals(s2)){
                return false;
            }
        }
        return aaa;
    }
}
