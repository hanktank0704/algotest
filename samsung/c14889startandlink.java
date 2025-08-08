import java.io.*;
import java.util.*;
public class c14889startandlink {
    static int n;
    static int total;
    static int[][] arr;
    static int[] isused;
    static int[] pick;
    static int[] unpick;

    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n+1][n+1];
        isused = new int[n+1];
        pick = new int[n/2 + 1];
        unpick = new int[n/2 + 1];
        for (int i = 1; i < n+1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j < n+1; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                // 1 2 3, 4 5 6
                // 12, 21, 13, 31, 23, 32
                // 45, 54, 46, 64, 56, 65
            }
        }
        total = 0;
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < n+1; j++) {
                total+= arr[i][j];
            }
        }
//        System.out.println("total: " + total);
        //a, total -a -> total -2a
        recur(1);
        System.out.println(min);
    }

    public static void recur(int stage){
        if(stage == n/2+1){
//            System.out.println(Arrays.toString(pick));
            int temp = findsum(pick);
//            System.out.println("sum is : " + temp);

            // unpicked part sum calc
            int index=1;
            for (int i = 1; i < isused.length; i++) {
                if(isused[i] == 0)
                    unpick[index++] = i;
            }
//            System.out.println("unpick: " + Arrays.toString(unpick));
            int temp2 = findsum(unpick);

            int difference = temp2 - temp;
            if(difference < 0)
                difference = difference * (-1);

//            System.out.println("difference: " + difference);
            if(difference < min)
                min = difference;
        }
        else{
            for (int i = 1; i <= n; i++) {
                if(isused[i] != 1 && i > pick[stage-1]){
                    pick[stage] = i;
                    isused[i] = 1;
                    recur(stage+1);
                    isused[i] = 0;
                }
            }
        }
    }
    public static int findsum(int[] a){
        int sum=0;
        for (int i = 1; i < a.length+1; i++) {
            for (int j = i; j < a.length; j++) {
                int x = a[i];
                int y = a[j];
                sum += arr[x][y];
                sum += arr[y][x];
//                System.out.println("added " +arr[x][y]  + " and " +arr[y][x] );
            }
        }
        return sum;
    }
}

// 3, 5 : 7
// 3, 6 : 8
// 4, 6 : 9