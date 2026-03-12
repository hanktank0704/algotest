import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class b11660sectionsum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] token = br.readLine().split(" ");
        int n = Integer.parseInt(token[0]);
        int m = Integer.parseInt(token[1]);
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[] aa = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(aa[j]);
                if(i==0 && j>0)
                    arr[i][j] += arr[i][j-1];
                else if(i>0 && j==0)
                    arr[i][j] += arr[i-1][j];
                else if(i==0 && j==0)
                    arr[i][j] = arr[i][j];
                else
                    arr[i][j] = arr[i][j] + arr[i][j-1] + arr[i-1][j] - arr[i-1][j-1];
            }
        }

//        System.out.println();
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(arr[i][j] + " ");
//            }
//            System.out.println();
//        }

        for (int i = 0; i < m; i++) {
            int x1, y1;
            int x2, y2;
            String[] bb = br.readLine().split(" ");
            x1 = Integer.parseInt(bb[0]);
            y1 = Integer.parseInt(bb[1]);
            x2 = Integer.parseInt(bb[2]);
            y2 = Integer.parseInt(bb[3]);
            x1--;
            x2--;
            y1--;
            y2--;
            //x2, y2  x1-1, y1-1
            //4, 4  3,3
            //4,4 2,4 4,2 2,2
            //4,4 4,4
            //4,4 3,4 4,3 3,3
            int ans=0;
            if(x1==0 && y1==0)
                ans = arr[x2][y2];
            else if(x1 > 0 && y1==0)
                ans = arr[x2][y2] - arr[x1-1][y2];
            else if(x1 == 0 && y1>0)
                ans = arr[x2][y2] - arr[x2][y1-1];
            else
                ans = arr[x2][y2] - arr[x1-1][y2] - arr[x2][y1-1] + arr[x1-1][y1-1];
            System.out.println(ans);
        }
    }
}
