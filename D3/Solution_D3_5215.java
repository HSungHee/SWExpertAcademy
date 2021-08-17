package swea;

import java.util.Scanner;

public class Solution_D3_5215 {
	static int N;	// 재료 수
	static int L;		// 제한 칼로리
	static int[][] ingredients; // 점수 정보 2차원 배열 (재료 점수, 칼로리)
	static int max = 0;		// 최대 점수 변수
	
	private static void burger(int cnt, int score_sum, int kcal_sum) {
		// 제한 칼로리를 초과하는 경우
		if(kcal_sum>L) {
			return;
		}
		// 경우의 수를 끝까지 다 뽑은 경우
		if(cnt==N) {
			if(score_sum>max) {
				max = score_sum;
			}
			return;
		}
		
		burger(cnt+1, score_sum+ingredients[cnt][0], kcal_sum+ingredients[cnt][1]);	// 선택
		burger(cnt+1, score_sum, kcal_sum);	// 비선택
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int testcase = scan.nextInt();	// 테스트케이스 수
		
		for(int tc=1; tc<=testcase; tc++) {
			N = scan.nextInt();		
			L = scan.nextInt();		
			
			ingredients = new int[N][2];
			
			for(int i=0; i<N; i++) {
				ingredients[i][0] = scan.nextInt();		
				ingredients[i][1] = scan.nextInt();		
			}
	
			burger(0, 0, 0);
			
			System.out.println("#"+tc+" "+max);
		}
	}
}