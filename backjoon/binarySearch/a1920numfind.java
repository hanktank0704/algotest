import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

public class a1920numfind {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] a = br.readLine().split(" ");
        int[] arr = new int[n];
        for (int i = 0; i < a.length; i++) {
            arr[i] = Integer.parseInt(a[i]);
        }

        int m = Integer.parseInt(br.readLine());
        String[] b = br.readLine().split(" ");
        int[] check = new int[m];
        for (int i = 0; i < b.length; i++) {
            check[i] = Integer.parseInt(b[i]);
        }

        Arrays.sort(arr);
//        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < b.length; i++) {
            System.out.println(binarycheck(check[i], arr));
        }


    }
    //1 2 3 4 5
    public static int binarycheck(int target, int[] arr){
        int ans=0;
        int start = 0;
        int end = arr.length-1;
        while(start <= end){
//            System.out.println("target: " + target);
//            System.out.println(start + " " + end);
            if(target == arr[(start + end)/2])
                return 1;
            if(target < arr[(start + end)/2]){
                end = (start + end)/2 -1;
            } else {
                start = (start + end)/2 +1;
            }
        }
        return ans;
    }
}
