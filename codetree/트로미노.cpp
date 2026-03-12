#include <iostream>
#include <vector>
#include <algorithm>
#include <math.h>
using namespace std;
int n, m;
int arr[211][211];
int checkfirst(int r, int c) {
	int sum = -1; 
	//1
	if (r + 1 >= n || c + 1 >= m) return 0;

	int sum1 = arr[r][c] + arr[r + 1][c] + arr[r + 1][c + 1];
	int sum2 = arr[r][c+1] + arr[r + 1][c] + arr[r + 1][c + 1];
	int sum3 = arr[r][c] + arr[r][c+1] + arr[r + 1][c];
	int sum4 = arr[r][c] + arr[r][c+1] + arr[r + 1][c + 1];
	sum = max(sum1, sum);
	sum = max(sum2, sum);
	sum = max(sum3, sum);
	sum = max(sum4, sum);

	return sum;
}
int checksecond(int r, int c) {
	int sum = -1;
	int sum1 = 0;
	int sum2 = 0;
	if (c + 2 < m) {
		sum1 = arr[r][c] + arr[r][c + 1] + arr[r][c + 2];
	}
	if (r + 2 < n) {
		sum2 = arr[r][c] + arr[r+1][c] + arr[r+2][c];
	}

	sum = max(sum1, sum);
	sum = max(sum2, sum);

	return sum;
}
void input() {
	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> arr[i][j];
		}
	}
}
void func() {
	int mx = -1;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			int t1 = checkfirst(i,j);
			int t2 = checksecond(i,j);
			int temp = max(t1, t2);
			mx = max(mx, temp);
			//cout << mx << endl;
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
3 3
1 2 3
3 2 1
3 1 1

4 5
6 5 4 3 1
3 4 4 14 1
6 1 3 15 5
3 5 1 16 3
*/