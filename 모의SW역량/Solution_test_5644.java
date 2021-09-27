package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_test_5644 {

	public static class Battery {
		int x, y, capacity, power;

		public Battery(int y, int x, int capacity, int power) {
			this.x = x;
			this.y = y;
			this.capacity = capacity;
			this.power = power;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		br = new BufferedReader(new StringReader(src));
		
		int T = Integer.parseInt(br.readLine());	// 테스트케이스 수
		
		//				제자리	상	     우		 하	      좌			  
		int[][] delta = {{0,0},{-1,0},{0,1},{1,0},{0,-1}};
		StringBuilder sb = new StringBuilder();
		
		for(int testcase=1; testcase<=T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());	// 이동 시간
			int N = Integer.parseInt(st.nextToken());	// BC 개수
			int[] A = new int[M+1];
			int[] B = new int[M+1];
			
			Battery[] battery = new Battery[N];
			
			// 사용자 A의 이동 방향 정보 저장
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=M; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}
			
			// 사용자 B의 이동 방향 정보 저장
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=M; i++) {
				B[i] = Integer.parseInt(st.nextToken());				
			}

			// BC 정보 저장
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				battery[i] = new Battery(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1,
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}

			// 사용자 A의 출발점
			int ax = 0;
			int ay = 0;		
			
			// 사용자 B의 출발점
			int bx = 9;
			int by = 9;	
			
			int sum = 0;
			for(int i=0; i<=M; i++) {
				// 각 사용자별 이동 방향 정보에 따라 이동
				ax += delta[A[i]][0];
				ay += delta[A[i]][1];
				bx += delta[B[i]][0];
				by += delta[B[i]][1];
				
				int[] a_tmp = new int[N];
				int[] b_tmp = new int[N];
				
				// 이동 후, 사용자 A, 사용자 B 각각을 기준으로 배터리 들과의 사용 여부 판단
				for(int j=0; j<N; j++) {
					if(Manhattan(ax, ay, battery[j].x, battery[j].y, battery[j].capacity)) {	// 사용자 A가 배터리의 사용 범위안에 든다면
						a_tmp[j] = battery[j].power;	// 해당 배터리 용량 저장
					}
					if(Manhattan(bx, by, battery[j].x, battery[j].y, battery[j].capacity)) {	// 사용자 B가 배터리의 사용 범위안에 든다면
						b_tmp[j] = battery[j].power;	// 해당 배터리 용량 저장
					}
				}
				
				// 위에서 저장한 현재 위치에서의 사용자 A, 사용자 B의 각 배터리별 충전 정도를 비교하여 최대합 구하기
				int max = Integer.MIN_VALUE;
				for(int a=0; a<N; a++) {
					for(int b=0; b<N; b++) {
						if(a==b) max = Math.max(max, Math.max(a_tmp[a], b_tmp[b]));	// 동일 배터리 사용하는 경우
						else max = Math.max(max, a_tmp[a]+b_tmp[b]);				// 다른 배터리를 사용하는 경우			
					}
				}
				sum += max;		// 경우의 최대합을 누적
			}
			
			sb.append("#").append(testcase).append(" ").append(sum).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	// 맨하튼 거리 계산
	private static boolean Manhattan(int x1, int y1, int x2, int y2, int capacity) {
		return (Math.abs(x1-x2)+Math.abs(y1-y2) <= capacity);
	}
	
	static String src = "5\r\n" + 
			"20 3\r\n" + 
			"2 2 3 2 2 2 2 3 3 4 4 3 2 2 3 3 3 2 2 3\r\n" + 
			"4 4 1 4 4 1 4 4 1 1 1 4 1 4 3 3 3 3 3 3\r\n" + 
			"4 4 1 100\r\n" + 
			"7 10 3 40\r\n" + 
			"6 3 2 70\r\n"+
			"40 2\r\n" + 
			"0 3 0 3 3 2 2 1 0 4 1 3 3 3 0 3 4 1 1 3 2 2 2 2 2 0 2 3 2 2 3 4 4 3 3 3 2 0 4 4 \r\n" + 
			"0 1 0 3 4 0 4 0 0 1 1 1 0 1 4 4 4 4 4 3 3 3 0 1 0 4 3 2 1 4 4 3 2 3 2 2 0 4 2 1 \r\n" + 
			"5 2 4 140\r\n" + 
			"8 3 3 490\r\n" + 
			"60 4\r\n" + 
			"0 3 3 3 0 1 2 2 2 1 2 2 3 3 4 4 0 3 0 1 1 2 2 3 2 2 3 2 2 0 3 0 1 1 1 4 1 2 3 3 3 3 3 1 1 4 3 2 0 4 4 4 3 4 0 3 3 0 3 4 \r\n" + 
			"1 1 4 1 1 1 1 1 1 4 4 1 2 2 3 2 4 0 0 0 4 3 3 4 3 3 0 1 0 4 3 0 4 3 2 3 2 1 2 2 3 4 0 2 2 1 0 0 1 3 3 1 4 4 3 0 1 1 1 1 \r\n" + 
			"6 9 1 180\r\n" + 
			"9 3 4 260\r\n" + 
			"1 4 1 500\r\n" + 
			"1 3 1 230\r\n" + 
			"80 7\r\n" + 
			"2 2 2 2 2 2 0 2 2 0 4 0 2 3 3 2 3 3 0 3 3 3 4 3 3 2 1 1 1 0 4 4 4 1 0 2 2 2 1 1 4 1 2 3 4 4 3 0 1 1 0 3 4 0 1 2 2 2 1 1 3 4 4 4 4 4 4 3 2 1 4 4 4 4 3 3 3 0 3 3 \r\n" + 
			"4 4 1 1 2 1 2 3 3 3 4 4 4 4 4 1 1 1 1 1 1 1 1 0 3 3 2 0 4 0 1 3 3 3 2 2 1 0 3 2 3 4 1 0 1 2 2 3 2 0 4 0 3 4 1 1 0 0 3 2 0 0 4 3 3 4 0 4 4 4 4 0 3 0 1 1 4 4 3 0 \r\n" + 
			"4 3 1 170\r\n" + 
			"10 1 3 240\r\n" + 
			"10 5 3 360\r\n" + 
			"10 9 3 350\r\n" + 
			"9 6 2 10\r\n" + 
			"5 1 4 350\r\n" + 
			"1 8 2 450\r\n" + 
			"100 8\r\n" + 
			"2 2 3 2 0 2 0 3 3 1 2 2 2 2 3 3 0 4 4 3 2 3 4 3 3 2 3 4 4 4 2 2 2 0 2 2 4 4 4 4 1 1 1 2 2 2 4 3 0 2 3 3 4 0 0 1 1 4 1 1 1 1 2 2 1 1 3 3 3 0 3 2 3 3 0 1 3 3 0 1 1 3 3 4 0 4 1 1 2 2 4 0 4 1 1 2 2 1 1 1 \r\n" + 
			"4 4 4 0 4 1 1 4 1 1 1 1 3 2 1 2 1 1 4 4 1 0 2 3 4 4 4 4 4 0 1 0 2 2 2 0 2 2 2 2 2 2 3 0 0 1 4 3 3 2 0 0 4 4 4 0 2 0 4 1 1 2 2 0 4 4 0 0 2 0 2 3 3 0 2 3 0 3 4 0 4 3 4 4 3 4 1 1 2 2 2 0 0 1 0 4 1 1 1 4 \r\n" + 
			"3 4 2 340\r\n" + 
			"10 1 1 430\r\n" + 
			"3 10 4 10\r\n" + 
			"6 3 4 400\r\n" + 
			"7 4 1 80\r\n" + 
			"4 5 1 420\r\n" + 
			"4 1 2 350\r\n" + 
			"8 4 4 300";
}