#include <iostream>
#include <algorithm>
using namespace std;
int n, m, q;
int r;
char d;
int arr[101][101];
int vis[101];
void printarr() {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cout << arr[i][j] << " ";
		}
		cout << endl;
	}
}
void wind(int r, char d) {
	//왼쪽 오른쪽으로 민다.
	if (d == 'L') {
		//오른쪽으로 민다
		int t1 = arr[r][m - 1];
		for (int i = m - 1; i >= 1; i--) {
			arr[r][i] = arr[r][i - 1];
		}
		arr[r][0] = t1;
	}
	else {
		//왼쪽으로 민다
		int t2 = arr[r][0];
		for (int i = 0; i < m - 1; i++) {
			arr[r][i] = arr[r][i + 1];
		}
		arr[r][m - 1] = t2;
	}
}
void func(int r, char d) {
	wind(r, d);
	int nr;
	int flag = 0;
	char nd = d;
	// 위 방향 체크
	for (int i = r-1; i >= 0; i--) {
		flag = 0;
		for (int j = 0; j < m; j++) {
			if (arr[i][j] == arr[i + 1][j]) {
				flag = 1;
				break;
			}
		}
		if (flag == 1) {
			if (d == 'L') {
				nd = 'R';
				wind(i, nd);
			}
			else {
				nd = 'L';
				wind(i, nd);
			}
		}
		else break;
	}
	// 아래 방향 체크
	nd = d;
	for (int i = r + 1; i < n; i++) {
		flag = 0;
		for (int j = 0; j < m; j++) {
			if (arr[i][j] == arr[i - 1][j]) {
				flag = 1;
				break;
			}
		}
		if (flag == 1) {
			if (d == 'L') {
				nd = 'R';
				wind(i, nd);
			}
			else {
				nd = 'L';
				wind(i, nd);
			}
		}
		else break;
	}
}
void input() {
	cin >> n >> m >> q;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
	
			cin >> arr[i][j];
		}
	}
	for (int i = 0; i < q; i++) {
		cin >> r >> d;

		for (int i = 0; i < n; i++) vis[i] = 0;
		func(r-1, d);
	}
	printarr();
}
int main() {
	input();

	return 0;
}