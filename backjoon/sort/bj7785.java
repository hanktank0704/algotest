import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.*;

public class bj7785 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String[][] s = new String[n][2];
        for (int i = 0; i < n; i++) {
            String[] temp = new String[2];
            temp = br.readLine().split(" ");
            s[i][0] = temp[0];
            s[i][1] = temp[1];
        }
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if(s[i][1].equals("enter"))
                set.add(s[i][0]);
            else
                set.remove(s[i][0]);
        }
        List<String> list = new ArrayList<>(set);
        Collections.sort(list, Collections.reverseOrder());

        for(String name: list){
            System.out.println(name);
        }
    }
}
