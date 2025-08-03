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

        arr = new int[m][n];
        visited = new int[m][n];

        for (int i = 0; i < k; i++) {
            int x1, y1, x2, y2;
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());

            for (int j = 0; j < m; j++) {
                for (int l = 0; l < n; l++) {
                    arr[j][l] = 1;
                }
            }
            for (int j = x1; j < x2; j++) {
                for (int l = y1; l < y2; l++) {
                    arr[j][l] = 0;
                }
            }
            System.out.println();
            for (int j = 0; j < m; j++) {
                for (int l = 0; l < n; l++) {
                    System.out.print(arr[i][j] + " ");
                }
                System.out.println();
            }

        }

    }
}
