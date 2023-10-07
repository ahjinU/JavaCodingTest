
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int T, N, board[][], result, coreInd, maxCoreCnt, minLineCnt;
	static int xArr[], yArr[], visited[];
	static List<List<Integer>> list;
	// 상우하좌
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());

		for (int tc = 0; tc < T; tc++) {
			maxCoreCnt = -1;
			minLineCnt = Integer.MAX_VALUE;
			result = 0;

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			board = new int[N][N];
			
			// 최대 13개 배열 새로 만들기
			xArr = new int[13];
			yArr = new int[13];
			coreInd = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int num = Integer.parseInt(st.nextToken());
					if (num == 1) {
						board[i][j] = num;
						if (i >= 1 && i < N - 1 && j >= 1 && j < N - 1) {
							xArr[coreInd] = j;
							yArr[coreInd++] = i;
						}
					}
				}
			}

			minLineCnt = Integer.MAX_VALUE;
			maxCoreCnt = -1;

			calc(0, 0, 0);

			sb.append("#").append(tc + 1).append(" ").append(minLineCnt).append("\n");
		}
		System.out.println(sb);
	}

	private static void calc(int ind, int coreCnt, int lineCnt) {
		if (ind == coreInd) {
			if (coreCnt > maxCoreCnt) {
				maxCoreCnt = coreCnt;
				minLineCnt = lineCnt;
			} else if (coreCnt == maxCoreCnt) {
				minLineCnt = Math.min(minLineCnt, lineCnt);
			}
			return;
		}

		int curX = xArr[ind];
		int curY = yArr[ind];

		for (int i = 0; i < 4; i++) {
			int cnt = 0;
			int nextX = curX;
			int nextY = curY;

			while (true) {
				nextX += dx[i];
				nextY += dy[i];

				if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) {
					break;
				}

				if (board[nextY][nextX] != 0) {
					cnt = 0;
					break;
				}

				cnt++;
			}

			int possX = curX;
			int possY = curY;

			for (int j = 0; j < cnt; j++) {
				possX += dx[i];
				possY += dy[i];
				board[possY][possX] = 1;
			}

			if (cnt == 0)
				calc(ind + 1, coreCnt, lineCnt);
			else {
				calc(ind + 1, coreCnt + 1, lineCnt + cnt);

				possX = curX;
				possY = curY;

				for (int j = 0; j < cnt; j++) {
					possX += dx[i];
					possY += dy[i];
					board[possY][possX] = 0;
				}
			}

		}

	}

}