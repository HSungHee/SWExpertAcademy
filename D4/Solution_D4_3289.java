package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution_D4_3289 {
    static int n;
    static int[] parents;
     
    private static void makeSet() {
        // 모든 원소를 자신을 대표자로 만듦
        for(int i=0; i<n; i++) {
            parents[i] = i;
        }
    }
    // curr가 속한 집합의 대표자 찾기
    private static int find(int curr) {
        if(curr==parents[curr]) return curr;    // 자신이 자신의 대표자인 경우
         
        return parents[curr] = find(parents[curr]); // 자신이 속한 집합의 대표자를 자신의 부모로 : pass compression
    }
     
    // 두 원소를 하나의 집합으로 합치기(대표자를 이용해서 합침)
    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return false;    // 이미 같은 대표자(같은 집합)를 가진다면 합치지 않음
         
        parents[bRoot] = aRoot;
        return true;
    }
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
         
        for(int testcase=1; testcase<=T; testcase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            StringBuilder answer = new StringBuilder();
             
            parents = new int[n];
             
            makeSet();
             
            for(int i=0; i<m; i++) {
                st = new StringTokenizer(br.readLine());
                int operator = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;               
                 
                // union 연산 수행
                if(operator==0) {
                    union(a, b);
                } else {
                    if(find(a) == find(b)) {
                        answer.append(1);
                    } else {
                        answer.append(0);
                    }
                }
            }
            System.out.println("#"+testcase+" "+answer.toString());
        }
    }
}