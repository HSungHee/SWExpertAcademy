package swea;

import java.io.StringReader;
import java.util.Scanner;

/**
 * a^p = a (mod p)
 * a^(p-1) = 1 (mod p)
 * a^(p-2) = a^(-1) (mod p)
 * ...
 * 
 * nCr = n! / r! * (n-r)! = n! * (r! * (n-r)!)^(-1)
 * 	 ->	n! * (r! * (n-r)!)^(-1) (mod 1234567891)
 * 		= n! * (r! * (n-r)!)^(1234567891-2) (mod 1234567891)		
 * 
 * 
 * 
 */

public class Solution_D3_5607_한성희 {
	static int MOD = 1234567891;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//		sc = new Scanner(new StringReader(src));
		
		int T = sc.nextInt();
		for(int testcase=1; testcase<=T; testcase++) {
			int N = sc.nextInt();	
			int R = sc.nextInt();	
			
			System.out.println("#"+testcase+" "+combination(N, R));
		}		
	}
	
	private static long combination(int n, int r) {
		// nC0 = 1
		if(r==0) {
			return 1;
		} 
	
		long[] factorial = new long[n+1];
		factorial[0] = 1;
	
		// 팩토리얼 값 생성
		for(int i=1; i<=n; i++) {
			factorial[i] = (factorial[i-1] * i) % MOD;
		}
		
		//n! * (r! * (n-r)!)^(1234567891-2) (mod 1234567891)
		return (factorial[n] * power((factorial[r] * factorial[n-r]) % MOD, MOD-2)) % MOD;
	} 

	public static long power(long n, long r) {
		if(r==0) return 1;		
		if(r==1) return n;
		
		if(r%2==1) {	// 지수 r이 홀수라면
			return (n * power(n, r-1) % MOD) % MOD;	// n * n^(r-1)
		} else {	// 지수 r이 짝수라면
			long tmp = power(n, r/2) % MOD; 	
			return (tmp * tmp) % MOD;			// n^(r/2) * n^(r/2)
		}
	}

	static String src = "5\r\n" + 
			"10 2\r\n" +
			"5 5\r\n" +
			"1000000 7\r\n" +
			"1 0\r\n" +
			"90 78\r\n";
}
