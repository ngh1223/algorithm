package programmers.level1;

import java.util.HashMap;
import java.util.Map;

/**
 * 문제 주소: https://programmers.co.kr/learn/courses/30/lessons/92334
 */
public class GetReportResults {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        Map<String, Integer> idOrderMap = new HashMap<>();

        int index = 0;
        for(String id: id_list) {
            idOrderMap.put(id, index++);
        }

        int[][] reportCountArray = new int[index][index];

        for(String content: report) {
            String[] splitContent = content.split(" ");
            int reporterOrder = idOrderMap.get(splitContent[0]);
            int reportedOrder = idOrderMap.get(splitContent[1]);

            reportCountArray[reporterOrder][reportedOrder] = 1;
        }

        int[] reportedCountArray = new int[index];

        for (int[] reporterReportedArray : reportCountArray) {
            for (int j = 0; j < reporterReportedArray.length; j++) {
                reportedCountArray[j] += reporterReportedArray[j];
            }
        }

        for(int i=0; i<reportCountArray.length; i++) {
            int[] reporterReportedArray = reportCountArray[i];
            for(int j=0; j<reporterReportedArray.length; j++) {
                if(reportedCountArray[j] >= k) {
                    answer[i] += reporterReportedArray[j];
                }
            }
        }

        return answer;
    }
}
