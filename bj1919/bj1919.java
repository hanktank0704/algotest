import java.io.*;

public class bj1919 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        StringBuilder sb = new StringBuilder();
        char c;
        for(int i=0; i<s.length(); i++){
            c = s.charAt(i);
            if(Character.isUpperCase(c)){
                sb.append(Character.toLowerCase(c));
            }
            else{
                sb.append(Character.toUpperCase(c));
            }
        }
        System.out.println(sb);
    }
}