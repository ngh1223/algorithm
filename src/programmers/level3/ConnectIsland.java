package programmers.level3;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 문제 주소: https://school.programmers.co.kr/learn/courses/30/lessons/42861
 */
public class ConnectIsland {
    public int solution(int n, int[][] costs) {
        int answer = 0;
        Node[] nodes = new Node[n];

        for (int i=0; i<n; i++) {
            nodes[i] = new Node();
        }

        Arrays.sort(costs, Comparator.comparingInt(o -> o[2]));

        for (int[] cost : costs) {
            int startNode = cost[0];
            int endNode = cost[1];
            int cst = cost[2];
            boolean[] visited = new boolean[n];

            if (!isCircle(nodes, visited, startNode, endNode)) {
                nodes[startNode].addNode(endNode);
                nodes[endNode].addNode(startNode);
                answer+= cst;
            }
        }

        return answer;
    }

    private boolean isCircle(Node[] nodes, boolean[] visited, int startNode, int endNode) {
        dfs(nodes, visited, startNode);

        return visited[endNode];
    }

    private void dfs(Node[] nodes, boolean[] visited, int nodeId) {
        if (visited[nodeId]) {
            return;
        }

        visited[nodeId] = true;
        List<Integer> linkedNodes = nodes[nodeId].getLinkedNodes();

        for (int linkedNode: linkedNodes) {
            dfs(nodes, visited, linkedNode);
        }
    }

    static class Node {
        private final List<Integer> linkedNodes = new ArrayList<>();

        public void addNode(int nodeId) {
            linkedNodes.add(nodeId);
        }

        public List<Integer> getLinkedNodes() {
            return linkedNodes;
        }
    }
}
