import java.io.*;
import java.util.*;
public class f2239sudoku {
    static char[][] arr;
    static int empty = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new char[9][9];
        for (int i = 0; i < 9; i++) {
            String s = br.readLine();
            for (int j = 0; j < 9; j++) {
                arr[i][j] = s.charAt(j);
                if(arr[i][j] == 0)
                    empty++;
            }
        }
        backtrack(0);
    }
    public static void backtrack(int stage){
        if(stage == empty){
        }
        else{
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if(arr[i][j] == 0){
                        
                    }

                }
            }

        }
    }
    public static int block(int xblock, int yblock){
        int[] check = new int[10];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int temp = arr[xblock * 3 + i][yblock * 3 + j];
                check[temp] = 1;
            }
        }

        for (int i = 1; i < 10; i++) {
            if(check[i] != 1)
                return 0;
        }
        return 1;
    }
}
