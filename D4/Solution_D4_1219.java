package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_D4_1219 {
	private static int[] map1;
	private static int[] map2;
	private static int T, N;
	private static boolean isAvailable;
	
	public static void bfs(int curr) {
		Queue<Integer> queue = new LinkedList<Integer>();
		
		queue.offer(curr);	// queue에 출발점(0) 넣기
		
		while(!queue.isEmpty()) {	// queue에 값이 있다면 계속 반복
			curr = queue.poll();	// queue에서 하나씩 꺼내오기
			if(curr==99) {			// 도착점(99) 이라면
				return;				// bfs 탈출 
			}
			if(map1[curr]!=0) {		// 다음에 연결된 노드가 있다면
				queue.offer(map1[curr]);	// map1에 값 넣기
			}
			if(map2[curr]!=0) { 	// 다음에 연결된 노드가 있다면
				queue.offer(map2[curr]);	// map2에 값 넣기
			}
		}
		isAvailable = false;	// 도착점(99)에 도달하지 못했다면, false
	}
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/swea_1219.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int i=0; i<10; i++) {	// 10번의 테스트케이스만큼 반복
			st = new StringTokenizer(br.readLine());
			T = Integer.parseInt(st.nextToken());	// 테스트 케이스 수
			N = Integer.parseInt(st.nextToken());	// 순서쌍의 수
			map1 = new int[100];
			map2 = new int[100];
			isAvailable = true;		// 출발점에서 도착점까지 유효한 길이 있는지 체크
			
			// 순서쌍 입력 받아오기
			st = new StringTokenizer(br.readLine());
			int from, to;
			for(int j=0; j<N; j++) {	// 순서쌍 수만큼
				from = Integer.parseInt(st.nextToken());
				to = Integer.parseInt(st.nextToken());
				if(map1[from]==0) map1[from] = to;	// 이미 map1에 값이 들어왔다면
				else map2[from] = to;				// 해당 토큰 값 map2에 저장
			}
						
			bfs(0);
			System.out.println("#"+T+" "+(isAvailable?1:0));
		}
	}
}
