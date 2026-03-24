#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;
int ans = 0;
int R, C;
int k;
int startc;
int dir;
int arr[80][80];
int vis[80][80];
int dx[4] = { -1,0,1,0 };
int dy[4] = { 0,1,0,-1 };
int gol_cnt = 0;
struct golem {
	int r;
	int c;
	int dir;
};
vector<golem> G;

void printarr() {
	cout << "arr" << endl;
	for (int i = 0; i < R + 3; i++) {
		for (int j = 0; j < C; j++) {
			cout << arr[i][j] << " ";
		}
		cout << endl;
	}
	cout << "----------------" << endl;
}
int fall(golem g) {
	int r = g.r;
	int c = g.c;
	int dir = g.dir;

	int flag = 1;
	if (r + 2 > R + 2) flag = 0;
	// if (r + 2 <= R + 3) flag = 0;
	if (arr[r + 1][c - 1] != 0) {
		flag = 0;
	}
	if (arr[r + 2][c] != 0) {
		flag = 0;
	}
	if (arr[r + 1][c + 1] != 0) {
		flag = 0;
	}

	return flag;
}
int left(golem g) {
	int r = g.r;
	int c = g.c;
	int dir = g.dir;

	int flag = 1;
	//	if (c - 2 < 0 || c + 2 >= C) flag = 0;
	if (c - 2 < 0) flag = 0;
	if (r + 2 > R + 2) flag = 0;

	if (arr[r - 1][c - 1] != 0) {
		flag = 0;
	}
	if (arr[r][c - 2] != 0) {
		flag = 0;
	}
	if (arr[r + 1][c - 1] != 0) {
		flag = 0;
	}

	if (arr[r + 1][c - 2] != 0) {
		flag = 0;
	}
	if (arr[r + 2][c - 1] != 0) {
		flag = 0;
	}

	return flag;
}
int right(golem g) {
	int r = g.r;
	int c = g.c;
	int dir = g.dir;

	int flag = 1;
	if (c + 2 >= C) flag = 0;
	//cout << "flag " << flag << endl;
	if (r + 2 > R + 2) flag = 0;
	//cout << "flag " << flag << endl;

	if (arr[r - 1][c + 1] != 0) {
		flag = 0;
	}
	//cout << "flag " << flag << endl;

	if (arr[r][c + 2] != 0) {
		flag = 0;
		//cout << "flag " << flag << endl;
	}
	//cout << "flag " << flag << endl;
	if (arr[r + 1][c + 1] != 0) {
		flag = 0;
	}
	//cout << "flag " << flag << endl;

	if (arr[r + 1][c + 2] != 0) {
		flag = 0;
	}
	//cout << "flag " << flag << endl;
	if (arr[r + 2][c + 1] != 0) {
		flag = 0;
	}
	//cout << "flag " << flag << endl;

	return flag;
}
void golemstart(int startc, int dir) {
	//cout << "golemstart" << endl;
	golem gol = { 1,startc,dir };
	gol_cnt++;
	// fall, left, right
	while (1) {
		//cout << gol.r << " " << gol.c << " " << gol.dir << endl;
		if (fall(gol) == 1) {
			gol.r++;
		}
		else if (left(gol) == 1) {
			gol.r++;
			gol.c--;

			//dir 반시계
			gol.dir--;
			if (gol.dir < 0) gol.dir = 3;
		}
		else if (right(gol) == 1) {
			gol.r++;
			gol.c++;

			//dir 시계
			gol.dir++;
			if (gol.dir > 3) gol.dir = 0;
		}
		else {
			G.push_back(gol);
			int r = gol.r;
			int c = gol.c;
			int dir = gol.dir;

			int exit_r = r + dx[dir];
			int exit_c = c + dy[dir];


			arr[r][c] = gol_cnt;
			arr[r + 1][c] = gol_cnt;
			arr[r - 1][c] = gol_cnt;
			arr[r][c - 1] = gol_cnt;
			arr[r][c + 1] = gol_cnt;

			arr[exit_r][exit_c] = arr[exit_r][exit_c] * (-1);
			break;
		}
	}
}
int golemsouth(int r, int c) {
	// bfs 로 최남단 찾아야함
	//vis 초기화
	for (int i = 0; i < R + 3; i++) {
		for (int j = 0; j < C; j++) {
			vis[i][j] = 0;
		}
	}
	int south = 0;
	queue<pair<int, int>> q;
	q.push({ r,c });
	south = max(south, r);
	vis[r][c] = 1;
	while (!q.empty()) {
		pair<int, int > cur = q.front(); q.pop();
		int cx = cur.first;
		int cy = cur.second;
		south = max(south, cx);
		for (int i = 0; i < 4; i++) {
			int nx = cx + dx[i];
			int ny = cy + dy[i];
			if (nx < 0 || nx >= R + 3 || ny < 0 || ny >= C) continue;
			if (vis[nx][ny] != 0) continue;
			if (arr[nx][ny] == 0) continue;
			// 출구면 인접한 공간 접근 가능하다. 방문했는지도 확인해야함
			if (arr[cx][cy] < 0) {
				vis[nx][ny] = 1;
				q.push({ nx,ny });
			}
			// 출구가 아니면 현재 공간과 동일한 곳만 방문 가능하다.
			if (arr[cx][cy] > 0) {
				if (abs(arr[nx][ny]) == arr[cx][cy]) {
					vis[nx][ny] = 1;
					q.push({ nx,ny });
				}
			}
		}

	}

	return south;
	// 
}
int checkOverflow() {
	// r; 0,1,2,에 숫자가 있으면 오버플러우임
	int flag = 0;
	for (int i = 0; i < 3; i++) {
		for (int j = 0; j < C; j++) {
			if (arr[i][j] != 0) {
				flag = 1;
				break;
			}
		}
	}
	return flag;
}
void input() {
	cin >> R >> C >> k;
	for (int i = 0; i < k; i++) {
		cin >> startc >> dir;
		startc--;
		golemstart(startc, dir);


		// 비우는 거 해야함.
		// arr 에 다 입력되어있는데,
		// 이게 0,1,2 구역에 숫작 잇으면 넘어간거다. 그것만 판별해서 
		// 넘어가면 다 지운다.
		if (checkOverflow() == 1) {
			for (int i = 0; i < R + 3; i++) {
				for (int j = 0; j < C; j++) {
					arr[i][j] = 0;
					vis[i][j] = 0;
				}
			}
			continue;
		}

		
		// 이제 최남단 위치를 찾아야한다.
		golem cur = G[i];
		int cr = cur.r;
		int cc = cur.c;
		int temp = golemsouth(cr, cc);
		temp-=2;
		//cout << "south " << temp << endl;
		ans += temp;

		//printarr();
	}
	cout << ans << endl;
}
int main() {
	input();
	return 0;
}
/*
6 5 6
2 3
2 0
4 2
2 0
2 0
2 2
*/