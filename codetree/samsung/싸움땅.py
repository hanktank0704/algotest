"""
한 격자에 무기가 여러개 있을 수 있다.
시작위치에는 무기가 없는 곳에 사람이 배치된다? 시작위치에 무기 여부 확인해야!!
플레이어 : 초기 능력치, 좌표, 방향, 총 보유: 처음에는 없음

1-1
방향으로 한칸이동한다
만약 격자 벗어날 예정이면 정반대 방향으로 방향바꾸고 1만큼 이동

2-1
이동한 곳에 사람이 없는경우 총 있는지 확인, 가장 쎈 총만 가지고 나머지 총들은 바닥에 둔다

2-2-1
이동한곳에 사람이 있는 경우, 싸운다.
공격력 총합 큰놈이 이김. 같은면 초기 능력치 큰놈이 이김. 초기능력치는 다다름
이긴 플레이어는 공격력 차이 만큼 점수

2-2-2
진놈은 총 내려놓는다. 방향으로 한칸 이동해야한다.
만약 이동해야하는 칸에 사람이 있는면 오른쪽 90도씩 회전해서 사람이 없을때까지 회전한다.
이동할수있는 공간이 없을 수도 있나??
이동하고 공격력 가장 높은 총 줍는다. 있으면

2-2-3
이긴 놈은 총을 다시 확인한다. 진놈이 떨군게 더 공격력 높을 수도 있다.

위를 반복한다. k 라운드 만큼

라운드 동안 각플레이거 얻은 점수 출력


"""
import sys

input = sys.stdin.readline


def show_gun():
    print("gun")
    for i in range(1, n + 1):
        for j in range(1, n + 1):
            print(gun[i][j], end="\t")
        print()


def show_player():
    print("player")
    for i in range(m):
        p = players[i]
        print("r,c:", p.r, p.c, " dir:", p.dir, "attack:", p.attack, " gun:", p.gun, " score:", p.score, " id:", p.id)
    print()


class Player:
    def __init__(self, r, c, attack, gun, dir, score, id):
        self.r = r
        self.c = c
        self.attack = attack
        self.gun = gun
        self.dir = dir
        self.score = score
        self.id = id


players = []

n, m, k = map(int, input().split())
gun = [[[] for _ in range(n + 1)] for _ in range(n + 1)]
arr = [[0 for _ in range(n + 1)] for _ in range(n + 1)]

for i in range(1, n + 1):
    row = [0] + list(map(int, input().split()))
    for j in range(len(row)):
        if row[j] == 0:
            continue
        gun[i][j].append(row[j])

for i in range(m):
    r, c, dir, attack = map(int, input().split())
    players.append(Player(r, c, attack, 0, dir, 0, i + 1))


# show_gun()
# show_player()


def who_won(a, b):
    # 공격력 합, 같은면 사람 공격력
    a_total = a.attack + a.gun
    b_total = b.attack + b.gun

    if a_total > b_total:
        return a, b
    elif a_total < b_total:
        return b, a
    else:
        if a.attack > b.attack:
            return a, b
        else:
            return b, a


def in_range(r, c):
    if r < 1 or r > n or c < 1 or c > n:
        return False
    return True


def is_there_man(nnr, nnc):
    for p in players:
        if p.r == nnr and p.c == nnc:
            return True
    return False


def gun_exchange(man):
    p = man
    if len(gun[p.r][p.c]) == 0:
        #
        pass
    else:
        # 바닥에 총이 있으면 가지고 있는 총과 비교해서 더쏀총, 플레이어가 총이 없으면 무조건줍고

        gun[p.r][p.c].sort()
        big_gun = gun[p.r][p.c][-1]
        player_gun = p.gun

        # 총이 없는 경우
        if player_gun == 0:
            p.gun = big_gun
            gun[p.r][p.c].pop()

        # 플레이어가 총이 있는 경우, 더 쎈 총을 가져가고 내꺼를 바닥에 둔다
        elif big_gun > player_gun:
            p.gun = big_gun
            gun[p.r][p.c].pop()
            gun[p.r][p.c].append(player_gun)
            gun[p.r][p.c].sort()


