import java.io.*;
import java.util.*;
public class b1991treetraverse {
    static int n;
    static Node[] nodes = new Node[26];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char ptemp, ltemp, rtemp;
            String s = st.nextToken();
            ptemp = s.charAt(0);
            s = st.nextToken();
            ltemp = s.charAt(0);
            s = st.nextToken();
            rtemp = s.charAt(0);

            if(nodes[ptemp - 'A'] == null){
                nodes[ptemp - 'A'] = new Node(ptemp);
            }
            Node parent = nodes[ptemp - 'A'];
//            System.out.print("root: " + ptemp + " ");
            if(ltemp != '.'){
                nodes[ltemp - 'A'] = new Node(ltemp);
                parent.left = nodes[ltemp - 'A'];
//                System.out.print("left: " + ltemp + " ");
            }
            if(rtemp != '.'){
                nodes[rtemp - 'A'] = new Node(rtemp);
                parent.right = nodes[rtemp - 'A'];
//                System.out.print("right: " + rtemp + " ");
            }
//            System.out.println();
        }
        preorder(nodes[0]);
        System.out.println();
        inorder(nodes[0]);
        System.out.println();
        postorder(nodes[0]);
    }
    public static void preorder(Node node){
        if(node == null){
            return;
        }
        System.out.print(node.data);
        preorder(node.left);
        preorder(node.right);
    }
    public static void inorder(Node node){
        if(node == null){
            return;
        }
        inorder(node.left);
        System.out.print(node.data);
        inorder(node.right);
    }
    public static void postorder(Node node){
        if(node == null){
            return;
        }
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.data);
    }
}
class Node{
    char data;
    Node left;
    Node right;
    public Node(char data){
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
