import sys
from collections import deque

input = sys.stdin.readline

n, m, k = map(int, input().split())
arr = [[0 for _ in range(m + 1)] for _ in range(n + 1)]
last_attack = [[0 for _ in range(m + 1)] for _ in range(n + 1)]
affected = [[0 for _ in range(m + 1)] for _ in range(n + 1)]

for i in range(1, n + 1):
    row = [0] + list(map(int, input().split()))
    arr[i] = row


def show_arr():
    print("arr")
    for i in range(1, n + 1):
        for j in range(1, m + 1):
            print(arr[i][j], end="\t")
        print()

def show_vis(vis):
    print("vis")
    for i in range(1, n + 1):
        for j in range(1, n + 1):
            print(vis[i][j], end="\t")
        print()

def show_map(map):
    # print("vis")
    for i in range(1, n + 1):
        for j in range(1, n + 1):
            print(map[i][j], end="\t")
        print()


def choose_attacker():
    # 일단 최솟값을 찾는다.
    # 최솟값을 가진 좌표들을 모두 저장한다.
    # 그중에서 가장 최근에 발포한거 찾는다.
    # 그중에서 행열 합이 가장큰거, 이후 열이 가장 큰거
    candidate = []
    mn = 21e8
    for i in range(1, n + 1):
        for j in range(1, m + 1):
            if arr[i][j] <= 0:
                continue
            if mn > arr[i][j]:
                mn = arr[i][j]
    # print(mn)

    for i in range(1, n + 1):
        for j in range(1, m + 1):
            if arr[i][j] == mn:
                candidate.append((i, j, last_attack[i][j]))

    # 후보들중에서 판별
    candidate.sort(key=lambda x: (-x[2], -(x[0] + x[1]), -x[1]))

    # print("candidate")
    # print(candidate)
    # for i in range(1,n+1):
    #     for j in range(1,m+1):
    #         print(last_attack[i][j], end = "\t")
    #     print()

    nr, nc, x = candidate[0]

    arr[nr][nc] += (n + m)

    # print("attacker", nr, nc)
    return (nr, nc)


def attack(sr, sc, time):
    # sr,sc 를 제외한 가장 강한 포탑

    # 일단 최댓값을 찾는다.
    # 최댓값 후보들을 모두 candidate에 저장한다
    # 그중에서 가장 오래된거, 행열합, 열이 가장 작은

    affected[sr][sc] = 1

    candidate = []
    mx = -1
    for i in range(1, n + 1):
        for j in range(1, m + 1):
            if i==sr and j==sc:
                continue
            if arr[i][j] <= 0:
                continue
            if mx < arr[i][j]:
                mx = arr[i][j]
                # print(arr[i][j], i, j)

    # print("mx", mx)



    for i in range(1, n + 1):
        for j in range(1, m + 1):
            if i==sr and j ==sc:
                continue
            if arr[i][j] == mx:
                candidate.append((i, j, last_attack[i][j]))

    # print(candidate)

    candidate.sort(key=lambda x: (x[2], x[0] + x[1], x[1]))

    nr, nc, x = candidate[0]

    # print("attacked", nr, nc)

    # 레이저 공격
    # 레이저 공격이 안되면 폭탄 공격을한다
    # 레이저 공격이 가능한지 먼저 판별을 해야한다.
    # bfs이지만 벽을 통과할시 반대로 순간이동하는 방식이다.
    # 경로도 저장해야한다. 그래서 parent[][] 도 필요하다

    q = deque()
    dx = [0, 1, 0, -1]
    dy = [1, 0, -1, 0]

    vis = [[0 for _ in range(m + 1)] for _ in range(n + 1)]
    parent = [[[] for _ in range(m + 1)] for _ in range(n + 1)]

    q.append((sr, sc))
    vis[sr][sc] = 1
    parent[sr][sc].append((-1, -1))

    while q:
        cr, cc = q.popleft()
        for i in range(4):
            nexr = cr + dx[i]
            nexc = cc + dy[i]

            if nexr < 1:
                nexr = n
            if nexr > n:
                nexr = 1
            if nexc < 1:
                nexc = m
            if nexc > m:
                nexc = 1

            if arr[nexr][nexc] <= 0:
                continue
            if vis[nexr][nexc] != 0:
                continue

            vis[nexr][nexc] = vis[cr][cc] + 1
            parent[nexr][nexc].append((cr, cc))

            q.append((nexr, nexc))

    # print("vis")
    # show_map(vis)
    # print("map")
    # show_map(parent)

    # 일단 공격을 하니까
    last_attack[sr][sc] = time

    # vis[i][j] 가 0 이 아니면 레이저 공격 가능!
    # show_vis(vis)
    if vis[nr][nc] != 0:
        # parent로 경로를 알수있다
        # 일단 목적지 nr, nc 의 공격력을 감소시킨다.
        # 경로는 절반만큼 감소!

        arr[nr][nc] -= arr[sr][sc]
        affected[nr][nc] = 1

        # parent 를 따라서 경로의 자표들을 모두 감소시켜준다!
        tr, tc = nr, nc
        # print("start", tr,tc)
        while True:
            if len(parent[tr][tc]) == 0:
                break
            tr, tc = parent[tr][tc][0]
            if tr == sr and tc == sc:
                break
            # print(tr,tc)
            arr[tr][tc] -= int((arr[sr][sc] / 2))
            affected[tr][tc] = 1

        # show_arr()

    # 폭탄 공격
    # TODO
    else:
        bomb_attack(sr, sc, nr, nc, time)

    # return (nr, nc)

