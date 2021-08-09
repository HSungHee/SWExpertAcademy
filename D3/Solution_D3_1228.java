package swea;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Solution_D3_1228 {

	public static void main(String[] args) throws NumberFormatException, IOException {
//		System.setIn(new FileInputStream("res/1228.txt"));
		Scanner sc = new Scanner(System.in);
			
		for(int testcase=1; testcase<=10; testcase++) {
			int N = sc.nextInt();	// 원본 암호문의 길이 N
			LinkedList<Integer> list = new LinkedList<Integer>();
			
			for(int i=0; i<N; i++) {
				list.add(sc.nextInt());
			}
			
			int M = sc.nextInt();	// 명령어 개수
			while(M-- > 0) {
				String cmd = sc.next(); 	// 명령어 종류
				int start = sc.nextInt();	// 추가 위치
				int num = sc.nextInt();		// 추가 개수
				
				for(int j=0; j<num; j++) {
					list.add(start, sc.nextInt());
					start++;
				}
			}
			System.out.print("#"+testcase);
			for(int i=0; i<10; i++) {		// 처음 10개만 출력
				System.out.print(" " + list.pollFirst());
			}
			System.out.println();
			list.clear();
		}
	}
}
 