import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.LinkedList;
import java.util.*;
import java.util.Queue;

public class a10025whitebear {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] token = br.readLine().split(" ");
        int n = Integer.parseInt(token[0]);
        int k = Integer.parseInt(token[1]);

        // weight, coordinate
        int[] arr = new int[1000001];
        List<int[]> pair= new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] token1 = br.readLine().split(" ");
            int weight = Integer.parseInt(token1[0]);
            int where = Integer.parseInt(token1[1]);
            pair.add(new int[] {weight, where});
            arr[where] = weight;
        }
//        for (int i = 0; i < 1000001;i++) {
//
//        }
        // 1 2 3 4
        // 1 3 6 19

        // 0 1 2 3 4 5 6 7 8 9 101112131415
        // 0 5 2 4 0 0 0 4 0 0 0 0 0 0 0 10

        int max = 0;
        int sum=0;
        for (int i = 0; i < 1000001; i++) {
            if(i - (2*k+1) >= 0)
                sum -= arr[i - (2*k + 1)];
            sum+= arr[i];
//            System.out.println("i: " + sum);
            if(max < sum)
                max = sum;
        }
        System.out.println(max);

//        pair.sort((a,b) -> Integer.compare(a[1],b[1]));


    }
}
