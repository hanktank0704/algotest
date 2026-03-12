#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;
int n, t;
int ans[31] = { 0 };
#define MINT 2;
#define CHOCO 3;
#define MILK 5;
#define MINTCHOCO 6;
#define CHOCOMILK 15;
#define MINTMILK 10;
#define MINTCHOCOMILK 30;
int dx[4] = { -1,1,0,0 };
int dy[4] = { 0,0,-1,1 };
int vis[50][50] = { 0 };
struct stu {
	int food;
	int belief;
	int junpa;
};
struct junpaza {
	int food;
	int belief;
	int x;
	int y;
};
stu arr[50][50];
vector<pair<int, int>> leader;
vector<junpaza> group1; // mint choco milk
vector<junpaza> group2; // mintchoco
vector<junpaza> group3; // mintchocomilk
void printBelief() {
	cout << "Belief" << endl;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cout << arr[i][j].belief << " ";
		}
		cout << endl;
	}
	cout << endl;
}
void printFood() {
	cout << "Food" << endl;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cout << arr[i][j].food << " ";
		}
		cout << endl;
	}
	cout << endl;
}
void printLeader() {
	cout << "leader" << endl;
	for (auto val : leader) {
		cout << val.first << " " << val.second << endl;
	}
	cout << endl;
	cout << endl;
}
void input() {
	cin >> n >> t;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			char c;
			cin >> c;
			if (c == 'T') {
				arr[i][j].food = MINT;
			}
			if (c == 'C') {
				arr[i][j].food = CHOCO;
			}
			if (c == 'M') {
				arr[i][j].food = MILK;
			}
		}
	}
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			int x;
			cin >> x;
			arr[i][j].belief = x;
		}
	}
}
void morning() {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			arr[i][j].belief += 1;
		}
	}
}
void bfs(int startx, int starty, int idx) {
	int mx = -1;
	int leaderx, leadery;
	queue<pair<int, int>> q;
	q.push({ startx,starty });
	vis[startx][starty] = idx;

	int startfood = arr[startx][starty].food;
	mx = arr[startx][starty].belief;
	leaderx = startx; leadery = starty;

	while (!q.empty()) {
		pair<int, int> cur = q.front(); q.pop();
		int curx = cur.first;
		int cury = cur.second;
		if (mx < arr[curx][cury].belief) {
			mx = arr[curx][cury].belief;
			leaderx = curx; leadery = cury;
		}

		for (int i = 0; i < 4; i++) {
			int nexx = curx + dx[i];
			int nexy = cury + dy[i];

			if (nexx < 0 || nexx >= n || nexy < 0 || nexy >= n)continue;
			if (vis[nexx][nexy] != 0) continue;
			if (arr[nexx][nexy].food != startfood) continue;

			vis[nexx][nexy] = idx;
			q.push({ nexx,nexy });
		}
	}
	leader.push_back({ leaderx, leadery });
}
void visitclear() {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			vis[i][j] = 0;
		}
	}
}
void lunch() {
	leader.clear();
	visitclear();
	int idx = 1;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (vis[i][j] == 0) {
				bfs(i, j, idx);
				pair<int, int> curleader = leader[leader.size() - 1];
				int curx = curleader.first;
				int cury = curleader.second;

				for (int a = 0; a < n; a++) {
					for (int b = 0; b < n; b++) {
						if (a == curx && b == cury)continue;
						if (vis[a][b] == idx) {
							arr[a][b].belief -= 1;
							arr[curx][cury].belief += 1;
						}
					}
				}
				idx++;
			}
		}
	}
}
void printgroup() {
	cout << "group1" << endl;
	for (auto val : group1) {
		cout << val.x << " " << val.y << endl;
	}
	cout << "group2" << endl;
	for (auto val : group2) {
		cout << val.x << " " << val.y << endl;
	}
	cout << "group3" << endl;
	for (auto val : group3) {
		cout << val.x << " " << val.y << endl;
	}
	cout << endl << endl;
}
bool cmp(junpaza a, junpaza b) {
	if (a.belief != b.belief) return a.belief > b.belief;
	if (a.x != b.x) return a.x > b.x;
	if (a.y != b.y) return a.y > b.y;
	return true;
}
int mixfood(int food1, int food2) {
	int temp = food1 * food2;
	int xx = 1;
	if (temp % 2 == 0) xx *= 2;
	if (temp % 3 == 0) xx *= 3;
	if (temp % 5 == 0) xx *= 5;

	return xx;
}
void mission(int startx, int starty) {
	//0 : up, 1 : down, 2: left, 3: right
	int dir = arr[startx][starty].belief % 4;
	int x = arr[startx][starty].belief - 1;
	arr[startx][starty].belief = 1;
	int probex = startx ; int probey = starty ;
	// 전파 시작
	while (1) {
		probex += dx[dir]; probey += dy[dir];
		//TODO : 간절함이 0이하가 되면 어떻게 한다는거지?????????????????????????
		if (probex < 0 || probex >= n || probey < 0 || probey >= n) break;
		if (x <= 0) break;
		if (arr[probex][probey].food == arr[startx][starty].food) {
			//probex += dx[dir]; probey += dy[dir];

			continue;
		}
		// 강한전파
		int y = arr[probex][probey].belief;
		if (x > y) {
			arr[probex][probey].food = arr[startx][starty].food;
			x -= (y + 1);
			arr[probex][probey].belief += 1;
		}
		// 약한전파
		else {
			// 전파자의 모든 음식도 관심을 가지게 된다.

			arr[probex][probey].food = mixfood(arr[probex][probey].food, arr[startx][starty].food);
			arr[probex][probey].belief += x;
			x = 0;
		}
	}
	cout << startx+1 << " " << starty+1 << endl;
	printBelief();
}
void dinner() {
	//전파 순서
	//단일 음식 -> 이중조합 ->삼중
	// 신앙심 , 행 ,열
	vector<junpaza> jjj;
	for (int i = 0; i < leader.size(); i++) {
		pair<int, int> cur = leader[i];
		int curx = cur.first; int cury = cur.second;
		jjj.push_back({ arr[curx][cury].food, arr[curx][cury].belief, curx, cury });
	}
	/*
	cout << "jjj" << endl;
	for (int i = 0; i < jjj.size(); i++) {
		cout << jjj[i].x << " " << jjj[i].y << endl;
	}
	cout << endl;
	*/
	for (int i = 0; i < jjj.size(); i++) {
		junpaza temp = { jjj[i].food,jjj[i].belief,jjj[i].x,jjj[i].y };
		if (jjj[i].food == 2 || jjj[i].food == 3 || jjj[i].food == 5) group1.push_back(temp);
		if (jjj[i].food == 6 || jjj[i].food == 10 || jjj[i].food == 15) group2.push_back(temp);
		if (jjj[i].food == 30 ) group3.push_back(temp);
	}

	//printgroup();
	sort(group1.begin(), group1.end(), cmp);
	sort(group2.begin(), group2.end(), cmp);
	sort(group3.begin(), group3.end(), cmp);
	printgroup();

	//group1
	for (auto val : group1) {
		mission(val.x, val.y);
	}
	//group2
	for (auto val : group2) {
		mission(val.x, val.y);
	}
	//group3
	for (auto val : group3) {
		mission(val.x, val.y);
	}
}
void findans() {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (arr[i][j].food == 30) {
				ans[30] += arr[i][j].belief;
			}
			if (arr[i][j].food == 6) {
				ans[6] += arr[i][j].belief;

			}
			if (arr[i][j].food == 10) {
				ans[10] += arr[i][j].belief;

			}
			if (arr[i][j].food == 15) {
				ans[15] += arr[i][j].belief;

			}
			if (arr[i][j].food == 5) {
				ans[5] += arr[i][j].belief;

			}
			if (arr[i][j].food == 3) {
				ans[3] += arr[i][j].belief;

			}
			if (arr[i][j].food == 2) {
				ans[2] += arr[i][j].belief;

			}
		}
	}
	cout << ans[30] << " ";
	cout << ans[6] << " ";
	cout << ans[10] << " ";
	cout << ans[15] << " ";
	cout << ans[5] << " ";
	cout << ans[3] << " ";
	cout << ans[2] << " ";
	cout << endl;
}
void func() {
	for (int i = 0; i < t; i++) {
		leader.clear();
		group1.clear();
		group2.clear();
		group3.clear();
		visitclear();
		printFood();
		morning();
		cout << "morning" << endl;
		printBelief();
		lunch();
		cout << "lunch" << endl;
		printBelief();
		dinner();
		//printFood();
		//printBelief();
		findans();
	}
}
void test() {
	morning();
	lunch();
	printBelief();
	printLeader();

	dinner();
	printFood();
	printBelief();

	findans();

};
int main() {
	input();

	//test();
	func();

	return 0;
}
/*
4 2
TTCC
TTTM
CCMM
CMMM
1 3 3 3
2 23 16 8
12 6 7 8
12 8 3 5
*/