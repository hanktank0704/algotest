#include <iostream>
#include <algorithm>
using namespace std;
int t=0;
int n =0;
int d=0;
int arr[501][501];
int save[501];
void spin(int n, int d){
    d = d/45;
    if(d < 0){
        d += 8;
    }
    int temp = n/2;
    for(int aa=0; aa<d; aa++){
        for(int i=0; i<n; i++){
            save[i] = arr[temp][i];
        }
        for(int i=0; i<n; i++){
            arr[temp][i] = arr[n-1 -i][i];
        }
        for(int i=0; i<n; i++){
            arr[n-1 -i][i] = arr[n-i-1][temp];
        }
        for(int i=0; i<n; i++){
            arr[i][temp] = arr[i][i];
        }
        for(int i=0; i<n; i++){
            arr[i][i] = save[i];
        }
    }
}
int main(){
    cin >> t;
    for(int tc=0; tc<t; tc++){
        cin >> n >> d;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                cin >> arr[i][j];
            }
        }
        spin(n,d);
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                cout << arr[i][j] << " ";
            }
            cout << endl;
        }
    }

    return 0;
}