n, m, k = map(int, input().split())
total_movement = 0


def print_arr(arr):
    for i in range(1, n + 1):
        for j in range(1, n + 1):
            print(arr[i][j], end="\t")
        print()
    print()


arr = [[0] * (n + 1) for _ in range(n + 1)]
exit_r, exit_c = 0, 0
for i in range(1, n + 1):
    row = [0] + list(map(int, input().split()))
    arr[i] = row

for i in range(m):
    r, c = map(int, input().split())
    # print("input",r,c)
    arr[r][c] += -1

exit_r, exit_c = map(int, input().split())
arr[exit_r][exit_c] = 11


# print_arr(arr)


def everyone_escape():
    for i in range(1, n + 1):
        for j in range(1, n + 1):
            if arr[i][j] < 0:
                return False
    return True


def move():
    """
    # 상하 우선
    # 출구와 더가까워져야만 한다
    움직일 수 없으면 안 움직인다
    """
    global total_movement
    er = exit_r
    ec = exit_c

    narr = [[0] * (n + 1) for _ in range(n + 1)]
    for i in range(1, n + 1):
        for j in range(1, n + 1):
            if arr[i][j] < 0:
                narr[i][j] = 0
                continue
            narr[i][j] = arr[i][j]

    dr = [1, -1, 0, 0]
    dc = [0, 0, 1, -1]
    for i in range(1, n + 1):
        for j in range(1, n + 1):
            # 0보다 작으면 사람이 있는거
            moved = False
            if (arr[i][j] < 0):
                # 출구까지의 거리
                dis = abs(er - i) + abs(ec - j)
                # 방향 찾기
                for dir in range(4):
                    nx = i + dr[dir]
                    ny = j + dc[dir]
                    if nx < 1 or nx > n or ny < 1 or ny > n:
                        continue
                    if arr[nx][ny] > 0 and arr[nx][ny] != 11:
                        continue

                    ndis = abs(er - nx) + abs(ec - ny)

                    # 이동이 발생하는 곳!!!
                    if ndis < dis:
                        # 상하좌우 순서대로 다음 좌표를 확인한다. 만약 거리가 작아진다면 그 방향으로 이동한다.
                        # 만약 이동한 곳이 출구라면 그냥 없애면 된다.
                        # print(nx, ny)
                        if arr[nx][ny] == 11:
                            total_movement += arr[i][j] * (-1)
                            # narr[i][j] = 0
                            arr[i][j] = 0
                            moved = True

                        # 출구가 아닌곳으로 이동
                        else:
                            total_movement += arr[i][j] * (-1)
                            # print("arr ij", arr[i][j])
                            narr[nx][ny] += arr[i][j]
                            # narr[i][j] = 0
                            arr[i][j] = 0
                            moved = True

                        break

                # 못 움직이는 경우
                if moved == False:
                    narr[i][j] += arr[i][j]

    for i in range(1, n + 1):
        for j in range(1, n + 1):
            arr[i][j] = narr[i][j]

    # print_arr(arr)
    # print_arr(narr)


def find_square():
    for s in range(2, n + 1):
        # 1: 사람이 있는지, 2: 출구가 있는지
        for i in range(1, n + 1):
            for j in range(1, n + 1):
                flag1 = False
                flag2 = False
                for x in range(s):
                    for y in range(s):
                        if i + x > n or j + y > n:
                            continue
                        if arr[i + x][j + y] < 0:
                            flag1 = True
                        if arr[i + x][j + y] == 11:
                            flag2 = True
                if flag1 == True and flag2 == True:
                    square_r, square_c = i, j
                    return square_r, square_c, s


def spin():
    # print("spin")
    """
    한 명 이상의 참가자와 출구를 포함한 가장 작은 정사각형을 잡습니다.
    가장 작은 크기를 갖는 정사각형이 2개 이상이라면, 좌상단 r 좌표가 작은 것이 우선되고, 그래도 같으면 c 좌표가 작은 것이 우선됩니다.
    선택된 정사각형은 시계방향으로 90도 회전하며, 회전된 벽은 내구도가 1씩 깎입니다.
    99999 9999999 9999999
    99999 0 0 0
    99999 0 0 0
    99999 0 0 0
    [-9999999, 1, 2, 4]
    """
    global exit_r, exit_c

    er, ec = exit_r, exit_c

    # i,j 순회를 돌면서 거리를 구한다. 그리고 거리가 최소인 값을 찾는다
    # 거리가 최소인것이 가장 작은 정사각형이다.
    min = 21e8
    min_r, min_c = 0, 0
    min_len = 0

    square_r, square_c, size = -1, -1, -1
    square_r, square_c, size = find_square()
    # print("square r,c,s", square_r, square_c, size)

    # 선택된 정사각형은 시계방향으로 90도 회전하며, 회전된 벽은 내구도가 1씩 깎입니다.

    # 미리 내구도를 1씩 감소시켜준다
    for i in range(square_r, square_r + size):
        for j in range(square_c, square_c + size):
            if arr[i][j] > 0 and arr[i][j] != 11:
                arr[i][j] -= 1

    # 회전시켜준다.
    narr = [[0] * (size) for _ in range(size)]
    narr2 = [[0] * (size) for _ in range(size)]

    for i in range(square_r, square_r + size):
        for j in range(square_c, square_c + size):
            narr[i - square_r][j - square_c] = arr[i][j]

    # print(narr)

    # narr 는 0 에서부터 size-1 까지
    # 시계방향으로 90도 회전해야한다
    for i in range(0, size):
        for j in range(0, size):
            narr2[i][j] = narr[size - j - 1][i]

    # print("narr2", narr2)

    for i in range(square_r, square_r + size):
        for j in range(square_c, square_c + size):
            arr[i][j] = narr2[i - square_r][j - square_c]

    for i in range(1, n + 1):
        for j in range(1, n + 1):
            if arr[i][j] == 11:
                exit_r = i
                exit_c = j


def solve():
    for time in range(0, k):
        # print("###############")
        # print("time ", time)
        temp = everyone_escape()
        if temp == True:
            break
        move()
        # print_arr(arr)
        temp = everyone_escape()
        if temp == True:
            break
        spin()
        # print_arr(arr)

        # print("movement", total_movement)
        # print("###############")
        # input()


# print_arr(arr)
solve()
print(total_movement)
print(exit_r, exit_c)
