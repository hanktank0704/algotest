#include <iostream>
#include <algorithm>
#include <vector>
#include <math.h>
using namespace std;
int n;
int arr[21][21];
int dx[4] = {1,1,-1,-1};
int dy[4] = {-1,1,1,-1};
void find() {

}
void input() {
	cin >> n; 
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> arr[i][j];
		}
	}
}
void func() {

}
int main() {
	input();
	func();
	return 0;
}