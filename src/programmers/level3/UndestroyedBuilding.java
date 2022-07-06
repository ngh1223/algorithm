package programmers.level3;

/**
 * 문제 주소: https://school.programmers.co.kr/learn/courses/30/lessons/92344
 */
public class UndestroyedBuilding {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int[][] sum = new int[board.length][board[0].length];

        for (int[] row : skill) {
            int type = row[0]; // 1: 공격, 2: 치료
            int r1 = row[1];
            int c1 = row[2];
            int r2 = row[3];
            int c2 = row[4];
            int degree = row[5];
            if (type == 1) {
                degree = degree * -1;
            }

            sum[r1][c1] += degree;

            if (c2 + 1 < board[0].length) {
                sum[r1][c2 + 1] += (degree * -1);
            }

            if (r2 + 1 < board.length) {
                sum[r2 + 1][c1] += (degree * -1);
            }

            if (r2 + 1 < board.length && c2 + 1 < board[0].length) {
                sum[r2 + 1][c2 + 1] += degree;
            }
        }

        // 누적 합
        // 행의 합
        for (int i=0; i< sum.length; i++) {
            int[] row = sum[i];
            for (int k=0; k<row.length; k++) {
                if(k > 0) {
                    sum[i][k] += sum[i][k-1];
                }
            }
        }

        // 열의 합
        for (int i=0; i< sum.length; i++) {
            int[] row = sum[i];
            for (int k=0; k<row.length; k++) {
                if(i > 0) {
                    sum[i][k] += sum[i-1][k];
                }
            }
        }

        // 누적 합 + board
        for (int i=0; i<sum.length; i++) {
            int[] row = sum[i];
            for (int k = 0; k<row.length; k++) {
                if (board[i][k] + sum[i][k] > 0) {
                    answer++;
                }
            }
        }

        return answer;
    }
}
