import java.io.*;
import java.util.*;
public class f2792jewelbox {
    static int n,m;
    static int[] arr;
    static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[m];
        int sum=0;
        for(int i=0; i<m; i++){
            arr[i] = Integer.parseInt(br.readLine());
            if(max < arr[i])
                max = arr[i];
        }



        System.out.println();
    }
}
