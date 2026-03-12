#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;
int arr[74][71];
int r, c, k;
int dx[4] = { -1,0,1,0 };
int dy[4] = { 0,1,0,-1 };
int glcnt = 0;
int vis[74][71];
struct golem {
	int id;
	int x, y;
	int exitx, exity;
	int dir;
};
golem gl[1000];

void golemstart(int ci, int di, int glcnt) {
	gl[glcnt].id = glcnt;
	gl[glcnt].x = 1;
	gl[glcnt].y = ci;
	gl[glcnt].dir = di;
	arr[1][ci] = 1;
	for (int i = 0; i < 4; i++) {
		int nexx = 1 + dx[i];
		int nexy = ci + dy[i];
		arr[nexx][nexy] = 1;
	}

	//glcnt++;
}
bool fall(int gindex) {
	// 일단 현재 있는 골렘을 0으로 지운다
	// 골렘의 위치를 내린다
	// 혹시 이동이 불가능한 곳이 있으면 이동불가능을로 이전의 골렘을 복원
	// 이동이 가능하면 이동한 위치에서 arr에 1로 다시 표시한다
	int moveflag = 1;
	int x = gl[gindex].x;
	int y = gl[gindex].y;
	int dir = gl[gindex].dir;

	// 일단 지금있는 장소 0으로 밀어야함 
	// 아니면 이동후 겹치는거 체크할때, 무조건 오류가 나버림
	arr[x][y] = 0;
	for (int i = 0; i < 4; i++) {
		int nexx = x + dx[i];
		int nexy = y + dy[i];
		arr[nexx][nexy] = 0;
	}

	int xx = x + 1;
	int yy = y;
	if (arr[xx][yy] != 0) moveflag = 0;
	for (int i = 0; i < 4; i++) {
		int nexx = xx + dx[i];
		int nexy = yy + dy[i];
		if (nexx < 0 || nexx >= r+3 || nexy < 0 || nexy >= c) moveflag = 0;
		if (arr[nexx][nexy] != 0) moveflag=0;
	}

	if (moveflag == 1) {
		gl[gindex].x = xx;
		gl[gindex].y = yy;
		arr[xx][yy] = gindex;
		for (int i = 0; i < 4; i++) {
			int nexx = xx + dx[i];
			int nexy = yy + dy[i];
			arr[nexx][nexy] = gindex;
		}
		return fall(gindex);
		//return true;
	}
	else {
		arr[x][y] = gindex;
		for (int i = 0; i < 4; i++) {
			int nexx = x + dx[i];
			int nexy = y + dy[i];
			arr[nexx][nexy] = gindex;
		}
		return false;
	}
	return true;
}
bool left(int gindex) {
	int moveflag = 1;
	int x = gl[gindex].x;
	int y = gl[gindex].y;
	int dir = gl[gindex].dir;

	//초기화
	arr[x][y] = 0;
	for (int i = 0; i < 4; i++) {
		int nexx = x + dx[i];
		int nexy = y + dy[i];
		arr[nexx][nexy] = 0;
	}

	//이동 가능한지 체크
	int xx = x+1;
	int yy = y - 1;
	if (arr[xx][yy] != 0) moveflag = 0;
	for (int i = 0; i < 4; i++) {
		int nexx = xx + dx[i];
		int nexy = yy + dy[i];
		if (nexx < 0 || nexx >= r + 3 || nexy <= 0 || nexy >= c) moveflag = 0;
		if (arr[nexx][nexy] != 0) moveflag = 0;
	}

	//가능하면 새 장소에서 arr 표시
	if (moveflag == 1) {
		gl[gindex].x = xx;
		gl[gindex].y = yy;
		arr[xx][yy] = gindex;
		for (int i = 0; i < 4; i++) {
			int nexx = xx + dx[i];
			int nexy = yy + dy[i];
			arr[nexx][nexy] = gindex;
		}
		dir = dir - 1;
		if (dir < 0) dir = 3;

		//return fall(gindex);
		return true;
	}
	//불가능하면 기존의 장소에서 arr 표시
	else {
		arr[x][y] = gindex;
		for (int i = 0; i < 4; i++) {
			int nexx = x + dx[i];
			int nexy = y + dy[i];
			arr[nexx][nexy] = gindex;
		}
		return false;
	}
	
	return true;
}
bool right(int gindex) {
	int moveflag = 1;
	int x = gl[gindex].x;
	int y = gl[gindex].y;
	int dir = gl[gindex].dir;

	//초기화
	arr[x][y] = 0;
	for (int i = 0; i < 4; i++) {
		int nexx = x + dx[i];
		int nexy = y + dy[i];
		arr[nexx][nexy] = 0;
	}

	//이동 가능한지 체크
	int xx = x+1;
	int yy = y + 1;
	if (arr[xx][yy] != 0) moveflag = 0;
	for (int i = 0; i < 4; i++) {
		int nexx = xx + dx[i];
		int nexy = yy + dy[i];
		if (nexx <= 0 || nexx >= r + 3 || nexy <= 0 || nexy >= c) moveflag = 0;
		if (arr[nexx][nexy] != 0) moveflag = 0;
	}

	//가능하면 새 장소에서 arr 표시
	if (moveflag == 1) {
		gl[gindex].x = xx;
		gl[gindex].y = yy;
		arr[xx][yy] = gindex;
		for (int i = 0; i < 4; i++) {
			int nexx = xx + dx[i];
			int nexy = yy + dy[i];
			arr[nexx][nexy] = gindex;
		}
		dir = dir + 1;
		if (dir > 3) dir = 0;

		//오른쪽으로 이동후 다시 아래로
		//return fall(gindex);
		return true;
	}
	//불가능하면 기존의 장소에서 arr 표시
	else {
		arr[x][y] = gindex;
		for (int i = 0; i < 4; i++) {
			int nexx = x + dx[i];
			int nexy = y + dy[i];
			arr[nexx][nexy] = gindex;
		}
		return false;
	}
	return true;
}
void print() {
	for (int i = 0; i < r + 3; i++) {
		for (int j = 1; j <= c; j++) {
			cout << arr[i][j] << " ";
		}
		cout << endl;
	}
	cout << endl;
}
void test() {
	r = 6; c = 5; k = 6;
	
	golemstart(2,3,1);
	print();
	while (1) {
		if (fall(1) == 1)
			continue;
		if (left(1) == 1)
			continue;
		if (right(1) == 1)
			continue;
		break;
	}
	print();
	
	golemstart(2, 3, 2);
	while (1) {
		if (fall(2) == 1)
			continue;
		if (left(2) == 1)
			continue;
		if (right(2) == 1)
			continue;
		break;
	}
	
	print();
	
}
void bfs(int gindex) {
	int dir = gl[gindex].dir;
	int x = gl[gindex].x;
	int y = gl[gindex].y;

	queue<pair<int, int>> q;
	q.push({ x,y });

}
int main() {
	/*cin >> r >> c >> k;
	for (int i = 0; i < k; i++) {
		int ci, di;
		cin >> ci >> di;
	}*/
	test();
	return 0;
}