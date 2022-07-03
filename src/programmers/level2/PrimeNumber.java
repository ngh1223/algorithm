package programmers.level2;

// https://programmers.co.kr/learn/courses/30/lessons/92335
public class PrimeNumber {
    public int solution(int n, int k) {
        int answer = 0;
        String value = "";


        while (n>0) {
            int remains = n % k;
            n = n / k;
            value = remains + value;
        }

        String[] values = value.split("0");

        for (String val : values) {
            if(!"".equals(val)) {
                int number = Integer.parseInt(val);
                if(isPrimeNumber(number)) {
                    answer++;
                }
            }
        }

        return answer;
    }

    private boolean isPrimeNumber(int number) {
        if(number < 2) {
            return false;
        }

        for (int i=2; i*i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }
}
