#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
#include <queue>
using namespace std;
int n, m;
int sr, sc; int er, ec;
vector<pair<int, int>> route;
vector<pair<int, int>> mroute;
int routeflag = 0;
vector<pair<int, int>> war;
queue<pair<int, int>> q;

int arr[51][51];
int vis[51][51];
int medusa_zone[51][51];

int dx[4] = {-1,1,0,0};
int dy[4] = {0,0,-1,1};
int shortestlen = 0;
struct warrior {
	int x;
	int y;
	int rock;
};
void visclear() {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			vis[i][j] = 0;
		}
	}
}
void init() {
	memset(arr, 0, size(arr));
	memset(vis, 0, size(vis));
}
void input() {
	cin >> n >> m;
	cin >> sr >> sc >> er >> ec;
	for (int i = 0; i < m; i++) {
		int x, y;
		cin >> x >> y;
		war.push_back({ x,y });
	}
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> arr[i][j];
		}
	}
}
void printroute() {
	cout << "route" << endl;
	for (auto val : route) {
		cout << val.first << " " << val.second << "\n";
	}
	cout << endl;
}
void printmroute() {
	cout << "mroute" << endl;
	for (auto val : mroute) {
		cout << val.first << " " << val.second << "\n";
	}
	cout << endl;
}
void printvis() {
	cout << "vis" << endl;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cout << vis[i][j] << " ";
		}
		cout << endl;
	}
}
void bfs(int sx, int sy) {
	q.push({ sx,sy });
	vis[sx][sy] = 1;
	while (!q.empty()) {
		pair<int, int> cur = q.front(); q.pop();
		int cx = cur.first;
		int cy = cur.second;

		for (int i = 0; i < 4; i++) {
			int nx = cx + dx[i];
			int ny = cy + dy[i];
			if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
			if (vis[nx][ny] != 0 || arr[nx][ny] != 0) continue;
			vis[nx][ny] = vis[cx][cy] + 1;

			q.push({ nx,ny });
		}
	}

	shortestlen = vis[er][ec]-1;
	
	cout << "shorttestlen " << shortestlen << endl;
}
void dfs(int cx, int cy) {
	if (cx == er && cy == ec) {
		//route.push_back({ cx,cy });
		if (route.size() == shortestlen) {
			//printroute();
			if (routeflag == 0) {
				routeflag = 1;
				for (auto val : route) {
					mroute.push_back(val);
				}
			}

		}
		return;
	}
	for (int i = 0; i < 4; i++) {
		int nx = cx + dx[i];
		int ny = cy + dy[i];

		if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
		if (vis[nx][ny] != 0 || arr[nx][ny] != 0) continue;

		vis[nx][ny] = 1;
		route.push_back({ nx,ny });
		dfs(nx, ny);
		vis[nx][ny] = 0;
		route.pop_back();
	}
}
void move() {

}

void medusazone_clear() {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			medusa_zone[i][j] = 0;
		}
	}
}
void see(int r, int c) { // 메두사 좌표
	// for 문으로 상하좌우 방향
	int mx = -1;
	int mx_dir = -1;
	int cnt = 0;
	// 상
	medusazone_clear();
	int x, y;
	for (int i = 1; i <= r; i++) {
		// r-i, c-i ~ c+i
		x = r - i;

		// 일단 메두사 존을 표시한다. 1이 있으면 메두사의 영역이다.
		for (int j = -i; j <= i; j++) {
			y = c + j;
			if (x < 0 || x >= n || y < 0 || y >= n) continue;
			medusa_zone[x][y] = 1;
		}
		
		// 이제 1에 존재하는 전사를 찾는다.
		// 전사가 가리는 부분을 다시 0으로 수정해준다.
		for (auto val : war) {
			int wx = val.first; int wy = val.second;
			if (medusa_zone[wx][wy] == 1) {
				int xdif = r - wx;
				int ydif = c - wy;

				if (xdif == 0) {
					// 수직 위 방향  x-1
					for (int a = 0; a < wx; a++) {
						medusa_zone[a][wy] = 0;
					}
				}
				if (xdif > 0 && ydif < 0) {
					//오른쪽 위 방향 x-1, y+1
					for (int a = 1; a <= wx; a++) {
						int xx = wx - a;
						for (int b = wy; b <= wy + a; b++) {
							medusa_zone[xx][b] = 0;
						}
					}
				}
				if (xdif < 0 && ydif < 0) {
					//왼쪽 위 방향 x-1, y-1
					for (int a = 1; a <= wx; a++) {
						int xx = wx - a;
						for (int b = wy - a; b <= wy; b++) {
							medusa_zone[xx][b] = 0;
						}
					}
				}
			}
		}

		// 이제 medusa zone 에 존재하는 전사들의 개수를 계산한다.
		for (auto val : war) {
			int wx = val.first;
			int wy = val.second;

			if (medusa_zone[wx][wy] == 1)cnt++;
		}
		
		if (mx < cnt) {
			mx = cnt;
			mx_dir = 0;
		}
	}
	// 하
	medusazone_clear();
	int x, y;
	for (int i = 1; i <= n - r - 1; i++) {
		x = r + i;
		for (int j = -i; j <= i; j++) {
			y = c + j;

			if (x < 0 || x >= n || y < 0 || y >= n) continue;
			medusa_zone[x][y] = 1;
		}
	}
	for (auto val : war) {
		int wx = val.first; int wy = val.second;
		if (medusa_zone[wx][wy] == 1) {
			int xdif = r - wx; int ydif = c - wy;

			if (xdif == 0) {
				for (int a = c; a <= n; a++) {
					medusa_zone[wx][a] = 0;
				}
			}
			if (xdif > 0 && ydif < 0) {

			}
			if (xdif < 0 && ydif < 0) {

			}
		}
	}

	// 좌

	// 우

	// 각 방향에서 영향을 받는 전사의 수 계산

	/// 그중에서 가장 많은 방향을 찾는다.

	// 그 방향의 전사들의 rock 변수를 1로 설정해준다.

}
void warmove() {

}
void warattack() {

}
void func() {
	//mroute에 메두사의 경로를 저장했다.
	bfs(sr, sc);
	visclear();
	dfs(sr, sc);
	printmroute();

	int cnt = 0;
	while (1) {
		move();
		see(0,0);
		warmove();
		warattack();

		cnt++;
	}
}
void test() {
	bfs(sr,sc);
	visclear();
	dfs(sr,sc);
	printmroute();
	//printvis();
}
int main() {
	init();
	input();
	//func();
	test();

	return 0;
}