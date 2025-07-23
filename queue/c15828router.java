import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class c15828router {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int input = 1;
        Queue<Integer> queue = new LinkedList<>();
        while(input!=-1){
            input = Integer.parseInt(br.readLine());
            if(input==-1)
                break;
            if(input>0){
                if(queue.size() >= n)
                    continue;
                queue.add(input);
            }
            else{
                queue.poll();
            }
        }
        if(queue.isEmpty()){
            System.out.println("empty");
        }
        else{
            for(int val: queue){
                System.out.print(val + " ");
            }
        }
//        System.out.println(queue);
    }
}
