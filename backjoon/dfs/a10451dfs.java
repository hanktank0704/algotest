package dfs;
import java.io.*;
import java.util.*;
public class a10451dfs {
    static int t,n;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());

        for(int i=0; i<t; i++){
            n = Integer.parseInt(br.readLine());
            arr = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                arr[i] = Integer.parseInt(st.nextToken());
            }
        }

    }
}
