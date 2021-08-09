package swea;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution_D3_9229 {
	static int N;	// 과자 봉지 개수
	static int M;	// 과자 무게 합
	static int[] snack;	// 입력 과자 무게 배열
	static int[] tmp;
	static int max;	// 최대값
	
	public static void combination(int cnt, int start) {
		if(cnt == 2) {
			int sum = tmp[0]+tmp[1];
			if(sum<=M && max<sum) {
				max = sum;
			}
			return;
		}
		for(int i=start; i<N; i++) {
			tmp[cnt] = snack[i];
			combination(cnt+1, i+1);
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
//		System.setIn(new FileInputStream("res/9229.txt"));
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();	// 테스트케이스 수
		
		for(int testcase=1; testcase<=TC; testcase++) {
			N = sc.nextInt();	
			M = sc.nextInt();	
			
			snack = new int[N];
			for(int i=0; i<N; i++) {
				snack[i] = sc.nextInt();
			}
			
			max = Integer.MIN_VALUE;
			tmp = new int[2];
			combination(0, 0);
			
			System.out.println("#"+testcase+" "+((max==Integer.MIN_VALUE)?-1:max));
		}
	}
}

