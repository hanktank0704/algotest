#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#include <set>
using namespace std;
int arr[16][16] = {0,};
int nextarr[16][16] = {0,}; // 이동할 어레이
vector<vector<int>> coordinate; 
int N, Q;
queue<pair<int,int>> q;
int visited[16][16] = {0,};
int dx[4] = {1,0,-1,0};
int dy[4] = {0,1,0,-1};

void printarr(){
    for (int i = N - 1; i >= 0; i--) { 
        for (int j = 0; j < N; j++) {
            cout << arr[j][i] << " ";
        }
        cout << endl;
    }
    // for (int i = N - 1; i >= 0; i--) { 
    //     for (int j = 0; j < N; j++) {
    //         cout << visited[j][i] << " ";
    //     }
    //     cout << endl;
    // }
}
// step 1 : 미생물 배치, 잘릴경우 미생물 삭제
void bfs(int cnt){
    while(!q.empty()){
        pair<int,int> cur = q.front(); q.pop();
        int curx = cur.first; int cury = cur.second;
        for(int i=0; i<4; i++){
            int x = curx + dx[i];
            int y = cury + dy[i];
            if(x<0 || x>=N || y<0 || y>=N) continue;
            if(visited[x][y] != 0 || arr[x][y] != cnt) continue;

            visited[x][y] = cnt;
            q.push({x,y});
        }
    }
}
int checksplit(int r1,int c1, int r2,int c2, int cnt){
    //visited 초기화
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            visited[i][j] = 0;
        }
    }

    //cnt 에 해당하는 칸을 하나 고른다
    //bfs를 한번 실행한다. 
    int flag = -1;
    for(int i=0; i<N; i++){
        if(flag == 1) break;
        for(int j=0; j<N; j++){
            if(arr[i][j] == cnt){
                visited[i][j] = cnt;
                q.push({i,j});
                bfs(cnt);
                flag = 1;
                break;
            }
        }
    }
    //만약 cnt 가 적힌 칸들중 
    //visited == 0 인 칸이 있으면 스플릿이 있는것이다
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            if(arr[i][j] == cnt){
                if(visited[i][j] != cnt){
                    return 1;
                }
            }
        }
    }
    return 0;
}
void setarr(int r1,int c1, int r2,int c2, int cnt){
    for(int i=r1; i<r2; i++){
        for(int j=c1; j<c2; j++){
            arr[i][j] = cnt;
        }
    }
    
    //만약 미생물이 잘리면 아예 사라져야 한다
    for(int i=1; i<cnt; i++){
        int flag = checksplit(r1,c1,r2,c2,i);
        // i 미생물이 잘려서 삭제되어야한다.
        if(flag == 1){
            for(int x=0; x<N; x++){
                for(int y=0; y<N; y++){
                    if(arr[x][y] == i){
                        arr[x][y] = 0;
                    }
                }
            }
        }
    }
    
    // printarr();
    // cout << endl;
}

// step2 배양 용기 이동
int findbiggest(){
    int dat[51]={0,};
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            if(arr[i][j] == 0) continue;
            dat[arr[i][j]]++;
        }
    }
    int max = -1;
    int maxindex = 0;
    for(int i=0; i<=Q; i++){
        if(max < dat[i]){
            max = dat[i];
            maxindex = i;
        }
    }
    return maxindex;
};
void moveBiggest(int big ){
    int minx=99,miny=99;
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            if(arr[i][j] == big){
                minx = min(minx, i);
                miny = min(miny, j);
            }
        }
    }
    vector<pair<int,int>> shape;
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            if(arr[i][j] == big){
                shape.push_back({i-minx, j-miny});
                arr[i][j] = 0;
            }
        }
    }
    // 새로운 어레이에 이동시켜보고 겹치거나 벗어나는 부분이 있으면 다른 좌표로 옮겨ㄱ야한다.
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            bool flag=1;
            for(auto val : shape){
                int xx = val.first; int yy = val.second;
                int x = i+xx; int y = j + yy;
                if(x < 0 || x >= N || y < 0 || y >= N){
                    flag = 0;
                    break;
                } 
                if(nextarr[x][y] != 0){
                    flag = 0;
                    break;
                }
            }
            //이동이 가능한 i,j 찾았다
            if(flag == 1){
                for(auto val : shape){
                    int xx = val.first; int yy = val.second;
                    int x = i+xx; int y = j+yy;
                    nextarr[x][y] = big;
                }
                return;
            }
        }
    }
}
void moveVirus(){
    //가장 많은거 찾아야함
    while(1){
        int biggest = findbiggest();
        if(biggest==0) break;
        moveBiggest(biggest);
    }
    
    //모양을 유지해서 이동시키기
    //이동못하면 아예 없애 버리기

    // 움직이고 나서는 new arr를 기존의 arr로 옮겨야다다한ㄷ.
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            arr[i][j] = nextarr[i][j];
            nextarr[i][j] = 0;
        }
    }
}

void findNear(int germ){

}
// step3 결과 계산
void calculateScore(){
    // 1. 각 미생물의 넓이 구하기
    int area[1001] = {0,};
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            if(arr[i][j] != 0){
                area[arr[i][j]]++;
            }
        }
    }

    long long total_score = 0;
    set<pair<int, int>> pairs; // 중복 계산 방지용

    // 2. 인접한 쌍 찾기
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            int cur = arr[i][j];
            if(cur == 0) continue;

            for(int k=0; k<4; k++){
                int nx = i + dx[k];
                int ny = j + dy[k];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                
                int neighbor = arr[nx][ny];
                // 다른 미생물과 맞닿아 있다면
                if(neighbor != 0 && neighbor != cur){
                    // (작은번호, 큰번호) 순서로 저장하여 중복 체크
                    int small = min(cur, neighbor);
                    int big = max(cur, neighbor);

                    if(pairs.find({small, big}) == pairs.end()){
                        pairs.insert({small, big});
                        total_score += (long long)area[cur] * area[neighbor];
                    }
                }
            }
        }
    }
    cout << total_score << endl;
}

void input(){
    int r1,r2,c1,c2;
    cin >> N >> Q;
    for(int i=0; i<Q; i++){
        cin >> r1 >> c1 >> r2 >> c2;
        vector<int> temp;
        temp.push_back(r1);
        temp.push_back(c1);
        temp.push_back(r2);
        temp.push_back(c2);
        coordinate.push_back(temp);
        
        setarr(r1,c1,r2,c2, i+1);
        moveVirus();
        calculateScore();
    }
}
int main(){
    //input에서 미생물 배치한다.
    input();
    // moveVirus();
    // printarr();
    // calculateScore();

    // test();
    // printarr();

    return 0;
}