package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D4_3234 {
	private static int answer;		// 총 경우의 수
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/swea_3234.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());		// 테스트 케이스 수
		for(int testcase=1; testcase<=T; testcase++) {
			int N = Integer.parseInt(br.readLine());
			int[] chu = new int[N];	// 무게추들의 무게를 담는 배열
			boolean[] used = new boolean[N];	// 사용 여부 체크
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				chu[i] = Integer.parseInt(st.nextToken());
			}
			answer = 0;
			WeightCheck(chu, used, 0, 0, 0);
			System.out.println("#"+testcase+ " "+answer);
		}
	}
	
	private static void WeightCheck(int[] chu, boolean[] used, int cnt, int leftSum, int rightSum) {
		if(leftSum < rightSum) return;		// 오른쪽이 더 무거우면 return
		if(cnt==chu.length) {
			answer++;
			return;
		}	
		
		for(int i=0; i<chu.length; i++) {
			if(used[i]) continue;
			used[i] = true;
			WeightCheck(chu, used, cnt+1, leftSum+chu[i], rightSum);
			WeightCheck(chu, used, cnt+1, leftSum, rightSum+chu[i]);
			used[i] = false;
		}
	}

}
