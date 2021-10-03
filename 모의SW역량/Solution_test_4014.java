package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class Solution_test_4014 {
	static int answer, N, X;
	static int map1[][], map2[][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		
		int T = Integer.parseInt(br.readLine());	// 테스트케이스 수
		
		for(int testcase=1; testcase<=T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// 활주로 크기
			X = Integer.parseInt(st.nextToken());	// 경사로 가로 길이
			
			map1 = new int[N][N];	// 가로 탐색을 위한 map
			map2 = new int[N][N];	// 세로 탐색을 위한 map
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map1[i][j] = map2[j][i] = Integer.parseInt(st.nextToken());
				}
			}

			answer = 0;
			for(int i=0; i<N; i++) {
				// 가로 탐방
				if(search(map1, i)) answer++;
				
				// 세로 탐방
				if(search(map2, i))	answer++;
				
			}
			System.out.println("#"+testcase+" "+answer);
		}
	}

	
	private static boolean search(int[][] map, int i) {
		int com = map[i][0];	// 매개변수로 받은 i열 첫번재 행
		int cnt = 1;			// 현재까지 개수 1
		boolean down = false;
		
		for(int j=1; j<N; j++) {
			if(map[i][j]==com) {	// 기준 값과 값이 같은 경우
				cnt++;
			} else {		// 기준 값과 값이 다른 경우
				// 내려가는 경사로
				if(!down && com - map[i][j] == 1) {		// 처음 내려가는 것이라면 높이가 바뀌기전 지점의 길이 체크는 필요 X 
					down = true;	// down 발생 여부 표시
				} 
				else if(down && com - map[i][j] == 1) {	// 처음 내려가는 것이 아니라면 높이가 바뀌기전 지점의 길이 체크 필요
					if(cnt < X) return false;	// 그 길이가 X보다 적다면 활주로 설치 불가
				}
				
				// 올라가는 경사로
				else if(map[i][j] - com == 1) {
					if(cnt < X) return false;	// 현재의 전, 즉, 낮은 지점의 길이가 X보다 작다면 활주로 설치 불가
					// 다시 올라가능 경우
					if(down) {
						if(cnt < 2*X) return false;	// V 모양이 될 때, 현재의 전 길이가 X의 2배가 아니라면 탈출
						else down = false;	// 현재의 전 지점의 길이가 X의 2배를 성립한다면 down을 false로 다시 변환
					}
					
				}
				else return false;	// 값이 바뀌는 지점의 차이가 1보다 크다면 활주로 설치 불가
				
				// 높이가 바뀌고 난 후, 해당 높이에 대한 탐색을 위해 cnt(길이 값)와 com(기준 높이) 재설정
				cnt = 1;	// 다시 새로운 지점을 counting하기 위해 cnt를 1로 초기화
				com = map[i][j];	// 기준점 변환
			}
		}
		if(down && cnt < X) return false;	// 마지막까지 왔을때 평평한 지점의 길이가 X보다 작다면 활주로 설치 불가
		return true;
	}

	static String src = "10\r\n" + 
			"6 2\r\n" + 
			"3 3 3 2 1 1\r\n" + 
			"3 3 3 2 2 1\r\n" + 
			"3 3 3 3 3 2\r\n" + 
			"2 2 3 2 2 2\r\n" + 
			"2 2 3 2 2 2\r\n" + 
			"2 2 2 2 2 2\r\n" +
			"6 4\r\n" + 
			"3 2 2 2 1 2 \r\n" + 
			"3 2 2 2 1 2 \r\n" + 
			"3 3 3 3 2 2 \r\n" + 
			"3 3 3 3 2 2 \r\n" + 
			"3 2 2 2 2 2 \r\n" + 
			"3 2 2 2 2 2 \r\n" + 
			"7 2\r\n" + 
			"1 1 1 1 2 1 1 \r\n" + 
			"1 1 1 2 2 2 1 \r\n" + 
			"2 2 2 2 2 2 2 \r\n" + 
			"2 2 2 2 2 2 2 \r\n" + 
			"2 2 2 2 2 2 2 \r\n" + 
			"2 2 2 2 2 2 2 \r\n" + 
			"2 2 2 2 2 2 2 \r\n" +
			"8 3\r\n" + 
			"2 2 2 3 3 4 2 2 \r\n" + 
			"2 2 2 3 3 4 2 2 \r\n" + 
			"2 2 2 2 2 2 2 2 \r\n" + 
			"2 2 2 2 2 2 2 2 \r\n" + 
			"2 2 2 2 1 1 2 2 \r\n" + 
			"2 1 1 1 1 1 1 1 \r\n" + 
			"2 1 1 1 1 1 1 1 \r\n" + 
			"2 1 1 1 1 1 1 1 \r\n" + 
			"8 4\r\n" + 
			"1 1 1 1 1 1 1 1 \r\n" + 
			"1 1 1 1 1 1 1 1 \r\n" + 
			"1 1 1 1 1 1 1 1 \r\n" + 
			"2 1 1 1 1 1 1 1 \r\n" + 
			"2 1 1 1 1 1 1 1 \r\n" + 
			"2 1 1 1 1 1 1 1 \r\n" + 
			"1 1 1 1 1 1 1 2 \r\n" + 
			"1 1 1 1 1 1 1 2 \r\n" + 
			"9 4\r\n" + 
			"4 4 3 3 3 3 2 2 2 \r\n" + 
			"4 4 3 3 1 1 2 2 3 \r\n" + 
			"3 3 2 2 1 1 1 1 2 \r\n" + 
			"1 1 1 1 1 1 1 1 1 \r\n" + 
			"1 1 1 1 1 1 1 1 1 \r\n" + 
			"2 2 1 1 1 1 1 1 1 \r\n" + 
			"2 2 1 1 1 1 1 1 1 \r\n" + 
			"2 2 2 2 2 2 1 1 1 \r\n" + 
			"3 3 3 3 2 2 2 2 1 \r\n" + 
			"10 2\r\n" + 
			"2 2 3 5 3 1 1 1 1 1 \r\n" + 
			"2 2 3 5 3 1 1 1 1 1 \r\n" + 
			"3 3 4 5 4 3 2 1 1 2 \r\n" + 
			"3 3 4 5 4 3 2 1 1 2 \r\n" + 
			"5 5 5 5 5 5 3 1 1 3 \r\n" + 
			"4 4 4 5 5 5 4 3 3 3 \r\n" + 
			"4 4 4 5 5 5 5 5 5 3 \r\n" + 
			"4 4 4 4 4 5 5 5 5 3 \r\n" + 
			"4 4 4 4 4 5 5 5 5 3 \r\n" + 
			"5 5 4 4 4 5 5 5 5 4 \r\n" + 
			"12 4\r\n" + 
			"4 4 4 5 5 4 4 4 4 4 4 4 \r\n" + 
			"5 5 5 5 5 5 5 5 5 5 5 4 \r\n" + 
			"4 4 4 5 5 4 4 4 5 5 5 4 \r\n" + 
			"3 3 4 5 5 4 3 4 5 5 5 4 \r\n" + 
			"3 3 4 5 5 4 3 4 5 5 5 4 \r\n" + 
			"2 2 3 4 4 4 4 4 4 4 4 5 \r\n" + 
			"2 2 3 4 4 4 4 4 4 4 4 5 \r\n" + 
			"2 2 3 3 3 4 5 3 2 2 4 4 \r\n" + 
			"3 3 3 4 4 4 5 4 3 3 4 4 \r\n" + 
			"3 3 4 5 5 5 5 5 5 5 5 4 \r\n" + 
			"3 3 4 5 5 5 5 5 5 5 5 4 \r\n" + 
			"4 4 4 4 4 4 5 5 5 5 5 4 \r\n" + 
			"15 3\r\n" + 
			"5 4 4 3 3 3 2 2 2 3 4 4 4 4 4 \r\n" + 
			"5 4 4 3 3 3 2 2 2 3 4 4 4 4 4 \r\n" + 
			"5 5 5 5 4 4 2 2 3 4 4 4 4 4 5 \r\n" + 
			"5 4 4 3 3 3 2 2 3 4 4 4 4 4 4 \r\n" + 
			"5 3 3 1 2 2 3 3 3 4 4 4 4 4 4 \r\n" + 
			"3 3 3 3 3 3 3 3 3 3 4 4 4 4 4 \r\n" + 
			"3 3 3 3 3 3 3 3 3 3 4 4 4 4 4 \r\n" + 
			"2 3 3 4 3 3 3 3 3 3 3 4 4 4 3 \r\n" + 
			"2 3 3 4 3 3 3 3 3 3 3 4 4 4 3 \r\n" + 
			"3 4 4 4 4 4 3 3 3 3 3 3 4 4 4 \r\n" + 
			"5 5 5 4 4 4 4 4 3 3 3 3 4 4 5 \r\n" + 
			"5 5 5 4 4 4 4 4 3 3 3 3 4 4 5 \r\n" + 
			"5 5 5 5 4 4 4 4 3 3 2 2 3 3 4 \r\n" + 
			"5 5 5 5 5 5 4 4 3 3 2 1 2 2 3 \r\n" + 
			"5 5 5 5 5 5 4 4 3 3 2 1 2 2 3 \r\n" + 
			"20 3\r\n" + 
			"3 3 3 2 2 2 2 3 3 3 4 4 4 4 4 4 5 5 5 5 \r\n" + 
			"3 3 3 2 2 2 2 3 3 3 4 4 4 4 4 4 5 5 5 5 \r\n" + 
			"5 3 3 2 2 2 2 2 3 3 4 4 4 4 5 5 5 5 5 5 \r\n" + 
			"4 3 3 1 1 1 1 1 2 3 4 4 4 5 5 5 5 5 5 5 \r\n" + 
			"4 2 2 1 1 1 1 1 2 3 4 5 5 5 5 5 5 5 5 5 \r\n" + 
			"4 3 3 2 2 2 2 1 2 3 4 5 5 5 5 5 5 5 5 5 \r\n" + 
			"4 4 4 4 4 3 3 2 3 4 5 5 5 5 5 5 5 5 5 5 \r\n" + 
			"4 3 3 3 3 3 3 3 4 4 4 5 5 5 5 5 5 4 4 4 \r\n" + 
			"4 3 3 3 3 3 3 3 4 4 4 5 5 5 5 5 5 4 4 4 \r\n" + 
			"4 3 3 3 3 4 4 4 4 4 5 5 5 5 5 5 5 5 5 5 \r\n" + 
			"3 3 3 3 3 4 4 4 4 4 5 5 5 5 5 5 5 5 5 5 \r\n" + 
			"3 3 3 3 3 4 4 4 4 4 4 4 5 5 5 5 5 5 5 5 \r\n" + 
			"3 3 3 3 4 4 4 4 5 5 5 5 5 5 5 5 5 5 5 5 \r\n" + 
			"4 4 4 4 4 4 4 5 5 5 5 5 5 5 5 5 5 4 4 4 \r\n" + 
			"4 4 4 4 4 4 4 5 5 5 5 5 5 5 5 5 5 4 4 4 \r\n" + 
			"5 5 5 5 5 5 5 5 5 5 5 5 4 4 4 4 4 4 4 4 \r\n" + 
			"5 5 5 5 5 5 5 5 5 5 5 5 3 3 3 3 4 4 4 4 \r\n" + 
			"5 5 5 5 5 5 5 5 5 5 5 5 3 3 2 2 3 3 4 4 \r\n" + 
			"5 5 5 5 5 5 5 5 5 5 5 5 3 3 2 2 3 3 4 4 \r\n" + 
			"5 5 5 5 5 5 5 5 4 4 4 4 3 3 3 3 4 4 4 4";
}
