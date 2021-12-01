package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/** 1. 중복 부분집합
 *  2. 
 */
public class Solution_test_2112 {
	static int D, W, K, value[], map[][], backup[][], answer;
	static ArrayList<Integer> index;
	static boolean isSelected[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		StringBuilder sb = new StringBuilder();		// 출력을 위한 StringBuilder

		int T = Integer.parseInt(br.readLine());	// 테스트케이스 수
		
		StringTokenizer st = null;
		for(int testcase=1; testcase<=T; testcase++) {
			st = new StringTokenizer(br.readLine());
			
			D = Integer.parseInt(st.nextToken());	// 두께
			W = Integer.parseInt(st.nextToken());	// 가로크기
			K = Integer.parseInt(st.nextToken());	// 합격기준
			
			map = new int[D][W];	// 필름 단면 정보 저장을위한 2차원 배열
			backup = new int[D][W];	// 백업용 
			
			index = new ArrayList<Integer>();	// 부분집합의 경우의 수를 담을 배열
			isSelected = new boolean[D+1];	// 부분집합 생성 시, 사용 여부 판단 배열
			
			// 필름 단면 정보 입력
			for(int i=0; i<D; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++) {
					map[i][j] = backup[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			answer = Integer.MAX_VALUE;	// 정답이될 최소 투입 횟수
			
			// 부분집합 생성
			subset(0);
			
			sb.append("#").append(testcase).append(" ").append(answer).append("\n");
		}		
		System.out.println(sb);
	}
	private static void subset(int cnt) {
		if(cnt==D) {
			for(int i=0; i<D; i++) {
				if(isSelected[i]) index.add(i);
			}
			if(index.size()<answer)	binary(index.size());
			index.clear();
			return;
		}
		
		// 현재 원소를 부분집합에 넣기
		isSelected[cnt] = true;
		subset(cnt+1);
		// 현재 원소를 부분집합에 넣지 않기
		isSelected[cnt] = false;
		subset(cnt+1);
		
	}
	
	// 부분집합의 길이에 맞춰 가능한 AB 조합을 2진수형태로 구하기
	private static void binary(int size) {
		int end = (int) Math.pow(2, size);
	
		for(int i=0; i<end; i++) {
			value = new int[size];
			String s = Integer.toBinaryString(i);
			for(int j=s.length()-1, k=size-1; j>-1; j--) {
				if(k>-1) value[k--] = s.charAt(j)-'0';
			}
//			System.out.println(Arrays.toString(value));
			injection(value);
		}
	}

	private static void injection(int[] value) {
		// 필름의 index자리에 value값으로 투입
		for(int i=0, end=value.length; i<end; i++) {
			for(int c=0; c<W; c++) {
				map[index.get(i)][c] = value[i];
			}
		}
	
		// 성능검사를 통과할 수 있는지 test
		boolean pass = false;
		for(int c=0; c<W; c++) {
			int cnt = 1;
			pass = false;
			for(int r=1; r<D; r++) {
				if(map[r][c]==map[r-1][c]) cnt++;
				else cnt=1;
				if(cnt==K) {
					pass = true;
					break;
				}
			}
			if(!pass) break;	// 성능검사를 통과하지 않은 열이 발생
		}	
		
		if(pass) answer = Math.min(answer, value.length);
		reset();
	}

	private static void reset() {
		for(int i=0; i<D; i++) {
			map[i] = backup[i].clone();
		}
	}

	static String src = "10\r\n" + 
			"6 8 3\r\n" + 
			"0 0 1 0 1 0 0 1\r\n" + 
			"0 1 0 0 0 1 1 1\r\n" + 
			"0 1 1 1 0 0 0 0\r\n" + 
			"1 1 1 1 0 0 0 1\r\n" + 
			"0 1 1 0 1 0 0 1\r\n" + 
			"1 0 1 0 1 1 0 1\r\n" +
			"6 8 3\r\n" + 
			"1 1 1 1 0 0 1 0\r\n" + 
			"0 0 1 1 0 1 0 1\r\n" + 
			"1 1 1 1 0 0 1 0\r\n" + 
			"1 1 1 0 0 1 1 0\r\n" + 
			"1 1 0 1 1 1 1 0\r\n" + 
			"1 1 1 0 0 1 1 0\r\n" + 
			"6 8 4\r\n" + 
			"1 1 0 0 0 1 1 0\r\n" + 
			"1 0 1 0 0 1 1 1\r\n" + 
			"0 1 0 0 1 1 0 0\r\n" + 
			"1 0 1 0 0 0 0 0\r\n" + 
			"1 1 0 0 0 0 0 0\r\n" + 
			"1 0 0 0 1 1 1 1\r\n" + 
			"6 4 4\r\n" + 
			"1 1 0 0\r\n" + 
			"0 1 0 1\r\n" + 
			"0 0 0 1\r\n" + 
			"1 1 1 1\r\n" + 
			"1 1 0 1\r\n" + 
			"1 0 1 0\r\n" + 
			"6 10 3\r\n" + 
			"0 1 0 0 0 1 0 0 1 1\r\n" + 
			"0 1 1 0 0 1 0 0 1 0\r\n" + 
			"0 1 0 0 1 0 1 1 1 1\r\n" + 
			"0 0 0 0 0 1 1 1 1 0\r\n" + 
			"0 1 0 0 1 1 1 1 1 1\r\n" + 
			"1 0 0 0 1 1 0 0 1 1\r\n" + 
			"6 6 5\r\n" + 
			"0 0 0 0 0 0\r\n" + 
			"0 0 0 0 0 0\r\n" + 
			"0 0 0 0 0 0\r\n" + 
			"0 0 0 0 0 0\r\n" + 
			"0 0 0 0 0 0\r\n" + 
			"0 0 0 0 0 0\r\n" + 
			"6 6 4\r\n" + 
			"1 1 1 1 1 1\r\n" + 
			"0 0 0 0 0 1\r\n" + 
			"0 1 1 1 0 1\r\n" + 
			"0 1 0 1 0 1\r\n" + 
			"0 1 0 0 0 1\r\n" + 
			"0 1 1 1 1 1\r\n" + 
			"8 15 3\r\n" + 
			"0 1 1 0 0 1 1 0 1 1 0 0 0 0 0\r\n" + 
			"1 0 0 0 1 1 0 0 0 0 0 1 0 1 1\r\n" + 
			"1 1 0 1 0 1 0 1 0 1 0 1 0 0 0\r\n" + 
			"0 1 1 1 0 0 1 0 0 0 0 1 0 0 0\r\n" + 
			"0 0 0 0 0 0 1 0 0 0 1 1 0 0 1\r\n" + 
			"1 0 1 0 0 1 0 1 1 1 1 0 1 1 1\r\n" + 
			"0 0 0 0 0 1 1 1 0 0 0 0 0 1 0\r\n" + 
			"0 0 1 0 1 1 0 1 1 0 0 0 1 0 0\r\n" + 
			"10 20 4\r\n" + 
			"1 0 1 1 1 1 1 1 1 1 0 0 1 1 1 0 1 1 0 1\r\n" + 
			"1 1 0 1 1 1 0 0 1 0 0 0 1 1 1 1 0 0 1 0\r\n" + 
			"1 1 0 1 1 0 0 0 1 1 1 1 1 0 0 1 1 0 1 0\r\n" + 
			"0 0 0 1 1 0 0 0 0 1 0 0 1 0 1 1 1 0 1 0\r\n" + 
			"0 1 1 0 1 0 1 0 1 0 0 1 0 0 0 0 1 1 1 1\r\n" + 
			"1 0 1 0 1 0 1 1 0 0 0 0 1 1 1 0 0 0 0 0\r\n" + 
			"0 1 0 0 1 1 0 0 0 0 0 1 1 0 0 1 1 0 1 1\r\n" + 
			"1 0 0 0 0 1 0 1 1 0 1 1 0 1 0 0 1 1 1 0\r\n" + 
			"0 1 1 0 0 1 0 1 0 0 0 0 0 0 0 1 1 1 0 1\r\n" + 
			"0 0 0 0 0 0 1 1 0 0 1 1 0 0 0 0 0 0 1 0\r\n" + 
			"13 20 5\r\n" + 
			"1 1 0 1 0 0 0 1 1 1 1 0 0 0 1 1 1 0 0 0\r\n" + 
			"1 1 1 1 0 1 0 1 0 0 0 0 1 0 0 0 0 1 0 0\r\n" + 
			"1 0 1 0 1 1 0 1 0 1 1 0 0 0 0 1 1 0 1 0\r\n" + 
			"0 0 1 1 0 1 1 0 1 0 0 1 1 0 0 0 1 1 1 1\r\n" + 
			"0 0 1 0 0 1 0 0 1 0 0 0 0 1 0 0 0 0 1 1\r\n" + 
			"0 0 1 0 0 0 0 0 0 0 0 0 1 1 1 0 0 1 0 1\r\n" + 
			"0 0 0 1 0 0 0 0 0 0 1 1 0 0 0 1 0 0 1 0\r\n" + 
			"1 1 1 0 0 0 1 0 0 1 1 1 0 1 0 1 0 0 1 1\r\n" + 
			"0 1 1 1 1 0 0 0 1 1 0 1 0 0 0 0 1 0 0 1\r\n" + 
			"0 0 0 0 1 0 1 0 0 0 1 0 0 0 0 1 1 1 1 1\r\n" + 
			"0 1 0 0 1 1 0 0 1 0 0 0 0 1 0 1 0 0 1 0\r\n" + 
			"0 0 1 1 0 0 1 0 0 0 1 0 1 1 0 1 1 1 0 0\r\n" + 
			"0 0 0 1 0 0 1 0 0 0 1 0 1 1 0 0 1 0 1 0\r\n";
}
