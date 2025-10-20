import java.io.*;
import java.util.*;
public class g2343guitarlesson {
    static int n,m;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int max=0;
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];

        long temp=0;
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            temp += arr[i];
            if(max < arr[i])
                max =arr[i];
        }

        long l = max;
        long r = temp;
        long mid=0;
        while(l<=r){
            // System.out.println("l r " + l + " " + r);
            mid = (l+r)/2;
            if(checkpossible(mid) == true){
                r = mid -1;
            }
            else{
                l = mid+1;
            }
        }
        System.out.println(l);
    }
    public static boolean checkpossible(long len){
        int sum=0;
        int cnt=1;
        // System.out.println("len : " + len);
        for(int i=0; i<arr.length; i++){
            if(sum + arr[i] > len){
                cnt++;
                sum = arr[i];
            }
            else{
                sum+=arr[i];
            }
            if(cnt > m)
                return false;
        }
        return true;
    }
}
