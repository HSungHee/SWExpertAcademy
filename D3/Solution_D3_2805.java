package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D3_2805 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea_2805.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int testcase=1; testcase<=T; testcase++) {
			int N =Integer.parseInt(br.readLine());		// 농장의 크기
			int[][] map = new int[N][N];	// 농장 가치 정보 배열
			
			for(int r=0; r<N; r++) {
				char[] tmp = br.readLine().toCharArray();
				for(int c=0; c<N; c++) {
					map[r][c] = tmp[c]-'0';		// 입력데이터 정수로 변환하여 map에 입력 
				}
			}
			
			int mid = N/2;		// 길이 N의 절반 지점 구하기
			int profit = 0;		// 총 수익
			
			for(int r=0; r<N; r++) {
				if(r<mid) {		// 행이 절반 mid보다 작은 값이라면
					for(int c=mid-r; c<=mid+r; c++) {	// 열의 중간 지점부터 1, 3, 5, ... 홀수의 2씩 크기로 커지는 범위
						profit += map[r][c];	// 해당 수익 합에 누적
					}
				} else {
					for(int c=mid-(N-r-1); c<=mid+(N-r-1); c++) {	// 열의 중간 지점부터 ..., 5, 3, 1 홀수의 2씩 크기로 작아는 범위
						profit += map[r][c];	// 해당 수익 합에 누적
					}
				}
			}
			System.out.println("#"+testcase+" "+profit);
		}
	}
}
