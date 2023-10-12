import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int cnt, N, M, dp[][], result = Integer.MAX_VALUE;
	static int rx, ry, ox, oy, bx, by;
	static char board[][];
	static int visited[][][][];

	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {

		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new char[N][M];
		visited = new int[N][M][N][M];

		// 배열 받기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			board[i] = st.nextToken().toCharArray();
			for (int j = 0; j < M; j++) {
				if (board[i][j] == 'R') {
					ry = i;
					rx = j;
				} else if (board[i][j] == 'B') {
					by = i;
					bx = j;
				} else if (board[i][j] == 'O') {
					oy = i;
					ox = j;
				}
			}
		}

		bfs(ry, rx, by, bx, 0);

		if (result == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else
			System.out.println(result);

	}

	private static void bfs(int ry, int rx, int by, int bx, int cnt) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { ry, rx, by, bx, cnt });
		visited[ry][rx][by][bx] = 1;

		int nry = 0, nrx = 0, nbx = 0, nby = 0, curCnt = 0;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
//			System.out.println(cur[0] + " " + cur[1] + " " + cur[2] + " " + cur[3] + " " + cur[4]);
			for (int i = 0; i < 4; i++) {
				nry = cur[0];
				nrx = cur[1];
				nby = cur[2];
				nbx = cur[3];
				curCnt = cur[4];

				if (curCnt >= 10) {
					return;
				}

				while (board[nry + dy[i]][nrx + dx[i]] != '#') {
					nry += dy[i];
					nrx += dx[i];
					if (board[nry][nrx] == 'O')
						break;
				}

				while (board[nby + dy[i]][nbx + dx[i]] != '#') {
					nby += dy[i];
					nbx += dx[i];
					if (board[nby][nbx] == 'O')
						break;
				}

				if (board[nby][nbx] == 'O') {
					continue;
				}

				if (board[nry][nrx] == 'O') {
					result = Math.min(result, curCnt + 1);
					return;
				}

				if (nry == nby && nbx == nrx) {
					// 겹치면
					int rdis = Math.abs(nrx - cur[1]) + Math.abs(nry - cur[0]);
					int bdis = Math.abs(nbx - cur[3]) + Math.abs(nby - cur[2]);

					if (rdis < bdis) {// 빨강 먼저
						nbx -= dx[i];
						nby -= dy[i];
					} else {
						nrx -= dx[i];
						nry -= dy[i];
					}
				}

				if (visited[nry][nrx][nby][nbx] == 0) {
					visited[nry][nrx][nby][nbx] = 1;
					q.add(new int[] { nry, nrx, nby, nbx, curCnt + 1 });
				}

			}

		}

	}

}