package swea;

import java.util.Scanner;

public class Solution_D3_5603 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int testcase=1; testcase<=T; testcase++) {
			int N = sc.nextInt();		// 건초더미 개수
			int[] grass = new int[N];	// 건초 높이를 입력받을 배열
			
			int sum = 0;		// 건초들의 높이 합을 저장할 변수
			for(int i=0; i<N; i++) {	
				grass[i] = sc.nextInt();
				sum += grass[i];
			}
		
			int avg = sum / N;		// 건초들의 높이 평균 
			sum = 0;
			for(int g : grass) {	
				sum += Math.abs(avg-g);		// 차이의 절대값을 누적
			}
			
			// 높은곳 -> 낮은곳 / 낮은곳 -> 높은곳 한군데에서 옮기면 2개의 건초더미에 변화가 생기니 결과에 / 2
			System.out.println("#"+testcase+" "+sum/2);		
		}
	}
}
