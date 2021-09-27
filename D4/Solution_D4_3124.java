package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D4_3124 {
	
	static int V, E;
	static Edge[] edgeList;
	static int[] parents;  // 부모원소를 관리(트리처럼 사용)
	
	static class Edge implements Comparable<Edge> {
		int start, end, weight;

		public Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
//			return this.weight - o.weight;	// 간선의 부호가 모두 같을때
			return Integer.compare(this.weight, o.weight);
		}		
	}
	
	private static void makeSet() {
		parents = new int[V+1];
		// 모든 원소를 자신을 대표자로 만듦
		for(int i=1; i<=V; i++) {
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
											// 이미 연결한 것이라 cycle이 생길 수 있는 상황
		
		parents[bRoot] = aRoot;
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		br = new BufferedReader(new StringReader(src));
		int T = Integer.parseInt(br.readLine());
		
		for(int testcase=1; testcase<=T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			// 간선리스트 작성
			edgeList = new Edge[E];
			
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				edgeList[i] = new Edge(start, end, weight);
			}
			
			Arrays.sort(edgeList);	// 오름차순 정렬
	
			makeSet();		// 모든 정점을 각각의 집합으로 만들고 출발
						
			// 간선 하나씩 시도하며 트리 만들어 감.
			int cnt = 0;
			long result = 0;
			for(Edge edge : edgeList) {
				if(union(edge.start, edge.end)) {
					result += edge.weight;	// 가중치 누적
					if(++cnt == V-1) break;	// 신장트리 완성
				}
			}
    		System.out.println("#" + testcase + " " + result);
		}		
	}
	static String src = "1\r\n" + 
			"3 3\r\n" + 
			"1 2 1\r\n" + 
			"2 3 2\r\n" + 
			"1 3 3";
}
