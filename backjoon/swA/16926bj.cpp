#include <iostream>
using namespace std;
#define MAX (300+30)

int n,m,r;
int map[MAX][MAX];
int temp[MAX][MAX];

struct rc{
    int r;
    int c;
};

rc arr[MAX*MAX];

void input(){
    cin >> n >> m >> r;
    for(int i=1; i<=n; i++){
        for(int j=1; j<=m; j++){
            cin >> map[i][j];
        }
    }
}
void copyMap(int copy[MAX][MAX], int original[MAX][MAX]){
    for(int i=1; i<=n; i++){
        for(int j=1; j<=m; j++){
            copy[i][j] = original[i][j];
        }
    }
}
void printMap(){
    for(int i=1; i<=n; i++){
        for(int j=1; j<=m; j++){
            cout << map[i][j] << " ";
        }
        cout << endl;
    }
}
void rotateStep(int sr, int sc, int n, int m, int rotatecnt){
    int er, ec;
    er = n + sr -1;
    ec = m + sc -1;
    copyMap(temp, map);
    int index = 0;
    for(int i=sc; i<=ec; i++){
        arr[index].r = sr;
        arr[index].c = i;
        index++;
    }
    for(int i=sr+1; i<=er; i++){
        arr[index].r = i;
        arr[index].c = ec;
        index++;
    }
    for(int i=ec-1; i>=sc; i--){
        arr[index].r = er;
        arr[index].c = i;
        index++;
    }
    for(int i=er-1; i>=sr+1; i--){
        arr[index].r = i;
        arr[index].c = sc;
        index++;
    }

    for(int i=0; i<index; i++){
        int newidx = (i - rotatecnt)%index;
        if(newidx < 0){
            newidx += index;
        }
        rc next = arr[newidx];
        map[next.r][next.c] = temp[arr[i].r][arr[i].c];
    }
}
void rotate(int rotatecount){
    int sr,sc,tn,tm;
    int step = n < m ? n/2 : m/2;
    sr = sc = 1;
    tn = n;
    tm = m;

    for(int i=0; i<step; i++){
        rotateStep(sr,sc,tn,tm, rotatecount);
        sr++;
        sc++;
        tn -= 2;
        tm -= 2;
    }

}
int main(){
    input();
    rotate(r);
    printMap();

    return 0;
}