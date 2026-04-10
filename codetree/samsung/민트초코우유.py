import sys
from collections import deque

input = sys.stdin.readline


def bitcount(x):
    cnt = 0
    while x:
        cnt += x & 1
        x >>= 1
    return cnt


N, T = map(int, input().split())
food = []
for _ in range(N):
    s = input().strip()
    row = []
    for ch in s:
        if ch == "T":
            row.append(1)
        elif ch == "C":
            row.append(2)
        else:
            row.append(4)
    food.append(row)
belief = [list(map(int, input().split())) for _ in range(N)]
dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]


def morning():
    global i, j, visited, reps
    for i in range(N):
        for j in range(N):
            belief[i][j] += 1
    visited = [[False] * N for _ in range(N)]
    reps = []


def lunch():
    global i, j, r, c
    for i in range(N):
        for j in range(N):
            if visited[i][j]:
                continue
            q = deque()
            q.append((i, j))
            visited[i][j] = True
            members = []
            target_food = food[i][j]
            leader_r, leader_c = i, j
            best_belief = belief[i][j]

            while q:
                r, c = q.popleft()
                members.append((r, c))

                if belief[r][c] > best_belief:
                    best_belief = belief[r][c]
                    leader_r, leader_c = r, c
                elif belief[r][c] == best_belief:
                    if r < leader_r or (r == leader_r and c < leader_c):
                        leader_r, leader_c = r, c

                for d in range(4):
                    nr = r + dr[d]
                    nc = c + dc[d]
                    if nr < 0 or nr >= N or nc < 0 or nc >= N:
                        continue
                    if visited[nr][nc]:
                        continue
                    if food[nr][nc] != target_food:
                        continue
                    visited[nr][nc] = True
                    q.append((nr, nc))

            for r, c in members:
                if r == leader_r and c == leader_c:
                    continue
                belief[r][c] -= 1
                belief[leader_r][leader_c] += 1

            reps.append((leader_r, leader_c, food[leader_r][leader_c], belief[leader_r][leader_c]))


def dinner():
    global r, c, _
    defended = [[False] * N for _ in range(N)]
    single, dual, triple = [], [], []
    for r, c, f, b in reps:
        cnt = bitcount(f)
        if cnt == 1:
            single.append((r, c, f, b))
        elif cnt == 2:
            dual.append((r, c, f, b))
        else:
            triple.append((r, c, f, b))
    single.sort(key=lambda x: (-x[3], x[0], x[1]))
    dual.sort(key=lambda x: (-x[3], x[0], x[1]))
    triple.sort(key=lambda x: (-x[3], x[0], x[1]))
    for group in [single, dual, triple]:
        for sr, sc, rep_food, _ in group:
            if defended[sr][sc]:
                continue
            B = belief[sr][sc]
            direction = B % 4
            x = B - 1
            belief[sr][sc] = 1

            r, c = sr, sc
            while x > 0:
                r += dr[direction]
                c += dc[direction]

                if r < 0 or r >= N or c < 0 or c >= N:
                    break
                if food[r][c] == rep_food:
                    continue

                y = belief[r][c]
                defended[r][c] = True

                if x > y:
                    food[r][c] = rep_food
                    x -= (y + 1)
                    belief[r][c] += 1
                else:
                    food[r][c] |= rep_food
                    belief[r][c] += x
                    x = 0


for _ in range(T):
    # morning
    morning()
    # lunch
    lunch()
    # dinner
    dinner()

    ans = [0] * 8
    for i in range(N):
        for j in range(N):
            ans[food[i][j]] += belief[i][j]
    print(ans[7], ans[3], ans[5], ans[6], ans[4], ans[2], ans[1])
