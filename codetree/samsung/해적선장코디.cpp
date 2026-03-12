#include <iostream>
using namespace std;
struct ship {
	int id;
	int p;
	int r;
	int state;
};
int main() {
	// T 명령

	// 1. 공격 준비
	// N 척의 선박 사격 준비

	// 2. 지원요청
	// 선박 추가, 사격 대기, id, p ,r

	// 3. 함포 교체
	// id 의 선박 p 수정

	// 4. 공격 명령
	// state가 사격대기인것 중, 최대 공격력 5개 사격
	// ship.p 동일하면 id 작은거
	// 공격력 합만큼 피해
	// state : 재장전 , r 시간 경과해야 대기 상태
	// 재장전은 공격못함

	// for(int i=0; i<T; i++) 명령어 하나씩 실행

	// T = 5 * 10^5
	// 선박 N개 10^4 개
	// id는 상관없는듯? 걍 int 수정하는거라
	// pw 보니 오버플러우나서 long long 해야할 듯
	// 대기 시간 최대 10

	// ship struct를 정렬해야할 듯 최대 5개를 바로 찾을 수 있게
	// 근데 10000 개의 배를 정렬을 매번하면 시간초과
	// n log(n) * n 인듯. 하면 되는데 프루닝 하면 될지도? 5초
	// 10^8 * 4 라서 4초 인듯? 근데, 항상 10000개는 아니라 그것보다 작을듯

	// 단순 구현으로도 가능할까.
	// struct에 정렬 operator 만들어서 계속 정렬 시키면 될듯.

	return 0;
}