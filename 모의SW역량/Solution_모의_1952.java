package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 10
 * 10 40 100 300
 * 0 0 2 9 1 5 0 0 0 0 0 0
 * 
 *
 */
public class Solution_모의_0806 {
	static int[] fee;
	static int[] month;
	static int min;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int T = Integer.parseInt(br.readLine());
		
		for(int testcase=1; testcase<=T; testcase++) {
			fee = new int[4];
			month = new int[12];
			min = Integer.MAX_VALUE;
			
			String[] tmp = br.readLine().split(" ");
			for(int j=0; j<4; j++) {
				fee[j] = Integer.parseInt(tmp[j]);
			}
			
			tmp = br.readLine().split(" ");
			for(int j=0; j<12; j++) {
				month[j] = Integer.parseInt(tmp[j]);
			}
			swim(0, 0);
			
			if(min>fee[3]) {
				min = fee[3];
			}
			System.out.println("#"+testcase+" "+min);
		}
		
	}
	
	public static void swim(int idx, int sum) {
		if(idx>11) {
			// 기존의 요금보다 새로운 이용권 사용이 더 저렴하다면 갱신
			if(min>sum) {
				min = sum;
			}
			return;
		} 
		if(month[idx]==0) {
			swim(idx+1, sum);
		} else {
			// 1일권만 사용한다고 가정
			swim(idx+1, sum+month[idx]*fee[0]);
			// 1달권 사용금액
			swim(idx+1, sum+fee[1]);
			// 3달권 사용금액
			swim(idx+3, sum+fee[2]);
		}
	}
}


//재귀안에 처음에 1일권으로 다 밀면 입력 1같은 경우는
//20 90 10 50 이고 이후에 1달권 한달씩 넣어서 기존의 합이랑 비교해서적으면 갱신해주는걸로하면
//40 90 10 50 (x) / 20 40 10 50 (o) / 20 40 40 50 (x) / 20 40 10 40 (0)이고 이제 
//3달짜리 비교하면 100 40 (x) /  20 + 100 (x) 
//과정에서 계속 합 비교해서 작은걸로 갱신