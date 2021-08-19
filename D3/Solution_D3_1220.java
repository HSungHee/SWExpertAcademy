package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D3_1220 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea_1220.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int testcase=1; testcase<11; testcase++) {	// 총 10개의 testcase
			int N = Integer.parseInt(br.readLine());	// 한 변의 길이
			int[][] map = new int[N][N];
			
			for(int r=0; r<N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int c=0; c<N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			int pair = 0;	// 총 페어 수
			for(int c=0; c<N; c++) {
				int state = 0;	// 페어 상태
				for(int r=0; r<N; r++) {
					if(state!=1 && map[r][c]==1) {	// 이전 상태가 N이 아니면서 현재 N자성 이면
						state = 1;
					}
					if(state==1 && map[r][c]==2) {	// 이전 상태가 N이면서 현재 S자성 이면
						pair++;
						state = 2;
					}
				}
			}
			System.out.println("#"+testcase+" "+pair);
		}
	}
}
