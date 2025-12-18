import java.io.*;
import java.util.*;
public class olympic8979 {
    static int n,k;
    static int[][] arr;
    static int[] karr = new int[4];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        int cnt=1;

        arr = new int[n+1][4];
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<4; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(i == k){
                    for(int kk=0; kk<4; kk++){
                        karr[kk] = arr[i][kk];
                    }
                }
            }
        }

        for(int i=1; i<=n; i++){
            if(i == k)
                continue;
            if(higher(i) == true){
                //System.out.println("coutnry " + i + " " + higher(i));
                cnt++;
            }
        }

        // Arrays.sort(arr, (o1,o2) -> {
        //     if(o1[1] != o2[1]){
        //         return o2[1] - o1[1];
        //     }
        //     if(o1[2] != o2[2]){
        //         return o2[2] - o1[2];
        //     }
        //     return o2[3] - o1[3];
        // });

        // System.out.println(Arrays.toString(karr));
        // System.out.println(Arrays.deepToString(arr));
        System.out.println(cnt);
    }
    public static boolean higher(int country){
        if(arr[country][1] > karr[1]){
            return true;
        }
        else if(arr[country][1] == karr[1] && arr[country][2] > karr[2]){
            return true;
        }
        else if(arr[country][1] == karr[1] && arr[country][2] == karr[2] && arr[country][3] > karr[3]){
            return true;
        }
        return false;
    }
}
