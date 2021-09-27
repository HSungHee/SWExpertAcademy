package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution_test_4012 {
	private static int N;			// 총 재료 수
	private static int[][] S;		// 시너지 정보 2차원배열
	private static int[] a;			// a 손님의 재료 조합
	private static int[] b;			// b 손님의 재료 조합
	private static boolean[] used;	// 재료 사용 여부
	private static int answer;
	
	private static void combination(int n, int r) {
		if(r==0) {
			int idx = 0;
			for(int i=0; i<N; i++) {
				if(!used[i]) b[idx++] = i;
				if(idx==N/2) break;
			}
			
			diff(a, b);
			return;
		}
		if(n<r) return;
		
		a[r-1] = n-1;
		used[n-1] = true;
		combination(n-1, r-1);	// 선택
		used[n-1] = false;
		combination(n-1, r);	// 비선택
	}

	private static void diff(int[] a, int[] b) {
		int sum_a = 0;
		int sum_b = 0;
		for(int i=0; i<N/2; i++) {
			for(int j=0; j<N/2; j++) {
				sum_a += S[a[i]][a[j]];
				sum_b += S[b[i]][b[j]];
			}
		}
		answer = Math.min(answer, Math.abs(sum_a-sum_b));
	}

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/swea_4012.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());	// 테스트 케이스 수
		
		for(int testcase=1; testcase<=T; testcase++) {
			N = Integer.parseInt(br.readLine());	// 재료 수 
			
			S = new int[N][N];
			a = new int[N/2];
			b = new int[N/2];
			used = new boolean[N];
			answer = Integer.MAX_VALUE;
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					S[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			combination(N, N/2);
			System.out.println("#"+testcase+" "+answer);
		}
	}
	
}
