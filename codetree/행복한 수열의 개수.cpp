#include <iostream>
#include <vector>
#include <algorithm>
#include <string.h>
using namespace std;
int n, m;
int arr[101][101];
void input() {
	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> arr[i][j];
		}
	}
}
int checkrow(int row) {
	int flag = 1;
	for (int i = 0; i <= n - m; i++) {
		flag = 1;
		int start = arr[row][i];
		for (int j = 0; j < m; j++) {
			if (arr[row][i + j] != start) {
				flag = 0;
				break;
			}
		}
		if (flag == 1) {
			//cout << "row " << i << endl;
			return 1;
		}
	}
	return 0;
}
int checkcolumn(int col) {
	int flag = 1;
	for (int i = 0; i <= n - m; i++) {
		flag = 1;
		int start = arr[i][col];
		for (int j = 0; j < m; j++) {
			if (arr[i + j][col] != start) {
				flag = 0;
				break;
			}
		}
		if (flag == 1) {
			//cout << "col " << i << endl;
			return 1;
		}
	}
	return 0;
}
void func() {
	int sum = 0;
	for (int i = 0; i < n; i++) {
		sum += checkrow(i);
	}
	//cout << "column" << "\n";
	for (int i = 0; i < n; i++) {
		sum += checkcolumn(i);
	}
	cout << sum << "\n";
}
int main() {
	input();
	func();
	return 0;
}
/*
3 2
1 2 2
1 3 4
1 2 3
*/