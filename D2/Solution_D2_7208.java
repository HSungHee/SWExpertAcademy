package swea;

import java.util.Arrays;
import java.util.Scanner;

public class Solution_D2_7208_한성희 {
	static int[][] map;		// 국가간 인접 관계 저장 2차원 배열
	static int[] color;		// 초기에 국가별 할당된 색상 저장 배열
	static int[] palette;	// 새로운 색상 저장 배열
	static int answer;			// 변경 횟수 저장
	static int N;			// 국가 수
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();	// 테스트케이스 수
	
		for(int testcase=1; testcase<=T; testcase++) {
			N = scan.nextInt();			
			map = new int[N][N];	
			color = new int[N];		
			palette = new int[N];
			answer = Integer.MAX_VALUE;
			
			for(int i=0; i<N; i++) {
				color[i] = scan.nextInt();
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j] = scan.nextInt();
				}
			}

			permutation(0);
			
			System.out.println("#"+ testcase + " " + answer);
		}
	}
	
	// 현재 국가와 인접하면서 같은 색상을 가지면 false, 아니면 true 
	public static boolean explore() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]==1 && palette[i] == palette[j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	// 모든 순열을 구해서 색상 차가 최소인 값을 answer에 저장 후 반환
	public static void permutation(int cnt) {
		if(cnt==N) {	// 모든 순열을 다 뽑은 상태
			if(explore()) {
				int min = 0;

				for(int i=0; i<N; i++) {
					if(color[i]!=palette[i]) {
						min++;
					}
				}
				if(min<answer) {
					answer = min;
				}
			}
			return;
		}
		
		for(int i=1; i<5; i++) {
			palette[cnt] = i;
			permutation(cnt+1);
		}
	}
}