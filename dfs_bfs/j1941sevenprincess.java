import java.io.*;
import java.util.*;
public class j1941sevenprincess {
    static char[][] arr;
    static int[] combination;
    static int[] isused;
    static int[] dx ={1, 0, -1, 0};
    static int[] dy ={0, 1, 0, -1};
    static int ans=0;
    static int[][] combArr;
    static int[][] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        arr= new char[5][5];
        combination = new int[7];
        isused = new int[25];
        combArr= new int[5][5];
        visited= new int[5][5];

        for (int i = 0; i < 5; i++) {
            String ss = br.readLine();
            for (int j = 0; j < 5; j++) {
                arr[i][j] = ss.charAt(j);
            }
        }
        backtrack(0, 0);
        System.out.println(ans);
    }
    public static void backtrack(int stage, int start){
        if(stage == 7){
//            System.out.println(Arrays.toString(combination));
            if(checksom() == 1 && checkdfs() == 1){
                ans++;
            }
        }
        else{
            for (int i = start; i < 25; i++) {
                if(isused[i] != 1){
                    combination[stage] = i;
                    backtrack(stage+1, i+1);
                    isused[i] = 0;
                }
            }
        }
    }
    public static int checkdfs(){
        combArr = new int[5][5];
        visited = new int[5][5];
        for (int i = 0; i < 7; i++) {
            int temp = combination[i];
            int x = temp/5;
            int y = temp%5;
            combArr[x][y] = 1;
        }
        Queue<int[]> q = new LinkedList<>();
        int connected = 0;
        int temp = combination[0];
        int startx = temp/5;
        int starty = temp%5;
        q.add(new int[] {startx,starty});
        visited[startx][starty] = 1;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            connected++;
            int xx = cur[0];
            int yy = cur[1];

            for (int k = 0; k < 4; k++) {
                int x = xx + dx[k];
                int y = yy + dy[k];

                if(x>=0 && x<5 && y>=0 && y<5){
                    if(combArr[x][y] == 1 && visited[x][y] != 1){
                        q.add(new int[] {x,y});
                        visited[x][y] = 1;
                    }
                }
            }
        }
//        printArr();
        if(connected == 7){
            return 1;
        }
        return 0;
    }
    public static void dfs(){

    }
    public static int checksom(){
        int som=0;
        for (int i = 0; i < 7; i++) {
            int temp = combination[i];
            int x = temp/5;
            int y = temp%5;
            if(arr[x][y] == 'S'){
                som++;
            }
        }
        if(som>=4){
            return 1;
        }
        return 0;
    }
    public static void printArr(){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(combArr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
