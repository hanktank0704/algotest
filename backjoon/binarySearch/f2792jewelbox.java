import java.io.*;
import java.util.*;
public class f2792jewelbox {
    static int n,m;
    static int[] arr;
    static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[m];
        int sum=0;
        for(int i=0; i<m; i++){
            arr[i] = Integer.parseInt(br.readLine());
            if(max < arr[i])
                max = arr[i];
        }

        int l=1;
        int r=max;
        int mid=0;

        while(l<=r){
            //System.out.println(l + " " + r);
            mid = (l+r)/2;
            if(check(mid) == true){
                r = mid - 1;
            }
            else{
                l = mid + 1;
            }
        }

        System.out.println(l);
    }
    static public boolean check(int cnt){
        int num=0;
        int temp=0;
        for(int i=0; i<arr.length; i++){
            if(arr[i] % cnt == 0)
                temp = arr[i]/cnt;
            else
                temp = arr[i]/cnt + 1;

            num += temp;
        }
        if(num > n)
            return false;

        return true;
    }
}
