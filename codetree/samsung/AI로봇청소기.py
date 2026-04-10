"""
1. 청소기 이동
청소기 class로 정의, id, r, c, dir
bfs 를 돌려서 vis를 만든다
arr 가 0 이상인 ( 먼지가 존재하는 ) 칸 중에서 vis가 최소인
좌표를 찾는다.

거리 mn 값을 선언하고
arr>0 인 vis를 mn과 비교한다. 만약 현재의 vis가 더 작으면
업데이트하고 좌표도 없데이트 한다.

2. 청소
4가지 청소가능 먼지량을 계싼하고
최대값의 방향을 찾는다.

4. 먼지확산은
새로운 arr를 만들어서
기존의 빈칸의 상하좌우를 다확인한다.
그리고 /10해서 다더한값을 새 arr에 넣어준다.
그리고 기존의 arr에 더해주면 된다.

"""

from collections import deque
import sys

input = sys.stdin.readline


class Robot:
    def __init__(self, id, r, c, dir):
        self.id = id
        self.r = r
        self.c = c
        self.dir = dir


robots = []

n, k, l = map(int, input().split())
arr = [[0 for _ in range(n + 1)] for _ in range(n + 1)]
rob_arr = [[0 for _ in range(n + 1)] for _ in range(n + 1)]
for i in range(1, n + 1):
    row = [0] + list(map(int, input().split()))
    arr[i] = row
for i in range(1, k + 1):
    r, c = map(int, input().split())
    robots.append(Robot(i, r, c, 0))


def show_narr(arr):
    print("narr")
    for i in range(1, n + 1):
        for j in range(1, n + 1):
            print(arr[i][j], end="\t")
        print()


def show_arr():
    print("arr")
    for i in range(1, n + 1):
        for j in range(1, n + 1):
            print(arr[i][j], end="\t")
        print()


def show_rob_arr():
    print("rob arr")
    for i in range(1, n + 1):
        for j in range(1, n + 1):
            print(rob_arr[i][j], end="\t")
        print()


def show_robot():
    print("robot")
    for rob in robots:
        print("id: ", rob.id, "(", rob.r, rob.c, ")")


def show_vis(vis):
    print("vis")
    for i in range(1, n + 1):
        for j in range(1, n + 1):
            print(vis[i][j], end="\t")
        print()


# show_arr()
# show_robot()


def move():
    # 청소기의 위치도 arr에 표시를 해줘야한다.
    # 이동하고 이전의 청소기 위치를 지워줘야한다!!!!!!!
    for rob in robots:
        id, r, c = rob.id, rob.r, rob.c
        rob_arr[r][c] = id

    # show_arr()
    # show_rob_arr()

    # bfs로 vis 만들어야한다!
    for rob in robots:
        id, r, c = rob.id, rob.r, rob.c

        vis = [[0 for _ in range(n + 1)] for _ in range(n + 1)]
        dx = [0, 0, 1, -1]
        dy = [1, -1, 0, 0]

        q = deque()

        vis[r][c] = 1
        q.append((r, c))

        while q:
            cr, cc = q.popleft()

            for i in range(4):
                nr = cr + dx[i]
                nc = cc + dy[i]

                if nr < 1 or nr > n or nc < 1 or nc > n:
                    continue
                if rob_arr[nr][nc] > 0:
                    continue
                if arr[nr][nc] < 0:
                    continue
                if vis[nr][nc] != 0:
                    continue
                vis[nr][nc] = vis[cr][cc] + 1
                q.append((nr, nc))

        # show_vis(vis)

        # 이제 vis로 다음 이동 장소를 찾아야한다.
        # vis >0 and arr > 0 인것중 vis 가 최소이면 좌표를 업데이트 해준다.

        mn = 21e8
        # 틀린 부분 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! -1, -1 로 초기화하면 안된다
        # nex_r, nex_c = -1, -1
        nex_r, nex_c = r, c
        for i in range(1, n + 1):
            for j in range(1, n + 1):
                if vis[i][j] > 0 and arr[i][j] > 0:
                    if mn > vis[i][j]:
                        mn = vis[i][j]
                        nex_r, nex_c = i, j

        # print("nex", nex_r, nex_c)

        # nex 로 이동한다!!

        # 기존의 로봇을 arr에서 지운다.
        rob_arr[r][c] = 0
        rob_arr[nex_r][nex_c] = id

        rob.r = nex_r
        rob.c = nex_c

        # show_rob_arr()


def over_20(num):
    if num > 20:
        return 20
    else:
        return num


