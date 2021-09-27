package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D4_7465 {
	static int N; 	// 사람 수
	static int[] parents;  // 부모원소를 관리(트리처럼 사용)
		
	private static void makeSet() {
		// 모든 원소를 자신을 대표자로 만듦
		for(int i=0; i<N; i++) {
			parents[i] = i;
		}
	}
	// curr가 속한 집합의 대표자 찾기
	private static int find(int curr) {
		if(curr==parents[curr]) return curr; 	// 자신이 자신의 대표자인 경우
		
		return parents[curr] = find(parents[curr]);	// 자신이 속한 집합의 대표자를 자신의 부모로 : pass compression
	}
	
	// 두 원소를 하나의 집합으로 합치기(대표자를 이용해서 합침)
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;	// 이미 같은 대표자(같은 집합)를 가진다면 합치지 않음
		
		parents[bRoot] = aRoot;
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		br = new BufferedReader(new StringReader(src));

		int T = Integer.parseInt(br.readLine());
		
		for(int testcase=1; testcase<=T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	
			int M = Integer.parseInt(st.nextToken());	// 관계 수
			int cnt = 1;		// 무리 개수
			
			parents = new int[N];
			
			makeSet();
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				union(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);
			}
			
			for(int i=0; i<N; i++) {
				if(parents[i]!=parents[parents[i]])	{	// 자신이 자신의 대표자가 아닌경우
					parents[i] = find(parents[parents[i]]);	// 자신이 속한 집합의 대표자를 자신의 부모로 : pass compression
				}
			}
			
			Arrays.sort(parents);	// 오름차순 정렬 후
			for(int i=1; i<parents.length; i++) {	// index 1부터 탐색하며 
				if(parents[i]!=parents[i-1]) cnt++;	// 이전 값과 다르면 cnt + 1
			}
			
			System.out.println("#"+testcase+" "+cnt);
		}
	}
//	static String src = "2\r\n" + 
//			"6 5\r\n" + 
//			"1 2\r\n" + 
//			"2 5\r\n" + 
//			"5 1\r\n" + 
//			"3 4\r\n" + 
//			"4 6\r\n" + 
//			"6 8\r\n" + 
//			"1 2\r\n" + 
//			"2 5\r\n" + 
//			"5 1\r\n" + 
//			"3 4\r\n" + 
//			"4 6\r\n" + 
//			"5 4\r\n" + 
//			"2 4\r\n" + 
//			"2 3";
}
