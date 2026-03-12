import java.io.*;
import java.util.*;
public class g2512budget{
    static int n;
    static int[] arr;
    static int budget;
    static long sum=0;
    static int max=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
            if(max < arr[i])
                max = arr[i];
        }
        budget = Integer.parseInt(br.readLine());

        Arrays.sort(arr);
        // for(int val : arr){
        //     System.out.print(val + " ");
        // }
        // System.out.println();

        if(budget > sum){
            System.out.println(max);
            // System.out.println("sum " + sum);
        }
        else{
            binarysearch();
        }
    }
    public static void binarysearch(){
        int l=0, r= max;
        int mid=0;
        while(l<=r){
            mid = (l+r)/2;
            int temp = checkbudget(mid);
            if(temp > budget){
                r = mid - 1;
            }
            else{
                l = mid + 1;
            }
        }
        System.out.println(r);
    }
    public static int checkbudget(int bud){
        int temp=0;
        for(int i=0; i<arr.length; i++){
            if(arr[i] < bud){
                temp += arr[i];
            }
            else{
                temp += bud;
            }
        }
        return temp;
    }
}
