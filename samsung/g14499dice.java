import java.io.*;
import java.util.*;
public class g14499dice {
    static int n,m;
    static int startx, starty;
    static int k;
    static int[][] board;
    static int[] moves;

    static int top, bottom, east, west, north, south;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        startx = Integer.parseInt(st.nextToken());
        starty = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        moves = new int[k];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            moves[i] = Integer.parseInt(st.nextToken());
        }
        top = 0;
        bottom = 0;
        east = 0;
        west = 0;
        north = 0;
        south = 0;

        int x = startx;
        int y = starty;
//        board[x][y] = top;

        for (int val : moves) {
            if(val == 1){   //east
                y++;
                if(x>=0 && x<n && y>=0 && y<m){
                    east();
                    if(board[x][y] == 0){
                        board[x][y] = bottom;
                    }
                    else{
                        bottom = board[x][y];
                        board[x][y] = 0;
                    }
                    System.out.println(top);
                }
                else{
                    y--;
                }
            }
            if(val == 2){   //west
                y--;
                if(x>=0 && x<n && y>=0 && y<m){
                    west();
                    if(board[x][y] == 0){
                        board[x][y] = bottom;
                    }
                    else{
                        bottom = board[x][y];
                        board[x][y] = 0;
                    }
                    System.out.println(top);
                }
                else{
                    y++;
                }
            }
            if(val == 3){   //north
                x--;
                if(x>=0 && x<n && y>=0 && y<m){
                    north();
                    if(board[x][y] == 0){
                        board[x][y] = bottom;
                    }
                    else{
                        bottom = board[x][y];
                        board[x][y] = 0;
                    }
                    System.out.println(top);
                }
                else{
                    x++;
                }
            }
            if(val == 4){   //south
                x++;
                if(x>=0 && x<n && y>=0 && y<m){
                    south();
                    if(board[x][y] == 0){
                        board[x][y] = bottom;
                    }
                    else{
                        bottom = board[x][y];
                        board[x][y] = 0;
                    }
                    System.out.println(top);
                }
                else{
                    x--;
                }
            }

        }
    }
    public static void east(){
        int temp = east;
        east = top;
        top = west;
        west = bottom;
        bottom = temp;
    }
    public static void west(){
        int temp = west;
        west = top;
        top = east;
        east = bottom;
        bottom = temp;
    }
    public static void north(){
        int temp = north;
        north = top;
        top = south;
        south = bottom;
        bottom = temp;

    }
    public static void south(){
        int temp = south;
        south = top;
        top = north;
        north = bottom;
        bottom = temp;
    }
    public static void printdice(){
        System.out.println("top: " + top);
        System.out.println("bottom: " + bottom);
        System.out.println("east: " + east);
        System.out.println("west: " + west);
        System.out.println("north: " + north);
        System.out.println("south: " + south);
    }
}
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                System.out.print(board[i][j] + " ");
//            }
//            System.out.println();
//        }
