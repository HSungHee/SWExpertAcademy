package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution_D4_1223 {
	private static char[] node;
	private static char[][] child;
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/swea_1223.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int testcase=1; testcase<=10; testcase++) {
			int N = Integer.parseInt(br.readLine());
			String result = "";
			Stack<Character> stack = new Stack<Character>();		// 연산자 저장 stack
			char[] inputs = br.readLine().toCharArray();	
			
			// 후위 표기식으로 만들기
			for(int i=0; i<N; i++) {
				if(inputs[i] >= 48 && inputs[i] <= 57) {	// 입력이 숫자라면
					result += inputs[i];		// 바로 String에 저장
				} else {					
					if(inputs[i]==43 				// 현재 입력이 + 이고
						&& !stack.isEmpty() 		// operator 스택이 비어있지않고
						&& stack.peek()==42) {	// 스택의 top에 *가 있다면
						while(!stack.isEmpty()) {// stack이 비어있을때까지
							result+=stack.pop();	// pop()해서 String에 저장
						}
					}
					stack.push(inputs[i]);		// 현재 +를 다시 연산자 stack에 저장
				}
			}
			// 수행후 나머지 연산자들도 String에 저장
			while(!stack.isEmpty()) {
				result+=stack.pop();
			}

			Stack<Integer> numbers = new Stack<Integer>();		// 숫자 저장 스택
			// 후위 표기식 계산하기
			for(int i=0; i<N; i++) {
				if(result.charAt(i) >= 48 && result.charAt(i) <= 57) {	// 입력이 숫자라면
					numbers.push(result.charAt(i)-'0');
				} else {
					// 스택에서 연산자 2개 빼서 현재 연산자로 계산하기
					int operand1 = numbers.pop();
					int operand2 = numbers.pop();
					if(result.charAt(i)==43) {	// 현재 + 연산자라면
						numbers.push(operand1+operand2);	// 연산 결과 저장
					} else {	// * 인 경우
						numbers.push(operand1*operand2);	// 연산 결과 저장
					}
				}
			}
			// 결과 출력
			System.out.println("#" + testcase + " " + numbers.pop());
		}
	}
}
