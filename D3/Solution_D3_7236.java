package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class Solution_D3_7236 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		
		// 상, 하, 좌, 우, 좌상, 우상, 좌하, 우하
		int[][] d = {{-1,0},{1,0},{0,-1},{0,1},{-1,-1},{-1,1},{-1,1},{1,1}};
		
		int T = Integer.parseInt(br.readLine());	// 테스트 케이스 수
		
		for(int testcase=1; testcase<=T; testcase++) {
			int N = Integer.parseInt(br.readLine());
			char[][] map = new char[N][N];
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = st.nextToken().charAt(0);
				}
			}
			
			int max = Integer.MIN_VALUE;
			for(int r=0; r<N; r++) {
				for(int c=0; c<N; c++) {
					int cnt = 0;	// 주변 저수지 개수
					int nr=0, nc=0;	// 주변 좌표
					if(map[r][c]=='W') {			// 현재 좌표가 W 라면
						for(int i=0; i<8; i++) {	// 8방 탐색
							nr = r+d[i][0];
							nc = c+d[i][1];
							
							if(nr>-1 &&  nr<N && nc>-1 && nc<N	// 경계 내에 존재하면서
										&& map[nr][nc]=='W') {	// 탐색하는 주변 좌표가 W 라면
								cnt++;		// cnt 1 증가
							}
						}
					}
					max = Math.max(max, cnt);	// 이전의 주변 저수지 최고 수와 현재 저수지 수를 비교하여 더 큰 값으로 갱신
				}
			}
			if(max==0) max = 1;	// 만약 주변에 저수지가 하나도 없었다면 1
			System.out.println("#"+testcase+" "+max);
		}		
	}
	static String src = "4\r\n" + 
			"6\r\n" + 
			"G W G G W W\r\n" + 
			"G W G G W G\r\n" + 
			"W W W W G W\r\n" + 
			"W G W W W G\r\n" + 
			"G W W W W G\r\n" + 
			"G W W G W G\r\n" + 
			"5\r\n" + 
			"G W G G W\r\n" + 
			"G W G G W\r\n" + 
			"W W W W G\r\n" + 
			"W G W W W\r\n" + 
			"G W W W W\r\n" + 
			"3\r\n" + 
			"G G W\r\n" + 
			"G W W\r\n" + 
			"W W W\r\n" + 
			"3\r\n" + 
			"G G G\r\n" + 
			"G W G\r\n" + 
			"G G G";
}
