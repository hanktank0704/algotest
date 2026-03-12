import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class c2295sumofthree {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> minusset = new HashSet<>();

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
//            set.add(arr[i]);
        }
        Arrays.sort(arr);
//        System.out.println(Arrays.toString(arr));

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                set.add(arr[i]+arr[j]);
            }
        }
//        System.out.println();
        for (int i = n-1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if(set.contains(arr[i] - arr[j])){
                    System.out.println(arr[i]);
                    return;
                }
            }
        }
//        int ans = 0;
//        for(int num: minusset){
//            if(set.contains(num) && num > ans)
//                ans = num;
//        }
//        System.out.println();
//        System.out.println(ans);

    }
    public static int findarr(int[] arr, int target){
        int l=0, r=0;
        int mid = (l+r)/2;
        int ans=0;
        while(l<=r){
            if(arr[mid] == target){
                ans = target;
            }
            if(arr[mid] < target){
                l = mid+1;
            }
            if(arr[mid] > target){
                r = mid-1;
            }
        }
        return ans;
    }
}
