import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class c2230numpick {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] token = br.readLine().split(" ");
        int n = Integer.parseInt(token[0]);
        int m = Integer.parseInt(token[1]);
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
//        System.out.println(Arrays.toString(arr));
        Arrays.sort(arr);
        int l=0; int r=0;
        int min = Integer.MAX_VALUE;
        while (r < n) {
            int diff = arr[r] - arr[l];
            if(diff < m){
                r++;
            } else if (diff == m) {
                l++;
                r++;
                min = diff;
            }
            else{
                l++;
                if(diff < min)
                    min = diff;
            }
        }
        System.out.println(min);
    }
}
// 1 3 5
// 1 3 5 7 9 11 13 110
//
