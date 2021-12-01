package swea.D4;

import java.io.StringReader;
import java.util.Scanner;

public class Solution_D4_6026 {
	static final int MOD = 1000000007;
	static long[][] C;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc = new Scanner(new StringReader(src));
		
		int T = sc.nextInt();
		for(int testcase=1; testcase<=T; testcase++) {
			int M = sc.nextInt();	// 키보드의 키 수
			int N = sc.nextInt();	// 비밀번호 자리수
			
			C = new long[M+1][M+1];	// 조합 값들을 저장할 2차원 배열
			
			// 조합 값 생성 
			for(int i=1; i<=M; i++) {
				for(int j=1; j<=i; j++) {
					C[i][j] = combination(i, j) % MOD;					
				}
			}
			long answer = 0;	// 정답 저장을 위한 변수
			
			for(int i=0; i<=M; i++) {
				long flag = i%2==0?1:-1;
				long a = combination(M, i);
				long b = power(M-i, N);
				long result = ((flag * a) * b) % MOD;
				answer = (answer+result+MOD) % MOD;
			}
			
			System.out.println("#"+testcase+" "+answer%MOD);
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

	// 지수 승 구하기
	public static long power(long n, long r) {
		if(r==0) return 1;			// n^0 = 1
		if(r==1) return n;			// n^1 = n
		
		if(r%2==1) {	// 지수 r이 홀수라면
			return (n * power(n, r-1) % MOD) % MOD;	// n * n^(r-1)
		} else {	// 지수 r이 짝수라면
			long tmp = power(n, r/2) % MOD; 	
			return (tmp * tmp) % MOD;			// n^(r/2) * n^(r/2)
		}
	}
	
	static String src = "4\r\n" + 
			"1 1\r\n" + 
			"2 5\r\n" + 
			"3 3\r\n" +
			"7 11";
}
