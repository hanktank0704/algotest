import java.io.*;
import java.util.*;
public class f5639binarysearchtree {
    public static class Node{
        int num;
        Node left, right;
        Node(int num){
            this.num = num;
        }
        Node(int num, Node left, Node right){
            this.num = num;
            this.left = left;
            this.right = right;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
        Node root = new Node(Integer.parseInt(br.readLine()));

        while(true){
            String temp = br.readLine();
            if(temp == null || temp.equals(""))
                break;
            int input = Integer.parseInt(temp);
            inserttree(root, input);
        }
        huwee(root);
        System.out.println("left : " + root.left.num);
        System.out.println("right : " + root.right.num);
    }
    public static void huwee(Node cur){
        if(cur.left != null){
            huwee(cur.left);
        }
        if(cur.right!= null){
            huwee(cur.right);
        }
        System.out.println(cur.num);
    }
    public static void inserttree(Node cur, int add){
        if(cur.num > add){
            if(cur.left == null){
                cur.left = new Node(add);
            }
            else{
                inserttree(cur.left, add);
            }
        }
        else{
            if(cur.right == null){
                cur.right = new Node(add);
            }
            else{
                inserttree(cur.right, add);
            }
        }
    }
}