def bomb_attack(sr, sc, nr, nc, time):
    dx = [-1, -1, -1, 0, 1, 1, 1, 0]
    dy = [-1, 0, 1, 1, 1, 0, -1, -1]

    arr[nr][nc] -= arr[sr][sc]
    affected[nr][nc] = 1

    for i in range(8):
        nexr = nr + dx[i]
        nexc = nc + dy[i]


        if nexr < 1:
            nexr = n
        if nexr > n:
            nexr = 1
        if nexc < 1:
            nexc = m
        if nexc > m:
            nexc = 1

        if nexr == sr and nexc == sc:
            continue

        if arr[nexr][nexc] <= 0:
            continue

        arr[nexr][nexc] -= int(arr[sr][sc] / 2)
        affected[nexr][nexc] = 1


def destroy():
    # 공격력이 0 이하면 부서진다.
    for i in range(1, n + 1):
        for j in range(1, m + 1):
            if arr[i][j] < 0:
                arr[i][j] = 0

    pass


def fix():
    for i in range(1,n+1):
        for j in range(1,m+1):
            if arr[i][j] > 0 and affected[i][j] == 0:
                arr[i][j] += 1

def one_left():
    cnt = 0
    for i in range(1,n+1):
        for j in range(1,m+1):
           if arr[i][j] > 0:
               cnt += 1
    if cnt == 1:
        return True
    else:
        return False

def debug():
    for time in range(1,k+1):
        # affected = [[0 for _ in range(n + 1)] for _ in range(m + 1)]
        for i in range(n+1):
            for j in range(m+1):
                affected[i][j] = 0



        print("###############")
        show_arr()

        # 1.
        nr, nc = choose_attacker()
        print("attacker", nr , nc)

        # show_arr()

        # 2.
        attack(nr, nc, time)

        # laser_attack()

        # bomb_attack()

        # 3.
        destroy()

        # 4.
        fix()

        show_arr()

        print("###############")
        input()

        mx = -1
        for i in range(1,n+1):
            for j in range(1,m+1):
                if mx < arr[i][j]:
                    mx = arr[i][j]
        print(mx)


def solution():
    for time in range(1, k + 1):
        # affected = [[0 for _ in range(n + 1)] for _ in range(m + 1)]
        for i in range(n+1):
            for j in range(m+1):
                affected[i][j] = 0

        if one_left() == True:
            break
        # show_arr()

        # 1.
        nr, nc = choose_attacker()


        # 2.
        attack(nr, nc, time)

        # 3.
        destroy()

        # 4.
        fix()

        mx = -1

    for i in range(1, n + 1):
        for j in range(1, m + 1):
            if mx < arr[i][j]:
                mx = arr[i][j]
    print(mx)

solution()
# debug()
