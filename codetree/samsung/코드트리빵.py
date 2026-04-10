# 1번은 1분에, n번은 n번에 이동

# 목표하는 편의점은 다다르다

# 1, 2, 3

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
    flag = True
    for i in range(1, m + 1):
        man = mans[i]
        if man.onboard == True:
            return False

    return True


def cal_dis(sx, sy, ex, ey):
    return abs(sx - ex) + abs(sy - ey)


def bfs_return_next(sx, sy, ex, ey):
    q = deque()
    vis = [[0 for _ in range(n + 1)] for _ in range(n + 1)]
    parent = [[[] for _ in range(n + 1)] for _ in range(n + 1)]

    vis[sx][sy] = 1
    q.append([sx, sy])

    while q:
        cur = q.popleft()
        cx, cy = cur[0], cur[1]

        for i in range(4):
            nx = cx + dx[i]
            ny = cy + dy[i]

            if nx < 1 or nx > n or ny < 1 or ny > n:
                continue
            if vis[nx][ny] != 0:
                continue

            # 못지나가게 막힌 경우
            if arr[nx][ny] < 0:
                continue

            vis[nx][ny] = vis[cx][cy] + 1
            q.append([nx, ny])
            # 각 노드의 부모 좌표를 저장해놓는다.
            parent[nx][ny].append((cx,cy))

    # 부모의 좌표들을 거슬러 올라가야한다.
    # show_parent(parent)

    trace = []
    trace.append((ex,ey))
    bx, by = ex, ey
    before_x, before_y = 0, 0
    while True:
        if bx == sx and by == sy:
            break
        # print("bx, by", bx, by)
        before = parent[bx][by][0]
        # print("before", before)
        bx,by = before
        trace.append((bx, by))

    trace.reverse()
    # print("trace", trace)

    before_x, before_y = trace[1][0], trace[1][1]
    return before_x, before_y


# 1.
# 편의점을 향해서 1칸 이동, 최단거리 상좌우하 순서
def move():
    for i in range(1, m + 1):
        man = mans[i]
        if man.onboard == False:
            continue

        cx, cy = man.r, man.c
        ex, ey = man.destin_r, man.destin_c

        nx, ny = bfs_return_next(cx, cy, ex, ey)
        # print("move", nx,ny)
        man.r = nx
        man.c = ny


# 2.
# 편의점에 도착하면 편의점에서 정지.
# 이때부터 편의점이 있는 칸을 지날 수 없게된다.
# 하지만 격자에 있는 모든사람이 이동한 뒤에 해당 칸을 지나지 못하게 된다??
def arrive():
    # 모든 사람이 이동하고 난뒤에 편의점을 이동 불가능하게 막는 거기 때문에
    # move() 함수가 실행되고 난후 , arrive() 실행해서 확인하는 방향으로 하면 될듯???????
    for i in range(1, m + 1):
        man = mans[i]
        if man.onboard == False:
            continue

        if man.r == man.destin_r and man.c == man.destin_c:
            man.onboard = False
            arr[man.r][man.c] = -(10 + man.id)

    pass


# 3.
# t 분일떄, t<=m 이면, t번 사람은 편의점과 가장가까운 베이스캠프에 들어간다.
# 베이스캠프가 여러개면 행열 이 작은 곳으로 들어간다.
# 이동하는데 시간 안걸림
# 이때부터 베이스캠프 칸을 지날수없게된다.
# t번 사람이 편의점을 향해 움직이기 시작했더라도
# 해당 베이스 캠프는 앞으로 절대 지나갈 수 없음에 유의합니다??
def basecamp(t):
    if t > m:
        return

    arrive_x, arrive_y = 0, 0
    # 목적지 편의점의 좌표를 일단구한다
    for i in range(1, n + 1):
        for j in range(1, n + 1):
            if arr[i][j] == 10 + t:
                arrive_x, arrive_y = i, j
                break

    # 편의점을 중심으로 bfs를 하고 최단거리 편의점을 구한다.
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

    # vis 기반으로 거리를 계산해야한다.
    # 베이스캠프를 전수조사해서 vis 값이 가장 작은 값을 찾는다. 같으면 행,열 작은곳으로'
    # 생각해보니 bfs도 필요없는듯?

    # for i in range(1,n+1):
    #     for j in range(1,n+1):
    #         print(vis[i][j], end = "\t")
    #     print()
    # print()
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

    # 베이스캠프로 이동시킨다
    man = mans[t]
    man.r = mn_x
    man.c = mn_y
    man.destin_r = arrive_x
    man.destin_c = arrive_y
    man.onboard = True

    # 이제 그 칸은 못이동한다. move() 이후에 베이스캠프 이동이 발생하므로 못이동시키게하면 된다.
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

# print(pyun)
# show_arr()
ans = 0

def test():
    for time in range(1, 100000):
        print("################################")
        print(time)

        if is_everyone_arrive() and time > 1 == True:
            ans = time
            break
        # print("move")
        move()
        # print("arrive")
        arrive()
        # print("base")
        basecamp(time)

        show_arr()
        show_man()
        print("################################")
        input()
    print(ans - 1)


def solve():
    for time in range(1, 100000):
        if is_everyone_arrive() and time > 1 == True:
            ans = time
            break
        # print("move")
        move()
        # print("arrive")
        arrive()
        # print("base")
        basecamp(time)

    print(ans - 1)



# test()
solve()


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


