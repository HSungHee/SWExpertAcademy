package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution_D2_2005 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int testcase=1; testcase<=T; testcase++) {
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			
			for(int i=0; i<N; i++) {	// 0 ~ n-1 열
				for(int j=0; j<i+1; j++) {	// 1, 2, ..., n-1 행
					if(j==0) {		// 첫번째 열 : 1
						map[i][j] = 1;
					} else if(j==i) {	// 마지막 열 : 1
						map[i][j] = 1;
					} else {	// 나머지 열 
						map[i][j] = map[i-1][j-1] + map[i-1][j];	// 왼쪽 대각선 + 바로 위
					}
				}
			}
            System.out.println("#"+testcase);

			for(int[] ma : map) {
				for(int m : ma) {
					if(m!=0) {
						System.out.print(m + " ");
					}
				}
				System.out.println();
			}
		}
	}
}
