package swea;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class Solution_test_1767 {
	static int[] comb;
	static int size, N, min, coreCnt;
	static Core[] core;
	static int[][] map;
	static int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};
	
	public static class Core {
		int x, y;
		
		Core(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		br = new BufferedReader(new StringReader(src));
		
		int T = Integer.parseInt(br.readLine().trim());		// 테스트케이스 수
		StringTokenizer st;
		
		for(int testcase=1; testcase<=T; testcase++) {
			N = Integer.parseInt(br.readLine().trim());	// 가로, 세로 길이 N
			map = new int[N][N];
			
			core = new Core[12];		// 코어의 좌표 담기
			size = 0;					// 가장자리를 제외한 코어 개수
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j]==1 && (i!=0 && i!=N-1 && j!=0 && j!=N-1)) {
						core[size++] = new Core(i, j);
					}
				}
			}
			
			min = Integer.MAX_VALUE;
			comb = new int[size];
			coreCnt = Integer.MIN_VALUE;
			dfs(0, 0, 0);
			System.out.println("#"+testcase+" "+min);
		}
	}
	
	private static void dfs(int idx, int len, int count) {	
		// 코어 탐색이 끝나
		if(idx==size) {
			// 기존의 최대 core 수랑 같은 개수라면, 둘 중 전선 길이 합이 더 짧은 것으로 최소길이 갱신. 
			if(coreCnt==count) min = Math.min(min, len);	
			else if(coreCnt<count){		// 기존의 최대 core 수보다 크다면, 현재 길이로 갱신
				coreCnt = count;	// 최대 사용 core 수 갱신
				min = len;
			}
			return;
		}
		
		// 사방 탐색
		for(int i=0; i<4; i++) {
			int nx = core[idx].x;
			int ny = core[idx].y;			
			int cnt = 0;
			
			while(true) {
				nx += delta[i][0];
				ny += delta[i][1];
				if(nx<0 || nx>=N || ny<0 || ny>=N) {	// 벽을 만나면
					break;
				}
				if(map[nx][ny]==1) { 	// 다른 코어나 전선을 만나면
					cnt = 0;	// 전선을 놓을 수 없는 경로니깐 cnt를 reset
					break;					
				}
				cnt++;
			}
			
			nx = core[idx].x;
			ny = core[idx].y;
			
			// cnt만큼 전선 설치
			for(int j=0; j<cnt; j++) {
				nx += delta[i][0];
				ny += delta[i][1];
				map[nx][ny] = 1;
			}
			
			// 해당 코어 연결 불가
			if(cnt==0) dfs(idx+1, len, count);
			else {	// 해당 코어 연결 가능하다면
				dfs(idx+1, len+cnt, count+1);
				nx = core[idx].x;
				ny = core[idx].y;
				
				// cnt만큼 전선 설치 해제
				for(int j=0; j<cnt; j++) {
					nx += delta[i][0];
					ny += delta[i][1];
					map[nx][ny] = 0;
				}
			}
		}
	}
	static String src = "3  \r\n" + 
			"7    \r\n" + 
			"0 0 1 0 0 0 0\r\n" + 
			"0 0 1 0 0 0 0\r\n" + 
			"0 0 0 0 0 1 0\r\n" + 
			"0 0 0 0 0 0 0\r\n" + 
			"1 1 0 1 0 0 0\r\n" + 
			"0 1 0 0 0 0 0\r\n" + 
			"0 0 0 0 0 0 0\r\n" + 
			"9  \r\n" + 
			"0 0 0 0 0 0 0 0 0\r\n" + 
			"0 0 1 0 0 0 0 0 1\r\n" + 
			"1 0 0 0 0 0 0 0 0\r\n" + 
			"0 0 0 1 0 0 0 0 0\r\n" + 
			"0 1 0 0 0 0 0 0 0\r\n" + 
			"0 0 0 0 0 0 1 0 0\r\n" + 
			"0 0 0 1 0 0 0 0 0\r\n" + 
			"0 0 0 0 0 0 0 1 0\r\n" + 
			"0 0 0 0 0 0 0 0 1\r\n" + 
			"11 \r\n" + 
			"0 0 1 0 0 0 0 0 0 0 0\r\n" + 
			"0 0 0 0 0 0 0 0 0 0 0\r\n" + 
			"0 0 0 0 0 0 0 0 0 0 1\r\n" + 
			"0 0 0 1 0 0 0 0 1 0 0\r\n" + 
			"0 1 0 1 1 0 0 0 1 0 0\r\n" + 
			"0 0 0 0 0 0 0 0 0 0 0\r\n" + 
			"0 0 0 0 0 0 0 1 0 0 0\r\n" + 
			"0 0 0 0 0 0 0 0 0 0 0\r\n" + 
			"0 0 0 0 0 0 0 0 1 0 0\r\n" + 
			"0 0 0 0 0 0 1 0 0 0 0\r\n" + 
			"0 0 0 0 0 0 0 0 0 0 0";
}