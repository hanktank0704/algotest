import java.io.*;

public class bj1919anangram {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();
        StringBuilder sb = new StringBuilder();
        int[] s1count = new int[26];
        int[] s2count = new int[26];

        char c;
        for(int i=0; i<s1.length(); i++){
            c = s1.charAt(i);
            s1count[c - 'a']++;
        }
        for(int i=0; i<s2.length(); i++){
            c = s2.charAt(i);
            s2count[c - 'a']++;
        }
//        System.out.println(Arrays.toString(s1count));
//        System.out.println(Arrays.toString(s2count));
        int answer=0;
        for(int i=0; i<26; i++){
            answer += Math.abs(s1count[i] - s2count[i]);
        }
        System.out.println(answer);
    }
}