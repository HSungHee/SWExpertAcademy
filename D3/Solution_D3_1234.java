package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution_D3_1234 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br = new BufferedReader(new StringReader(src));
		
		for(int testcase=1; testcase<=10; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			char[] password = st.nextToken().toCharArray();		
			Stack<Character> stack = new Stack<Character>();	// 결과 비밀번호를 담을 stack
			
			for(int i=0; i<N; i++) {
				// stack이 비어있지 않고 현재 비밀번호랑 stack의 top에 있는 비밀번호랑  같다면, 소거하기
				if(!stack.isEmpty() && stack.peek()==password[i]) {	
					stack.pop();
				} else {
					stack.push(password[i]);	// 아니면 그냥 stack에 넣기
				}
			}
			StringBuilder sb = new StringBuilder();
			while(!stack.isEmpty()) sb.append(stack.pop());	// 스택에서 꺼내서 Stringbuilder에 저장	
			System.out.println("#"+testcase+" "+sb.reverse().toString());	// 역순서로 출력
		}
	}
	
	static String src = "10 1238099084\r\n" + 
			"16 4100112380990844\r\n" + 
			"26 12380990844100112380990844\r\n" + 
			"42 123809908444100112380990844100112380990844\r\n" + 
			"55 1238099084441001123809908441001321238099084432180990844\r\n" + 
			"60 123809908444100145351123809908441001321238099084432180990844\r\n" + 
			"71 12380990844410013218099084441001123809908441001321238099084432180990844\r\n" + 
			"99 123809908444100180990844410013211238099084410013212380990844123809908441238099084410013232180990844\r\n" + 
			"82 1238099084441001410011238099084412380990844100132123809908441238099084432180990844\r\n" + 
			"58 0899809812380990844100132123809908441238099084432180990844";
}
