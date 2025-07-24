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
//        for(int val : queue){
//            System.out.println(val + " ");
//        }
        int count=0;
        System.out.print("<");
        while(!queue.isEmpty()){
            count++;
            if(count%k == 0){   //TODO
                if(queue.size()==1)
                    System.out.println(queue.peek() + ">");
                else
                    System.out.print(queue.peek() + ", ");
                queue.poll();
            }
            else{
                int temp = queue.peek();
                queue.add(temp);
                queue.poll();
//            queue.add(queue.poll());
            }
        }
    }
}
// 0: 1 2 3 4 5 6 7
// 1: 2 3 4 5 6 7 1
// 2: 3 4 5 6 7 1 2
// 3: 4 5 6 7 1 2
// 4: 5 6 7 1 2 4
// 5: 6 7 1 2 4 5
// 6: 7 1 2 4 5