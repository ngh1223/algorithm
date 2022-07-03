package programmers.level2;

// https://programmers.co.kr/learn/courses/30/lessons/92335
public class PrimeNumber {
    public int solution(int n, int k) {
        int answer = 0;
        StringBuilder value = new StringBuilder();


        while (n>0) {
            int remains = n % k;
            n = n / k;
            value.insert(0, remains);
        }

        String[] values = value.toString().split("0");

        for (String val : values) {
            if(!"".equals(val)) {
                long number = Long.parseLong(val);
                if(isPrimeNumber(number)) {
                    answer++;
                }
            }
        }

        return answer;
    }

    private boolean isPrimeNumber(long number) {
        if(number < 2) {
            return false;
        }

        long root = (long) Math.sqrt(number);
        for (int i=2; i <= root; i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }
}
