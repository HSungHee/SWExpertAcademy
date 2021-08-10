package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_D4_1233 {
	public static void main(String[] args) throws NumberFormatException, IOException {
//		System.setIn(new FileInputStream("res/1233.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testcase=0;
		boolean isVaild;
		
		while(testcase++ < 10) {
			isVaild = true;
			int N = Integer.parseInt(br.readLine());
			for(int i=0; i<N; i++) {
				String[] cmd = br.readLine().split(" ");
				// 길이가 4 : 번호 연산자 피연산자 피연산자
				// 길이가 3 : 번호 피연산자 피연산자
				// 길이가 2 : 번호 피연산자
				// 즉, 2번째자리에 연산자가 나왔을때 길이가 4가아니면 안된다.
				// 또한 마지막 자리는 무조건 피연산자
				
				// 1. 2번째자리에 연산자가 나왔다면, 길이가 4인치 체크
				if((cmd[1].equals("+") ||cmd[1].equals("-") ||cmd[1].equals("*") ||cmd[1].equals("/")) && cmd.length!=4) isVaild=false;
				
				// 2. 길이가 4일때, 3번째자리가 피연산자인지 체크
				if(cmd.length==4) {
					if(cmd[2].equals("+") ||cmd[2].equals("-") ||cmd[2].equals("*") ||cmd[2].equals("/")) isVaild=false;
				}
				
				// 3. 마지막 자리가 피연산자인지 체크
				int last = cmd.length-1;
				if(cmd[last].equals("+") ||cmd[last].equals("-") ||cmd[last].equals("*") ||cmd[last].equals("/")) isVaild=false;
			}
			System.out.println("#"+testcase+" "+(isVaild?1:0));
		}
	}
}