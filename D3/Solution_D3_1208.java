package Solution;

import java.util.Arrays;
import java.util.Scanner;

public class Solution_D3_1208 {

	public static void move(int d, int[] arr) {
		Arrays.sort(arr);	// 배열 정렬
		if(arr[arr.length-1]-arr[0]==1 || d==0) {	// 최대값 - 최소값이 1인 경우나 dump만큼 수행한 경우 재귀 종료
			return;
		} else {
			arr[0]++;	// 최소 기둥 높이 1 증가
			arr[arr.length-1]--;	// 최대 기둥 높이 1 감소
			move(d-1, arr);	// dump 만큼 재귀문 반복
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = 10; 	// 테스트케이스 수

		for(int i=1; i<=T; i++) {
			int dump = sc.nextInt();
			int[] arr = new int[100];
			
			for(int idx=0; idx<100; idx++) {
				arr[idx] = sc.nextInt();
			}
			move(dump, arr);
			System.out.printf("#%d %d%n", i, arr[arr.length-1]-arr[0]);
		}
	}
}