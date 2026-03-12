import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

public class f2805treecut {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] token = br.readLine().split(" ");
        int n = Integer.parseInt(token[0]);
        long m = Integer.parseInt(token[1]);
        String[] token1 = br.readLine().split(" ");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(token1[i]);
        }

        Arrays.sort(arr);
        int l=0;
        int r=arr[n-1];
        int mid=0;
        long sum=0;
        while(l<=r){
            mid = (l+r)/2;
            sum = checktree(arr, mid);
            if(sum < m){
                r = mid -1;
            } else if (sum >= m) {
                l = mid +1;
            }
//            System.out.println(l+ " " + r);
        }
        System.out.println(r);
    }
    public static long checktree(int[] arr, int tree){
        long sum=0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] <= tree)
                continue;
            else{
                sum += arr[i] - tree;
            }

        }
        return sum;
    }
}
