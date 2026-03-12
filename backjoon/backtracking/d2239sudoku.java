import java.io.*;
import java.util.*;
public class d2239sudoku {
    static int[][] arr;
    static int[][] row;
    static int[][] col;
    static int[][] box;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new int[10][10];
        for (int i = 1; i <= 9; i++) {
            String s = br.readLine();
            for (int j = 1; j <= 9; j++) {
                arr[i][j] = (s.charAt(j-1) - '0');
            }
        }
        printarr();
        row = new int[10][10];
        col = new int[10][10];
        box = new int[10][10];



    }
    public static void backtrack(){
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                if(arr[i][j] == 0){


                }
            }
        }
        
    }
    public static void printarr(){
        System.out.println();
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
