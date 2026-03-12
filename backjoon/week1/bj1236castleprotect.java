import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class bj1236castleprotect {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        int N = Integer.parseInt(s[0]);
        int M = Integer.parseInt(s[1]);

        char[][] castle = new char[N][M];
        for(int i=0; i<N; i++){
            String line = br.readLine();
            castle[i] = line.toCharArray();
        }
//        System.out.println(Arrays.deepToString(castle));
        int[] xcheck = new int[M];
        int[] ycheck = new int[N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(castle[i][j] == 'X'){
                    xcheck[j] = 1;
                    ycheck[i] = 1;
                }
            }
        }
//        System.out.println(Arrays.toString(xcheck));
//        System.out.println(Arrays.toString(ycheck));

        int xmax=0;
        int ymax=0;
        for (int i = 0; i < M; i++) {
            if(xcheck[i] == 0){
                xmax++;
            }
        }
        for (int i = 0; i < N; i++) {
            if(ycheck[i] == 0){
                ymax++;
            }
        }
        System.out.println(xmax>ymax?xmax:ymax);
    }
}
