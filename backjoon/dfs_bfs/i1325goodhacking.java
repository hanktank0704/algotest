import java.io.*;
import java.util.*;
public class i1325goodhacking {
    static int n,m;
    static int[][] trust;
    static int[] ans;
    static int[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        trust = new int[n+1][n+1];
        ans = new int[n+1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int aa = Integer.parseInt(st.nextToken());
            int bb = Integer.parseInt(st.nextToken());

            trust[bb][aa] = 1;
        }

        Queue<Integer> q = new LinkedList<>();
        visited = new int[n+1][n+1];
        int[] cnt = new int[n+1];
        for (int i = 0; i < n; i++) {
            int temp=0;
            q.add(i);
            while(!q.isEmpty()){
                int cur = q.poll();
                for (int j = 0; j < n+1; j++) {
                    if(trust[cur][j] == 1 && visited[cur][j] != 1){
                        q.add(j);
                        temp++;
                        visited[i][j] = 1;
                    }
                }
            }
            cnt[i] = temp;
        }
//        printarr(visited);
//        System.out.println(Arrays.toString(cnt));
        int max=0;
        for (int i = 0; i < cnt.length; i++) {
            if(max < cnt[i])
                max = cnt[i];
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cnt.length; i++) {
            if(max == cnt[i]){
                sb.append(i).append(" ");
            }
        }
        System.out.println(sb);

    }
    public static void printarr(int[][] arr){
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < n+1; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
