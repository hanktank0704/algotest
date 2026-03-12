import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

public class b14425charset {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] token = br.readLine().split(" ");
        int n = Integer.parseInt(token[0]);
        int m = Integer.parseInt(token[1]);
        String[] arr = new String[n];
        String[] check = new String[m];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = br.readLine();
        }
        for (int i = 0; i < check.length; i++) {
            check[i] = br.readLine();
        }
        Arrays.sort(arr);
//        System.out.println(Arrays.toString(arr));
        System.out.println(binarycheck(check, arr));
    }
    public static int binarycheck(String[] target, String[] arr){
        int ans=0;
        int mid;
        for (int i = 0; i < target.length; i++) {
            int l=0;
            int r = arr.length-1;
            while(l<=r){
//                System.out.println("target: " + target[i]);
//                System.out.println(Arrays.toString(arr));
//                System.out.println(l + " " + r);
                mid = (l+r)/2;
                if(target[i].equals(arr[mid])){
                    ans++;
                    break;
                }
                else if(target[i].compareTo(arr[mid]) < 0)
                    r = mid-1;
                else if(target[i].compareTo(arr[mid]) > 0)
                    l = mid+1;
            }
        }
        return ans;
    }
}
