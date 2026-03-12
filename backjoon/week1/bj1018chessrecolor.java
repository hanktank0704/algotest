import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

public class bj1018chessrecolor {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] size = br.readLine().split(" ");
        int n = Integer.parseInt(size[0]);
        int m = Integer.parseInt(size[1]);

        char[][] chess1 = new char[8][8];
        char[][] chess2 = new char[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if((i+j)%2 == 0){
                    chess1[i][j] = 'B';
                }
                else{
                    chess1[i][j] = 'W';
                }
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if((i+j)%2 == 1){
                    chess2[i][j] = 'B';
                }
                else{
                    chess2[i][j] = 'W';
                }
            }
        }

        char[][] board = new char[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            board[i] = line.toCharArray();
        }

//        for (int i = 0; i < 8; i++) {
//            System.out.println(Arrays.toString(chess1[i]));
//        }

        int mina=64, minb=64;
        for (int i = 0; i < n + 1 - 8; i++) {
            for (int j = 0; j < m + 1 - 8; j++) {
                int a=0, b=0;
                for (int k = 0; k < 8; k++) {
                    for (int l = 0; l < 8; l++) {
                        if(chess1[k][l] != board[i+k][j+l]){
                            a++;
                        }
                        if(chess2[k][l] != board[i+k][j+l]){
                            b++;
                        }
                    }
                }
                if(mina > a)
                    mina = a;
                if(minb > b)
                    minb = b;
            }
        }
//        System.out.println("a: " + mina + " b: " + minb);
        System.out.println(mina>minb?minb:mina);
    }
}
