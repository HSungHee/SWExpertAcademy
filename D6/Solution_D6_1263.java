package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D6_1263 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int testcase=1; testcase<=T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[][] map = new int[N][N];
			int max = Integer.MAX_VALUE>>2;
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(i!=j && map[i][j]==0) {			
						map[i][j] = max;
					}
				}
			}
			
			for(int k=0; k<N; k++) {				
				for(int i=0; i<N; i++) {			
					for(int j=0; j<N; j++) {		
						if(map[i][j] > map[i][k]+map[k][j]) {	
							map[i][j] = map[i][k]+map[k][j];	
						}							
					}
				}
			}
			
			int min = Integer.MAX_VALUE;
			for(int[] ma : map) {
				int sum = 0;
				for(int m : ma) {
					sum += m;
				}
				min = Math.min(min, sum);
			}
			System.out.println("#"+testcase+" "+min);
		}
	}
}
