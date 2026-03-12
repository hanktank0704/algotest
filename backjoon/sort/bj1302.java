import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;

public class bj1302 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            int count = map.getOrDefault(s, 0);
            if(count == 0)
                map.put(s, 1);
            else
                map.put(s, count+1);
        }
        int max = 0;
        String ans;
        for(String key : map.keySet()){
            if(max < map.get(key))
                max = map.get(key);
        }

        List<String> arr = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            if(entry.getValue() == max)
                arr.add(entry.getKey());
        }
        Collections.sort(arr);
        System.out.println(arr.get(0));
    }
}
