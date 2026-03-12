import java.io.*;
import java.util.*;
public class d14888yunsanja {
    static int n;
    static int[] arr;
    static int[] calc;
    static int[] whattodo;
    static int plus, minus, mul, div;
    static long max = Long.MIN_VALUE;
    static long min = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        calc = new int[4];
        whattodo = new int[n-1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
//            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            calc[i] = Integer.parseInt(st.nextToken());
        }
        // seems like backtrack for me
        backtrack(0);
        System.out.println(max);
        System.out.println(min);
    }
    public static void backtrack(int stage){
        //0: +, 1: -, 2: *, 3: /
        if(stage == n-1){
//            System.out.println(Arrays.toString(whattodo));
            long temp = calculate(whattodo);
            if(temp < min)
                min = temp;
            if(temp > max)
                max = temp;
        }
        else{
            for (int i = 0; i < 4; i++) {
                if(calc[i] > 0){
                    whattodo[stage] = i;
                    calc[i]--;
                    backtrack(stage+1);
                    calc[i]++;
                }
            }
        }
    }
    public static long calculate(int[] sequence){
        long outcome = arr[0];
        for (int i = 0; i < n-1; i++) {
            if(whattodo[i] == 0){
                outcome += arr[i+1];
            }
            else if(whattodo[i] == 1){
                outcome -= arr[i+1];
            }
            else if(whattodo[i] == 2){
                outcome *= arr[i+1];
            }
            else{
                outcome /= arr[i+1];
            }
        }
        return outcome;
    }
}
//        for (int i = 0; i < n; i++) {
//        System.out.print(arr[i] + " ");
//        }
