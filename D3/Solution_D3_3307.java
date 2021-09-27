package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D3_3307 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int testcase=1; testcase<=T; testcase++) {
			int N = Integer.parseInt(br.readLine());
			
			int[] numbers = new int[N];
			int[] LIS = new int[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<N; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}			
			
			int max = 0;
			for(int i=0; i<N; i++) {
				LIS[i] = 1;
				for(int j=0; j<i; j++) {
					if(numbers[j] < numbers[i] && LIS[i] < LIS[j]+1) {
						LIS[i] = LIS[j]+1;
					}
				}
				if(max < LIS[i]) max = LIS[i];
			}
			sb.append("#").append(testcase).append(" ").append(max).append("\n");
		}
		System.out.println(sb.toString());
	}
	static String src = "2\r\n" + 
			"5\r\n" + 
			"1 3 2 5 4\r\n" + 
			"6\r\n" + 
			"4 2 3 1 5 6";
}
