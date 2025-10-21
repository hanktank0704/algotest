import java.io.*;
import java.util.*;
public class e2143twoarraysum {
    static int t=0;
    static int n,m;
    static int[] arr1;
    static int[] arr2;
    static int[] sum1;
    static int[] sum2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        arr1 = new int[n];
        sum1 = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr1[i] = Integer.parseInt(st.nextToken());
            if(i>0){
                sum1[i] = sum1[i-1] + arr1[i];
            }
            else{
                sum1[0] = arr1[0];
            }
        }
        m = Integer.parseInt(br.readLine());
        arr2 = new int[m];
        sum2 = new int[m];
        // 0 1 2 3 4
        // 1 3 1 2
        // 1 4 5 7
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++){
            arr2[i] = Integer.parseInt(st.nextToken());
            if(i>0){
                sum2[i] = sum2[i-1] + arr2[i];
            }
            else{
                sum2[0] = arr2[0];
            }
        }

        HashMap<Integer, Integer> hash1 = new HashMap<>();
        HashMap<Integer, Integer> hash2 = new HashMap<>();
        for(int i=0; i<n; i++){
            for(int j=i; j<n; j++){
                int sum = 0;
                if(i==0){
                    sum = sum1[j];
                }
                else{
                    sum = sum1[j] - sum1[i-1];
                }
                hash1.put(sum, hash1.getOrDefault(sum, 0) + 1);
            }
        }
        for(int i=0; i<m; i++){
            for(int j=i; j<m; j++){
                int sum = 0;
                if(i==0){
                    sum = sum2[j];
                }
                else{
                    sum = sum2[j] - sum2[i-1];
                }
                hash2.put(sum, hash2.getOrDefault(sum, 0) + 1);
            }
        }
        long ans=0;
        for(int val : hash1.keySet()){
            int temp1 = hash1.get(val);
            int temp2 = hash2.getOrDefault(t - val, 0);

            ans += (long)temp1 * temp2;
        }
        System.out.println(ans);
    }
}
