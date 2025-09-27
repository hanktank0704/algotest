import java.io.*;
import java.util.*;
class Solution {
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static int[][] visited;
    static char[][] arr;
    static int startx, starty;
    static int destinx, destiny;
    static int n , m;
    public int solution(String[] board) {
        int answer = 0;
        visited = new int[board.length][board[0].length()];
        arr = new char[board.length][board[0].length()];
        n = board.length;
        m = board[0].length();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length(); j++) {
                arr[i][j] = board[i].charAt(j);
                if(board[i].charAt(j) == 'R'){
                    startx = i;
                    starty = j;
                }
                if(board[i].charAt(j) == 'G'){
                    destinx = i;
                    destiny = j;
                }
            }
        }

        endlessBfs();

        answer = visited[destinx][destiny];

        return answer;
    }
    public static void endlessBfs(){
        Queue<int[]> q = new LinkedList<>();
        visited[startx][starty] = 1;
        q.add(new int[] {startx, starty});
        while(!q.isEmpty()){
            int cur[] = q.poll();
            int curx = cur[0];
            int cury = cur[1];
            for (int i = 0; i < 4; i++) {
                int x = curx;
                int y = cury;
                while(checkstop(x,y, dx[i], dy[i]) == false){
                    x += dx[i];
                    y += dy[i];
                }
                if(visited[x][y] == 0){
                    visited[x][y] = visited[curx][cury] + 1;
                    q.add(new int[] {x,y});
                }
            }
        }
    }
    public static boolean checkstop(int x, int y, int dx, int dy){
        if(x + dx < 0 || y + dy < 0 || x+dx >= n || y+dy >= m)
            return true;
        if(arr[x+dx][y+dy] == 'D')
            return true;

        return false;
    }
}