def move():
    dr = [-1, 0, 1, 0]
    dc = [0, 1, 0, -1]
    for i, p in enumerate(players):
        cr, cc, cdir, cattack = p.r, p.c, p.dir, p.attack
        nr = cr + dr[cdir]
        nc = cc + dc[cdir]
        # 만약 벽을 넘어가게되는경우, 반대의 방향으로 바꾸고 한칸이동
        if nr < 1 or nr > n or nc < 1 or nc > n:
            cdir = (cdir + 2) % 4
            nr = cr + dr[cdir]
            nc = cc + dc[cdir]

        # 이동한거 반영해준다.
        p.r, p.c, p.dir, p.attack = nr, nc, cdir, cattack

        # 이동한 곳에 사람이 없다면 총 관리
        # 2-1
        isman = 0
        for j, pp in enumerate(players):
            if i == j:
                continue
            # 사람이 있으면 싸워야한다.
            if p.r == pp.r and p.c == pp.c:
                isman = 1
                # fight!
                # print("fight! ", p.id, pp.id)
                # print("p:", p.attack, p.gun)
                # print("pp:", pp.attack, pp.gun)
                # 2-2-1 p 와 pp 가 싸운다
                # 총의 총 공격력 비교, 같으면 플레이어 공격력 비교
                pattack = p.attack + p.gun
                ppattack = pp.attack + pp.gun

                winner, loser = who_won(p, pp)

                # TODO 확인해 볼것 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

                winner.score += (winner.attack + winner.gun) - (loser.attack + loser.gun)

                # 2-2-2
                # 총을 내려놓는다. 한칸 방향대로 이동,
                # 좀불안함!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                if loser.gun != 0:
                    gun[loser.r][loser.c].append(loser.gun)
                else:
                    pass

                loser.gun = 0

                # 만약 이동하려는 칸에 다른 플레이어가 있거나 격자 범위 밖인 경우에는 오른쪽으로 90°씩 회전하여 빈 칸이 보이는 순간 이동합니다.

                while True:
                    nnr = loser.r + dr[loser.dir]
                    nnc = loser.c + dc[loser.dir]
                    # 확인을 해보고 난후, 방향을 바꿔야하는데, 방향을 먼저 바꾸고 확인을 했다.
                    # 그래서 확인한 좌표가 유효함에도 방향이 먼저 바뀌어버려서, 기존의 정답 방향이 바뀌어져 버렸다.

                    # 사람이 있으면 돌려야한다.
                    # 레인지 밖으로 나가면 돌려야한다.
                    # 사람이 없으면서 동시에 레인지 안에 있으면 break해야한다
                    if is_there_man(nnr, nnc) == False and in_range(nnr, nnc) == True:
                        break
                    loser.dir = (loser.dir + 1) % 4

                # 만약 해당 칸에 총이 있다면, 해당 플레이어는 가장 공격력이 높은 총을 획득하고 나머지 총들은 해당 격자에 내려 놓습니다.
                loser.r = nnr
                loser.c = nnc

                gun_exchange(loser)

                # 이긴놈 처리
                # 2-2-3
                # 승리한 칸에서 떨어져있는 총에서 가장 높은거 획득하고 내꺼는 내려놓는다.

                gun_exchange(winner)

            # 사람이 있어서 회전해야함 오른쪾으로 90도

        # 사람이 없는경우 총 찾기
        if isman == 0:
            # 총 있는 지 확인
            gun_exchange(p)


def gun_check():
    pass


def solve():
    # 1-1
    for round in range(k):
        move()
        # show_gun()
        # show_player()
        # input()


solve()
for p in players:
    print(p.score, end=" ")

"""
5 4 1
1 2 0 1 2
1 0 3 3 1
1 3 0 2 3
2 1 2 4 5
0 1 3 2 0
1 3 2 3
2 2 1 5
3 3 2 2
5 1 3 4

3 5 1
0 5 0
0 0 4
5 3 0
1 1 0 2
3 3 2 1
1 3 2 5
2 1 1 3
2 2 1 4

"""
