n = int(input())
w = int(input())

work = []
work.append((-1, -1))
for i in range(w):
    r, c = map(int, input().split())
    work.append((r, c))

# print(work)

INF = 21e8
dp = [[INF for _ in range(w + 1)] for _ in range(w + 1)]
parent = [[[] for _ in range(w + 1)] for _ in range(w + 1)]
dp[0][0] = 0


def show_dp():
    print("dp")
    for i in range(w + 1):
        for j in range(w + 1):
            if dp[i][j] == INF:
                print("X", end="\t")
            else:
                print(dp[i][j], end="\t")
        print()


def show_parent():
    print("parent")
    for i in range(w + 1):
        for j in range(w + 1):
            print(parent[i][j], end="\t")
        print()


c1_r, c1_c = 0, 0
c2_r, c2_c = n, n

for i in range(0, w):
    for j in range(0, w):
        if i == j and i != 0:
            continue
        nex = max(i, j) + 1
        if nex > w:
            continue
        # i,j 에서 next, j 로
        # 1번차가 움직이는 거다.
        # i번쨰 사건에서 j 번쨰 사건으로 이동한다.

        if i == 0:
            c1_r = 1
            c1_c = 1
        else:
            c1_r = work[i][0]
            c1_c = work[i][1]

        n1_r = work[nex][0]
        n1_c = work[nex][1]

        temp = abs(c1_r - n1_r) + abs(c1_c - n1_c) + dp[i][j]
        # dp[nex][j] = min(dp[nex][j], temp)
        if dp[nex][j] > temp:
            dp[nex][j] = temp
            parent[nex][j] = (i, j)

        # 2번차 이동하는 부분
        if j == 0:
            c2_r = n
            c2_c = n
        else:
            c2_r = work[j][0]
            c2_c = work[j][1]

        n1_r = work[nex][0]
        n1_c = work[nex][1]

        temp = abs(c2_r - n1_r) + abs(c2_c - n1_c) + dp[i][j]
        # dp[i][nex] = min(dp[i][nex], temp)
        if dp[i][nex] > temp:
            dp[i][nex] = temp
            parent[i][nex] = (i, j)

        # show_dp()
        # input()

# show_dp()
# show_parent()
mn = INF
destin_x, destin_y = -1, -1
for i in range(w + 1):
    # mn = min(mn, dp[w][i])
    if mn > dp[w][i]:
        mn = dp[w][i]
        destin_x, destin_y = w, i

for i in range(w + 1):
    # mn = min(mn, dp[w][i])
    if mn > dp[w][i]:
        mn = dp[w][i]
        destin_x, destin_y = w, i

route = []
route.append((destin_x, destin_y))
nx, ny = destin_x, destin_y
while True:
    if nx == 0 and ny == 0:
        break
    nx, ny = parent[nx][ny]
    route.append((nx, ny))

route.reverse()
# print(route)
ans_route = []

for i in range(1, len(route)):
    cx, cy = route[i - 1]
    nx, ny = route[i]

    if cx < nx:
        ans_route.append(1)
    elif cy < ny:
        ans_route.append(2)

# print(ans_route)
print(mn)
for i in ans_route:
    print(i)
# print(destin_x, destin_y)
# show_dp()
