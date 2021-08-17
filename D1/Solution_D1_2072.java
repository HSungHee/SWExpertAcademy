package swea;

import java.util.Scanner;

public class Solution_D1_2072 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int n;
		
		for(int testcase=1; testcase<=T; testcase++) {
			int sum=0;
			for(int i=0; i<10; i++) {
				n = sc.nextInt();
				if(n%2==1) {
					sum+=n;
				}
			}
			System.out.println("#"+testcase+" "+sum);
		}
	}
}
