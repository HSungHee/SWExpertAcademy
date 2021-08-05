package swea;

import java.util.Scanner;

public class Solution_D2_2001 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();	// 테스트 케이스 수
		for(int testcase = 1; testcase<=T; testcase++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			int[][] map = new int[N][N];
			int sum = 0;
			int max = Integer.MIN_VALUE;
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			// 전체 N x N 배열 탐색
			for(int r=0; r<=N-M; r++) {
				for(int c=0; c<=N-M; c++) {
					sum = 0;
					// 모기채에 죽은 모기 수 count해서 합에 저장
					for(int i=r; i<r+M; i++) {
						for(int j=c; j<c+M; j++) {
							
							sum+=map[i][j];
						}
					}
					// 현재 합이 최대값 보다 크다면 최대값 갱신
					if(sum>max) {
						max = sum;
					}
				}
			}
			System.out.println("#"+testcase+" "+max);
		}
	}
}
