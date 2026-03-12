#include <iostream>
#include <vector>
#include <algorithm>
#include <math.h>
using namespace std;
int n, m;
int arr[21][21];
int mx = 0;
int dig_cnt = 0;

//마름모가 0,0 에 있고 n-1,n-1 까지 다 덮을려면 k가 n이 아니라 
//2*n 까지 다 덮어야한다. 
//mx는 이론상 가장 작은 값으로 하는게 좋다. 괜히 너무 작은 값으로해도
//오류가 나올수 있다.
void input() {
	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> arr[i][j];
		}
	}
}
int check(int r, int c, int width) {
	int gold = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			int dis = abs(i - r) + abs(j - c);
			//if (dis <= width) dig_cnt++;
			if (dis <= width && arr[i][j] == 1) {
				gold++;
			}
		}
	}
	return gold;
}
void func() {
	int total_gold = 0;
	dig_cnt = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			for (int k = 0; k <= 2*n; k++) {
				total_gold = check(i, j, k);
				if (total_gold * m >= k*k + (k+1)*(k+1)) {
					mx = max(total_gold, mx);
					//cout << "total_gold " << total_gold << " ";
					//cout << "cord: " << i << " " << j << " ";
					//cout << "k " << k << endl;
				}
			}
		}
	}
	cout << mx << endl;
}
int main() {
	input();
	func();
	return 0;
}
/*
5 5
0 0 0 0 0
0 1 0 0 0
0 0 1 0 1
0 0 0 0 0
0 0 0 1 0

3 2
0 1 0
1 0 1
0 0 0
*/