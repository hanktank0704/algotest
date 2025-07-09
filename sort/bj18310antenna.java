import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class bj18310antenna {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] s = br.readLine().split(" ");
        int[] arr = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            arr[i] = Integer.parseInt(s[i]);
        }
        Arrays.sort(arr);
        if(arr.length%2==0)
            System.out.println(arr[arr.length/2 - 1]);
        else
            System.out.println(arr[arr.length/2]);

//        float sum=0;
//        for (int i = 0; i < arr.length; i++) {
//            sum+=arr[i];
//        }
//        sum = sum / arr.length;
//        System.out.println((int)Math.floor(sum));
    }
}
