package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * 1. 쪼개기
 * 2. 곱하기
 * 3. 곱해서 2자리 수 이상이라면 1~2 반복
 */
public class Solution_D5_7206 {
	static int Len;
	static boolean[] isSelected;
	static int[] d;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		
		int T = Integer.parseInt(br.readLine());	// 테스트케이스 수
		
		d = new int[100000];	// 메모이제이션
		
		// 메모이제이션 배열 init
		for(int i=10; i<100000; i++) {
			String str = Integer.toString(i);	
			Len = str.length()-1;
			
			isSelected = new boolean[Len+1];
			subset(str, 0);
		}	
	
		for(int testcase=1; testcase<=T; testcase++) {
			int input = Integer.parseInt(br.readLine());	// 입력 정수
			
			System.out.println("#"+testcase+" "+d[input]);
		}
	}
	
	private static void subset(String str, int cnt) {
		if(cnt==Len) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			for(int i=0; i<=Len; i++) {
				if(isSelected[i]) {
					list.add(i+1);
				}
			}
			if(list.size()>0) {	// 쪼갤 위치가 존재하는 경우
				int result = split(str, list);
				int N = Integer.parseInt(str);
				d[N] = Math.max(d[N], d[result]+1);
				list.clear();
			}
			return;
		}
		
		// 현재 원소를 부분집합에 넣기
		isSelected[cnt] = true;
		subset(str, cnt+1);
		// 현재 원소를 부분집합에 넣지 않기
		isSelected[cnt] = false;
		subset(str, cnt+1);
		
	}
	
	private static int split(String str, ArrayList<Integer> list) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		int start = 0;	
		int end = list.get(0);	// 처음 쪼갤 위치
		for(int i=0; i<list.size(); i++) {
			result.add(Integer.parseInt(str.substring(start, end)));	// 쪼개
			start = list.get(i);
			if(i+1 < list.size()) end = list.get(i+1);
		}
		result.add(Integer.parseInt(str.substring(end, str.length())));	// 마지막 끝 덩어리 넣어주기
		
		// 쪼갠 값들을 곱한 총 합 구하기
		int total = 1;
		for(int i=0; i<result.size(); i++) {
			total *= result.get(i);
		}
		return total;
	}

	static String src = "5\r\n" + 
			"6\r\n" + 
			"79\r\n" + 
			"123\r\n" + 
			"423\r\n" + 
			"1089";
}
