package swea;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution_D3_1225 {

	public static void main(String[] args) throws NumberFormatException, IOException {
//		System.setIn(new FileInputStream("res/encryption.txt"));
		Scanner sc = new Scanner(System.in);
		Queue<Integer> queue = new LinkedList<Integer>();
		
		for(int n=0; n<10; n++) {
			int testcase = sc.nextInt();
			int minus = 1;
			queue.clear();
			
			for(int i=0; i<8; i++) {
				queue.offer(sc.nextInt());
			}
			
			int tmp;	// 암호화할 값 임시 저장 변수
			while(true) {			
				if(minus==6) minus=1;	// 1~5가 감소시킬 숫자 사이클
				
				// queue 맨 앞 숫자 뽑아서 감소
				tmp = queue.poll();		
				tmp = tmp-minus++;		
				
				// 만약 감소한 값이 0이면 queue 맨 뒤에 0 추가 후, 반복문 탈출
				if(tmp<=0) {
					queue.offer(0);
					break;
				}
				// 반복문 탈출하지 않았다면 감소한 결과 값 queue 맨 뒤에 추가
				queue.offer(tmp);
			}
			
			// 결과 출력
			System.out.print("#"+testcase);
			while(!queue.isEmpty()) {
				System.out.print(" "+queue.poll());
			}
			System.out.println();
		}
	}
}

		