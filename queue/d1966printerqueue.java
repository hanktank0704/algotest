import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class d1966printerqueue {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            String[] token = br.readLine().split(" ");
            int n = Integer.parseInt(token[0]);
            int m = Integer.parseInt(token[1]);

            String[] aa = br.readLine().split(" ");
            Queue<int[]> queue = new LinkedList<>();
            int a =0;
            for (String val : aa) {
                queue.add(new int[] {Integer.parseInt(val),a++});
            }

            int order=0;
            while(!queue.isEmpty()){
                int[] front = queue.poll();
                if(hashigherpriority(queue, front[0])){
                    queue.add(front);
                }
                else{
                    order++;
                    if(front[1] == m){
                        System.out.println(order);
                        break;
                    }
                }
            }


        }

    }
    public static boolean hashigherpriority(Queue<int[]> queue, int current){
        for(int[] doc : queue){
            if(doc[0] > current){
                return true;
            }
        }
        return false;
    }
}
