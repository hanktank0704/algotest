#include <iostream>
using namespace std;
int t,n,m;
int aarr[21];
int barr[21];
int temp[21];
int ans=0;
void findsum(){
    int max = -1;
    int sum=0;
    int ss;
    int ll;
    if(n < m){
        ss = n; ll = m;
    }
    else{
        ss = m; ll = n;
        for(int i=0; i<n; i++){
            temp[i] = aarr[i];
        }
        for(int i=0; i<m; i++){
            aarr[i] = barr[i];
        }
        for(int i=0; i<n; i++){
            barr[i] = temp[i];
        }
    }

    //cout << "sums" <<endl;
    for(int aa = 0; aa< ll - ss+1; aa++){
        sum = 0;
        for(int i=0; i<ss; i++){
            sum += aarr[i] * barr[i + aa];
        }
        if(max < sum){
            max = sum;
        }
        //cout << sum << " ";
    }
    ans = max;
}
void input(){
    cin >> t;
    for(int i=0; i<t; i++){
        cin >> n >> m;
        for(int j=0; j<n; j++){
            cin >> aarr[j];
        }
        for(int j=0; j<m; j++){
            cin >> barr[j];
        }
        findsum();
        cout << "#" << i+1 << " " << ans << endl;
    }
}
int main(){
    input();
    return 0;
}