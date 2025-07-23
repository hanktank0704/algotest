import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class test1 {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(3);
        queue.add(13);
        queue.add(23);
        queue.add(103);
        queue.add(4);

        int[] arr = new int[queue.size()];
        int[] sortedArr = new int[queue.size()];

        int i =0;
        for (int val : queue){
            arr[i++] = val;
        }

        sortedArr = Arrays.copyOf(arr, arr.length);
        arr[0] = -1;

        System.out.println(Arrays.toString(sortedArr));

    }
}
