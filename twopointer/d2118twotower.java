import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class d2118twotower {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int sum=0;
        for (int i = 0; i < n; i++) {
            sum+= arr[i];
        }
        for (int i = 1; i < n; i++) {
            arr[i] += arr[i-1];
        }
        System.out.println(Arrays.toString(arr));
        int l=0; int r=0;
        int max = 0;
        while(r < n){
            int diff = arr[r] - arr[l];
            int ldiff = sum - diff;
            System.out.println("diff: " + diff + "ldiff: " + ldiff);
            if(diff < ldiff){
                r++;
            }
            else{
                l++;
            }
            int temp = Math.min(diff,ldiff);
            if (max < temp)
                max= temp;
        }
        System.out.println(max);

    }
}
