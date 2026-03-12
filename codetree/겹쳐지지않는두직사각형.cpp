#include <iostream>
#include <algorithm>
using namespace std;
#define MX -21e8
int n, m;
int grid[6][6];

int check(int r1, int c1, int r2, int c2, int x1, int y1, int x2, int y2) {
	//if (x1 >= r1 && x1 <= r2 && y1 >= c1 && y1 <= c2) {
	//	return MX;
	//}
	//if (x2 >= r1 && x2 <= r2 && y1 >= c1 && y1 <= c2) {
	//	return MX;
	//}
	//if (x2 >= r1 && x2 <= r2 && y2 >= c1 && y2 <= c2) {
	//	return MX;
	//}
	//if (x1 >= r1 && x1 <= r2 && y2 >= c1 && y2 <= c2) {
	//	return MX;
	//}
	//if (r1 <= x1 && x2 <= r2 && c1 <= y1 && y2 <= c2) {
	//	return MX;
	//}
	//if (x1 <= r1 && r2 <= x2 && y1 <= c1 && c2 <= y2) {
	//	return MX;
	//}

	//if (r2 >= x1) return MX;
	//if (c2 >= y1) return MX;

	int sum = 0;
	if (r2 < x1 || x2 < r1 || c2 < y1 || y2 < c1) {
		for (int r = r1; r <= r2; r++) {
			for (int c = c1; c <= c2; c++) {
				sum += grid[r][c];
			}
		}
		for (int r = x1; r <= x2; r++) {
			for (int c = y1; c <= y2; c++) {
				sum += grid[r][c];
			}
		}
		//cout << r1 << " " << c1 << " ";
		//cout << r2 << " " << c2 << endl;
		//cout << x1 << " " << y1 << " ";
		//cout << x2 << " " << y2 << endl;
		//cout << sum << endl;
		//if (sum == 84) {
		//	cout << r1 << " " << c1 << " ";
		//	cout << r2 << " " << c2 << endl;
		//	cout << x1 << " " << y1 << " ";
		//	cout << x2 << " " << y2 << endl;
		//	cout << sum << endl;
		//	cout << "sum is 84 l..........." << endl;
		//}
	}
	else {
		return MX;
	}
	
	return sum;
}
int main() {
	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			cin >> grid[i][j];
		}
	}

	// Please write your code here.
	int mx = MX;
	for (int r1 = 0; r1 < n; r1++) {
		for (int c1 = 0; c1 < m; c1++) {
			for (int r2 = r1; r2 < n; r2++) {
				for(int c2 = c1; c2<m; c2++){
					for (int x1 = 0; x1 < n; x1++) {
						for (int y1 = 0; y1 < m; y1++) {
							for (int x2 = x1; x2 < n; x2++) {
								for (int y2 = y1; y2 < m; y2++) {
									int temp = check(r1, c1, r2, c2, x1, y1, x2, y2);
									mx = max(temp, mx);
									//if (temp != MX) {
									//	cout << temp << endl;
									//}
								}
							}
						}
					}
				}
			}
		}
	}
	cout << mx << endl;
	return 0;
}
/*
2 2
-1 -2
-3 -4

4 5
6 5 4 -3 1
3 -4 -4 14 1
6 1 -3 15 -5
3 -5 1 16 3
*/
