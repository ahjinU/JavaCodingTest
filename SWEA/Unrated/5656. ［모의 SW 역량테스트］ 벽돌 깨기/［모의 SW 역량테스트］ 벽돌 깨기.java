import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int T, N, W, H, board[][], init[][], visited[][], result;
	static List<int[]> list;

	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());

		for (int tc = 0; tc < T; tc++) {
			result = Integer.MAX_VALUE;

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 벽돌 개수
			W = Integer.parseInt(st.nextToken());// 너비
			H = Integer.parseInt(st.nextToken());// 높이

			board = new int[H + 1][W];
			init = new int[H + 1][W];
			visited = new int[H + 1][W];

			for (int i = 1; i < H + 1; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					board[i][j] = init[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			dfs(board, 0);

			if (result == Integer.MAX_VALUE)
				result = 0;

			sb.append("#").append(tc + 1).append(" ").append(result).append("\n");

		}

		System.out.println(sb);

	}

	private static void dfs(int[][] arr, int cnt) {

//		if(cnt==1) System.out.println("*     "+cnt);
//		else System.out.println(cnt);
////		if(cnt==2) {
//			for (int i = 1; i < H+1; i++) {
//				for (int j = 0; j < W; j++) {
//					System.out.print(arr[i][j]);
//				}
//				System.out.println();
//			}
//			System.out.println();
//		}
//		for (int i = 1; i < H+1; i++) {
//			for (int j = 0; j < W; j++) {
//				System.out.print(arr[i][j]);
//			}
//			System.out.println();
//		}
//		System.out.println();

		if (cnt == N) {
//				if(result>count(arr)){
//					for (int i = 1; i < H+1; i++) {
//						for (int j = 0; j < W; j++) {
//							System.out.print(arr[i][j]);
//						}
//						System.out.println();
//					}
//					System.out.println();
//				}
			result = Math.min(result, count(arr));
			return;
		}

		for (int j = 0; j < W; j++) {
			for (int i = 1; i < H + 1; i++) {
				if (arr[i][j] > 0 && arr[i - 1][j] == 0) {
					list = new ArrayList<>();

					int[][] tmp = new int[H + 1][W];
					for (int y = 1; y < H + 1; y++) {
						for (int x = 0; x < W; x++) {
							tmp[y][x] = arr[y][x];
						}
					}

					crush(i, j, tmp, tmp[i][j] - 1);
					down(tmp);

//					for (int m = 1; m < H + 1; m++) {
//						for (int n = 0; n < W; n++) {
//							System.out.print(arr[m][n]);
//						}
//						System.out.println();
//					}
//					System.out.println();

					dfs(tmp, cnt + 1);
				}
			}
		}

	}

	// 남은 벽돌 세기
	private static int count(int[][] arr) {
		int cnt = 0;
		for (int i = 1; i < H + 1; i++) {
			for (int j = 0; j < W; j++) {
				if (arr[i][j] > 0)
					cnt++;
			}
		}
		return cnt;
	}

	private static void down(int[][] arr) {
		for (int j = 0; j < W; j++) {
			for (int i = H; i >= 1; i--) {
				if (arr[i][j] == 0) {
					int curI = i;
					while (curI > 0 && arr[curI][j] == 0) {
						curI--;
					}
					arr[i][j] = arr[curI][j];
					arr[curI][j] = 0;
				}
			}
		}
	}

	// 깨기
	private static void crush(int y, int x, int[][] arr, int cur) {
		arr[y][x] = 0; // 부수기

		for (int dir = 0; dir < 4; dir++) { // 네방향으로
			int curX = x; // 기준점
			int curY = y;
			for (int i = 1; i <= cur; i++) {// 개수만큼
				int nextX = curX + dx[dir];
				int nextY = curY + dy[dir];

				if (nextX < 0 || nextX >= W || nextY < 1 || nextY >= H + 1)
					break; // 범위 넘어가면

//				System.out.println((arr[nextY][nextX] - 1)+" "+(cur - i));

				if (arr[nextY][nextX] >= arr[curY][curX]) {
					// 더 깨줘야할때
					crush(nextY, nextX, arr, arr[nextY][nextX] - 1);
				}

				arr[nextY][nextX] = 0;
				curX = nextX;
				curY = nextY;

			}
		}

	}
}
