package programmers.level3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 문제 주소: https://programmers.co.kr/learn/courses/30/lessons/92343
 */
public class SheepAndWolf {

    static int max = 0;
    static Map<Integer, List<Integer>> linkMap = new HashMap<>();

    public int solution(int[] info, int[][] edges) {
        int nodeCount = info.length;

        for(int i = 0; i <= nodeCount; i++) {
            linkMap.put(i, new ArrayList<>());
        }

        for(int[] link: edges) {
            linkMap.get(link[0]).add(link[1]);
            linkMap.get(link[1]).add(link[0]);
        }

        boolean[][][] visit = new boolean[nodeCount][nodeCount+1][nodeCount+1];
        dfs(0, 0, 0, info, visit);

        return max;
    }

    // DFS
    private void dfs(int node, int sheep, int wolf, int[] info, boolean[][][] visit) {
        if(info[node] == 0) {
            sheep++;
        } else if(info[node] == 1){
            wolf++;
        }

        // 늑대가 양 이상이거나, 같은 늑대수, 양수를 가지고 노드를 방문한 적이 있으면 탈출
        if(sheep <= wolf || visit[node][sheep][wolf]) {
            return;
        }

        if(max < sheep) {
            max = sheep;
        }


        List<Integer> nextNodeList = linkMap.get(node);

        for (int nextNode: nextNodeList) {
            visit[node][sheep][wolf] = true;
            int beforeInfo = info[node];
            info[node] = -1;

            dfs(nextNode, sheep, wolf, info, visit);

            visit[node][sheep][wolf] = false;
            info[node] = beforeInfo;
        }
    }

}
