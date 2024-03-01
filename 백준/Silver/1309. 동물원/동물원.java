import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static int N;
	static long[][][] dp;
	
	public static void main(String args[]) throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		dp = new long [N][2][2];
		
		dp[0][0][0] = 1;
		dp[0][0][1] = 1;
		dp[0][1][0] = 2;
		dp[0][1][1] = 1;
		
		for(int i=1;i<N;i++) {
			 dp[i][0][0] = dp[i-1][1][0] + dp[i-1][1][1] ;
			 dp[i][0][1] = (dp[i][0][0] - dp[i-1][0][1] + 9901) % 9901;
			    
			 dp[i][1][0] = dp[i][0][0] + dp[i][0][1];
			 dp[i][1][1] = (dp[i][1][0] - dp[i-1][1][1] - dp[i][0][1] + 9901) % 9901;
		}
		
//		for(int i=0;i<100;i++) {
//			for(int j=0;j<2;j++) {
//				for(int n=0;n<2;n++) {
//					System.out.print(dp[i][j][n]+" ");
//				}
//				System.out.print(" ");
//			}
//			System.out.println();
//
//		}
		
		
		System.out.println((dp[N-1][1][1]+dp[N-1][1][0])%9901);
		
		
	}
	
}	