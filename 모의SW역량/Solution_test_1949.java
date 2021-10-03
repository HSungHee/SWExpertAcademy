package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class Solution_test_1949 {
	private static int[][] map;		// 등산로 정보
	private static boolean[][] visited;	// 방문 여부
	private static int N;	// 등산로 부지 크기, N x N
	private static int[][] d = {{-1,0},{1,0},{0,-1},{0,1}};	// 상, 하, 좌, 우
	private static int answer;	// 정답
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st = null;
		for(int testcase=1; testcase<=T; testcase++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	
			int K = Integer.parseInt(st.nextToken());	// 공사 가능 깊이
			
			map = new int[N][N];	// 등산로 정보
			visited = new boolean[N][N];
			answer = 0;			
			int top = 0;	// 가장 높은 봉우리
				
			// 등산로 정보 저장
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					top = Math.max(top,  map[i][j]);	// 가장 높은 봉우리 높이를 저장
				}
			}
		
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j]==top) {
						visited[i][j] = true;	// 시작좌표 방문 표시
						dfs(i, j, K, 1);	// 시작 좌표 : (i, j), 공사 가능 깊이 K, 현재 완성된 등산로 길이 
						visited[i][j] = false;	// 시작좌표 방문 해제
					}
				}
			}	
			System.out.println("#"+testcase+" "+answer);
		}
	}
	
	private static void dfs(int r, int c, int k, int len) {
		// 사방 탐색
		for(int i=0; i<4; i++) {
			int nr = r + d[i][0];
			int nc = c + d[i][1];
			
			// 전진하려는 곳이 경계 내에 있으면서 아직 방문하지 않은 값이라면
			if(nr>-1 && nr<N && nc>-1 && nc<N && !visited[nr][nc]) {
				// 다음 길이 현재보다 더 낮다면 그 길로 전진
				if(map[r][c] > map[nr][nc]) {
					visited[nr][nc] = true;	// 방문 표시
					dfs(nr, nc, k, len+1);	
					visited[nr][nc] = false;	// 방문 해제
				} else {
					// 다음 길이 현재보다 더 높지만 공사 가능
					if((map[nr][nc]-map[r][c]+1) <= k) {
						int offset = (map[nr][nc]-map[r][c]+1);	// 현재 칸보다 하나 더 낮게 만들기 위한 차이
						visited[nr][nc] = true;	// 방문 표시
						map[nr][nc] -= offset;	// 공사 진행
						
						dfs(nr, nc, 0, len+1);
						
						visited[nr][nc] = false;	// 방문 해제
						map[nr][nc] += offset;	// 공사 해제
						
					}
					// 다음 길이 현재보다 더 높고 공사 불가능
					else {
						answer = Math.max(answer, len);	// 더이상 나갈 수 없는 경우 현재까지 
					}					
				}
			}
		}
	}
	
	static String src = "10\r\n" + 
			"5 1\r\n" + 
			"9 3 2 3 2\r\n" + 
			"6 3 1 7 5\r\n" + 
			"3 4 8 9 9\r\n" + 
			"2 3 7 7 7\r\n" + 
			"7 6 5 5 8\r\n" + 
			"3 2\r\n" + 
			"1 2 1\r\n" + 
			"2 1 2\r\n" + 
			"1 2 1\r\n" + 
			"5 2\r\n" + 
			"9 3 2 3 2\r\n" + 
			"6 3 1 7 5\r\n" + 
			"3 4 8 9 9\r\n" + 
			"2 3 7 7 7\r\n" + 
			"7 6 5 5 8\r\n" + 
			"4 4\r\n" + 
			"8 3 9 5\r\n" + 
			"4 6 8 5\r\n" + 
			"8 1 5 1\r\n" + 
			"4 9 5 5\r\n" + 
			"4 1\r\n" + 
			"6 6 1 7\r\n" + 
			"3 6 6 1\r\n" + 
			"2 4 2 4\r\n" + 
			"7 1 3 4\r\n" + 
			"5 5\r\n" + 
			"18 18 1 8 18\r\n" + 
			"17 7 2 7 2\r\n" + 
			"17 8 7 4 3\r\n" + 
			"17 9 6 5 16\r\n" + 
			"18 10 17 13 18\r\n" + 
			"6 4\r\n" + 
			"12 3 12 10 2 2\r\n" + 
			"13 7 13 3 11 6\r\n" + 
			"2 2 6 5 13 9\r\n" + 
			"1 12 5 4 10 5\r\n" + 
			"11 10 12 8 2 6\r\n" + 
			"13 13 7 4 11 7\r\n" + 
			"7 3\r\n" + 
			"16 10 14 14 15 14 14\r\n" + 
			"15 7 12 2 6 4 9\r\n" + 
			"10 4 11 4 6 1 1\r\n" + 
			"16 4 1 1 13 9 14\r\n" + 
			"3 12 16 14 8 13 9\r\n" + 
			"3 4 17 15 12 15 1\r\n" + 
			"6 6 13 6 6 17 12\r\n" + 
			"8 5\r\n" + 
			"2 3 4 5 4 3 2 1\r\n" + 
			"3 4 5 6 5 4 3 2\r\n" + 
			"4 5 6 7 6 5 4 3\r\n" + 
			"5 6 7 8 7 6 5 4\r\n" + 
			"6 7 8 9 8 7 6 5\r\n" + 
			"5 6 7 8 7 6 5 4\r\n" + 
			"4 5 6 7 6 5 4 3\r\n" + 
			"3 4 5 6 5 4 3 2\r\n" + 
			"8 2\r\n" + 
			"5 20 15 11 1 17 10 14\r\n" + 
			"1 1 11 16 1 14 7 5\r\n" + 
			"17 2 3 4 5 13 19 20\r\n" + 
			"6 18 5 16 6 7 8 5\r\n" + 
			"10 4 5 4 9 2 10 16\r\n" + 
			"2 7 16 5 8 9 10 11\r\n" + 
			"12 19 18 8 7 11 15 12\r\n" + 
			"1 20 18 17 16 15 14 13";
}
