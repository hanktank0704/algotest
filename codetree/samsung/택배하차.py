import sys
from collections import deque

input = sys.stdin.readline

n, m = map(int, input().split())

arr = [[0 for _ in range(n + 1)] for _ in range(n + 1)]


def print_arr():
    print("arr")
    for i in range(1, n + 1):
        for j in range(1, n + 1):
            print(arr[i][j], end=" ")
        print()


def down(k, h, w, c):
    # k 번호, h 높이, w 너비, c 시작 좌표
    # arr[n][c] ~ arr[n][c+w-1] 까지 확인해본다
    # 만약 모두 0이면 내려갈수있는거고 아니면 n-1을 확인해본다

    print("down", k, h, w, c)
    # 높이 뺴먹음
    for i in range(n, 0, -1):
        print("i", i)
        flag = 1
        for j in range(c, c + w):
            if arr[i][j] != 0:
                flag = 0
                break

        print("flag", flag)
        if flag == 1:
            print("write")
            for up in range(h):
                # c+w-1 이 0보다 작은 경우가 존재하는 지 확인 !!!!!!!!
                for j in range(c, c + w):
                    arr[i - up][j] = k
                    print("write", i - up, j)
            break


def find_minmax_rc(r, c):
    # visit 어딘가에 문제점이 있다!!!!!!!!!!
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]

    max_r, max_c, min_r, min_c = r, c, r, c

    vis = [[0 for _ in range(n + 1)] for _ in range(n + 1)]
    q = deque()
    q.append([r, c])
    vis[r][c] = 1
    while q:
        print("queue",q)
        print()
        cur = q.popleft()
        cr, cc = cur[0], cur[1]
        max_r = max(max_r, cr)
        max_c = max(max_c, cc)

        min_r = min(min_r, cr)
        min_c = min(min_c, cc)
        for i in range(0, 4):
            nr, nc = cr + dx[i], cc + dy[i]
            if nr < 1 or nr > n or nc < 1 or nc > n:
                continue
            if vis[nr][nc] == 1:
                continue
            if arr[nr][nc] != arr[cr][cc]:
                continue
            vis[nr][nc] = 1
            q.append([nr, nc])

    return max_r, max_c, min_r, min_c


def left():
    # 왼쪽 콜롬부터 확인해야한다
    # 왼쪽 콜롬부터 확인해서 먼저 닿는걸 bfs로 최대 최소 좌표를 구한다
    for i in range(n, 0, -1):
        for j in range(1, n + 1):
            if arr[i][j] != 0:
                min_r, max_r = 0, 0
                min_c, max_c = 0, 0
                min_r, min_c, max_r, max_c = find_minmax_rc(i, j)

                break
    pass


def right():
    pass


print(n, m)
for _ in range(m):
    k, h, w, c = map(int, input().split())
    down(k, h, w, c)
    print_arr()

ans = []
while (1):
    # if is_empty() == True:
    #     break
    temp = left()
    ans.append(temp)
    temp = right()
    ans.append(temp)

print(ans)

# 택배 하차
# 1번을 어떻게 찾아야하나
# 시작점, 세로의 길이를 알아야한다.
