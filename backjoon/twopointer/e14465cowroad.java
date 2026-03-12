import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] a = br.readLine().split(" ");
        int n = Integer.parseInt(a[0]);
        int k = Integer.parseInt(a[1]);
        int b = Integer.parseInt(a[2]);

        int[] arr= new int[n+1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
        for (int i = 0; i < b; i++) {
            int temp = Integer.parseInt(br.readLine());
            arr[temp] = 0;
        }

//        System.out.println(Arrays.toString(arr));
        int l=1; int r=1;
        int num = 0;
        int howmany = 0;
        int min=Integer.MAX_VALUE;

        for (int i = 0; i < k; i++) {
            howmany += arr[1 + i];
        }
        for (int i = k+1; i < n+1; i++) {
            howmany += arr[i];
            howmany -= arr[i-k];

            int temp = k-howmany;
            if(min > temp)
                min = temp;

        }
        System.out.println(min);
    }
}
