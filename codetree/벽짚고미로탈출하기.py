import sys

N = int(input())
x, y = map(int, input().split())
arr = [[0] * (N + 1) for _ in range(N + 1)]
for i in range(1, N + 1):
    row = "0" + input()
    arr[i] = row

path = []


# print(arr)
class Man:
    def __init__(self, r, c, dir):
        self.r = r
        self.c = c
        self.dir = dir


"""
1. 보고 있는 방향으로 못움직이면 반시계90도 회전
2. 보고 있는 방향으로 움직일 수 있으면 
    이동하고 격자밖이면 탈출
    이동했는데, 오른쪽에 벽이 있으면 그냥 움직이면된다
    이동했는데, 벽이 없으면 이동후 시계로 90도 회전후 한칸더 이동

이걸 반복
"""

dx = [0, -1, 0, 1]
dy = [1, 0, -1, 0]

man = Man(x, y, 0)


def printMan():
    print(man.r, man.c, man.dir)
    print()


def printarr():
    for i in range(1, N + 1):
        for j in range(1, N + 1):
            if i == man.r and j == man.c:
                print('X', end=" ")
            else:
                print(arr[i][j], end=" ")
        print()
    print()


def checkEscape():
    mx, my = man.r, man.c
    if mx < 1 or mx > N or my < 1 or my > N:
        return 1


def move(first):
    mx, my, mdir = man.r, man.c, man.dir
    nx, ny, ndir = mx, my, mdir
    impossible = 1
    for i in range(0, 4):
        ndir = (ndir + i) % 4

        nx, ny = mx + dx[ndir], my + dy[ndir]
        # print("move nx ny ", nx, ny)

        if nx < 1 or nx > N or ny < 1 or ny > N:
            man.r = nx
            man.c = ny
            impossible = 0
            return

        if arr[nx][ny] == '.':
            impossible = 0
            break

    if impossible == 1:
        return -1
    # nx, ny 이동가능한 곳 찾음
    # 격자 밖으로 나갔는지 확인

    # 이제, 직진해서 오른쪽에 벽이 있는지 확인해본다.
    if ndir == 0:
        right_dir = 3
    else:
        right_dir = ndir - 1

    rx, ry = nx + dx[right_dir], ny + dy[right_dir]

    if arr[rx][ry] == '#':
        # 벽이 있으면 그냥 이동시키면 된다
        man.r, man.c = nx, ny
    else:
        # 벽이 없으면 다시 돌려서 이동시켜야함
        ndir = right_dir
        # nx = nx + dx[right_dir]
        # ny = ny + dy[right_dir]

    # 이제 이동한 것들은 구조체에 반영해준다
    man.r = nx
    man.c = ny
    man.dir = ndir


# 못찾은 경우도 처리해함 아마 리턴 -1 해서 못간다고 출력해야할듯


def solve():
    time = 0
    temp = 0
    first = 1
    while (1):
        if checkEscape() == 1:
            break

        time += 1
        temp = move(first)
        first = 0
        # 시작점에 다시 돌아올경우 무한루프라서 -1ss
        path.append([man.r, man.c, man.dir])

        tr = path[0][0]
        tc = path[0][1]
        tdir = path[0][2]
        flag = 0
        for i in range(1, len(path)):
            if tr == path[i][0] and tc == path[i][1] and tdir == path[i][2]:
                flag = 1
                break
        if flag == 1:
            temp = -1
            break
        # printarr()
        # printMan()
        # input()

    if temp == -1:
        print(-1)
    else:
        print(time)


solve()

"""
5
1 3
.....
#.#..
.....
.....
.....

6
3 3
######
#....#
##...#
#.##.#
#....#
######
"""

