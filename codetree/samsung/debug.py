import sys
from collections import deque

n, m = map(int, input().split())

dx = [-1, 0, 0, 1]
dy = [0, -1, 1, 0]


class Man:
    def __init__(self, id, r, c, destin_r, destin_c, onboard):
        self.id = id
        self.r = r
        self.c = c
        self.destin_r = destin_r
        self.destin_c = destin_c
        self.onboard = onboard


def show_man():
    print("man")
    for i in range(1, m + 1):
        man = mans[i]
        print(man.r, man.c, man.destin_r, man.destin_c, man.onboard)


def show_arr():
    print("arr")
    for i in range(1, n + 1):
        for j in range(1, n + 1):
            print(arr[i][j], end="\t\t")
        print()


def show_parent(parent):
    print("parent")
    for i in range(1, n + 1):
        for j in range(1, n + 1):
            print(parent[i][j], end="\t")
        print()


def is_everyone_arrive():
    for i in range(1, m + 1):
        man = mans[i]
        if man.onboard == True:
            return False
    return True


def cal_dis(sx, sy, ex, ey):
    return abs(sx - ex) + abs(sy - ey)


def bfs_return_next(sx, sy, ex, ey, debug=False):
    q = deque()
    vis = [[0 for _ in range(n + 1)] for _ in range(n + 1)]
    parent = [[[] for _ in range(n + 1)] for _ in range(n + 1)]

    vis[sx][sy] = 1
    q.append([sx, sy])

    depth = 0

    # 디버깅 상태 출력을 위한 내부 함수
    def print_debug_state(current_depth, is_final=False):
        if not debug: return
        print(f"\n▶ [BFS 현재 거리(Depth): {current_depth}]")
        print(f"시작점: ({sx}, {sy})  |  목적지: ({ex}, {ey})")

        print("\nvis ")
        for i in range(1, n + 1):
            for j in range(1, n + 1):
                if arr[i][j] < 0:
                    print("  -  ", end="\t")
                else:
                    print(f"  {vis[i][j]}  ", end="\t")
            print()

        print("\nparent")
        for i in range(1, n + 1):
            for j in range(1, n + 1):
                if arr[i][j] < 0:
                    print("  -  ", end="\t")
                elif i == sx and j == sy:
                    print(" START ", end="\t")
                elif parent[i][j]:
                    px, py = parent[i][j][0]
                    print(f"({px},{py})", end="\t")
                else:
                    print("  .  ", end="\t")
            print()

        if not is_final:
            input("다음 겹(Layer) 탐색을 보려면 엔터를 누르세요...")

    while q:
        print_debug_state(depth)

        for _ in range(len(q)):
            cur = q.popleft()
            cx, cy = cur[0], cur[1]

            for i in range(4):
                nx = cx + dx[i]
                ny = cy + dy[i]

                if nx < 1 or nx > n or ny < 1 or ny > n:
                    continue
                if vis[nx][ny] != 0:
                    continue
                if arr[nx][ny] < 0:
                    continue

                vis[nx][ny] = vis[cx][cy] + 1
                q.append([nx, ny])
                parent[nx][ny].append((cx, cy))

        depth += 1

        if vis[ex][ey] != 0:
            print_debug_state(depth, is_final=True)
            if debug:
                print(f"\n>> 🚩 목적지({ex}, {ey}) 도달 완료! 역추적을 시작합니다.")
            break

    trace = []
    trace.append((ex, ey))
    bx, by = ex, ey
    before_x, before_y = 0, 0
    while True:
        if bx == sx and by == sy:
            break
        before = parent[bx][by][0]
        bx, by = before
        trace.append((bx, by))

    trace.reverse()

    if debug:
        print(f">> 🐾 역추적된 최종 경로: {trace}\n")

    before_x, before_y = trace[1][0], trace[1][1]
    return before_x, before_y


def move():
    for i in range(1, m + 1):
        man = mans[i]
        if man.onboard == False:
            continue

        cx, cy = man.r, man.c
        ex, ey = man.destin_r, man.destin_c

        # 여기서 debug=True가 적용되어 시뮬레이션 출력이 발생합니다.
        nx, ny = bfs_return_next(cx, cy, ex, ey, debug=True)
        man.r = nx
        man.c = ny


def arrive():
    for i in range(1, m + 1):
        man = mans[i]
        if man.onboard == False:
            continue

        if man.r == man.destin_r and man.c == man.destin_c:
            man.onboard = False
            arr[man.r][man.c] = -(10 + man.id)


def basecamp(t):
    if t > m:
        return

    arrive_x, arrive_y = 0, 0
    for i in range(1, n + 1):
        for j in range(1, n + 1):
            if arr[i][j] == 10 + t:
                arrive_x, arrive_y = i, j
                break

    vis = [[21e8 for _ in range(n + 1)] for _ in range(n + 1)]
    q = deque()

    vis[arrive_x][arrive_y] = 1
    q.append([arrive_x, arrive_y])

    while q:
        cur = q.popleft()
        cx, cy = cur[0], cur[1]

        for i in range(4):
            nx = cx + dx[i]
            ny = cy + dy[i]
            if nx < 1 or nx > n or ny < 1 or ny > n:
                continue
            if vis[nx][ny] != 21e8:
                continue
            if arr[nx][ny] < 0:
                continue
            vis[nx][ny] = vis[cx][cy] + 1
            q.append([nx, ny])

    mn_dis = 21e8
    mn_x, mn_y = 0, 0
    for i in range(1, n + 1):
        for j in range(1, n + 1):
            if arr[i][j] == 1:
                dis = vis[i][j]
                if mn_dis > dis:
                    mn_dis = dis
                    mn_x = i
                    mn_y = j

    man = mans[t]
    man.r = mn_x
    man.c = mn_y
    man.destin_r = arrive_x
    man.destin_c = arrive_y
    man.onboard = True

    arr[mn_x][mn_y] = -1


arr = [[0 for _ in range(n + 1)] for _ in range(n + 1)]
pyun = []
mans = []

for i in range(1, n + 1):
    row = [0] + list(map(int, input().split()))
    arr[i] = row
for i in range(1, m + 1):
    x, y = map(int, input().split())
    arr[x][y] = i + 10
    pyun.append([x, y])

mans.append(Man(0, -1, -1, -1, -1, False))
for i in range(1, m + 1):
    mans.append(Man(i, -1, -1, -1, -1, False))

ans = 0


def test():
    for time in range(1, 100000):
        print("\n" + "=" * 50)
        print(f"[{time} 분] 시뮬레이션 시작")
        print("=" * 50)

        if is_everyone_arrive() and time > 1 == True:
            global ans
            ans = time
            break

        move()
        arrive()
        basecamp(time)

        show_arr()
        # show_man()

    print(f"\n>> 최종 정답: {ans - 1}")


# 테스트 실행!
test()

"""
4 5
1 0 1 1
1 0 1 1
0 0 0 1
1 0 1 1
4 2
3 1
3 3
3 2
1 2
"""