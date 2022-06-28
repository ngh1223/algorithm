package programmers.level2;

import java.util.HashMap;
import java.util.Map;

/**
 * 문제 주소: https://programmers.co.kr/learn/courses/30/lessons/92341
 */
public class ParkingFeeCalculation {
    public int[] solution(int[] fees, String[] records) {
        int[] answer;
        Map<String, Integer> useMinMap = new HashMap<>();

        int defaultTime = fees[0];
        int defaultCharge = fees[1];
        double unitTime = fees[2];
        int unitCharge = fees[3];

        Map<String, String> inTimeMap = new HashMap<>();

        for (String record: records) {
            String[] splitRecord = record.split(" ");
            if("IN".equals(splitRecord[2])) {
                inTimeMap.put(splitRecord[1], splitRecord[0]);
            } else {
                String outCarNumber = splitRecord[1];

                String inTime = inTimeMap.get(outCarNumber);
                int inMin = stringTimeToInt(inTime);

                String outTime = splitRecord[0];
                int outMin = stringTimeToInt(outTime);

                int useMin = outMin - inMin;

                useMinMap.put(outCarNumber, useMinMap.getOrDefault(outCarNumber, 0) + useMin);
                inTimeMap.remove(outCarNumber);
            }
        }

        for(String inCarNumber: inTimeMap.keySet()) {
            int inMin = stringTimeToInt(inTimeMap.get(inCarNumber));
            int outMin = 23 * 60 + 59;
            int useMin = outMin - inMin;

            useMinMap.put(inCarNumber, useMinMap.getOrDefault(inCarNumber, 0) + useMin);
        }

        answer = useMinMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .mapToInt(v -> v.getValue() > defaultTime ? defaultCharge + (int)Math.ceil((v.getValue() - defaultTime) / unitTime) * unitCharge : defaultCharge)
                .toArray();

        return answer;
    }

    private int stringTimeToInt(String time) {
        String[] splitInTime = time.split(":");
        return Integer.parseInt(splitInTime[0]) * 60 + Integer.parseInt(splitInTime[1]);
    }
}
