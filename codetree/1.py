from collections import deque
import heapq

def dijk(N, road):
    dis = [0 for _ in range(N + 1)]
    edges = [[] for _ in range(N + 1)]

    for r in road:
        a, d, b = r[0], r[2], r[1]
        edges[a].append((d, b))

    print(edges)

    pq = []
    dis[1] = 0
    heapq.heappush(pq, (dis[1], 1))
    while pq:
        cdis, cnode = heapq.heappop(pq)

        if dis[cnode] <= cdis:
            continue


def solution(N, road, K):
    answer = 0

    # [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
    print('Hello Python')

    dijk(N, road)


    return answer

N= 5
road = [[1,2,1],[2,3,3],[5,2,2],[1,4,2],[5,3,1],[5,4,2]]
k=3
solution(N, road, k)