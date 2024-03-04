import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static int N,T;
	static int[][] dp, board;
	static int[] dx = {-1,-2,-1,-2};
	static int[] dy = {-1,-1,1,1};
	
	public static void main(String args[]) throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		T = Integer.parseInt(st.nextToken());
		
		for(int tc = 0;tc<T;tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			dp = new int [2][N];
			board = new int[2][N];
			
			for(int i=0;i<2;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int j=0;j<N;j++) {
				for(int i=0;i<2;i++) {
					int max = -1;
					for(int dir=0;dir<4;dir++) {
						int beforeX = j + dx[dir];
						int beforeY = i + dy[dir];
						
						if(beforeX<0||beforeX>=N||beforeY>=2||beforeY<0) continue;
						
						max = Math.max(max, dp[beforeY][beforeX]);
					}
					dp[i][j] = Math.max(board[i][j], board[i][j] + max);
				}
			}
			
			int result = -1;
			for(int i=0;i<2;i++) {
				for(int j=0;j<N;j++) {
					result = Math.max(result, dp[i][j]);
				}
			}
			
			sb.append(result).append("\n");
			
		}
		System.out.println(sb);
		
	}
	
}	