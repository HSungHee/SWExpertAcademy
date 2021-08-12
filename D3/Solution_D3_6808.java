package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D3_6808 {
	static int[] card1;	// 규영이 카드 숫자 정보
	static int[] card2;	// 인영이의 카드 정보
	static int win;		// 규영이가 이긴 횟수
	static int lose;	// 규영이가 진 횟수
	static int[] numbers;	// 순열 저장 배열
	static boolean[] isSelected;	// 탐색 여부 체크 배열
	
	public static void permutation(int cnt) {
		if(cnt==9) {	// 새로운 경우를 완성했을 때
			int score1 = 0;
			int score2 = 0;
			for(int i=0; i<9; i++) {
				if(card1[i]>numbers[i]) {	// 규영이카드와 새로 만든 카드를 하나씩 비교
					score1 += card1[i]+numbers[i];	// 규영이카드가 더 크면 두카드의 합을 규영이 총점에 저장
				} else {
					score2 += card1[i]+numbers[i];	// 인영이카드가 더 크면 두카드의 합을 인영이 총점에 저장
				}
			}
			if(score1>score2) win++;	// 규영이 총점이 높다면, 이긴 횟수 증가
			else lose++;				// 규영이 총점이 낮다면, 진 횟수 증가
			return;
		}
		for(int i=0; i<9; i++) {	// 인영이의 카드 정보를 이용해서 9!개의 경우 만들기 
			if(isSelected[i]) continue;
			
			numbers[cnt] = card2[i];
			isSelected[i] = true;
			permutation(cnt+1);
			isSelected[i] = false;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
//		System.setIn(new FileInputStream("res/swea_6808.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int testcase=1; testcase<=T; testcase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			card1 = new int[9];
			card2 = new int[9];
			
			// 규영이 카드 정보 저장
			for(int i=0; i<9; i++) {
				card1[i] = Integer.parseInt(st.nextToken());
			}
				
			// 규영이가 가지고있는 카드 체크
			int[] tmp = new int[19];
			for(int i=0; i<9; i++) {
				tmp[card1[i]] = 1;
			}
			
			// 1~18 중에 규영이가 가지고있지 않은 숫자 카드이면 인영이 카드 정보에 저장
			int idx=0;
			for(int i=1; i<19; i++) {
				if(tmp[i]!=1) {
					card2[idx++] = i;
				}
			}

			win=0;
			lose=0;
			numbers = new int[9];
			isSelected = new boolean[9];
			
			permutation(0);
			System.out.println("#"+testcase+" "+win+" "+lose);
		}
	}
}
