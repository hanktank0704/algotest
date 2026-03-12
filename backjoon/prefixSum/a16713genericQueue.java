import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;

public class a16713genericQueue {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n=0, q=0;
        String[] temp = br.readLine().split(" ");
        n = Integer.parseInt(temp[0]);
        q = Integer.parseInt(temp[1]);
        int[] arr = new int[n+1];
        String[] tokens = br.readLine().split(" ");
        arr[0] = 0;
        for (int i = 1; i < n+1; i++) {
            arr[i] = Integer.parseInt(tokens[i-1]);
            arr[i] = arr[i] ^ arr[i-1];
        }
        int ans = 0;
        for (int i = 0; i < q; i++) {
            int a,b;
            String[] as = br.readLine().split(" ");
            a= Integer.parseInt(as[0]);
            b= Integer.parseInt(as[1]);
            ans = ans ^ (arr[b] ^ arr[a-1]);
//            System.out.println("arr[a] ^ arr[b] : " + (arr[a] ^ arr[b]));
        }
        System.out.println(ans);
//        System.out.println(n + " " + q);
//        System.out.println(Arrays.toString(arr));
    }
}