def clean():
    dx = [0, 1, 0, -1]
    dy = [1, 0, -1, 0]
    for rob in robots:
        id, r, c = rob.id, rob.r, rob.c
        # print("id, r, c", id, r, c)

        mx = -1
        mx_dir = -1

        sum = over_20(arr[r][c])
        # sum = arr[r][c]

        for i in range(4):
            k = (i + 2) % 4
            nr = r + dx[k]
            nc = c + dy[k]

            if nr < 1 or nr > n or nc < 1 or nc > n:
                continue

            # 이거 뺴먹음 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 틀린부분
            if arr[nr][nc] < 0:
                continue

            sum += over_20(arr[nr][nc])
            # sum += arr[nr][nc]

        # print("sum", sum)

        for i in range(4):
            k = (i + 2) % 4
            nr = r + dx[k]
            nc = c + dy[k]


            # if nr < 1 or nr > n or nc < 1 or nc > n:
            #     continue

            if nr < 1 or nr > n or nc < 1 or nc > n:
                x = 0

            # TODO 이것도 빼먹음 !!!!!!!!!!!!!!!!!!!!!!!!!! 틀린부분
            elif arr[nr][nc] < 0:
                x = 0
            else:
                x = arr[nr][nc]

            temp = sum - over_20(x)
            # temp = sum - x

            # print("temp", temp, "i", i)

            if mx < temp:
                mx = temp
                mx_dir = i

        # mx_dir = (mx_dir + 2) % 4

        # print("mx", mx)
        # print("id", id, "mxdir", mx_dir)

        # 방향이 정해졌다.
        # 그방향으로 ㅗ 자 모양으로 지워줘야한다.

        arr[r][c] = max(0, arr[r][c] - 20)

        n_dir = (mx_dir + 2) % 4
        for i in range(4):
            # n dir 는 넣으면 안되는 방향
            if i == n_dir:
                continue

            nr = r + dx[i]
            nc = c + dy[i]

            if nr < 1 or nr > n or nc < 1 or nc > n:
                continue

            if arr[nr][nc] < 0: continue

            arr[nr][nc] = max(0, arr[nr][nc] - 20)

        # show_arr()

    pass


def dust_more():
    # print("dust more")
    # show_arr()
    for i in range(1, n + 1):
        for j in range(1, n + 1):
            if arr[i][j] > 0:
                # print("added")
                # print(arr[i][j])
                arr[i][j] += 5
                # print(arr[i][j])


def dust_expand():
    # 먼지 가 확산
    # 새로운 arr에 먼지를 입력하고
    # 기존의 arr에 더해줘야한다.
    dx = [0, 0, 1, -1]
    dy = [1, -1, 0, 0]

    narr = [[0 for _ in range(n + 1)] for _ in range(n + 1)]

    # show_arr()
    for i in range(1, n + 1):
        for j in range(1, n + 1):
            if arr[i][j] == 0:
                sum = 0
                for k in range(4):
                    nr = i + dx[k]
                    nc = j + dy[k]

                    if nr < 1 or nr > n or nc < 1 or nc > n:
                        continue

                    if arr[nr][nc] < 0:
                        continue

                    sum += arr[nr][nc]

                sum = sum / 10
                narr[i][j] += int(sum)

    # show_narr(narr)

    # 이제 narr를 기존의 arr에 더해준다
    for i in range(1, n + 1):
        for j in range(1, n + 1):
            arr[i][j] += narr[i][j]

    # print("after add")
    # show_arr()

    # pass


def debug():
    for time in range(1, l + 1):
        print("######################")
        print("time", time)
        show_arr()

        # 1.
        move()
        show_rob_arr()

        # 2.
        print("clean")
        clean()
        show_arr()

        # 3.
        print("dust more")
        dust_more()
        show_arr()

        # 4.
        print("dust expand")
        dust_expand()
        show_arr()
        print("######################")
        input()

    # 5. 출력
    sum = 0
    for i in range(1, n + 1):
        for j in range(1, n + 1):
            if arr[i][j] == -1:
                continue
            sum += arr[i][j]

    print(sum)


def solution():
    for time in range(1, l + 1):
        # print("######################")
        # print("time", time)

        # 1.
        move()

        # 2.
        clean()

        # 3.
        dust_more()

        # 4.
        dust_expand()
        # print("######################")

        # 5. 출력
        sum = 0
        for i in range(1, n + 1):
            for j in range(1, n + 1):
                if arr[i][j] == -1:
                    continue
                sum += arr[i][j]

        print(sum)


solution()
# debug()
