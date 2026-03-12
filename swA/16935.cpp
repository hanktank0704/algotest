#include <iostream>
using namespace std;
int n,m,r;
int arr[101][101];
int temp[101][101];
int order[1001];

void input(){
    cin >> n >> m >> r;
    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++){
            cin >> arr[i][j];
        }
    }
    for(int i=0; i<r; i++){
        cin >> order[i];
    }
}
void copyarr(int a[101][101], int b[101][101]){
    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++){
            a[i][j] = b[i][j];
        }
    }
}
void updown(){
    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++){
            temp[i][j] = arr[n-1-i][j];
        }
    }
    copyarr(arr, temp);
}
void leftright(){
    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++){
            temp[i][j] = arr[i][m-1-j];
        }
    }
    copyarr(arr, temp);
}
void right90(){
    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++){
            temp[j][n-1-i] = arr[i][j];
        }
    }
    int swap = n;
    n=m;
    m=swap;
    copyarr(arr, temp);
}
void left90(){
    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++){
            temp[m-1-j][i] = arr[i][j];
        }
    }
    int swap = n;
    n=m;
    m=swap;
    copyarr(arr, temp);
}
void splitright(){
    int i1,j1,i2,j2,i3,j3,i4,j4;
    i1 = 0; j1 = 0;
    i2 = n/2; j2 = 0;
    i3 = n/2; j3 = m/2;
    i4 = 0; j4 = m/2;
    for(int i=0; i<n/2; i++){
        for(int j=0; j<m/2; j++){
            temp[i1+i][j1+j] = arr[i2+i][j2+j];
        }
    }
    for(int i=0; i<n/2; i++){
        for(int j=0; j<m/2; j++){
            temp[i2+i][j2+j] = arr[i3+i][j3+j];
        }
    }
    for(int i=0; i<n/2; i++){
        for(int j=0; j<m/2; j++){
            temp[i3+i][j3+j] = arr[i4+i][j4+j];
        }
    }
    for(int i=0; i<n/2; i++){
        for(int j=0; j<m/2; j++){
            temp[i4+i][j4+j] = arr[i1+i][j1+j];
        }
    }
    copyarr(arr, temp);
}
void splitleft(){
    int i1,j1,i2,j2,i3,j3,i4,j4;
    i1 = 0; j1 = 0;
    i2 = n/2; j2 = 0;
    i3 = n/2; j3 = m/2;
    i4 = 0; j4 = m/2;
    for(int i=0; i<n/2; i++){
        for(int j=0; j<m/2; j++){
            temp[i1+i][j1+j] = arr[i4+i][j4+j];
        }
    }
    for(int i=0; i<n/2; i++){
        for(int j=0; j<m/2; j++){
            temp[i4+i][j4+j] = arr[i3+i][j3+j];
        }
    }
    for(int i=0; i<n/2; i++){
        for(int j=0; j<m/2; j++){
            temp[i3+i][j3+j] = arr[i2+i][j2+j];
        }
    }
    for(int i=0; i<n/2; i++){
        for(int j=0; j<m/2; j++){
            temp[i2+i][j2+j] = arr[i1+i][j1+j];
        }
    }
    copyarr(arr, temp);
}
void printarr(){
    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++){
            cout << temp[i][j] << " ";
        }
        cout << endl;
    }
}
void orderrun(){
    for(int i=0; i<r; i++){
        if(order[i] == 1){
            updown();
        }
        if(order[i] == 2){
            leftright();
        }
        if(order[i] == 3){
            right90();
        }
        if(order[i] == 4){
            left90();
        }
        if(order[i] == 5){
            splitright();
        }
        if(order[i] == 6){
            splitleft();
        }
    }
}
int main(){
    input();
    orderrun();
    printarr();

    return 0;
}