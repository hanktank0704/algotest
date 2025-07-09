import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj1730panhwa {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String s = br.readLine();
        int x=0, y =0;

        int[][] boardx = new int[n][n];
        int[][] boardy = new int[n][n];

        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == 'U'){
                if(x - 1 < 0)
                    continue;
                boardx[x][y] = 1;
                boardx[x-1][y] = 1;
                x--;
            }
            if(s.charAt(i) == 'D'){
                if(x + 1 >= n)
                    continue;
                boardx[x][y] = 1;
                boardx[x+1][y] = 1;
                x++;
            }
            if(s.charAt(i) == 'L'){
                if(y - 1 < 0)
                    continue;
                boardy[x][y] = 1;
                boardy[x][y-1] = 1;
                y--;
            }
            if(s.charAt(i) == 'R'){
                if(y + 1 >= n)
                    continue;
                boardy[x][y] = 1;
                boardy[x][y+1] = 1;
                y++;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(boardx[i][j] == 1 && boardy[i][j] == 1)
                    System.out.print('+');
                if(boardx[i][j] == 1 && boardy[i][j] == 0)
                    System.out.print('|');
                if(boardx[i][j] == 0 && boardy[i][j] == 1)
                    System.out.print('-');
                if(boardx[i][j] == 0 && boardy[i][j] == 0)
                    System.out.print('.');
            }
            System.out.println();
        }

    }
}
