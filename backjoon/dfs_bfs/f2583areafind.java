import java.io.*;
import java.util.*;
public class f2583areafind {
    static int m, n, k;
    static int[][] arr;
    static int[][] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        visited = new int[n][m];

        for (int i = 0; i < k; i++) {
            int x1, y1, x2, y2;
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());

            for (int j = x1; j < x2; j++) {
                for (int l = y2; l < y2; l++) {
                    arr[l][j] = 1;
                }
            }
        }
        printarr();

    }
    public static void printarr(){
        System.out.println();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
