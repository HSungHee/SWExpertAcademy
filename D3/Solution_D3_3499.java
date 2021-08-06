package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_D3_3499 {

	public static void main(String[] args) throws NumberFormatException, IOException {
//		System.setIn(new FileInputStream("res/3499.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		String[] inputs;								// 입력받을 String 배열
		Queue<String> queue = new LinkedList<String>();	// 결과를 저장할 queue
		
		for(int testcase=1; testcase<=T; testcase++) {
			int N = Integer.parseInt(br.readLine());
			inputs = br.readLine().split(" ");
			
			int half = N%2==0 ? N/2 : N/2+1;		// 짝수, 홀수에 따른 half지점 구하기
			
			for(int i=0; i<half; i++) {
				queue.offer(inputs[i]);			// 앞쪽부터 1개씩 queue에 저장
				if(N%2==1 && i==half-1) {		// 입력이 홀수개이고 마지막 반복 사이클이라면, 값을 넣지 않고 반복문 탈출
					break;
				}
				queue.offer(inputs[half+i]);	// 입력 길이의 half지점부터 1개씩 queue저장
			}
			
			// 결과 출력
			System.out.print("#"+testcase);
			while(!queue.isEmpty()) {
				System.out.print(" " + queue.poll());
			}
			System.out.println();
		}
	}
}

