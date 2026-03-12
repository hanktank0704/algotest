import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class d2470twoliquid {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] token = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(token[i]);
        }
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

        int ansr=0;
        int ansl=arr.length-1;
        int sum = 2000000001;
        for (int i = 0; i < n; i++) {
            int target = 0 - arr[i];
            int l=0;
            int r=arr.length-1;
            int mid=0;
            while(l<=r){
                mid = (l+r)/2;
                if(target == arr[mid]){
                    ansl = i;
                    ansr = mid;
                    System.out.println(arr[ansl] + " " + arr[ansr]);
                    return;
                } else if (target < arr[mid]) {
                    r = mid-1;
                    if(sum > arr[i] + arr[mid]){
                        ansl = i;
                        ansr = r;
                    }
                }
                else{
                    l = mid+1;
                    if(sum > arr[i] + arr[mid]){
                        ansl = i;
                        ansr = l;
                    }
                }
            }
//            System.out.println("target: " + target);
        }

        System.out.println(ansl + " " + ansr);
    }

}
