package swea;

import java.util.Scanner;
import java.util.Stack;

public class Solution_D2_1859 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		Stack<Integer> stack = new Stack<Integer>();
		
		for(int testcase=1; testcase<=T; testcase++) {
			int N = sc.nextInt();
			long sum=0;			// <중요> long 타입, int로 하니 10개중 7개 pass
			
			for(int i=0; i<N; i++) {
				stack.push(sc.nextInt());
			}
			int tmp;
			
			while(true) {
				tmp = stack.pop();		// stack에서 하나 꺼내기
				if(stack.isEmpty()) {
					break;
				}
				if(stack.peek()<tmp) {	// 스택의 top의 값이 꺼낸 값보다 작다면
					sum += tmp-stack.pop();	// 차이만큼 누적	
					stack.push(tmp);	// 둘 중 큰 수 다시 stack에 넣기
				}
			}
			stack.clear();
			System.out.println("#" + testcase + " " + sum);
		}
	}
}
