import java.io.*;
import java.util.*;
public class f3190snake {
    static int n,m;
    static int[][] arr;
    static int[] sec = new int[100];
    static char[] c = new char[100];
    // left up right down
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};
    static Queue<int[]> q = new LinkedList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        int x,y;
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            arr[x-1][y-1] = 1;
        }
        int l = Integer.parseInt(br.readLine());
        sec = new int[100];
        c = new char[100];
        for (int i = 0; i < l; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            sec[i] = Integer.parseInt(st.nextToken());
            c[i] = st.nextToken().charAt(0);
        }
//        printarr();

        snake();
    }

    public static void snake(){
        int x=0 ,y=0;
        int time=0;
        int dir = 2;
        int index = 0; // for direction change

        q.add(new int[] {x,y});
        while(x>=0 && x<n && y>=0 && y<n){
//            System.out.println(time);
            printarr();
            time++;
            // next snake position
            //straight
            x = x + dx[dir];
            y = y + dy[dir];
            if(x<0 || x>=n || y<0 || y>=n)
                break;

            q.add(new int[] {x,y});

            // left right
            // left up right down
            // 0     1   2    3
            if(time == sec[index]){
                if(c[index] == 'L'){
                    if(dir==0)
                        dir = 3;
                    else
                        dir--;
                }
                if(c[index] == 'D'){
                    if(dir==3)
                        dir = 0;
                    else
                        dir++;
                }
                index++;
            }

            //when snake meets itself
            if(arr[x][y] == 7){
                System.out.println(time);
                return;
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(arr[i][j] == 7)
                        arr[i][j] = 0;
                }
            }
            //no apple
            if(arr[x][y] != 1){
                q.poll();
            }
        }
        System.out.println(time);
    }
    public static void printarr(){
        int x=0,y=0;
        for (int[] val : q) {
            x = val[0];
            y = val[1];

            arr[x][y] = 7;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
//                System.out.print(arr[i][j] + " ");
            }
//            System.out.println();
        }
//        System.out.println();
    }
}
