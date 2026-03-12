#include <iostream>
using namespace std;

int arr[129][129];
int temp[129][129];
int n,r,k,l;
int len;
void input(){
    cin >> n >> r;
    len = 1;
    for(int i=0; i<n; i++){
        len = len*2;
    }
    for(int i=1; i<=len; i++){
        for(int j=1; j<=len; j++){
            cin >> arr[i][j];
        }
    }
}
void copymap(int a[129][129], int b[129][129]){
    for(int i=1; i<=len; i++){
        for(int j=1; j<=len; j++){
            a[i][j] = b[i][j];
        }
    }
}
void printarr(){
    //cout<<endl;
    for(int i=1; i<=len; i++){
        for(int j=1; j<=len; j++){
            cout << arr[i][j] << " ";
        }
        cout << endl;
    }
}
void updownsmall(int x, int y, int size){
    for(int i=0; i<size; i++){
        for(int j=0; j<size; j++){
            arr[i+x][j+y] = temp[size + x - 1 - i][j+y];
        }
    }
}
void updown(int level){
    copymap(temp, arr);
    int size = (1 << level);
    for(int i=1; i<=len; i+=size){
        for(int j=1; j<=len; j+=size){
            updownsmall(i,j,size);
        }
    }
}
void leftrightsmall(int x, int y, int size){
    for(int i=0; i<size; i++){
        for(int j=0; j<size; j++){
            arr[i+x][j+y] = temp[i+x][size + y - 1 - j];
        }
    }
}
void leftright(int level){
    copymap(temp, arr);
    int size = (1 << level);
    for(int i=1; i<=len; i+=size){
        for(int j=1; j<=len; j+=size){
            leftrightsmall(i,j,size);
        }
    }
}
void right90small(int x, int y, int size){
    for(int i=0; i<size; i++){
        for(int j=0; j<size; j++){
            arr[i+x][j+y] = temp[size-j-1+x][i+y];
        }
    }

}
void right90(int level){
    copymap(temp, arr);
    int size = (1 << level);
    for(int i=1; i<=len; i+=size){
        for(int j=1; j<=len; j+=size){
            right90small(i,j,size);
        }
    }
}
void left90small(int x, int y, int size){
    for(int i=0; i<size; i++){
        for(int j=0; j<size; j++){
            arr[i+x][j+y] = temp[j+x][size-i-1+y];
        }
    }

}
void left90(int level){
    copymap(temp, arr);
    int size = (1 << level);
    for(int i=1; i<=len; i+=size){
        for(int j=1; j<=len; j+=size){
            left90small(i,j,size);
        }
    }
}
void five_updown(int level){
    copymap(temp, arr);
    updown(n);
    updown(level);
}
void six_leftright(int level){
    copymap(temp, arr);
    leftright(n);
    leftright(level);
}
void seven_right90(int level){
    copymap(temp, arr);
    right90(n);
    left90(level);
}
void eight_left90(int level){
    copymap(temp, arr);
    left90(n);
    right90(level);
}

int main(){
    input();
    copymap(temp, arr);
    int k,l;
    for(int i=0; i<r; i++){
        cin >> k >> l;
        if(k==1){
            updown(l);
        }
        else if(k==2){
            leftright(l);
        }
        else if(k==3){
            right90(l);
        }
        else if(k==4){
            left90(l);
        }
        else if(k==5){
            five_updown(l);
        }
        else if(k==6){
            six_leftright(l);
        }
        else if(k==7){
            seven_right90(l);
        }
        else if(k==8){
            eight_left90(l);
        }
    }
    //updownflip(3);
    //leftrightflip(3);
    //right90(2);
    //left90(2);
    //five_updown(2);
    printarr();

    return 0;
}