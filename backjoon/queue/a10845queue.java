import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class a10845queue {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            String[] token = br.readLine().split(" ");
            if(token[0].equals("push")){
                queue.add(token[1]);
            }
            if(token[0].equals("pop")){
                if(queue.isEmpty()){
                    System.out.println(-1);
                    continue;
                }
                System.out.println(queue.peek());
                queue.poll();
            }
            if(token[0].equals("size")){
                System.out.println(queue.size());
            }
            if(token[0].equals("empty")){
                if(queue.isEmpty())
                    System.out.println(1);
                else
                    System.out.println(0);
            }
            if(token[0].equals("front")){
                if(queue.isEmpty()){
                    System.out.println(-1);
                    continue;
                }
                System.out.println(queue.peek());
            }
            if(token[0].equals("back")){
                if(queue.isEmpty()){
                    System.out.println(-1);
                    continue;
                }
                System.out.println(((LinkedList<String>) queue).peekLast());
            }
        }

    }
}

// 1 2
// 1 2 2 0 1 2