package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D4_1251 {

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/swea_1251.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int C = Integer.parseInt(br.readLine());
		
		for(int testcase=1; testcase<=C; testcase++) {
			int N = Integer.parseInt(br.readLine());
			int[] x = new int[N];	// x좌표 저장 배열
			int[] y = new int[N];	// y좌표 저장 배열
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			// x좌표 입력받기
			for(int i=0; i<N; i++) {
				x[i] = Integer.parseInt(st.nextToken());
			}
			// y좌표 입력받기
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				y[i] = Integer.parseInt(st.nextToken());
			}
			
			double E = Double.parseDouble(br.readLine());	// 환경 부담 세율 실수 E		
		
			boolean[] visited = new boolean[N];	// 방문여부 체크 배열
			double[] minEdge = new double[N];	// 정점까지 가는 최소 비용을 저장하는 배열
			double[][] adjMatrix = new double[N][N];	// 인접정보 및 비용 리스트
			
			for(int i=0; i<N; i++) {
				for(int j=i+1; j<N; j++) {
					// 두 정점 사이의 거리 구하기 (비용) : (x1 - x2)^2 + (y1 - y2)^2
					adjMatrix[i][j] = adjMatrix[j][i] = Math.pow(x[i]-x[j], 2)+Math.pow(y[i]-y[j], 2);
				}
				minEdge[i] = Double.MAX_VALUE;
			}
			
			double result = 0.0;	// 최소 신장 트리 비용
			minEdge[0] = 0.0;	// 임의의 시작점 0의 간선 비용을 0 으로 세팅
			
			for(int i=0; i<N; i++) {
				// 1. 신장트리에 포함되지 않은 정점 중 최소간선비용의 정점 찾기
				double min = Double.MAX_VALUE;	// 초기값으로 정수의 최대치를 주고 시작
				int minVertex = -1;		// 최소간선비용의 정점번호 (최소 값을 선택된 정점)
				
				for(int j=0; j<N; j++) {
					// 아직 선택하지 않은 정점중에 최소 값의 정점을 선택
					if(!visited[j] && min>minEdge[j]) {
						min = minEdge[j];
						minVertex = j;
					}
				}
				  
				visited[minVertex] = true;	// 신장트리에 포함시킴
				result += min;		// 간선 비용 누적
				
				// 2. 선택된 정점 기준으로 신장트리에 연결되지 않은 타 정점과의 간선 비용 최초로 업데이트
				for(int j=0; j<N; j++) {
					// 방문하지 않았고	   간선이 존재하고                                           기존의 가중치보다 현재 정점에서 가는 비용이 적다면
					if(!visited[j] && adjMatrix[minVertex][j]!=0.0 && minEdge[j] > adjMatrix[minVertex][j]) {
						minEdge[j] = adjMatrix[minVertex][j];	// 현재 정점에서의 비용으로 간선 비용 업데이트
					}
				}
			}			
			System.out.println("#"+testcase+" "+Math.round(E * result));
		}
	}
}
