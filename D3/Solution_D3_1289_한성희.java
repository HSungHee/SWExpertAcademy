package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_D3_1289 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int num = Integer.parseInt(in.readLine());
		int cnt;
		for(int n=1; n<=num; n++) {
			String str = in.readLine();
			int[] arr = new int[str.length()];	// 입력한 값들을 저장하기위한 배열
			cnt = 0;	// 변경해야할 횟수를 카운트하는 변수
			for(int i=0, size=str.length(); i<size; i++) {
				arr[i] = str.charAt(i) - '0';	// 입력 값 정수로 변경하여 배열에 저장
			}
			
			// 비트 값이 달라질대 cnt 1 추가
			for(int i=1; i<arr.length; i++) {
				if(arr[i-1]!=arr[i]) {
					cnt++;
				}	
			}
			// 맨 끝자리가 1로 시작하면 cnt 1 추가
			if(arr[0]==1) {
				cnt++;
			}
			System.out.println("#" + n + " " + cnt);
		}
	}
}