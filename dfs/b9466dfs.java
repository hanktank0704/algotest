package dfs;

import java.io.*;
import java.util.*;
public class b9466dfs {
    static int t,n;
    // static ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
    static ArrayList<Integer> arr = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        t = Integer.parseInt(br.readLine());
        for(int ii=0; ii<t; ii++){
            n = Integer.parseInt(br.readLine());
            // for(int i=0; i<n+1; i++){
            //     arr.add(new ArrayList<>());
            // }
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=1; i<n+1; i++){
                //arr[i] = Integer.parseInt(st.nextToken());
                arr.add(Integer.parseInt(st.nextToken()));
            }
        }



    }
}
