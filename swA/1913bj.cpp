#include <iostream>
using namespace std;
int arr[1001][1001];
int n;
int dx[4] = {0,-1,0,1};
int dy[4] = {-1,0,1,0};
int num;
void input(){
    cin >> n;
    cin >> num;
}
void printarr(){
    for(int i=0; i<n; i++){
        for(int j=0; j<n; j++){
            cout << arr[i][j] << " ";
        }
        cout << endl;
    }
}
void snail(){
    int dir=0;
    int x=n/2;
    int y=n/2;
    int ansx, ansy;
    for(int i=1; i<=n*n; i++){
        arr[x][y] = i;
        if(i == num){
            ansx = x;
            ansy = y;
        }
        if(i == n*n)
            break;
        int temp = (dir+1)%4;
        int nx = x + dx[temp];
        int ny = y + dy[temp];
        if(nx>=0 && nx<n && ny>=0 && ny<n && arr[x + dx[temp]][y + dy[temp]] == 0){
            x=nx;
            y=ny;
            dir = temp;
        }
        else{
            x = x + dx[dir];
            y = y + dy[dir];
        }
        //printarr();
    }
    printarr();
    cout << ansx+1 << " " << ansy+1 << endl;
}

int main(){
    input();
    snail();
    //printarr();
    return 0;
}