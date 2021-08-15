package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_1231 {
	static char[] node;
	static int[][] child;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("res/swea_1231.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int testcase=1; testcase<=10; testcase++) {
			int N = Integer.parseInt(br.readLine());
			
			node = new char[N+1];
			child = new int[N+1][2];
			
			for(int i=1; i<N+1; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				st.nextToken();
						
				node[i] = st.nextToken().charAt(0);	// 부모노드
				
				if(st.countTokens()==0) {	// 리프노드
					child[i][0] = -1;	// 빈 자식 노드 -1 처리 
					child[i][1] = -1;	// 빈 자식 노드 -1 처리 
				} else if(st.countTokens()==1) {	// 자식 노드가 하나면 왼쪽노드에 저장
					child[i][0] = Integer.parseInt(st.nextToken());
					child[i][1] = -1;	// 빈 자식 노드 -1 처리 
				} else {
					child[i][0] = Integer.parseInt(st.nextToken());	// 입력1, 왼쪽노드에 저장
					child[i][1] = Integer.parseInt(st.nextToken());	// 입력2, 오른쪽 노드에 저장
				}
			}
			System.out.print("#"+testcase+" ");
			InOrder(1);
			System.out.println();
		}
	}
	
	// 중위 순회 : 좌 -> 부모 -> 우
	public static void InOrder(int i) {
		
		if(child[i][0] == -1 && child[i][1] == -1) {	// 자식이 없는 경우, 리프노드인 경우
			System.out.print(node[i]);
		} else {	// 리프 노드가 아닐때  
			if(child[i][0] != -1) {	// 왼쪽 자식이 있으면 들어가기
				InOrder(child[i][0]);
			}
			System.out.print(node[i]);
			if(child[i][1] != -1) {	// 오른쪽 자식이 있으면 들어가기
				InOrder	(child[i][1]);
			}
		} 
	}
}
