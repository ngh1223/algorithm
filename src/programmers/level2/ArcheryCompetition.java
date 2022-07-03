package programmers.level2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ArcheryCompetition {
    private final int MAX_SCORE = 10;
    private Map<Integer, int[]> scoreMap = new HashMap<>();

    public int[] solution(int n, int[] info) {
        int[] answer;

        int lionScore = 0;
        int targetIndex = info.length-1;

        int peachScore = 0;
        for (int i=0; i<targetIndex; i++) {
            if(info[i] > 0) {
                peachScore += (MAX_SCORE - i);
            }
        }

        for (int i=targetIndex; i>=0; i--) {
            int[] lionTarget = new int[info.length];
            dfs(lionScore, peachScore, i, n, info, lionTarget);
        }

        int maxKey = scoreMap.keySet().stream().max(Comparator.comparing(x -> x)).orElse(-9999);

        if (maxKey > 0) {
            answer = scoreMap.get(maxKey);
            int sum = Arrays.stream(answer).sum();
            if(sum < n) {
                answer[answer.length-1] = n-sum;
            }
        } else {
            answer = new int[]{-1};
        }

        return answer;
    }

    private void dfs(int lionScore, int peachScore, int targetIndex, int n, int[] info, int[] lionTarget) {
        int peachHitCount = info[targetIndex];

        if(n > peachHitCount) {
            n -= (peachHitCount + 1);
            lionTarget[targetIndex] = peachHitCount + 1;
            lionScore += (MAX_SCORE - targetIndex);
            if(peachHitCount > 0) {
                peachScore -= (MAX_SCORE - targetIndex);
            }
        }

        for(int i = targetIndex-1; i>=0; i--) {
            int[] copyLionTarget = lionTarget.clone();
            dfs(lionScore, peachScore, i, n, info, copyLionTarget);
        }

        if(targetIndex == 0) {
            int different = lionScore - peachScore;
            if(scoreMap.containsKey(different)) {
                int[] beforeTarget = scoreMap.get(different);

                for(int i = beforeTarget.length-1; i>=0; i--) {
                    if(beforeTarget[i] < lionTarget[i]) {
                        scoreMap.put(different, lionTarget);
                        break;
                    } else if (beforeTarget[i] > lionTarget[i]) {
                        break;
                    }
                }
            } else {
                scoreMap.put(different, lionTarget);
            }
        }
    }
}
