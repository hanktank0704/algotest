#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int n, m, k;
int arr[200][200];
int exit_r, exit_c;
struct man {
	int r;
	int c;
	int dis;
	int exit;
};
man marr[11];
void printarr() {
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			cout << arr[i][j] << " ";
		}
		cout << endl;
	}
	cout << endl;
}
void printman() {
	cout << "--------" << endl;
	for (int i = 0; i < m; i++) {
		cout << marr[i].r << " " << marr[i].c << endl;
	}
	cout << "--------" << endl;
}
void input() {
	cin >> n >> m >> k;
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			cin >> arr[i][j];
		}
	}
	for (int i = 0; i < m; i++) {
		int r, c;
		cin >> r >> c;
		marr[i] = { r,c, 0, 0 };
	}
	cin >> exit_r >> exit_c;
}

void move() {
	// marr에 존재하는 모든 참여자들이 움직여야한다

	// marr를 순회하면서 한명씩 고른다
	for (int i = 0; i < m; i++) {
		// 이미 엑싯을 한사람이면 안 돌림
		if (marr[i].exit == 1) continue;
		int cr = marr[i].r;
		int cc = marr[i].c;
		int cdis = marr[i].dis;

		int nr = cr;
		int nc = cc;
		int ndis = cdis;

		if (cr > exit_r && cc > exit_c) {
			// 상
			nr--;
		}
		if (cr > exit_r && cc == exit_c) {
			// 상
			nr--;
		}
		if (cr > exit_r && cc < exit_c) {
			// 상
			nr--;
		}
		if (cr == exit_r && cc < exit_c) {
			// 우
			nc++;
		}
		if (cr < exit_r && cc < exit_c) {
			// 하
			nr++;
		}
		if (cr < exit_r && cc == exit_c) {
			// 하
			nr++;
		}
		if (cr < exit_r && cc > exit_c) {
			// 하
			nr++;
		}
		if (cr == exit_r && cc > exit_c) {
			// 좌
			nc--;
		}
		// 이동한곳이 출구인 경우
		if (nr == exit_r && nc == exit_c) {
			marr[i].r = nr;
			marr[i].c = nc;
			marr[i].dis = cdis + 1;
			marr[i].exit = 1;
			continue;
		}

		// 가만히 있어야하는 경우, 벗어나거나 벽이있는겨우
		if (nr < 1 || nr > n || nc < 1 || nc > n || arr[nr][nc] != 0) {
			nr = cr;
			nc = cc;
			ndis = cdis;
		}
		else {
			marr[i].r = nr;
			marr[i].c = nc;
			marr[i].dis = cdis + 1;
		}
	}
	printman();


	// 고른 한명이 출구로 가능 방향을 구한다.
	// 참여자가 r,c 이고 출구가 er, ec
	// 8가지 경우로 나누어진다
	// r > a, c > b : 상 
	// r > a ,b ==c : 상
	// r> a, c < b : 상
	// r==a, b > c : 우
	// r < a, b < c : 하
	// r < a, b == c : 하
	// r < a, c > b : 하
	// r == a, c > b : 좌


	// 상하가 우선순위
	// 못움직이면 그냥 가만히 있는다


}

pair<pair<int, int>, int> find_square_rc() {
	int square_r;
	int square_c;
	for (int size = 2; size <= n; size++) {
		for (int i = 1; i <= n - size + 1; i++) {
			for (int j = 1; j <= n - size + 1; j++) {
				// r 의 범위 : i <= r <= i+size-1
				// 사람과 출구가 둘다 있어야함
				int flag = 0;

				//출구가 있는지 검사
				if (i <= exit_r && exit_r <= i + size - 1 && j <= exit_c && exit_c <= j + size - 1) {
					// 사람이 있는지 검사
					for (int p = 0; p < m; p++) {
						int cr = marr[p].r;
						int cc = marr[p].c;
						if (i <= cr && cr <= i + size - 1 && j <= cc && cc <= j + size - 1) {
							square_r = i;
							square_c = j;
							return { {square_r, square_c}, size };
						}

					}
				}

			}
		}
	}
	return { { 0,0 }, 0};
}

void spin() {
	// rotate 쓰면 안된다. 정직하게 어레이 돌려야지 사람이랑 출구도 돌릴수있음.!!!

	// 참가자를 한명은 포함하는 최소의 정사각형
	// 여러개 있으면 r이 작은순, 그다음 c가 작은순

	// 크기 2에서부터 시작해서 r=1, c=1에서부터 쭉 확인해본다.
	// 만약 범위안에 사람과 출구가 동시에 하면 break하고 그 i,j 가 최소의 정사각형이된다.

	int r, c;
	int size;
	pair<pair<int, int>, int> cur = find_square_rc();
	r = cur.first.first;
	c = cur.first.second;
	size = cur.second;

	cout << "square r, c, size: " << r << " " << c << " " << size << endl;

	// 정사각형을 시계로 90도 회전
	// 회전당한 벽은 내구도 - 1
	// 정사각형이 정해지면 일단 벽을 다 -1 하고 난뒤에 회전해도 될듯?
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= n; j++) {
			if (arr[i][j] > 0) {
				arr[i][j] -= 1;
			}
		}
	}

	// r,c,에서 size 크기의 정사각형을 시계로 90도 회전
	for (int i = r; i < r + size; i++) {
		for (int j = c; j < c + size; j++) {
			arr[i][j] = arr[size - 1 - j][i];
		}
	}
	
	/*
	for (int k = 0; k < size / 2; k++) {
		// k 번쨰 껍질 회전

		vector<int> v;
		for (int i = c + k; i < c + size - 1 - k; i++) {
			v.push_back(arr[r + k][i]);
		}
		for (int i = r + k; i < r + size - 1 - k; i++) {
			v.push_back(arr[i][c + size - 1 - k]);
		}
		for (int i = c + size - 1 - k; i > c + k; i--) {
			v.push_back(arr[r + size - 1 - k][i]);
		}
		for (int i = r + size - 1 - k; i > r + k; i--) {
			v.push_back(arr[i][c + k]);
		}

		rotate(v.begin(), v.begin() + 3 * (size - 2 * k - 1), v.end());

		int idx = 0;
		for (int i = c + k; i < c + size - 1 - k; i++) {
			arr[r + k][i] = v[idx++];
		}
		for (int i = r + k; i < r + size - 1 - k; i++) {
			arr[i][c + size - 1 - k] = v[idx++];
		}
		for (int i = c + size - 1 - k; i > c + k; i--) {
			arr[r + size - 1 - k][i] = v[idx++];
		}
		for (int i = r + size - 1 - k; i > r + k; i--) {
			arr[i][c + k] = v[idx++];
		}
	}
	*/

}


int main() {
	input();
	// k 초동안 움직인다
	for (int t = 0; t < k; t++) {
		move();
		spin();
		printarr();
		printman();
		int cc;
		cin >> cc;
	}

	/*
	printarr();
	for (int i = 0; i < m; i++) {
		cout << marr[i].r << " " << marr[i].c << endl;
	}
	cout << endl;
	*/
}
/*
5 3 8
0 0 0 0 1
9 2 2 0 0
0 1 0 1 0
0 0 0 1 0
0 0 0 0 0
1 3
3 1
3 5
3 3

5 3 8
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
0 0 0 0 0
1 3
3 1
3 5
3 3
*/