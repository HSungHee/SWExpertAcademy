package Solution;

import java.util.Scanner;

public class Solution_D2_1954_한성희 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();	// 테스트케이스 수
		int[][] path;			// 달팽이 숫자 저장 배열
		
		// 우, 하, 좌, 상 
		int[][] d = {{0,1}, {1,0}, {0,-1}, {-1,0}};
	
		// 전체 기능 테스트케이스 수만큼 반복
		for(int t=1; t<=T; t++) {
			int N = sc.nextInt();	// 
			int r=0, c=0;			// row, column
			int turn = 0;			// 회전 변수
			path = new int[N][N];	// N 크기만큼 배열 초기화
			
			for(int i=0; i<N*N; i++) {
				if(N==1) {
					path[r][c] = 1;
					break;
				}
				path[r][c] = i+1;	// 값 넣어주기

				r = r + d[turn][0];	// 현재 방향으로 전진
				c = c + d[turn][1];
				
				// 회전을 해야하는 경우
				// 1. 달팽이가 범위를 벗어나는 경우 
				// 2. 이미 지나간 길을 만난 경우
				if((r<0 || r>=N || c<0 || c>=N) || path[r][c]!=0) {	
					r = r - d[turn][0];	// 다시 원 위치
					c = c - d[turn][1];	
					
					turn = (turn+1)%4;	// 방향 전환

					r = r + d[turn][0];	// 방향 전환 후 전진
					c = c + d[turn][1];
				}
			}				
			
			// 출력
			System.out.printf("#%d%n", t);
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					System.out.printf("%d ", path[i][j]);
				}
				System.out.println();
			}
		}
	}
}
