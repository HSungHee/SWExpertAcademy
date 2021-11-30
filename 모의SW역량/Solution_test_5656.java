package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/** 중복순열 이용 
 * 
 * 1. 중복 순열에 모든 열에 구슬을 떨어뜨려보기
 * 2. 구슬 깨기 bfs/dfs-> bfs가 더 편할 듯
 * 3. 깨진 벽돌을 없애고 위에있는 것을 내리기
 * 4. 위의 단계들을 N번 반복
 * 5. 남은 벽돌 세서 최소값으로 갱신
 * 
 * **/

public class Solution_test_5656 {
	static int N, W, H, map[][], backupMap[][], numbers[], answer;
	static int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};	// 사방 탐색을위한 delta 배열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		
		int T = Integer.parseInt(br.readLine());	// 테스트케이스 수
		
		for(int testcase=1; testcase<=T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// 구슬을 쏠 수 있는 횟수
			W = Integer.parseInt(st.nextToken());	// 판의  width
			H = Integer.parseInt(st.nextToken());	// 판의 height
			
			map = new int[H][W];	// 판을 생성
			backupMap = new int[H][W];	// 초기 백업용 판 정보
			numbers = new int[N];	// 중복 순열 경우를 담기위한 배열
			answer = Integer.MAX_VALUE;
			
			for(int r=0; r<H; r++) {
				st = new StringTokenizer(br.readLine());
				for(int c=0; c<W; c++) {
					map[r][c] = backupMap[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			permutation(0);		// W열에서 N번 떨어뜨리기
			System.out.println("#"+testcase+" "+answer);
		}	
	}
	
	// 중복 순열로 구슬을 N번 떨어뜨리는 경우의 수 구하기
	private static void permutation(int cnt) {
		if(cnt==N) {		// 경우의 수가 완성 되었을 때
			breakBrick();	// 완성된 경우의 수에 따라 벽돌 부수기
			int count = 0; 	// 남은 벽 count
			for(int[] ma : map) {
				for(int m : ma) {
					if(m>0) count++;
				}
			}
			answer = Math.min(answer, count);
			reset();
			return;
		}
		
		for(int i=0; i<W; i++) {
			numbers[cnt] = i;
			permutation(cnt+1);
		}		
	}
	
	// 벽돌 부수기
	private static void breakBrick() {
		Queue<int[]> queue = new LinkedList<int[]>();
		
		for(int i=0; i<N; i++) {		// 만들어진 조합에 의해 1번째, 2번째, ..., N번째 구슬 떨어뜨리기
			for(int j=0; j<H; j++) {	// 해당 열에서 
				if(map[j][numbers[i]]!=0) {		// 처음 0이아닌 행의 위치 구하기
					queue.offer(new int[] {j, numbers[i]});	// 위치 queue에 저장
					break;
				}
			}
			while(!queue.isEmpty()) {
				int[] curr = queue.poll();	// queue에서 하나 꺼내오기
				int r = curr[0];
				int c = curr[1];
				int num = map[r][c];	// 해당 좌표에 적힌 숫자
				
				for(int d=0; d<4; d++) {	// 사방 탐색
					for(int offset=1; offset<num; offset++) {	// 시작위치의 적힌 숫자만큼 뻗어나가기
						int nr = r + delta[d][0] * offset;
						int nc = c + delta[d][1] * offset;
						if(nr>-1 && nr<H && nc>-1 && nc<W) {
							if(map[nr][nc]==1) map[nr][nc] = 0;	// 벽돌 깨기
							else if(map[nr][nc]>1) {
								queue.offer(new int[] {nr, nc});	// 해당 벽돌의 정보를 저장하여 위의 과정 반복
							}
						}
					}
				}
			}
			reorganize();	// 벽을 한 번 부순 후, 재 정렬
		}		
	}

	// 벽돌을 부순 후, 재정렬을 위한 함수
	private static void reorganize() {
		for(int c=0; c<W; c++) {
			ArrayList<Integer> list = new ArrayList<Integer>(H);
			for(int r=H-1; r>-1; r--) {					// 열마다 밑에서부터 위로 값이 존재하면 list에 저장
				if(map[r][c]>0) list.add(map[r][c]);
			}
			int size = 0;
			for(int r=H-1; r>-1; r--) {		// list에 있는 값들을 열마다 밑에서부터 저장한 뒤, 나머지는 0 저장
				if(size==list.size()) {
					map[r][c] = 0;
				} else {
					map[r][c] = list.get(H-r-1);
					size++;
				}
			}
		}
	}
	
	// map 정보 초기화
	private static void reset() {
		for(int i=0; i<H; i++) {
			map[i] = backupMap[i].clone();
		}
	}

	static String src = "5\r\n" + 
			"3 10 10\r\n" + 
			"0 0 0 0 0 0 0 0 0 0\r\n" + 
			"1 0 1 0 1 0 0 0 0 0\r\n" + 
			"1 0 3 0 1 1 0 0 0 1\r\n" + 
			"1 1 1 0 1 2 0 0 0 9\r\n" + 
			"1 1 4 0 1 1 0 0 1 1\r\n" + 
			"1 1 4 1 1 1 2 1 1 1\r\n" + 
			"1 1 5 1 1 1 1 2 1 1\r\n" + 
			"1 1 6 1 1 1 1 1 2 1\r\n" + 
			"1 1 1 1 1 1 1 1 1 5\r\n" + 
			"1 1 7 1 1 1 1 1 1 1\r\n" + 
			"2 9 10\r\n" + 
			"0 0 0 0 0 0 0 0 0\r\n" + 
			"0 0 0 0 0 0 0 0 0\r\n" + 
			"0 1 0 0 0 0 0 0 0\r\n" + 
			"0 1 0 0 0 0 0 0 0\r\n" + 
			"1 1 0 0 1 0 0 0 0\r\n" + 
			"1 1 0 1 1 1 0 1 0\r\n" + 
			"1 1 0 1 1 1 0 1 0\r\n" + 
			"1 1 1 1 1 1 1 1 0\r\n" + 
			"1 1 3 1 6 1 1 1 1\r\n" + 
			"1 1 1 1 1 1 1 1 1\r\n" + 
			"3 6 7\r\n" + 
			"1 1 0 0 0 0\r\n" + 
			"1 1 0 0 1 0\r\n" + 
			"1 1 0 0 4 0\r\n" + 
			"4 1 0 0 1 0\r\n" + 
			"1 5 1 0 1 6\r\n" + 
			"1 2 8 1 1 6\r\n" + 
			"1 1 1 9 2 1\r\n" + 
			"4 4 15\r\n" + 
			"0 0 0 0 \r\n" + 
			"0 0 0 0 \r\n" + 
			"0 0 0 0 \r\n" + 
			"1 0 0 0 \r\n" + 
			"1 0 0 0 \r\n" + 
			"1 0 0 0 \r\n" + 
			"1 0 0 0 \r\n" + 
			"1 0 5 0 \r\n" + 
			"1 1 1 0 \r\n" + 
			"1 1 1 9 \r\n" + 
			"1 1 1 1 \r\n" + 
			"1 6 1 2 \r\n" + 
			"1 1 1 5 \r\n" + 
			"1 1 1 1 \r\n" + 
			"2 1 1 2 \r\n" + 
			"4 12 15\r\n" + 
			"9 9 9 9 9 9 9 9 9 9 9 9\r\n" + 
			"9 9 9 9 9 9 9 9 9 9 9 9\r\n" + 
			"9 9 9 9 9 9 9 9 9 9 9 9\r\n" + 
			"9 9 9 9 9 9 9 9 9 9 9 9\r\n" + 
			"9 9 9 9 9 9 9 9 9 9 9 9\r\n" + 
			"9 9 9 9 9 9 9 9 9 9 9 9\r\n" + 
			"9 9 9 9 9 9 9 9 9 9 9 9\r\n" + 
			"9 9 9 9 9 9 9 9 9 9 9 9\r\n" + 
			"9 9 9 9 9 9 9 9 9 9 9 9\r\n" + 
			"9 9 9 9 9 9 9 9 9 9 9 9\r\n" + 
			"9 9 9 9 9 9 9 9 9 9 9 9\r\n" + 
			"9 9 9 9 9 9 9 9 9 9 9 9\r\n" + 
			"9 9 9 9 9 9 9 9 9 9 9 9\r\n" + 
			"9 9 9 9 9 9 9 9 9 9 9 9\r\n" + 
			"9 9 9 9 9 9 9 9 9 9 9 9";
}
