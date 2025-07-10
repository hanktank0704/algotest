import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj1931meetingdistribution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];
        int[] maxarr = new int[n];
        for (int i = 0; i < n; i++) {
            String[] token = br.readLine().split(" ");
            arr[i][0] = Integer.parseInt(token[0]);
            arr[i][1] = Integer.parseInt(token[1]);
        }

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] != o2[1])
                    return o1[1] - o2[1];
                else
                    return o1[0] - o2[0];
            }
        });

//        System.out.println();
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < 2; j++) {
//                System.out.print(arr[i][j] + " ");
//            }
//            System.out.println();
//        }
        int max=0;
        int lastEnd=0;
        for (int i = 0; i < n; i++) {
            if(arr[i][0] >= lastEnd){
                max++;
                lastEnd = arr[i][1];
            }
        }
        System.out.println(max);

    }
}
//arr[0], checkarr[7......] = 1;
//if(checkarr == 1) continue;
//checkarr[5....] = 1;

//0 6
//1 4
//2 13
//3 5
//3 8
//5 7
//5 9
//6 10
//8 11
//8 12
//12 14
