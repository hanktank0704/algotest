#include <iostream>
using namespace std;
int n,m;
int arr[16][16];
int plusSum(){
    int max = -1;
    int sum=0;
    for(int i=0; i<n; i++){
        for(int j=0; j<n; j++){
            sum = 0;
            sum += arr[i][j];
            for(int k=1; k<m; k++){
                //up
                if(i-k >= 0){
                    sum += arr[i-k][j];
                }
                //down
                if(i+k < n){
                    sum += arr[i+k][j];
                }
                //left
                if(j-k >= 0){
                    sum += arr[i][j-k];
                }
                //right
                if(j+k < n){
                    sum += arr[i][j+k];
                }
            }
            if(max < sum){
                max = sum;
            }
        }
    }
    return max;
}
int xSum(){
    int max = -1;
    int sum=0;
    for(int i=0; i<n; i++){
        for(int j=0; j<n; j++){
            sum = 0;
            sum += arr[i][j];
            for(int k=1; k<m; k++){
                //
                if(i-k >= 0 && j-k >= 0){
                    sum += arr[i-k][j-k];
                }
                //
                if(i-k >= 0 && j+k < n){
                    sum += arr[i-k][j+k];
                }
                //
                if(i+k < n && j-k >= 0){
                    sum += arr[i+k][j-k];
                }
                //
                if(i+k < n && j+k < n){
                    sum += arr[i+k][j+k];
                }
            }
            if(max < sum){
                max = sum;
            }
        }
    }
    return max;

}
void input(){
    cin >> n >> m;
    for(int i=0; i<n; i++){
        for(int j=0; j<n; j++){
            cin >> arr[i][j];
        }
    }
}
void func(){
    int a = xSum();
    int b = plusSum();

    if(a>b)
        cout << a << endl;
    else{
        cout << b << endl;
    }

}
int main(){
    int T;
    cin >> T;
    for(int aa=0; aa<T; aa++){
        input();
        cout << "#" << aa+1 << " ";
        func();

    }

    return 0;
}