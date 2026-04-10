n, m = map(int, input().split())
arr = [[0 for _ in range(n + 1)] for _ in range(n + 1)]
ans = []


class Box:
    def __init__(self, id, mn_r, mn_c, mx_r, mx_c, inside):
        self.mn_r = mn_r
        self.mn_c = mn_c
        self.mx_r = mx_r
        self.mx_c = mx_c
        self.inside = inside
        self.id = id


boxes = []


def show_boxes():
    for b in boxes:
        print("box", b.id, b.mn_r, b.mn_c, b.mx_r, b.mx_c, b.inside)


def show_arr():
    for i in range(1, n + 1):
        for j in range(1, n + 1):
            print(arr[i][j], end=" ")
        print()

boxes.append(Box(0,0,0,0,0,False))
for i in range(m):
    k, h, w, c = map(int, input().split())
    boxes.append(Box(k, 0, c, h - 1, c + w - 1, True))


def down(box):
    # 내려갈때는 mn_c, mx_c
    # arr상에서 mn_c, mx_c까지 모두 0이면 내려갈수있는거다
    cur_r = box.mx_r
    mn_r = box.mn_r
    mn_c = box.mn_c
    mx_r = box.mx_r
    mx_c = box.mx_c

    # 일단 현재의 상자를 arr에서 지워준다.
    # 그리고 나서 수정된 좌표로 arr에 그려준다. 다시 마지막에
    for i in range(mn_r, mx_r + 1):
        for j in range(mn_c, mx_c + 1):
            arr[i][j] = 0

    # 바닥 내려갈수있을떄까지 내려간다
    cnt = 0
    while True:
        flag = True
        for i in range(mn_c, mx_c + 1):
            if cur_r + 1 > n:
                flag = False
                break
            if arr[cur_r + 1][i] != 0:
                flag = False
                break

        # 내려갈수있는 경우
        if flag == True:
            cnt += 1
            cur_r += 1
        # 못내려가는경우
        else:
            break

    box.mx_r += cnt
    box.mn_r += cnt

    # 이동한 상자를 arr에 표시해준다.
    for i in range(box.mn_r, box.mx_r + 1):
        for j in range(box.mn_c, box.mx_c + 1):
            arr[i][j] = box.id


# 얘가 뺴야할거를 찾아야한다 왼쪽으로
# 순서는 id가 작은거 부터, 여러개 있으면
def left():
    # 일단 box를 다순회해요.
    # box의 mn_r, mx_r 를 확인하고
    # j 를 box의 mn_c에서부터 왼쪾으로 쫙 확인을해서
    # 만약 하나라도 0이 아닌게 있으면 못나가는거다
    # 만약 모두 0이면 그냥 break
    for i in range(1,m+1):

        box = boxes[i]
        # box가 arr안에있는지 확인해야한다
        if box.inside == False:
            continue
        flag = True
        mn_r = box.mn_r
        mx_r = box.mx_r
        mn_c = box.mn_c
        mx_c = box.mx_c

        for j in range(mn_c):
            for k in range(mn_r, mx_r + 1):
                if arr[k][j] != 0:
                    flag = False
                    break

        # 왼쪽으로 나갈수있다. 내보내야한다
        # 빠져나가는거 ans에 저장한다
        if flag == True:
            ans.append(box.id)
            box.inside = False
            for i in range(mn_r, mx_r + 1):
                for j in range(mn_c, mx_c + 1):
                    arr[i][j] = 0
            break


def all_down():
    # 왼쪽으로 하나뻇으니까. 내려갈수있는게 있는지 다 한번씩 확인해야한다
    # 맨 밑에 꺼 부터 확인해야한다.
    for i in range(n, 0, -1):
        for j in range(1, n + 1):
            if arr[i][j] != 0:
                # arr[i][j] 의 id를 가진 박스가 내려갈수있는지 확인해본다.
                bid = arr[i][j]
                print("bid",bid)
                show_boxes()
                box = boxes[bid]

                down(box)


def solve():
    # 압력받은 박스들을 다 바닥으로 내려준다
    for i in range(1, m+1):
        b = boxes[i]
        down(b)

    # 다 내렸으면 일단, 이제 왼쪽 뺴고, 내리고 ,오른쪾 뺴고 내리고 무한 반복 지워질때까지
    left()
    all_down()

    # while True:
    #     if is_empty():
    #         break
    #     left()
    #
    #     all_down()
    #
    #     right()
    #
    #     all_down()

    show_arr()


solve()

# show_boxes()
# print(boxes)
