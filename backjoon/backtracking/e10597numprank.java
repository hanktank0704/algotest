import java.io.*;
import java.util.*;
public class e10597numprank {
    static int N;
    static int[] check;
    static String s;
    static int[] input;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
        s = br.readLine();
        input = new int[s.length()+2];
        for (int i = 0; i < s.length(); i++) {
            input[i+1] = (s.charAt(i) - '0');
        }

        if(s.length() <= 9)
            N = s.length();
        else{
            N = 9 + (s.length() - 9)/2;
        }
        check = new int[N+1];

        backtrack(1, "");
    }
    public static void backtrack(int point, String ans){
        if(point == s.length()+1){
            for (int i = 1; i < N+1; i++) {
                if(check[i] != 1){
                    return;
                }
            }
            System.out.println(ans);
            System.exit(0);
        }
        else{
            if(input[point] > 0 && check[input[point]] != 1){
                check[input[point]] = 1;
                backtrack(point+1, ans + " " + input[point]);
//                System.out.println("backtrack1");
                check[input[point]] = 0;
            }

            // 이거 안하면 마지막 숫자체크할떄, 두자리 체크를 하게되면 n+2 범위까지 체크하게 되서 outofboudn arry 하게된다.
            if(point + 1 <= s.length()){
                int temp = input[point] * 10 + input[point+1];
                if(temp <= N && check[temp] != 1){
                    check[temp] = 1;
                    backtrack(point+2, ans + " " + temp);
//                System.out.println("backtrack2");
                    check[temp] = 0;
                }

            }
        }
    }
}
