import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class b1158yosepus {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] token = br.readLine().split(" ");
        int n = Integer.parseInt(token[0]);
        int k = Integer.parseInt(token[1]);

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            queue.add(i+1);
        }
        int count=1;
        while(!queue.isEmpty()){
            if(count%k==0){
                System.out.print(queue.peek() + " ");
                queue.poll();
                count=0;
            }
            queue.add(queue.peek());
            queue.poll();
//            System.out.println("count : " + count);
            count++;
        }
    }
}