import java.util.*;
import java.io.*;
public class d1012baechu {
    static int[][] arr;
    static int[][] visited;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Queue<int[]> q = new LinkedList<>();
    static List<Integer>[] l;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        for (int tc = 0; tc < t; tc++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            arr = new int[m][n];
            visited = new int[m][n];
            l = new ArrayList[Math.max(m,n) + 1];
            for (int i = 0; i < Math.max(m,n)+1; i++) {
                l[i] = new ArrayList<>();
            }
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                arr[x][y] = 1;
//            arr[y][x] = 1;
                l[x].add(y);
                l[y].add(x);
            }

            int cnt=0;
            for (int i = 0; i < m; i++) {
                for(int j=0; j<n; j++){
                    if(visited[i][j] ==1 || arr[i][j] != 1)
                        continue;

                    q.add(new int[] {i,j});
                    visited[i][j] = 1;
                    cnt++;

                    while(!q.isEmpty()){
                        int[] cur = q.poll();
                        int x = cur[0];
                        int y = cur[1];
//                        System.out.println(Arrays.toString(cur));

//                    if(visited[x][y] == 1)
//                        continue;
                        for (int p = 0; p < 4; p++) {
                            if(x+dx[p]>=0 && x+dx[p] <= m-1 && y+dy[p]>=0 && y+dy[p]<=n-1 && arr[x+dx[p]][y+dy[p]] == 1){

                                if(visited[x+dx[p]][y+dy[p]] == 1)
                                    continue;
                                q.add(new int[] {x+dx[p], y+dy[p]});
                                visited[x+dx[p]][y+dy[p]] = 1;
                            }
                        }
                    }

                }

            }
            System.out.println(cnt);
//        for (int i = 0; i < n; i++) {
//            for(int val : l[i]){
//                if(visited[i][val] ==1)
//                    continue;
//
//                q.add(new int[] {i,val});
//                visited[i][val] = 1;
//                cnt++;
//
//                while(!q.isEmpty()){
//                    int[] cur = q.poll();
//                    int x = cur[0];
//                    int y = cur[1];
//                    System.out.println(Arrays.toString(cur));
//
//                    if(visited[x][y] == 1)
//                        continue;
//                    for (int j = 0; j < 4; j++) {
//                        if(x+dx[j]>=0 && x+dx[j] <= m-1 && y+dy[j]>=0 && y+dy[j]<=n-1){
//                            q.add(new int[] {x+dx[j], y+dy[j]});
//                            visited[x+dx[j]][y+dy[j]] = 1;
//                        }
//                    }
//                }
//
//            }
//
//        }

        }
    }
}
