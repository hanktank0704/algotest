#include <iostream>
using namespace std;
int t,n;
int arr[9][9];
int temp1[9][9];
int temp2[9][9];
int temp3[9][9];
void ninespin(int temp[9][9], int input[9][9]){
    for(int i=0; i<n; i++){
        for(int j=0; j<n; j++){
            temp[i][j] = input[n-j-1][i];
        }
    }
}
void print(){
    for(int i=0; i<n; i++){
        for(int j=0; j<n; j++){
            cout << temp1[i][j];
        }
        cout << " ";
        for(int j=0; j<n; j++){
            cout << temp2[i][j];
        }
        cout << " ";
        for(int j=0; j<n; j++){
            cout << temp3[i][j];
        }
        cout << endl;
    }
}
int main(){
    cin >> t;
    for(int a=0; a<t; a++){
        cin >> n;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                cin >> arr[i][j];
            }
        }
        ninespin(temp1, arr);
        ninespin(temp2, temp1);
        ninespin(temp3, temp2);

        cout << "#" <<  a+1 << endl;
        print();
    }

    return 0;
}