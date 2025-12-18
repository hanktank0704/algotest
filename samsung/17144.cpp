#include <iostream>
#include <algorithm>
#include <string>
#include <vector>
using namespace std;
int r,c,t;
int arr[51][1001];
int dx[4] = {1,0,-1,0};
int dy[4] = {0,1,0,-1};
int main(){
    cin >> r >> c >> t;
    for(int i=0; i<r; i++){
        for(int j=0; j<c; j++){
            cin >> arr[i][j];
        }
    }
    for(int a=0; a<t; a++){
        //확산
        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){

            }
        }
        //공기순환
    }

    return 0;
}